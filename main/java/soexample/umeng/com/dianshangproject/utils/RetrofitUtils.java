package soexample.umeng.com.dianshangproject.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitUtils {

    private MyApiService myApiService;
    private OkHttpClient okHttpClient;

    public RetrofitUtils() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .connectTimeout(20,TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Contacts.BASEURL)
                .client(okHttpClient)
                .build();

        myApiService = retrofit.create(MyApiService.class);
    }

    public static RetrofitUtils getInterface(){
        return RetrofitHolder.retrofitUtils;
    }

    private static class RetrofitHolder{
        private static final RetrofitUtils retrofitUtils = new RetrofitUtils();
    }

    //get方式
    public RetrofitUtils get(String url, Map<String,String> map,Subscriber subscriber){
        myApiService.get(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        return RetrofitUtils.getInterface();
    }
    //post方式
    public RetrofitUtils post(String url, Map<String,String> map, Subscriber subscriber){
        if(map==null){
            map = new HashMap<>();
        }
        myApiService.post(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        return RetrofitUtils.getInterface();
    }

    //get方式  Header头
    public RetrofitUtils getHeader(String url, Map<String,Object> headermap, Map<String,String> map,Subscriber subscriber){
        myApiService.getHeader(url,headermap,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        return RetrofitUtils.getInterface();
    }

    //post方式  Header头
    public RetrofitUtils postHeader(String url, Map<String,Object> headermap, Map<String,String> map,Subscriber subscriber){
        myApiService.postHeader(url,headermap,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        return RetrofitUtils.getInterface();
    }

    //delete方式  Header头
    public RetrofitUtils deleteHeader(String url, Map<String,Object> headermap, Map<String,String> map,Subscriber subscriber){
        myApiService.deleteHeader(url,headermap,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        return RetrofitUtils.getInterface();
    }

    //put方式  Header头
    public RetrofitUtils putHeader(String url, Map<String,Object> headermap, Map<String,String> map,Subscriber subscriber){
        myApiService.putHeader(url,headermap,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        return RetrofitUtils.getInterface();
    }
}
