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
package com.xuandexx.android.train.base;

import android.app.Application;
import android.content.Context;

import org.xutils.x;

/**
 * 应用程序入口
 */
public class BaseApplication extends Application {

    public static Context applicationContext;

    private static BaseApplication instance;

    private static String userToken = null;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        instance = this;
        x.Ext.init(this);
        x.Ext.setDebug(true); //输出debug日志，开启会影响性能
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public static String getUserToken() {
        if (userToken == null) {
            userToken = "";
        }
        return userToken;
    }
}
