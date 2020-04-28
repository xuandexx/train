package com.xuandexx.android.train.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.xuandexx.android.train.R;

import org.xutils.x;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected String TAG;

    protected View rootLayout;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        //设置状态栏颜色为透明
        setStatusBarColor(this, 0x00000000);
        x.view().inject(this);
//        //这里注意下 因为在评论区发现有网友调用setRootViewFitsSystemWindows 里面 winContent.getChildCount()=0 导致代码无法继续
//        //是因为你需要在setContentView之后才可以调用 setRootViewFitsSystemWindows
//        rootLayout = findViewById(R.id.rootLayout);
//        //内容根布局设置为true 让内容不被状态栏遮挡
//        rootLayout.setFitsSystemWindows(true);
        initEvent();
    }

    /**
     * 设置状态栏 背景颜色
     */
    public void setStatusBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            if (option != decorView.getSystemUiVisibility()) {
                decorView.setSystemUiVisibility(option);
            }
            if (color != activity.getWindow().getStatusBarColor()) {
                activity.getWindow().setStatusBarColor(color);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * 功能描述:简单地 Activity 的跳转(不携带任何数据)
     *
     * @param cls 目标 Activity 实例
     */
    public void startActivity(Class<? extends Activity> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    /**
     * 功能描述：带数据的 Activity 之间的跳转
     *
     * @param cls     目标 Activity 实例
     * @param hashMap 传递的数据
     */
    protected void startActivity(Class<? extends Activity> cls, HashMap<String, ? extends Object> hashMap) {
        Intent intent = new Intent(this, cls);
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
        Intent intent = new Intent(this, cls);
        Bundle bundle = new Bundle();
        bundle.putParcelable(key, object);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    protected void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected void toast(@StringRes int resId) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }


    public void loge(String msg) {
        Log.e(TAG, msg);
    }

    public void logd(String msg) {
        Log.d(TAG, msg);
    }

    public void logi(String msg) {
        Log.i(TAG, msg);
    }

    /**
     * 初始化事件
     */
    protected abstract void initEvent();

}
