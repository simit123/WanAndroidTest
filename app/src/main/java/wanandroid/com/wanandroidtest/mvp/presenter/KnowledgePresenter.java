package wanandroid.com.wanandroidtest.mvp.presenter;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2019/2/13 ADD
 */

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import wanandroid.com.wanandroidtest.base.BasePresenter;
import wanandroid.com.wanandroidtest.mvp.contract.KnowledgeHierarchyContract;
import wanandroid.com.wanandroidtest.mvp.model.KnowledgeHierarchyModel;
import wanandroid.com.wanandroidtest.mvp.model.bean.BaseResponse1;
import wanandroid.com.wanandroidtest.mvp.model.bean.KnowledgeHierarchyData;
import wanandroid.com.wanandroidtest.net.BaseObserver;

/**
 * p 层中要拿到view的对象,实际的P要继承BaseP（BaseP 中集中处理了attachView 和 addSubscription 等操作，并且通过BaseP的泛型参数可以将View传入p） 实现P的接口可以使数据流实现
 *  M -> P -> v 的流动
 */
public class KnowledgePresenter extends BasePresenter<KnowledgeHierarchyContract.KnowledgeView> implements KnowledgeHierarchyContract.KnowledgePresenter{


    private KnowledgeHierarchyModel knowledgeHierarchyModel;

    @Override
    public void getKnowledge() {
        checkViewAttach();
        mRootViw.showLoading();
        knowledgeHierarchyModel = new KnowledgeHierarchyModel();

       Disposable mDisposable =  knowledgeHierarchyModel.getKnowledgeHierarchyData()
               .subscribeWith(new BaseObserver<BaseResponse1<List<KnowledgeHierarchyData>>>(mRootViw) {
                   @Override
                   public void onNext(BaseResponse1<List<KnowledgeHierarchyData>> listBaseResponse1) {
                       mRootViw.showSuccess(listBaseResponse1.getData());
                   }
               });


//               .subscribe(new Consumer<BaseResponse1<List<KnowledgeHierarchyData>>>() {
//           @Override
//           public void accept(BaseResponse1<List<KnowledgeHierarchyData>> listBaseResponse1) throws Exception {
//                    mRootViw.showSuccess(listBaseResponse1.getData());
//           }
//       }, new Consumer<Throwable>() {
//           @Override
//           public void accept(Throwable throwable) throws Exception {
//               mRootViw.showFail(throwable.getMessage());
//           }
//       });

       addSubscription(mDisposable);
    }
}
