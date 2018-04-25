package cn.esports.entity;

import java.util.List;

public class MatchInfo {
	 /**
     * msg : 成功
     * code : 200
     * t : {"filter":{"gameType":""},"level":0,"limit":15,"offset":0,"page":1,"result":[{"createDate":1515399299000,"endEntryTime":1516636800000,"entryFeeType":7,"entryTime":1515340800000,"gameType":2,"id":105,"matchId":"2SS000119","matchIntroduce":"<p>11111111<\/p>\n","matchName":"赛事名称2","matchPic":"http://yj-jinggai.oss-cn-shanghai.aliyuncs.com/logo/1515640519667.jpg","matchPoster":"http://yj-jinggai.oss-cn-shanghai.aliyuncs.com/logo/1515640517545.jpg","matchStatus":0,"matchSystem":0,"prizeType":"钻石","serverType":9,"startTime":1515340800000,"terraceType":11,"totalMumber":"0","totalPrize":"15"},{"createDate":1516189233000,"endEntryTime":1516032000000,"endTime":1516809600000,"entryFeeType":4,"entryTime":1515513600000,"gameType":1,"id":115,"matchId":"1SS000121","matchIntroduce":"本比赛是由深圳市腾讯计算机系统有限公司主办，整个赛事经历1个星期，如果想知道本场赛事的具体详情，敬请关注《指尖上行》APP的新闻资讯与赛事更新","matchName":"S7全球总决赛","matchPic":"http://p22o6bknk.bkt.clouddn.com/%E8%B5%9B%E4%BA%8B-%E5%88%97%E8%A1%A84.jpg","matchPoster":"http://p22o6bknk.bkt.clouddn.com/%E8%B5%9B%E4%BA%8B-%E6%B5%B7%E6%8A%A51.jpg","matchStatus":3,"matchSystem":0,"prizeType":"钻石","serverType":10,"startTime":1516550400000,"terraceType":11,"totalMumber":"25","totalPrize":"3000000"}],"total":5,"totalPage":1}
     */

    private String msg;
    private int code;
    private TBean t;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public TBean getT() {
        return t;
    }

    public void setT(TBean t) {
        this.t = t;
    }

    public static class TBean {
        /**
         * filter : {"gameType":""}
         * level : 0
         * limit : 15
         * offset : 0
         * page : 1
         * result : [{"createDate":1515399299000,"endEntryTime":1516636800000,"entryFeeType":7,"entryTime":1515340800000,"gameType":2,"id":105,"matchId":"2SS000119","matchIntroduce":"<p>11111111<\/p>\n","matchName":"赛事名称2","matchPic":"http://yj-jinggai.oss-cn-shanghai.aliyuncs.com/logo/1515640519667.jpg","matchPoster":"http://yj-jinggai.oss-cn-shanghai.aliyuncs.com/logo/1515640517545.jpg","matchStatus":0,"matchSystem":0,"prizeType":"钻石","serverType":9,"startTime":1515340800000,"terraceType":11,"totalMumber":"0","totalPrize":"15"},{"createDate":1516189233000,"endEntryTime":1516032000000,"endTime":1516809600000,"entryFeeType":4,"entryTime":1515513600000,"gameType":1,"id":115,"matchId":"1SS000121","matchIntroduce":"本比赛是由深圳市腾讯计算机系统有限公司主办，整个赛事经历1个星期，如果想知道本场赛事的具体详情，敬请关注《指尖上行》APP的新闻资讯与赛事更新","matchName":"S7全球总决赛","matchPic":"http://p22o6bknk.bkt.clouddn.com/%E8%B5%9B%E4%BA%8B-%E5%88%97%E8%A1%A84.jpg","matchPoster":"http://p22o6bknk.bkt.clouddn.com/%E8%B5%9B%E4%BA%8B-%E6%B5%B7%E6%8A%A51.jpg","matchStatus":3,"matchSystem":0,"prizeType":"钻石","serverType":10,"startTime":1516550400000,"terraceType":11,"totalMumber":"25","totalPrize":"3000000"}]
         * total : 5
         * totalPage : 1
         */

        private FilterBean filter;
        private int level;
        private int limit;
        private int offset;
        private int page;
        private int total;
        private int totalPage;
        private List<ResultBean> result;

        public FilterBean getFilter() {
            return filter;
        }

        public void setFilter(FilterBean filter) {
            this.filter = filter;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public List<ResultBean> getResult() {
            return result;
        }

        public void setResult(List<ResultBean> result) {
            this.result = result;
        }

        public static class FilterBean {
            /**
             * gameType : 
             */

            private String gameType;

            public String getGameType() {
                return gameType;
            }

            public void setGameType(String gameType) {
                this.gameType = gameType;
            }
        }

        public static class ResultBean {
            /**
             * createDate : 1515399299000
             * endEntryTime : 1516636800000
             * entryFeeType : 7
             * entryTime : 1515340800000
             * gameType : 2
             * id : 105
             * matchId : 2SS000119
             * matchIntroduce : <p>11111111</p>

             * matchName : 赛事名称2
             * matchPic : http://yj-jinggai.oss-cn-shanghai.aliyuncs.com/logo/1515640519667.jpg
             * matchPoster : http://yj-jinggai.oss-cn-shanghai.aliyuncs.com/logo/1515640517545.jpg
             * matchStatus : 0
             * matchSystem : 0
             * prizeType : 钻石
             * serverType : 9
             * startTime : 1515340800000
             * terraceType : 11
             * totalMumber : 0
             * totalPrize : 15
             * endTime : 1516809600000
             */

            private long createDate;
            private long endEntryTime;
            private int entryFeeType;
            private long entryTime;
            private int gameType;
            private int id;
            private String matchId;
            private String matchIntroduce;
            private String matchName;
            private String matchPic;
            private String matchPoster;
            private int matchStatus;
            private int matchSystem;
            private String prizeType;
            private int serverType;
            private long startTime;
            private int terraceType;
            private String totalMumber;
            private String totalPrize;
            private long endTime;

            public long getCreateDate() {
                return createDate;
            }

            public void setCreateDate(long createDate) {
                this.createDate = createDate;
            }

            public long getEndEntryTime() {
                return endEntryTime;
            }

            public void setEndEntryTime(long endEntryTime) {
                this.endEntryTime = endEntryTime;
            }

            public int getEntryFeeType() {
                return entryFeeType;
            }

            public void setEntryFeeType(int entryFeeType) {
                this.entryFeeType = entryFeeType;
            }

            public long getEntryTime() {
                return entryTime;
            }

            public void setEntryTime(long entryTime) {
                this.entryTime = entryTime;
            }

            public int getGameType() {
                return gameType;
            }

            public void setGameType(int gameType) {
                this.gameType = gameType;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getMatchId() {
                return matchId;
            }

            public void setMatchId(String matchId) {
                this.matchId = matchId;
            }

            public String getMatchIntroduce() {
                return matchIntroduce;
            }

            public void setMatchIntroduce(String matchIntroduce) {
                this.matchIntroduce = matchIntroduce;
            }

            public String getMatchName() {
                return matchName;
            }

            public void setMatchName(String matchName) {
                this.matchName = matchName;
            }

            public String getMatchPic() {
                return matchPic;
            }

            public void setMatchPic(String matchPic) {
                this.matchPic = matchPic;
            }

            public String getMatchPoster() {
                return matchPoster;
            }

            public void setMatchPoster(String matchPoster) {
                this.matchPoster = matchPoster;
            }

            public int getMatchStatus() {
                return matchStatus;
            }

            public void setMatchStatus(int matchStatus) {
                this.matchStatus = matchStatus;
            }

            public int getMatchSystem() {
                return matchSystem;
            }

            public void setMatchSystem(int matchSystem) {
                this.matchSystem = matchSystem;
            }

            public String getPrizeType() {
                return prizeType;
            }

            public void setPrizeType(String prizeType) {
                this.prizeType = prizeType;
            }

            public int getServerType() {
                return serverType;
            }

            public void setServerType(int serverType) {
                this.serverType = serverType;
            }

            public long getStartTime() {
                return startTime;
            }

            public void setStartTime(long startTime) {
                this.startTime = startTime;
            }

            public int getTerraceType() {
                return terraceType;
            }

            public void setTerraceType(int terraceType) {
                this.terraceType = terraceType;
            }

            public String getTotalMumber() {
                return totalMumber;
            }

            public void setTotalMumber(String totalMumber) {
                this.totalMumber = totalMumber;
            }

            public String getTotalPrize() {
                return totalPrize;
            }

            public void setTotalPrize(String totalPrize) {
                this.totalPrize = totalPrize;
            }

            public long getEndTime() {
                return endTime;
            }

            public void setEndTime(long endTime) {
                this.endTime = endTime;
            }
        }
    }
}
