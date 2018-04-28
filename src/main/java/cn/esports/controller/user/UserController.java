package cn.esports.controller.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.util.JSONPObject;

import cn.esports.entity.UserInfo;
import cn.esports.utils.SessionUtil;

@RestController
public class UserController {
	
	@RequestMapping(value = "/user/index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView view =new ModelAndView("user/index");
		view.addObject("userinf",JSON.parseObject(SessionUtil.getCurUser(),UserInfo.class));
		return view;
	}
	
	@RequestMapping(value = "/user/suc", method = RequestMethod.GET)
	public ModelAndView suc(String mobile) {
		return new ModelAndView("index");
	}
}
