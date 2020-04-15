package activity.fragments.wo;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.activity.fragments.business.AgreeProtocolActivity;
import cn.ieauto.autogroup.android.app.MyActivity;
import cn.ieauto.autogroup.android.util.CommonUtils;
import cn.ieauto.autogroup.android.util.HttpConnect;

/**
 * 关于车友信
 * 
 * @author Administrator
 * 
 */
public class AboutAutoGroup extends MyActivity implements OnClickListener {
	private ImageView img_Back;
	private TextView tv_number, tv_privacypolicy;
	private LinearLayout layout_VersionCheck, layout_Feedback;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_auto_group);
		initViews();
		initEvents();

	}

	@Override
	protected void initViews() {
		img_Back = (ImageView) this.findViewById(R.id.view_top_img_back);
		layout_Feedback = (LinearLayout) findViewById(R.id.aboutautogroup_linear_feedback);
		layout_VersionCheck = (LinearLayout) findViewById(R.id.aboutautogroup_linear_versioncheck);
		tv_number = (TextView) findViewById(R.id.aboutautogroup_tv_number);
		tv_privacypolicy = (TextView) findViewById(R.id.aboutautogroup_tv_privacypolicy);
	}

	@Override
	protected void initEvents() {
		img_Back.setBackgroundResource(R.drawable.button_return);
		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.mysettings_6);
		tv_number.setText(getVersion() + ".0");
		img_Back.setOnClickListener(this);
		tv_privacypolicy.setOnClickListener(this);
		layout_Feedback.setOnClickListener(this);
		layout_VersionCheck.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_top_img_back:
			defaultFinish();
			break;
		/*
		 * 意见反馈
		 */
		case R.id.aboutautogroup_linear_feedback:
			startActivity(MyFeedbackActivity.class);
			break;
		/*
		 * 版本检查
		 */
		case R.id.aboutautogroup_linear_versioncheck:
			toast("点击了");
			doversioncheck();
			break;
		/*
		 * 使用条款和隐私政策
		 */
		case R.id.aboutautogroup_tv_privacypolicy:
			// ReadandagreeActivity
			Bundle bundle = new Bundle();
			bundle.putString("visibility", "1");
			startActivity(AgreeProtocolActivity.class, bundle);
			break;
		default:
			break;
		}
	}

	/*
	 * 检查App本地版本
	 */
	@SuppressLint("NewApi")
	public String getVersion() {
		try {
			PackageManager manager = this.getPackageManager();
			PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
			String version = info.versionName;
			return version;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 联网操作,版本更新
	 * 
	 */
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	private void doversioncheck() {
		if (Build.VERSION.SDK_INT > 9) {
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
					.detectDiskReads().detectDiskWrites().detectNetwork()
					.penaltyLog().build());

			StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
					.detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()
					.build());
		}
		yesOrNoUpdataApk();
		toast("点击了+1");

	}

	/**
	 * 是否更新版本<BR>
	 * 
	 * @author Simon
	 */
	private int yesOrNoUpdataApk() {
		String nowVersion = getVersion();
		String newVersion = goToCheckNewVersion();
		toast("点击了+3");
		doLog(newVersion + ";" + nowVersion);
		if (null == newVersion) {
			doLog("服务器异常");
			return 0;
		}
		if (newVersion.equals("Error") || newVersion.equals("")) {
			return 0;
		}
		if (Double.valueOf(newVersion) > Double.valueOf(nowVersion)) {
			doNewVersionUpdate(nowVersion, newVersion);
			toast("点击了+2");

		}
		return 1;
	}

	/**
	 * 获取当前的应用最新版本<br>
	 * 
	 * @author Simon
	 */
	private String goToCheckNewVersion() {
		String newVersion = "Error";
		// 获取服务器版本号http://www.ieauto.cn/chat_server/getVersionServlet
		if (CommonUtils.isNetworkConnected(this)) {
			final String url = "http://www.ieauto.cn/chat_server/getVersionServlet";
			HttpConnect hc = new HttpConnect(url, this);
			newVersion = hc.getDataAsString(null);
		} else {
			toast("对不起，当前网络不可用!");
		}
		return newVersion;
	}

	/**
	 * 是否进行更新提示框<BR>
	 * 
	 * @author Simon
	 */
	private void doNewVersionUpdate(String nowVersion, String newVersion) {
		StringBuffer sb = new StringBuffer();
		sb.append("当前版本:");
		sb.append(nowVersion);
		sb.append(", 发现新版本:");
		sb.append(newVersion);
		sb.append(", 是否更新?");
		Dialog dialog = new AlertDialog.Builder(AboutAutoGroup.this)
				.setTitle("软件更新")
				.setMessage(sb.toString())
				// 设置内容
				.setPositiveButton("更新",// 设置确定按钮
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								goToDownloadApk();
							}
						})

				.setNegativeButton("暂不更新",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								// 点击"取消"按钮之后退出程序
								finish();
							}
						}).create();// 创建
		// 显示对话框
		dialog.show();
	}

	/**
	 * 打开浏览器下载更新<BR>
	 * 
	 * @author Simon
	 */
	private void goToDownloadApk() {

		Intent intent = new Intent();
		intent.setAction("android.intent.action.VIEW");
		Uri content_url = Uri
				.parse("http://www.ieauto.cn/android/autogroup.apk");
		intent.setData(content_url);
		startActivity(intent);

	}
}
