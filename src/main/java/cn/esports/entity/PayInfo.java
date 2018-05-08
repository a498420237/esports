/*
 * Copyright (c) 2018年4月26日 by XuanWu Wireless Technology Co.Ltd. 
 *             All rights reserved                         
 */
package cn.esports.entity;
/**
* @Description 直播列表对象
* @author <a href="mailto:zhangxuwei@wxchina.com">zhangxuwei</a>
* @date 2018年4月26日
* @version 1.0.0
*/
public class PayInfo {


    /**
     * msg : ok
     * code : 200
     * t : {"orderId":"20180508001303138tapup14443778","notify_url":"http://39.106.145.173:8087/api/pay/aliNotify.json","payInfo":"app_id=2018040202491200&biz_content=%7B%22timeout_express%22%3A%2230m%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22total_amount%22%3A%221.0%22%2C%22subject%22%3A%22%E5%85%85%E5%80%BC%22%2C%22body%22%3A%22%E7%94%B5%E7%AB%9Ee%E6%97%8F%22%2C%22out_trade_no%22%3A%2220180508001303138tapup14443778%22%7D&charset=utf-8&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F39.106.145.173%3A8087%2Fapi%2Fpay%2FaliNotify.json&sign_type=RSA2&timestamp=2018-05-08+00%3A13%3A03&version=1.0&sign=IOoLVy19h5Yk99bUCrdWwrrkBRGHS%2BwZxna2z3kHiDMhtBNyBPomjFjj3H2cCYADqZYlecTwruPST92lcoDzcVaNQykq%2FXrAtMk9MSDBn17M9nYAVQAi%2BiQ836aWSY0ry2YQ%2BdXSLFlK9p%2FT9XoIkbVUIMo8wgg16I1MuYdSsTf7RpK5XVaf5v8qnU5q9uuInXdGCFkSsd44Yq13VGEp1Fg3qMsAhn%2F9c2Yoh0%2FuZPtDahUlQjd8yMNLQiplcDwbUyCPHki7DpxGVcksbre%2FHRMkMiWFl97aVSSyp%2Bd2QbXgU%2FJ522y1GlwqALkQTtVh%2FjsxLPpnDaQsRMiDc%2F4CMA%3D%3D"}
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
         * orderId : 20180508001303138tapup14443778
         * notify_url : http://39.106.145.173:8087/api/pay/aliNotify.json
         * payInfo : app_id=2018040202491200&biz_content=%7B%22timeout_express%22%3A%2230m%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22total_amount%22%3A%221.0%22%2C%22subject%22%3A%22%E5%85%85%E5%80%BC%22%2C%22body%22%3A%22%E7%94%B5%E7%AB%9Ee%E6%97%8F%22%2C%22out_trade_no%22%3A%2220180508001303138tapup14443778%22%7D&charset=utf-8&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F39.106.145.173%3A8087%2Fapi%2Fpay%2FaliNotify.json&sign_type=RSA2&timestamp=2018-05-08+00%3A13%3A03&version=1.0&sign=IOoLVy19h5Yk99bUCrdWwrrkBRGHS%2BwZxna2z3kHiDMhtBNyBPomjFjj3H2cCYADqZYlecTwruPST92lcoDzcVaNQykq%2FXrAtMk9MSDBn17M9nYAVQAi%2BiQ836aWSY0ry2YQ%2BdXSLFlK9p%2FT9XoIkbVUIMo8wgg16I1MuYdSsTf7RpK5XVaf5v8qnU5q9uuInXdGCFkSsd44Yq13VGEp1Fg3qMsAhn%2F9c2Yoh0%2FuZPtDahUlQjd8yMNLQiplcDwbUyCPHki7DpxGVcksbre%2FHRMkMiWFl97aVSSyp%2Bd2QbXgU%2FJ522y1GlwqALkQTtVh%2FjsxLPpnDaQsRMiDc%2F4CMA%3D%3D
         */
        private String orderId;
        private String notify_url;
        private String payInfo;

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public void setNotify_url(String notify_url) {
            this.notify_url = notify_url;
        }

        public void setPayInfo(String payInfo) {
            this.payInfo = payInfo;
        }

        public String getOrderId() {
            return orderId;
        }

        public String getNotify_url() {
            return notify_url;
        }

        public String getPayInfo() {
            return payInfo;
        }
    }
}
