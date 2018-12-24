package wanandroid.com.wanandroidtest.scheduler;

import io.reactivex.Scheduler;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2018/12/20 ADD
 */

public class IoMainScheduler<T> extends BaseScheduler<T>{

    /**
     * @param subscribeOnScheduler 观察者所在线程
     * @param observeOnScheduler   被观察者所在线程
     */
    public IoMainScheduler(Scheduler subscribeOnScheduler, Scheduler observeOnScheduler) {
        super(subscribeOnScheduler, observeOnScheduler);
    }
}
