package com.xuandexx.android.chinesetrain.base;

import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

public class MyListFragment extends ListFragment {

	public Map<String, String> map;
	public String tag = this.getClass().getSimpleName(); // tag 用于测试log用
	public Context context; // 存储上下文对象
	public Activity activity; // 存储上下文对象

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getActivity();
		activity = getActivity();
	}

	/** 通过Class跳转界面 **/
	protected void startActivity(Class<?> cls) {
		Intent intent = new Intent();
		intent.setClass(context, cls);
		startActivity(intent);
	}
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
	}
}
