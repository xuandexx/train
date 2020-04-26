package com.xuandexx.android.train.ui;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.xuandexx.android.train.R;
import com.xuandexx.android.train.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import cn.jzvd.Jzvd;
import cn.jzvd.demo.CustomJzvd.MyJzvdStd;

@ContentView(R.layout.activity_broadcast)
public class BroadcastActivity extends BaseActivity {


    @ViewInject(R.id.myJzvdStd)
    private MyJzvdStd myJzvdStd;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        x.view().inject(this);
        initEvent();
    }

    @Override
    protected void initEvent() {
        myJzvdStd.setUp("http://jzvd.nathen.cn/342a5f7ef6124a4a8faf00e738b8bee4/cf6d9db0bd4d41f59d09ea0a81e918fd-5287d2089db37e62345123a1be272f8b.mp4"
                , "饺子快长大");
        Glide.with(this).load("http://jzvd-pic.nathen.cn/jzvd-pic/1bb2ebbe-140d-4e2e-abd2-9e7e564f71ac.png").into(myJzvdStd.thumbImageView);
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }


}
