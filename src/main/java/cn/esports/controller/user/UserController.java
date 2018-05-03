package cn.esports.controller.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import cn.esports.entity.UserInfo;
import cn.esports.service.UserService;
import cn.esports.utils.SessionUtil;
/**
 * 
 * @author huzhimin
 *
 */
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/user/index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView view =new ModelAndView("user/index");
		//view.addObject("userinfo",JSON.parseObject(SessionUtil.getCurUser(),UserInfo.class));
		return view;
	}
	
	@RequestMapping(value = "/user/saveInfo", method = RequestMethod.GET)
	public JSONObject saveInfo(@RequestParam Map<String, String> uriVariables) {
		return userService.saveUserInfo(uriVariables);
	}
	@RequestMapping(value = "/user/bindMobile", method = RequestMethod.POST)
	public JSONObject bindMobile(@RequestParam Map<String, String> uriVariables) {
		UserInfo userInfo=JSONObject.parseObject(SessionUtil.getCurUser(),UserInfo.class);
		uriVariables.put("type", userInfo.getT().isMobileBound()==true?"2":"1");
		return userService.BindMobile(uriVariables);
	}
	@RequestMapping(value = "/user/bindEmail", method = RequestMethod.POST)
	public JSONObject bindEmail(@RequestParam Map<String, String> uriVariables) {
		UserInfo userInfo=JSONObject.parseObject(SessionUtil.getCurUser(),UserInfo.class);
		uriVariables.put("type", userInfo.getT().isEmailBound()==true?"2":"1");
		return userService.BindEmail(uriVariables);
	}
	
	@RequestMapping(value = "/user/delUserPicture", method = RequestMethod.POST)
	public JSONObject delUserPicture(@RequestParam Map<String, String> uriVariables) {
		return userService.delUserPicture(uriVariables);
	}
	@RequestMapping(value = "/user/getUserBindGame", method = RequestMethod.GET)
	public JSONObject getUserBingGameAccess(@RequestParam Map<String, String> uriVariables) {
		return userService.getUserBingGameAccess(uriVariables);
	}
	@RequestMapping(value = "/user/getGameArea", method = RequestMethod.GET)
	public JSONObject getGameArea(@RequestParam Map<String, String> uriVariables) {
		return userService.getGameArea(uriVariables);
	}
	@RequestMapping(value = "/user/getGameRanks", method = RequestMethod.GET)
	public JSONObject getGameRanks(@RequestParam Map<String, String> uriVariables) {
		return userService.getGameRanks(uriVariables);
	}
	
	@RequestMapping(value = "/user/getGameInfo", method = RequestMethod.GET)
	public JSONObject getGameInfo(@RequestParam Map<String, String> uriVariables) {
		return userService.getGameInfo(uriVariables);
	}
	
	@RequestMapping(value = "/user/UserBindgameAccess", method = RequestMethod.GET)
	public JSONObject UserBindgameAccess(@RequestParam Map<String, String> uriVariables) {
		return userService.UserBindgameAccess(uriVariables);
	}
	
	@RequestMapping(value = "/user/suc", method = RequestMethod.GET)
	public ModelAndView suc(String mobile) {
		return new ModelAndView("index");
	}
	
	
	@RequestMapping(value = "/user/submit", method = RequestMethod.POST)
	public JSONObject usersubmit( String url, MultipartFile file) {
		return userService.addUserPicture(url,file);
	}
	
	
	
}
