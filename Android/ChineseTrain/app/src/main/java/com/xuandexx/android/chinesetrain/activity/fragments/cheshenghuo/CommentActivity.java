package activity.fragments.cheshenghuo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.app.MyActivity;

/**
 * 我要评论
 * 
 * @author Simon
 */
public class CommentActivity extends MyActivity implements OnClickListener {
	// 综合评分
	private TextView tv;
	// 打分：
	// 印象
	// private RadioGroup Radiog1, Radiog2, Radiog3, Radiog4;
	private RadioButton r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12;
	// 返回：提交
	private Button Return, button2;
	private String[] list = new String[4];
	float number5;
	private SeekBar seekbar1, seekbar2, seekbar3, seekbar4;
	private Button button3, button4, button5, button6, button7, button8,
			button9, button10;
	private int mnuber1 = 1, mnuber2 = 1, mnuber3 = 1, mnuber4 = 1;
	private Intent intent;
	private ImageView img_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.comment);
		// comment_title
		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.comment_title);
		initView();
		// initnumber();
	}

	private void initView() {
		img_back = (ImageView) this.findViewById(R.id.view_top_img_back);
		img_back.setBackgroundResource(R.drawable.button_return);
		img_back.setOnClickListener(this);
		tv = (TextView) findViewById(R.id.view_top_tv_title);
		tv.setText("评论");
		// r1 = (RadioButton) findViewById(R.id.radio0);
		// r2 = (RadioButton) findViewById(R.id.radio1);
		// r3 = (RadioButton) findViewById(R.id.radio2);
		// r4 = (RadioButton) findViewById(R.id.radio4);
		// r5 = (RadioButton) findViewById(R.id.radio5);
		// r6 = (RadioButton) findViewById(R.id.radio6);
		// r7 = (RadioButton) findViewById(R.id.radio7);
		// r8 = (RadioButton) findViewById(R.id.radio8);
		// r9 = (RadioButton) findViewById(R.id.radio9);
		// r10 = (RadioButton) findViewById(R.id.radio10);
		// r11 = (RadioButton) findViewById(R.id.radio11);
		// r12 = (RadioButton) findViewById(R.id.radio12);
		// Return = (Button) findViewById(R.id.Back);
		// button2 = (Button) findViewById(R.id.comment_button2);

		// Radiog1.setOnCheckedChangeListener(Radiog1list);
		// Radiog2.setOnCheckedChangeListener(Radiog2list);
		// Radiog3.setOnCheckedChangeListener(Radiog3list);
		// Radiog4.setOnCheckedChangeListener(Radiog4list);
		// button2.setOnClickListener(button2listener);
		// // -
		// button3 = (Button) findViewById(R.id.comment_button3);
		// // +
		// button4 = (Button) findViewById(R.id.comment_button4);
		// button5 = (Button) findViewById(R.id.comment_button5);
		// button6 = (Button) findViewById(R.id.comment_button6);
		// button7 = (Button) findViewById(R.id.comment_button7);
		// button8 = (Button) findViewById(R.id.comment_button8);
		// button9 = (Button) findViewById(R.id.comment_button9);
		// button10 = (Button) findViewById(R.id.comment_button10);
		// seekbar1 = (SeekBar) findViewById(R.id.comment_seekbar1);
		// seekbar2 = (SeekBar) findViewById(R.id.comment_seekbar2);
		// seekbar3 = (SeekBar) findViewById(R.id.comment_seekbar3);
		// seekbar4 = (SeekBar) findViewById(R.id.comment_seekbar4);
		// Radiog1 = (RadioGroup) findViewById(R.id.comment_radioGroup1);
		// Radiog2 = (RadioGroup) findViewById(R.id.comment_radioGroup2);
		// Radiog3 = (RadioGroup) findViewById(R.id.comment_radioGroup3);
		// Radiog4 = (RadioGroup) findViewById(R.id.comment_radioGroup4);
		// seekbar1.setProgress(mnuber1);
		// seekbar2.setProgress(mnuber2);
		// seekbar3.setProgress(mnuber3);
		// seekbar4.setProgress(mnuber4);
		// seekbar1.setOnSeekBarChangeListener(seeklistener1);
		// seekbar2.setOnSeekBarChangeListener(seeklistener2);
		// seekbar3.setOnSeekBarChangeListener(seeklistener3);
		// seekbar4.setOnSeekBarChangeListener(seeklistener4);
		// button3.setOnClickListener(butlistener);
		// button4.setOnClickListener(butlistener);
		// button5.setOnClickListener(butlistener);
		// button6.setOnClickListener(butlistener);
		// button7.setOnClickListener(butlistener);
		// button8.setOnClickListener(butlistener);
		// button9.setOnClickListener(butlistener);
		// button10.setOnClickListener(butlistener);
	}

	// 1
	private OnSeekBarChangeListener seeklistener1 = new OnSeekBarChangeListener() {
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
		}

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			// switch (seekBar.getId()) {
			// case R.id.comment_seekbar1:
			//
			// break;
			// }
			mnuber1 = seekbar1.getProgress();
			initnumber();
			// tv.setText(String.valueOf(number5));
		}
	};
	// 2
	private OnSeekBarChangeListener seeklistener2 = new OnSeekBarChangeListener() {
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
		}

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {

			mnuber2 = seekbar2.getProgress();
			initnumber();
			// tv.setText(String.valueOf(number5));
		}
	};
	// 3
	private OnSeekBarChangeListener seeklistener3 = new OnSeekBarChangeListener() {

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
		}

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			mnuber3 = seekbar3.getProgress();
			initnumber();
			// tv.setText(String.valueOf(number5));
		}
	};
	// 4
	private OnSeekBarChangeListener seeklistener4 = new OnSeekBarChangeListener() {
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
		}

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			mnuber4 = seekbar4.getProgress();
			initnumber();
			// tv.setText(String.valueOf(number5));
		}
	};

	// ƽ���
	private void initnumber() {
		number5 = (mnuber1 + mnuber2 + mnuber3 + mnuber4) / 4;
		tv.setText(String.valueOf(number5));
	}

	private OnClickListener butlistener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			// 1 mnuber1 ,seekbar1
			// case R.id.comment_button3:
			// if (mnuber1 >= 1) {
			// mnuber1 = mnuber1 - 1;
			// initnumber();
			// tv.setText(String.valueOf(number5));
			// seekbar1.setProgress(mnuber1);
			// }
			// break;
			// case R.id.comment_button4:
			// if (mnuber1 < 5) {
			// mnuber1 = mnuber1 + 1;
			// initnumber();
			// tv.setText(String.valueOf(number5));
			// seekbar1.setProgress(mnuber1);
			// }
			// break;
			// // 2 mnuber2 ,seekbar2
			// case R.id.comment_button5:
			// if (mnuber2 >= 1) {
			// mnuber2 = mnuber2 - 1;
			// initnumber();
			// tv.setText(String.valueOf(number5));
			// seekbar2.setProgress(mnuber2);
			// }
			// break;
			// case R.id.comment_button6:
			// if (mnuber2 < 5) {
			// mnuber2 = mnuber2 + 1;
			// initnumber();
			// tv.setText(String.valueOf(number5));
			// seekbar2.setProgress(mnuber2);
			// }
			// break;
			// // 3 mnuber3, seekbar3
			// case R.id.comment_button7:
			// if (mnuber3 >= 1) {
			// mnuber3 = mnuber3 - 1;
			// initnumber();
			// tv.setText(String.valueOf(number5));
			// seekbar3.setProgress(mnuber3);
			// }
			// break;
			// case R.id.comment_button8:
			// if (mnuber3 < 5) {
			// mnuber3 = mnuber3 + 1;
			// initnumber();
			// tv.setText(String.valueOf(number5));
			// seekbar3.setProgress(mnuber3);
			// }
			// break;
			// // 4 mnuber4, seekbar4
			// case R.id.comment_button9:
			// if (mnuber4 >= 1) {
			// mnuber4 = mnuber4 - 1;
			// initnumber();
			// tv.setText(String.valueOf(number5));
			// seekbar4.setProgress(mnuber4);
			// }
			// break;
			// case R.id.comment_button10:
			// if (mnuber4 < 5) {
			// mnuber4 = mnuber4 + 1;
			// initnumber();
			// tv.setText(String.valueOf(number5));
			// seekbar4.setProgress(mnuber4);
			// }
			// break;
			}

		}
	};

	private OnClickListener button2listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.view_top_img_back:
				finish();
				break;
			case R.id.comment_button2:
				intent = new Intent(CommentActivity.this,
						EvaluationActivity.class);
				Bundle bundle = new Bundle();
				bundle.putFloat("number", number5);
				bundle.putStringArray("list", list);
				intent.putExtras(bundle);
				startActivity(intent);
				finish();
				break;
			}

		}
	};

	private OnCheckedChangeListener Radiog1list = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			RadioButton btn = (RadioButton) findViewById(checkedId);
			System.out.println("-------");

			if (btn.isChecked()) {
				switch (checkedId) {
				case R.id.radio0:
					System.out.println("r1=" + r1.getText().toString());
					list[0] = r1.getText().toString();
					break;
				case R.id.radio1:
					System.out.println("r2=" + r2.getText().toString());
					list[0] = r2.getText().toString();
					break;
				case R.id.radio2:
					System.out.println("r3=" + r3.getText().toString());
					list[0] = r3.getText().toString();
					break;
				}
			}
		}
	};
	private OnCheckedChangeListener Radiog2list = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			RadioButton btn = (RadioButton) findViewById(checkedId);
			System.out.println("-------");
			if (btn.isChecked()) {
				switch (checkedId) {
				case R.id.radio4:
					System.out.println("r4=" + r4.getText().toString());
					list[1] = r4.getText().toString();
					break;
				case R.id.radio5:
					System.out.println("r5=" + r5.getText().toString());
					list[1] = r5.getText().toString();
					break;
				case R.id.radio6:
					System.out.println("r6=" + r6.getText().toString());
					list[1] = r6.getText().toString();
					break;
				}
			}
		}
	};
	private OnCheckedChangeListener Radiog3list = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			RadioButton btn = (RadioButton) findViewById(checkedId);
			System.out.println("-------");
			if (btn.isChecked()) {
				switch (checkedId) {
				case R.id.radio7:
					System.out.println("r7=" + r7.getText().toString());
					list[2] = r7.getText().toString();
					break;
				case R.id.radio8:
					System.out.println("r8=" + r8.getText().toString());
					list[2] = r8.getText().toString();
					break;
				case R.id.radio9:
					System.out.println("r9=" + r9.getText().toString());
					list[2] = r9.getText().toString();
					break;
				}
			}
		}
	};
	private OnCheckedChangeListener Radiog4list = new OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			RadioButton btn = (RadioButton) findViewById(checkedId);
			System.out.println("-------");
			if (btn.isChecked()) {
				switch (checkedId) {
				case R.id.radio10:
					System.out.println("r10=" + r10.getText().toString());
					list[3] = r10.getText().toString();
					break;
				case R.id.radio11:
					System.out.println("r11=" + r11.getText().toString());
					list[3] = r11.getText().toString();
					break;
				case R.id.radio12:
					System.out.println("r12=" + r12.getText().toString());
					list[3] = r12.getText().toString();
					break;
				}
			}
		}
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_top_img_back:
			defaultFinish();
			break;

		default:
			break;
		}
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initEvents() {
		// TODO Auto-generated method stub

	}
}
