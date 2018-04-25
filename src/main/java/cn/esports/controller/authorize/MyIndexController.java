package cn.esports.controller.authorize;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import cn.esports.entity.UserInfo;
import cn.esports.service.UserInfoService;

/**
 * 
 * <p>
 * Title: MyIndexController
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author zhimin.hu
 * @date 2018年4月24日
 */
@RestController
public class MyIndexController extends BaseController {

	@Autowired
	public UserInfoService uService;

	@RequestMapping(value = "/myInfo", method = RequestMethod.GET)
	public ModelAndView myInfo(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mView = new ModelAndView();
		try {
			if (SecurityUtils.getSubject() != null) {
				 String token= "F61604A9164AEB77442920D37634E3A623F9BC11";
				 UserInfo.TBean userInfo=uService.GetUserInfo(token);
				Cookie[] cookies = request.getCookies();
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("userName")) {
						System.out.println("cookie is  userName : "+ cookie.getValue());
					}
					System.out.println("cookie is : "+ cookie.getName());
				}
				UserInfo.TBean bean = new UserInfo.TBean();
				bean.setUserName("张三");
				bean.setSex(1);
				bean.setAge(50);
				mView.setViewName("user/myInfo");
				mView.addObject("UserInfo", userInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mView;

	}

	/*
	 * @RequestMapping(value = "/login", method = RequestMethod.POST) public
	 * ModelAndView loginin(UserInfo user) { System.out.println( "POJO1: " +
	 * user.getClass().getName() + ", hash code: " + user.hashCode() + ", " +
	 * user.toString()); Subject subject = SecurityUtils.getSubject();
	 * UsernamePasswordToken token = new
	 * UsernamePasswordToken(user.getT().getMobile(), user.getT().getArea()); try {
	 * subject.login(token); } catch (Exception ex) { ex.printStackTrace(); } return
	 * new ModelAndView(new RedirectView( "user/user")); }
	 * 
	 * @RequestMapping(value = "/login", method = RequestMethod.GET) public
	 * ModelAndView login() { return new ModelAndView("login"); }
	 */

	@RequestMapping("/user/user/user-table")
	public String getUsersByPage(Map<String, Object> model, @RequestParam("page") Integer page) {

		List<UserInfo> users = new ArrayList<>();
		/*
		 * for (int i = 1; i <= 5; i++) { User user = new User(); user.setId((page - 1)
		 * * 5 + i); user.setFirstName("John"); user.setLastName("berk_" + page);
		 * users.add(user); }
		 */
		model.put("users", users);
		return "user-table";
	}
}
