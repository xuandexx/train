package activity.fragments.cheshenghuo;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.adapter.RepairAdapter;
import cn.ieauto.autogroup.android.app.ComConfig;
import cn.ieauto.autogroup.android.app.MyActivity;
import cn.ieauto.autogroup.android.entity.RepairEntity;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

/**
 * 维修保养
 * 
 * @author Administrator
 * 
 */
public class MRActivity extends MyActivity implements OnClickListener,
		OnItemClickListener {
//	private Spinner spinner1, spinner2, spinner3;
//	String mCompanyName = null, mPhone = null, mAddress = null;
//	String mResult;
//	Bitmap bitmap;
//	String mPicture = null;
	private ImageView img_Back;
	private ListView listview;
	private RepairAdapter mAdapter;
	private List<RepairEntity> list_data;

	private String [] name = {"好好修理店","好好修理店","好好修理店","好好修理店","好好修理店"};
	private String [] jl = {"500米","500米","500米","500米","500米"};
	private String [] addres = {"上海市浦东大道880号","上海市浦东大道880号","上海市浦东大道880号","上海市浦东大道880号","上海市浦东大道880号"};
	private TextView  doedkoe;
	private String city;
	private String poxjl;//城市 .距离
	private TextView ih_tv_addrees;
	public MyLocationListenner myListener = new MyLocationListenner();
	private LocationClient mLocationClient;
	private TextView mrar_px;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_m_r);
//		Bundle bundle = this.getIntent().getExtras();
//		mResult = bundle.getString("result");
//		Log.i("result", mResult);
		initViews();
//		initStup();
		// PageTask pageTask = new PageTask();
		// pageTask.execute();
		getLocation();
	}

//	class PageTask extends AsyncTask<String, Integer, String> {
//
//		protected String doInBackground(String... params) {
//			bitmap = ImageUtils.getBitmap(mPicture);
//			return null;
//		}
//
//		@Override
//		protected void onPostExecute(String result) {
//			// but_layout.setImageBitmap(bitmap);
//		}
//
//	}
	@Override
	protected void initViews() {
		
		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.maintenance_title);
		listview = (ListView) findViewById(R.id.maintenance_listview);
		img_Back = (ImageView) this.findViewById(R.id.view_top_img_back);
		img_Back.setBackgroundResource(R.drawable.button_return);
//		spinner1 = (Spinner) findViewById(R.id.maintenance_spinner1);
//		spinner2 = (Spinner) findViewById(R.id.maintenance_spinner2);
//		spinner3 = (Spinner) findViewById(R.id.maintenance_spinner3);
		img_Back.setOnClickListener(this);
        list_data = getListItems();
        mAdapter = new RepairAdapter(MRActivity.this, list_data);
        listview.setAdapter(mAdapter);
        doedkoe=(TextView)findViewById(R.id.doedkoe);
        doedkoe.setOnClickListener(this);
        ih_tv_addrees=(TextView)findViewById(R.id.ih_tv_addrees);
        mrar_px=(TextView)findViewById(R.id.mrar_px);
        mrar_px.setOnClickListener(this);
    	poxjl = "按距离排序";
    	mrar_px.setText(poxjl);
	}
	 private List<RepairEntity> getListItems(){
	    	List<RepairEntity> data = new ArrayList<RepairEntity>();
	    	for (int i = 0; i < name.length; i++) {
	    		RepairEntity entity = new RepairEntity();
	    		entity.setShop_name(name[i]);
	    		entity.setShop_distance(jl[i]);
	    		entity.setShop_daaress(addres[i]);
	    		data.add(entity);
			}
			return data;
	    }
//	private void initStup() {
//
//		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
//
//		try {
//			JSONObject json = new JSONObject(mResult);
//			JSONArray jsonText = json.getJSONArray("content");
//			String mResult2 = null;
//			JSONObject json2 = null;
//			for (int i = 0; i < jsonText.length(); i++) {
//				mResult2 = jsonText.getString(i);
//				json2 = new JSONObject(mResult2);
//				Map<String, Object> map = new HashMap<String, Object>();
//				// "picture", "companyname", "distance","address", "phone"
//				mPhone = json2.getString("phone");
//				Log.i("res", mPhone);
//
//				mCompanyName = json2.getString("companyname");
//				mPhone = json2.getString("phone");
//				mAddress = json2.getString("address");
//
//				// mPicture = json2.getString("picture");
//
//				// map.put("picture",ImageUtils.
//				// convertBitmapToDrawable(bitmap));
//				map.put("companyname", mCompanyName);
//				map.put("distance", json2.getString("distance"));
//				map.put("address", mAddress);
//				map.put("phone", mPhone);
//				data.add(map);
//			}
//
//		} catch (JSONException e) {
//			doLog(e.toString());
//
//		}
//
//		if (mPhone == null) {
//			defaultFinish();
//			return;
//		}
//
////		String[] from = new String[] { "picture", "companyname", "distance",
////				"address", "phone" };
////		int[] to = new int[] { R.id.automotiveservices_item_imageView1,
////				R.id.name_shop, R.id.sex, R.id.signature,
////				R.id.automotiveservices_item_phone };
////		SimpleAdapter adapter = new SimpleAdapter(this, data,
////				R.layout.item_carabouttabhost, from, to);
////		listview.setAdapter(adapter);
////
////		listview.setOnItemClickListener(this);
//
//		SpinnerAdapter adapter1 = new SpinnerAdapter(this,
//				R.id.maintenance_spinner1, Ruser.string.CitiesSort);
//		spinner1.setAdapter(adapter1);
//		SpinnerAdapter adapter2 = new SpinnerAdapter(this,
//				R.id.maintenance_spinner2, Ruser.string.SelectRegion);
//		spinner2.setAdapter(adapter2);
//		SpinnerAdapter adapter3 = new SpinnerAdapter(this,
//				R.id.maintenance_spinner3, Ruser.string.STRING);
//		spinner3.setAdapter(adapter3);
//
//	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_top_img_back:
			defaultFinish();
			break;
		case R.id.doedkoe:
			Intent intent=new Intent();
			intent.setClass(MRActivity.this,AddressCityActivity.class);
			 startActivityForResult(intent, ComConfig.INTENT_CITY);
			 break;
		case R.id.mrar_px:
			//选择排序的方式
			Intent intnet=new Intent();
			intnet.setClass(MRActivity.this,SequenceActivity.class);
            startActivityForResult(intnet, ComConfig.INTENT_SORT_BY);
			break;
		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//		TextView tv = (TextView) arg1
//				.findViewById(R.id.automotiveservices_item_phone);
//		String message = "phone:" + tv.getText().toString();
//		Bundle bundle = new Bundle();
//		bundle.putString("companyname", mCompanyName);
//		bundle.putString("phone", mPhone);
//		bundle.putString("address", mAddress);
//		DataAcquisitionUtil.DataAcquisition(message, 135, MRActivity.this,
//				SerciveCompanyActivity.class);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case  ComConfig.INTENT_CITY:
			if(data!=null){
				mLocationClient.unRegisterLocationListener(myListener);
//				String eString =data.getStringExtra("province");
				String hString =data.getStringExtra("city");
//				localinspection_button1.setText(eString);
				ih_tv_addrees.setText(hString);
			}
			break;
		case ComConfig.INTENT_SORT_BY:
			if(data!=null){
				poxjl =data.getStringExtra("province");
				mrar_px.setText(poxjl);
			}
			break;  
		}
	}
	/**
	 * 开始定位
	 */
	public void getLocation(){
		/**************** 定位功能 *****************************************/
		mLocationClient = new LocationClient(getApplicationContext()); // 声明LocationClient类
		mLocationClient.registerLocationListener(myListener); // 注册监听函数

		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);
		option.setAddrType("all");// 返回的定位结果包含地址信息
		option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
		option.setScanSpan(5000);// 设置发起定位请求的间隔时间为5000ms
		option.disableCache(true);// 禁止启用缓存定位
		//option.setPoiNumber(5); // 最多返回POI个数
		option.setPoiDistance(1000); // poi查询距离
		//option.setPoiExtraInfo(true); // 是否需要POI的电话和地址等详细信息
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
			city=location.getCity();
			String[] newCity=city.split("市");
			ih_tv_addrees.setText(newCity[0]);
		}

		@Override
		public void onReceivePoi(BDLocation arg0) {
			// TODO Auto-generated method stub
			
		}
	}

	@Override
	protected void initEvents() {
		
	}
}
