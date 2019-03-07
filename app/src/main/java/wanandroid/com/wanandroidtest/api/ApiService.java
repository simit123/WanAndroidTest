package wanandroid.com.wanandroidtest.api;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import wanandroid.com.wanandroidtest.mvp.model.bean.BannerData;
import wanandroid.com.wanandroidtest.mvp.model.bean.BaseResponse1;
import wanandroid.com.wanandroidtest.mvp.model.bean.FeedArticleListData;
import wanandroid.com.wanandroidtest.mvp.model.bean.HomeDataBean;
import wanandroid.com.wanandroidtest.mvp.model.bean.KnowledgeHierarchyData;
import wanandroid.com.wanandroidtest.mvp.model.bean.LoginData;
import wanandroid.com.wanandroidtest.mvp.model.bean.NavigationListData;
import wanandroid.com.wanandroidtest.mvp.model.bean.ProjectClassifyData;
import wanandroid.com.wanandroidtest.mvp.model.bean.ProjectListData;
import wanandroid.com.wanandroidtest.mvp.model.bean.UsefulSiteData;
import wanandroid.com.wanandroidtest.mvp.model.bean.WxAuthor;


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


    /**
     * 知识体系
     * http://www.wanandroid.com/tree/json
     *
     * @return 知识体系数据
     */
    @GET("tree/json")
    Observable<BaseResponse1<List<KnowledgeHierarchyData>>> getKnowledgeHierarchyData();

    /**
     * 获取公众号列表
     * http://wanandroid.com/wxarticle/chapters/json
     *
     * @return 公众号列表数据
     */
    @GET("wxarticle/chapters/json")
    Observable<BaseResponse1<List<WxAuthor>>> getWxAuthorListData();

    /**
     * 常用网站
     * http://www.wanandroid.com/friend/json
     *
     * @return 常用网站数据
     */
    @GET("friend/json")
    Observable<BaseResponse1<List<UsefulSiteData>>> getUsefulSites();

    /**
     * 导航
     * http://www.wanandroid.com/navi/json
     *
     * @return 导航数据
     */
    @GET("navi/json")
    Observable<BaseResponse1<List<NavigationListData>>> getNavigationListData();

    /**
     * 项目分类
     * http://www.wanandroid.com/project/tree/json
     *
     * @return 项目分类数据
     */
    @GET("project/tree/json")
    Observable<BaseResponse1<List<ProjectClassifyData>>> getProjectClassifyData();

    /**
     * 项目类别数据
     * http://www.wanandroid.com/project/list/1/json?cid=294
     *
     * @param page page num
     * @param cid second page id
     * @return 项目类别数据
     */
    @GET("project/list/{page}/json")
    Observable<BaseResponse1<ProjectListData>> getProjectListData(@Path("page") int page, @Query("cid") int cid);



}
