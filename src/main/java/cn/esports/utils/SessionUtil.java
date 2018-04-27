package cn.esports.utils;

import java.util.Date;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.springframework.util.StringUtils;

import cn.esports.entity.SimpleUser;

public class SessionUtil {
	
	private static int sessionTimeout = 20 * 60;// session失效时间，单位秒
	private static ConcurrentHashMap<String, Session> sessionMap = new ConcurrentHashMap<String, Session>();

	/**
	 * 获取当前会话session
	 * 
	 * @return
	 */
	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	/**
	 * 设置当前登录用户
	 * 
	 * @param user
	 */
	public static void setCurUser(SimpleUser user) {
		if (user != null) {
			newRequestTimer();
			sessionMap.put(user.getUsername(), getSession());
		}
		SecurityUtils.getSubject().getSession(true).setAttribute(Constants.KEY_USER, user);
	}

	/**
	 * 获取当前登录用户
	 * 
	 * @return
	 */
	public static SimpleUser getCurUser() {
		return (SimpleUser) SecurityUtils.getSubject().getSession(true).getAttribute(Constants.KEY_USER);
	}

	/**
	 * 判断是否登录
	 * 
	 * @return
	 */
	public static boolean isLogin() {
		SimpleUser user = getCurUser();
		return user != null && !StringUtils.isEmpty(user.getUsername());
	}

	/**
	 * 更新Session请求时间
	 */
	public static void newRequestTimer() {
		SecurityUtils.getSubject().getSession(true).setAttribute(Constants.REQUEST_TIMER, System.currentTimeMillis());
	}

	/**
	 * 判断session是否已经过期
	 * 
	 * @return
	 */
	public static boolean isSessionTimeout() {
		Long requestTimer = (Long) SecurityUtils.getSubject().getSession(true).getAttribute(Constants.REQUEST_TIMER);
		if (requestTimer == null || getCurUser() == null) {
			return true;
		}
		return requestTimer < System.currentTimeMillis() - sessionTimeout * 1000;
	}

	/**
	 * 设置验证码
	 * 
	 * @param imageVerificationCode
	 */
	public static void setImageVerificationCode(String type, String imageVerificationCode) {
		SecurityUtils.getSubject().getSession(true).setAttribute(Constants.IMAGE_VERIFICATION_CODE + type,
				imageVerificationCode);
	}

	/**
	 * 获取验证码
	 * 
	 * @return
	 */
	public static String getImageVerificationCode(String type) {
		String code = (String) SecurityUtils.getSubject().getSession(true)
				.getAttribute(Constants.IMAGE_VERIFICATION_CODE + type);
		setImageVerificationCode(type, null);// 清理验证码，避免重复使用
		return code;
	}


	/**
	 * 清理在线session数据
	 */
	public static void cleanSessionMap() {
		Iterator<Entry<String, Session>> sessions = sessionMap.entrySet().iterator();
		while (sessions.hasNext()) {
			Entry<String, Session> entry = sessions.next();
			Session session = entry.getValue();
			try {
				Date lastAccessTime = session.getLastAccessTime();
				if (lastAccessTime != null) {
					Date timeOutTime = new Date(lastAccessTime.getTime() + sessionTimeout * 1000);
					if ((new Date()).after(timeOutTime)) {
						sessions.remove();
					}
				}
			} catch (UnknownSessionException e) {
				sessions.remove();
			}
		}
	}

	/**
	 * 判断session是否互斥，即同一账号被异地登录
	 * 
	 * @return
	 */
	public static boolean isMutualSession() {
		Session session1 = getSession();
		Session session2 = sessionMap.get(getCurUser().getUsername());
		if (session1 == null || session2 == null || !session1.getId().equals(session2.getId())) {
			return true;
		}
		return false;
	}

	/**
	 * 设置过时时间
	 * 
	 * @param sessionTimeout
	 */
	public static void setSessionTimeout(int sessionTimeout) {
		SessionUtil.sessionTimeout = sessionTimeout;
	}
}
