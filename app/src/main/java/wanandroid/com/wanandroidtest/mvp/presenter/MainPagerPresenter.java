package wanandroid.com.wanandroidtest.mvp.presenter;

import android.util.Log;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import wanandroid.com.wanandroidtest.base.BasePresenter;
import wanandroid.com.wanandroidtest.mvp.contract.MainPagerContract;
import wanandroid.com.wanandroidtest.mvp.model.MainPageModel;
import wanandroid.com.wanandroidtest.mvp.model.bean.BannerData;
import wanandroid.com.wanandroidtest.mvp.model.bean.BaseResponse;
import wanandroid.com.wanandroidtest.mvp.model.bean.BaseResponse1;
import wanandroid.com.wanandroidtest.mvp.model.bean.FeedArticleListData;
import wanandroid.com.wanandroidtest.scheduler.SchedulerUtils;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2019/1/22 ADD
 */

public class MainPagerPresenter extends BasePresenter<MainPagerContract.IView> implements MainPagerContract.IPresenter {

    MainPageModel mainPageModel;

    @Override
    public void loadMainData(int i) {
        checkViewAttach();
        mRootViw.showLoading();
        mainPageModel = new MainPageModel();
        Observable<BaseResponse1<List<BannerData>>> banner = mainPageModel.getBanner();
        Observable<BaseResponse1<FeedArticleListData>> feedArticleList = mainPageModel.getFeedArticleList(i);


        Disposable subscribe = Observable.zip(banner, feedArticleList, (listBaseResponse1, feedArticleListData) -> createMap(listBaseResponse1, feedArticleListData))
                .compose(SchedulerUtils.ioToMain())
                .subscribe(stringObjectHashMap -> {
                    BaseResponse1<List<BannerData>> bannerData = ((BaseResponse1<List<BannerData>>) stringObjectHashMap.get("BannerData"));
                    BaseResponse1<FeedArticleListData> feedArticleListData = (BaseResponse1<FeedArticleListData>) stringObjectHashMap.get("FeedArticleListData");
                    if (bannerData.getErrorCode() == BaseResponse1.SUCCESS) {
                        //展示banner的ui
                        mRootViw.showBanner(bannerData.getData());
                    }

                    if (feedArticleListData.getErrorCode() == BaseResponse.SUCCESS) {
                        //展示列表ui
                        mRootViw.showArticleList(feedArticleListData.getData());
                    }


                }, throwable -> Log.i("Throwable", "" + throwable.getMessage()));

        addSubscription(subscribe);
    }

    private HashMap<String, Object> createMap(BaseResponse1<List<BannerData>> bannerData, BaseResponse1<FeedArticleListData> feedArticleListData) {
        HashMap<String, Object> map = new HashMap<>(2);
        map.put("BannerData", bannerData);
        map.put("FeedArticleListData", feedArticleListData);
        return map;
    }
}
