package wanandroid.com.wanandroidtest.mvp.contract;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2019/2/28 ADD
 */

import java.util.List;

import wanandroid.com.wanandroidtest.base.IBasePresenter;
import wanandroid.com.wanandroidtest.base.IBaseView;
import wanandroid.com.wanandroidtest.mvp.model.bean.UsefulSiteData;

/**
 * 定义View的接口和Presenter接口
 */
public class UsageContract {

    public interface IUsageView extends IBaseView{
       void showUsageData(List<UsefulSiteData> data);
       void showFail(String err);
    }

    public interface IUsagePresenter extends IBasePresenter<IUsageView> {
        void getUsageData();
    }

}
