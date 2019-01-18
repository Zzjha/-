package soexample.umeng.com.dianshangproject.view;

public interface IView<T> {
    void success(T success);
    void error(T error);
}
