package wanandroid.com.wanandroidtest.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wanandroid.com.wanandroidtest.R;
import wanandroid.com.wanandroidtest.base.BaseFragment;
import wanandroid.com.wanandroidtest.mvp.contract.ProjectContract;
import wanandroid.com.wanandroidtest.mvp.model.bean.ProjectClassifyData;
import wanandroid.com.wanandroidtest.mvp.presenter.ProjectPresenter;
import wanandroid.com.wanandroidtest.utils.CommonUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectFragment extends BaseFragment implements ProjectContract.IProjectV {


    @BindView(R.id.project_tab_layout)
    SlidingTabLayout projectTabLayout;
    @BindView(R.id.project_divider)
    View projectDivider;
    @BindView(R.id.project_viewpager)
    ViewPager projectViewpager;
    @BindView(R.id.normal_view)
    LinearLayout normalView;
    private ProjectPresenter projectPresenter;

    public static ProjectFragment getInstance(String title) {
        ProjectFragment mProjectFragment = new ProjectFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        mProjectFragment.setArguments(bundle);
        return mProjectFragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    public void initView() {
        projectPresenter = new ProjectPresenter();
        projectPresenter.attachView(this);
        projectPresenter.getProjectData();
        if (CommonUtils.isNetworkConnected()) {
            showLoading();
        }

    }

    private void initViewPager(List<ProjectClassifyData> data) {
        List<ProjectListFragment> mFragments = new ArrayList<>();
        for (ProjectClassifyData mData : data) {
            ProjectListFragment listFragment = ProjectListFragment.newInstance(mData.getName(),mData.getId());
            mFragments.add(listFragment);
        }

        projectViewpager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return data.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return data.get(position).getName();
            }
        });
        projectTabLayout.setViewPager(projectViewpager);
    }

    @Override
    public void showProjectData(List<ProjectClassifyData> data) {
        initViewPager(data);
        showNormal();
    }
}
