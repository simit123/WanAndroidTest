package wanandroid.com.wanandroidtest.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.just.agentweb.LogUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import wanandroid.com.wanandroidtest.mvp.contract.MainPagerContract;
import wanandroid.com.wanandroidtest.utils.ActivityCollector;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2018/12/24 ADD
 */

public abstract class AbstractBaseActivity extends AppCompatActivity{


    private Unbinder unBinder;
    private static final String TAG = "AbstractBaseActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unBinder = ButterKnife.bind(this);
        ActivityCollector.getInstance().addActivity(this);
        onViewCreated();
        initToolbar();
        initData();
        Log.i("life",TAG);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.getInstance().removeActivity(this);
        if (unBinder != null && unBinder != Unbinder.EMPTY) {
            unBinder.unbind();
            unBinder = null;
        }
    }


    /**
     * 在initData()之前执行
     */
    protected abstract void onViewCreated();

    /**
     * 获取当前Activity的UI布局
     *
     * @return 布局id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化ToolBar
     */
    protected abstract void initToolbar();

    /**
     * 初始化数据
     */
    protected abstract void initData();
}
