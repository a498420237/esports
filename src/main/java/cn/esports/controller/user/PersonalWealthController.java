package cn.esports.controller.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.esports.config.BaseConfig;
import cn.esports.controller.BaseController;
import cn.esports.service.ForecastService;
import cn.esports.service.PersonalWealthService;
import cn.esports.utils.SessionUtil;
/**
 * 个人中心-消费记录
 * @author huzhimin
 *
 */
@RestController
public class PersonalWealthController extends BaseController {

	@Autowired
	public PersonalWealthService personalWealthService;
	
	@RequestMapping(value="/user/PersonalWealth", method = RequestMethod.GET)
	public ModelAndView forecast() {
		ModelAndView view =new ModelAndView("user/personalWealth");
		//view.addObject("userLogin", fService.GetForecastList(1, 15));
		return view;
	}
	
	@RequestMapping(value="/user/PersonalWealth/list", method = RequestMethod.GET)
	public JSONObject getList(@RequestParam Map<String, String> uriVariables){
		return personalWealthService.getPersonalWealthInfo("",uriVariables);
	}
}
