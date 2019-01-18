package soexample.umeng.com.dianshangproject.presenter;

import java.util.Map;

public interface IPresenter {
    //GET方法
    void getRequest(String url,int type, Map<String,String> map);
    //POST方法
    void postRequest(String url,int type, Map<String,String> map);



    //GET方法
    void getHeaderRequest(String url,int type,Map<String,Object> headermap, Map<String,String> map);
    //POST方法
    void postHeaderRequest(String url,int type,Map<String,Object> headermap, Map<String,String> map);
    //DELETE方法
    void deleteHeaderRequest(String url,int type,Map<String,Object> headermap, Map<String,String> map);
    //PUT方法
    void putHeaderRequest(String url,int type,Map<String,Object> headermap, Map<String,String> map);
}
