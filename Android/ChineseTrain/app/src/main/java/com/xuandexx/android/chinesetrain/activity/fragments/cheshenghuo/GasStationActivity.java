package activity.fragments.cheshenghuo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.ieauto.autogroup.android.R;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.InfoWindow.OnInfoWindowClickListener;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.SupportMapFragment;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

public class GasStationActivity extends FragmentActivity implements
		OnGetPoiSearchResultListener {
	private PoiSearch mPoiSearch = null;
	private BaiduMap mBaiduMap = null;
	private int load_Index = 0;
	// 进行定位的功能
	private LocationClient locationClient;
	public MyLocationListenner myListener = new MyLocationListenner();
	boolean first = true;
	private LocationMode locationMode;
	private BitmapDescriptor bitmapDescriptor;
	private MapView mapView;
	private LatLng latLng;
	private GeoCoder geoCoder;// 进行经纬度的互相转换
	double late;
	double lon;
	
	private TextView tvTextView;
	private ImageView img_back;
	private InfoWindow mInfoWindow;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gas_poi_station);
		// 初始化搜索模块，注册搜索事件监听
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		mPoiSearch = PoiSearch.newInstance();
		mPoiSearch.setOnGetPoiSearchResultListener(this);

		mBaiduMap = ((SupportMapFragment) (getSupportFragmentManager()
				.findFragmentById(R.id.map))).getBaiduMap();

		img_back = (ImageView) findViewById(R.id.view_top_img_back);
		img_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		tvTextView = (TextView) findViewById(R.id.view_top_tv_title);
		tvTextView.setText("附近加油站");
		// 定位的
		mapView = (MapView) findViewById(R.id.gs_bmapView);
		mBaiduMap = mapView.getMap();
		mBaiduMap.setMyLocationEnabled(true);
		// 定位初始化
		locationClient = new LocationClient(this);
		locationClient.registerLocationListener(myListener);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);
		option.setAddrType("all");
		option.setCoorType("bd09ll");
		option.disableCache(true);// 禁止启用缓存定位
		option.setPoiNumber(5); // 最多返回POI个数
		option.setPoiDistance(2000); // poi查询距离
		option.setPoiExtraInfo(true); // 是否需要POI的电话和地址等详细信息
		locationClient.setLocOption(option);
		locationClient.start();
	
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onDestroy() {

		// 退出时销毁定位
		locationClient.stop();
		// 关闭定位图层
		mBaiduMap.setMyLocationEnabled(false);
		mapView.onDestroy();
		mPoiSearch.destroy();
		super.onDestroy();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
	}

	public void shareAddr(View view) {
		// 发起反地理编码请求
		geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));
	}
	public void onGetPoiResult(PoiResult result) {
		if (result == null
				|| result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
			Toast.makeText(GasStationActivity.this, "未找到结果", Toast.LENGTH_LONG)
					.show();
			return;
		}
		if (result.error == SearchResult.ERRORNO.NO_ERROR) {
			mBaiduMap.clear();
			PoiOverlay overlay = new MyPoiOverlay(mBaiduMap);
			mBaiduMap.setOnMarkerClickListener(overlay);
			overlay.setData(result);
			overlay.addToMap();
			overlay.zoomToSpan();
			return;
		}
		if (result.error == SearchResult.ERRORNO.AMBIGUOUS_KEYWORD) {

			// 当输入关键字在本市没有找到，但在其他城市找到时，返回包含该关键字信息的城市列表
			String strInfo = "在";
			for (CityInfo cityInfo : result.getSuggestCityList()) {
				strInfo += cityInfo.city;
				strInfo += ",";
			}
			strInfo += "找到结果";
			Toast.makeText(GasStationActivity.this, strInfo, Toast.LENGTH_LONG)
					.show();
		}
	}
	public void onGetPoiDetailResult(final PoiDetailResult result) {
		final View v=LayoutInflater.from(this).inflate(R.layout.item_map_you, null);
		TextView tv_jinru=(TextView)v.findViewById(R.id.tv_jinru);
		final TextView text = (TextView) v.findViewById(R.id.marktext);
		//Sun
		mBaiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {
			public boolean onMarkerClick(final Marker marker) {
				OnInfoWindowClickListener listener = null;
		
				if (result.error != SearchResult.ERRORNO.NO_ERROR) {
					text.setText("抱歉，未找到结果");
				} else {
					text.setText(result.getName());
				}
				listener = new OnInfoWindowClickListener() {
					public void onInfoWindowClick() {
						mBaiduMap.hideInfoWindow();
					}
				};
				LatLng ll = marker.getPosition();
				mInfoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(v), ll, -47, listener);
				mBaiduMap.showInfoWindow(mInfoWindow);

				return true;
			}
		});
		
//		if (result.error != SearchResult.ERRORNO.NO_ERROR) {
//			Toast.makeText(getApplicationContext(),"抱歉，未找到结果",Toast.LENGTH_LONG).show();
//			text.setText("抱歉，未找到结果");
//		} else {
//			Toast.makeText(getApplicationContext(), result.getName(),Toast.LENGTH_LONG).show();
//			text.setText(result.getName());
//		}
		
	}

	private class MyPoiOverlay extends PoiOverlay {

		public MyPoiOverlay(BaiduMap baiduMap) {
			super(baiduMap);
		}

		@Override
		public boolean onPoiClick(int index) {
			super.onPoiClick(index);
			PoiInfo poi = getPoiResult().getAllPoi().get(index);
			// if (poi.hasCaterDetails) {
			mPoiSearch.searchPoiDetail((new PoiDetailSearchOption())
					.poiUid(poi.uid));
			// }
			return true;
		}
	}

	public class MyLocationListenner implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// map view 销毁后不在处理新接收的位置
			if (location == null || mapView == null)
				// Double X = location.getLatitude() * 1E6;
				// Double Y = location.getLongitude() * 1E6;
				return;
			MyLocationData locData = new MyLocationData.Builder()
					.accuracy(location.getRadius())
					// 此处设置开发者获取到的方向信息，顺时针0-360
					.direction(100).latitude(location.getLatitude())
					.longitude(location.getLongitude()).build();
			mBaiduMap.setMyLocationData(locData);
			if (first) {
				first = false;
				late = location.getLatitude();
				lon = location.getLongitude();
				latLng = new LatLng(late, lon);
				latLng = new LatLng(location.getLatitude(),
						location.getLongitude());
				MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(latLng);
				mBaiduMap.animateMapStatus(u);
				PoiNearbySearchOption op = new PoiNearbySearchOption();
				op.keyword("加油站");
				op.location(latLng);
				op.pageCapacity(5);
				op.radius(2000);
				mPoiSearch.searchNearby(op);
			}
		}
		@Override
		public void onReceivePoi(BDLocation arg0) {
			// TODO Auto-generated method stub

		}

	}
}
