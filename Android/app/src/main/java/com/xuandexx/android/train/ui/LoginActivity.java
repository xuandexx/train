package com.xuandexx.android.train.ui;

import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.xuandexx.android.train.R;
import com.xuandexx.android.train.base.BaseActivity;

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
        initEvent();


    }

    public void login(View view) {
        startActivity(MainActivity.class);
        finish();
//        if (!CommonUtils.isNetWorkConnected(this)) {
//            Toast.makeText(this, R.string.network_isnot_available, Toast.LENGTH_SHORT).show();
//            return;
//        }
//        String currentUsername = usernameEditText.getText().toString().trim();
//        String currentPassword = passwordEditText.getText().toString().trim();
//        if (TextUtils.isEmpty(currentUsername)) {
//            Toast.makeText(this, R.string.User_name_cannot_be_empty, Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(currentPassword)) {
//            Toast.makeText(this, R.string.Password_cannot_be_empty, Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        progressShow = true;
//        final ProgressDialog pd = new ProgressDialog(LoginActivity.this);
//        pd.setCanceledOnTouchOutside(false);
//        pd.setOnCancelListener(new OnCancelListener() {
//
//            @Override
//            public void onCancel(DialogInterface dialog) {
//                Log.d(TAG, "EMClient.getInstance().onCancel");
//                progressShow = false;
//            }
//        });
//        pd.setMessage(getString(R.string.Is_landing));
//        pd.show();
//        final long start = System.currentTimeMillis();
//        // call login method
//        Log.d(TAG, "EMClient.getInstance().login");
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

    /**
     * register
     *
     * @param view
     */
    public void register(View view) {
        startActivity(BroadcastActivity.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (autoLogin) {
            return;
        }
    }


    @Override
    protected void initEvent() {

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
}
