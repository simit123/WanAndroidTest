package wanandroid.com.wanandroidtest.api;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;
import wanandroid.com.wanandroidtest.mvp.model.bean.BannerData;
import wanandroid.com.wanandroidtest.mvp.model.bean.BaseResponse1;
import wanandroid.com.wanandroidtest.mvp.model.bean.FeedArticleListData;
import wanandroid.com.wanandroidtest.mvp.model.bean.HomeDataBean;
import wanandroid.com.wanandroidtest.mvp.model.bean.LoginData;


public interface ApiService {



    @GET
    Observable<HomeDataBean> getHomeData(@Url String url);

    @POST("user/register")
    @FormUrlEncoded//请求正文使用表单网址编码
    Observable<LoginData> registerUser(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);;

    @POST("user/login")
    @FormUrlEncoded
    Observable<LoginData> login(@Field("username") String username, @Field("password") String password);


    /**
     * 获取feed文章列表
     *
     * @param num 页数
     * @return feed文章列表数据
     */
    @GET("article/list/{num}/json")
    Observable<BaseResponse1<FeedArticleListData>> getFeedArticleList(@Path("num") int num);


    /**
     * 广告栏
     * http://www.wanandroid.com/banner/json
     *
     * @return 广告栏数据
     */
    @GET("banner/json")
    Observable<BaseResponse1<List<BannerData>>> getBannerData();

}
