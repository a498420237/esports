package cn.esports.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import cn.esports.cache.Cache;
import cn.esports.entity.Permission;
import cn.esports.entity.Role;
import cn.esports.entity.UserInfo;

public class MyShiroRealm extends AuthorizingRealm {

	@Autowired
	private Cache cache;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		UserInfo user = (UserInfo) principals.getPrimaryPrincipal();
		for (Role role : user.getRoles()) {
			authorizationInfo.addRole(role.getRoleName());
			for (Permission permission : role.getPermissions()) {
				authorizationInfo.addStringPermission(permission.getPath());
			}
		}
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String userName = (String) token.getPrincipal();
		UserInfo userInfo = cache.findByUserName(userName);
		if (userInfo == null) {
			return null;
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				userInfo, userInfo.getPassword(), getName());
		return authenticationInfo;
	}
}
