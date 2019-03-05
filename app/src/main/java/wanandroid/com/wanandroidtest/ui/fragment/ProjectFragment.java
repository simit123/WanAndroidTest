package wanandroid.com.wanandroidtest.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wanandroid.com.wanandroidtest.R;
import wanandroid.com.wanandroidtest.base.BaseFragment;
import wanandroid.com.wanandroidtest.base.BasePresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectFragment extends BaseFragment {


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

    }

}
