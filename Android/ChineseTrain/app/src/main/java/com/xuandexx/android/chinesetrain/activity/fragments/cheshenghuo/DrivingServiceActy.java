package activity.fragments.cheshenghuo;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.app.MyActivity;

/**
 * 我要代驾 选择希望到达时间
 * 
 * @author Administrator
 * 
 */
public class DrivingServiceActy extends MyActivity implements
		View.OnClickListener {
	private Button button3;
	private ImageView img_Back;
	private RadioButton radio1, radio2, radio3, radio4, radio5;
	private EditText edittext;
	private String[] list = new String[2];
    private String content;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.findsubstitutedriv);
		initViews();
		initEvents();
	}

	@Override
	protected void initViews() {

		img_Back = (ImageView) this.findViewById(R.id.view_top_img_back);
		img_Back.setBackgroundResource(R.drawable.button_return);
		button3 = (Button) findViewById(R.id.findsubstitutedriv_button3);
		radio1 = (RadioButton) findViewById(R.id.findsubstitutedriv_radioButton1);
		radio2 = (RadioButton) findViewById(R.id.findsubstitutedriv_radioButton2);
		radio3 = (RadioButton) findViewById(R.id.findsubstitutedriv_radioButton3);
		radio4 = (RadioButton) findViewById(R.id.findsubstitutedriv_radioButton4);
		radio5 = (RadioButton) findViewById(R.id.findsubstitutedriv_radioButton5);
		edittext = (EditText) findViewById(R.id.findsubstitutedriv_editText1);
		((TextView) findViewById(R.id.view_top_tv_title))
		.setText(R.string.findsubstitutedriv_tirle);
	}


	protected void dialog() {
		Builder builder = new Builder(this);
		builder.setMessage("确认发出吗？");
		builder.setTitle(list[0] + "\n" + list[1]);
		builder.setPositiveButton("确认", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(DrivingServiceActy.this, null);
				startActivity(intent);
				DrivingServiceActy.this.finish();
			}
		});
		builder.setNegativeButton("取消", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create().show();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_top_img_back:
			defaultFinish();
			break;
		case R.id.findsubstitutedriv_radioButton1:
			content = "3分钟";
			radio1.setChecked(true);
			radio2.setChecked(false);
			radio3.setChecked(false);
			radio4.setChecked(false);
			radio5.setChecked(false);
			break;
		case R.id.findsubstitutedriv_radioButton2:
			content = "5分钟";
			radio2.setChecked(true);
			radio1.setChecked(false);
			radio3.setChecked(false);
			radio4.setChecked(false);
			radio5.setChecked(false);
			break;
		case R.id.findsubstitutedriv_radioButton3:
			content = "10分钟";
			radio3.setChecked(true);
			radio1.setChecked(false);
			radio2.setChecked(false);
			radio4.setChecked(false);
			radio5.setChecked(false);
			break;
		case R.id.findsubstitutedriv_radioButton4:
			content = "20分钟";
			radio4.setChecked(true);
			radio1.setChecked(false);
			radio3.setChecked(false);
			radio2.setChecked(false);
			radio5.setChecked(false);
			break;
		case R.id.findsubstitutedriv_radioButton5:
			content = "30分钟";
			radio5.setChecked(true);
			radio1.setChecked(false);
			radio3.setChecked(false);
			radio4.setChecked(false);
			radio2.setChecked(false);
			break;
		case R.id.findsubstitutedriv_button3:
			String ed_content = edittext.getText().toString().trim();
			toast(content + ed_content);
			break;
		default:
			break;
		}
	}

	@Override
	protected void initEvents() {
		img_Back.setOnClickListener(this);
		button3.setOnClickListener(this);
		radio1.setOnClickListener(this);
		radio2.setOnClickListener(this);
		radio3.setOnClickListener(this);
		radio4.setOnClickListener(this);
		radio5.setOnClickListener(this);
	}
}
