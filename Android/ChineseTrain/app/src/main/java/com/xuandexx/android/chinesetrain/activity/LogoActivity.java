package activity;

import android.os.Bundle;
import android.os.Handler;
import cn.ieauto.autogroup.android.MyApplication;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.app.ComConfig;
import cn.ieauto.autogroup.android.app.MyActivity;
import cn.ieauto.autogroup.android.util.SharedPreferencesHelper;

/**
 * 程序入口画面,需要换成公司的logo或者改成动画png格式的动画
 * 
 * @author Simon,lilongbin
 * 
 */
public class LogoActivity extends MyActivity {

	private SharedPreferencesHelper sph;
	/** 主要功能，跳转到不同的界面 */
	private Handler mHandler;

	private boolean autoLogin = false;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logo);
		initViews();
		initEvents();
	}

	@Override
	protected void initViews() {
		sph = new SharedPreferencesHelper(LogoActivity.this);
		mHandler = new Handler() {
			/** 0：跳转到用户首页；1：跳转到引导页。 */
			public void handleMessage(android.os.Message msg) {
				super.handleMessage(msg);
				switch (msg.what) {
				case 0:// 非第一次,有用户名没有密码
					startActivity(LoginActivity.class);
					LogoActivity.this.defaultFinish();
					break;
				case 1:// 第一次使用
					startActivity(WelcomeActivity.class);
					sph.putInt(ComConfig.IS_FIRST_RUN, 1);
					LogoActivity.this.defaultFinish();
					break;
				}
			};
		};
	}

	@Override
	protected void initEvents() {
		int isFirst = sph.getInt(ComConfig.IS_FIRST_RUN);
		String phone = MyApplication.getInstance().getUserPhone();
		final String password = MyApplication.getInstance().getPassword();
		if (isFirst != ComConfig.IS_FIRST) {
			mHandler.sendEmptyMessageDelayed(0, 3000);
		} else

		// if (!TextUtils.isEmpty(phone) && TextUtils.isEmpty(password))
		{
			mHandler.sendEmptyMessageDelayed(1, 3000);
		}

		// else if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(password)) {
		// autoLogin = true;
		// final String huanxinUser = "cyx"
		// + MyApplication.getInstance().getUserPhone();
		// new CommonUser("", "", huanxinUser);
		// MyApplication.getInstance().saveBaseInfo();
		// // 调用登录方法
		// new Thread(new Runnable() {
		// @Override
		// public void run() {
		// EMChatManager.getInstance().login(huanxinUser, password,
		// new EMCallBack() {
		// @Override
		// public void onSuccess() {
		// startActivity(MainActivity.class);
		// LogoActivity.this.defaultFinish();
		// }
		//
		// @Override
		// public void onProgress(int progress,
		// String status) {
		// }
		//
		// @Override
		// public void onError(int code,
		// final String message) {
		// }
		// });
		// }
		// }).start();
		// }
	}
}
