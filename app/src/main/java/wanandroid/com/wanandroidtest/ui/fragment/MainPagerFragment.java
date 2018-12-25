package wanandroid.com.wanandroidtest.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wanandroid.com.wanandroidtest.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainPagerFragment extends Fragment {


    public static MainPagerFragment getInstance(String title) {
        MainPagerFragment mMainPagerFragment = new MainPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        mMainPagerFragment.setArguments(bundle);
        return mMainPagerFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_pager, container, false);
    }

}
