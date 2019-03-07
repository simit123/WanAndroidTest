package wanandroid.com.wanandroidtest.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import wanandroid.com.wanandroidtest.R;
import wanandroid.com.wanandroidtest.adapter.ArticleListAdapter;
import wanandroid.com.wanandroidtest.base.BaseFragment;
import wanandroid.com.wanandroidtest.mvp.contract.WXDetailContract;
import wanandroid.com.wanandroidtest.mvp.model.bean.FeedArticleData;
import wanandroid.com.wanandroidtest.mvp.presenter.WXDetailPresenter;


public class WxArticleDetailFragment extends BaseFragment implements WXDetailContract.IwxDetailV{

    @BindView(R.id.search_back_ib)
    ImageButton searchBackIb;
    @BindView(R.id.search_tv)
    TextView searchTv;
    @BindView(R.id.search_edit)
    EditText searchEdit;
    @BindView(R.id.search_tint_tv)
    TextView searchTintTv;
    @BindView(R.id.search_toolbar)
    Toolbar searchToolbar;
    @BindView(R.id.we_detail_list_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.normal_view)
    SmartRefreshLayout normalView;
    private WXDetailPresenter mPresenter;
    private List<FeedArticleData> mData;
    private ArticleListAdapter articleListAdapter;

    public static WxArticleDetailFragment getInstance(String title,int id) {
        WxArticleDetailFragment mWxArticleDetailFragment = new WxArticleDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putInt("id",id);
        mWxArticleDetailFragment.setArguments(bundle);
        return mWxArticleDetailFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_wx_article_detail;
    }

    @Override
    public void initView() {
        mPresenter = new WXDetailPresenter();
        mPresenter.attachView(this);
        Bundle bundle = getArguments();
        int id = bundle.getInt("id");
        mPresenter.getWXDetailData(id,0);
        initRecyclerView();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showDetailData(List<FeedArticleData> data) {
            articleListAdapter.addData(data);
    }

    private void initRecyclerView(){
        mData = new ArrayList<>();
        articleListAdapter = new ArticleListAdapter(R.layout.item_search_pager, mData);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(articleListAdapter);

    }
}
