package activity.fragments.wo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.ieauto.autogroup.android.MyApplication;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.activity.LoginActivity;
import cn.ieauto.autogroup.android.activity.MainActivity;
import cn.ieauto.autogroup.android.app.MyActivity;
import cn.ieauto.autogroup.android.dialog.CustomDialog;

/**
 * 设置
 * 
 * @author Lampo
 * 
 */
public class MySettingActivity extends MyActivity implements OnClickListener {
	/** 返回 */
	private ImageView img_Back;
	/**
	 * 账号,新信息提醒,隐私,关于车友信,退出
	 */
	private LinearLayout rlayout_newmessagenotice;
	private RelativeLayout rlayout_myaccount, rlayout_secrecy,
			rlayout_respecting, rlayout_abort;
	private CustomDialog.Builder builder;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mysettings);
		initViews();
		initEvents();
	}

	@Override
	protected void initViews() {
		img_Back = (ImageView) this.findViewById(R.id.view_top_img_back);
		rlayout_myaccount = (RelativeLayout) findViewById(R.id.mysettings_myaccount);
		rlayout_newmessagenotice = (LinearLayout) findViewById(R.id.mysettings_newmessagenotice);
		rlayout_secrecy = (RelativeLayout) findViewById(R.id.mysettings_secrecy);
		rlayout_respecting = (RelativeLayout) findViewById(R.id.mysettings_respecting);
		rlayout_abort = (RelativeLayout) findViewById(R.id.mysettings_abort);
	}

	@Override
	protected void initEvents() {
		img_Back.setBackgroundResource(R.drawable.button_return);
		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.setting);

		img_Back.setOnClickListener(this);
		rlayout_myaccount.setOnClickListener(this);
		rlayout_newmessagenotice.setOnClickListener(this);
		rlayout_secrecy.setOnClickListener(this);
		rlayout_respecting.setOnClickListener(this);
		rlayout_abort.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_top_img_back:
			defaultFinish();
			break;

		/*
		 * case R.id.mysettings_myaccount:
		 * 
		 * break;
		 */
		/*
		 * 新消息提醒
		 */
		case R.id.mysettings_newmessagenotice:
			startActivity(NewsReminded.class);
			break;
		/**
		 * 隐私
		 */
		case R.id.mysettings_secrecy:
			startActivity(Secrecy.class);
			break;
		/*
		 * 关于车友信
		 */
		case R.id.mysettings_respecting:
			startActivity(AboutAutoGroup.class);
			break;
		/*
		 * 退出程序
		 */
		case R.id.mysettings_abort:
			builder = new CustomDialog.Builder(MySettingActivity.this);
			builder.setTitle("车友信")
					.setMessage("确定退出登录?")
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									MyApplication.getInstance().logout();
									MainActivity.getIntances().defaultFinish();
									startActivity(LoginActivity.class);
								}
							}).setNegativeButton("取消", null).create().show();
			break;
		}
	}
}
