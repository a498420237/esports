package cn.esports.controller.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.esports.controller.BaseController;
import cn.esports.service.PayService;
import cn.esports.service.UserService;

/**
 * 支付
* <p>Title: PayController</p>  
* <p>Description: </p>  
* @author zhimin.hu  
* @date 2018年5月7日
 */
@RestController
public class PayController extends BaseController{
	@Autowired
	private PayService payService;
	
	@RequestMapping(value = "/user/pay", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView view =new ModelAndView("user/pay");
		//view.addObject("userinfo",JSON.parseObject(SessionUtil.getCurUser(),UserInfo.class));
		return view;
	}
	
	@RequestMapping(value="/user/pay/activitylist", method = RequestMethod.GET)
	public JSONObject getList(@RequestParam Map<String, String> uriVariables){
		return payService.getActivityList(uriVariables);
	}
	
	
}
