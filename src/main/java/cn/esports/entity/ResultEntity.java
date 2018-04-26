package cn.esports.entity;

public class ResultEntity {


	/**
	 * msg : 成功
	 * code : 200
	 * t : {"imagePrefix":"http://192.168.1.12:8094","sex":0,"mobile":"13816048404","mobileBound":true,"updateTime":"Apr 24, 2018 12:40:49 PM","registerType":1,"userName":"YH9985","leveling":0,"isOfficial":false,"token":"96F99289BC797EA52359D977BB46259E3C0B77E9","gold":0,"diamond":0,"createTime":"Apr 24, 2018 12:40:49 PM","imToken":"011f44ac1f745df6fc3c0f258ff8df97","id":131,"emailBound":false,"age":0,"status":1}
	 */
	private String msg;
	private int code;


	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public int getCode() {
		return code;
	}
}
