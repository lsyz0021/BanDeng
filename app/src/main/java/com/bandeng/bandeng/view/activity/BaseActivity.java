package com.bandeng.bandeng.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        ButterKnife.bind(this);

        initViewData();

        initListener();

    }

    /**
     * 获取资源id
     */
    public abstract int getLayoutResId();

    /**
     * 初始view数据
     */
    public abstract void initViewData();

    /**
     * 初始化监听事件
     */
    public abstract void initListener();

}
