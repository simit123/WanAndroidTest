package wanandroid.com.wanandroidtest.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import wanandroid.com.wanandroidtest.R;
import wanandroid.com.wanandroidtest.base.BaseActivity;
import wanandroid.com.wanandroidtest.mvp.contract.LoginContract;
import wanandroid.com.wanandroidtest.mvp.presenter.LoginPresenter;
import wanandroid.com.wanandroidtest.utils.CommonUtils;
import wanandroid.com.wanandroidtest.utils.Preferences;
import wanandroid.com.wanandroidtest.utils.StatusBarUtil;

public class LoginActivity extends BaseActivity implements LoginContract.LoginView {

    @BindView(R.id.login_toolbar)
    Toolbar loginToolbar;
    @BindView(R.id.login_tv)
    TextView loginTv;
    @BindView(R.id.login_account_edit)
    EditText loginAccountEdit;
    @BindView(R.id.login_account_group)
    LinearLayout loginAccountGroup;
    @BindView(R.id.login_divider)
    View loginDivider;
    @BindView(R.id.login_password_edit)
    EditText loginPasswordEdit;
    @BindView(R.id.login_password_group)
    LinearLayout loginPasswordGroup;
    @BindView(R.id.register_divider)
    View registerDivider;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.login_or_tv)
    TextView loginOrTv;
    @BindView(R.id.login_register_btn)
    Button loginRegisterBtn;
    @BindView(R.id.login_group)
    RelativeLayout loginGroup;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onViewCreated() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initToolbar() {
        StatusBarUtil.immersive(this);
        StatusBarUtil.setPaddingSmart(this, loginToolbar);
        loginToolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    protected void initData() {
        loginPresenter = new LoginPresenter();
        loginPresenter.attachView(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showLoginSuccess() {
        CommonUtils.showMessage(this,"登录成功！");
        Preferences.getInstance().setAccount(loginAccountEdit.getText().toString().trim());
        Preferences.getInstance().setLoginStatus(true);
        Intent intent  = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void showLoginFail(String err) {
        CommonUtils.showMessage(this,err);
        Preferences.getInstance().setAccount("");
        Preferences.getInstance().setLoginStatus(false);
    }

    @OnClick({R.id.login_btn, R.id.login_register_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                checkNull();
                break;
            case R.id.login_register_btn:
                Intent intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void checkNull(){
        if (TextUtils.isEmpty(loginAccountEdit.getText().toString().trim()) || TextUtils.isEmpty(loginPasswordEdit.getText().toString().trim())) {
            CommonUtils.showMessage(this,"账号密码不可为空");
            return;
        }
        loginPresenter.login(loginAccountEdit.getText().toString().trim(),loginPasswordEdit.getText().toString().trim());
    }
}
