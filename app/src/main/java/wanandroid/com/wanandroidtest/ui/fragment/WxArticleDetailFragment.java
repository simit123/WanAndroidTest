package wanandroid.com.wanandroidtest.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wanandroid.com.wanandroidtest.R;
import wanandroid.com.wanandroidtest.base.BaseFragment;


public class WxArticleDetailFragment extends BaseFragment {

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
    RecyclerView weDetailListRecyclerView;
    @BindView(R.id.normal_view)
    SmartRefreshLayout normalView;
    Unbinder unbinder;

    public static WxArticleDetailFragment getInstance(String title) {
        WxArticleDetailFragment mWxArticleDetailFragment = new WxArticleDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        mWxArticleDetailFragment.setArguments(bundle);
        return mWxArticleDetailFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_wx_article_detail;
    }

    @Override
    public void initView() {


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
