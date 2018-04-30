package cn.esports.entity;

import java.util.List;

public class UserInfo {


    /**
     * code : 200
     * msg : 成功
     * t : {"id":132,"userName":"huzhimin","leveling":0,"sex":1,"age":28,"status":1,"mobile":"13428899017","mobileBound":true,"emailBound":false,"signature":"我就是我是颜色不一样的烟火","area":"广州地区","avatar":"aa.png","registerType":1,"imToken":"1d5f7b5fd1cc7128e2d3a35412a1b3e1","isOfficial":false,"createTime":"Apr 25, 2018 6:33:41 PM","updateTime":"Apr 25, 2018 6:33:41 PM","token":"F61604A9164AEB77442920D37634E3A623F9BC11","imagePrefix":"http://192.168.1.12:8094","gold":100,"diamond":505,"userPictures":[{"id":1,"uid":132,"picture":""}]}
     */

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
         * id : 132
         * userName : huzhimin
         * leveling : 0
         * sex : 1
         * age : 28
         * status : 1
         * mobile : 13428899017
         * mobileBound : true
         * emailBound : false
         * signature : 我就是我是颜色不一样的烟火
         * area : 广州地区
         * avatar : aa.png
         * registerType : 1
         * imToken : 1d5f7b5fd1cc7128e2d3a35412a1b3e1
         * isOfficial : false
         * createTime : Apr 25, 2018 6:33:41 PM
         * updateTime : Apr 25, 2018 6:33:41 PM
         * token : F61604A9164AEB77442920D37634E3A623F9BC11
         * imagePrefix : http://192.168.1.12:8094
         * gold : 100
         * diamond : 505
         * userPictures : [{"id":1,"uid":132,"picture":""}]
         */

        private int id;
        private String userName;
        private int leveling;
        private int sex;
        private int age;
        private int status;
        private String mobile;
        private boolean mobileBound;
        private boolean emailBound;
        private String signature;
        private String area;
        private String avatar;
        private int registerType;
        private String imToken;
        private boolean isOfficial;
        private String createTime;
        private String updateTime;
        private String token;
        private String imagePrefix;
        private int gold;
        private int diamond;
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

        public int getLeveling() {
            return leveling;
        }

        public void setLeveling(int leveling) {
            this.leveling = leveling;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
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

        public String getImToken() {
            return imToken;
        }

        public void setImToken(String imToken) {
            this.imToken = imToken;
        }

        public boolean isIsOfficial() {
            return isOfficial;
        }

        public void setIsOfficial(boolean isOfficial) {
            this.isOfficial = isOfficial;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getImagePrefix() {
            return imagePrefix;
        }

        public void setImagePrefix(String imagePrefix) {
            this.imagePrefix = imagePrefix;
        }

        public int getGold() {
            return gold;
        }

        public void setGold(int gold) {
            this.gold = gold;
        }

        public int getDiamond() {
            return diamond;
        }

        public void setDiamond(int diamond) {
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
             * uid : 132
             * picture : 
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
