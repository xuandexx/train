package com.xuandexx.android.train.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * contact list
 */
public abstract class BaseFragment extends Fragment {

    protected static String TAG;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initEvent();
    }

    protected abstract void initView();

    protected abstract void initEvent();


    /**
     * 功能描述:简单地 Activity 的跳转(不携带任何数据)
     *
     * @param cls 目标 Activity 实例
     */
    public void startActivity(Class<? extends Activity> cls) {
        Intent intent = new Intent(this.getActivity(), cls);
        startActivity(intent);
    }

    /**
     * 功能描述：带数据的 Activity 之间的跳转
     *
     * @param cls     目标 Activity 实例
     * @param hashMap 传递的数据
     */
    public void startActivity(Class<? extends Activity> cls, HashMap<String, ? extends Object> hashMap) {
        Intent intent = new Intent(this.getActivity(), cls);
        Iterator<?> iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            @SuppressWarnings("unchecked")
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iterator
                    .next();
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String) {
                intent.putExtra(key, (String) value);
            }
            if (value instanceof Boolean) {
                intent.putExtra(key, (boolean) value);
            }
            if (value instanceof Integer) {
                intent.putExtra(key, (int) value);
            }
            if (value instanceof Float) {
                intent.putExtra(key, (float) value);
            }
            if (value instanceof Double) {
                intent.putExtra(key, (double) value);
            }
        }
        startActivity(intent);
    }

    /**
     * 功能描述：带数据的 Activity 之间的跳转
     *
     * @param key    实体类Key
     * @param cls    目标 Activity 实例
     * @param object 实体类
     */
    public void startActivity(Class<? extends Activity> cls, String key, Parcelable object) {
        Intent intent = new Intent(this.getActivity(), cls);
        Bundle bundle = new Bundle();
        bundle.putParcelable(key, object);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    public void toast(String msg) {
        Toast.makeText(this.getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    public void toast(@StringRes int resId) {
        Toast.makeText(this.getActivity(), resId, Toast.LENGTH_SHORT).show();
    }

    public void loge(String msg) {
        Log.e(TAG, msg);
    }

    public void logi(String msg) {
        Log.i(TAG, msg);
    }

}
