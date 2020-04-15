package activity.fragments.cheshenghuo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.app.MyActivity;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.navi.BaiduMapAppNotSupportNaviException;
import com.baidu.mapapi.navi.BaiduMapNavigation;
import com.baidu.mapapi.navi.NaviPara;

//定图导航
public class MapActivity extends MyActivity implements OnClickListener{

	/**
	 * MapView 是地图主控件
	 */
	private MapView mMapView;
	private BaiduMap mBaiduMap;
	private Button button1;
	
	//返回
	private ImageView img_back;
	private TextView view_top_tv_title;

	// 定位相关
	public MyLocationListenner myListener = new MyLocationListenner();
	private LocationClient mLocationClient;
	boolean isFirstLoc = true;// 是否首次定位
	private LocationMode mCurrentMode;
	private BitmapDescriptor mCurrentMarker;
	
	private double jin;
	private double wei;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gas_station);
		initViews();
		initEvents();
		initOverlay();
	}
	
	/**
	 * 添加覆盖物
	 */
	public void initOverlay(){
		mBaiduMap = mMapView.getMap();
		MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(14.0f);
		mBaiduMap.setMapStatus(msu);
		//initOverlay();
		/************************* 添加标记物***************************/
		// 定义Maker坐标点：深圳龙华民治 纬度：22.626603;经度：114.046639;
		double lat=31.245426;
		double log=121.50626;
		LatLng p = new LatLng(lat,log);
		// 构建Marker图标

		/******************************添加自定义标记物*************************/
		// 创建InfoWindow展示的view		 
		View v = LayoutInflater.from(this).inflate(R.layout.item_map,null); 
		TextView tv_jinru=(TextView)v.findViewById(R.id.tv_jinru);
		TextView text = (TextView) v.findViewById(R.id.marktext); 
		text.setText("上海东方明珠");	
		
		//创建InfoWindow 
		InfoWindow mInfoWindow = new InfoWindow(v, p,0); 
		//显示InfoWindow 
		mBaiduMap.showInfoWindow(mInfoWindow);
		MapStatusUpdate u2 = MapStatusUpdateFactory.newLatLng(p);//坐标为中心
		mBaiduMap.setMapStatus(u2);	
		tv_jinru.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getLocation();
				startNavi();
				
			}
		});
	}
	
	/**
	 * 开始定位
	 */
	public void getLocation(){
		/**************** 定位功能 *****************************************/
		mLocationClient = new LocationClient(getApplicationContext()); // 声明LocationClient类
		mLocationClient.registerLocationListener(myListener); // 注册监听函数

		mCurrentMarker = BitmapDescriptorFactory
				.fromResource(R.drawable.icon_geo);
		mBaiduMap
				.setMyLocationConfigeration(new MyLocationConfiguration(
						mCurrentMode, true, mCurrentMarker));
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
	 * 开始导航
	 * 
	 * @param view
	 */
	public void startNavi() {
		LatLng pt1 = new LatLng(wei,jin);
		LatLng pt2 = new LatLng(31.245426,121.50626);
		// 构建 导航参数
		NaviPara para = new NaviPara();
		para.startPoint = pt1;
		para.startName = "从这里开始";
		para.endPoint = pt2;
		para.endName = "到这里结束";

		try {

			BaiduMapNavigation.openBaiduMapNavi(para, this);

		} catch (BaiduMapAppNotSupportNaviException e) {
			e.printStackTrace();
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("您尚未安装百度地图app或app版本过低，点击确认安装？");
			builder.setTitle("提示");
			builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					BaiduMapNavigation.getLatestBaiduMapApp(MapActivity.this);
				}
			});

			builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});

			builder.create().show();
		}
	}

	public void startWebNavi() {
		LatLng pt1 = new LatLng(31.268918,121.409854);
		LatLng pt2 = new LatLng(31.255953, 121.461884);
		// 构建 导航参数
		NaviPara para = new NaviPara();
		para.startPoint = pt1;
		para.endPoint = pt2;
		BaiduMapNavigation.openWebBaiduMapNavi(para, this);
	}


	@Override
	protected void onPause() {
		// MapView的生命周期与Activity同步，当activity挂起时需调用MapView.onPause()
		mMapView.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		// MapView的生命周期与Activity同步，当activity恢复时需调用MapView.onResume()
		mMapView.onResume();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		// MapView的生命周期与Activity同步，当activity销毁时需调用MapView.destroy()
		mMapView.onDestroy();
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

//		case R.id.button1:
//			startNavi();
//			//startWebNavi();
//			break;
		case R.id.view_top_img_back:
			defaultFinish();
			break;
		default:
			break;

		}
		
	}

	@Override
	protected void initViews() {
		img_back = (ImageView) this.findViewById(R.id.view_top_img_back);
		img_back.setBackgroundResource(R.drawable.button_return);
		img_back.setOnClickListener(this);
		mMapView = (MapView) findViewById(R.id.gs_bmapView);
		view_top_tv_title=(TextView)findViewById(R.id.view_top_tv_title);
		//button1=(Button)findViewById(R.id.button1);
		view_top_tv_title.setText("百度地图");
	}

	@Override
	protected void initEvents() {
		//button1.setOnClickListener(this);
	}
	
	/**
	 * 定位SDK监听函数
	 */
	public class MyLocationListenner implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {

			wei=location.getLatitude();
			jin=location.getLongitude();
		}

		@Override
		public void onReceivePoi(BDLocation arg0) {
			// TODO Auto-generated method stub
			
		}
	}

}
