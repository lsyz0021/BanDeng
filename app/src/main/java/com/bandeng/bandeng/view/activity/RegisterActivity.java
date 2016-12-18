package com.bandeng.bandeng.view.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bandeng.bandeng.R;
import com.bandeng.bandeng.utils.NetUtils;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements TextView.OnEditorActionListener {

    @BindView(R.id.et_register_name)
    public EditText et_register_name;

    @BindViews({R.id.et_register_pwd1, R.id.et_register_pwd2})
    public List<EditText> et_register_pwd;

    @BindView(R.id.btn_register_submit)
    public Button btn_register_submit;

    @BindView(R.id.pb_register_progress_bar)
    public ProgressBar mpProgressBar;
    @BindView(R.id.ll_register_linearlayout)
    public LinearLayout mRegisterFormView;


    private String requestParams;
    private static final int register_action_name = 102;
    private static final int register_action_pwd1 = 103;
    private static final int register_action_pwd2 = 104;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_register;
    }

    @Override
    public void initViewData() {
        et_register_name.setError(null);
        et_register_pwd.get(0).setError(null);
        et_register_pwd.get(1).setError(null);

    }

    @Override
    public void initListener() {
        // 输入法Entry键监听
        et_register_name.setOnEditorActionListener(this);
        et_register_pwd.get(0).setOnEditorActionListener(this);
        et_register_pwd.get(1).setOnEditorActionListener(this);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        boolean result = false;
        System.out.println("register actionId = " + actionId);
        switch (actionId) {
            case register_action_name:
                // 光标到输入密码行
                et_register_pwd.get(0).requestFocus();
                result = true;
                break;
            case register_action_pwd1:
                // 光标到确认密码行
                et_register_pwd.get(1).requestFocus();
                result = true;
                break;
            case register_action_pwd2:
                // 提交数据
                submitData();
                result = true;
                break;


        }
        return result;
    }

    @OnClick(R.id.btn_register_submit)
    public void submitData() {
        String register_name = et_register_name.getText().toString();
        String register_pwd1 = et_register_pwd.get(0).getText().toString();
        String register_pwd2 = et_register_pwd.get(1).getText().toString();

        boolean isCancelRegister = false;
        View focusView = null;

        if (TextUtils.isEmpty(register_name)) {
            et_register_name.setError(getString(R.string.error_empty_name));
            focusView = et_register_name;
            isCancelRegister = true;
        } else if (TextUtils.isEmpty(register_pwd1)) {
            et_register_pwd.get(0).setError(getString(R.string.error_empty_password));
            focusView = et_register_pwd.get(0);
            isCancelRegister = true;
        } else if (TextUtils.isEmpty(register_pwd2)) {
            et_register_pwd.get(1).setError(getString(R.string.error_empty_password));
            focusView = et_register_pwd.get(1);
            isCancelRegister = true;
        } else if (!register_pwd1.equals(register_pwd2)) {
            et_register_pwd.get(1).setError(getString(R.string.error_twice_pwd));
            focusView = et_register_pwd.get(1);
            isCancelRegister = true;
        }

        if (isCancelRegister) {
            focusView.requestFocus();
            return;
        }

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name", register_name);
        hashMap.put("pwd1", register_pwd1);
        hashMap.put("pwd2", register_pwd2);
        requestParams = NetUtils.getRequestParams(hashMap);

        showProgress(true);

        MyAsyncTask asyncTask = new MyAsyncTask();
        asyncTask.execute((Void) null);

        // 进行post网路请求,requestParams,这里进行数据库的操作
        Logger.d("注册成功 = " + requestParams);

    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mRegisterFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mRegisterFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mRegisterFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mpProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            mpProgressBar.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mpProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mpProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            mRegisterFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    private class MyAsyncTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void[] params) {
            SystemClock.sleep(2000);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            super.onPostExecute(success);
            showProgress(false);

            if (success) {
                // 登录成功
                finish();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                RegisterActivity.this.startActivity(intent);
            } else {
                et_register_pwd.get(0).setError(getString(R.string.error_incorrect_password));
                et_register_pwd.get(0).requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            showProgress(false);
        }
    }
}
