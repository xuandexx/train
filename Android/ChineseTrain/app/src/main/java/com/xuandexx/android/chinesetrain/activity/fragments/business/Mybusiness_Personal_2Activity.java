package activity.fragments.business;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.AddressCityActivity;
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.CarSafeActivity;
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.ColorActivity;
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.CourseActivity;
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.GearActivity;
import cn.ieauto.autogroup.android.adapter.BrandAdapter;
import cn.ieauto.autogroup.android.app.ComConfig;
import cn.ieauto.autogroup.android.app.MyActivity;

/**
 * 个人
 * 
 * @author Administrator 私车租借 私车转让
 */
public class Mybusiness_Personal_2Activity extends MyActivity implements
		OnClickListener {
	private ImageView img_Back;
	private Button button2, button3;
	// 车辆品牌,车辆型号,排档 ,颜色,保险，上牌日期
	private TextView spinner1, spinner2, spinner3, spinner4, spinner5;
	// 上牌日期 ，牌照地, 行驶里程 ,排放标准,
	private TextView spinner6, spinner7, spinner8, spinner9;
	// 行驶证 ,产权证 , 驾驶证,电话 ,联系时间 ,价格 ,店友自述
	private String mSpinner1, mSpinner2, mSpinner3, mSpinner4, mSpinner5,
			mSpinner6, mSpinner7, mSpinner8, mSpinner9;
	private EditText ed1, ed2, ed3, ed4, ed5, ed6, ed7;

	private String med1, med2, med3, med4, med5, med6, med7;
//	private Intent intent;
	private TextView tv_title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mybusiness_personal_2);
		initView();
		// initStep();
	}

	private void initView() {
		img_Back = (ImageView) this.findViewById(R.id.view_top_img_back);
		img_Back.setBackgroundResource(R.drawable.button_return);
		button2 = (Button) findViewById(R.id.mybusiness_personal_2_button2);
		button3 = (Button) findViewById(R.id.mybusiness_personal_2_Submit);
		spinner1 = (TextView) findViewById(R.id.mybusiness_personal_2_spinner1);
		spinner1.setText("点我选择车品牌");
		spinner2 = (TextView) findViewById(R.id.mybusiness_personal_2_spinner2);
		spinner2.setText("点我选择车系");
		spinner3 = (TextView) findViewById(R.id.mybusiness_personal_2_spinner3);
		spinner3.setText("点我选择排挡");
		spinner4 = (TextView) findViewById(R.id.mybusiness_personal_2_spinner4);
		spinner4.setText("选择车辆颜色");
		spinner5 = (TextView) findViewById(R.id.mybusiness_personal_2_spinner5);
		spinner5.setText("点我选择保险的种类");
		spinner6 = (TextView) findViewById(R.id.mybusiness_personal_2_spinner6);
		spinner6.setText("点我设置时间");
		
		spinner7 = (TextView) findViewById(R.id.mybusiness_personal_2_spinner7);
		spinner7.setText("点我设置牌照地");
		spinner8 = (TextView) findViewById(R.id.mybusiness_personal_2_spinner8);
		spinner8.setText("点我设置行驶里程");
		spinner9 = (TextView) findViewById(R.id.mybusiness_personal_2_spinner9);
		spinner9.setText("点我选择排放标准");
		ed1 = (EditText) findViewById(R.id.mybusiness_personal_2_ed1);
		ed2 = (EditText) findViewById(R.id.mybusiness_personal_2_ed2);
		ed3 = (EditText) findViewById(R.id.mybusiness_personal_2_ed3);
		ed4 = (EditText) findViewById(R.id.mybusiness_personal_2_ed4);
		ed5 = (EditText) findViewById(R.id.mybusiness_personal_2_ed5);
		ed6 = (EditText) findViewById(R.id.mybusiness_personal_2_ed6);
		ed7 = (EditText) findViewById(R.id.mybusiness_personal_2_ed7);

		tv_title = (TextView) findViewById(R.id.view_top_tv_title);
		img_Back.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
		
		spinner1.setOnClickListener(this);
		spinner2.setOnClickListener(this);
		spinner3.setOnClickListener(this);
		spinner4.setOnClickListener(this);
		spinner5.setOnClickListener(this);
		spinner6.setOnClickListener(this);
		spinner7.setOnClickListener(this);
		spinner8.setOnClickListener(this);
	}

	private void initEdit() {
		med1 = ed1.getText().toString().trim();
		med2 = ed2.getText().toString().trim();
		med3 = ed3.getText().toString().trim();
		med4 = ed4.getText().toString().trim();
		med5 = ed5.getText().toString().trim();
		med6 = ed6.getText().toString().trim();
		med7 = ed7.getText().toString().trim();
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_top_img_back:
			defaultFinish();
			break;
		case R.id.mybusiness_personal_2_button2:

			break;
		case R.id.mybusiness_personal_2_Submit:
			initEdit();
//			String M = mSpinner1 + mSpinner2 + mSpinner3 + mSpinner4
//					+ mSpinner5 + mSpinner6 + mSpinner7 + mSpinner8 + mSpinner9
//					+ med1 + med2 + med3 + med4 + med5 + med6 + med7;
//			toast(M);
			break;
		// 车品牌
		case R.id.mybusiness_personal_2_spinner1:
			Intent intent1 = new Intent();
			intent1.setClass(Mybusiness_Personal_2Activity.this,
					BrandAdapter.class);
			startActivityForResult(intent1, ComConfig.INTENT_BRAND_CARS);
			break;
		case R.id.mybusiness_personal_2_spinner2:
			Intent intent2 = new Intent();
			intent2.setClass(Mybusiness_Personal_2Activity.this,
					BrandAdapter.class);
			startActivityForResult(intent2, ComConfig.INTENT_BRAND_CARS);
			break;
		case R.id.mybusiness_personal_2_spinner3:
			Intent intent3=new Intent();
			intent3.setClass(Mybusiness_Personal_2Activity.this, GearActivity.class);
			startActivityForResult(intent3, ComConfig.INTENT_CARS_GEAR);
		break;
		case R.id.mybusiness_personal_2_spinner4:
			Intent intent4=new Intent();
			intent4.setClass(Mybusiness_Personal_2Activity.this, ColorActivity.class);
			startActivityForResult(intent4, ComConfig.INTENT_CARS_COLORS);
			break;
		case R.id.mybusiness_personal_2_spinner5:
			Intent intent5=new Intent();
			intent5.setClass(Mybusiness_Personal_2Activity.this, CarSafeActivity.class);
			startActivityForResult(intent5, ComConfig.INTENT_CARS_SAFE);
			break;
			//时间
		case R.id.mybusiness_personal_2_spinner6:
//			Intent intent6=new Intent();
//			intent6.setClass(Mybusiness_Personal_2Activity.this, CarSafeActivity.class);
//			startActivityForResult(intent6, ComConfig.INTENT_CARS_SAFE)
			break;
		case R.id.mybusiness_personal_2_spinner7:
			Intent intent7=new Intent();
			intent7.setClass(Mybusiness_Personal_2Activity.this, AddressCityActivity.class);
			startActivityForResult(intent7, ComConfig.INTENT_CITY);
			break;
		case R.id.mybusiness_personal_2_spinner8:
			Intent intent8=new Intent();
			intent8.setClass(Mybusiness_Personal_2Activity.this, CourseActivity.class);
			startActivityForResult(intent8, ComConfig.INTENT_CARS_COURS);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case ComConfig.INTENT_BRAND_CARS:
			spinner1.setText(data.getStringExtra("province"));
			spinner2.setText(data.getStringExtra("city"));
			break;
		case ComConfig.INTENT_CARS_GEAR:
			spinner3.setText(data.getStringExtra("province"));
			break;
		case ComConfig.INTENT_CARS_COLORS:
			spinner4.setText(data.getStringExtra("province"));
			break;
		case ComConfig.INTENT_CARS_SAFE:
			spinner5.setText(data.getStringExtra("province"));
			break;
//		case
		case ComConfig.INTENT_CITY:
			spinner7.setText(data.getStringExtra("province")+data.getStringExtra("city"));
			break;
		case ComConfig.INTENT_CARS_COURS:
			spinner8.setText(data.getStringExtra("province"));
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
