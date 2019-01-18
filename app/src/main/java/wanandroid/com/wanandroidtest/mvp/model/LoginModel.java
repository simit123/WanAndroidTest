package wanandroid.com.wanandroidtest.mvp.model;

import io.reactivex.Observable;
import wanandroid.com.wanandroidtest.mvp.model.bean.LoginData;
import wanandroid.com.wanandroidtest.net.RetrofitManager;
import wanandroid.com.wanandroidtest.scheduler.SchedulerUtils;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2019/1/15 ADD
 */

public class LoginModel {

   public Observable<LoginData> getLoginData(String account,String password){
       return RetrofitManager.service.login(account,password).compose(SchedulerUtils.ioToMain());
    }
}
