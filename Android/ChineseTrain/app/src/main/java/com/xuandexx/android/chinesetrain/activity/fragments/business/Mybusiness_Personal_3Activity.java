package activity.fragments.business;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.CarTypeActivity;
import cn.ieauto.autogroup.android.app.ComConfig;
import cn.ieauto.autogroup.android.app.MyActivity;

/**
 * 个人的
 * 
 * @author Administrator 车辆求购
 */
public class Mybusiness_Personal_3Activity extends MyActivity implements
		OnClickListener {
	private ImageView img_Back;
	private Button button2;
	// 求购车型 ,价格区间 ,车龄区间 ,公里数范围
//	private Spinner spinner1, spinner2, spinner3, spinner4, spinner5;
	// 排量 ,排放标准, 牌照地 ,颜色 ,配置等级
	private TextView mybusiness_peraonal_3_spinner1,mybusiness_peraonal_3_spinner2,mybusiness_peraonal_3_spinner3,
	mybusiness_peraonal_3_spinner4,mybusiness_peraonal_3_spinner5,mybusiness_peraonal_3_spinner6,
	mybusiness_peraonal_3_spinner7,mybusiness_peraonal_3_spinner8,mybusiness_peraonal_3_spinner9,
	mybusiness_peraonal_3_spinner10;
//	private Spinner spinner6, spinner7, spinner8, spinner9, spinner10;
	private String carType,priece;
	// 联系电话 ,车款优先序,买家自述
	private EditText ed1, ed2, ed3;
	private String med1, med2, med3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mybusiness_personal_3);
		initView();
//		initStep();
	}

	private void initView() {
		Intent intent=new Intent();
		String title = intent.getStringExtra("result3");
		img_Back = (ImageView) this.findViewById(R.id.view_top_img_back);
		img_Back.setBackgroundResource(R.drawable.button_return);
		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.mybusiness_3);

		button2 = (Button) findViewById(R.id.mybusiness_personal_3_button2);

		ed1 = (EditText) findViewById(R.id.mybusiness_peraonal_3_ed1);
		ed2 = (EditText) findViewById(R.id.mybusiness_peraonal_3_ed2);
		ed3 = (EditText) findViewById(R.id.mybusiness_peraonal_3_ed3);
		mybusiness_peraonal_3_spinner1 = (TextView) findViewById(R.id.mybusiness_peraonal_3_spinner1);
		carType="点我设置车型";
		mybusiness_peraonal_3_spinner1.setText(carType);
		mybusiness_peraonal_3_spinner2 = (TextView) findViewById(R.id.mybusiness_peraonal_3_spinner2);
		priece="点我设置价格";
		mybusiness_peraonal_3_spinner2.setText(priece);
		mybusiness_peraonal_3_spinner3 = (TextView) findViewById(R.id.mybusiness_peraonal_3_spinner3);
		mybusiness_peraonal_3_spinner4 = (TextView) findViewById(R.id.mybusiness_peraonal_3_spinner4);
		mybusiness_peraonal_3_spinner5 = (TextView) findViewById(R.id.mybusiness_peraonal_3_spinner5);
		mybusiness_peraonal_3_spinner6 = (TextView) findViewById(R.id.mybusiness_peraonal_3_spinner6);
		mybusiness_peraonal_3_spinner7 = (TextView) findViewById(R.id.mybusiness_peraonal_3_spinner7);
		mybusiness_peraonal_3_spinner8 = (TextView) findViewById(R.id.mybusiness_peraonal_3_spinner8);
		mybusiness_peraonal_3_spinner9 = (TextView) findViewById(R.id.mybusiness_peraonal_3_spinner9);
		mybusiness_peraonal_3_spinner10 = (TextView) findViewById(R.id.mybusiness_peraonal_3_spinner10);
		img_Back.setOnClickListener(this);
		button2.setOnClickListener(this);
	}

	private void initEdit() {
		med1 = ed1.getText().toString();
		med2 = ed2.getText().toString();
		med3 = ed3.getText().toString();
	}
	private void initStep() {
		mybusiness_peraonal_3_spinner1.setOnClickListener(this);
		mybusiness_peraonal_3_spinner2.setOnClickListener(this);
		mybusiness_peraonal_3_spinner3.setOnClickListener(this);
		mybusiness_peraonal_3_spinner4.setOnClickListener(this);
		mybusiness_peraonal_3_spinner5.setOnClickListener(this);
		mybusiness_peraonal_3_spinner6.setOnClickListener(this);
		mybusiness_peraonal_3_spinner7.setOnClickListener(this);
		mybusiness_peraonal_3_spinner8.setOnClickListener(this);
		mybusiness_peraonal_3_spinner9.setOnClickListener(this);
		mybusiness_peraonal_3_spinner10.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_top_img_back:
			defaultFinish();
			break;
		case R.id.mybusiness_personal_3_button2:
			initEdit();
			break;
		case R.id.mybusiness_peraonal_3_spinner1:
			Intent intent=new Intent();
			intent.setClass(Mybusiness_Personal_3Activity.this,CarTypeActivity.class);
			 startActivityForResult(intent, ComConfig.INTENT_MODELS);
			break;
		case R.id.mybusiness_peraonal_3_spinner2:
//			Intent intent=new Intent();
//			intent.setClass(Mybusiness_Personal_3Activity.this,CarTypeActivity.class);
//			 startActivityForResult(intent, ComConfig.INTENT_SORT_BY);
//			break;
		}

	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case  ComConfig.INTENT_MODELS:
			if(data!=null){
				carType =data.getStringExtra("province");
				mybusiness_peraonal_3_spinner1.setText(carType);
			}
			break;
//		case Co
//			break;  
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
