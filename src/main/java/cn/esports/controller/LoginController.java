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

import cn.esports.service.UserService;
import cn.esports.utils.Constants;

@RestController
public class LoginController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/sendMobileCode", method = RequestMethod.POST)
	public void sendMobileCode(String mobile) {
		userService.sendMobileCode(mobile);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(String mobile, String code) {
		Subject subject = SecurityUtils.getSubject();  
	    UsernamePasswordToken token = new UsernamePasswordToken(mobile, code); 
	    try {  
	        subject.login(token);  
	    } catch (Exception e) {  
	    	logger.error("login occurred error,cause by:",e);
	    } 
	}
}
