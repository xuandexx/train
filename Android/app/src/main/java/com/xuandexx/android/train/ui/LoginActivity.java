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

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xuandexx.android.train.R;
import com.xuandexx.android.train.common.CommonUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Login screen
 */

@ContentView(R.layout.em_activity_login)
public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginActivity";
    public static final int REQUEST_CODE_SETNICK = 1;


    private boolean progressShow;
    private boolean autoLogin = false;

    @ViewInject(R.id.username)
    private EditText usernameEditText;

    @ViewInject(R.id.password)
    private EditText passwordEditText;

    @ViewInject(R.id.txt_service_ckeck)
    TextView serviceCheckTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        // if user changed, clear the password
        usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                passwordEditText.setText(null);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || ((event.getKeyCode() == KeyEvent.KEYCODE_ENTER) && (event.getAction() == KeyEvent.ACTION_DOWN))) {
                    login(null);
                    return true;
                } else {
                    return false;
                }
            }
        });

        usernameEditText.setHint("请输入用户名");

        serviceCheckTV.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

    }

    /**
     * login
     *
     * @param view
     */
    public void login(View view) {
        if (!CommonUtils.isNetWorkConnected(this)) {
            Toast.makeText(this, R.string.network_isnot_available, Toast.LENGTH_SHORT).show();
            return;
        }
        String currentUsername = usernameEditText.getText().toString().trim();
        String currentPassword = passwordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(currentUsername)) {
            Toast.makeText(this, R.string.User_name_cannot_be_empty, Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(currentPassword)) {
            Toast.makeText(this, R.string.Password_cannot_be_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        progressShow = true;
        final ProgressDialog pd = new ProgressDialog(LoginActivity.this);
        pd.setCanceledOnTouchOutside(false);
        pd.setOnCancelListener(new OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                Log.d(TAG, "EMClient.getInstance().onCancel");
                progressShow = false;
            }
        });
        pd.setMessage(getString(R.string.Is_landing));
        pd.show();


        final long start = System.currentTimeMillis();
        // call login method
        Log.d(TAG, "EMClient.getInstance().login");
//        EMClient.getInstance().login(currentUsername, currentPassword, new EMCallBack() {
//
//            @Override
//            public void onSuccess() {
//                Log.d(TAG, "login: onSuccess");
//
//
//                // ** manually load all local groups and conversation
//                EMClient.getInstance().groupManager().loadAllGroups();
//                EMClient.getInstance().chatManager().loadAllConversations();
//
//                // update current user's display name for APNs
//                boolean updatenick = EMClient.getInstance().pushManager().updatePushNickname(
//                        BaseApplication.currentUserNick.trim());
//                if (!updatenick) {
//                    Log.e("LoginActivity", "update current user nick fail");
//                }
//
//                if (!LoginActivity.this.isFinishing() && pd.isShowing()) {
//                    pd.dismiss();
//                }
//
//                // get user's info (this should be get from App's server or 3rd party service)
//                DemoHelper.getInstance().getUserProfileManager().asyncGetCurrentUserInfo();
//
//                Intent intent = new Intent(LoginActivity.this,
//                        MainActivity.class);
//                startActivity(intent);
//
//                finish();
//            }
//
//            @Override
//            public void onProgress(int progress, String status) {
//                Log.d(TAG, "login: onProgress");
//            }
//
//            @Override
//            public void onError(final int code, final String message) {
//                Log.d(TAG, "login: onError: " + code);
//                if (!progressShow) {
//                    return;
//                }
//                runOnUiThread(new Runnable() {
//                    public void run() {
//                        pd.dismiss();
//                        Toast.makeText(getApplicationContext(), getString(R.string.Login_failed) + message,
//                                Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
    }

//    /**
//     * register
//     *
//     * @param view
//     */
//    public void register(View view) {
//        startActivityForResult(new Intent(this, RegisterActivity.class), 0);
//    }

    @Override
    protected void onResume() {
        super.onResume();
        if (autoLogin) {
            return;
        }
    }
}
