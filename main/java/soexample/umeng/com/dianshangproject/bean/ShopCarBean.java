package soexample.umeng.com.dianshangproject.bean;

import java.io.Serializable;
import java.util.List;

/**
 * author:author${朱佳华}
 * data:2019/1/13
 */
public class ShopCarBean implements Serializable  {
    List<ShopCarDataBean.ResultBean> mList;

    public ShopCarBean(List<ShopCarDataBean.ResultBean> mList) {
        this.mList = mList;
    }

    public List<ShopCarDataBean.ResultBean> getmList() {
        return mList;
    }
}
