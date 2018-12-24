package wanandroid.com.wanandroidtest.base;

/**
 * presenter 的基类，泛型参数为 IBaseView 的子类
 * @param <T>
 */
public interface IBasePresenter<T extends IBaseView> {

    void attachView(T mRootView);
    void detachView();
}
