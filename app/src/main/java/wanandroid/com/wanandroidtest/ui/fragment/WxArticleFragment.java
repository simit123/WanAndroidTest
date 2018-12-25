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
public class WxArticleFragment extends Fragment {


    public static WxArticleFragment getInstance(String title) {
        WxArticleFragment mWxArticleFragment = new WxArticleFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        mWxArticleFragment.setArguments(bundle);
        return mWxArticleFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wx_article, container, false);
    }

}
