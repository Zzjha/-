package soexample.umeng.com.dianshangproject.model;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.ResponseBody;
import rx.Subscriber;
import soexample.umeng.com.dianshangproject.bean.CircleListBean;
import soexample.umeng.com.dianshangproject.bean.DaiFukuanBean;
import soexample.umeng.com.dianshangproject.bean.DelListBean;
import soexample.umeng.com.dianshangproject.bean.GetMyAddrBean;
import soexample.umeng.com.dianshangproject.bean.GoPayBean;
import soexample.umeng.com.dianshangproject.bean.GoodsDetailsBean;
import soexample.umeng.com.dianshangproject.bean.MyCircleBean;
import soexample.umeng.com.dianshangproject.bean.MyFootBean;
import soexample.umeng.com.dianshangproject.bean.MyInfoBean;
import soexample.umeng.com.dianshangproject.bean.GoodsInfoBean;
import soexample.umeng.com.dianshangproject.bean.GreatBean;
import soexample.umeng.com.dianshangproject.bean.LoginBean;
import soexample.umeng.com.dianshangproject.bean.LunboBean;
import soexample.umeng.com.dianshangproject.bean.MorenAddrBean;
import soexample.umeng.com.dianshangproject.bean.MyAddrBean;
import soexample.umeng.com.dianshangproject.bean.MyWalletBean;
import soexample.umeng.com.dianshangproject.bean.QuanbuDingdanBean;
import soexample.umeng.com.dianshangproject.bean.RegisterBean;
import soexample.umeng.com.dianshangproject.bean.SearchBean;
import soexample.umeng.com.dianshangproject.bean.ShopCarDataBean;
import soexample.umeng.com.dianshangproject.bean.TongBuBean;
import soexample.umeng.com.dianshangproject.bean.UpdataIconBean;
import soexample.umeng.com.dianshangproject.bean.UpdateNickAndPwdBean;
import soexample.umeng.com.dianshangproject.bean.WaitPayBean;
import soexample.umeng.com.dianshangproject.bean.XinpinBean;
import soexample.umeng.com.dianshangproject.callback.MyCallBack;
import soexample.umeng.com.dianshangproject.utils.RetrofitUtils;

public class MyModel implements IModel{

    //GET方法
    @Override
    public void getData(String url, final int type, Map<String, String> map, final MyCallBack myCallBack) {
        RetrofitUtils.getInterface().get(url, map, new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                String message = e.getMessage();
                Log.e("onError", "" + message);
            }

            @Override
            public void onNext(ResponseBody o) {
                try {
                    String string = o.string();
                    Gson gson = new Gson();
                    switch (type) {
                        case 1:
                            LunboBean lunboBean = gson.fromJson(string, LunboBean.class);
                            myCallBack.success(lunboBean);
                            break;
                        case 2:
                            XinpinBean xinpinBean = gson.fromJson(string, XinpinBean.class);
                            myCallBack.success(xinpinBean);
                            break;
                        case 3:
                            CircleListBean circleListBean = gson.fromJson(string, CircleListBean.class);
                            myCallBack.success(circleListBean);
                            break;
                        case 4:
                            SearchBean searchBean = gson.fromJson(string, SearchBean.class);
                            myCallBack.success(searchBean);
                            break;
                        case 5:
                            GoodsInfoBean goodsInfoBean = gson.fromJson(string, GoodsInfoBean.class);
                            myCallBack.success(goodsInfoBean);
                            break;
                        case 6:
                            MyAddrBean myAddrBean = gson.fromJson(string, MyAddrBean.class);
                            myCallBack.success(myAddrBean);
                            Log.e("aaa",myAddrBean.toString());
                            break;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //POST方法
    @Override
    public void postData(String url, final int type, Map<String, String> map, final MyCallBack myCallBack) {
        RetrofitUtils.getInterface().post(url, map, new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted(){

            }

            @Override
            public void onError(Throwable e) {
                String message = e.getMessage();
                Log.e("onError",""+message);
            }

            @Override
            public void onNext(ResponseBody o) {
                try {
                    String string = o.string();
                    Gson gson = new Gson();
                    switch (type){
                        case 1:
                            LoginBean loginBean = gson.fromJson(string, LoginBean.class);
                            myCallBack.success(loginBean);
                            break;
                        case 2:
                            RegisterBean registerBean = gson.fromJson(string,RegisterBean.class);
                            myCallBack.success(registerBean);
                            break;
                        case 3:
                            GetMyAddrBean getMyAddrBean = gson.fromJson(string,GetMyAddrBean.class);
                            myCallBack.success(getMyAddrBean);
                            break;

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //get方式  Header头
    @Override
    public void getHeaderData(String url, final int type, Map<String, Object> headermap, Map<String, String> map, final MyCallBack myCallBack) {
        RetrofitUtils.getInterface().getHeader(url, headermap, map, new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                String message = e.getMessage();
                Log.e("onError",""+message);
            }

            @Override
            public void onNext(ResponseBody o) {
                try {
                    String string = o.string();
                    Gson gson = new Gson();
                    switch (type){
                        case 1:
                            MyAddrBean myAddrBean = gson.fromJson(string,MyAddrBean.class);
                            myCallBack.success(myAddrBean);
                            break;
                        case 2:
                            MyWalletBean myWalletBean = gson.fromJson(string,MyWalletBean.class);
                            myCallBack.success(myWalletBean);
                            break;
                        case 3:
                            MyInfoBean myInfoBean = gson.fromJson(string,MyInfoBean.class);
                            myCallBack.success(myInfoBean);
                            break;
                        case 4:
                            MyCircleBean myCircleBean = gson.fromJson(string,MyCircleBean.class);
                            myCallBack.success(myCircleBean);
                            break;
                        case 5:
                            MyFootBean myFootBean = gson.fromJson(string,MyFootBean.class);
                            myCallBack.success(myFootBean);
                            break;
                        case 6:
                            GoodsDetailsBean goodsDetailsBean = gson.fromJson(string,GoodsDetailsBean.class);
                            myCallBack.success(goodsDetailsBean);
                            break;
                        case 7:
                            ShopCarDataBean shopCarDataBean = gson.fromJson(string,ShopCarDataBean.class);
                            myCallBack.success(shopCarDataBean);
                            break;
                        case 8:
                            WaitPayBean waitPayBean = gson.fromJson(string,WaitPayBean.class);
                            myCallBack.success(waitPayBean);
                            break;
                        case 9:
                            DaiFukuanBean daiFukuanBean = gson.fromJson(string,DaiFukuanBean.class);
                            myCallBack.success(daiFukuanBean);
                            break;
                        case 10:
                            QuanbuDingdanBean quanbuDingdanBean = gson.fromJson(string,QuanbuDingdanBean.class);
                            myCallBack.success(quanbuDingdanBean);
                            break;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    //post方式  Header头
    @Override
    public void postHeaderData(String url, final int type, Map<String,Object> headermap, Map<String, String> map, final MyCallBack myCallBack) {
        RetrofitUtils.getInterface().postHeader(url, headermap, map, new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                String message = e.getMessage();
                Log.e("onError",""+message);
            }

            @Override
            public void onNext(ResponseBody o) {
                try {
                    String string = o.string();
                    Gson gson = new Gson();
                    switch (type){
                        case 1:
                            GetMyAddrBean getMyAddrBean = gson.fromJson(string,GetMyAddrBean.class);
                            myCallBack.success(getMyAddrBean);
                            break;
                        case 2:
                            MorenAddrBean morenAddrBean = gson.fromJson(string,MorenAddrBean.class);
                            myCallBack.success(morenAddrBean);
                            break;
                        case 3:
                            GreatBean greatBean = gson.fromJson(string,GreatBean.class);
                            myCallBack.success(greatBean);
                            break;
                        case 4:
                            UpdataIconBean updataIconBean = gson.fromJson(string,UpdataIconBean.class);
                            myCallBack.success(updataIconBean);
                            break;
                        case 5:
                            GoPayBean goPayBean = gson.fromJson(string,GoPayBean.class);
                            myCallBack.success(goPayBean);
                            break;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //delete方式  Header头
    @Override
    public void deleteHeaderData(String url, final int type, Map<String, Object> headermap, Map<String, String> map, final MyCallBack myCallBack) {
        RetrofitUtils.getInterface().deleteHeader(url, headermap, map, new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                String message = e.getMessage();
                Log.e("onError",""+message);
            }

            @Override
            public void onNext(ResponseBody o) {
                try {
                    String string = o.string();
                    Gson gson = new Gson();
                    switch (type){
                        case 1:
                            RegisterBean registerBean = gson.fromJson(string,RegisterBean.class);
                            myCallBack.success(registerBean);
                            break;
                        case 2:
                            DelListBean delListBean = gson.fromJson(string,DelListBean.class);
                            myCallBack.success(delListBean);
                            break;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //put方式    Header头
    @Override
    public void putHeaderData(String url, final int type, Map<String, Object> headermap, Map<String, String> map, final MyCallBack myCallBack) {
        RetrofitUtils.getInterface().putHeader(url, headermap, map, new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                String message = e.getMessage();
                Log.e("onError",""+message);
            }

            @Override
            public void onNext(ResponseBody o) {
                try {
                    String string = o.string();
                    Gson gson = new Gson();
                    switch (type){
                        case 1:
                            UpdateNickAndPwdBean updateNickAndPwdBean = gson.fromJson(string,UpdateNickAndPwdBean.class);
                            myCallBack.success(updateNickAndPwdBean);
                            break;
                        case 2:
                            TongBuBean tongBuBean = gson.fromJson(string,TongBuBean.class);
                            myCallBack.success(tongBuBean);
                            break;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
