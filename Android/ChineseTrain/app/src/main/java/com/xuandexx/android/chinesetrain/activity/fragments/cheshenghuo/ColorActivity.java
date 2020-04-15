package activity.fragments.cheshenghuo;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.app.MyActivity;

public class ColorActivity extends MyActivity implements OnWheelChangedListener{
	private TextView submit_address,cancel_address;
	private int current;

	// 把全国的省市区的信息以json的格式保存，解析完成后赋值为null
	private JSONObject mJsonObj;
	// 省的WheelView控件
	private WheelView mProvince;
	// 市的WheelView控件
	private WheelView mCity;
	// 区的WheelView控件
	private WheelView mArea;
	// 所有省
	private String[] mProvinceDatas;
	// key - 省 value - 市s
	private Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
	// key - 市 values - 区s
	private Map<String, String[]> mAreaDatasMap = new HashMap<String, String[]>();
	// 当前省的名称
	private String mCurrentProviceName;
	// 当前市的名称
	private String mCurrentCityName;
	// 当前区的名称
	private String mCurrentAreaName = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_address);
		initViews();
		initEvents();
	}
	@Override
	protected void initViews() {
		// 设置wheelview的大小
		Window lp = getWindow();
		lp.setGravity(Gravity.BOTTOM);
		lp.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

		initJsonData();

		submit_address=(TextView) findViewById(R.id.submit_address);
		cancel_address=(TextView) findViewById(R.id.cancel_address);
		mProvince = (WheelView) findViewById(R.id.select_city);

		initDatas();

		mProvince.setViewAdapter(new ArrayWheelAdapter<String>(this,
				mProvinceDatas));
		// 添加change事件
		mProvince.addChangingListener(this);
		mProvince.setVisibleItems(5);
		updateCities();

		
		submit_address.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.putExtra("province", mCurrentProviceName);
				setResult(1, intent);
				finish();
				
			}
		});
		cancel_address.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	protected void initEvents() {
	}
	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		if (wheel == mProvince) {
			if (newValue < mProvinceDatas.length) {
				updateCities();
			} else {
				mProvince.setCurrentItem(oldValue);
				 updateCities();
			}
		} else if (wheel == mCity) {

//			updateAreas();

		} else if (wheel == mArea) {
			mCurrentAreaName = mAreaDatasMap.get(mCurrentCityName)[newValue];
		}
	}
	/**
	 * 从assert文件夹中读取省市区的json文件，然后转化为json对象
	 */
	private void initJsonData() {
		try {
			StringBuffer sb = new StringBuffer();
			InputStream is = getAssets().open("carColor.json");
			int len = -1;
			byte[] buf = new byte[1024];
			while ((len = is.read(buf)) != -1) {
				sb.append(new String(buf, 0, len, "utf-8"));
			}
			is.close();
			mJsonObj = new JSONObject(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 解析整个Json对象，完成后释放Json对象的内存
	 */
	private void initDatas() {
		try {
			JSONArray jsonArray = mJsonObj.getJSONArray("carColorlist");
			mProvinceDatas = new String[jsonArray.length()];
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonP = jsonArray.getJSONObject(i);// 每个省的json对象
				String province = jsonP.getString("name");// 省名字

				mProvinceDatas[i] = province;
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		mJsonObj = null;
	}

	// 根据当前的省，更新市WheelView的信息
	private void updateCities() {
		int pCurrent = mProvince.getCurrentItem();
		mCurrentProviceName = mProvinceDatas[pCurrent];
	}

}
