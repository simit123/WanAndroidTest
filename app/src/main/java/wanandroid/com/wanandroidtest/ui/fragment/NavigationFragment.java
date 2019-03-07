package wanandroid.com.wanandroidtest.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import junit.framework.TestResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.SimpleTabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;
import wanandroid.com.wanandroidtest.R;
import wanandroid.com.wanandroidtest.adapter.NavigationAdapter;
import wanandroid.com.wanandroidtest.base.BaseFragment;
import wanandroid.com.wanandroidtest.mvp.contract.NavigationContract;
import wanandroid.com.wanandroidtest.mvp.model.bean.NavigationListData;
import wanandroid.com.wanandroidtest.mvp.presenter.NavigationPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationFragment extends BaseFragment implements NavigationContract.INavigationV {


    @BindView(R.id.navigation_tab_layout)
    VerticalTabLayout navigationTabLayout;
    @BindView(R.id.navigation_divider)
    View navigationDivider;
    @BindView(R.id.navigation_RecyclerView)
    RecyclerView navigationRecyclerView;
    @BindView(R.id.normal_view)
    LinearLayout normalView;
    private NavigationPresenter mNavigationPresenter;
    private NavigationAdapter mNavigationAdapter;
    private LinearLayoutManager mManager;

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
//        normalView.setVisibility(View.VISIBLE);
        initRecyclerView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showNavigationData(List<NavigationListData> data) {
        navigationTabLayout.setTabAdapter(new SimpleTabAdapter() {
            @Override
            public int getCount() {
                return data == null ? 0 : data.size();
            }

            @Override
            public ITabView.TabTitle getTitle(int position) {
                return new TabView.TabTitle.Builder().setContent(data.get(position).getName())
                        .setTextColor(ContextCompat.getColor(getActivity(), R.color.shallow_green),
                                ContextCompat.getColor(getActivity(), R.color.shallow_grey))
                        .build();
            }
        });
        mNavigationAdapter.replaceData(data);
    }

    private void initRecyclerView() {
        List<NavigationListData> mList = new ArrayList<>();
        mNavigationAdapter = new NavigationAdapter(R.layout.item_navigation, mList);
        mManager = new LinearLayoutManager(getActivity());
        navigationRecyclerView.setLayoutManager(mManager);
        navigationRecyclerView.setHasFixedSize(true);
        navigationRecyclerView.setAdapter(mNavigationAdapter);
        rightFlowLeft();
    }

    private void rightFlowLeft(){
        navigationTabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                //根据position实现联动
                scrollThePosition(position);
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });

        navigationRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    navigationTabLayout.setTabSelected(mManager.findFirstVisibleItemPosition());
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

    }


    private void scrollThePosition(int position){
        int firstVisibleItemPosition = mManager.findFirstVisibleItemPosition();
        int lastVisibleItemPosition = mManager.findLastVisibleItemPosition();
        if (position <= lastVisibleItemPosition){
            //scrollTop
            int top = navigationRecyclerView.getChildAt(lastVisibleItemPosition - firstVisibleItemPosition).getTop();
            navigationRecyclerView.scrollBy(0,top);
        }else if (position <= firstVisibleItemPosition){
            navigationRecyclerView.scrollToPosition(position);
        }else {
            navigationRecyclerView.scrollToPosition(position);

        }
    }
}
