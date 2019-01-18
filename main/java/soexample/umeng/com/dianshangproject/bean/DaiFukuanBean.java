package soexample.umeng.com.dianshangproject.bean;

import java.util.List;

/**
 * author:author${朱佳华}
 * data:2019/1/15
 */
public class DaiFukuanBean {

    /**
     * orderList : [{"detailList":[{"commentStatus":1,"commodityCount":1,"commodityId":106,"commodityName":"三星Galaxy S9+ 6GB+128GB版曲屏手机/指纹识别手机/拍照手机","commodityPic":"http://172.17.8.100/images/small/commodity/sjsm/sj/7/1.jpg,http://172.17.8.100/images/small/commodity/sjsm/sj/7/2.jpg,http://172.17.8.100/images/small/commodity/sjsm/sj/7/3.jpg,http://172.17.8.100/images/small/commodity/sjsm/sj/7/4.jpg,http://172.17.8.100/images/small/commodity/sjsm/sj/7/5.jpg","commodityPrice":6199,"orderDetailId":1879}],"expressCompName":"京东快递","expressSn":"1001","orderId":"20190115204438621791","orderStatus":1,"payAmount":6199,"payMethod":1,"userId":791},{"detailList":[{"commentStatus":1,"commodityCount":1,"commodityId":5,"commodityName":"双头两用修容笔","commodityPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/3/1.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/2.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/3.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/4.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/5.jpg,","commodityPrice":39,"orderDetailId":1878}],"expressCompName":"京东快递","expressSn":"1001","orderId":"20190115204233507791","orderStatus":1,"payAmount":39,"payMethod":1,"userId":791},{"detailList":[{"commentStatus":1,"commodityCount":1,"commodityId":5,"commodityName":"双头两用修容笔","commodityPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/3/1.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/2.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/3.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/4.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/5.jpg,","commodityPrice":39,"orderDetailId":1877}],"expressCompName":"京东快递","expressSn":"1001","orderId":"20190115204116030791","orderStatus":1,"payAmount":39,"payMethod":1,"userId":791},{"detailList":[{"commentStatus":1,"commodityCount":1,"commodityId":106,"commodityName":"三星Galaxy S9+ 6GB+128GB版曲屏手机/指纹识别手机/拍照手机","commodityPic":"http://172.17.8.100/images/small/commodity/sjsm/sj/7/1.jpg,http://172.17.8.100/images/small/commodity/sjsm/sj/7/2.jpg,http://172.17.8.100/images/small/commodity/sjsm/sj/7/3.jpg,http://172.17.8.100/images/small/commodity/sjsm/sj/7/4.jpg,http://172.17.8.100/images/small/commodity/sjsm/sj/7/5.jpg","commodityPrice":6199,"orderDetailId":1876}],"expressCompName":"京东快递","expressSn":"1001","orderId":"20190115203847070791","orderStatus":1,"payAmount":6199,"payMethod":1,"userId":791},{"detailList":[{"commentStatus":1,"commodityCount":1,"commodityId":106,"commodityName":"三星Galaxy S9+ 6GB+128GB版曲屏手机/指纹识别手机/拍照手机","commodityPic":"http://172.17.8.100/images/small/commodity/sjsm/sj/7/1.jpg,http://172.17.8.100/images/small/commodity/sjsm/sj/7/2.jpg,http://172.17.8.100/images/small/commodity/sjsm/sj/7/3.jpg,http://172.17.8.100/images/small/commodity/sjsm/sj/7/4.jpg,http://172.17.8.100/images/small/commodity/sjsm/sj/7/5.jpg","commodityPrice":6199,"orderDetailId":1873}],"expressCompName":"京东快递","expressSn":"1001","orderId":"20190115203846511791","orderStatus":1,"payAmount":6199,"payMethod":1,"userId":791}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<OrderListBean> orderList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderListBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderListBean> orderList) {
        this.orderList = orderList;
    }

    public static class OrderListBean {
        /**
         * detailList : [{"commentStatus":1,"commodityCount":1,"commodityId":106,"commodityName":"三星Galaxy S9+ 6GB+128GB版曲屏手机/指纹识别手机/拍照手机","commodityPic":"http://172.17.8.100/images/small/commodity/sjsm/sj/7/1.jpg,http://172.17.8.100/images/small/commodity/sjsm/sj/7/2.jpg,http://172.17.8.100/images/small/commodity/sjsm/sj/7/3.jpg,http://172.17.8.100/images/small/commodity/sjsm/sj/7/4.jpg,http://172.17.8.100/images/small/commodity/sjsm/sj/7/5.jpg","commodityPrice":6199,"orderDetailId":1879}]
         * expressCompName : 京东快递
         * expressSn : 1001
         * orderId : 20190115204438621791
         * orderStatus : 1
         * payAmount : 6199
         * payMethod : 1
         * userId : 791
         */

        private String expressCompName;
        private String expressSn;
        private String orderId;
        private int orderStatus;
        private int payAmount;
        private int payMethod;
        private int userId;
        private List<DetailListBean> detailList;

        public String getExpressCompName() {
            return expressCompName;
        }

        public void setExpressCompName(String expressCompName) {
            this.expressCompName = expressCompName;
        }

        public String getExpressSn() {
            return expressSn;
        }

        public void setExpressSn(String expressSn) {
            this.expressSn = expressSn;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public int getPayAmount() {
            return payAmount;
        }

        public void setPayAmount(int payAmount) {
            this.payAmount = payAmount;
        }

        public int getPayMethod() {
            return payMethod;
        }

        public void setPayMethod(int payMethod) {
            this.payMethod = payMethod;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public List<DetailListBean> getDetailList() {
            return detailList;
        }

        public void setDetailList(List<DetailListBean> detailList) {
            this.detailList = detailList;
        }

        public static class DetailListBean {
            /**
             * commentStatus : 1
             * commodityCount : 1
             * commodityId : 106
             * commodityName : 三星Galaxy S9+ 6GB+128GB版曲屏手机/指纹识别手机/拍照手机
             * commodityPic : http://172.17.8.100/images/small/commodity/sjsm/sj/7/1.jpg,http://172.17.8.100/images/small/commodity/sjsm/sj/7/2.jpg,http://172.17.8.100/images/small/commodity/sjsm/sj/7/3.jpg,http://172.17.8.100/images/small/commodity/sjsm/sj/7/4.jpg,http://172.17.8.100/images/small/commodity/sjsm/sj/7/5.jpg
             * commodityPrice : 6199
             * orderDetailId : 1879
             */

            private int commentStatus;
            private int commodityCount;
            private int commodityId;
            private String commodityName;
            private String commodityPic;
            private int commodityPrice;
            private int orderDetailId;

            public int getCommentStatus() {
                return commentStatus;
            }

            public void setCommentStatus(int commentStatus) {
                this.commentStatus = commentStatus;
            }

            public int getCommodityCount() {
                return commodityCount;
            }

            public void setCommodityCount(int commodityCount) {
                this.commodityCount = commodityCount;
            }

            public int getCommodityId() {
                return commodityId;
            }

            public void setCommodityId(int commodityId) {
                this.commodityId = commodityId;
            }

            public String getCommodityName() {
                return commodityName;
            }

            public void setCommodityName(String commodityName) {
                this.commodityName = commodityName;
            }

            public String getCommodityPic() {
                return commodityPic;
            }

            public void setCommodityPic(String commodityPic) {
                this.commodityPic = commodityPic;
            }

            public int getCommodityPrice() {
                return commodityPrice;
            }

            public void setCommodityPrice(int commodityPrice) {
                this.commodityPrice = commodityPrice;
            }

            public int getOrderDetailId() {
                return orderDetailId;
            }

            public void setOrderDetailId(int orderDetailId) {
                this.orderDetailId = orderDetailId;
            }
        }
    }
}
