package activity.fragments.cheyouhui;

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
import android.widget.Spinner;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.activity.fragments.chat.AddContactActivity;
import cn.ieauto.autogroup.android.adapter.SpinnerAdapter;
import cn.ieauto.autogroup.android.app.MyActivity;

/**
 * 按条件查找车友
 * 
 * @author Administrator
 * 
 */
public class Findnew_ConditionsActivity extends MyActivity {
	private Button button2;
	private ImageView img_Back;
	private RadioGroup radiogroup;
	private RadioButton radiob0, radiob1;
	private Spinner spinner1, spinner2, spinner3;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.findnew_conditions);
		initView();
		initStep();
	}

	private void initView() {

		img_Back = (ImageView) this.findViewById(R.id.view_top_img_back);
		img_Back.setBackgroundResource(R.drawable.button_return);
		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.findnewfriends_5);
		button2 = (Button) findViewById(R.id.findnew_conditions_button2);
		radiogroup = (RadioGroup) findViewById(R.id.findnew_conditions_radioGroup1);
		radiob0 = (RadioButton) findViewById(R.id.findnew_conditions_radio0);
		radiob1 = (RadioButton) findViewById(R.id.findnew_conditions_radio1);
		spinner1 = (Spinner) findViewById(R.id.findnew_conditions_spinner1);
		spinner2 = (Spinner) findViewById(R.id.findnew_conditions_spinner2);
		spinner3 = (Spinner) findViewById(R.id.findnew_conditions_spinner3);
		img_Back.setOnClickListener(butlistener);
		button2.setOnClickListener(butlistener);
		radiogroup.setOnCheckedChangeListener(radiolistener);
	}

	private void initStep() {
		String[] a = new String[] { "10~20", "21~30", "31~40", "41~50", "51~60" };
		SpinnerAdapter adapter1 = new SpinnerAdapter(this,
				R.id.findnew_conditions_spinner1, a);
		spinner1.setAdapter(adapter1);
		String[] b = new String[] { "Ʒ��", "" };
		SpinnerAdapter adapter2 = new SpinnerAdapter(this,
				R.id.findnew_conditions_spinner2, b);
		spinner2.setAdapter(adapter2);
		String[] c = new String[] { "��ϵ", "" };
		SpinnerAdapter adapter3 = new SpinnerAdapter(this,
				R.id.findnew_conditions_spinner3, c);
		spinner3.setAdapter(adapter3);
	}

	private OnCheckedChangeListener radiolistener = new OnCheckedChangeListener() {
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			RadioButton btn = (RadioButton) findViewById(checkedId);
			if (btn.isChecked()) {
				switch (checkedId) {
				case R.id.findnew_conditions_radio0:

					break;
				case R.id.findnew_conditions_radio1:

					break;
				}

			}

		}
	};
	private OnClickListener butlistener = new OnClickListener() {

		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.view_top_img_back:
				Intent intent = new Intent(Findnew_ConditionsActivity.this,
						AddContactActivity.class);
				startActivity(intent);
				Findnew_ConditionsActivity.this.finish();
				break;
			case R.id.findnew_conditions_button2:

				break;

			}

		}
	};

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initEvents() {
		// TODO Auto-generated method stub
		
	}

}
