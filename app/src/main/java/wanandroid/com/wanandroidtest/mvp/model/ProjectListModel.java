package wanandroid.com.wanandroidtest.mvp.model;

import io.reactivex.Observable;
import wanandroid.com.wanandroidtest.mvp.model.bean.BaseResponse1;
import wanandroid.com.wanandroidtest.mvp.model.bean.ProjectListData;
import wanandroid.com.wanandroidtest.net.RetrofitManager;
import wanandroid.com.wanandroidtest.scheduler.SchedulerUtils;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2019/3/6 ADD
 */

public class ProjectListModel {

    public static Observable<BaseResponse1<ProjectListData>> getProjectListData(int page,int cid){
        return RetrofitManager.service.getProjectListData(page,cid).compose(SchedulerUtils.ioToMain());
    }
}
