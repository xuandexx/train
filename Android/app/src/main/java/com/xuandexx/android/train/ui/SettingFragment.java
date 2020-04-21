package com.xuandexx.android.train.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xuandexx.android.train.R;

import org.xutils.view.annotation.ContentView;

/**
 * 内容提供
 */
public class SettingFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root;
        TAG = this.getClass().getSimpleName();
        root = inflater.inflate(R.layout.fragment_mine, container, false);
        return root;
    }

    @Override
    protected void initView() {
        TAG = this.getClass().getSimpleName();
    }

    @Override
    protected void initEvent() {

    }

}