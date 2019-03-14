package wanandroid.com.wanandroidtest.mvp.presenter;

import android.util.Log;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import wanandroid.com.wanandroidtest.base.BasePresenter;
import wanandroid.com.wanandroidtest.mvp.contract.MainPagerContract;
import wanandroid.com.wanandroidtest.mvp.model.MainPageModel;
import wanandroid.com.wanandroidtest.mvp.model.bean.BannerData;
import wanandroid.com.wanandroidtest.mvp.model.bean.BaseResponse;
import wanandroid.com.wanandroidtest.mvp.model.bean.BaseResponse1;
import wanandroid.com.wanandroidtest.mvp.model.bean.FeedArticleListData;
import wanandroid.com.wanandroidtest.net.BaseObserver;
import wanandroid.com.wanandroidtest.scheduler.SchedulerUtils;
import wanandroid.com.wanandroidtest.utils.StringUtils;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2019/1/22 ADD
 */

public class MainPagerPresenter extends BasePresenter<MainPagerContract.IView> implements MainPagerContract.IPresenter {

    MainPageModel mainPageModel;
    private int page = 0;
//    private boolean isRefresh = false;//是否属于刷新状态

    @Override
    public void loadMainData(int i) {
        page = 0;
        checkViewAttach();
        mRootViw.showLoading();
        mainPageModel = new MainPageModel();
        Observable<BaseResponse1<List<BannerData>>> banner = mainPageModel.getBanner();
        Observable<BaseResponse1<FeedArticleListData>> feedArticleList = mainPageModel.getFeedArticleList(i);


        Disposable subscribe = Observable.zip(banner, feedArticleList, (listBaseResponse1, feedArticleListData) -> createMap(listBaseResponse1, feedArticleListData))
                .compose(SchedulerUtils.ioToMain())
                .subscribeWith(new BaseObserver<HashMap<String, Object>>(mRootViw) {


                    @Override
                    public void onNext(HashMap<String, Object> stringObjectHashMap) {
                        BaseResponse1<List<BannerData>> bannerData = ((BaseResponse1<List<BannerData>>) stringObjectHashMap.get("BannerData"));
                        BaseResponse1<FeedArticleListData> feedArticleListData = (BaseResponse1<FeedArticleListData>) stringObjectHashMap.get("FeedArticleListData");
                        if (bannerData.getErrorCode() == BaseResponse1.SUCCESS) {
                            //展示banner的ui
                            mRootViw.showBanner(bannerData.getData());
                        }

                        if (feedArticleListData.getErrorCode() == BaseResponse.SUCCESS) {
                            //展示列表ui
                            mRootViw.showArticleList(feedArticleListData.getData(), false);
                        }
                    }
                });


//                .subscribe(stringObjectHashMap -> {
//                    BaseResponse1<List<BannerData>> bannerData = ((BaseResponse1<List<BannerData>>) stringObjectHashMap.get("BannerData"));
//                    BaseResponse1<FeedArticleListData> feedArticleListData = (BaseResponse1<FeedArticleListData>) stringObjectHashMap.get("FeedArticleListData");
//                    if (bannerData.getErrorCode() == BaseResponse1.SUCCESS) {
//                        //展示banner的ui
//                        mRootViw.showBanner(bannerData.getData());
//                    }
//
//                    if (feedArticleListData.getErrorCode() == BaseResponse.SUCCESS) {
//                        //展示列表ui
//                        mRootViw.showArticleList(feedArticleListData.getData(),false);
//                    }
//
//
//                }, throwable -> Log.i("Throwable", "" + throwable.getMessage()));

        addSubscription(subscribe);
    }

    @Override
    public void loadMoreData() {

        page++;
        addSubscription(mainPageModel.getFeedArticleList(page).subscribe(new Consumer<BaseResponse1<FeedArticleListData>>() {
            @Override
            public void accept(BaseResponse1<FeedArticleListData> feedArticleListDataBaseResponse1) throws Exception {
                if (feedArticleListDataBaseResponse1 != null) {
                    if (!StringUtils.isNull(feedArticleListDataBaseResponse1.getData())) {
                        mRootViw.showArticleList(feedArticleListDataBaseResponse1.getData(), true);
                    }
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }));
    }

    private HashMap<String, Object> createMap(BaseResponse1<List<BannerData>> bannerData, BaseResponse1<FeedArticleListData> feedArticleListData) {
        HashMap<String, Object> map = new HashMap<>(2);
        map.put("BannerData", bannerData);
        map.put("FeedArticleListData", feedArticleListData);
        return map;
    }
}
