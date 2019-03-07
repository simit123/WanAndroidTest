package wanandroid.com.wanandroidtest.mvp.model;


import java.util.List;

import io.reactivex.Observable;
import wanandroid.com.wanandroidtest.mvp.model.bean.BaseResponse1;
import wanandroid.com.wanandroidtest.mvp.model.bean.ProjectClassifyData;
import wanandroid.com.wanandroidtest.net.RetrofitManager;
import wanandroid.com.wanandroidtest.scheduler.SchedulerUtils;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2019/3/6 ADD
 */

public class ProjectModel {

    public static Observable<BaseResponse1<List<ProjectClassifyData>>> getProjectData(){
        return RetrofitManager.service.getProjectClassifyData().compose(SchedulerUtils.ioToMain());
    }
}
