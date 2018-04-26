package cn.esports.entity;

public class LoginEntity {


	/**
	 * msg : 成功
	 * code : 200
	 * t : {"area":"","signature":"只想遇见那个她","sex":1,"mobile":"15202117928","mobileBound":true,"avatar":"","registerType":1,"userName":"YH1539","token":"C945202F54EAE13CB522370CF63E69652DE0B742","imToken":"63e8a4107b266b1c0e6a00f745d07e8e","id":2,"emailBound":false,"age":24,"email":"","status":1}
	 */
	private String msg;
	private int code;
	private TEntity t;

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setT(TEntity t) {
		this.t = t;
	}

	public String getMsg() {
		return msg;
	}

	public int getCode() {
		return code;
	}

	public TEntity getT() {
		return t;
	}

	public class TEntity {
		/**
		 * area :
		 * signature : 只想遇见那个她
		 * sex : 1
		 * mobile : 15202117928
		 * mobileBound : true
		 * avatar :
		 * registerType : 1
		 * userName : YH1539
		 * token : C945202F54EAE13CB522370CF63E69652DE0B742
		 * imToken : 63e8a4107b266b1c0e6a00f745d07e8e
		 * id : 2
		 * emailBound : false
		 * age : 24
		 * email :
		 * status : 1
		 */
		private String area;
		private String signature;
		private int sex;
		private String mobile;
		private boolean mobileBound;
		private String avatar;
		private int registerType;
		private String userName;
		private String token;
		private String imToken;
		private int id;
		private boolean emailBound;
		private int age;
		private String email;
		private int status;

		public void setArea(String area) {
			this.area = area;
		}

		public void setSignature(String signature) {
			this.signature = signature;
		}

		public void setSex(int sex) {
			this.sex = sex;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public void setMobileBound(boolean mobileBound) {
			this.mobileBound = mobileBound;
		}

		public void setAvatar(String avatar) {
			this.avatar = avatar;
		}

		public void setRegisterType(int registerType) {
			this.registerType = registerType;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public void setImToken(String imToken) {
			this.imToken = imToken;
		}

		public void setId(int id) {
			this.id = id;
		}

		public void setEmailBound(boolean emailBound) {
			this.emailBound = emailBound;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public String getArea() {
			return area;
		}

		public String getSignature() {
			return signature;
		}

		public int getSex() {
			return sex;
		}

		public String getMobile() {
			return mobile;
		}

		public boolean isMobileBound() {
			return mobileBound;
		}

		public String getAvatar() {
			return avatar;
		}

		public int getRegisterType() {
			return registerType;
		}

		public String getUserName() {
			return userName;
		}

		public String getToken() {
			return token;
		}

		public String getImToken() {
			return imToken;
		}

		public int getId() {
			return id;
		}

		public boolean isEmailBound() {
			return emailBound;
		}

		public int getAge() {
			return age;
		}

		public String getEmail() {
			return email;
		}

		public int getStatus() {
			return status;
		}
	}
}
