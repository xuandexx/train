package com.xuandexx.android.chinesetrain.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * 共享数据,用于存储用户的基本信息,cid,username,phone等
 *
 * @author Simon
 */
public class MySharedpreference {

    private static SharedPreferences sharedPreferences;
    private static MySharedpreference mySharedpreference;
    private static SharedPreferences.Editor editor;
    /**
     * 存储用户基本信息
     */
    public static final String PREFERENCE_NAME = "userinfo";
    /**
     * 存储用户设置
     */
    private String SHARED_KEY_SETTING_NOTIFICATION = "shared_key_setting_notification";
    private String SHARED_KEY_SETTING_SOUND = "shared_key_setting_sound";
    private String SHARED_KEY_SETTING_VIBRATE = "shared_key_setting_vibrate";
    private String SHARED_KEY_SETTING_SPEAKER = "shared_key_setting_speaker";

    /**
     * 存储提交是否成功
     */
    boolean flag = false;

    private MySharedpreference(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);
    }

    /**
     * 单例模式，获取instance实例
     *
     * @param context
     * @return
     */
    @SuppressLint("CommitPrefEdits")
    public static MySharedpreference getInstance(Context context) {
        if (null == mySharedpreference) {
            mySharedpreference = new MySharedpreference(context);
        }
        editor = sharedPreferences.edit();
        return mySharedpreference;
    }

    /**
     * 存储用户基本信息
     */
    public boolean saveBaseInfo(String phone, String pswd, String hxUsername) {
        boolean flag = false;
        if (!TextUtils.isEmpty(phone)) {
            editor.putString("phone", phone);
        }
        if (!TextUtils.isEmpty(pswd)) {
            editor.putString("pswd", pswd);
        }
        if (!TextUtils.isEmpty(hxUsername)) {
            editor.putString("huanxin_username", hxUsername);
        }
        flag = editor.commit();
        return flag;
    }

    /**
     * 储存我的座驾信息
     */
    public boolean saveMessage(String brand, String cars, String models,
                               String chassis, String engineno, String cartime) {
        boolean flag = false;
        if ("" != brand) {
            editor.putString("brand", brand);
        }
        if ("" != cars) {
            editor.putString("cars", cars);
        }
        if ("" != models) {
            editor.putString("models", models);
        }
        if ("" != chassis) {
            editor.putString("chassis", chassis);
        }
        if ("" != engineno) {
            editor.putString("engineno", engineno);
        }
        if ("" != cartime) {
            editor.putString("cartime", cartime);
        }
        flag = editor.commit();
        return flag;
    }

    /**
     * 储存我的座驾信息中的车牌号信息
     */
    public boolean saveLicenseNo(String mLicenseNo, String mProvince,
                                 String mCityNo, String mCarLicenseNo) {
        boolean flag = false;
        if ("" != mLicenseNo) {
            editor.putString("licenseno", mLicenseNo);
        }
        if ("" != mProvince) {
            editor.putString("province", mProvince);
        }
        if ("" != mCityNo) {
            editor.putString("cityno", mCityNo);
        }
        if ("" != mCarLicenseNo) {
            editor.putString("carlicenseno", mCarLicenseNo);
        }
        flag = editor.commit();
        return flag;
    }

    public boolean saveDetailInfo(String sex, String nickName, String birthday,
                                  String address, String signature) {
        boolean flag = false;
        if ("" != nickName) {
            editor.putString("neckname", nickName);
        }
        if ("" != sex) {
            editor.putString("sex", sex);
        }
        if ("" != birthday) {
            editor.putString("birthday", birthday);
        }
        if ("" != address) {
            editor.putString("address", address);
        }
        if ("" != signature) {
            editor.putString("signature", signature);
        }
        flag = editor.commit();
        return flag;
    }

    // 存储密码
    public boolean savePassword(String password) {
        boolean flag = false;
        editor.putString("pswd", password);
        return flag;
    }

    // 存储出生日期
    public boolean saveData(String data) {
        boolean flag = false;
        editor.putString("data", data);
        return flag;
    }

    // 获得出生日期
    public String getData() {
        return sharedPreferences.getString("data", "");
    }

    // 获得环信用户名
    public String getHuanxinUser() {
        return sharedPreferences.getString("hx_user", null);
    }

    /*
     * 获得neck name
     */
    public String getNickName() {
        return sharedPreferences.getString("neckname", "");
    }

    /*
     * 获取用户密码
     */
    public String getPassword() {
        return sharedPreferences.getString("pswd", "");
    }

    /*
     * 获取用户的手机号
     */
    public String getUserPhone() {
        return sharedPreferences.getString("phone", "");
    }

    /*
     * 品牌
     */

    public String getBrand() {
        return sharedPreferences.getString("brand", "");
    }

    /*
     * 车系
     */

    public String getCars() {
        return sharedPreferences.getString("cars", "");
    }

    public String officialGroup() {
        return getBrand() + getCars() + "群";
    }

    /*
     * 车型
     */

    public String getModels() {
        return sharedPreferences.getString("models", "");
    }

    /*
     * 车牌号
     */

    public String getLicenseNo() {
        return sharedPreferences.getString("licenseno", "");
    }

    /**
     * 简称
     */
    public String getProvince() {
        return sharedPreferences.getString("province", "");
    }

    /**
     * 地区
     */
    public String getCityNo() {
        return sharedPreferences.getString("cityno", "");
    }

    /**
     * 数字
     */
    public String getCarLicenseNo() {
        return sharedPreferences.getString("carlicenseno", "");
    }

    /*
     * 车架号
     */
    public String getChassis() {
        return sharedPreferences.getString("chassis", "");
    }

    /*
     * 发动机号
     */
    public String getEngineNo() {
        return sharedPreferences.getString("engineNo", "");
    }

    /*
     * 上牌时间
     */
    public String getCartime() {
        return sharedPreferences.getString("cartime", "");
    }

    /*
     * 性别
     */
    public String getSex() {
        return sharedPreferences.getString("sex", "");
    }

    /*
     * 年龄
     */
    public String getAge() {
        return sharedPreferences.getString("age", "");
    }

    /*
     * 地区
     */
    public String getAddress() {
        return sharedPreferences.getString("address", "");
    }

    /*
     * 个人签名
     */
    public String getSignature() {
        return sharedPreferences.getString("signature", "");

    }

    /*
     * 存储车友信系统分配唯一ID
     */
    public void setUserSystemID(String userID) {
        editor.putString("userId", userID);
        editor.commit();
    }

    /*
     * 获取车友信系统分配唯一ID
     */
    public String getUserSystemID() {
        return sharedPreferences.getString("userId", null);
    }

    /**
     * **********************************************************************
     *
     * @param paramBoolean
     */

    public void setSettingMsgNotification(boolean paramBoolean) {
        editor.putBoolean(SHARED_KEY_SETTING_NOTIFICATION, paramBoolean);
        editor.commit();
    }

    public boolean getSettingMsgNotification() {
        return sharedPreferences.getBoolean(SHARED_KEY_SETTING_NOTIFICATION,
                true);
    }

    public void setSettingMsgSound(boolean paramBoolean) {
        editor.putBoolean(SHARED_KEY_SETTING_SOUND, paramBoolean);
        editor.commit();
    }

    public boolean getSettingMsgSound() {

        return sharedPreferences.getBoolean(SHARED_KEY_SETTING_SOUND, true);
    }

    public void setSettingMsgVibrate(boolean paramBoolean) {
        editor.putBoolean(SHARED_KEY_SETTING_VIBRATE, paramBoolean);
        editor.commit();
    }

    public boolean getSettingMsgVibrate() {
        return sharedPreferences.getBoolean(SHARED_KEY_SETTING_VIBRATE, true);
    }

    public void setSettingMsgSpeaker(boolean paramBoolean) {
        editor.putBoolean(SHARED_KEY_SETTING_SPEAKER, paramBoolean);
        editor.commit();
    }

    public boolean getSettingMsgSpeaker() {
        return sharedPreferences.getBoolean(SHARED_KEY_SETTING_SPEAKER, true);
    }

    public void setPasswordNull() {
        editor.putString("pswd", "");
        flag = editor.commit();

    }

    public String getBirthday() {
        return sharedPreferences.getString("birthday", "");
    }

}
