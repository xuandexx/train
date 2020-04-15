package activity.fragments.wo;

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
import cn.ieauto.autogroup.android.util.MyAsyncTask;

/**
 * 修改密码
 * 
 * @author Lampo
 * 
 */
public class ChangePassword extends MyActivity implements OnClickListener {
	private EditText ed_one, ed_two;
	private Button but_ok;
	private ImageView img_back;
	private TextView tv_title;
	String mOne = null, mTwo = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.change_password);
		initViews();
		initEvents();
	}

	@Override
	protected void initViews() {
		img_back = (ImageView) findViewById(R.id.view_top_img_back);
		tv_title = (TextView) findViewById(R.id.view_top_tv_title);
		ed_one = (EditText) findViewById(R.id.changepassword_ed_one);
		ed_two = (EditText) findViewById(R.id.changepassword_ed_two);
		but_ok = (Button) findViewById(R.id.changepassword_but);

	}

	@Override
	protected void initEvents() {
		tv_title.setText(R.string.ChangePassword);
		img_back.setBackgroundResource(R.drawable.button_return);
		but_ok.setOnClickListener(this);
		img_back.setOnClickListener(this);
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
		 * 提交修改
		 */
		case R.id.changepassword_but:
			doEdit();
			if (TextUtils.isEmpty(mOne)) {
				ed_one.setError("不能为空");
				return;
			}
			if (TextUtils.isEmpty(mTwo)) {
				ed_two.setError("不能为空");
				return;
			}
			if (mOne.length() > 6 && mOne.length() < 16) {
				toast("长度不能为空");
				return;
			}
			if (mTwo.length() > 6 && mTwo.length() < 16) {
				toast("长度不能为空");
				return;
			}
			if (!mOne.equals(mTwo)) {
				toast("两次密码必须一致");
				return;
			}
			// DOTO:还没有向服务器提交数据
			String message = mOne;
			// code=5010,表示登录时,更新cid数据
			MessageContent changpasswordMessage = new MessageContent(message,
					MyApplication.getInstance().getUserPhone(), 0);
			MyAsyncTask myAsyncTask = new MyAsyncTask(changpasswordMessage) {

				@Override
				protected void onPreExecute() {
					showLoadingDialog("正在提交");
				}

				@Override
				protected void onPostExecute(String result) {
					dismissLoadingDialog();
					if ("ok" == result) {
						MyApplication.getInstance().saveBaseInfo("", mOne, "");
						defaultFinish();
					}
				}
			};
			myAsyncTask.execute();
			break;
		default:
			break;
		}
	}

	private void doEdit() {
		mOne = ed_one.getText().toString().trim();
		mTwo = ed_two.getText().toString().trim();
	}
}
