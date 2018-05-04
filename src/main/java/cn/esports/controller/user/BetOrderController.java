package cn.esports.controller.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.esports.controller.BaseController;
import cn.esports.service.BetOrderService;
import cn.esports.service.PersonalWealthService;
/**
 * 个人中心-我的竞猜
 * @author huzhimin
 *
 */
@RestController
public class BetOrderController extends BaseController {


	@Autowired
	public BetOrderService betOrderService;
	
	@RequestMapping(value="/user/BetOrder", method = RequestMethod.GET)
	public ModelAndView forecast() {
		ModelAndView view =new ModelAndView("user/betOrder");
		//view.addObject("userLogin", fService.GetForecastList(1, 15));
		return view;
	}
	
	@RequestMapping(value="/user/BetOrder/list", method = RequestMethod.GET)
	public JSONObject getList(@RequestParam Map<String, String> uriVariables){
		return betOrderService.getBetOrderList("",uriVariables);
	}

	@RequestMapping(value="/user/BetOrder/saveBet", method = RequestMethod.GET)
	public JSONObject saveBet(@RequestParam Map<String, String> uriVariables){
		return betOrderService.saveBet(uriVariables);
	}
	
	
}
