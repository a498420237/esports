package cn.esports.entity;

import java.util.List;

public class UserInfo {
	private int code;
    private String msg;
    private TBean t;

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

    public TBean getT() {
        return t;
    }

    public void setT(TBean t) {
        this.t = t;
    }

    public static class TBean {
        /**
         * id : 1
         * userName : 御龙归宇月
         * sex : 1
         * age : 24
         * mobile : 15202117928
         * mobileBound : true
         * email : 603498287@qq.com
         * emailBound : true
         * signature : 只想遇见那个她
         * area : 安徽
         * imToken : 63e8a4107b266b1c0e6a00f745d07e8e
         * avatar : http://p3.gexing.com/shaitu/20120814/2322/502a6d183fad8.jpg
         * registerType : 1
         * imagePrefix : http://192.168.1.12:8094
         * gold : 0.0
         * diamond : 0.0
         * userPictures : [{"id":1,"uid":1,"picture":"http://p2.gexing.com/shaitu/20120814/2321/502a6d05cf996.jpg"},{"id":2,"uid":1,"picture":"http://p2.gexing.com/shaitu/20120814/2321/502a6d10724dc.jpg"}]
         */

        private int id;
        private String userName;
        private int sex;
        private int age;
        private String mobile;
        private boolean mobileBound;
        private String email;
        private boolean emailBound;
        private String signature;
        private String area;
        private String imToken;
        private String avatar;
        private int registerType;
        private String imagePrefix;
        private double gold;
        private double diamond;
        private List<UserPicturesBean> userPictures;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public boolean isMobileBound() {
            return mobileBound;
        }

        public void setMobileBound(boolean mobileBound) {
            this.mobileBound = mobileBound;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public boolean isEmailBound() {
            return emailBound;
        }

        public void setEmailBound(boolean emailBound) {
            this.emailBound = emailBound;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getImToken() {
            return imToken;
        }

        public void setImToken(String imToken) {
            this.imToken = imToken;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getRegisterType() {
            return registerType;
        }

        public void setRegisterType(int registerType) {
            this.registerType = registerType;
        }

        public String getImagePrefix() {
            return imagePrefix;
        }

        public void setImagePrefix(String imagePrefix) {
            this.imagePrefix = imagePrefix;
        }

        public double getGold() {
            return gold;
        }

        public void setGold(double gold) {
            this.gold = gold;
        }

        public double getDiamond() {
            return diamond;
        }

        public void setDiamond(double diamond) {
            this.diamond = diamond;
        }

        public List<UserPicturesBean> getUserPictures() {
            return userPictures;
        }

        public void setUserPictures(List<UserPicturesBean> userPictures) {
            this.userPictures = userPictures;
        }

        public static class UserPicturesBean {
            /**
             * id : 1
             * uid : 1
             * picture : http://p2.gexing.com/shaitu/20120814/2321/502a6d05cf996.jpg
             */

            private int id;
            private int uid;
            private String picture;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }
        }
    }
}
