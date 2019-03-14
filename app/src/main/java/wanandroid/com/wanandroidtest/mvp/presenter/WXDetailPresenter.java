package wanandroid.com.wanandroidtest.mvp.presenter;

import io.reactivex.functions.Consumer;
import wanandroid.com.wanandroidtest.base.BasePresenter;
import wanandroid.com.wanandroidtest.mvp.contract.WXDetailContract;
import wanandroid.com.wanandroidtest.mvp.model.WXDetailModel;
import wanandroid.com.wanandroidtest.mvp.model.bean.BaseResponse1;
import wanandroid.com.wanandroidtest.mvp.model.bean.FeedArticleListData;
import wanandroid.com.wanandroidtest.net.BaseObserver;
import wanandroid.com.wanandroidtest.utils.StringUtils;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2019/3/7 ADD
 */

public class WXDetailPresenter extends BasePresenter<WXDetailContract.IwxDetailV> implements WXDetailContract.IwxDetailP{

    @Override
    public void getWXDetailData(int id, int page) {
        checkViewAttach();
       addSubscription(WXDetailModel.getWXDetailData(id,page)
               .subscribeWith(new BaseObserver<BaseResponse1<FeedArticleListData>>(mRootViw) {
                   @Override
                   public void onNext(BaseResponse1<FeedArticleListData> baseData) {
                       if (baseData != null) {
                           if (!StringUtils.isNull(baseData.getData())) {
                               mRootViw.showDetailData(baseData.getData().getDatas());
                           }
                       }
                   }
               }));
//               .subscribe(new Consumer<BaseResponse1<FeedArticleListData>>() {
//           @Override
//           public void accept(BaseResponse1<FeedArticleListData> baseData) throws Exception {
//               if (baseData != null) {
//                   if (!StringUtils.isNull(baseData.getData())) {
//                       mRootViw.showDetailData(baseData.getData().getDatas());
//                   }
//               }
//           }
//       }, new Consumer<Throwable>() {
//           @Override
//           public void accept(Throwable throwable) throws Exception {
//                mRootViw.showError(throwable.getMessage());
//           }
//       }));
    }
}
