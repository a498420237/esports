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
		ModelAndView view =null;
		//view.addObject("userLogin", fService.GetForecastList(1, 15));
		JSONObject jsonObject =myWarteamService.getUserTeamList("",uriVariables);
		String code = jsonObject.getString("code");
		if("200".equals(code.toString())){
			JSONObject t = jsonObject.getJSONObject(("t"));
			JSONArray list = t.getJSONArray("troops");
			if(list.size()==0){
			//if(true){
				view=new ModelAndView("user/myWarTeamNothing");
			}else{
				JSONObject jsonObject2 = list.getJSONObject(0);
				if(list.size()==1){
					//有一个战队为空
					int gameType = Integer.parseInt(jsonObject2.getString("gameType"));
					if(gameType==1){
						//1 王者荣耀
						view=new ModelAndView("user/myWarTeamWz");
					}else {
						//2 绝地求生
						view=new ModelAndView("user/myWarTeamOneJd");
					}

				}else{
					//都有
					view=new ModelAndView("user/myWarTeam");
				}
			}
		}
		return view;
	}

	/**
	 * 获取用户战队列表
	 * @param uriVariables
	 * @return
	 */
	@RequestMapping(value="/user/myWarTeam/list", method = RequestMethod.GET)
	public JSONObject getList(@RequestParam Map<String, String> uriVariables){
		return myWarteamService.getUserTeamList("",uriVariables);
	}

	/**
	 * 获取战队列表(无战队)
	 * @param uriVariables
	 * @return
	 */
	@RequestMapping(value="/user/myWarTeam/nothingList", method = RequestMethod.GET)
	public JSONObject getNotingList(@RequestParam Map<String, String> uriVariables){
		return myWarteamService.getAllUserTeamList("",uriVariables);
	}
}
