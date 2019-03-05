package wanandroid.com.wanandroidtest.mvp.presenter;

import java.util.List;

import io.reactivex.functions.Consumer;
import wanandroid.com.wanandroidtest.base.BasePresenter;
import wanandroid.com.wanandroidtest.mvp.contract.UsageContract;
import wanandroid.com.wanandroidtest.mvp.model.UsageMode;
import wanandroid.com.wanandroidtest.mvp.model.bean.BaseResponse1;
import wanandroid.com.wanandroidtest.mvp.model.bean.UsefulSiteData;
import wanandroid.com.wanandroidtest.utils.CommonUtils;
import wanandroid.com.wanandroidtest.utils.StringUtils;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2019/2/28 ADD
 */

public class UsagePresenter extends BasePresenter<UsageContract.IUsageView> implements UsageContract.IUsagePresenter {


    UsageMode usageMode = null;


    @Override
    public void getUsageData() {
        checkViewAttach();
        mRootViw.showLoading();
        usageMode = new UsageMode();
        usageMode.getUsefulData().subscribe(baseData -> {
            if (!StringUtils.isNull(baseData.getData())) {
                mRootViw.showUsageData(baseData.getData());
            }
        }, throwable -> mRootViw.showError(throwable.getMessage()));


    }
}
