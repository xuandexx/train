package activity.fragments.wo;

import java.util.Calendar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.MyApplication;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.CarTypeActivity;
import cn.ieauto.autogroup.android.adapter.BrandAdapter;
import cn.ieauto.autogroup.android.adapter.DataFillAdapter;
import cn.ieauto.autogroup.android.app.ComConfig;
import cn.ieauto.autogroup.android.app.MyActivity;
import cn.ieauto.autogroup.android.dialog.MyCarTimeDialog;
import cn.ieauto.autogroup.android.entity.MessageContent;
import cn.ieauto.autogroup.android.util.CarTypeKeyUtil;

/**
 * 我的座驾
 * 
 * @author Administrator mycar_title
 */
public class MycarsActivity extends MyActivity implements OnClickListener {
	@SuppressWarnings("unused")
	// 照片
	private ImageView image;

	/** 选择品牌 */
	private Button but_brand;
	/** 选择车系 */
	private Button but_cars;
	/** 选择车型 */
	private Button but_models;

	/**
	 * 选择车牌号
	 */
	private TextView tv_CarLicenseNo = null;
	/** 选择简称 */
	private TextView tv_province = null;
	/** 选择地区 */
	private TextView tv_cityno = null;
	/**
	 * 车牌号 =mProvince+mCity+mCarLicenseNo
	 */
	String mLicenseNo;
	/** 简称 */
	String mProvince;
	/** 地区 */
	String mCityNo;
	/** 数字 */
	String mCarLicenseNo;
	/**
	 * 车架号
	 */
	private TextView tv_Chassis = null;
	/**
	 * 发动机号
	 */
	private TextView tv_EngineNo = null;

	/**
	 * 选择上牌日期
	 */
	private Button but_CarTime;
	/** 上牌日期 */
	String mCarTime;
	/** 返回 */
	private ImageView img_Back;
	String mBrand = null, mCars = null, mModels = null;
	String mChassis = null;
	String mEngineNo = null;

	/**
	 * 时间
	 */
	private Calendar calendar = Calendar.getInstance();
	String initDate = calendar.get(Calendar.YEAR) + "年"
			+ calendar.get(Calendar.MONTH) + "月"
			+ calendar.get(Calendar.DAY_OF_MONTH) + "日";
	// 获取车牌号的工具类
	CarTypeKeyUtil carType = new CarTypeKeyUtil(this);
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.mycar);
		initViews();
		doResult();
		initEvents();
		initMyApplicatione();
	}

	@Override
	protected void initViews() {
		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.mycar_title);
		image = (ImageView) findViewById(R.id.mycar_imageView1);
		img_Back = (ImageView) findViewById(R.id.view_top_img_back);
		but_brand = (Button) findViewById(R.id.Mycars_but_brand);
		but_cars = (Button) findViewById(R.id.Mycars_but_cars);
		but_models = (Button) findViewById(R.id.Mycar_but_modles);

		tv_province = (TextView) findViewById(R.id.mycar_but_province);
		tv_cityno = (TextView) findViewById(R.id.mycar_but_city);
		tv_CarLicenseNo = (TextView) findViewById(R.id.mycar_LicenseNo);

		tv_Chassis = (TextView) findViewById(R.id.mycar_Chassis);
		tv_EngineNo = (TextView) findViewById(R.id.mycar_EngineNo);

		but_CarTime = (Button) findViewById(R.id.mycar_but_cartime);
	}

	private void doResult() {
		intent = this.getIntent();
		Bundle bundle = new Bundle();
		mBrand = bundle.getString("auto_brand");
		mCars = bundle.getString("auto_series");
		mModels = bundle.getString("auto_style");
		mLicenseNo = bundle.getString("auto_license");
		mChassis = bundle.getString("auto_frame_no");
		mEngineNo = bundle.getString("auto_engine_no");
		mCarTime = bundle.getString("auto_on_sign_date");
		but_brand.setText(mBrand);
		but_cars.setText(mCars);
		but_models.setText(mModels);
		tv_CarLicenseNo.setText(mLicenseNo);
		tv_Chassis.setText(mChassis);
		tv_EngineNo.setText(mEngineNo);
		but_CarTime.setText(mCarTime);
		MyApplication.getInstance().saveMessage(mBrand, mCars, mModels,
				mChassis, mEngineNo, mCarTime);
		MyApplication.getInstance().saveLicenseNo(mLicenseNo, mProvince,
				mCityNo, mCarLicenseNo);
	}

	/** 启动界面时加载数据 */
	private void initMyApplicatione() {
		but_brand.setText(MyApplication.getInstance().getBrand());
		but_cars.setText(MyApplication.getInstance().getCars());
		but_models.setText(MyApplication.getInstance().getModels());

		if (TextUtils.isEmpty(MyApplication.getInstance().getProvince())) {
			tv_province.setText("省份");
		} else {
			tv_province.setText(MyApplication.getInstance().getProvince());
		}

		if (TextUtils.isEmpty(MyApplication.getInstance().getCityNo())) {
			tv_cityno.setText("区域");
		} else {
			tv_cityno.setText(MyApplication.getInstance().getCityNo());
		}

		tv_CarLicenseNo.setText(MyApplication.getInstance().getCarLicenseNo());

		tv_Chassis.setText(MyApplication.getInstance().getChassis());
		tv_EngineNo.setText(MyApplication.getInstance().getEngineNo());

		if (TextUtils.isEmpty(MyApplication.getInstance().getCartime())) {
			but_CarTime.setText(initDate);
		} else {
			but_CarTime.setText(MyApplication.getInstance().getCartime());
		}
	}

	@Override
	protected void initEvents() {
		img_Back.setOnClickListener(this);
		but_CarTime.setOnClickListener(this);
		tv_province.setOnClickListener(this);
		tv_cityno.setOnClickListener(this);
		but_models.setOnClickListener(this);
		but_brand.setOnClickListener(this);
		but_cars.setOnClickListener(this);

		tv_Chassis.setOnClickListener(this);
		tv_EngineNo.setOnClickListener(this);
		tv_CarLicenseNo.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		/** 返回 */
		case R.id.view_top_img_back:
			defaultFinish();
			break;

		/** 选择品牌 */
		case R.id.Mycars_but_brand:
			intent = new Intent(MycarsActivity.this, BrandAdapter.class);
			startActivityForResult(intent, ComConfig.INTENT_BRAND_CARS);

			break;
		/** 选择车系 */
		case R.id.Mycars_but_cars:
			intent = new Intent(MycarsActivity.this, BrandAdapter.class);
			startActivityForResult(intent, ComConfig.INTENT_BRAND_CARS);
			break;
		/** 获取车型 */

		case R.id.Mycar_but_modles:
			intent = new Intent(MycarsActivity.this, CarTypeActivity.class);
			startActivityForResult(intent, ComConfig.INTENT_MODELS);
			break;
		/** 获取上牌时间 */
		case R.id.mycar_but_cartime:
			MyCarTimeDialog myCarTimeDialog = new MyCarTimeDialog(
					MycarsActivity.this, initDate);
			myCarTimeDialog.datePicKDialog(but_CarTime);
			myCarTimeDialog.toast("请选择正确的上牌时间?");
			System.out.println("mCarTime" + mCarTime);
			break;
		/** 选择车牌号 */
		/** 简称 */
		case R.id.mycar_but_province:
			carType.getCarLocation(v, tv_province);
			mProvince = tv_province.getText().toString().trim();
			MyApplication.getInstance().saveLicenseNo(null, mProvince, null,
					null);
			System.out.println("mProvince" + mProvince);
			break;
		/** 地区 */
		case R.id.mycar_but_city:
			carType.getCarLetters(v, tv_cityno);

			mCityNo = tv_cityno.getText().toString().trim();
			MyApplication.getInstance()
					.saveLicenseNo(null, null, mCityNo, null);
			System.out.println("mCityNo" + mCityNo);
			break;
		/** 数字 */
		case R.id.mycar_LicenseNo:
			intent = new Intent(this, DataFillAdapter.class);
			intent.putExtra("title", "号码");
			startActivityForResult(intent, ComConfig.INTENT_LICENSE_NUMBER);
			break;
		/** 车架号 */
		case R.id.mycar_Chassis:
			intent = new Intent(MycarsActivity.this, DataFillAdapter.class);
			intent.putExtra("title", "车架号");
			startActivityForResult(intent, ComConfig.INTENT_CHASSIS_NUMBER);
			break;
		/** 发动机号 */
		case R.id.mycar_EngineNo:
			intent = new Intent(MycarsActivity.this, DataFillAdapter.class);
			intent.putExtra("title", "发动机号");
			startActivityForResult(intent, ComConfig.INTENT_ENGINE_NO);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		System.out.println("1=" + requestCode + "-" + resultCode + "-" + data);
		switch (requestCode) {
		case ComConfig.INTENT_BRAND_CARS:
			System.out.println("2=" + resultCode);
			if (data != null) {
				but_brand.setText(data.getStringExtra("province"));
				but_cars.setText(data.getStringExtra("city"));
				mBrand = but_brand.getText().toString().trim();
				mCars = but_cars.getText().toString().trim();
				MyApplication.getInstance().saveMessage(mBrand, mCars, mModels,
						mChassis, mEngineNo, mCarTime);
			}
			break;

		case ComConfig.INTENT_MODELS:
			System.out.println("3=" + resultCode);
			if (data != null) {
				but_models.setText(data.getStringExtra("province"));
				mModels = but_models.getText().toString().trim();
				MyApplication.getInstance().saveMessage(mBrand, mCars, mModels,
						mChassis, mEngineNo, mCarTime);
			}
			break;

		case ComConfig.INTENT_CHASSIS_NUMBER:

			if (TextUtils.isEmpty(data.getStringExtra("text"))) {
				tv_Chassis.setText(MyApplication.getInstance().getChassis());
			} else {
				tv_Chassis.setText(data.getStringExtra("text"));
				mChassis = tv_Chassis.getText().toString().trim();
				MyApplication.getInstance().saveMessage(mBrand, mCars, mModels,
						mChassis, mEngineNo, mCarTime);
			}

			break;

		case ComConfig.INTENT_ENGINE_NO:
			if (TextUtils.isEmpty(data.getStringExtra("text"))) {
				tv_EngineNo.setText(MyApplication.getInstance().getEngineNo());
			} else {
				tv_EngineNo.setText(data.getStringExtra("text"));
				mEngineNo = tv_EngineNo.getText().toString().trim();
				MyApplication.getInstance().saveMessage(mBrand, mCars, mModels,
						mChassis, mEngineNo, mCarTime);
			}
			break;

		case ComConfig.INTENT_LICENSE_NUMBER:
			String numberString = data.getStringExtra("text");
			if (TextUtils.isEmpty(numberString)) {
				tv_CarLicenseNo.setText(MyApplication.getInstance()
						.getCarLicenseNo());
			} else if (numberString.length() > 5) {
				toast("填写错误!");
			} else {
				tv_CarLicenseNo.setText(numberString);
				mCarLicenseNo = tv_CarLicenseNo.getText().toString().trim();
				mProvince = tv_province.getText().toString().trim();
				mCityNo = tv_cityno.getText().toString().trim();
				mLicenseNo = mProvince + mCityNo + mCarLicenseNo;
				/** 储存车牌号信息 */
				MyApplication.getInstance().saveLicenseNo(mLicenseNo,
						mProvince, mCityNo, mCarLicenseNo);
			}
			String message = "licenseno" + mLicenseNo;
			@SuppressWarnings("unused")
			MessageContent messageContent = new MessageContent(message,
					MyApplication.getInstance().getUserPhone(), 123);
		}
	}
}
