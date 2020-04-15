package activity.fragments.business;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.app.MyActivity;

/**
 * 申请服务 个人
 * 
 * @author Lilongbing 代驾 ,陪练 代办违章，代办过户 ，代办验车，车险销售，理赔指导
 */
public class Mybusiness_Personal_1Activity extends MyActivity implements
		OnClickListener {
	private Button button2, button3;
	// 姓名,身份证,驾驶证,教练证,驾龄,
	private EditText ed1, ed2, ed3, ed4, ed5;
	// 联系地址,联系电话,服务时间,保险公司,收费标准,店友自述
	private EditText ed6, ed7, ed8, ed9, ed10, ed11;
	private String med1, med3, med4, med6, med8, med9, med10, med11;
	private int med2, med5, med7;
	private TextView tv_title;
	private ImageView img_Back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mybusiness_personal_1);
		
		initView();
//		initStep();
	}

	private void initView() {
		tv_title = (TextView) findViewById(R.id.view_top_tv_title);
		Intent intent = getIntent();
		String title = intent.getStringExtra("result");
		tv_title.setText(title);
		img_Back = (ImageView) this.findViewById(R.id.view_top_img_back);
		img_Back.setBackgroundResource(R.drawable.button_return);
		button2 = (Button) findViewById(R.id.mybusiness_personal_button2);
		button3 = (Button) findViewById(R.id.mybusiness_personal_button3);
		ed1 = (EditText) findViewById(R.id.mybusiness_personal_ed1);
		ed2 = (EditText) findViewById(R.id.mybusiness_personal_ed2);
		ed3 = (EditText) findViewById(R.id.mybusiness_personal_ed3);
		ed4 = (EditText) findViewById(R.id.mybusiness_personal_ed4);
		ed5 = (EditText) findViewById(R.id.mybusiness_personal_ed5);
		ed6 = (EditText) findViewById(R.id.mybusiness_personal_ed6);
		ed7 = (EditText) findViewById(R.id.mybusiness_personal_ed7);
	
		ed7.setInputType(InputType.TYPE_CLASS_PHONE);
		
		ed8 = (EditText) findViewById(R.id.mybusiness_personal_ed8);
		ed9 = (EditText) findViewById(R.id.mybusiness_personal_ed9);
		ed10 = (EditText) findViewById(R.id.mybusiness_personal_ed10);
		ed11 = (EditText) findViewById(R.id.mybusiness_personal_ed11);
		img_Back.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
	}

	private void initEdit() {
		med1 = ed1.getText().toString();
		med2 = Integer.parseInt(ed2.getText().toString());
		med3 = ed1.getText().toString();
		med4 = ed1.getText().toString();
		med5 = Integer.parseInt(ed5.getText().toString());
		med6 = ed1.getText().toString();
		med7 = Integer.parseInt(ed7.getText().toString());
		med8 = ed1.getText().toString();
		med9 = ed1.getText().toString();
		med10 = ed1.getText().toString();
		med11 = ed1.getText().toString();
	}

	private void initStep() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_top_img_back:
			defaultFinish();
			break;
		case R.id.mybusiness_personal_button2:

			break;
		case R.id.mybusiness_personal_button3:
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
