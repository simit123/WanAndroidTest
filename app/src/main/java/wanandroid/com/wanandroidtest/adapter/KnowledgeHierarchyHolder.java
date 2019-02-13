package wanandroid.com.wanandroidtest.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import wanandroid.com.wanandroidtest.R;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2019/2/13 ADD
 */

public class KnowledgeHierarchyHolder extends BaseViewHolder {

    @BindView(R.id.item_knowledge_hierarchy_title)
    TextView mTitle;
    @BindView(R.id.item_knowledge_hierarchy_content)
    TextView mContent;

    public KnowledgeHierarchyHolder(View view) {
        super(view);
        ButterKnife.bind(this,view);
    }
}
