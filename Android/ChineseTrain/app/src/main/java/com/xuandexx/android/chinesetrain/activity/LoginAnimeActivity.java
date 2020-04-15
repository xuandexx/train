package activity;

import android.os.Bundle;
import android.view.Window;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.app.MyActivity;

/**
 * 欢迎动画,休眠一秒
 * 
 * @author lilongbing
 * 
 */
public class LoginAnimeActivity extends MyActivity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login_animation);
		new Thread(new Runnable() {
			public void run() {
				// 登录动画休眠一秒
				try {
					Thread.sleep(1000);
					startActivity(MainActivity.class);
					defaultFinish();
				} catch (InterruptedException e) {
				}
			}
		}).start();
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initEvents() {
		// TODO Auto-generated method stub

	}
}
