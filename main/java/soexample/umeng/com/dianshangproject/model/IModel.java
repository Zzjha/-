package soexample.umeng.com.dianshangproject.model;

import java.util.Map;

import soexample.umeng.com.dianshangproject.callback.MyCallBack;

public interface IModel {
    //GET方法
    void getData(String url,int type, Map<String,String> map, MyCallBack myCallBack);
    //POST方法
    void postData(String url,int type, Map<String,String> map, MyCallBack myCallBack);


    //GET方法   Header头
    void getHeaderData(String url,int type,Map<String,Object> headermap, Map<String,String> map, MyCallBack myCallBack);
    //POST方法   Header头
    void postHeaderData(String url,int type,Map<String,Object> headermap, Map<String,String> map, MyCallBack myCallBack);
    //DELETE方法   Header头
    void deleteHeaderData(String url,int type,Map<String,Object> headermap, Map<String,String> map, MyCallBack myCallBack);
    //PUT方法   Header头
    void putHeaderData(String url,int type,Map<String,Object> headermap, Map<String,String> map, MyCallBack myCallBack);

}