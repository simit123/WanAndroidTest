package wanandroid.com.wanandroidtest.scheduler;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2018/12/20 ADD
 */

public class BaseScheduler<T> implements ObservableTransformer<T,T> {
    private Scheduler subscribeOnScheduler;
    private Scheduler observeOnScheduler;

    /**
     * @param subscribeOnScheduler 观察者所在线程
     * @param observeOnScheduler 被观察者所在线程
     */
    public BaseScheduler(Scheduler subscribeOnScheduler, Scheduler observeOnScheduler) {
        this.subscribeOnScheduler = subscribeOnScheduler;
        this.observeOnScheduler = observeOnScheduler;
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream.subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler);
    }
}
