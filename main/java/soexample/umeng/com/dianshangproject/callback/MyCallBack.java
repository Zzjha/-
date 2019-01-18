package soexample.umeng.com.dianshangproject.callback;

public interface MyCallBack<T> {
    void success(T successData);
    void error(T errorData);
}
