package wanandroid.com.wanandroidtest.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.zhy.view.flowlayout.FlowLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import wanandroid.com.wanandroidtest.R;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2019/3/5 ADD
 */

public class NavigationViewHolder extends BaseViewHolder{
    @BindView(R.id.item_navigation_tv)
    TextView mTitle;
    @BindView(R.id.item_navigation_flow_layout)
    FlowLayout mFlowLayout;

    public NavigationViewHolder(View view) {
        super(view);
        ButterKnife.bind(this,view);
    }
}
