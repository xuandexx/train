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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.adapter.DrivingAdapter;
import cn.ieauto.autogroup.android.entity.DrivingSchoolEntity;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

/**
 * 陪练 3270
 * 
 * @author Administrator
 * 
 */
public class DrivingFragment extends Fragment implements OnClickListener{
	private ListView listview;
	private Activity mActivity;
	private View view;
	private DrivingAdapter mAdapter;
	private List<DrivingSchoolEntity> list_data;
	private String [] name = {"谔谔","天天","等等"};
	private String [] addres = {"上海市","上海市","上海市"};
	private String [] phone = {"宝样路1234号","长宁路1245号","金沙江路25号"};
    private LinearLayout ai_ll_price;
    //全选
  	private CheckBox df_cb;
  	private int checkNum; // 记录选中的条目数量  
  	//城市选择
  	private LinearLayout ai_ll_distance,ai_ll_evaluation;
  	private TextView localinspection_button1,ai_tv_evaluation;
  	private ImageView vc_img_left,vc_img_center;
  	//默认距离\城市
  	private String city,px;
  	public MyLocationListenner myListener = new MyLocationListenner();
	private LocationClient mLocationClient;
	//确定
	private Button sd_btn_ok;
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.mActivity = activity;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.sparrdriving_sparring, null);
		initView();
		getLocation();
		return view;
	}

	private void initView() {
		ai_ll_price = (LinearLayout) view.findViewById(R.id.ai_ll_price);
		ai_ll_price.setVisibility(View.GONE);
		listview = (ListView) view.findViewById(R.id.sd_list);
		list_data = getListItems();
		mAdapter = new DrivingAdapter(mActivity, list_data);
		listview.setAdapter(mAdapter);
		df_cb = (CheckBox) view.findViewById(R.id.ai_cb);
		df_cb.setOnClickListener(this);
		
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
		
		sd_btn_ok = (Button) view.findViewById(R.id.sd_btn_ok);
		sd_btn_ok.setOnClickListener(this);
	}

	private List<DrivingSchoolEntity> getListItems() {
		List<DrivingSchoolEntity> data = new ArrayList<DrivingSchoolEntity>();
		for (int i = 0; i < name.length; i++) {
			DrivingSchoolEntity rescue = new DrivingSchoolEntity();
			rescue.setName(name[i]);
			rescue.setAddress(addres[i]);
			rescue.setRegional(phone[i]);
			data.add(rescue);
		}
		return data;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ai_cb:
			if (df_cb.isChecked()) {
				for (int i = 0; i < list_data.size(); i++) {
					DrivingAdapter.getIsSelected().put(i, true);
				}
				dataChanged(); 
				checkNum = list_data.size();
			}else{
				 // 遍历list的长度，将已选的按钮设为未选  
                for (int i = 0; i < list_data.size(); i++) {  
                    if (DrivingAdapter.getIsSelected().get(i)) {  
                    	DrivingAdapter.getIsSelected().put(i, false);  
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
		case R.id.sd_btn_ok:
			Toast.makeText(mActivity, "发送成功", Toast.LENGTH_SHORT).show();
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
