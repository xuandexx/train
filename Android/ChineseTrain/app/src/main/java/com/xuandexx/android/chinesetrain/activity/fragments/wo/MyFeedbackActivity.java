package activity.fragments.wo;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.ieauto.autogroup.android.MyApplication;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.app.MyActivity;

/**
 * 意见反馈
 * 
 * @author Administrator
 * 
 */
public class MyFeedbackActivity extends MyActivity implements OnClickListener {
	private ImageView img_Back;
	private Button but_Submit;
	private EditText ed_content, ed_phone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_feedback);
		initViews();
		initEvents();
	}

	@Override
	protected void initViews() {
		img_Back = (ImageView) this.findViewById(R.id.view_top_img_back);
		but_Submit = (Button) findViewById(R.id.myfeedback_but_submit);
		ed_content = (EditText) findViewById(R.id.myfeedback_content);
		ed_phone = (EditText) findViewById(R.id.myfeedback_ed_phone);
	}

	@Override
	protected void initEvents() {
		img_Back.setBackgroundResource(R.drawable.button_return);
		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.myfeedback_title);
		ed_phone.setText(MyApplication.getInstance().getUserPhone());

		img_Back.setOnClickListener(this);
		but_Submit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_top_img_back:
			defaultFinish();
			break;
		case R.id.myfeedback_but_submit:
			String mContent = ed_content.getText().toString().trim();
			String mPhone = ed_phone.getText().toString().trim();
			Toast.makeText(MyFeedbackActivity.this,
					mContent + "," + "/n" + mPhone, Toast.LENGTH_SHORT).show();
			defaultFinish();
			break;
		default:
			break;
		}
	}

}
