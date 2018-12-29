package wanandroid.com.wanandroidtest.api;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;
import wanandroid.com.wanandroidtest.mvp.model.bean.HomeDataBean;
import wanandroid.com.wanandroidtest.mvp.model.bean.LoginData;


public interface ApiService {



    @GET
    Observable<HomeDataBean> getHomeData(@Url String url);

    @POST("user/register")
    @FormUrlEncoded//请求正文使用表单网址编码
    Observable<LoginData> registerUser(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);
}
