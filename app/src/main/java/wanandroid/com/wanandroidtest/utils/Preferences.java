package wanandroid.com.wanandroidtest.utils;

import android.content.Context;
import android.content.SharedPreferences;

import wanandroid.com.wanandroidtest.app.Constants;

/**
 * sharePreferences 工具类
 */

public class Preferences {

    private static final String PREFS_NAME = "PrefsFile";

    private static Preferences instance = null;

    private Context mContext;

    private SharedPreferences sp;

    public static void init(Context mContext){
        if (instance == null) {
            instance = new Preferences(mContext);
        }
    }

    public static Preferences getInstance(){
        return instance;
    }

    private Preferences(Context mContext){
        this.mContext = mContext;
        sp = mContext.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
    }

    public boolean getLoginStatus(){
        return sp.getBoolean(Constants.LOGIN_STATUS,false);
    }

    public void setLoginStatus(boolean loginStatus){
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(Constants.LOGIN_STATUS,loginStatus);
        edit.apply();
    }

    public String getAccount(){
        return sp.getString(Constants.ACCOUNT,"");
    }

    public void setAccount(String account){
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(Constants.ACCOUNT,account);
        edit.apply();
    }
}
