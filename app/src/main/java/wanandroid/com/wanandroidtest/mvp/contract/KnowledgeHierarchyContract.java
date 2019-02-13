package wanandroid.com.wanandroidtest.mvp.contract;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2019/2/13 ADD
 */

import java.util.List;

import wanandroid.com.wanandroidtest.base.IBasePresenter;
import wanandroid.com.wanandroidtest.base.IBaseView;
import wanandroid.com.wanandroidtest.mvp.model.bean.KnowledgeHierarchyData;

/**
 * 定义View的显示接口（接口请求返回:成功或失败） 定义presenter接口，使View 和 p 建立关联（View可以调用 p 的方法 p 调用 model
 *  方法，从而拿到数据）
 */
public class KnowledgeHierarchyContract {
    public interface KnowledgeView extends IBaseView{
        void showSuccess(List<KnowledgeHierarchyData> data);
        void showFail(String err);
    }
    public interface KnowledgePresenter extends IBasePresenter<KnowledgeView> {
        void getKnowledge();
    }
}
