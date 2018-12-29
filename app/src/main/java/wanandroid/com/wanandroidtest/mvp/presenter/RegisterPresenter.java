package wanandroid.com.wanandroidtest.mvp.presenter;

import android.text.TextUtils;

import dagger.Module;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.ResourceObserver;
import wanandroid.com.wanandroidtest.base.BasePresenter;
import wanandroid.com.wanandroidtest.mvp.contract.RegisterContract;
import wanandroid.com.wanandroidtest.mvp.model.RegisterModel;
import wanandroid.com.wanandroidtest.mvp.model.bean.LoginData;


/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2018/12/29 ADD
 */

public class RegisterPresenter extends BasePresenter<RegisterContract.RegisterView> implements RegisterContract.RegisterPresenter{

    private RegisterModel registerModel;

    @Override
    public void Register(String account, String passWord, String rePassWord) {
        registerModel = new RegisterModel();
        checkViewAttach();
        mRootViw.showLoading();

        Disposable disposable = registerModel.getRegisterData(account, passWord, rePassWord)

                .subscribe(new Consumer<LoginData>() {
                    @Override
                    public void accept(LoginData loginData) throws Exception {

                        if (loginData != null) {
                            if (loginData.getErrorCode() == 0) {
                                mRootViw.showRegisterSuccess();
                            }else {
                                mRootViw.showRegisterFail(loginData.getErrorMsg());
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
