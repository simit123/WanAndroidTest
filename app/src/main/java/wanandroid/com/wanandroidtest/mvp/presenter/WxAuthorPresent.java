package wanandroid.com.wanandroidtest.mvp.presenter;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import wanandroid.com.wanandroidtest.base.BasePresenter;
import wanandroid.com.wanandroidtest.mvp.contract.WxAuthorContract;
import wanandroid.com.wanandroidtest.mvp.model.WxAuthorModel;
import wanandroid.com.wanandroidtest.mvp.model.bean.BaseResponse1;
import wanandroid.com.wanandroidtest.mvp.model.bean.WxAuthor;
import wanandroid.com.wanandroidtest.net.BaseObserver;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2019/2/14 ADD
 */

public class WxAuthorPresent extends BasePresenter<WxAuthorContract.IWxAuthorV> implements WxAuthorContract.IWxAuthorP {

    private WxAuthorModel wxAuthorModel;

    @Override
    public void getAuthorData() {
        checkViewAttach();
        mRootViw.showLoading();
        wxAuthorModel = new WxAuthorModel();
        Disposable disposable = wxAuthorModel.getWxAuthor()
                .subscribeWith(new BaseObserver<BaseResponse1<List<WxAuthor>>>(mRootViw) {
                    @Override
                    public void onNext(BaseResponse1<List<WxAuthor>> listBaseResponse1) {
                        mRootViw.showAuthorData(listBaseResponse1.getData());
                    }
                });

        addSubscription(disposable);

//                .subscribe(new Consumer<BaseResponse1<List<WxAuthor>>>() {
//            @Override
//            public void accept(BaseResponse1<List<WxAuthor>> listBaseResponse1) throws Exception {
//                mRootViw.showAuthorData(listBaseResponse1.getData());
//            }
//        }, new Consumer<Throwable>() {
//            @Override
//            public void accept(Throwable throwable) throws Exception {
//                mRootViw.showFail(throwable.getMessage());
//            }
//        });
    }
}
