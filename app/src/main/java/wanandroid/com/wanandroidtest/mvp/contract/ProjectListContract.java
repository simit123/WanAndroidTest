package wanandroid.com.wanandroidtest.mvp.contract;

import wanandroid.com.wanandroidtest.base.IBasePresenter;
import wanandroid.com.wanandroidtest.base.IBaseView;
import wanandroid.com.wanandroidtest.mvp.model.bean.ProjectListData;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2019/3/6 ADD
 */

public class ProjectListContract {
    public interface IProjectListV extends IBaseView{
        void showProjectListData(ProjectListData mData);
    }

    public interface IProjectListP extends IBasePresenter<IProjectListV>{
       void getData(int page,int cid);
    }
}
