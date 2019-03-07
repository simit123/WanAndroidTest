package wanandroid.com.wanandroidtest.mvp.model;

import io.reactivex.Observable;
import wanandroid.com.wanandroidtest.mvp.model.bean.BaseResponse1;
import wanandroid.com.wanandroidtest.mvp.model.bean.FeedArticleListData;
import wanandroid.com.wanandroidtest.net.RetrofitManager;
import wanandroid.com.wanandroidtest.scheduler.SchedulerUtils;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2019/3/4 ADD
 */

public class WXDetailModel {

    public static Observable<BaseResponse1<FeedArticleListData>> getWXDetailData(int id,int page){
        return RetrofitManager.service.getWxSumData(id,page).compose(SchedulerUtils.ioToMain());
    }
}
