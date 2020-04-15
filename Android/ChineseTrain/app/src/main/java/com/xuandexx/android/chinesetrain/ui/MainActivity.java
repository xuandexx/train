package com.xuandexx.android.chinesetrain.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xuandexx.android.chinesetrain.R;
import com.xuandexx.android.chinesetrain.view.ICallBack;
import com.xuandexx.android.chinesetrain.view.SearchView;
import com.xuandexx.android.chinesetrain.view.bCallBack;

public class MainActivity extends Activity {

    // 1. 初始化搜索框变量
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 2. 绑定视图
        setContentView(R.layout.activity_search);
        // 3. 绑定组件
        searchView = (SearchView) findViewById(R.id.search_view);
        // 4. 设置点击搜索按键后的操作（通过回调接口）
        // 参数 = 搜索框输入的内容
        searchView.setOnClickSearch(new ICallBack() {
            @Override
            public void SearchAciton(String string) {
                System.out.println("我收到了" + string);
            }
        });
        // 5. 设置点击返回按键后的操作（通过回调接口）
        searchView.setOnClickBack(new bCallBack() {
            @Override
            public void BackAciton() {
                finish();
            }
        });


    }
}
