package activity.fragments.cheshenghuo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.adapter.LocalInspectionAdapter;
import cn.ieauto.autogroup.android.entity.LocalInspectionEntity;

/**
 * 代开委托书
 * 
 * @author Administrator
 * 
 */
public class OpenproxyFragment extends Fragment implements OnClickListener {
	/** ListView */
	private ListView listview;
	String mPicture = null;
	Bitmap bitmap;

	private Activity mActivity;
	private View view;
	private LocalInspectionAdapter mAdapter;
	private List<LocalInspectionEntity> list_data;
	private String[] name = { "修改", "好好", "好店", "好好店", "好店" };
	private String[] jl = { "面议", "200", "300", "500", "100" };

	private Button of_btn_ok;
	private TextView localinspection_button1;
	private TextView ai_tv_evaluation;
	private LinearLayout ai_ll_distance;
	private LinearLayout ai_ll_evaluation;
	private Intent intent;

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		this.mActivity = activity;
	}

	// /** 集合数据 */
	// public ArrayList<Merchants> mListData;
	//
	// final MessageContent LocalInspectionMessage = new MessageContent("",
	// "", 3180);
	//
	// public void onCreate(Bundle savedInstanceState) {
	// super.onCreate(savedInstanceState);
	// this.getActivity();
	// }

	// // 加载多次
	// public void onActivityCreated(Bundle savedInstanceState) {
	// super.onActivityCreated(savedInstanceState);
	// Log.i(tag, "onActivityCreated");
	// listview = (ListView) getListView();//
	// (ListView)activity.findViewById(android.R.id.list);
	// if (mListData != null && mListData.size() > 0) {
	// return;
	// }
	// mListData = new ArrayList<Merchants>();
	// // 初始化数据
	// // AnsynHttpRequest.requestByGet(context, callbackData,
	// // R.string.http_news, url, true, true, false);
	// MyAsyncTask asyncTask = new MyAsyncTask(LocalInspectionMessage) {
	// protected void onPostExecute(String result) {
	// if ("20".equals(result)) {
	// shMg("服务器异常...");
	// return;
	// } else {
	// initStep(result);
	// Log.i("result+3", result);
	// }
	// }
	//
	// protected void onPreExecute() {
	// // showLoadingDialog("正在登录,请稍后...");
	// }
	// };
	// asyncTask.execute();
	// }
	//
	// public void shMg(String text) {
	// Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
	// }

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.vehicle_localinspection, container,
				false);
		initView();
		return view;
	}

	private void initView() {
		listview = (ListView) view.findViewById(R.id.ls_list);
		list_data = getListItems();
		mAdapter = new LocalInspectionAdapter(mActivity, list_data);
		listview.setAdapter(mAdapter);
		of_btn_ok = (Button) view.findViewById(R.id.at_btn_ok);
		of_btn_ok.setOnClickListener(this);
		localinspection_button1 = (TextView) view
				.findViewById(R.id.localinspection_button1);
		ai_tv_evaluation = (TextView) view.findViewById(R.id.ai_tv_evaluation);
		ai_tv_evaluation.setOnClickListener(this);
		ai_ll_distance = (LinearLayout) view.findViewById(R.id.ai_ll_distance);
		ai_ll_distance.setOnClickListener(this);
		ai_ll_evaluation = (LinearLayout) view
				.findViewById(R.id.ai_ll_evaluation);
		ai_ll_evaluation.setOnClickListener(this);
	}

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

	// private void initStep(String result) {
	// List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
	// try {
	// JSONObject json = new JSONObject(result);
	// JSONArray jsonText = json.getJSONArray("content");
	// String mResult2 = null;
	// JSONObject json2 = null;
	// for (int i = 0; i < jsonText.length(); i++) {
	// mResult2 = jsonText.getString(i);
	// json2 = new JSONObject(mResult2);
	// Map<String, Object> map = new HashMap<String, Object>();
	// map.put("phone", json2.getString("phone"));
	// map.put("name", json2.getString("name"));
	// map.put("rating", json2.getString("rating"));
	// map.put("price", json2.getString("price"));
	// Log.i("android", map.toString());
	// data.add(map);
	// }
	//
	// } catch (JSONException e) {
	// Log.i("JSONException", e.toString());
	//
	// }
	// // String[] from = new String[] { "phone", "name", "rating", "price" };
	// // int[] to = new int[] { R.id.vehicleownership_phone,
	// // R.id.vehicleownership_name, R.id.vehicleownership_rating,
	// // R.id.vehicleownership_price };
	// // SimpleAdapter adapter = new SimpleAdapter(getActivity(), data,
	// // R.layout.item_vehicleownership_listview, from, to);
	// // listview.setAdapter(adapter);
	// // listview.setOnItemClickListener(this);
	//
	// }
	//
	// @Override
	// public void onItemClick(AdapterView<?> parent, View view, int position,
	// long id) {
	// Intent intent = new Intent(super.context, Openproxy.class);
	// TextView tv = (TextView) view.findViewById(R.id.vehicleownership_phone);
	// Bundle bundle = new Bundle();
	// bundle.putString("phone", (tv.getText().toString()) + "");
	// startActivity(intent);
	// }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.at_btn_ok:
			intent = new Intent(mActivity, Openproxy.class);
			mActivity.startActivity(intent);
			break;
		case R.id.ai_ll_distance:
			intent = new Intent(mActivity, AddressCityActivity.class);
			startActivityForResult(intent, 1);
			break;
		case R.id.ai_ll_evaluation:
			intent = new Intent(mActivity, SequenceActivity.class);
			startActivityForResult(intent, 2);
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
			if (data != null) {
				// mLocationClient.unRegisterLocationListener(myListener);
				// String eString =data.getStringExtra("province");
				String hString = data.getStringExtra("city");
				// localinspection_button1.setText(eString);
				localinspection_button1.setText(hString);
			}
			break;
		case 2:
			if (data != null) {
				ai_tv_evaluation.setText(data.getStringExtra("province"));
			}
			break;
		default:
			break;
		}
	}

}
