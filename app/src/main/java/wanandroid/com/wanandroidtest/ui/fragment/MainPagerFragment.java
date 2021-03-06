package wanandroid.com.wanandroidtest.ui.fragment;


import android.app.ActivityOptions;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.just.agentweb.LogUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wanandroid.com.wanandroidtest.R;
import wanandroid.com.wanandroidtest.adapter.MainPageAdapter;
import wanandroid.com.wanandroidtest.base.BaseFragment;
import wanandroid.com.wanandroidtest.mvp.contract.MainPagerContract;
import wanandroid.com.wanandroidtest.mvp.model.bean.BannerData;
import wanandroid.com.wanandroidtest.mvp.model.bean.BaseResponse1;
import wanandroid.com.wanandroidtest.mvp.model.bean.FeedArticleData;
import wanandroid.com.wanandroidtest.mvp.model.bean.FeedArticleListData;
import wanandroid.com.wanandroidtest.mvp.presenter.MainPagerPresenter;
import wanandroid.com.wanandroidtest.utils.CommonUtils;
import wanandroid.com.wanandroidtest.utils.GlideImageLoad;
import wanandroid.com.wanandroidtest.utils.JudgeUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainPagerFragment extends BaseFragment implements MainPagerContract.IView {

    @BindView(R.id.main_pager_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.normal_view)
    SmartRefreshLayout normalView;
    MainPagerPresenter mainPagerPresenter;
    private List<FeedArticleData> articleData;
    private MainPageAdapter mAdapter;
    private Banner banner;
    private List<String> bannerTitleList;
    private List<String> bannerUrlList;
    private List<String> bannerImageList;


    public static MainPagerFragment getInstance(String title) {
        MainPagerFragment mMainPagerFragment = new MainPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        mMainPagerFragment.setArguments(bundle);
        return mMainPagerFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main_pager;
    }

    @Override
    public void initView() {
        setRefresh();
        initRecyclerView();
        mainPagerPresenter = new MainPagerPresenter();
        mainPagerPresenter.attachView(this);
        mainPagerPresenter.loadMainData(0);
        if (CommonUtils.isNetworkConnected()) {
            showLoading();
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        if (banner != null) {
            banner.startAutoPlay();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (banner != null) {
            banner.stopAutoPlay();
        }
    }


    @Override
    public void showBanner(List<BannerData> bannerData) {
        initBanner(bannerData);
    }

    @Override
    public void showArticleList(FeedArticleListData feedArticleListData, boolean isRefresh) {

        if (isRefresh) {
//            articleData.addAll(feedArticleListData.getDatas());
            mAdapter.addData(feedArticleListData.getDatas());
            Log.i("isRefresh", "刷新-----");
        } else {
            Log.i("isRefresh", "刷新-----非");

//            articleData = feedArticleListData.getDatas();
            mAdapter.replaceData(feedArticleListData.getDatas());
        }

        showNormal();
    }


    private void initRecyclerView() {
        //recyclerView 先加载空的数据，等数据填充之后再notify
        articleData = new ArrayList<>();
        mAdapter = new MainPageAdapter(R.layout.item_search_pager, articleData);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        LinearLayout inflate = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.head_banner, null);
        banner = ((Banner) inflate.findViewById(R.id.head_banner));
        inflate.removeView(banner);
        mAdapter.addHeaderView(banner);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            startDetailPage(view,position);
        });
    }

    private void initBanner(List<BannerData> bannerData) {
        bannerTitleList = new ArrayList<>();
        bannerUrlList = new ArrayList<>();
        bannerImageList = new ArrayList<>();
        for (int i = 0; i < bannerData.size(); i++) {
            bannerTitleList.add(bannerData.get(i).getTitle());
            bannerUrlList.add(bannerData.get(i).getUrl());
            bannerImageList.add(bannerData.get(i).getImagePath());
        }

        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoad());
        //设置图片集合
        banner.setImages(bannerImageList);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合
        banner.setBannerTitles(bannerTitleList);
        //设置自动轮播
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(bannerData.size() * 300);
        //设置指示器位置
        banner.setIndicatorGravity(BannerConfig.CENTER);

        banner.setOnBannerListener(position -> {
            CommonUtils.showMessage(getActivity(), "点击banner");
        });

        banner.start();
    }

    private void setRefresh() {
        normalView.setOnLoadMoreListener(refreshLayout -> {
            mRecyclerView.stopScroll();
            mainPagerPresenter.loadMoreData();
            refreshLayout.finishLoadMore(1000);
        });
    }

    private void startDetailPage(View view,int position){
        if (mAdapter.getData().size() < 0 || mAdapter.getData().size() < position) {
            return;
        }
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), view, getString(R.string.share_view));
        JudgeUtils.startArticleDetailActivity(getActivity(),
                options,
                mAdapter.getData().get(position).getId(),
                mAdapter.getData().get(position).getTitle(),
                mAdapter.getData().get(position).getLink(),
                mAdapter.getData().get(position).isCollect(),
                false,
                false);
    }
}
