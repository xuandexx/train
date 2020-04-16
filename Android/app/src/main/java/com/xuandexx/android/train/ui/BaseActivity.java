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

package com.xuandexx.android.train.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
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
     * @param activity 发起跳转的 Activity 实例
     * @param cls      目标 Activity 实例
     * @param hashMap  传递的数据
     */
    public void startActivity(Activity activity, Class<? extends Activity> cls, HashMap<String, ? extends Object> hashMap) {
        Intent intent = new Intent(activity, cls);
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
        activity.startActivity(intent);
    }

}
