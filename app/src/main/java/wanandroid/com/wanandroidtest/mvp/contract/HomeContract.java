package wanandroid.com.wanandroidtest.mvp.contract;


import wanandroid.com.wanandroidtest.base.IBasePresenter;
import wanandroid.com.wanandroidtest.base.IBaseView;
import wanandroid.com.wanandroidtest.mvp.model.bean.HomeDataBean;

/**
 * 契约类 用来定义View 和 presenter 的子接口
 */
public class HomeContract {

   public interface IView extends IBaseView {

        /**
         * 拿到请求的数据 并 设置给View 加载
         */
        void setData(HomeDataBean homeDataBean);
    }


   public interface IPresenter extends IBasePresenter<IView> {

        /**
         * 请求数据
         */
        void getHomeData();
    }
}
