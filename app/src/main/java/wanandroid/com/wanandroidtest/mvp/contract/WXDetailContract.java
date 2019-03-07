package wanandroid.com.wanandroidtest.mvp.contract;

import java.util.List;

import wanandroid.com.wanandroidtest.base.IBasePresenter;
import wanandroid.com.wanandroidtest.base.IBaseView;
import wanandroid.com.wanandroidtest.mvp.model.bean.FeedArticleData;
import wanandroid.com.wanandroidtest.mvp.model.bean.FeedArticleListData;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2019/3/7 ADD
 */

public class WXDetailContract {
    public interface IwxDetailV extends IBaseView{
        void showDetailData(List<FeedArticleData> data);
    }

    public interface IwxDetailP extends IBasePresenter<IwxDetailV>{
        void getWXDetailData(int id,int page);
    }
}
