package activity.fragments.cheshenghuo;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import cn.ieauto.autogroup.android.MyApplication;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.app.MyActivity;
import cn.ieauto.autogroup.android.entity.MessageContent;
import cn.ieauto.autogroup.android.util.CarTypeKeyUtil;
import cn.ieauto.autogroup.android.util.CommonUtils;
import cn.ieauto.autogroup.android.util.MyAsyncTask;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

/**
 * 违章查询
 * 
 * @author
 * 
 */
public class ViolationQueriesActivity extends MyActivity implements
		OnClickListener {
	int mviewpos[] = new int[2];
	private Button Submit;
	private ImageView img_Back;
	private TextView tv_title;

	private EditText Number, Engineno;
	private TextView textView2;
	private TextView Query_spinner1_city;
	private TextView Query_car_spinner2;
	private TextView Query_spinner3_province;
	private TextView Query_spinner4_city;
	// ChassisNumber;
	String mCity, hpcl, mCarCity, mNumber, mEngineno = null,
	// mChassisNumber = null,
			mProvinceReferred = null, mEnglishLetters = null;
	private String city;
	public MyLocationListenner myListener = new MyLocationListenner();
	private LocationClient mLocationClient;
	DisplayMetrics metrics;
	PopupWindow pop1;
	PopupWindow pop2;
	private InputMethodManager manager;
	// 获取
	CarTypeKeyUtil carType = new CarTypeKeyUtil(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_violation_queries);
		initViews();
		initEvents();
		doMyappliaction();
		getLocation();
		// pop1 = new PopupWindow(layout,200,LayoutParams.WRAP_CONTENT);
		// pop2 = new PopupWindow(layout,200,LayoutParams.WRAP_CONTENT);
	}

	protected void initViews() {
		manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		textView2 = (TextView) findViewById(R.id.textView2);
		img_Back = (ImageView) this.findViewById(R.id.view_top_img_back);
		tv_title = (TextView) this.findViewById(R.id.view_top_tv_title);
		img_Back.setBackgroundResource(R.drawable.button_return);
		tv_title.setText("违章查询");
		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.carlife_fargment_no_3);
		Submit = (Button) findViewById(R.id.Query_button);
		img_Back.setOnClickListener(this);
		Submit.setOnClickListener(this);
		textView2.setOnClickListener(this);

		// spinner4 = (Spinner) findViewById(R.id.Query_spinner4);
		Number = (EditText) findViewById(R.id.Query_number_ed);
		Engineno = (EditText) findViewById(R.id.Query_GeneratorNo_ed);
		textView2 = (TextView) findViewById(R.id.textView2);
		Query_spinner1_city = (TextView) findViewById(R.id.Query_spinner1_city);
		Query_car_spinner2 = (TextView) findViewById(R.id.Query_car_spinner2);
		Query_spinner3_province = (TextView) findViewById(R.id.Query_spinner3_province);
		Query_spinner3_province.setOnClickListener(this);
		Query_spinner4_city = (TextView) findViewById(R.id.Query_spinner4_city);
		Query_spinner4_city.setOnClickListener(this);
	}

	protected void initEvents() {
		// 发动机号
		// Engineno.setText(mySharedpreference.getEngineno());
		Query_car_spinner2.setOnClickListener(this);
		Query_car_spinner2.setText("选择全部");
		img_Back.setBackgroundResource(R.drawable.button_return);
		tv_title.setText("违章查询");
		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.carlife_fargment_no_3);
		Number.setOnClickListener(this);
		Engineno.setOnClickListener(this);
	}

	private void doMyappliaction() {
		Query_car_spinner2.setText(MyApplication.getInstance().getModels());
		if (TextUtils.isEmpty(MyApplication.getInstance().getProvince())) {
			Query_spinner3_province.setText("省份");
		} else {
			Query_spinner3_province.setText(MyApplication.getInstance()
					.getProvince());
		}

		if (TextUtils.isEmpty(MyApplication.getInstance().getCityNo())) {
			Query_spinner4_city.setText("区域");
		} else {
			Query_spinner4_city
					.setText(MyApplication.getInstance().getCityNo());
		}

		Number.setText(MyApplication.getInstance().getCarLicenseNo());
		Engineno.setText(MyApplication.getInstance().getEngineNo());
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 1:
			if (data != null) {
				mLocationClient.unRegisterLocationListener(myListener);
				// String eString =data.getStringExtra("province");
				String hString = data.getStringExtra("city");
				// localinspection_button1.setText(eString);
				Query_spinner1_city.setText(hString);
			}
			break;
		case 2:
			if (data != null) {
				Query_car_spinner2.setText(data.getStringExtra("province"));
			}
			break;
		default:
			break;
		}
	}

	/**
	 * 开始定位
	 */
	public void getLocation() {
		/**************** 定位功能 *****************************************/
		mLocationClient = new LocationClient(getApplicationContext()); // 声明LocationClient类
		mLocationClient.registerLocationListener(myListener); // 注册监听函数

		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);
		option.setAddrType("all");// 返回的定位结果包含地址信息
		option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
		option.setScanSpan(5000);// 设置发起定位请求的间隔时间为5000ms
		option.disableCache(true);// 禁止启用缓存定位
		// option.setPoiNumber(5); // 最多返回POI个数
		option.setPoiDistance(1000); // poi查询距离
		// option.setPoiExtraInfo(true); // 是否需要POI的电话和地址等详细信息
		// 发起定位请求
		mLocationClient.setLocOption(option);
		mLocationClient.start();

	}

	/**
	 * 定位SDK监听函数
	 */
	public class MyLocationListenner implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			city = location.getCity();
			String[] newCity = city.split("市");
			Query_spinner1_city.setText(newCity[0]);
		}

		@Override
		public void onReceivePoi(BDLocation arg0) {

		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_top_img_back:
			defaultFinish();
			break;
		case R.id.Query_button:
			getYanZheng();
			// mGeneratorNo,mChassisNumber
			break;
		case R.id.textView2:
			Intent intent = new Intent(ViolationQueriesActivity.this,
					AddressCityActivity.class);
			startActivityForResult(intent, 1);
			break;
		case R.id.Query_car_spinner2:
			Intent intent2 = new Intent(ViolationQueriesActivity.this,
					CarTypeActivity.class);
			startActivityForResult(intent2, 2);
			break;
		case R.id.Query_spinner3_province:
			hideKeyboard();
			carType.getCarLocation(v, Query_spinner3_province);
			break;
		case R.id.Query_spinner4_city:
			hideKeyboard();
			carType.getCarLetters(v, Query_spinner4_city);
			break;
		}

	}

	/**
	 * 验证填写是否正确
	 */
	public void getYanZheng() {
		mNumber = Number.getText().toString().trim();
		mEngineno = Engineno.getText().toString().trim();
		// mChassisNumber = ChassisNumber.getText().toString().trim();
		if ("".equals(mNumber)) {
			toast("车牌号不能为空!");
			return;
		} else if ("".equals(mEngineno)) {
			toast("发动机号不能为空!");
			return;
		} else if (mNumber.length() > 5) {
			toast("请输入5位有效车牌号!");
			return;
		}

		String hphm = mProvinceReferred + mEnglishLetters + mNumber;
		String message = "city:" + mCity + "" + ",hpcl:" + "02" + "" + ",hphm:"
				+ hphm + "" + ",generatorno:" + mEngineno + "";
		// + ",chassisnumber:" + mChassisNumber+"";
		if (CommonUtils.isNetworkConnected(ViolationQueriesActivity.this)) {
			MessageContent loginMessage = new MessageContent(message, "", 3140);
			MyAsyncTask asyncTask = new MyAsyncTask(loginMessage) {
				protected void onPostExecute(String result) {
					dismissLoadingDialog();
					if ("20".equals(result)) {
						toast("网络连接失败");
						return;
					} else if (!"".equals(result)) {
						toast("提交成功!");
						int mStatus;
						try {
							JSONObject json = new JSONObject(result);
							Log.i("result+4", result);
							mStatus = json.getInt("status");

							if ("5000".equals(mStatus)) {
								showAlertDialog("", "请求超时，请稍后重试");
								return;
							} else if ("5001".equals(mStatus)) {
								showAlertDialog("", "交管局系统连线忙碌中，请稍后再试");
								return;
							} else if ("5002".equals(mStatus)) {
								showAlertDialog("", "恭喜，当前城市交管局暂无您的违章记录");
								return;
							} else if ("5003".equals(mStatus)) {
								showAlertDialog("", "数据异常，请重新查询");
								return;
							} else if ("5004".equals(mStatus)) {
								showAlertDialog("", "系统错误，请稍后重试");
								return;
							} else if ("5008".equals(mStatus)) {
								showAlertDialog("", "输入的车辆信息有误，请查证后重新输入");
								return;
							} else if ("2000".equals(mStatus)) {
								showAlertDialog("", "无违章纪录");
								return;
							} else if ("2001".equals(mStatus)) {
								toast("查询成功!");
								Bundle bundle = new Bundle();
								bundle.putString("result", result);
								startActivity(SearchResults.class, bundle);
								return;
							} else {
								toast("其他错误,请稍后查询");
								return;
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}
					} else {
						toast("提交失败!");
					}
				}

				protected void onPreExecute() {
					showLoadingDialog("正在提交中,请稍后...");
				}
			};
			asyncTask.execute();
		} else {
			toast("对不起，当前网络不可用!");
		}
	}

	/**
	 * 隐藏软键盘
	 */
	private void hideKeyboard() {
		if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
			if (getCurrentFocus() != null)
				manager.hideSoftInputFromWindow(getCurrentFocus()
						.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}
}
