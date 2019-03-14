package wanandroid.com.wanandroidtest.net;

import android.text.TextUtils;

import io.reactivex.observers.ResourceObserver;
import retrofit2.HttpException;
import wanandroid.com.wanandroidtest.R;
import wanandroid.com.wanandroidtest.app.MyApplication;
import wanandroid.com.wanandroidtest.base.IBaseView;
import wanandroid.com.wanandroidtest.utils.LogHelper;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2019/3/12 ADD
 */

/**
 * 封装的观察者，用于集中处理一些错误相关的东西
 */
public abstract class BaseObserver<T> extends ResourceObserver<T> {

    private IBaseView mView;
    private String mErrorMsg;//暴漏给用户的请求错误提示
    private boolean isShowError = true;//是否展示点击重新加载的UI

    protected BaseObserver(IBaseView view){
        this.mView = view;
    }

    protected BaseObserver(IBaseView view, String errorMsg){
        this.mView = view;
        this.mErrorMsg = errorMsg;
    }

    protected BaseObserver(IBaseView view, boolean isShowError){
        this.mView = view;
        this.isShowError = isShowError;
    }

    protected BaseObserver(IBaseView view, String errorMsg, boolean isShowError){
        this.mView = view;
        this.mErrorMsg = errorMsg;
        this.isShowError = isShowError;
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        if (mView == null) {
            return;
        }
        if (mErrorMsg != null && !TextUtils.isEmpty(mErrorMsg)) {
            mView.showError(mErrorMsg);
        } else if (e instanceof ServerException) {
            mView.showError(e.toString());
        } else if (e instanceof HttpException) {
            mView.showError(MyApplication.getInstance().getString(R.string.http_error));
        } else {
            mView.showError(MyApplication.getInstance().getString(R.string.unKnown_error));
            LogHelper.d(e.toString());
        }
        if (isShowError) {
            mView.showLoadError();
        }

    }
}
