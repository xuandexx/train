package com.xuandexx.android.chinesetrain.base;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.xuandexx.android.chinesetrain.MyApplication;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public abstract class MyActivity extends FragmentActivity {

    protected MyApplication mApplication;
    /**
     * 进度框
     */
    protected static dialog.FlippingLoadingDialog mLoadingDialog;

    protected Context context;

    protected String tagName;

    /**
     * 获取用户偏好设置
     */
    protected List<AsyncTask<Void, Void, Boolean>> mAsyncTasks = new ArrayList<AsyncTask<Void, Void, Boolean>>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoadingDialog = new dialog.FlippingLoadingDialog(this, "请求提交中");
    }

    protected void onDestroy() {
        clearAsyncTask();
        super.onDestroy();
    }

    protected void clearAsyncTask() {
        Iterator<AsyncTask<Void, Void, Boolean>> iterator = mAsyncTasks
                .iterator();
        while (iterator.hasNext()) {
            AsyncTask<Void, Void, Boolean> asyncTask = iterator.next();
            if (asyncTask != null && !asyncTask.isCancelled()) {
                asyncTask.cancel(true);
            }
        }
        mAsyncTasks.clear();
    }

    /**
     * 初始化视图
     **/
    protected abstract void initViews();

    /**
     * 初始化事件
     **/
    protected abstract void initEvents();


    /**
     * 短暂显示Toast提示(来自res)
     **/
    protected void toast(int resId) {
        Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show();
    }

    /**
     * 短暂显示Toast提示(来自String)
     **/
    protected void toast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }


    /**
     * Debug输出Log日志
     **/
    protected void logBug(String msg) {
        Log.w(tagName, msg);
    }

    /**
     * Error输出Log日志
     **/
    protected void logError(String tag, String msg) {
        Log.e(tag, msg);
    }

    /**
     * 通过Class跳转界面
     **/
    protected void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 含有Bundle通过Class跳转界面 ,Bundle参数无必要可为null
     **/
    protected void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 通过Action跳转界面
     **/
    protected void startActivity(String action) {
        startActivity(action, null);
    }

    /**
     * 含有Bundle通过Action跳转界面 ,Bundle参数无必要可为null
     * 1.setAction()：表明我们想要启动能够响应设置的这个action的活动，并在清单文件AndroidManifest.xml中设置action属性。如:(打开一个拨号界面)
     * 2.setData()：通常是URI格式定义的操作数据。（只要设置setAction ()就要在清单文件AndroidManifest.xml中设置action属性）例如：tel:
     * 3.setType()：指定数据类型，选出适合的应用来。（只要设置setAction ()就要在清单文件AndroidManifest.xml中设置action属性）例如：
     * 4.putExtra()：把要传递的数据暂存在Intent中，启动了另一个活动后，只需要把这些数据再从Intent中取出就可以了。
     **/
    protected void startActivity(String action, Bundle bundle) {
        Intent intent = new Intent();
        intent.setAction(action);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 进度条
     *
     * @param text
     */
    protected static void showLoadingDialog(String text) {
        if (text != null) {
            mLoadingDialog.setText(text);
        }
        mLoadingDialog.show();
    }

    /**
     * 含有标题和内容的对话框
     **/
    protected AlertDialog showAlertDialog(String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle(title)
                .setMessage(message).setCancelable(true).show();
        return alertDialog;
    }

    /** 含有标题、内容、两个按钮的对话框 **/
    // protected AlertDialog showAlertDialog(String title, String message,
    // String positiveText,
    // DialogInterface.OnClickListener onPositiveClickListener,
    // String negativeText,
    // DialogInterface.OnClickListener onNegativeClickListener) {
    // AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle(title)
    // .setMessage(message).setCancelable(true)
    // .setPositiveButton(positiveText, onPositiveClickListener)
    // .setNegativeButton(negativeText, onNegativeClickListener)
    // .show();
    // return alertDialog;
    // }

    /**
     * 含有标题、内容、图标、两个按钮的对话框
     **/
    protected AlertDialog showAlertDialog(String title, String message,
                                          int icon, String positiveText,
                                          DialogInterface.OnClickListener onPositiveClickListener,
                                          String negativeText,
                                          DialogInterface.OnClickListener onNegativeClickListener) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle(title)
                .setMessage(message).setCancelable(true).setIcon(icon)
                .setPositiveButton(positiveText, onPositiveClickListener)
                .setNegativeButton(negativeText, onNegativeClickListener)
                .show();
        return alertDialog;
    }

    /**
     * 含有标题,输入框,两个按钮的对话框
     **/
    protected AlertDialog showAlertDialog(String title, View view,
                                          String positiveText,
                                          DialogInterface.OnClickListener onPositiveClickListener,
                                          String negativeText,
                                          DialogInterface.OnClickListener onNegativeClickListener) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle(title)
                .setView(view)
                .setPositiveButton(positiveText, onPositiveClickListener)
                .setNegativeButton(negativeText, onNegativeClickListener)
                .setCancelable(true).show();
        return alertDialog;
    }

    /**
     * 默认退出
     **/
    public void defaultFinish() {
        super.finish();
    }

    protected static void dismissLoadingDialog() {
        if (mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }
}
