package cn.esports.controller.user;

import cn.esports.controller.BaseController;
import cn.esports.service.MyWarteamService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 个人中心-我的战队
 * @author sdw
 *
 */
@RestController
public class MyWarTeamController extends BaseController {

	@Autowired
	public MyWarteamService myWarteamService;

	@RequestMapping(value="/user/myWarTeam", method = RequestMethod.GET)
	public ModelAndView forecast(@RequestParam Map<String, String> uriVariables) {
		ModelAndView view =new ModelAndView("user/myWarTeam");
		//view.addObject("userLogin", fService.GetForecastList(1, 15));
		JSONObject jsonObject =myWarteamService.getUserTeamList("",uriVariables);
		String code = jsonObject.getString("code");
		if("200".equals(code.toString())){
			JSONObject t = jsonObject.getJSONObject(("t"));
			JSONArray list = t.getJSONArray("troops");
		}
		return view;
	}

	@RequestMapping(value="/user/myWarTeam/list", method = RequestMethod.GET)
	public JSONObject getList(@RequestParam Map<String, String> uriVariables){
		return myWarteamService.getUserTeamList("",uriVariables);
	}
}
