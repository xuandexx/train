package activity;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.MyApplication;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.app.MyActivity;
import cn.ieauto.autogroup.android.entity.MessageContent;
import cn.ieauto.autogroup.android.httpTask.PersonalRequest;
import cn.ieauto.autogroup.android.util.CommonUtils;
import cn.ieauto.autogroup.android.util.MyAsyncTask;

/**
 * 注册用户
 * 
 * @author Simon
 */
public class RegisterFirstActivity extends MyActivity {

	/** 事件控件 */
	private Button resgisterButton;
	private Button getVerify_code;
	/** 非事件控件 */
	private EditText userPhoneEditText;
	private EditText passwordEditText;
	private EditText confirmPwdEditText;
	private EditText verify_code; // 验证码文本框
	/** 数据变/常量 */
	private String password, confirm_pwd, userPhone;

	private String mVerifcode;
	private ImageView img_Back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		initViews();
		initEvents();
	}

	@Override
	protected void initViews() {
		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.login_register);
		userPhoneEditText = (EditText) findViewById(R.id.resgister_ed_userphone);
		passwordEditText = (EditText) findViewById(R.id.resgister_ed_setpassword);
		confirmPwdEditText = (EditText) findViewById(R.id.resgister_ed_pwdconfirm);
		verify_code = (EditText) findViewById(R.id.resgister_ed_verificationcode);
		getVerify_code = (Button) findViewById(R.id.resgister_tv_confirmpwd);
		resgisterButton = (Button) findViewById(R.id.resgister_button);
		img_Back = (ImageView) findViewById(R.id.view_top_img_back);
	}

	@Override
	protected void initEvents() {
		img_Back.setBackgroundResource(R.drawable.button_return);
		getVerify_code.setOnClickListener(ocl);
		img_Back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				defaultFinish();
			}
		});
		resgisterButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				userPhone = userPhoneEditText.getText().toString().trim();
				password = passwordEditText.getText().toString().trim();
				confirm_pwd = confirmPwdEditText.getText().toString().trim();
				mVerifcode = verify_code.getText().toString().trim();
				if (TextUtils.isEmpty(userPhone)) {
					toast("手机号码不能为空!");
					userPhoneEditText.requestFocus();
					return;
				}
				if (!CommonUtils.validatePhone(userPhone)) {
					toast("请输入正确的手机号码");
					userPhoneEditText.requestFocus();
					return;
				}
				if (TextUtils.isEmpty(password)) {
					toast("密码不能为空！");
					passwordEditText.requestFocus();
					return;
				}
				if (password.length() < 6 || password.length() >= 16) {
					toast("密码长度为6~16,请重新输入");
					return;
				}
				if (TextUtils.isEmpty(confirm_pwd)) {
					toast("确认密码不能为空！");
					confirmPwdEditText.requestFocus();
					return;
				}
				if (!password.equals(confirm_pwd)) {
					toast("两次输入的密码不一致，请重新输入！");
					return;
				} else if (mVerifcode.equals("")) {
					toast("请输入验证码");
					verify_code.requestFocus();
					return;
				}
				if (!CommonUtils.isNetworkConnected(RegisterFirstActivity.this)) {
					toast("对不起，当前网络不可用!");
				} else {
					new Thread(new Runnable() {

						@Override
						public void run() {
							String result = null;
							try {
								result = PersonalRequest.register_first(
										userPhone, password, mVerifcode);
							} catch (ClientProtocolException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							} catch (JSONException e) {
								e.printStackTrace();
							}
							MyApplication.getInstance().setUserSystemID(result);
							startActivity(RegisterSecondActivity.class);
							defaultFinish();
						}
					}).start();
				}
			}
		});
	}

	/** 注册获取验证码 */
	private OnClickListener ocl = new OnClickListener() {
		@Override
		public void onClick(View v) {
			userPhone = userPhoneEditText.getText().toString().trim();
			if (!CommonUtils.validatePhone(userPhone)) {
				toast("请输入正确的手机号码");
				return;
			} else {
				doVerifCode();
			}
		}
	};

	/** 获取验证码 */
	private void doVerifCode() {
		if (CommonUtils.isNetworkConnected(RegisterFirstActivity.this)) {
			toast("对不起，当前网络不可用!");
		}
		// 消息封装,获取验证码VerifCodeMessage
		MessageContent VerifCodeMessage = new MessageContent("", userPhone,
				5021);
		MyAsyncTask asyncTask = new MyAsyncTask(VerifCodeMessage) {
			protected void onPreExecute() {
				showLoadingDialog("验证码正在发送....");
			}

			protected void onPostExecute(String result) {
				dismissLoadingDialog();
				if ("20".equals(result)) {
					toast("服务器异常...");
				}
				if ("ok".equals(result)) {
					toast("验证码已发送!");
				}
				if ("false".equals(result)) {
					toast("用户已存在!");
				}
			}
		};
		asyncTask.execute();
	}

	public void back(View view) {
		defaultFinish();
	}

}