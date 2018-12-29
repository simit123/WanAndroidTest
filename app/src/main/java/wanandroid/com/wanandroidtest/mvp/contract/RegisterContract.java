package wanandroid.com.wanandroidtest.mvp.contract;

import wanandroid.com.wanandroidtest.base.IBasePresenter;
import wanandroid.com.wanandroidtest.base.IBaseView;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2018/12/29 ADD
 */

public class RegisterContract {


   public interface RegisterView extends IBaseView{
        void showRegisterSuccess();
        void showRegisterFail(String err);
    }

   public interface RegisterPresenter extends IBasePresenter<RegisterView> {
        void Register(String account,String passWord,String rePassWord);
    }
}
