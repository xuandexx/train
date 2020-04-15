package activity.fragments.business;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.adapter.SpinnerAdapter;
import cn.ieauto.autogroup.android.app.MyActivity;

/**
 * 个人的
 * 
 * @author Administrator 拼车
 */
public class Mybusiness_Personal_4Activity extends MyActivity implements
		OnClickListener {
	// 本人类型,拼车类型
	private Spinner spinner1, spinner2;
	private Button button2, but_Submit;
	private ImageView img_Back;
	// 真实姓名、身份证、联系电话、联系时间、出发地 , 目的地,拼友自述,出发时间
	private EditText ed1, ed2, ed3, ed4, ed5, ed6, ed7;
	private String med1, med2, med3, med4, med5, med6, med7;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mybusiness_personal_4);
		initView();
//		initStep();
	}

	private void initView() {
		spinner1 = (Spinner) findViewById(R.id.mybusiness_personal_4_spinner1);
		spinner2 = (Spinner) findViewById(R.id.mybusiness_personal_4_spinner2);
		img_Back = (ImageView) this.findViewById(R.id.view_top_img_back);
		img_Back.setBackgroundResource(R.drawable.button_return);

		button2 = (Button) findViewById(R.id.mybusiness_personal_4_button2);
		but_Submit = (Button) findViewById(R.id.mybusiness_personal_4_button3);
		ed1 = (EditText) findViewById(R.id.mybusiness_personal_4_ed1);
		ed2 = (EditText) findViewById(R.id.mybusiness_personal_4_ed2);
		ed3 = (EditText) findViewById(R.id.mybusiness_personal_4_ed3);
		ed4 = (EditText) findViewById(R.id.mybusiness_personal_4_ed4);
		ed5 = (EditText) findViewById(R.id.mybusiness_personal_4_ed5);
		ed6 = (EditText) findViewById(R.id.mybusiness_personal_4_ed6);
		ed7 = (EditText) findViewById(R.id.mybusiness_personal_4_ed7);
		img_Back.setOnClickListener(this);
		button2.setOnClickListener(this);
		but_Submit.setOnClickListener(this);
	}

	private void initEdit() {
		med1 = ed1.getText().toString();
		med2 = ed2.getText().toString();
		med3 = ed3.getText().toString();
		med4 = ed4.getText().toString();
		med5 = ed5.getText().toString();
		med6 = ed6.getText().toString();
		med7 = ed7.getText().toString();
	}

	private void initStep() {
		String[] a = new String[] { "无车寻车友", "有车寻拼友" };
		SpinnerAdapter adapter1 = new SpinnerAdapter(this,
				R.id.mybusiness_personal_4_spinner1, a);
		spinner1.setAdapter(adapter1);
		String[] b = new String[] { "上下班拼车", "长途拼车", "自驾游拼车" };
		SpinnerAdapter adapter2 = new SpinnerAdapter(this,
				R.id.mybusiness_personal_4_spinner2, b);
		spinner2.setAdapter(adapter2);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_top_img_back:
			defaultFinish();
			break;
		case R.id.mybusiness_personal_4_button2:

			break;

		case R.id.mybusiness_personal_4_button3:
			initEdit();
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
