package cn.esports.controller.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.esports.controller.BaseController;
import cn.esports.entity.UserInfo;
import cn.esports.service.UserHonorService;
import cn.esports.utils.SessionUtil;

@RestController
public class UserHonorController extends BaseController {

	@Autowired
	public UserHonorService userHonorService;
	
	@RequestMapping(value="/user/UserHonor", method = RequestMethod.GET)
	public ModelAndView forecast() {
		ModelAndView view =new ModelAndView("user/userHonor");
		view.addObject("userinfo",JSON.parseObject(SessionUtil.getCurUser(),UserInfo.class));
		return view;
	}
	
	@RequestMapping(value="/user/UserHonor/list", method = RequestMethod.GET)
	public JSONObject getList(@RequestParam Map<String, String> uriVariables){
		return userHonorService.getUserHonoList("",uriVariables);
	}
}
