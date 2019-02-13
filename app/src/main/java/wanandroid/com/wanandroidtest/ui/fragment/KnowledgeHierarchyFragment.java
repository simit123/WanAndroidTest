package wanandroid.com.wanandroidtest.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wanandroid.com.wanandroidtest.R;
import wanandroid.com.wanandroidtest.adapter.KnowledgeHierarchyAdapter;
import wanandroid.com.wanandroidtest.base.BaseFragment;
import wanandroid.com.wanandroidtest.mvp.contract.KnowledgeHierarchyContract;
import wanandroid.com.wanandroidtest.mvp.model.bean.KnowledgeHierarchyData;
import wanandroid.com.wanandroidtest.mvp.presenter.KnowledgePresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class KnowledgeHierarchyFragment extends BaseFragment implements KnowledgeHierarchyContract.KnowledgeView {

    @BindView(R.id.knowledge_hierarchy_recycler_view)
    RecyclerView knowledgeHierarchyRecyclerView;
    @BindView(R.id.normal_view)
    SmartRefreshLayout normalView;
    private KnowledgePresenter knowledgePresenter;

    public static KnowledgeHierarchyFragment getInstance(String title) {
        KnowledgeHierarchyFragment mKnowledgeHierarchyFragment = new KnowledgeHierarchyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        mKnowledgeHierarchyFragment.setArguments(bundle);
        return mKnowledgeHierarchyFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_knowledge_hierarchy;
    }

    @Override
    public void initView() {
        knowledgePresenter = new KnowledgePresenter();
        knowledgePresenter.attachView(this);
        knowledgePresenter.getKnowledge();


    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showSuccess(List<KnowledgeHierarchyData> data) {
        initRecyclerView(data);
    }

    @Override
    public void showFail(String err) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (knowledgePresenter != null) {
            knowledgePresenter.detachView();
        }
    }


    private void initRecyclerView(List<KnowledgeHierarchyData> data){
        KnowledgeHierarchyAdapter knowledgeHierarchyAdapter = new KnowledgeHierarchyAdapter(R.layout.item_knowledge_hierarchy,data);
        knowledgeHierarchyRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        knowledgeHierarchyRecyclerView.setHasFixedSize(true);
        knowledgeHierarchyRecyclerView.setAdapter(knowledgeHierarchyAdapter);
    }
}
