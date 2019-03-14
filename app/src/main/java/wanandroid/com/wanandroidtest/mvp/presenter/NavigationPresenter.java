package wanandroid.com.wanandroidtest.mvp.presenter;

import android.support.v4.app.NavUtils;

import java.util.List;

import io.reactivex.functions.Consumer;
import wanandroid.com.wanandroidtest.base.BasePresenter;
import wanandroid.com.wanandroidtest.mvp.contract.NavigationContract;
import wanandroid.com.wanandroidtest.mvp.model.NavigationModel;
import wanandroid.com.wanandroidtest.mvp.model.bean.BaseResponse1;
import wanandroid.com.wanandroidtest.mvp.model.bean.NavigationListData;
import wanandroid.com.wanandroidtest.net.BaseObserver;
import wanandroid.com.wanandroidtest.utils.StringUtils;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2019/3/4 ADD
 */

public class NavigationPresenter extends BasePresenter<NavigationContract.INavigationV> implements NavigationContract.INavigationP {

    @Override
    public void getNavigationData() {
        checkViewAttach();
        addSubscription(NavigationModel.getNavigationData()
                .subscribeWith(new BaseObserver<BaseResponse1<List<NavigationListData>>>(mRootViw) {
                    @Override
                    public void onNext(BaseResponse1<List<NavigationListData>> dataList) {
                        if (dataList != null) {
                            if (!StringUtils.isNull(dataList.getData())) {
                                mRootViw.showNavigationData(dataList.getData());
                            }
                        }
                    }
                }));







//                .subscribe(new Consumer<BaseResponse1<List<NavigationListData>>>() {
//            @Override
//            public void accept(BaseResponse1<List<NavigationListData>> dataList) throws Exception {
//                if (dataList != null) {
//                    if (!StringUtils.isNull(dataList.getData())) {
//                        mRootViw.showNavigationData(dataList.getData());
//                    }
//                }
//            }
//        }, new Consumer<Throwable>() {
//            @Override
//            public void accept(Throwable throwable) throws Exception {
//                mRootViw.showError(throwable.getMessage());
//            }
//        }));
    }
}
