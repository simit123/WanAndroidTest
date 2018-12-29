package wanandroid.com.wanandroidtest.app;

import android.app.Application;

import wanandroid.com.wanandroidtest.utils.Preferences;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2018/12/24 ADD
 */

public class MyApplication extends Application{


    private static MyApplication instance;
    public static boolean isFirstRun = true;//是否是第一次启动

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Preferences.init(getApplicationContext());
    }

    public synchronized static MyApplication getInstance(){
        return instance;
    }
}
