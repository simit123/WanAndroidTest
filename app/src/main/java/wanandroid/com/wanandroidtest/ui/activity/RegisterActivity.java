package wanandroid.com.wanandroidtest.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import wanandroid.com.wanandroidtest.R;
import wanandroid.com.wanandroidtest.base.BaseActivity;
import wanandroid.com.wanandroidtest.mvp.contract.RegisterContract;
import wanandroid.com.wanandroidtest.mvp.presenter.RegisterPresenter;
import wanandroid.com.wanandroidtest.utils.CommonUtils;
import wanandroid.com.wanandroidtest.utils.StatusBarUtil;

public class RegisterActivity extends BaseActivity implements RegisterContract.RegisterView {

    @BindView(R.id.common_toolbar_title_tv)
    TextView commonToolbarTitleTv;
    @BindView(R.id.common_toolbar)
    Toolbar commonToolbar;
    @BindView(R.id.register_password_edit)
    EditText registerPasswordEdit;
    @BindView(R.id.register_account_edit)
    EditText registerAccountEdit;
    @BindView(R.id.register_confirm_password_edit)
    EditText registerConfirmPasswordEdit;
    @BindView(R.id.register_btn)
    Button registerBtn;

    private RegisterPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onViewCreated() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initToolbar() {
        StatusBarUtil.immersive(this);
        StatusBarUtil.setPaddingSmart(this, commonToolbar);
        commonToolbarTitleTv.setText(R.string.register);
        commonToolbarTitleTv.setTextColor(ContextCompat.getColor(this, R.color.white));
        commonToolbarTitleTv.setTextSize(20);
        commonToolbar.setNavigationOnClickListener(v -> {
            finish();
        });
    }

    @Override
    protected void initData() {
        initSoft();
        mPresenter = new RegisterPresenter();
        mPresenter.attachView(this);
    }

    private void initSoft() {
        InputMethodManager inputMethodManager = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
        if (inputMethodManager != null) {
            registerAccountEdit.requestFocus();
            inputMethodManager.showSoftInput(registerAccountEdit, 0);
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {
        CommonUtils.showMessage(this, "注册失败");
    }


    private void checkNull(String account, String password, String rePassword) {
        if (TextUtils.isEmpty(account)) {
            CommonUtils.showMessage(this, "账号不可为空");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            CommonUtils.showMessage(this, "密码不可为空");
            return;
        }

        if (TextUtils.isEmpty(rePassword)) {
            CommonUtils.showMessage(this, "确认密码不可为空");
            return;
        }

        if (!rePassword.equals(password)) {
            CommonUtils.showMessage(this, "两次密码输入不一致");
            return;
        }
        mPresenter.Register(account, password, rePassword);
    }

    @OnClick({R.id.register_btn})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_btn:
                checkNull(registerAccountEdit.getText().toString().trim(), registerPasswordEdit.getText().toString().trim(), registerConfirmPasswordEdit.getText().toString().trim());
                break;
            default:
                break;
        }
    }

    @Override
    public void showRegisterSuccess() {
        CommonUtils.showMessage(this, "注册成功");
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void showRegisterFail(String err) {
        CommonUtils.showMessage(this, err);
    }
}
