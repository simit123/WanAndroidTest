package wanandroid.com.wanandroidtest.mvp.contract;

import java.util.List;

import wanandroid.com.wanandroidtest.base.BasePresenter;
import wanandroid.com.wanandroidtest.base.IBasePresenter;
import wanandroid.com.wanandroidtest.base.IBaseView;
import wanandroid.com.wanandroidtest.mvp.model.bean.WxAuthor;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2019/2/14 ADD
 */

public class WxAuthorContract {

    public interface IWxAuthorV extends IBaseView{
        void showAuthorData(List<WxAuthor> data);
        void showFail(String err);
    }

    public interface IWxAuthorP extends IBasePresenter<IWxAuthorV> {
        void getAuthorData();
    }
}
