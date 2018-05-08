package cn.esports.controller.user;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cn.esports.config.AlipayConfig;
import cn.esports.entity.PayInfo;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.esports.controller.BaseController;
import cn.esports.service.PayService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 支付
 * <p>Title: PayController</p>
 * <p>Description: </p>
 *
 * @author zhimin.hu
 * @date 2018年5月7日
 */
@RestController
public class PayController extends BaseController {
    @Autowired
    private PayService payService;

    @RequestMapping(value = "/user/pay", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, AlipayApiException {
        ModelAndView view = new ModelAndView("user/pay");


        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();

        if (requestParams != null && requestParams.size() > 0) {

            for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用
                valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                params.put(name, valueStr);
            }

            boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGNTYPE); //调用SDK验证签名

            if (signVerified) {
                //商户订单号
                String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

                //支付宝交易号
                String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

                //付款金额
                String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

                view.addObject("out_trade_no", out_trade_no);
                view.addObject("trade_no", trade_no);
                view.addObject("total_amount", total_amount);
                view.addObject("code", 200);
                view.addObject("msg", "支付成功");


            } else {
                view.addObject("code", -1);
                view.addObject("msg", "验签失败");
            }
        }

        view.addObject("code", 0);

        return view;

    }

    @RequestMapping(value = "/user/pay/activitylist", method = RequestMethod.GET)
    public JSONObject getList(@RequestParam Map<String, String> uriVariables) {
        return payService.getActivityList(uriVariables);
    }

    //微信支付
    //@RequestMapping(value = "/user/pay/wxpay", method = RequestMethod.POST)
    //public JSONObject payWeixin(@RequestParam Map<String, String> uriVariables) {
    // return payService.createOrder(uriVariables, "wxpay");
    //}

    //阿里支付
    @RequestMapping(value = "/user/pay/alipay", method = RequestMethod.POST)
    public JSONObject payAlipay(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, String> uriVariables) throws AlipayApiException {

        //ModelAndView view = new ModelAndView("alipay");
        // ModelAndView view = new ModelAndView("redirect:/user/pay/alipay");

        JSONObject result = new JSONObject();


        PayInfo payInfo = payService.createOrder(uriVariables, "alipay");

        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        //商户订单号，商户网站订单系统中唯一订单号，必填
        if (payInfo.getT() != null) {
            String out_trade_no = payInfo.getT().getOrderId();

            // 订单名称，必填
            String subject = "充值金额" + uriVariables.get("rmb");
            // 付款金额，必填
            String total_amount = uriVariables.get("rmb");
            // 商品描述，可空
            String body = uriVariables.get("body");
            // 封装请求支付信息
            AlipayTradePagePayModel model = new AlipayTradePagePayModel();
            model.setOutTradeNo(out_trade_no);
            model.setSubject(subject);
            model.setTotalAmount(total_amount);
            model.setBody(body);
            model.setTimeoutExpress("");
            // 销售产品码 必填
            model.setProductCode("FAST_INSTANT_TRADE_PAY");
            alipayRequest.setBizModel(model);
            // 调用SDK生成表单
            String form = alipayClient.pageExecute(alipayRequest).getBody();

            result.put("code", 200);
            result.put("form", form);
            return result;

        }
        result.put("code", 0);
        return result;

    }

    @RequestMapping(value = "/user/pay/alipayresult", method = RequestMethod.GET)
    public ModelAndView alipayres(@RequestParam Map<String, String> uriVariables) {
        return new ModelAndView("user/alipay");
    }

}
