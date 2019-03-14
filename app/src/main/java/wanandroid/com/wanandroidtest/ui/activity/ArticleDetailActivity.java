package wanandroid.com.wanandroidtest.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;

import butterknife.BindView;
import butterknife.ButterKnife;
import wanandroid.com.wanandroidtest.R;
import wanandroid.com.wanandroidtest.app.Constants;
import wanandroid.com.wanandroidtest.base.BaseActivity;
import wanandroid.com.wanandroidtest.utils.StatusBarUtil;

public class ArticleDetailActivity extends BaseActivity {


    @BindView(R.id.article_detail_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.article_detail_web_view)
    FrameLayout mWebContent;
    @BindView(R.id.article_detail_group)
    LinearLayout articleDetailGroup;
    private Bundle mBundle;

    private int articleId;
    private String articleLink;
    private String title;

    private boolean isCollect;
    private boolean isCommonSite;
    private boolean isCollectPage;

    private AgentWeb mWeb;

    @Override
    protected void onViewCreated() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_article_detail;
    }

    @Override
    protected void initToolbar() {
        getBundleData();
        mToolbar.setTitle(Html.fromHtml(title));
        setSupportActionBar(mToolbar);
        StatusBarUtil.immersive(this);
        StatusBarUtil.setPaddingSmart(this, mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        initWebView();

    }

    private void getBundleData(){
        mBundle = getIntent().getExtras();
        title = (String) mBundle.get(Constants.ARTICLE_TITLE);
        articleLink = (String) mBundle.get(Constants.ARTICLE_LINK);
        articleId = ((int) mBundle.get(Constants.ARTICLE_ID));
        isCommonSite = ((boolean) mBundle.get(Constants.IS_COMMON_SITE));
        isCollect = ((boolean) mBundle.get(Constants.IS_COLLECT));
        isCollectPage = ((boolean) mBundle.get(Constants.IS_COLLECT_PAGE));
    }

    private void initWebView(){
        mWeb = AgentWeb.with(this)
                .setAgentWebParent(mWebContent,new LinearLayout.LayoutParams(-1,-1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(articleLink);
        WebView mWebview = mWeb.getWebCreator().getWebView();
        WebSettings mWebviewSettings = mWebview.getSettings();
        mWebviewSettings.setJavaScriptEnabled(true);
        mWebviewSettings.setSupportZoom(true);
        mWebviewSettings.setBuiltInZoomControls(true);
        mWebviewSettings.setDisplayZoomControls(true);
        mWebviewSettings.setUseWideViewPort(true);
        mWebviewSettings.setLoadWithOverviewMode(true);
        mWebviewSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mWeb.handleKeyEvent(keyCode,event)||super.onKeyDown(keyCode,event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mWeb.getWebLifeCycle().onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWeb.getWebLifeCycle().onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWeb.getWebLifeCycle().onResume();
    }
}
