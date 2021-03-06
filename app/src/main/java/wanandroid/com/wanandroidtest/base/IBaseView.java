package wanandroid.com.wanandroidtest.base;

/**
 *  view 接口的基类 统一的view ui 处理
 */
public interface IBaseView {

    void showLoading();
    void dismissLoading();
    void showError(String err);
    void reLoad();
    void showLoadError();
    void showNormal();
}
