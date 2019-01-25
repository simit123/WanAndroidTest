package wanandroid.com.wanandroidtest.adapter;

import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import java.util.List;

import wanandroid.com.wanandroidtest.R;
import wanandroid.com.wanandroidtest.mvp.model.bean.FeedArticleData;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2019/1/23 ADD
 */

public class MainPageAdapter extends BaseQuickAdapter<FeedArticleData,KnowledgeHierarchyListViewHolder> {


    public MainPageAdapter(int layoutResId, @Nullable List<FeedArticleData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(KnowledgeHierarchyListViewHolder helper, FeedArticleData article) {
        if (!TextUtils.isEmpty(article.getTitle())) {
            helper.setText(R.id.item_search_pager_title, Html.fromHtml(article.getTitle()));
        }
        if (article.isCollect()) {
            helper.setImageResource(R.id.item_search_pager_like_iv, R.drawable.icon_like);
        } else {
            helper.setImageResource(R.id.item_search_pager_like_iv, R.drawable.icon_like_article_not_selected);
        }
        if (!TextUtils.isEmpty(article.getAuthor())) {
            helper.setText(R.id.item_search_pager_author, article.getAuthor());
        }
        if (!TextUtils.isEmpty(article.getChapterName())) {
            String classifyName = article.getSuperChapterName() + " / " + article.getChapterName();
//            if (isCollectPage) {
//                helper.setText(R.id.item_search_pager_chapterName, article.getChapterName());
//            } else {
                helper.setText(R.id.item_search_pager_chapterName, classifyName);
//            }
        }
        if (!TextUtils.isEmpty(article.getNiceDate())) {
            helper.setText(R.id.item_search_pager_niceDate, article.getNiceDate());
        }
    }
}
