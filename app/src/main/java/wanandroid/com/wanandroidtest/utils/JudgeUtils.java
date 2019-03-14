package wanandroid.com.wanandroidtest.utils;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2019/3/14 ADD
 */

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import wanandroid.com.wanandroidtest.app.Constants;
import wanandroid.com.wanandroidtest.ui.activity.ArticleDetailActivity;

/**
 * 公共跳转类
 */
public class JudgeUtils {

    public static void startArticleDetailActivity(Context mActivity, ActivityOptions activityOptions, int id, String articleTitle,
                                                  String articleLink, boolean isCollect,
                                                  boolean isCollectPage, boolean isCommonSite){
        Intent intent = new Intent(mActivity, ArticleDetailActivity.class);
        intent.putExtra(Constants.ARTICLE_ID, id);
        intent.putExtra(Constants.ARTICLE_TITLE, articleTitle);
        intent.putExtra(Constants.ARTICLE_LINK, articleLink);
        intent.putExtra(Constants.IS_COLLECT, isCollect);
        intent.putExtra(Constants.IS_COLLECT_PAGE, isCollectPage);
        intent.putExtra(Constants.IS_COMMON_SITE, isCommonSite);
        if (activityOptions != null && !Build.MANUFACTURER.contains("samsung") && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mActivity.startActivity(intent, activityOptions.toBundle());
        } else {
            mActivity.startActivity(intent);
        }

    }
}
