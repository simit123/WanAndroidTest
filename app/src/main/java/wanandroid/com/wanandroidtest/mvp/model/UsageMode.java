package wanandroid.com.wanandroidtest.mvp.model;

import java.util.List;

import io.reactivex.Observable;
import wanandroid.com.wanandroidtest.mvp.model.bean.BaseResponse1;
import wanandroid.com.wanandroidtest.mvp.model.bean.UsefulSiteData;
import wanandroid.com.wanandroidtest.net.RetrofitManager;
import wanandroid.com.wanandroidtest.scheduler.SchedulerUtils;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2019/2/28 ADD
 */

public class UsageMode {
    public Observable<BaseResponse1<List<UsefulSiteData>>> getUsefulData(){
        return RetrofitManager.service.getUsefulSites().compose(SchedulerUtils.ioToMain());
    }
}
