package soexample.umeng.com.dianshangproject.bean;

import java.io.Serializable;
import java.util.List;

/**
 * author:author${朱佳华}
 * data:2019/1/10
 */
public class ShopCarDataBean implements Serializable {

    /**
     * result : [{"commodityId":5,"commodityName":"双头两用修容笔","count":3,"pic":"http://172.17.8.100/images/small/commodity/mzhf/cz/3/1.jpg","price":39},{"commodityId":6,"commodityName":"轻柔系自然裸妆假睫毛","count":4,"pic":"http://172.17.8.100/images/small/commodity/mzhf/cz/4/1.jpg","price":39}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable{
        /**
         * commodityId : 5
         * commodityName : 双头两用修容笔
         * count : 3
         * pic : http://172.17.8.100/images/small/commodity/mzhf/cz/3/1.jpg
         * price : 39
         */

        private int commodityId;
        private String commodityName;
        private int count;
        private String pic;
        private int price;

        private boolean whetherChecked;

        public ResultBean(boolean whetherChecked) {
            this.whetherChecked = whetherChecked;
        }

        public void setWhetherChecked(boolean whetherChecked) {
            this.whetherChecked = whetherChecked;
        }

        public boolean isWhetherChecked() {
            return whetherChecked;
        }

        public ResultBean(int commodityId, String commodityName, int count, String pic, int price, boolean whetherChecked) {
            this.commodityId = commodityId;
            this.commodityName = commodityName;
            this.count = count;
            this.pic = pic;
            this.price = price;
            this.whetherChecked = whetherChecked;
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

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
