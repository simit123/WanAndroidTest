package wanandroid.com.wanandroidtest.net;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.internal.fuseable.HasUpstreamObservableSource;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.PUT;
import wanandroid.com.wanandroidtest.api.ApiService;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2018/12/20 ADD
 */

public class RetrofitManager {
    private RetrofitManager() {
    }

   public static final String HOST = "http://www.wanandroid.com/";

    private static Retrofit retrofit = null;
    private static OkHttpClient okHttpClient = null;

    public static ApiService service = getRetrofit().create(ApiService.class);

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            synchronized (RetrofitManager.class) {
                if (retrofit == null) {
                    //添加log拦截器
                    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                    //设置请求过滤水平
                    // BASEIC:请求/响应行
                    //HEADER:请求/响应行 + 头
                    //BODY:请求/响应行 + 头 + 体
                    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    //设置请求的缓存大小和位置
//                    File cacheFile = File(Context,"cacheFile");
//                    Cache cache = new Cache(cacheFile,1024 * 1024 * 50);//50Mb 缓存的大小
                    okHttpClient = new OkHttpClient.Builder()
//                            .addInterceptor(addQueryParameterInterceptor())  //参数添加
//                            .addInterceptor(addHeaderInterceptor()) // token过滤
//                            .addInterceptor(addCacheInterceptor())
                            .addInterceptor(httpLoggingInterceptor) //日志,所有的请求响应度看到
//                            .cache(cache)  //添加缓存
                            .connectTimeout(60L, TimeUnit.SECONDS)
                            .readTimeout(60L, TimeUnit.SECONDS)
                            .writeTimeout(60L, TimeUnit.SECONDS)
                            .build();
                    //获取Retrofit 实例
                    retrofit = new Retrofit.Builder()
                            .baseUrl(HOST)
                            .client(okHttpClient)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();
                }
            }
        }
        return retrofit;
    }


    /**
     * 设置公共参数
     */
    private Interceptor addQueryParameterInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request request;
                HttpUrl modifiedUrl = originalRequest.url().newBuilder()
                        .addQueryParameter("phoneSystem", "")
                        .addQueryParameter("phoneModel", "")
                        .build();
                request = originalRequest.newBuilder().url(modifiedUrl).build();

                return chain.proceed(request);
            }
        };
    }


    /**
     * 设置头
     */
    private Interceptor addHeaderInterceptor() {

        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request originalRequest = chain.request();

                Request requestBuilder = originalRequest.newBuilder()
                        .header("token", "")
                        .method(originalRequest.method(), originalRequest.body())
                        .build();
                return chain.proceed(requestBuilder);
            }
        };
    }
}
