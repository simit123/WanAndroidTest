package wanandroid.com.wanandroidtest.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;
import wanandroid.com.wanandroidtest.R;
import wanandroid.com.wanandroidtest.base.BaseActivity;
import wanandroid.com.wanandroidtest.mvp.contract.UsageContract;
import wanandroid.com.wanandroidtest.mvp.model.bean.UsefulSiteData;
import wanandroid.com.wanandroidtest.mvp.presenter.UsagePresenter;
import wanandroid.com.wanandroidtest.utils.CommonUtils;
import wanandroid.com.wanandroidtest.utils.StatusBarUtil;

public class UsageActivity extends BaseActivity implements UsageContract.IUsageView{

    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.common_toolbar)
    Toolbar commonToolbar;
    @BindView(R.id.useful_sites_flow_layout)
    TagFlowLayout usefulSitesFlowLayout;
    @BindView(R.id.usage_scroll_view)
    NestedScrollView usageScrollView;
    @BindView(R.id.usage_coordinator_group)
    CoordinatorLayout usageCoordinatorGroup;
    UsagePresenter usagePresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onViewCreated() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_usage;
    }

    @Override
    protected void initToolbar() {
        mTitleTv.setText(R.string.useful_sites);
        StatusBarUtil.setStatusColor(getWindow(), ContextCompat.getColor(this, R.color.main_status_bar_blue), 1f);
        commonToolbar.setNavigationOnClickListener(v -> {
            finish();
        });

//        if (mPresenter.getNightModeState()) {
//            setToolbarView(R.color.comment_text, R.color.colorCard, R.drawable.ic_arrow_back_white_24dp);
//        } else {
//            setToolbarView(R.color.title_black, R.color.white, R.drawable.ic_arrow_back_grey_24dp);
//        }
//        mToolbar.setNavigationOnClickListener(v -> mCircularRevealAnim.hide(mTitleTv, mRootView));
    }

    @Override
    protected void initData() {
        usagePresenter = new UsagePresenter();
        usagePresenter.attachView(this);
        usagePresenter.getUsageData();

    }



    @Override
    public void showUsageData(List<UsefulSiteData> data) {
        usefulSitesFlowLayout.setAdapter(new TagAdapter<UsefulSiteData>(data) {
            @Override
            public View getView(FlowLayout parent, int position, UsefulSiteData usefulSiteData) {

                TextView tv = ((TextView) LayoutInflater.from(UsageActivity.this).inflate(R.layout.flow_layout_tv, parent, false));
//                assert usefulSiteData != null;//它对一个boolean表达式进行检查，一个正确程序必须保证这个boolean表达式的值为true；如果该值为false，说明程序已经处于不正确的状态下，assert将给出警告或退出
                String name = usefulSiteData.getName();
                tv.setText(name);
                setItemBackground(tv);
                usefulSitesFlowLayout.setOnTagClickListener((view, position1, parent1) -> false);
                return tv;
            }
        });
    }

    @Override
    public void showFail(String err) {

    }
    private void setItemBackground(TextView tv) {
        tv.setBackgroundColor(CommonUtils.randomTagColor());
        tv.setTextColor(ContextCompat.getColor(this, R.color.white));
    }
}
