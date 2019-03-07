package wanandroid.com.wanandroidtest.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;

import butterknife.BindView;
import wanandroid.com.wanandroidtest.R;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2019/3/6 ADD
 */

public class ProjectListViewHolder extends BaseViewHolder {


    @BindView(R.id.item_project_list_iv)
    ImageView itemProjectListIv;
    @BindView(R.id.item_project_list_title_tv)
    TextView itemProjectListTitleTv;
    @BindView(R.id.item_project_list_content_tv)
    TextView itemProjectListContentTv;
    @BindView(R.id.item_project_list_time_tv)
    TextView itemProjectListTimeTv;
    @BindView(R.id.item_project_list_author_tv)
    TextView itemProjectListAuthorTv;
    @BindView(R.id.item_project_list_install_tv)
    TextView itemProjectListInstallTv;

    public ProjectListViewHolder(View view) {
        super(view);
    }
}
