package cn.esports.controller.user;

import cn.esports.controller.BaseController;
import cn.esports.service.MyMatchService;
import cn.esports.service.PersonalWealthService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 个人中心-我的赛事
 * @author sdw
 *
 */
@RestController
public class MyMatchController extends BaseController {

	@Autowired
	public MyMatchService myMatchService;

	@RequestMapping(value="/user/myMatch", method = RequestMethod.GET)
	public ModelAndView forecast() {
		ModelAndView view =new ModelAndView("user/myMatch");
		//view.addObject("userLogin", fService.GetForecastList(1, 15));
		return view;
	}
	
	@RequestMapping(value="/user/myMatch/list")
	public JSONObject getList(@RequestParam Map<String, String> uriVariables){
		return myMatchService.getMyMatchList("",uriVariables);
	}
	@RequestMapping(value="/user/myMatch/oldlist")
	public JSONObject getOldList(@RequestParam Map<String, String> uriVariables){
		return myMatchService.getMyMatchOldList("",uriVariables);
	}
	@RequestMapping(value="/user/myMatch/info", method = RequestMethod.GET)
	public JSONObject getInfo(@RequestParam Map<String, String> uriVariables){
		return myMatchService.getMyMatchInfo("",uriVariables);
	}
}
