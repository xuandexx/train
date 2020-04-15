package activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.app.MyActivity;
import cn.ieauto.autogroup.android.entity.MessageContent;
import cn.ieauto.autogroup.android.util.CommonUtils;
import cn.ieauto.autogroup.android.util.MyAsyncTask;

/**
 * 忘记密码 第一步
 * 
 * @author lilongbing 手机短信验证码
 */
@SuppressLint("HandlerLeak")
public class FrogetPasswordActivity extends MyActivity implements
		OnClickListener {
	private static String phoneString = null;
	/**
	 * recode:短信验证码,phone:手机号码
	 */
	private EditText recode = null, phone = null;
	private Button mNext = null;
	private TextView mResend = null;
	private ImageView img_Back;

	// private final static int GET_OK = 1;
	// private Handler mHandler =null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_froget_password);
		initViews();
		initEvents();

	}

	@Override
	protected void initViews() {
		img_Back = (ImageView) this.findViewById(R.id.view_top_img_back);
		phone = (EditText) findViewById(R.id.froget_password_phone);
		recode = (EditText) findViewById(R.id.froget_recode);
		mResend = (TextView) findViewById(R.id.froget_resend);
		mNext = (Button) findViewById(R.id.sms_verification_next);
	}

	@Override
	protected void initEvents() {
		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.sms_verification_title);
		img_Back.setBackgroundResource(R.drawable.button_return);
		mResend.setOnClickListener(this);
		mNext.setOnClickListener(this);
		img_Back.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		// 点击获取验证码
		case R.id.froget_resend:

			phoneString = phone.getText().toString().trim();
			if (!CommonUtils.validatePhone(phoneString)) {
				Log.i("android", phoneString);
				toast("输入的手机号码不合法");
				phone.requestFocus();
				break;
			} else {
				final MessageContent messageContent = new MessageContent("",
						phoneString, 5030);
				MyAsyncTask asyncTask = new MyAsyncTask(messageContent) {

					protected void onPostExecute(String result) {
						dismissLoadingDialog();
						if ("20".equals(result)) {
							toast("服务器异常");
							return;
						} else if ("ok".equals(result)) {
							toast("验证码已经发送成功");
						}
					}

					@Override
					protected void onPreExecute() {
						showLoadingDialog("正在发送请求...");
					}

				};
				asyncTask.execute();

			}
			// mHandler.sendEmptyMessage(GET_OK);
			//
			break;
		case R.id.view_top_img_back:
			defaultFinish();
			break;
		// 点击下一步,提交验证码,并且判断验证码是否正确
		case R.id.sms_verification_next:
			// String mVerifcode = recode.getText().toString().trim();
			// phoneString = phone.getText().toString().trim();
			// if (!"".equals(recode.getText().toString())) {
			// String message = "verifcode:" + mVerifcode;
			// final MessageContent messageContent = new MessageContent(
			// message, "", phoneString, 5031);
			// MyAsyncTask asyncTask = new MyAsyncTask(messageContent) {
			//
			// protected void onPostExecute(String result) {
			// // dismissLoadingDialog();
			// if ("20".equals(result)) {
			//
			// } else if ("false".equals(result)) {
			// toast("验证码不正确,请重新输入");
			// return;
			// } else if ("ok".equals(result)) {
			Intent intent = new Intent(FrogetPasswordActivity.this,
					ResetPasswordActivity.class);
			// Bundle bundle=new Bundle();
			// bundle.putString("phone", phoneString);
			// intent.putExtras(bundle);
			startActivity(intent);
			// defaultFinish();
			// }
			// }
			//
			// @Override
			// protected void onPreExecute() {
			// showLoadingDialog("正在发送请求...");
			// }
			// };
			// asyncTask.execute();
			// return;
			// } else {
			// toast("验证码不为空!");
			// }
			break;
		}
	}

}
