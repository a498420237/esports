package cn.esports.controller.free;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.esports.controller.BaseController;
import cn.esports.entity.UserInfo;
import cn.esports.entity.UserLogin;
import cn.esports.enums.SendType;
import cn.esports.service.UserInfoService;

/*@RestController*/
public class LoginController extends BaseController {

	@Autowired
	public UserInfoService uService;
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView view =new ModelAndView("login");
		view.addObject("userLogin", new UserLogin());
		return view;
	}
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public ModelAndView loginin(String userName) {
		UserLogin userLogin=uService.SendSMS(userName, SendType.LOGIN);
		if(200==userLogin.getCode()) {
		return new ModelAndView("loginIn");
		}else {
			ModelAndView view =new ModelAndView("login");
			view.addObject("userLogin", userLogin);
			return view;
		}
	}
	@RequestMapping(value="/loginIn", method = RequestMethod.GET)
	public ModelAndView loginIn2() {
		ModelAndView view =new ModelAndView("loginIn");
		view.addObject("token", new UserLogin());
		return view;
	}
	@RequestMapping(value="/loginIn", method = RequestMethod.POST)
	public ModelAndView loginIn1(HttpServletRequest request ,HttpServletResponse response,String userName,String code) {
		UserLogin token=uService.GetToken(userName, code);
		ModelAndView view =new ModelAndView("loginIn");
		UserInfo.TBean bean=new UserInfo.TBean();
		
		Cookie[] cookies = request.getCookies();
		
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("userName")) {
				System.out.println("cookie is  userName : "+ cookie.getValue());
			}
			System.out.println("cookie is : "+ cookie.getName());
		}
		if(token.getCode()==200) {
			bean=uService.GetUserInfo(token.getT());
		}
		if(bean==null)
		{
			bean.setUserName("张三");
			bean.setSex(1);
			bean.setAge(50);
			view.setViewName("loginIn");
		}else {
			view.setViewName("user/myInfo");
		}
		
		view.addObject("UserInfo",bean);
		return view;
	}
}
