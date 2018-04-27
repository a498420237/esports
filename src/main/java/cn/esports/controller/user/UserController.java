package cn.esports.controller.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserController {
	
	@RequestMapping(value = "/user/index", method = RequestMethod.GET)
	public ModelAndView sendMobileCode(String mobile) {
		return new ModelAndView("user/index");
	}
	
	@RequestMapping(value = "/user/suc", method = RequestMethod.GET)
	public ModelAndView suc(String mobile) {
		return new ModelAndView("index");
	}
}
