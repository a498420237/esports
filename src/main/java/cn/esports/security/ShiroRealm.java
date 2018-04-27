package cn.esports.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import cn.esports.service.UserService;
import cn.esports.utils.Constants;

public class ShiroRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		SecurityUtils.getSubject().logout();// 覆盖登录
		String mobile = (String) token.getPrincipal();
		char[] ch = (char[]) token.getCredentials();
		String code = new String(ch);
		String tokenStr= userService.getToken(mobile, code);
		if (StringUtils.isEmpty(tokenStr)) {
			return null;
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(mobile, code, getName());
		return authenticationInfo;
	}
}
