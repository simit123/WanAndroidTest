package wanandroid.com.wanandroidtest.base;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 *  presenter的基类 用于处理 View 没有attach 可能造成的空指针异常 和 Rxjava 数据流调用过程中导致内存泄漏 （一种情况是 网络请求过程中 Activity由于某种情况而被销毁，rxjava的订阅没有得到及时的释放）
 */
public class BasePresenter<T extends IBaseView> implements IBasePresenter<T> {

    public T mRootViw = null;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void attachView(T mRootView) {
        this.mRootViw = mRootView;
    }

    @Override
    public void detachView() {
        mRootViw = null;
        //取消rxjava的所有订阅
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }

    private boolean isViewAttached(){
        return mRootViw != null;
    }

    public void addSubscription(Disposable disposable ) {
        compositeDisposable.add(disposable);
    }
    /**
     * 每次请求数据之前手动调用此方法检验View 是否Attached
     */
    public void checkViewAttach(){
        if (!isViewAttached()) {
            throw new RuntimeException("Please call IPresenter.attachView(IBaseView) before\" + \" requesting data to the IPresenter");
        }
    }
}
