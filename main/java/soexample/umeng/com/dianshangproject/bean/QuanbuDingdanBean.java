package soexample.umeng.com.dianshangproject.bean;

import java.util.List;

/**
 * author:author${朱佳华}
 * data:2019/1/16
 */
public class QuanbuDingdanBean {

    /**
     * orderList : [{"detailList":[{"commentStatus":1,"commodityCount":1,"commodityId":3,"commodityName":"Lara style女神的魔盒全套彩妆","commodityPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/1/1.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/1/2.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/1/3.jpg","commodityPrice":3499,"orderDetailId":1938}],"expressCompName":"京东快递","expressSn":"1001","orderId":"20190116131629681791","orderStatus":1,"payAmount":3499,"payMethod":1,"userId":791},{"detailList":[{"commentStatus":1,"commodityCount":1,"commodityId":106,"commodityName":"三星Galaxy S9+ 6GB+128GB版曲屏手机/指纹识别手机/拍照手机","commodityPic":"http://172.17.8.100/images/small/commodity/sjsm/sj/7/1.jpg,http://172.17.8.100/images/small/commodity/sjsm/sj/7/2.jpg,http://172.17.8.100/images/small/commodity/sjsm/sj/7/3.jpg,http://172.17.8.100/images/small/commodity/sjsm/sj/7/4.jpg,http://172.17.8.100/images/small/commodity/sjsm/sj/7/5.jpg","commodityPrice":6199,"orderDetailId":1872}],"expressCompName":"京东快递","expressSn":"1001","orderId":"20190115203846340791","orderStatus":1,"payAmount":6199,"payMethod":1,"userId":791},{"detailList":[{"commentStatus":1,"commodityCount":1,"commodityId":106,"commodityName":"三星Galaxy S9+ 6GB+128GB版曲屏手机/指纹识别手机/拍照手机","commodityPic":"http://172.17.8.100/images/small/commodity/sjsm/sj/7/1.jpg,http://172.17.8.100/images/small/commodity/sjsm/sj/7/2.jpg,http://172.17.8.100/images/small/commodity/sjsm/sj/7/3.jpg,http://172.17.8.100/images/small/commodity/sjsm/sj/7/4.jpg,http://172.17.8.100/images/small/commodity/sjsm/sj/7/5.jpg","commodityPrice":6199,"orderDetailId":1870}],"expressCompName":"京东快递","expressSn":"1001","orderId":"20190115203845190791","orderStatus":1,"payAmount":6199,"payMethod":1,"userId":791},{"detailList":[{"commentStatus":1,"commodityCount":1,"commodityId":106,"commodityName":"三星Galaxy S9+ 6GB+128GB版曲屏手机/指纹识别手机/拍照手机","commodityPic":"http://172.17.8.100/images/small/commodity/sjsm/sj/7/1.jpg,http://172.17.8.100/images/small/commodity/sjsm/sj/7/2.jpg,http://172.17.8.100/images/small/commodity/sjsm/sj/7/3.jpg,http://172.17.8.100/images/small/commodity/sjsm/sj/7/4.jpg,http://172.17.8.100/images/small/commodity/sjsm/sj/7/5.jpg","commodityPrice":6199,"orderDetailId":1869}],"expressCompName":"京东快递","expressSn":"1001","orderId":"20190115203841019791","orderStatus":1,"payAmount":6199,"payMethod":1,"userId":791},{"detailList":[{"commentStatus":1,"commodityCount":1,"commodityId":106,"commodityName":"三星Galaxy S9+ 6GB+128GB版曲屏手机/指纹识别手机/拍照手机","commodityPic":"http://172.17.8.100/images/small/commodity/sjsm/sj/7/1.jpg,http://172.17.8.100/images/small/commodity/sjsm/sj/7/2.jpg,http://172.17.8.100/images/small/commodity/sjsm/sj/7/3.jpg,http://172.17.8.100/images/small/commodity/sjsm/sj/7/4.jpg,http://172.17.8.100/images/small/commodity/sjsm/sj/7/5.jpg","commodityPrice":6199,"orderDetailId":1865}],"expressCompName":"京东快递","expressSn":"1001","orderId":"20190115203840240791","orderStatus":1,"payAmount":6199,"payMethod":1,"userId":791}]
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
         * detailList : [{"commentStatus":1,"commodityCount":1,"commodityId":3,"commodityName":"Lara style女神的魔盒全套彩妆","commodityPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/1/1.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/1/2.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/1/3.jpg","commodityPrice":3499,"orderDetailId":1938}]
         * expressCompName : 京东快递
         * expressSn : 1001
         * orderId : 20190116131629681791
         * orderStatus : 1
         * payAmount : 3499
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
             * commodityId : 3
             * commodityName : Lara style女神的魔盒全套彩妆
             * commodityPic : http://172.17.8.100/images/small/commodity/mzhf/cz/1/1.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/1/2.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/1/3.jpg
             * commodityPrice : 3499
             * orderDetailId : 1938
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
