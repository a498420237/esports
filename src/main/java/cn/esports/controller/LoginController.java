package cn.esports.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.esports.service.UserService;
import cn.esports.utils.Constants;

@RestController
public class LoginController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView sendMobileCode() {
		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "/sendMobileCode", method = RequestMethod.POST)
	public JSONObject sendMobileCode(String mobile) {
		return userService.sendMobileCode(mobile);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public JSONObject login(String mobile, String code) {
		Subject subject = SecurityUtils.getSubject();  
		   JSONObject jsonObject=new JSONObject();
	    UsernamePasswordToken token = new UsernamePasswordToken(mobile, code); 
	    try {  
	        subject.login(token); 
	        jsonObject.put("code", 200);
	        jsonObject.put("code", "登录成功");
	        return jsonObject;
	    } catch (Exception e) {  
	    	logger.error("login occurred error,cause by:",e);
	    	jsonObject.put("code", 100);
	    	jsonObject.put("msg", "验证码效验失败，请重新输入");
	    	return jsonObject;
	    }
		//return  jsonObject;
	}
}
