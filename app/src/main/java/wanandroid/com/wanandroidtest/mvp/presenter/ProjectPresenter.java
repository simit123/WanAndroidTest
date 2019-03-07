package wanandroid.com.wanandroidtest.mvp.presenter;

import java.util.List;

import io.reactivex.functions.Consumer;
import wanandroid.com.wanandroidtest.base.BasePresenter;
import wanandroid.com.wanandroidtest.mvp.contract.ProjectContract;
import wanandroid.com.wanandroidtest.mvp.model.ProjectModel;
import wanandroid.com.wanandroidtest.mvp.model.bean.BaseResponse1;
import wanandroid.com.wanandroidtest.mvp.model.bean.ProjectClassifyData;
import wanandroid.com.wanandroidtest.utils.StringUtils;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2019/3/6 ADD
 */

public class ProjectPresenter extends BasePresenter<ProjectContract.IProjectV> implements ProjectContract.IProjectP {


    @Override
    public void getProjectData() {
        checkViewAttach();
        addSubscription(ProjectModel.getProjectData().subscribe(new Consumer<BaseResponse1<List<ProjectClassifyData>>>() {
            @Override
            public void accept(BaseResponse1<List<ProjectClassifyData>> data) throws Exception {
                if (data != null) {
                    if (!StringUtils.isNull(data.getData())) {
                        mRootViw.showProjectData(data.getData());
                    }
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mRootViw.showError(throwable.getMessage());
            }
        }));
    }
}
