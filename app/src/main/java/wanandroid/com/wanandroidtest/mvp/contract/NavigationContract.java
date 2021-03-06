package wanandroid.com.wanandroidtest.mvp.contract;

import java.util.List;

import wanandroid.com.wanandroidtest.base.IBasePresenter;
import wanandroid.com.wanandroidtest.base.IBaseView;
import wanandroid.com.wanandroidtest.mvp.model.bean.NavigationListData;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2019/3/4 ADD
 */

public class NavigationContract {
    public interface INavigationV extends IBaseView{
        void showNavigationData(List<NavigationListData> data);
    }

    public interface INavigationP extends IBasePresenter<INavigationV> {
        void getNavigationData();
    }
}
