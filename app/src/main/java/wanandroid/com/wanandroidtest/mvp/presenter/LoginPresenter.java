package wanandroid.com.wanandroidtest.mvp.presenter;

import android.text.TextUtils;

import java.io.LineNumberInputStream;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import wanandroid.com.wanandroidtest.base.BasePresenter;
import wanandroid.com.wanandroidtest.mvp.contract.LoginContract;
import wanandroid.com.wanandroidtest.mvp.model.LoginModel;
import wanandroid.com.wanandroidtest.mvp.model.RegisterModel;
import wanandroid.com.wanandroidtest.mvp.model.bean.LoginData;
import wanandroid.com.wanandroidtest.utils.CommonUtils;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2019/1/15 ADD
 */

public class LoginPresenter extends BasePresenter<LoginContract.LoginView> implements LoginContract.LoginPresenter{


    private LoginModel loginModel;

    @Override
    public void login(String account, String password) {

        checkViewAttach();
        mRootViw.showLoading();

        loginModel = new LoginModel();
        Disposable disposable = loginModel.getLoginData(account, password)
                .subscribe(new Consumer<LoginData>() {
                    @Override
                    public void accept(LoginData loginData) throws Exception {
                        mRootViw.dismissLoading();
                        if (loginData != null) {
                            if (loginData.getErrorCode() == 0) {
                                mRootViw.showLoginSuccess();
                            }else {
                                mRootViw.showLoginFail(loginData.getErrorMsg());
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mRootViw.dismissLoading();

                    }
                });
        addSubscription(disposable);
    }

}
