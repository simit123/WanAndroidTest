package wanandroid.com.wanandroidtest.ui.fragment;

import android.app.ActivityOptions;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wanandroid.com.wanandroidtest.R;
import wanandroid.com.wanandroidtest.adapter.ProjectListAdapter;
import wanandroid.com.wanandroidtest.base.BaseFragment;
import wanandroid.com.wanandroidtest.mvp.contract.ProjectListContract;
import wanandroid.com.wanandroidtest.mvp.model.bean.FeedArticleData;
import wanandroid.com.wanandroidtest.mvp.model.bean.ProjectListData;
import wanandroid.com.wanandroidtest.mvp.presenter.ProjectListPresenter;
import wanandroid.com.wanandroidtest.utils.CommonUtils;
import wanandroid.com.wanandroidtest.utils.JudgeUtils;

public class ProjectListFragment extends BaseFragment implements ProjectListContract.IProjectListV {


    @BindView(R.id.project_list_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.normal_view)
    SmartRefreshLayout normalView;
    private ProjectListPresenter mPresenter;
    private List<FeedArticleData> mList;
    private ProjectListAdapter mAdapter;

    public ProjectListFragment() {
        // Required empty public constructor
    }

    public static ProjectListFragment newInstance(String title, int cid) {
        ProjectListFragment fragment = new ProjectListFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putInt("cid", cid);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_project_list;
    }

    @Override
    public void initView() {
        mPresenter = new ProjectListPresenter();
        mPresenter.attachView(this);
        Bundle bundle = getArguments();
        int cid = bundle.getInt("cid");
        mPresenter.getData(0, cid);
        if (CommonUtils.isNetworkConnected()) {
            showLoading();
        }
        initRecyclerView();

    }

    @Override
    public void showProjectListData(ProjectListData mData) {
        List<FeedArticleData> datas = mData.getDatas();
        mAdapter.addData(datas);
        showNormal();
    }

    private void initRecyclerView(){
        mList = new ArrayList<>();
        mAdapter = new ProjectListAdapter(R.layout.item_project_list, mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            startDetailPage(view,position);
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
