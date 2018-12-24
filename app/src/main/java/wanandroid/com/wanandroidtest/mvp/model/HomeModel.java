package wanandroid.com.wanandroidtest.mvp.model;


import io.reactivex.Observable;
import wanandroid.com.wanandroidtest.mvp.model.bean.HomeDataBean;
import wanandroid.com.wanandroidtest.net.RetrofitManager;
import wanandroid.com.wanandroidtest.scheduler.SchedulerUtils;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2018/12/20 ADD
 */

public class HomeModel {

    public Observable<HomeDataBean> getHomeData() {

        return RetrofitManager.service.getHomeData("https://www.baidu.com/").compose(SchedulerUtils.<HomeDataBean>ioToMain());
    }
}
