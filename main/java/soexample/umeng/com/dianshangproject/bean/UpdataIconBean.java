package soexample.umeng.com.dianshangproject.bean;

/**
 * author:author${朱佳华}
 * data:2019/1/8
 */
public class UpdataIconBean {

    /**
     * headPath : http://172.17.8.100/images/small/head_pic/2018-11-21/20181121102818.jpg
     * message : 上传成功
     * status : 0000
     */

    private String headPath;
    private String message;
    private String status;

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }

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
