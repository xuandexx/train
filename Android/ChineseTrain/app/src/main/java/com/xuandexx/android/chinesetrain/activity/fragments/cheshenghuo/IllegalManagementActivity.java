package activity.fragments.cheshenghuo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.adapter.IllegalManagementAdapter;
import cn.ieauto.autogroup.android.app.MyActivity;
import cn.ieauto.autogroup.android.entity.IllegalManagementEntity;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * 违章办理
 * 
 * @author Administrator
 * 
 */
public class IllegalManagementActivity extends MyActivity implements
		OnItemClickListener, OnClickListener {
	private ImageView img_Back;
	private CheckBox checkbox;
	// private ListView listview;
	private String[] name = { "张三1", "李四1", "冉冉1", "张三2", "李四2", "冉冉2", "张三3",
			"李四3", "冉冉3", "张三4", "李四4", "冉冉4", "张三5", "李四5", "冉冉5", "张三6",
			"李四6", "冉冉6", "张三7", "李四7", "冉冉7" };
	private String[] b = { "违章办理", "代驾", "代驾", "违章办理", "代驾", "代驾", "违章办理",
			"代驾", "代驾", "违章办理", "代驾", "代驾", "违章办理", "代驾", "代驾", "违章办理", "代驾",
			"代驾", "违章办理", "代驾", "代驾" };
	private String[] m = { "50", "200", "300", "50", "200", "300", "50", "200",
			"300", "50", "200", "300", "50", "200", "300", "50", "200", "300",
			"50", "200", "300" };
	private List<IllegalManagementEntity> list_data;
	private IllegalManagementAdapter mAdapter;
	private TextView tv_illegal_qie;
	// 全选
	private CheckBox ih_cb;
	private int checkNum; // 记录选中的条目数量
	// 确定
	private Button ih_btn_ok;
	// 城市选择
	private LinearLayout ih_ll_addrees;
	private TextView ih_tv_addrees;
	private String address;
	private String city;
	public MyLocationListenner myListener = new MyLocationListenner();
	private LocationClient mLocationClient;
	private LinearLayout tv_ll_paixu;
	private TextView tv_illegal_paixu;
	private PullToRefreshListView listview;
	private int page = 0;
	private int pagesize = 6;
	private LinkedList<String> mListItems;
	List<IllegalManagementEntity> list_entity = new ArrayList<IllegalManagementEntity>();
	List<IllegalManagementEntity> list_entity_new;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_illegal_management);
		getLocation();
		initViews();
		initEvents();
	}

	private List<IllegalManagementEntity> getitems(int page) {
		list_entity_new = new ArrayList<IllegalManagementEntity>();
		// Toast.makeText(getApplicationContext(), page+"dddddd", 0).show();
		int j = name.length - page * 6;
		if (j < 6) {
			for (int i = page * 6; i < j + page * 6; i++) {
				IllegalManagementEntity entity = new IllegalManagementEntity();
				entity.setBusiness(b[i]);
				entity.setName(name[i]);
				entity.setMonery(m[i]);
				list_entity_new.add(entity);
			}
		} else {
			for (int i = page * 6; i < (page + 1) * 6; i++) {
				IllegalManagementEntity entity = new IllegalManagementEntity();
				entity.setBusiness(b[i]);
				entity.setName(name[i]);
				entity.setMonery(m[i]);
				list_entity_new.add(entity);
			}

		}
		// Toast.makeText(getApplicationContext(),
		// list_entity.size()+"ffffffff", 0).show();
		return list_entity_new;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_top_img_back:
			defaultFinish();
			break;
		case R.id.ih_btn_ok:
			Intent IIintent = new Intent(this,
					IwantIllegalhandldispelActivity.class);
			IIintent.putExtra("title", "我要消违章");
			startActivity(IIintent);
			break;
		case R.id.ih_ll_addrees:
			Intent intent = new Intent(this, AddressActivity.class);
			startActivityForResult(intent, 1);
			break;
		case R.id.ih_cb:
			if (ih_cb.isChecked()) {
				for (int i = 0; i < list_data.size(); i++) {
					IllegalManagementAdapter.getIsSelected().put(i, true);
				}
				dataChanged();
				checkNum = list_data.size();
			} else {
				// 遍历list的长度，将已选的按钮设为未选
				for (int i = 0; i < list_data.size(); i++) {
					if (IllegalManagementAdapter.getIsSelected().get(i)) {
						IllegalManagementAdapter.getIsSelected().put(i, false);
						checkNum--;// 数量减1
					}
				}
				// 刷新listview
				dataChanged();
			}
			break;
		// case R.id.ih_tv_addrees:
		//
		// break;
		case R.id.tv_illegal_qie:
			Intent intent2 = new Intent(IllegalManagementActivity.this,
					AddressCityActivity.class);
			startActivityForResult(intent2, 1);
			break;
		case R.id.tv_ll_paixu:
			Intent seintent = new Intent(IllegalManagementActivity.this,
					SequenceActivity.class);
			startActivityForResult(seintent, 2);
			break;
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 1:
			if (data != null) {
				mLocationClient.unRegisterLocationListener(myListener);
				// String eString =data.getStringExtra("province");
				String hString = data.getStringExtra("city");
				// localinspection_button1.setText(eString);
				ih_tv_addrees.setText(hString);
			}
			break;
		case 2:
			if (data != null) {
				String px = data.getStringExtra("province");
				tv_illegal_paixu.setText(px);
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TextView tv = (TextView) view.findViewById(R.id.lllegalhandl_phone);
		// String message = "phone:" + tv.getText().toString();
		// Bundle bundle = new Bundle();
		// bundle.putString("title", "我要消违章");
		// bundle.putString("phone", mPhone);
	}

	@Override
	protected void initViews() {
		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.CaraboutTabhost_Illegalhandl);
		img_Back = (ImageView) this.findViewById(R.id.view_top_img_back);
		img_Back.setBackgroundResource(R.drawable.button_return);
		checkbox = (CheckBox) findViewById(R.id.ih_cb);
		listview = (PullToRefreshListView) findViewById(R.id.illegalhandl_listview);
		ih_btn_ok = (Button) this.findViewById(R.id.ih_btn_ok);
		list_data = getitems(page);
		mAdapter = new IllegalManagementAdapter(IllegalManagementActivity.this,
				list_data);
		listview.setAdapter(mAdapter);
		ih_ll_addrees = (LinearLayout) this.findViewById(R.id.ih_ll_addrees);
		ih_ll_addrees.setOnClickListener(this);
		ih_tv_addrees = (TextView) this.findViewById(R.id.ih_tv_addrees);
		tv_illegal_qie = (TextView) findViewById(R.id.tv_illegal_qie);
		ih_cb = (CheckBox) this.findViewById(R.id.ih_cb);
		tv_ll_paixu = (LinearLayout) findViewById(R.id.tv_ll_paixu);
		tv_illegal_paixu = (TextView) findViewById(R.id.tv_illegal_paixu);

		listview.setMode(Mode.BOTH);
		listview.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载");
		listview.getLoadingLayoutProxy(false, true).setRefreshingLabel("正在加载");
		listview.getLoadingLayoutProxy(false, true).setReleaseLabel("松开加载更多");
		listview.getLoadingLayoutProxy(false, true).setLastUpdatedLabel("");
		// listview.getLoadingLayoutProxy(false, true).set
		// mListItems = new LinkedList<String>();
		// mListItems.addAll(Arrays.asList(mStrings));
	}

	@Override
	protected void initEvents() {
		ih_cb.setOnClickListener(this);
		img_Back.setOnClickListener(this);
		ih_btn_ok.setOnClickListener(this);
		ih_tv_addrees.setOnClickListener(this);
		tv_illegal_qie.setOnClickListener(this);
		tv_ll_paixu.setOnClickListener(this);
		listview.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				// getReferWenZi(refreshView);
				// String label =
				// DateUtils.formatDateTime(getApplicationContext(),
				// System.currentTimeMillis(),
				// DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE |
				// DateUtils.FORMAT_ABBREV_ALL);
				// refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
				new GetDataTask().execute();
			}

		});
	}

	// 刷新listview
	private void dataChanged() {
		// 通知listView刷新
		mAdapter.notifyDataSetChanged();
	};

	/**
	 * 开始定位
	 */
	public void getLocation() {
		/**************** 定位功能 *****************************************/
		mLocationClient = new LocationClient(getApplicationContext()); // 声明LocationClient类
		mLocationClient.registerLocationListener(myListener); // 注册监听函数

		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);
		option.setAddrType("all");// 返回的定位结果包含地址信息
		option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
		option.setScanSpan(5000);// 设置发起定位请求的间隔时间为5000ms
		option.disableCache(true);// 禁止启用缓存定位
		// option.setPoiNumber(5); // 最多返回POI个数
		option.setPoiDistance(1000); // poi查询距离
		// option.setPoiExtraInfo(true); // 是否需要POI的电话和地址等详细信息
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
			city = location.getCity();
			String[] newCity = city.split("市");
			ih_tv_addrees.setText(newCity[0]);
		}

		@Override
		public void onReceivePoi(BDLocation arg0) {
			// TODO Auto-generated method stub

		}
	}

	/**
	 * 刷新操作
	 * 
	 * @author Simon1
	 * 
	 */
	private class GetDataTask extends AsyncTask<Void, Void, String> {// 定义返回值的类型
		public GetDataTask() {

		}

		// 后台处理部分
		@Override
		protected String doInBackground(Void... params) {
			// Simulates a background job.
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			String str = "Added after refresh...I add";
			return str;
		}

		// 这里是对刷新的响应，可以利用addFirst（）和addLast()函数将新加的内容加到LISTView中
		// 根据AsyncTask的原理，onPostExecute里的result的值就是doInBackground()的返回值
		@Override
		protected void onPostExecute(String result) {
			// 在头部增加新添内容
			page = page + 1;
			getitems(page);
			list_data.addAll(list_entity_new);
			if (list_entity_new.size() <= 0) {
				listview.getLoadingLayoutProxy(false, true).setPullLabel(
						"暂无更多数据");
				listview.getLoadingLayoutProxy(false, true).setRefreshingLabel(
						"暂无更多数据");
				listview.getLoadingLayoutProxy(false, true).setReleaseLabel(
						"暂无更多数据");
				listview.getLoadingLayoutProxy(false, true)
						.setLastUpdatedLabel("");
			}

			// mListItems.addFirst(result);
			// mAdapter = new
			// IllegalManagementAdapter(IllegalManagementActivity.this,list_data);
			// 通知程序数据集已经改变，如果不做通知，那么将不会刷新mListItems的集合
			mAdapter.notifyDataSetChanged();
			// Call onRefreshComplete when the list has been refreshed.
			listview.onRefreshComplete();

			super.onPostExecute(result);// 这句是必有的，AsyncTask规定的格式
		}
	}
}