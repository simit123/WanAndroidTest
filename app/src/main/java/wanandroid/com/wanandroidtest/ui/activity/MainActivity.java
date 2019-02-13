package wanandroid.com.wanandroidtest.ui.activity;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.header.storehouse.StoreHouseBarItem;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import wanandroid.com.wanandroidtest.R;
import wanandroid.com.wanandroidtest.app.MyApplication;
import wanandroid.com.wanandroidtest.base.BaseActivity;
import wanandroid.com.wanandroidtest.scheduler.SchedulerUtils;
import wanandroid.com.wanandroidtest.ui.fragment.KnowledgeHierarchyFragment;
import wanandroid.com.wanandroidtest.ui.fragment.MainPagerFragment;
import wanandroid.com.wanandroidtest.ui.fragment.NavigationFragment;
import wanandroid.com.wanandroidtest.ui.fragment.ProjectFragment;
import wanandroid.com.wanandroidtest.ui.fragment.WxArticleFragment;
import wanandroid.com.wanandroidtest.utils.BottomNavigationViewHelper;
import wanandroid.com.wanandroidtest.utils.CommonUtils;
import wanandroid.com.wanandroidtest.utils.Preferences;
import wanandroid.com.wanandroidtest.utils.StatusBarUtil;

public class MainActivity extends BaseActivity {

    @BindView(R.id.common_toolbar_title_tv)
    TextView commonToolbarTitleTv;
    @BindView(R.id.common_toolbar)
    Toolbar commonToolbar;
    @BindView(R.id.fragment_group)
    FrameLayout fragmentGroup;
    @BindView(R.id.main_floating_action_btn)
    FloatingActionButton mainFloatingActionBtn;
    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    private int index = 0;//Fragment的角标
    private MainPagerFragment mMainPagerFragment = null;
    private KnowledgeHierarchyFragment mKnowledgeHierarchyFragment = null;
    private WxArticleFragment mWxArticleFragment = null;
    private NavigationFragment mNavigationFragment = null;
    private ProjectFragment mProjectFragment = null;
    private TextView loginView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            index = savedInstanceState.getInt("currTabIndex");
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onViewCreated() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Observable.just("")
                        .compose(SchedulerUtils.ioToMain())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Exception {
                                Log.i("currentThread","currentThread =  accept >>>> "+Thread.currentThread().getName());
                            }
                        });
                Log.i("currentThread","currentThread"+Thread.currentThread().getName());
            }
        }).start();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initToolbar() {
        setSupportActionBar(commonToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            return;
        }
        //隐藏默认title
        actionBar.setDisplayShowTitleEnabled(false);
        commonToolbarTitleTv.setText(getString(R.string.home_pager));
        StatusBarUtil.setStatusColor(getWindow(), ContextCompat.getColor(this, R.color.main_status_bar_blue), 1f);
        commonToolbar.setNavigationOnClickListener(v -> CommonUtils.showMessage(this, "点击"));

    }

    @Override
    protected void initData() {
        initBottomNavigationView();
        initDrawerLayout();
        setIndex(bottomNavigationView.getSelectedItemId());
        switchFragment(index);
        initNavigationView();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currTabIndex",index);
    }

    /**
     * 设置BottomNavigationView正常显示，不设置会错位显示
     */
    private void initBottomNavigationView() {
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item ->
        {
            setIndex(item.getItemId());
            switchFragment(index);
            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_usage:
                CommonUtils.showMessage(this, "常用网站");
                break;
            case R.id.action_search:
                CommonUtils.showMessage(this, "搜索");
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void initDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, commonToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //获取mDrawerLayout中的第一个子布局，也就是布局中的RelativeLayout
                //获取主页内容的view
                View mContent = drawerLayout.getChildAt(0);
                float scale = 1 - slideOffset;
                float endScale = 0.8f + scale * 0.2f;
                float startScale = 1 - 0.3f * scale;

                //设置左边菜单滑动后的占据屏幕大小
                drawerView.setScaleX(startScale);
                drawerView.setScaleY(startScale);
                //设置主页内容的透明度
                drawerView.setAlpha(0.6f + 0.4f * (1 - scale));

                //设置内容界面水平和垂直方向偏转量
                //在滑动时内容界面的宽度为 屏幕宽度减去菜单界面所占宽度
                mContent.setTranslationX(drawerView.getMeasuredWidth() * (1 - scale));
                //设置内容界面操作无效（比如有button就会点击无效）
                mContent.invalidate();
                //设置右边主页布局滑动后的占据屏幕大小
                mContent.setScaleX(endScale);
                mContent.setScaleY(endScale);
            }
        };
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);
    }


    /**
     * 根据角标切换fragment
     * @param position
     */
    private void switchFragment(int position) {
        FragmentTransaction fs = getSupportFragmentManager().beginTransaction();
        hideFragment(fs);

        switch (position) {
            case 0:
                if (mMainPagerFragment != null) {
                    fs.show(mMainPagerFragment);
                } else {
                    mMainPagerFragment = MainPagerFragment.getInstance(getString(R.string.home_pager));
                    fs.add(R.id.fragment_group, mMainPagerFragment, getString(R.string.home_pager));
                }
                commonToolbarTitleTv.setText(getString(R.string.home_pager));
                break;
            case 1:
                if (mKnowledgeHierarchyFragment != null) {
                    fs.show(mKnowledgeHierarchyFragment);
                } else {
                    mKnowledgeHierarchyFragment = KnowledgeHierarchyFragment.getInstance(getString(R.string.knowledge_hierarchy));
                    fs.add(R.id.fragment_group, mKnowledgeHierarchyFragment, getString(R.string.knowledge_hierarchy));
                }
                commonToolbarTitleTv.setText(getString(R.string.knowledge_hierarchy));
                break;
            case 2:
                if (mWxArticleFragment != null) {
                    fs.show(mWxArticleFragment);
                } else {
                    mWxArticleFragment = WxArticleFragment.getInstance(getString(R.string.wx_article));
                    fs.add(R.id.fragment_group, mWxArticleFragment, getString(R.string.wx_article));
                }
                commonToolbarTitleTv.setText(getString(R.string.wx_article));
                break;
            case 3:
                if (mNavigationFragment != null) {
                    fs.show(mNavigationFragment);
                } else {
                    mNavigationFragment = NavigationFragment.getInstance(getString(R.string.navigation));
                    fs.add(R.id.fragment_group, mNavigationFragment, getString(R.string.navigation));
                }
                commonToolbarTitleTv.setText(getString(R.string.navigation));
                break;
            case 4:
                if (mProjectFragment != null) {
                    fs.show(mNavigationFragment);
                } else {
                    mProjectFragment = ProjectFragment.getInstance(getString(R.string.project));
                    fs.add(R.id.fragment_group, mProjectFragment, getString(R.string.project));
                }
                commonToolbarTitleTv.setText(getString(R.string.project));
                break;
            default:
                break;
        }

        index = position;
        fs.commitAllowingStateLoss();
    }


    /**
     * 隐藏所有fragment
     * @param fs
     */
    private void hideFragment(FragmentTransaction fs) {
        if (mMainPagerFragment != null) {
            fs.hide(mMainPagerFragment);
        }
        if (mKnowledgeHierarchyFragment != null) {
            fs.hide(mKnowledgeHierarchyFragment);
        }
        if (mWxArticleFragment != null) {
            fs.hide(mWxArticleFragment);
        }
        if (mNavigationFragment != null) {
            fs.hide(mNavigationFragment);
        }
        if (mProjectFragment != null) {
            fs.hide(mProjectFragment);
        }
    }

    /**
     * 将BottomNavigationView 的item的id 变换成 index
     * @param itemId BottomNavigationView 的item id
     */
    private void setIndex(int itemId) {
        switch (itemId) {
            case R.id.tab_main_pager:
                //根据选项加载不同的fragment
                index = 0;
                break;
            case R.id.tab_knowledge_hierarchy:
                index = 1;
                break;
            case R.id.tab_wx_article:
                index = 2;
                break;
            case R.id.tab_navigation:
                index = 3;
                break;
            case R.id.tab_project:
                index = 4;
                break;
            default:
                index = 0;
                break;
        }
    }

    /**
     * 设置抽屉布局
     */
    private void initNavigationView(){
        loginView = navView.getHeaderView(0).findViewById(R.id.nav_header_login_tv);
        if (Preferences.getInstance().getLoginStatus()) {
            showLoginView();
        }else {
            showLogoutView();
        }

        navView.getMenu().findItem(R.id.nav_item_wan_android)
                .setOnMenuItemClickListener(item -> {
//                    startMainPager();
                    CommonUtils.showMessage(this,"回到主页面");
                    return true;
                });
        navView.getMenu().findItem(R.id.nav_item_my_collect)
                .setOnMenuItemClickListener(item -> {
                    if (Preferences.getInstance().getLoginStatus()) {
//                        startCollectFragment();
                        CommonUtils.showMessage(this,"收藏");
                        return true;
                    } else {
//                        startActivity(new Intent(this, LoginActivity.class));
                        CommonUtils.showMessage(this, getString(R.string.login_tint));
                        return true;
                    }
                });
        navView.getMenu().findItem(R.id.nav_item_about_us)
                .setOnMenuItemClickListener(item -> {
//                    startActivity(new Intent(this, AboutUsActivity.class));
                    CommonUtils.showMessage(this,"关于我们");
                    return true;
                });
        navView.getMenu().findItem(R.id.nav_item_logout)
                .setOnMenuItemClickListener(item -> {
//                    logout();
                    CommonUtils.showMessage(this,"退出登录");
                    return true;
                });
        navView.getMenu().findItem(R.id.nav_item_setting)
                .setOnMenuItemClickListener(item -> {
//                    startSettingFragment();
                    CommonUtils.showMessage(this,"设置");
                    return true;
                });

    }

    /**
     * 展示登录完成的UI 显示用户名
     */
    private void showLoginView(){
        if (navView == null) {
            return;
        }
        loginView.setText(Preferences.getInstance().getAccount());
        loginView.setOnClickListener(null);
        navView.getMenu().findItem(R.id.nav_item_logout).setVisible(true);
    }

    /**
     * 展示未登录的UI
     */
    private void showLogoutView(){
        loginView.setText(getString(R.string.login_in));
        loginView.setOnClickListener(v -> {
            // TODO: 2018/12/26 跳转登录
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        });
        if (navView == null) {
            return;
        }
        navView.getMenu().findItem(R.id.nav_item_logout).setVisible(false);
    }

}
