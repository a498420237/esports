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
	@RequestMapping(value="/user/warTeamInfo", method = RequestMethod.GET)
	public ModelAndView myWarTeam(@RequestParam Map<String, String> uriVariables,@RequestParam String id) {
		ModelAndView view =new ModelAndView("user/warTeamInfo");
		view.addObject("id",id);
		return  view;
	}
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
						view=new ModelAndView("user/myWarTeamWz");
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

	/**
	 * 获取战队荣誉 /  历史赛事
	 * @param uriVariables
	 * @return
	 */
	@RequestMapping(value="/user/myWarTeam/teamHonorOrHistoryList", method = RequestMethod.GET)
	public JSONObject getTeamHonorList(@RequestParam Map<String, String> uriVariables){
		return myWarteamService.getTeamHonorOrHistoryList("",uriVariables);
	}

	/**
	 * 战队成员
	 * @param uriVariables
	 * @return
	 */
	@RequestMapping(value="/user/myWarTeam/teamMemberList", method = RequestMethod.GET)
	public JSONObject getTeamMemberList(@RequestParam Map<String, String> uriVariables){
		return myWarteamService.getTeamMemberList("",uriVariables);
	}

	/**
	 * 退出战队
	 * @param uriVariables
	 * @return
	 */
	@RequestMapping(value="/user/myWarTeam/backTeam", method = RequestMethod.GET)
	public JSONObject backTeam(@RequestParam Map<String, String> uriVariables){
		return myWarteamService.backTeam("",uriVariables);
	}

	/**
	 * 战队详情
	 * @param uriVariables
	 * @return
	 */
	@RequestMapping(value="/user/myWarTeam/teamInfo", method = RequestMethod.GET)
	public JSONObject teamInfo(@RequestParam Map<String, String> uriVariables){
		return myWarteamService.teamInfo("",uriVariables);
	}

	/**
	 * 加入战队
	 * @param uriVariables
	 * @return
	 */
	@RequestMapping(value="/user/myWarTeam/addTeam", method = RequestMethod.GET)
	public JSONObject addTeam(@RequestParam Map<String, String> uriVariables){
		return myWarteamService.addTeam("",uriVariables);
	}

	/**
	 * 新增 修改战队
	 * @param uriVariables
	 * @return
	 */
	@RequestMapping(value="/user/myWarTeam/editTeam", method = RequestMethod.GET)
	public JSONObject editTeam(@RequestParam Map<String, String> uriVariables){
		return myWarteamService.editTeam("",uriVariables);
	}
}
