package cn.esports.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.esports.entity.UserInfo;

public class Cache {
	private Map<String, UserInfo> userMap = new HashMap<String, UserInfo>();
/*	private Map<UserInfo, List<Role>> userAndRolesMap = new HashMap<UserInfo, List<Role>>();
	private Map<Role, List<Permission>> roleAndRolesMap = new HashMap<Role, List<Permission>>();*/

	public Cache() {
		/*UserInfo user = new UserInfo();
		user.setUserName("admin");
		user.setNickName("张山");
		user.setPassword( "123456");
		List<Role> roles = new ArrayList<Role>();
		Role role = new Role();
		List<Permission> permissions = new ArrayList<Permission>();
		Permission permission = new Permission();
		permission.setPath("/user/index");
		permissions.add(permission);
		role.setPermissions(permissions);
		roles.add(new Role());
		user.setRoles(roles);
		userAndRolesMap.put(user, roles);
		roleAndRolesMap.put(role, permissions);
		UserInfo user2 = new UserInfo();
		user2.setUserName("wangwu");
		user2.setNickName("王武");
		user2.setPassword( "123456");
		user2.setRoles(roles);
		userAndRolesMap.put(user2, roles);
		roleAndRolesMap.put(role, permissions);
		userMap.put(user.getUserName(), user);
		userMap.put(user2.getUserName(), user2);*/
	}

	public UserInfo findByUserName(String userName) {
		return userMap.get(userName);
	}
}
