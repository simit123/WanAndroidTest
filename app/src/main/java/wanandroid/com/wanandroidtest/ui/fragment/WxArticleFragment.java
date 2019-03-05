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
import wanandroid.com.wanandroidtest.mvp.contract.WxAuthorContract;
import wanandroid.com.wanandroidtest.mvp.model.bean.WxAuthor;
import wanandroid.com.wanandroidtest.mvp.presenter.WxAuthorPresent;

/**
 * A simple {@link Fragment} subclass.
 */
public class WxArticleFragment extends BaseFragment implements WxAuthorContract.IWxAuthorV {


    @BindView(R.id.tab_wx_author)
    SlidingTabLayout tabWxAuthor;
    @BindView(R.id.wx_detail_viewpager)
    ViewPager wxDetailViewpager;
    @BindView(R.id.normal_view)
    LinearLayout normalView;
    private WxAuthorPresent wxAuthorPresent;
    private List<BaseFragment> mFragments = new ArrayList<>();

    public static WxArticleFragment getInstance(String title) {
        WxArticleFragment mWxArticleFragment = new WxArticleFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        mWxArticleFragment.setArguments(bundle);
        return mWxArticleFragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_wx_article;
    }

    @Override
    public void initView() {
        wxAuthorPresent = new WxAuthorPresent();
        wxAuthorPresent.attachView(this);
        wxAuthorPresent.getAuthorData();
    }


    @Override
    public void showAuthorData(List<WxAuthor> data) {
        mFragments.clear();
        for (WxAuthor wxAuthor : data){
            mFragments.add(WxArticleDetailFragment.getInstance(wxAuthor.getName()));
        }
        initTableAndViewPager(data);
    }

    @Override
    public void showFail(String err) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (wxAuthorPresent != null) {
            wxAuthorPresent.detachView();
        }
    }


    private void initTableAndViewPager(List<WxAuthor> data) {
        //ViewPager + Tablayout 以后就这么写
        wxDetailViewpager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return data.get(position).getName();
            }
        });
        tabWxAuthor.setViewPager(wxDetailViewpager);
    }

}
