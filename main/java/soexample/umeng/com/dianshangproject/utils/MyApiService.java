package soexample.umeng.com.dianshangproject.utils;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * 处理接口
 */
public interface MyApiService {
    @GET
    Observable<ResponseBody> get(@Url String url, @QueryMap Map<String,String> map);
    @POST
    Observable<ResponseBody> post(@Url String url, @QueryMap Map<String,String> map);


    //我的址
    @GET
    Observable<ResponseBody> getHeader(@Url String url, @HeaderMap Map<String,Object> headermap, @QueryMap Map<String,String> map);
    @POST
    Observable<ResponseBody> postHeader(@Url String url, @HeaderMap Map<String,Object> headermap, @QueryMap Map<String,String> map);
    @DELETE
    Observable<ResponseBody> deleteHeader(@Url String url, @HeaderMap Map<String,Object> headermap, @QueryMap Map<String,String> map);
    @PUT
    Observable<ResponseBody> putHeader(@Url String url, @HeaderMap Map<String,Object> headermap, @QueryMap Map<String,String> map);

}
