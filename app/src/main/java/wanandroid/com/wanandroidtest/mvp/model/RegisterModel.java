package wanandroid.com.wanandroidtest.mvp.model;

import io.reactivex.Observable;
import wanandroid.com.wanandroidtest.mvp.model.bean.LoginData;
import wanandroid.com.wanandroidtest.net.RetrofitManager;
import wanandroid.com.wanandroidtest.scheduler.SchedulerUtils;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2018/12/29 ADD
 */

public class RegisterModel {

    public Observable<LoginData> getRegisterData(String account,String password,String rePassword){
        return RetrofitManager.service.registerUser(account,password,rePassword).compose(SchedulerUtils.ioToMain());
    }
}
