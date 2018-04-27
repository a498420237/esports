package cn.esports.enums;

public enum SendType {
	LOGIN("login"),
	BINDMOBILE("bindMobile"),
	RESETBINDMOBILE("resetBindMobile"),
	RESETBINDEAMIL("resetBindEamil"),
	TRANSFERAPTAIN("transferaptain"),
	CREATETROOP("createTroop");
	
	private final String index;

	SendType(String index) {
        this.index = index;
    }
    public String getIndex() {
        return index;
    }
	//发送类型（登录注册：login 绑定手机号：bindMobile 更换手机号：resetBindMobile 更换邮箱：resetBindEamil 转移队长：transferaptain 创建战队：createTroop）
}
