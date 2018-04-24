package cn.esports.controller.authorize;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import cn.esports.cache.Cache;
import cn.esports.controller.BaseController;
import cn.esports.entity.User;
import cn.esports.entity.UserInfo;
/**
 * 
* <p>Title: MyIndexController</p>  
* <p>Description: </p>  
* @author zhimin.hu  
* @date 2018年4月24日
 */
@RestController
public class MyIndexController extends BaseController {

	@Autowired
	public  Cache cache;
	
	@RequestMapping(value = "/user/user", method = RequestMethod.GET)
	public ModelAndView getUsers() {
		ModelAndView mView=new ModelAndView();
		try {
			if(SecurityUtils.getSubject()!=null) {
			UserInfo user= (UserInfo)SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
			//model.addAttribute("users", user);
			
			mView.setViewName("user");
			mView.addObject("users",user);
			mView.addObject(user);
			mView.addObject("welcome", "恭喜你登录成功，这个页面需要登录才可以");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return mView;
		
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginin(UserInfo user) {
		System.out.println(
				"POJO1: " + user.getClass().getName() + ", hash code: " + user.hashCode() + ", " + user.toString());
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
		try {
			subject.login(token);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView(new RedirectView( "user/user"));
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		return new ModelAndView("login");
	}

	@RequestMapping("/user/user/user-table")
	public String getUsersByPage(Map<String, Object> model, @RequestParam("page") Integer page) {

		List<User> users = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			User user = new User();
			user.setId((page - 1) * 5 + i);
			user.setFirstName("John");
			user.setLastName("berk_" + page);
			users.add(user);
		}
		model.put("users", users);
		return "user-table";
	}
}
