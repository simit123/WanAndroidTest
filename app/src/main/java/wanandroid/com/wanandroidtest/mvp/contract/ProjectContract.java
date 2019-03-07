package wanandroid.com.wanandroidtest.mvp.contract;

import java.util.List;

import wanandroid.com.wanandroidtest.base.IBasePresenter;
import wanandroid.com.wanandroidtest.base.IBaseView;
import wanandroid.com.wanandroidtest.mvp.model.bean.ProjectClassifyData;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2019/3/6 ADD
 */

public class ProjectContract {

    public interface IProjectV extends IBaseView {
        void showProjectData(List<ProjectClassifyData> data);
    }

    public interface IProjectP extends IBasePresenter<IProjectV> {
        void getProjectData();
    }
}
