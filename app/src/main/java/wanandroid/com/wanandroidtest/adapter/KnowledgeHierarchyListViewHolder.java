package wanandroid.com.wanandroidtest.adapter;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import wanandroid.com.wanandroidtest.R;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2019/1/23 ADD
 */

public class KnowledgeHierarchyListViewHolder extends BaseViewHolder {


    @BindView(R.id.item_search_pager_group)
    CardView mItemSearchPagerGroup;
    @BindView(R.id.item_search_pager_like_iv)
    ImageView mItemSearchPagerLikeIv;
    @BindView(R.id.item_search_pager_title)
    TextView mItemSearchPagerTitle;
    @BindView(R.id.item_search_pager_author)
    TextView mItemSearchPagerAuthor;
    @BindView(R.id.item_search_pager_tag_green_tv)
    TextView mTagGreenTv;
    @BindView(R.id.item_search_pager_tag_red_tv)
    TextView mTagRedTv;
    @BindView(R.id.item_search_pager_chapterName)
    TextView mItemSearchPagerChapterName;
    @BindView(R.id.item_search_pager_niceDate)
    TextView mItemSearchPagerNiceDate;

    public KnowledgeHierarchyListViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
