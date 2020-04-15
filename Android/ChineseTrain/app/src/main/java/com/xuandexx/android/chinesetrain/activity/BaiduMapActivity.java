/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package activity;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import cn.ieauto.autogroup.android.app.MyActivity;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.BDNotifyListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

@SuppressLint("HandlerLeak")
public class BaiduMapActivity extends MyActivity {
	private LocationClient mLocationClient;
	private NotiftLocationListener listener;
	private double longtitude, latitude;
	private NotifyLister mNotifyLister;
	// private Vibrator mVibrator;
	private String address;
	private LocationClientOption option;

	@SuppressWarnings("unused")
	private Handler notifyHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			mNotifyLister = new NotifyLister();
			mNotifyLister
					.SetNotifyLocation(latitude, longtitude, 3000, "gcj02");// 4个参数代表要位置提醒的点的坐标，具体含义依次为：纬度，经度，距离范围，坐标系类型(gcj02,gps,bd09,bd09ll)
			mLocationClient.registerNotify(mNotifyLister);
		}

	};

	private void backToParentView() {
		Intent aintent = new Intent();
		aintent.putExtra("latitude", latitude);
		aintent.putExtra("longitude", longtitude);
		aintent.putExtra("address", address);
		setResult(RESULT_OK, aintent); // 这理有2个参数(int resultCode, Intent intent)
		finish();
	}

	public class NotiftLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// Receive Location
			longtitude = location.getLongitude();
			latitude = location.getLatitude();
			address = location.getAddrStr();
			backToParentView();
			// notifyHandler.sendEmptyMessage(0);

		}

		@Override
		public void onReceivePoi(BDLocation arg0) {
			if (arg0 != null) {
				address = arg0.getAddrStr();
				Toast.makeText(getApplication(), address, Toast.LENGTH_LONG)
						.show();
				backToParentView();
			}
		}
	}

	public class NotifyLister extends BDNotifyListener {
		public void onNotify(BDLocation mlocation, float distance) {
			// mVibrator.vibrate(1000);//振动提醒已到设定位置附近
			Toast.makeText(BaiduMapActivity.this, "震动提醒", Toast.LENGTH_SHORT)
					.show();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		listener = new NotiftLocationListener();
		// mVibrator = (Vibrator) getApplicationContext().getSystemService(
		// Service.VIBRATOR_SERVICE);
		getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
		option = new LocationClientOption();
		// 需要地址信息，设置为其他任何值（string类型，且不能为null）时，都表示无地址信息。
		option.setAddrType("all");
		// 设置是否返回POI的电话和地址等详细信息。默认值为false，即不返回POI的电话和地址信息。
		option.setPoiExtraInfo(true);

		mLocationClient = new LocationClient(this);
		mLocationClient.registerLocationListener(listener);
		mLocationClient.start();
		// mLocationClient.requestPoi();
		mLocationClient.setLocOption(option);
	}

	@Override
	protected void onStop() {
		super.onStop();
		mLocationClient.stop();
		mLocationClient.removeNotifyEvent(mNotifyLister);
		mLocationClient = null;
		mNotifyLister = null;
		listener = null;

	}

	@Override
	protected void initViews() {

	}

	@Override
	protected void initEvents() {

	}
}
