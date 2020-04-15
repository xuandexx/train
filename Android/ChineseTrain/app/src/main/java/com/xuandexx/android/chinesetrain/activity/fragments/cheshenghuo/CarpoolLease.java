package activity.fragments.cheshenghuo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.adapter.CarpoolLeaseAdapter;
import cn.ieauto.autogroup.android.entity.CarpoolLeaseEntity;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

/**
 * 私车租借 3240
 * 
 * @author Administrator
 * 
 */
public class CarpoolLease extends Fragment implements OnClickListener{
	
	private Activity mActivity;
	private ListView listview;
	private CarpoolLeaseAdapter mAdapter;
	private List<CarpoolLeaseEntity> list_data;
	private String [] name = {"宝马 750","奥迪 A7","奔驰 s300","奥迪 A7","奔驰 s300"};
	private String [] jl = {"自动档 沪A","自动档 沪A","自动档 沪A","自动档 沪A","自动档 沪A"};
	private String [] addres = {"车主自驾","车主自驾","车主自驾","车主自驾","车主自驾"};
	private String [] phone = {"2009年","2003年","2006年","2008年","2012年"};
	private String [] time = {"自由时间","自由时间","星期六","自由时间","自由时间"};
	//全选
	private CheckBox er_cb;
	private int checkNum; // 记录选中的条目数量
	//城市选择
  	private LinearLayout ai_ll_distance,ai_ll_evaluation;
  	private TextView localinspection_button1,ai_tv_evaluation;
  	private ImageView vc_img_left,vc_img_center;
  	//默认距离\城市
  	private String city,px;
	public MyLocationListenner myListener = new MyLocationListenner();
	private LocationClient mLocationClient;
    @Override
    public void onAttach(Activity activity) {
    	// TODO Auto-generated method stub
    	super.onAttach(activity);
    	this.mActivity = activity;
    }
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.carcarpool_privatel, null);
		initView(view);
		getLocation();
		return view;
	}

	private void initView(View view) {
		
		listview = (ListView) view.findViewById(R.id.sp_list);
		list_data = getListItems();
		mAdapter = new CarpoolLeaseAdapter(mActivity, list_data);
		listview.setAdapter(mAdapter);
		er_cb = (CheckBox) view.findViewById(R.id.ai_cb);
		er_cb.setOnClickListener(this);
		
		ai_ll_distance = (LinearLayout) view.findViewById(R.id.ai_ll_distance);
		ai_ll_distance.setOnClickListener(this);
		localinspection_button1 = (TextView) view.findViewById(R.id.localinspection_button1);
		vc_img_left = (ImageView) view.findViewById(R.id.vc_img_left);
		vc_img_left.setVisibility(View.VISIBLE);
		
		ai_ll_evaluation = (LinearLayout) view.findViewById(R.id.ai_ll_evaluation);
		ai_ll_evaluation.setOnClickListener(this);
		ai_tv_evaluation = (TextView) view.findViewById(R.id.ai_tv_evaluation);
		px = "按距离排序";
    	ai_tv_evaluation.setText(px);
		vc_img_center = (ImageView) view.findViewById(R.id.vc_img_center);
		vc_img_center.setVisibility(View.VISIBLE);
	}
	 private List<CarpoolLeaseEntity> getListItems(){
	    	List<CarpoolLeaseEntity> data = new ArrayList<CarpoolLeaseEntity>();
	    	for (int i = 0; i < name.length; i++) {
	    		CarpoolLeaseEntity rescue = new CarpoolLeaseEntity();
	    		rescue.setPp(name[i]);
	    		rescue.setPz(jl[i]);
	    		rescue.setZj(addres[i]);
	    		rescue.setDate(phone[i]);
	    		rescue.setTime(time[i]);
	    		data.add(rescue);
			}
			return data;
	    }
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ai_cb:
			if (er_cb.isChecked()) {
				for (int i = 0; i < list_data.size(); i++) {
					CarpoolLeaseAdapter.getIsSelected().put(i, true);
				}
				dataChanged(); 
				checkNum = list_data.size();
			}else{
				 // 遍历list的长度，将已选的按钮设为未选  
                for (int i = 0; i < list_data.size(); i++) {  
                    if (CarpoolLeaseAdapter.getIsSelected().get(i)) {  
                    	CarpoolLeaseAdapter.getIsSelected().put(i, false);  
                        checkNum--;// 数量减1  
                    }  
                }  
                // 刷新listview
                dataChanged(); 
			}
			break;
		case R.id.ai_ll_distance:
			Intent intent = new Intent(mActivity, AddressCityActivity.class);			
            startActivityForResult(intent, 1);
			break;
		case R.id.ai_ll_evaluation:
			Intent aiintent = new Intent(mActivity, SequenceActivity.class);			
            startActivityForResult(aiintent, 2);
			break;
		default:
			break;
		}
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 1:
			if(data!=null){
				mLocationClient.unRegisterLocationListener(myListener);
				String hString =data.getStringExtra("city");
				localinspection_button1.setText("城市:"+hString);
			}
			break;
		case 2:
			if(data!=null){
				px =data.getStringExtra("province");
				ai_tv_evaluation.setText(px);
			}
			break;

		default:
			break;
		}
	}
	// 刷新listview
    private void dataChanged() {  
        // 通知listView刷新  
        mAdapter.notifyDataSetChanged();  
    };
    /**
	 * 开始定位
	 */
	public void getLocation(){
		/**************** 定位功能 *****************************************/
		mLocationClient = new LocationClient(mActivity.getApplicationContext()); // 声明LocationClient类
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
			localinspection_button1.setText("城市:"+newCity[0]);
		}

		@Override
		public void onReceivePoi(BDLocation arg0) {
			// TODO Auto-generated method stub
			
		}
	}
}
