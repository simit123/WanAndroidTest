package wanandroid.com.wanandroidtest.mvp.model;

import java.util.List;

import io.reactivex.Observable;
import wanandroid.com.wanandroidtest.mvp.model.bean.BannerData;
import wanandroid.com.wanandroidtest.mvp.model.bean.BaseResponse1;
import wanandroid.com.wanandroidtest.mvp.model.bean.FeedArticleListData;
import wanandroid.com.wanandroidtest.net.RetrofitManager;
import wanandroid.com.wanandroidtest.scheduler.SchedulerUtils;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2019/1/22 ADD
 */

public class MainPageModel {

    /**
     * 请求列表数据
     * @param num
     * @return
     */
   public Observable<BaseResponse1<FeedArticleListData>> getFeedArticleList(int num){
       return RetrofitManager.service.getFeedArticleList(num).compose(SchedulerUtils.ioToMain());
   }

    /**
     * 请求banner数据
     * @return
     */
   public Observable<BaseResponse1<List<BannerData>>> getBanner(){
       return RetrofitManager.service.getBannerData().compose(SchedulerUtils.ioToMain());
   }
}
