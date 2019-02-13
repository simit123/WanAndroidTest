package wanandroid.com.wanandroidtest.mvp.model;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2019/2/13 ADD
 */

import java.util.List;

import io.reactivex.Observable;
import wanandroid.com.wanandroidtest.mvp.model.bean.BaseResponse1;
import wanandroid.com.wanandroidtest.mvp.model.bean.KnowledgeHierarchyData;
import wanandroid.com.wanandroidtest.net.RetrofitManager;
import wanandroid.com.wanandroidtest.scheduler.SchedulerUtils;

/**
 * model层定义接口请求方法
 */

public class KnowledgeHierarchyModel {

    public Observable<BaseResponse1<List<KnowledgeHierarchyData>>> getKnowledgeHierarchyData(){
       return RetrofitManager.service.getKnowledgeHierarchyData().compose(SchedulerUtils.ioToMain());
    }
}
