/**
 * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xuandexx.android.train.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.xuandexx.android.train.R;
import com.xuandexx.android.train.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.em_activity_main)
public class MainActivity extends BaseActivity {

    protected static final String TAG = "MainActivity";

    private Button[] mTabs;

    private ContentFragment contentFragment;//内容管理

    private HistoryFragment historyFragment;

    private SettingFragment settingFragment;

    private Fragment[] fragments;

    private int index;
    private int currentTabIndex;

    public boolean isConflict = false;

    @ViewInject(R.id.top_back)
    private TextView topBack;
    @ViewInject(R.id.top_title)
    private TextView topTitle;
    @ViewInject(R.id.top_back)
    private TextView topConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        rootLayout = findViewById(R.id.rootLayout);
//        rootLayout.setFitsSystemWindows(true);
        if (savedInstanceState != null) {
            loge("get fragments from saveInstanceState");
            contentFragment = (ContentFragment) getSupportFragmentManager().getFragment(savedInstanceState, ContentFragment.class.getSimpleName());
            historyFragment = (HistoryFragment) getSupportFragmentManager().getFragment(savedInstanceState, HistoryFragment.class.getSimpleName());
            settingFragment = (SettingFragment) getSupportFragmentManager().getFragment(savedInstanceState, SettingFragment.class.getSimpleName());
            fragments = new Fragment[]{contentFragment, historyFragment, settingFragment};
            getSupportFragmentManager().beginTransaction().show(contentFragment).hide(historyFragment).hide(settingFragment).commit();
        } else {
            contentFragment = new ContentFragment();
            historyFragment = new HistoryFragment();
            settingFragment = new SettingFragment();
            fragments = new Fragment[]{contentFragment, historyFragment, settingFragment};
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, contentFragment).add(R.id.fragment_container, historyFragment).hide(historyFragment)
                    .add(R.id.fragment_container, settingFragment).hide(settingFragment).show(contentFragment).commit();
        }
        // 获取华为 HMS 推送 token
    }


    @Override
    protected void initEvent() {
        topTitle.setText(R.string.course);
        Log.d(TAG, "initView");
        mTabs = new Button[3];
        mTabs[0] = findViewById(R.id.btn_conversation);
        mTabs[1] = findViewById(R.id.btn_address_list);
        mTabs[2] = findViewById(R.id.btn_setting);
        // select first tab
        mTabs[0].setSelected(true);


    }


    /**
     * on tab clicked
     *
     * @param view
     */
    public void onTabClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_conversation:
                index = 0;
                topTitle.setText(R.string.course);
                break;
            case R.id.btn_address_list:
                index = 1;
                topTitle.setText(R.string.history);
                break;
            case R.id.btn_setting:
                index = 2;
                topTitle.setText(R.string.setting);
                break;
        }
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                trx.add(R.id.fragment_container, fragments[index]);
            }
            trx.show(fragments[index]).commit();
        }
        mTabs[currentTabIndex].setSelected(false);
        // set current tab selected
        mTabs[index].setSelected(true);
        currentTabIndex = index;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("isConflict", isConflict);


        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
