package activity.fragments.cheshenghuo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.app.MyActivity;
import cn.ieauto.autogroup.android.dialog.CustomDialog;

/**
 * 附近车友求助 选择到达时间
 * 
 * @author Administrator
 * 
 */
public class Nearbyhelpriders extends MyActivity implements OnClickListener {
	private Button but_submit;
	private EditText ed;
	private RadioGroup radiogroup;
	private RadioButton radio0, radio1, radio2, radio3, radio4, radio5;
	String K = "";
	String RadioText = "";
	private CustomDialog.Builder builder;
	// String Myphone=null,Phone=null;
	// 返回
	private ImageView img_back;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.nearbyhelpriders);

		// Myphone=this.getIntent().getExtras().getString("myphone");
		// Phone=this.getIntent().getExtras().getString("phone");
		initViews();
		initEvents();
	}

	@Override
	protected void initViews() {
		img_back = (ImageView) this.findViewById(R.id.view_top_img_back);
		but_submit = (Button) findViewById(R.id.nearbyhel_submit);
		ed = (EditText) findViewById(R.id.nearbyhel_edittext1);
		radiogroup = (RadioGroup) findViewById(R.id.nearbyhel_radioGroup1);
		radio0 = (RadioButton) findViewById(R.id.nearbyhel_radio0);
		radio1 = (RadioButton) findViewById(R.id.nearbyhel_radio1);
		radio2 = (RadioButton) findViewById(R.id.nearbyhel_radio2);
		radio3 = (RadioButton) findViewById(R.id.nearbyhel_radio3);
		radio4 = (RadioButton) findViewById(R.id.nearbyhel_radio4);
		radio5 = (RadioButton) findViewById(R.id.nearbyhel_radio5);

	}

	@Override
	protected void initEvents() {
		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.nearbyhelpriders_title);
		img_back.setBackgroundResource(R.drawable.button_return);
		img_back.setOnClickListener(this);
		radiogroup.setOnCheckedChangeListener(radiogrouplistener);
		but_submit.setOnClickListener(this);
	}

	private OnCheckedChangeListener radiogrouplistener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			RadioButton btn = (RadioButton) findViewById(checkedId);

			if (btn.isChecked()) {
				switch (checkedId) {
				case R.id.nearbyhel_radio0:
					RadioText = radio0.getText().toString();
					break;
				case R.id.nearbyhel_radio1:
					RadioText = radio1.getText().toString();
					break;
				case R.id.nearbyhel_radio2:
					RadioText = radio2.getText().toString();
					break;
				case R.id.nearbyhel_radio3:
					RadioText = radio3.getText().toString();
					break;
				case R.id.nearbyhel_radio4:
					RadioText = radio4.getText().toString();
					break;
				case R.id.nearbyhel_radio5:
					RadioText = radio5.getText().toString();
					break;

				}
			}
		}
	};

	private void initdialog() {
		builder = new CustomDialog.Builder(Nearbyhelpriders.this);
		builder.setTitle("确认内容").setMessage(K)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						toast("回复成功!!");
						defaultFinish();
					}
				}).setNegativeButton("取消", null).create().show();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_top_img_back:
			defaultFinish();
			break;
		case R.id.nearbyhel_submit:
			if (TextUtils.isEmpty(RadioText)) {
				toast(R.string.findsubstitutedriv_tirle_10);
				return;
			} else if (TextUtils.isEmpty(ed.getText())) {
				K = RadioText + "到达";
			} else {
				K = RadioText + "到达" + "," + ed.getText();
			}
			initdialog();
			break;

		default:
			break;
		}
	}

}
