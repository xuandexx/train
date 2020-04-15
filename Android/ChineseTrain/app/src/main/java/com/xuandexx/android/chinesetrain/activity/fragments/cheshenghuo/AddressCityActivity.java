package activity.fragments.cheshenghuo;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.app.MyActivity;
//地址选择
public class AddressCityActivity extends MyActivity implements OnWheelChangedListener{

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
	@SuppressWarnings("unused")
	private String mCurrentAreaName = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_address);
		// 设置wheelview的大小
		Window lp = getWindow();
		lp.setGravity(Gravity.BOTTOM);
		lp.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		initViews();
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
		mProvince = (WheelView) findViewById(R.id.select_province);
		mCity = (WheelView) findViewById(R.id.select_city);
		mArea = (WheelView) findViewById(R.id.select_town);

		initDatas();

		mProvince.setViewAdapter(new ArrayWheelAdapter<String>(this,
				mProvinceDatas));
		// 添加change事件
		mProvince.addChangingListener(this);
		// 添加change事件
		mCity.addChangingListener(this);
		// 添加change事件
		mArea.addChangingListener(this);

		mProvince.setVisibleItems(5);
		mCity.setVisibleItems(5);
		mArea.setVisibleItems(5);
		updateCities();
		updateAreas();
		
		submit_address.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				current=mArea.getCurrentItem();
				Intent intent=new Intent();
				
				intent.putExtra("country", "中国");
				intent.putExtra("province", mCurrentProviceName);
				intent.putExtra("city", mCurrentCityName);
				//System.out.println("-------------------"+mAreaDatasMap.get(mCurrentCityName)[current]);
				if(null==mAreaDatasMap.get(mCurrentCityName)){
					
					intent.putExtra("town", " ");
				}else{
					
					intent.putExtra("town", mAreaDatasMap.get(mCurrentCityName)[current]);
				}
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

			updateAreas();

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
			InputStream is = getAssets().open("city.json");
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
			JSONArray jsonArray = mJsonObj.getJSONArray("citylist");
			mProvinceDatas = new String[jsonArray.length()];
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonP = jsonArray.getJSONObject(i);// 每个省的json对象
				String province = jsonP.getString("p");// 省名字
				mProvinceDatas[i] = province;
				if(province.equals("北京")){
					mCitisDatasMap.put(province,new String[]{"北京"});
				}else if(province.equals("天津")){
					mCitisDatasMap.put(province,new String[]{"天津"});
				}else if(province.equals("上海")){
					mCitisDatasMap.put(province,new String[]{"上海"});
				}else if(province.equals("重庆")){
					mCitisDatasMap.put(province,new String[]{"重庆"});
				}else{
	
					JSONArray jsonCs = null;
					try {
						/**
						 * Throws JSONException if the mapping doesn't exist or is
						 * not a JSONArray.
						 */
						jsonCs = jsonP.getJSONArray("c");
					} catch (Exception e1) {
						continue;
					}
					String[] mCitiesDatas = new String[jsonCs.length()];
					for (int j = 0; j < jsonCs.length(); j++) {
						JSONObject jsonCity = jsonCs.getJSONObject(j);
						String city = jsonCity.getString("n");// 市名字
						mCitiesDatas[j] = city;
						JSONArray jsonAreas = null;
						try {
							jsonAreas = jsonCity.getJSONArray("a");
						} catch (Exception e) {
							continue;
						}
						
						String[] mAreasDatas = new String[jsonAreas.length()];// 当前市的所有区
						for (int k = 0; k < jsonAreas.length(); k++) {
							String area = jsonAreas.getJSONObject(k).getString("s");// 区域的名称
							mAreasDatas[k] = area;
						}
						mAreaDatasMap.put(city, mAreasDatas);
					}
	
					
					mCitisDatasMap.put(province, mCitiesDatas);
				}
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
		String[] cities = mCitisDatasMap.get(mCurrentProviceName);
		if (cities == null) {
			cities = new String[] { "" };
		}
		mCity.setViewAdapter(new ArrayWheelAdapter<String>(this, cities));
		mCity.setCurrentItem(0);
		updateAreas();
	}

	/**
	 * 根据当前的市，更新区WheelView的信息
	 */
	private void updateAreas() {
		int pCurrent = mCity.getCurrentItem();
		mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
//		Toast.makeText(getApplicationContext(), mCurrentCityName+"fffffff", 0).show();
//		String[] areas = mAreaDatasMap.get(mCurrentCityName);
//		Toast.makeText(getApplicationContext(),mCurrentCityName, 0).show();
//		if (areas == null) {
//			areas = new String[] { "" };
//		}
//		mArea.setViewAdapter(new ArrayWheelAdapter<String>(this, areas));
//		mArea.setCurrentItem(0);
	}
}
