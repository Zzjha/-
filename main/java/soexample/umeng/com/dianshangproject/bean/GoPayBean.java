package soexample.umeng.com.dianshangproject.bean;

/**
 * author:author${朱佳华}
 * data:2019/1/15
 */
public class GoPayBean {

    /**
     * message : 支付成功
     * status : 0000
     */

    private String message;
    private String status;

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
}
