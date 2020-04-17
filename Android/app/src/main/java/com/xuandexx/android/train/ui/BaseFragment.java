package com.xuandexx.android.train.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import org.xutils.x;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * contact list
 */
public abstract class BaseFragment<showDialog> extends Fragment {
    //Log日志标记
    protected String TAG;

    protected InputMethodManager inputMethodManager;


    protected ProgressDialog pd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onViewCreated(View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
    }


    @Override
    public void onStart() {
        super.onStart();
        inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
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
    protected void startActivity(Class<? extends Activity> cls) {
        Intent intent = new Intent(this.getActivity(), cls);
        startActivity(intent);
    }

    /**
     * 功能描述：带数据的 Activity 之间的跳转
     *
     * @param cls     目标 Activity 实例
     * @param hashMap 传递的数据
     */
    protected void startActivity(Class<? extends Activity> cls, HashMap<String, ? extends Object> hashMap) {
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
    protected void startActivity(Class<? extends Activity> cls, String key, Parcelable object) {
        Intent intent = new Intent(this.getActivity(), cls);
        Bundle bundle = new Bundle();
        bundle.putParcelable(key, object);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    protected void toast(String msg) {
        Toast.makeText(this.getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    protected void toast(@StringRes int resId) {
        Toast.makeText(this.getActivity(), resId, Toast.LENGTH_SHORT).show();
    }

    protected void loge(String msg) {
        Log.e(TAG, msg);
    }

    protected void logi(String msg) {
        Log.i(TAG, msg);
    }

    protected <T extends View> T findViewById(int id) {
        return this.getActivity().findViewById(id);
    }

    protected void hideSoftKeyboard() {
        if (getActivity().getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getActivity().getCurrentFocus() != null)
                inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    protected void showDialog(String msg) {
        pd = new ProgressDialog(this.getActivity());
        pd.setMessage(msg);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
    }


    protected void dismissDialog() {
        pd.dismiss();
    }
}
