package com.xuandexx.android.train.ui;

import com.xuandexx.android.train.R;

import org.xutils.view.annotation.ContentView;

/**
 * 内容提供
 */
@ContentView(R.layout.fragment_mine)
public class SettingFragment extends BaseFragment {


    @Override
    protected void initEvent() {
        TAG = this.getClass().getSimpleName();
    }


}