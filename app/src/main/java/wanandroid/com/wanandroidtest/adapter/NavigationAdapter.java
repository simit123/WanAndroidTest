package wanandroid.com.wanandroidtest.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import wanandroid.com.wanandroidtest.R;
import wanandroid.com.wanandroidtest.mvp.model.bean.FeedArticleData;
import wanandroid.com.wanandroidtest.mvp.model.bean.NavigationListData;
import wanandroid.com.wanandroidtest.utils.CommonUtils;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2019/3/5 ADD
 */

public class NavigationAdapter extends BaseQuickAdapter<NavigationListData,NavigationViewHolder> {
    public NavigationAdapter(int layoutResId, @Nullable List<NavigationListData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(NavigationViewHolder helper, NavigationListData item) {
        if (!TextUtils.isEmpty(item.getName())) {
            helper.setText(R.id.item_navigation_tv,item.getName());
        }
        TagFlowLayout tagFlowLayout = helper.getView(R.id.item_navigation_flow_layout);
        List<FeedArticleData> mArticleData = item.getArticles();
        tagFlowLayout.setAdapter(new TagAdapter<FeedArticleData>(mArticleData) {

            @Override
            public View getView(FlowLayout parent, int position, FeedArticleData feedArticleData) {
                TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.flow_layout_tv,
                        tagFlowLayout, false);
                if (feedArticleData == null) {
                    return null;
                }
                String name = feedArticleData.getTitle();
                tv.setPadding(CommonUtils.dp2px(10),CommonUtils.dp2px(10),CommonUtils.dp2px(10),CommonUtils.dp2px(10));
                tv.setTextColor(CommonUtils.randomColor());
                tv.setText(name);
                tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                    @Override
                    public boolean onTagClick(View view, int position, FlowLayout parent) {
                        return false;
                    }
                });

                return tv;
            }
        });
    }
}
