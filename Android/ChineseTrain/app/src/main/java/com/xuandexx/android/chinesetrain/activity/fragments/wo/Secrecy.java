package activity.fragments.wo;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.activity.fragments.chat.BlacklistActivity;
import cn.ieauto.autogroup.android.app.MyActivity;

/**
 * 隐私 secrecy
 * 
 * @author Lampo
 * 
 */
public class Secrecy extends MyActivity implements OnClickListener {
	private ImageView img_back;
	private TextView tv_title;
	private Button but_blacklist, but_AddFriendPermissions,
			but_Carcirclepermissions, but_ChangePassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.secrecy);
		initViews();
		initEvents();
	}

	@Override
	protected void initViews() {
		img_back = (ImageView) findViewById(R.id.view_top_img_back);
		tv_title = (TextView) findViewById(R.id.view_top_tv_title);
		but_blacklist = (Button) findViewById(R.id.secrecy_but_blacklist);
		but_AddFriendPermissions = (Button) findViewById(R.id.secrecy_but_friendspermissions);
		but_Carcirclepermissions = (Button) findViewById(R.id.secrecy_but_carcirclepermissions);
		but_ChangePassword = (Button) findViewById(R.id.secrecy_but_changepassword);
	}

	@Override
	protected void initEvents() {
		tv_title.setText(R.string.mysettings_3);
		img_back.setBackgroundResource(R.drawable.button_return);
		img_back.setOnClickListener(this);
		but_blacklist.setOnClickListener(this);
		but_AddFriendPermissions.setOnClickListener(this);
		but_Carcirclepermissions.setOnClickListener(this);
		but_ChangePassword.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		/**
		 * 返回
		 */
		case R.id.view_top_img_back:
			defaultFinish();
			break;
		/**
		 * 加好友权限
		 */
		case R.id.secrecy_but_friendspermissions:

			break;
		/**
		 * 通讯录黑名单
		 */
		case R.id.secrecy_but_blacklist:
			startActivity(BlacklistActivity.class);

			break;
		/**
		 * 车有圈权限
		 */
		case R.id.secrecy_but_carcirclepermissions:
			break;
			/**
			 * 修改密码
			 */
		case R.id.secrecy_but_changepassword:
			startActivity(ChangePassword.class);
			break;
		default:
			break;
		}

	}

}
