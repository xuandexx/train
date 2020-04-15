package activity;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.MyApplication;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.activity.fragments.business.AgreeProtocolActivity;
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.AddressCityActivity;
import cn.ieauto.autogroup.android.adapter.BrandAdapter;
import cn.ieauto.autogroup.android.app.ComConfig;
import cn.ieauto.autogroup.android.app.MyActivity;
import cn.ieauto.autogroup.android.httpTask.PersonalRequest;
import cn.ieauto.autogroup.android.util.CommonUtils;
import cn.ieauto.autogroup.android.util.MD5;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMContact;
import com.easemob.exceptions.EaseMobException;

/**
 * 补充基本品牌，车系信息
 * 
 * @author Simon
 */
public class RegisterSecondActivity extends MyActivity {

	// baiduMap
	private static LocationClient mLocationClient = null;
	private BDLocationListener myListener = new MyLocationListener();
	// 事件控件
	private static Button buttonSubmit, buttonSwitchCity, buttonAutoSeries,
			buttonAutoBrand;
	//
	private static String spinner_tv, user_Id;
	private static ImageView imageViewBack;// 后退键
	private static TextView textViewResgisterCity;
	private static boolean flace;

	private static CheckBox checkboxProtocol;

	private static String registCity;
	Intent intent;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_second);
		initViews();
		initEvents();
		getLocation();
	}

	@Override
	protected void initViews() {
		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.cheyouxin);
		// 返回键
		imageViewBack = (ImageView) findViewById(R.id.view_top_img_back);
		imageViewBack.setBackgroundResource(R.drawable.button_return);
		// 品牌车系
		buttonAutoBrand = (Button) findViewById(R.id.btn_resgister2_brand);
		buttonAutoSeries = (Button) findViewById(R.id.btn_resgister2_series);
		// 城市显示
		textViewResgisterCity = (TextView) findViewById(R.id.tv_resgister2_city);
		// 城市选择
		buttonSwitchCity = (Button) findViewById(R.id.btn_resgister2_switch_city);
		// 提交资料
		buttonSubmit = (Button) findViewById(R.id.btn_resgister2_submit);
		// 协议同意
		checkboxProtocol = (CheckBox) findViewById(R.id.chk_resgister2_protocol);
		user_Id = MyApplication.getInstance().getUserSystemID();
	}

	@Override
	protected void initEvents() {
		imageViewBack.setOnClickListener(ocl);
		buttonAutoBrand.setOnClickListener(ocl);
		buttonAutoSeries.setOnClickListener(ocl);
		buttonSwitchCity.setOnClickListener(ocl);
		buttonSubmit.setOnClickListener(ocl);
		checkboxProtocol.setOnClickListener(ocl);
	}

	public OnClickListener ocl = new OnClickListener() {
		@Override
		public void onClick(View v) {

			switch (v.getId()) {
			case R.id.view_top_img_back:
				defaultFinish();
				break;
			case R.id.btn_resgister2_brand:
				intent = new Intent(RegisterSecondActivity.this,
						BrandAdapter.class);
				startActivityForResult(intent, ComConfig.INTENT_BRAND_CARS);
				break;
			case R.id.btn_resgister2_series:
				intent = new Intent(RegisterSecondActivity.this,
						BrandAdapter.class);
				startActivityForResult(intent, ComConfig.INTENT_BRAND_CARS);
				break;
			case R.id.btn_resgister2_switch_city:
				intent = new Intent(RegisterSecondActivity.this,
						AddressCityActivity.class);
				startActivityForResult(intent, ComConfig.INTENT_CITY);
				break;
			case R.id.chk_resgister2_protocol:
				flace = true;
				break;
			case R.id.btn_resgister2_perfection:
				startActivity(AgreeProtocolActivity.class);
				break;
			case R.id.btn_resgister2_submit:
				if (!flace == true) {
					toast("阅读并同意使用条款和隐私政策");
					return;
				} else if (!"".equals(spinner_tv)) {
					registCity = spinner_tv;
				} else {
					registCity = textViewResgisterCity.getText().toString();
				}

				// 调用注册
				doregister();
				break;
			default:
				break;
			}
		}
	};

	private void doregister() {
		if (!CommonUtils.isNetworkConnected(this)) {
			toast("对不起，当前网络不可用!");
			return;
		}
		/** 如果返回结果为注册成功,存储记录到Shared preference中,便于登陆时直接获得 */
		new Thread(new Runnable() {
			@Override
			public void run() {
				// result:注册第二步,服务器返回值
				String result = null;
				try {
					result = PersonalRequest.register_second(user_Id,
							buttonAutoBrand.getText().toString(),
							buttonAutoSeries.getText().toString(), registCity);
					doLog(result);
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					e.printStackTrace();
				}
				// huanxinID,通过用户手机号码生成的MD5
				String huanxinID = MD5.GetMD5Code(MyApplication.getInstance()
						.getUserPhone());
				// 如果result是OK,注册环信帐号
				try {
					EMChatManager.getInstance().createAccountOnServer(
							huanxinID,
							MyApplication.getInstance().getPassword());
				} catch (EaseMobException e) {
					e.printStackTrace();
				}
				// 注册成功以后,设置昵称
				EMContact contact = new EMContact(huanxinID);
				// 昵称应该是服务器返回一个自增长的ID,随用户数量增加而增加,作为一个默认昵称
				contact.setNick("车友信" + huanxinID);
				// TODO 返回给服务器环信帐号和昵称
				// 如果提交成功,跳转到登录界面
				startActivity(LoginActivity.class);
				defaultFinish();
			}
		}).start();

	}

	/**************** 定位功能 *****************************************/
	private void getLocation() {
		mLocationClient = new LocationClient(getApplicationContext()); // 声明LocationClient类
		mLocationClient.registerLocationListener(myListener); // 注册监听函数
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);
		option.setAddrType("all");// 返回的定位结果包含地址信息
		option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
		option.setScanSpan(5000);// 设置发起定位请求的间隔时间为5000ms
		option.disableCache(true);// 禁止启用缓存定位
		option.setPoiDistance(1000); // poi查询距离
		// 发起定位请求
		mLocationClient.setLocOption(option);
		mLocationClient.start();
	}

	/** 定位SDK监听函数 */
	public class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location == null)
				return;
			registCity = location.getCity();
			String[] newCity = registCity.split("市");
			textViewResgisterCity.setText(newCity[0]);
		}

		@Override
		public void onReceivePoi(BDLocation arg0) {
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case ComConfig.INTENT_CITY:
			if (data != null) {
				mLocationClient.unRegisterLocationListener(myListener);
				String hString = data.getStringExtra("city");
				textViewResgisterCity.setText(hString);
			}
			break;

		case ComConfig.INTENT_BRAND_CARS:
			if (data != null) {
				buttonAutoBrand.setText(data.getStringExtra("province"));
				buttonAutoSeries.setText(data.getStringExtra("city"));
			}
			break;

		default:
			break;
		}
	}

	@Override
	protected void onStop() {
		mLocationClient.stop();
		super.onStop();
	}
}
