package activity.fragments.cheshenghuo;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.adapter.LocalInspectionAdapter;
import cn.ieauto.autogroup.android.app.MyActivity;
import cn.ieauto.autogroup.android.entity.LocalInspectionEntity;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

/**
 * 代办过户
 * 
 * @author Administrator
 */
/*
 * VehicleownershipActivity 我要车辆过户
 */
public class TransferAgentActivity extends MyActivity implements OnClickListener {
	private ImageView img_Back;
	private TextView tv_title;
	private LocalInspectionAdapter mAdapter;
	private List<LocalInspectionEntity> list_data;
	private String [] name = {"修改","好好","好店","好好店","好店"};
	private String [] jl = {"面议","200","300","500","100"};
	private int currentPageIndex = 0;
	private CheckBox ai_cb;
	private int checkNum; // 记录选中的条目数量  
	private Button tfa_btn_ok;
	private LinearLayout ai_ll_distance,ai_ll_evaluation,ai_ll_price;
	private TextView localinspection_button1,ai_tv_evaluation;
	private ImageView vc_img_left,vc_img_center;
	private ListView listview;
    //默认距离\城市
	private String city,px;
	public MyLocationListenner myListener = new MyLocationListenner();
	private LocationClient mLocationClient;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_transfer_agent);
		initViews();
		initEvents();
		getLocation();
	}
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_top_img_back:
			defaultFinish();
			break;
		case R.id.ai_cb:
			if (ai_cb.isChecked()) {
				for (int i = 0; i < list_data.size(); i++) {
					LocalInspectionAdapter.getIsSelected().put(i, true);
				}
				dataChanged(); 
				checkNum = list_data.size();
			}else{
				 // 遍历list的长度，将已选的按钮设为未选  
                for (int i = 0; i < list_data.size(); i++) {  
                    if (LocalInspectionAdapter.getIsSelected().get(i)) {  
                    	LocalInspectionAdapter.getIsSelected().put(i, false);  
                        checkNum--;// 数量减1  
                    }  
                }  
                // 刷新listview
                dataChanged(); 
			}
			break;
		case R.id.tfa_btn_ok:
			if (checkNum < 0) {
				toast("请选择服务商");
			}else{
				startActivity(Transfer.class);
			}
			break;
		case R.id.ai_ll_distance:
			Intent intent = new Intent(TransferAgentActivity.this, AddressCityActivity.class);			
            startActivityForResult(intent, 1);
			break;
		case R.id.ai_ll_evaluation:
			Intent seintent = new Intent(TransferAgentActivity.this, SequenceActivity.class);			
            startActivityForResult(seintent, 2);
			break;
		default:
			break;
		}
	}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
    //初始化控件
	@Override
	protected void initViews() {
		((TextView) findViewById(R.id.view_top_tv_title)).setText(R.string.transferagent_title);
		img_Back = (ImageView) this.findViewById(R.id.view_top_img_back);
		img_Back.setBackgroundResource(R.drawable.button_return);
		tv_title = (TextView) this.findViewById(R.id.view_top_tv_title);
		tv_title.setText("代办过户");
		listview = (ListView) findViewById(R.id.transferagent_listview);
		list_data = getListItems();
    	mAdapter = new LocalInspectionAdapter(TransferAgentActivity.this, list_data);
    	listview.setAdapter(mAdapter);
    	ai_cb = (CheckBox) this.findViewById(R.id.ai_cb);
    	tfa_btn_ok = (Button) this.findViewById(R.id.tfa_btn_ok);
    	
    	ai_ll_distance = (LinearLayout) this.findViewById(R.id.ai_ll_distance);
    	localinspection_button1 = (TextView) this.findViewById(R.id.localinspection_button1);
    	vc_img_left = (ImageView) this.findViewById(R.id.vc_img_left);
    	vc_img_left.setVisibility(View.VISIBLE);
    	ai_ll_evaluation = (LinearLayout) this.findViewById(R.id.ai_ll_evaluation);
    	ai_tv_evaluation = (TextView) this.findViewById(R.id.ai_tv_evaluation);
    	px = "按距离排序";
    	ai_tv_evaluation.setText(px);
    	vc_img_center = (ImageView) this.findViewById(R.id.vc_img_center);
    	vc_img_center.setVisibility(View.VISIBLE);
    	ai_ll_price = (LinearLayout) this.findViewById(R.id.ai_ll_price);
    	ai_ll_price.setVisibility(View.GONE);
	}
    //自定义数据
	private List<LocalInspectionEntity> getListItems() {
		List<LocalInspectionEntity> data = new ArrayList<LocalInspectionEntity>();
		for (int i = 0; i < name.length; i++) {
			LocalInspectionEntity entity = new LocalInspectionEntity();
			entity.setName(name[i]);
			entity.setMonery(jl[i]);
			data.add(entity);
		}
		return data;
	}
	//控件点击事件
	@Override
	protected void initEvents() {
		img_Back.setOnClickListener(this);
		ai_cb.setOnClickListener(this);
		tfa_btn_ok.setOnClickListener(this);
		ai_ll_distance.setOnClickListener(this);
		ai_ll_evaluation.setOnClickListener(this);
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
			localinspection_button1.setText("城市:"+newCity[0]);
		}

		@Override
		public void onReceivePoi(BDLocation arg0) {
			// TODO Auto-generated method stub
			
		}
	}
}
