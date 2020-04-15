package activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.app.MyActivity;
import cn.ieauto.autogroup.android.entity.MessageContent;
import cn.ieauto.autogroup.android.util.MyAsyncTask;

/**
 * 忘记密码 第二步
 * 
 * @author lilongbing 重置密码
 */
public class ResetPasswordActivity extends MyActivity implements
		OnClickListener {
	private EditText ed1, ed2;
	private Button mpPerfection;
	private String mPhone = null;
	private ImageView img_Back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reset_password);

		initViews();
		initEvents();
	}

	protected void initViews() {
		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.reset_password_title);
		ed1 = (EditText) findViewById(R.id.reset_password_ed1);
		ed2 = (EditText) findViewById(R.id.reset_password_ed2);
		mpPerfection = (Button) findViewById(R.id.reset_password_perfection);
	}

	protected void initEvents() {
		mpPerfection.setOnClickListener(this);
		img_Back = (ImageView) this.findViewById(R.id.view_top_img_back);
		img_Back.setBackgroundResource(R.drawable.button_return);
		img_Back.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_top_img_back:
			defaultFinish();
			break;
		case R.id.reset_password_perfection:
			String mEd1 = ed1.getText().toString().trim();
			String mEd2 = ed2.getText().toString().trim();
			if ("".equals(mEd1) || "".equals(mEd2)) {
				toast("重置密码不能为空!");
				return;
			} else if (!(mEd1).equals(mEd2)) {
				toast("两次密码不一致,请重新输入!");
				return;
			} else {
				String message = "password:" + mEd2;
				final MessageContent messageContent = new MessageContent(
						message, mPhone, 5032);
				MyAsyncTask asyncTask = new MyAsyncTask(messageContent) {

					protected void onPostExecute(String result) {
						dismissLoadingDialog();
						if ("20".equals(result)) {
							toast("服务器异常!");
						} else if ("false".equals(result) || "".equals(result)) {
							toast("提交失败!");
							return;
						} else if ("ok".equals(result)) {
							toast("密码设置成功,请返回登录!");
							startActivity(new Intent(
									ResetPasswordActivity.this,
									LoginActivity.class));
							finish();
						}
					}

					@Override
					protected void onPreExecute() {
						showLoadingDialog("正在发送请求...");
					}

				};
				asyncTask.execute();
			}
			break;
		}
	}
}
