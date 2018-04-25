package cn.esports.entity;


public class UserLogin {

	private int code;
    private String msg;
    private String t;
    private String token;
    private int uid;
    private String userName;
    
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getT() {
		return t;
	}
	public void setT(String t) {
		this.t = t;
	}
    
}
