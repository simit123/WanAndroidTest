package wanandroid.com.wanandroidtest.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import wanandroid.com.wanandroidtest.R;
import wanandroid.com.wanandroidtest.base.BaseFragment;
import wanandroid.com.wanandroidtest.mvp.contract.NavigationContract;
import wanandroid.com.wanandroidtest.mvp.model.bean.NavigationListData;
import wanandroid.com.wanandroidtest.mvp.presenter.NavigationPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationFragment extends BaseFragment implements NavigationContract.INavigationV{


    @BindView(R.id.navigation_tab_layout)
    VerticalTabLayout navigationTabLayout;
    @BindView(R.id.navigation_divider)
    View navigationDivider;
    @BindView(R.id.navigation_RecyclerView)
    RecyclerView navigationRecyclerView;
    @BindView(R.id.normal_view)
    LinearLayout normalView;
    private NavigationPresenter mNavigationPresenter;

    public static NavigationFragment getInstance(String title) {
        NavigationFragment mNavigationFragment = new NavigationFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        mNavigationFragment.setArguments(bundle);
        return mNavigationFragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_navigation;
    }

    @Override
    public void initView() {
        mNavigationPresenter = new NavigationPresenter();
        mNavigationPresenter.attachView(this);
        mNavigationPresenter.getNavigationData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showNavigationData(List<NavigationListData> data) {

    }
}
