package cn.esports.entity;
import java.util.List;
/**
 * 
* <p>Title: Troops</p>  
* <p>Description: </p>  
* @author zhimin.hu  
* @date 2018年4月24日
 */
public class Troops {
	  /**
     * msg : 成功
     * code : 200
     * t : {"filter":{"troopsName":""},"level":0,"limit":15,"offset":0,"page":1,"result":[{"captainId":6,"gameType":1,"id":53,"serverType":10,"status":"0","troopsId":"3ZD00093","troopsManifesto":"天西大街","troopsName":"不知名战队13","troopsNumbers":"0","troopsUrl":"战队图片URL"},{"captainId":6,"gameType":2,"id":23,"serverType":9,"status":"1","troopsId":"2ZD00030","troopsManifesto":"战队宣言","troopsName":"测试战队AA","troopsNumbers":"6","troopsUrl":"URL"},{"captainId":7,"gameType":2,"id":2,"serverType":9,"status":"0","troopsId":"ZD123","troopsManifesto":"宣言","troopsName":"菜鸡队","troopsNumbers":"8","troopsUrl":"URL"},{"captainId":5,"gameType":2,"id":6,"serverType":9,"status":"1","troopsId":"ZD001254","troopsManifesto":"战队宣言","troopsName":"战神队","troopsNumbers":"7","troopsUrl":"URL"},{"captainId":1,"gameType":1,"id":58,"serverType":10,"status":"0","troopsId":"3ZD00098","troopsManifesto":"天西大街","troopsName":"不知名战队18","troopsNumbers":"0","troopsUrl":"战队图片URL"}],"total":38,"totalPage":3}
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
         * filter : {"troopsName":""}
         * level : 0
         * limit : 15
         * offset : 0
         * page : 1
         * result : [{"captainId":6,"gameType":1,"id":53,"serverType":10,"status":"0","troopsId":"3ZD00093","troopsManifesto":"天西大街","troopsName":"不知名战队13","troopsNumbers":"0","troopsUrl":"战队图片URL"},{"captainId":6,"gameType":2,"id":23,"serverType":9,"status":"1","troopsId":"2ZD00030","troopsManifesto":"战队宣言","troopsName":"测试战队AA","troopsNumbers":"6","troopsUrl":"URL"},{"captainId":7,"gameType":2,"id":2,"serverType":9,"status":"0","troopsId":"ZD123","troopsManifesto":"宣言","troopsName":"菜鸡队","troopsNumbers":"8","troopsUrl":"URL"},{"captainId":5,"gameType":2,"id":6,"serverType":9,"status":"1","troopsId":"ZD001254","troopsManifesto":"战队宣言","troopsName":"战神队","troopsNumbers":"7","troopsUrl":"URL"},{"captainId":1,"gameType":1,"id":58,"serverType":10,"status":"0","troopsId":"3ZD00098","troopsManifesto":"天西大街","troopsName":"不知名战队18","troopsNumbers":"0","troopsUrl":"战队图片URL"}]
         * total : 38
         * totalPage : 3
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
             * troopsName :
             */

            private String troopsName;

            public String getTroopsName() {
                return troopsName;
            }

            public void setTroopsName(String troopsName) {
                this.troopsName = troopsName;
            }
        }

        public static class ResultBean {
            /**
             * captainId : 6
             * gameType : 1
             * id : 53
             * serverType : 10
             * status : 0
             * troopsId : 3ZD00093
             * troopsManifesto : 天西大街
             * troopsName : 不知名战队13
             * troopsNumbers : 0
             * troopsUrl : 战队图片URL
             */

            private int captainId;
            private int gameType;
            private int id;
            private int serverType;
            private String status;
            private String troopsId;
            private String troopsManifesto;
            private String troopsName;
            private String troopsNumbers;
            private String troopsUrl;

            public int getCaptainId() {
                return captainId;
            }

            public void setCaptainId(int captainId) {
                this.captainId = captainId;
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

            public int getServerType() {
                return serverType;
            }

            public void setServerType(int serverType) {
                this.serverType = serverType;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getTroopsId() {
                return troopsId;
            }

            public void setTroopsId(String troopsId) {
                this.troopsId = troopsId;
            }

            public String getTroopsManifesto() {
                return troopsManifesto;
            }

            public void setTroopsManifesto(String troopsManifesto) {
                this.troopsManifesto = troopsManifesto;
            }

            public String getTroopsName() {
                return troopsName;
            }

            public void setTroopsName(String troopsName) {
                this.troopsName = troopsName;
            }

            public String getTroopsNumbers() {
                return troopsNumbers;
            }

            public void setTroopsNumbers(String troopsNumbers) {
                this.troopsNumbers = troopsNumbers;
            }

            public String getTroopsUrl() {
                return troopsUrl;
            }

            public void setTroopsUrl(String troopsUrl) {
                this.troopsUrl = troopsUrl;
            }
        }
    }
}
