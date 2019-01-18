package wanandroid.com.wanandroidtest.mvp.contract;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2019/1/15 ADD
 */

import wanandroid.com.wanandroidtest.base.IBasePresenter;
import wanandroid.com.wanandroidtest.base.IBaseView;

/**
 * 登录约束类 约束类中是 view的接口和presenter的接口
 */
public class LoginContract {

    //登录成功和失败的UI处理
    public interface LoginView extends IBaseView {
        void showLoginSuccess();

        void showLoginFail(String err);
    }

    public interface LoginPresenter extends IBasePresenter<LoginView> {
        void login(String account, String password);
    }

}
