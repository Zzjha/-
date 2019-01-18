package soexample.umeng.com.dianshangproject.presenter;

import android.util.Log;

import java.util.Map;

import soexample.umeng.com.dianshangproject.callback.MyCallBack;
import soexample.umeng.com.dianshangproject.model.MyModel;
import soexample.umeng.com.dianshangproject.view.IView;

public class MyPresenter implements IPresenter {

    private MyModel myModel;
    private IView iView;

    public MyPresenter(IView iView) {
        this.iView = iView;
        myModel = new MyModel();
    }

    //GET方法
    @Override
    public void getRequest(String url, final int type, Map<String, String> map) {
        myModel.getData(url, type, map, new MyCallBack() {
            @Override
            public void success(Object successData) {
                switch (type){
                    case 1:
                        iView.success(successData);
                        break;
                    case 2:
                        iView.success(successData);
                        break;
                    case 3:
                        iView.success(successData);
                        break;
                    case 4:
                        iView.success(successData);
                        break;
                    case 5:
                        iView.success(successData);
                        break;
                    case 6:
                        iView.success(successData);
                        break;
                }
            }

            @Override
            public void error(Object errorData) {

            }
        });
    }


    //POST
    @Override
    public void postRequest(String url, final int type, Map<String, String> map) {
        myModel.postData(url, type, map, new MyCallBack() {
            @Override
            public void success(Object successData) {
                switch (type){
                    case 1:
                        iView.success(successData);
                        break;
                    case 2:
                        iView.success(successData);
                        break;
                    case 3:
                        iView.success(successData);
                        break;
                }
            }

            @Override
            public void error(Object errorData) {
                Log.e("MyPresenter",errorData+"");
            }
        });
    }


    ///get方式  Header头
    @Override
    public void getHeaderRequest(String url, final int type, Map<String, Object> headermap, Map<String, String> map) {
        myModel.getHeaderData(url, type, headermap, map, new MyCallBack() {
            @Override
            public void success(Object successData) {
                switch (type){
                    case 1:
                        iView.success(successData);
                        break;
                    case 2:
                        iView.success(successData);
                        break;
                    case 3:
                        iView.success(successData);
                        break;
                    case 4:
                        iView.success(successData);
                        break;
                    case 5:
                        iView.success(successData);
                        break;
                    case 6:
                        iView.success(successData);
                        break;
                    case 7:
                        iView.success(successData);
                        break;
                    case 8:
                        iView.success(successData);
                        break;
                    case 9:
                        iView.success(successData);
                        break;
                    case 10:
                        iView.success(successData);
                        break;
                }
            }

            @Override
            public void error(Object errorData) {
                iView.error(errorData);
            }

        });
    }


    //post方式  Header头
    @Override
    public void postHeaderRequest(String url, final int type, Map<String, Object> headermap, Map<String, String> map) {
        myModel.postHeaderData(url, type, headermap, map, new MyCallBack() {
            @Override
            public void success(Object successData) {
                switch (type){
                    case 1:
                        iView.success(successData);
                        break;
                    case 2:
                        iView.success(successData);
                        break;
                    case 3:
                        iView.success(successData);
                        break;
                    case 4:
                        iView.success(successData);
                        break;
                    case 5:
                        iView.success(successData);
                        break;
                }
            }

            @Override
            public void error(Object errorData) {
                iView.error(errorData);
            }

        });
    }


    //delete方式  Header头
    @Override
    public void deleteHeaderRequest(String url, final int type, Map<String, Object> headermap, Map<String, String> map) {
        myModel.deleteHeaderData(url, type, headermap, map, new MyCallBack() {
            @Override
            public void success(Object successData) {
                switch (type){
                    case 1:
                        iView.success(successData);
                        break;
                    case 2:
                        iView.success(successData);
                        break;
                }
            }

            @Override
            public void error(Object errorData) {
                iView.error(errorData);
            }

        });
    }

    //put方式  Header头
    @Override
    public void putHeaderRequest(String url, final int type, Map<String, Object> headermap, Map<String, String> map) {
        myModel.putHeaderData(url, type, headermap, map, new MyCallBack() {
            @Override
            public void success(Object successData) {
                switch (type){
                    case 1:
                        iView.success(successData);
                        break;
                }
            }

            @Override
            public void error(Object errorData) {
                iView.error(errorData);
            }

        });
    }


    public void onDetch() {
        if (myModel != null) {
            myModel = null;
        }
        if (iView != null) {
            iView = null;
        }
    }
}

