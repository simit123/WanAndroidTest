package wanandroid.com.wanandroidtest.mvp.model;

import java.util.List;

import io.reactivex.Observable;
import wanandroid.com.wanandroidtest.mvp.model.bean.BaseResponse1;
import wanandroid.com.wanandroidtest.mvp.model.bean.WxAuthor;
import wanandroid.com.wanandroidtest.net.RetrofitManager;
import wanandroid.com.wanandroidtest.scheduler.SchedulerUtils;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2019/2/14 ADD
 */

public class WxAuthorModel {

    public Observable<BaseResponse1<List<WxAuthor>>> getWxAuthor(){
        return RetrofitManager.service.getWxAuthorListData().compose(SchedulerUtils.ioToMain());
    }
}
