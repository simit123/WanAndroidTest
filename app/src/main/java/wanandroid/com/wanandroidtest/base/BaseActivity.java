package wanandroid.com.wanandroidtest.base;

import wanandroid.com.wanandroidtest.utils.CommonUtils;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2018/12/24 ADD
 */

public abstract class BaseActivity extends AbstractBaseActivity{

    public void showToast(String message) {
        CommonUtils.showMessage(this, message);
    }
}
