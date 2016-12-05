package com.bandeng.bandeng.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bandeng.bandeng.R;
import com.bandeng.bandeng.adpter.WelcomePagerAdapter;
import com.bandeng.bandeng.animation.DepthPageTransformer;
import com.bandeng.bandeng.tools.Constant;
import com.bandeng.bandeng.utils.SPUtils;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager viewPager;
    private Button btn_banner;
    private WelcomePagerAdapter pagerAdapter;

    private int[] imageIds = new int[]{R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        // 如果不是第一次进入就直接进入splash界面，只有在banner的最后一页点击进入才将Constant.ISFIRSTOPEN的值为true
        if (!SPUtils.getBoolean(WelcomeActivity.this, Constant.ISFIRSTOPEN, true)) {
            Intent intent = new Intent(WelcomeActivity.this, SplashActivity.class);
            WelcomeActivity.this.startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
        initData();
    }

    /**
     * 初始化空控件
     */
    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.vp_banner);
        btn_banner = (Button) findViewById(R.id.btn_banner);
        // 在最后一页显示按钮
        btn_banner.setVisibility(View.GONE);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        final ArrayList<ImageView> imageViews = new ArrayList<>();
        imageViews.clear();
        // 将ImageView设置背景资源并且添加到集合中
        for (int imageId : imageIds) {
            ImageView image = new ImageView(this);
            image.setBackgroundResource(imageId);
            imageViews.add(image);
        }

        pagerAdapter = new WelcomePagerAdapter(imageViews);
        viewPager.setAdapter(pagerAdapter);

        // 设置ViewPager的滑动监听事件
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //在滑动中
            }

            @Override
            public void onPageSelected(int position) {
                // 滑到某页
                if (position == imageViews.size() - 1) {
                    // 最后一页显示按钮
                    btn_banner.setVisibility(View.VISIBLE);
                } else {
                    btn_banner.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // 滑动页状态被改变
            }
        });
        // 设置ViewPager切换动画
        viewPager.setPageTransformer(true, new DepthPageTransformer());
//        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());

        btn_banner.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_banner:// 点击按钮跳转到splash界面
                Intent intent = new Intent(WelcomeActivity.this, SplashActivity.class);
                WelcomeActivity.this.startActivity(intent);
                // 记录已经被打开过了
                SPUtils.putBoolean(WelcomeActivity.this, Constant.ISFIRSTOPEN, false);
                WelcomeActivity.this.finish();
                break;
            default:
        }
    }
}
