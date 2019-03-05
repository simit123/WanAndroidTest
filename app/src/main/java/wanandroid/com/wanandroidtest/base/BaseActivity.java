package wanandroid.com.wanandroidtest.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.just.agentweb.LogUtils;

import wanandroid.com.wanandroidtest.utils.CommonUtils;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2018/12/24 ADD
 */

public abstract class BaseActivity extends AbstractBaseActivity implements IBaseView{

    public void showToast(String message) {
        CommonUtils.showMessage(this, message);
    }



    private static final String TAG = "BaseActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("life",TAG);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showError(String err) {
        CommonUtils.showMessage(this, err);
    }

    @Override
    public void reLoad() {

    }

}
