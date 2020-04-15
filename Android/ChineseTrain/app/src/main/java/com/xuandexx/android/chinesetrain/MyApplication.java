package com.xuandexx.android.chinesetrain;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.pm.PackageManager;
import android.util.Log;

import com.xuandexx.android.chinesetrain.data.MySharedpreference;

import java.util.Iterator;
import java.util.List;

/**
 * 很多缓存信息均存储在MyApplication中
 *
 * @author Simon
 */
public class MyApplication extends Application {

    /**
     * 偏好设置
     */
    public static MySharedpreference mySharedpreferences = null;
    public static boolean isOnLine = false;
    //系统应用
    public static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        // 获取PID,主要用于终止程序
        mySharedpreferences = MySharedpreference.getInstance(instance);
        Log.d("EMChat Demo", "initialize EMChat SDK");
    }


    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this
                .getSystemService(ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> l = am.getRunningAppProcesses();
        Iterator<RunningAppProcessInfo> i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            RunningAppProcessInfo info = (RunningAppProcessInfo) (i
                    .next());
            try {
                if (info.pid == pID) {
                    CharSequence c = pm.getApplicationLabel(pm
                            .getApplicationInfo(info.processName,
                                    PackageManager.GET_META_DATA));
                    Log.d("Process", "Id: " + info.pid + " ProcessName: "
                            + info.processName + "  Label: " + c.toString());
                    // processName = c.toString();
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }


    /**
     * 退出登录,清空数据
     */
    public void logout() {
        mySharedpreferences.setPasswordNull();
    }
}