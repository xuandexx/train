package activity.fragments.cheshenghuo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.MyApplication;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.app.MyActivity;

/**
 * 我要消违章 我要验车
 * 
 * @author Administrator
 * 
 */
public class IwantIllegalhandldispelActivity extends MyActivity implements
		OnClickListener {
	private EditText dispeled1, dispeled2, dispeled3;
	/**
	 * title:标题 <br/>
	 * iPhone:商户的手机<br/>
	 * plate:<br/>
	 * frame:<br/>
	 * engine:发动机号
	 */
	private TextView tv_title;
	private ImageView img_back;
	private Button Submit;
	String title;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.illegalhandl_dispel);
		Intent intent = this.getIntent();
		title = intent.getExtras().getString("title");
		initViews();
		initEvents();
	}

	@Override
	protected void initViews() {
		img_back = (ImageView) this.findViewById(R.id.view_top_img_back);
		tv_title = (TextView) this.findViewById(R.id.view_top_tv_title);
		dispeled1 = (EditText) findViewById(R.id.illegalhandl_dispel_editText1);
		dispeled2 = (EditText) findViewById(R.id.illegalhandl_dispel_editText2);
		dispeled3 = (EditText) findViewById(R.id.illegalhandl_dispel_editText3);
		Submit = (Button) findViewById(R.id.illegalhandl_dispel_submit);

	}

	@Override
	protected void initEvents() {
		img_back.setBackgroundResource(R.drawable.button_return);
		tv_title.setText(title);
		Submit.setOnClickListener(this);
		img_back.setOnClickListener(this);
		dispeled1.setText(MyApplication.getInstance().getLicenseNo());
		dispeled2.setText(MyApplication.getInstance().getChassis());
		dispeled3.setText(MyApplication.getInstance().getEngineNo());

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_top_img_back:
			finish();
			break;
		// case R.id.illegalhandl_dispel_submit:
		// MessageContent content = null;
		// if ("本地车代验".equals(title)) {
		// plate = dispeled1.getText().toString();
		// frame = dispeled2.getText().toString();
		// engine = dispeled3.getText().toString();
		// String message = "plate:" + plate + ",frame:" + frame
		// + ",engine:" + engine;
		// content = new MessageContent(message, "", 3183);
		// MyAsyncTask asyncTask = new MyAsyncTask(content) {
		//
		// protected void onPreExecute() {
		// showLoadingDialog("请求提交中!");
		// }
		//
		// protected void onPostExecute(String result) {
		// toast("数据提交完毕!");
		// }
		// };
		// asyncTask.execute();
		// break;
		// }
		}
	}

}
