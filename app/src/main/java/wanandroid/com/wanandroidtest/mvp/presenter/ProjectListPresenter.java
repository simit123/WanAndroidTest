package wanandroid.com.wanandroidtest.mvp.presenter;

import io.reactivex.functions.Consumer;
import wanandroid.com.wanandroidtest.base.BasePresenter;
import wanandroid.com.wanandroidtest.mvp.contract.ProjectListContract;
import wanandroid.com.wanandroidtest.mvp.model.ProjectListModel;
import wanandroid.com.wanandroidtest.mvp.model.bean.BaseResponse1;
import wanandroid.com.wanandroidtest.mvp.model.bean.ProjectListData;
import wanandroid.com.wanandroidtest.net.BaseObserver;
import wanandroid.com.wanandroidtest.utils.StringUtils;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2019/3/6 ADD
 */

public class ProjectListPresenter extends BasePresenter<ProjectListContract.IProjectListV> implements ProjectListContract.IProjectListP{


    @Override
    public void getData(int page, int cid) {
        checkViewAttach();
        addSubscription(ProjectListModel.getProjectListData(page,cid)
                .subscribeWith(new BaseObserver<BaseResponse1<ProjectListData>>(mRootViw) {
                    @Override
                    public void onNext(BaseResponse1<ProjectListData> mBaseData) {
                        if (mBaseData != null) {
                            if (!StringUtils.isNull(mBaseData.getData())) {
                                mRootViw.showProjectListData(mBaseData.getData());
                            }
                        }
                    }
                }));




//                .subscribe(new Consumer<BaseResponse1<ProjectListData>>() {
//            @Override
//            public void accept(BaseResponse1<ProjectListData> mBaseData) throws Exception {
//                if (mBaseData != null) {
//                    if (!StringUtils.isNull(mBaseData.getData())) {
//                        mRootViw.showProjectListData(mBaseData.getData());
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
