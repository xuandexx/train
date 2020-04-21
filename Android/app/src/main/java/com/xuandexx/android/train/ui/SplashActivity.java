package com.xuandexx.android.train.ui;

import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;

import com.xuandexx.android.train.R;

/**
 * 开屏页
 */
public class SplashActivity extends BaseActivity {

    private static final int sleepTime = 20;

    @Override
    protected void onCreate(Bundle arg0) {
        setContentView(R.layout.em_activity_splash);
        super.onCreate(arg0);
        RelativeLayout rootLayout = findViewById(R.id.splash_root);
        AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
        animation.setDuration(1500);
        rootLayout.startAnimation(animation);
        initEvent();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEvent() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(sleepTime);
                    startActivity(LoginActivity.class);
                } catch (InterruptedException e) {
                    logi(e.getMessage());
                }
            }
        });
        thread.start();
    }
}
