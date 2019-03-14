package wanandroid.com.wanandroidtest.mvp.contract;

import java.util.List;

import wanandroid.com.wanandroidtest.base.IBasePresenter;
import wanandroid.com.wanandroidtest.base.IBaseView;
import wanandroid.com.wanandroidtest.mvp.model.bean.BannerData;
import wanandroid.com.wanandroidtest.mvp.model.bean.BaseResponse1;
import wanandroid.com.wanandroidtest.mvp.model.bean.FeedArticleListData;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2019/1/22 ADD
 */

public class MainPagerContract {

    public interface IView extends IBaseView {
        void showBanner(List<BannerData> bannerData);

        void showArticleList(FeedArticleListData feedArticleListData,boolean isRefresh);
    }

    public interface IPresenter extends IBasePresenter<IView> {
        void loadMainData(int i);
        void loadMoreData();
    }

}
