package wanandroid.com.wanandroidtest.app;


import android.content.SharedPreferences;
import android.graphics.Color;

import java.io.File;

import wanandroid.com.wanandroidtest.R;


/**
 * @author quchao
 * @date 2017/11/27
 */

public class Constants {

    /**
     *  SharedPreferences keys
     */
   public static final String LOGIN_STATUS = "LOGIN_STATUS";
   public static final String ACCOUNT = "ACCOUNT";
   public static final int DOUBLE_INTERVAL_TIME = 2000;

    /**
     * Tab colors
     */
    public static final int[] TAB_COLORS = new int[]{
            Color.parseColor("#90C5F0"),
            Color.parseColor("#91CED5"),
            Color.parseColor("#F88F55"),
            Color.parseColor("#C0AFD0"),
            Color.parseColor("#E78F8F"),
            Color.parseColor("#67CCB7"),
            Color.parseColor("#F6BC7E")
    };
}
