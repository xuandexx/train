package com.xuandexx.android.train.ui;

import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;

import com.xuandexx.android.train.R;

/**
 * 开屏页
 */
public class SplashActivity extends BaseActivity {

    private static final int sleepTime = 2000;

    @Override
    protected void onCreate(Bundle arg0) {
        setContentView(R.layout.em_activity_splash);
        super.onCreate(arg0);
        RelativeLayout rootLayout = (RelativeLayout) findViewById(R.id.splash_root);
        AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
        animation.setDuration(1500);
        rootLayout.startAnimation(animation);
    }

    @Override
    protected void onStart() {
        super.onStart();
        thread.start();
    }

    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
                startActivity(LoginActivity.class);
            } catch (InterruptedException e) {
            }
        }
    });
}
