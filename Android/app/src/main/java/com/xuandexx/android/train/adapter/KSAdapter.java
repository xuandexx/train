package com.xuandexx.android.train.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class KSAdapter<T> extends BaseAdapter {

    public List<T> list;//数据源

    public LayoutInflater inflater;//布局装载器对象

    // 通过构造方法将数据源与数据适配器关联起来
    // context:要使用当前的Adapter的界面对象
    public KSAdapter(Context context, List<T> list) {
        list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    //ListView需要显示的数据数量
    public int getCount() {
        return list.size();
    }

    @Override
    //指定的索引对应的数据项
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    //指定的索引对应的数据项ID
    public long getItemId(int position) {
        return position;
    }
}
