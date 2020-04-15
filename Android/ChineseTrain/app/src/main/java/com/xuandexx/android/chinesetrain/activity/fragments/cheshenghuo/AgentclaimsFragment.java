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
import android.widget.Spinner;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.adapter.AgentclaimsAdapter;
import cn.ieauto.autogroup.android.entity.InsuranceEntity;
import cn.ieauto.autogroup.android.entity.Merchants;


/**
 * 代办理赔
 * 3201
 * @author Administrator
 * 
 */
public class AgentclaimsFragment extends Fragment implements OnClickListener{
	private Activity mActivity;
	private Spinner spinner1, spinner2;
	private ListView listview;
	String mPhone = null, mCompanyName = null;
	Bitmap bitmap;
	private AgentclaimsAdapter mAdapter;
	private List<InsuranceEntity> list_data;
	private String [] name = {"修改","好好","好店","好好店","好店"};
	private String [] jl = {"面议","200","300","500","100"};
	private TextView localinspection_button1;
	private TextView ai_tv_evaluation;
	private LinearLayout ai_ll_distance;
	private LinearLayout ai_ll_evaluation;
	private Button ls_btn_ok;
	
	/** 集合数据 */
	public ArrayList<Merchants> mListData;

//	final MessageContent LocalInspectionMessage = new MessageContent("", 
//			"", 3201);
//
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		Log.i(tag, "onCreate");
//	}
//
//	// 加载多次
//	public void onActivityCreated(Bundle savedInstanceState) {
//		super.onActivityCreated(savedInstanceState);
//		Log.i(tag, "onActivityCreated");
//		listview = (ListView) getListView();// (ListView)activity.findViewById(android.R.id.list);
//		if (mListData != null && mListData.size() > 0) {
//			return;
//		}
//		mListData = new ArrayList<Merchants>();
//		// 初始化数据
//		// AnsynHttpRequest.requestByGet(context, callbackData,
//		// R.string.http_news, url, true, true, false);
//		MyAsyncTask asyncTask = new MyAsyncTask(LocalInspectionMessage) {
//			protected void onPostExecute(String result) {
//				if ("20".equals(result)) {
//					shMg("服务器异常...");
//					return;
//				} else {
//					initStep(result);
//					Log.i("result", result);
//				}
//			}
//
//			protected void onPreExecute() {
//				// showLoadingDialog("正在登录,请稍后...");
//			}
//		};
//		asyncTask.execute();
//	}
//
//	public void shMg(String text) {
//		Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
//	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.auto_agentclaims, null);
		initView(view);
		return view;
	}

	private void initView(View view) {
		ls_btn_ok = (Button) view.findViewById(R.id.at_btn_ok);
    	ls_btn_ok.setOnClickListener(this);
		listview=(ListView) view.findViewById(R.id.auto_lv_list);
		localinspection_button1=(TextView)view.findViewById(R.id.localinspection_button1);
    	ai_tv_evaluation=(TextView)view.findViewById(R.id.ai_tv_evaluation);
    	ai_tv_evaluation.setOnClickListener(this);
    	ai_ll_distance=(LinearLayout) view.findViewById(R.id.ai_ll_distance);
    	ai_ll_distance.setOnClickListener(this);
    	ai_ll_evaluation=(LinearLayout)view.findViewById(R.id.ai_ll_evaluation);
    	ai_ll_evaluation.setOnClickListener(this);
		list_data = getListItems();
    	mAdapter = new AgentclaimsAdapter(mActivity, list_data);
    	listview.setAdapter(mAdapter);
	}

	private List<InsuranceEntity> getListItems(){
    	List<InsuranceEntity> data = new ArrayList<InsuranceEntity>();
    	for (int i = 0; i < name.length; i++) {
    		InsuranceEntity entity = new InsuranceEntity();
    		entity.setName(name[i]);
    		entity.setMonery(jl[i]);
    		data.add(entity);
		}
		return data;
}
	private void initStep(String result) {
//		SpinnerAdapter adapter1 = new SpinnerAdapter(getActivity(),
//				R.id.auto_agentclaims_spinner1, Ruser.string.City);
//		spinner1.setAdapter(adapter1);
//		SpinnerAdapter adapter2 = new SpinnerAdapter(getActivity(),
//				R.id.auto_agentclaims_spinner2, Ruser.string.STRING02);
//		spinner2.setAdapter(adapter2);
//		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
//		try {
//			JSONObject json = new JSONObject(result);
//			JSONArray jsonText = json.getJSONArray("content");
//			String mResult2 = null;
//			JSONObject json2 = null;
//			for (int i = 0; i < jsonText.length(); i++) {
//				Log.i("result+4", result);
//				mResult2 = jsonText.getString(i);
//				json2 = new JSONObject(mResult2);
//				Map<String, Object> map = new HashMap<String, Object>();
//				// "picture", "phone","name", "age", "driving", "price"
//				mPhone = json2.getString("phone");
//				mCompanyName = json2.getString("companyname");
//				Log.i("res", mPhone);
//
//				map.put("phone", mPhone);
//				map.put("companyname", mCompanyName);
//				map.put("rating", json2.getString("rating"));
//				map.put("distance", json2.getString("distance"));
//
//				data.add(map);
//			}
//
//		} catch (JSONException e) {
//			Log.i("JSONException", e.toString());
//
//		}
//
//		String[] from = new String[] { "phone", "companyname", "rating",
//				"distance" };
//		int[] to = new int[] { R.id.automotiveservices_item_phone,
//				R.id.name_shop, R.id.signature,
//				R.id.automotiveservices_item_rmb };
//		SimpleAdapter adapter = new SimpleAdapter(getActivity(), data,
//				R.layout.item_carabouttabhost, from, to);
//		listview.setAdapter(adapter);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.at_btn_ok:
			Intent intent = new Intent(mActivity,
					IwantIllegalhandldispelActivity.class);
			intent.putExtra("title", "代办理赔");
			mActivity.startActivity(intent);
			break;
		case R.id.ai_ll_distance:
			Intent intent2 = new Intent(mActivity,AddressCityActivity.class);			
            startActivityForResult(intent2, 1);
			break;
		case R.id.ai_ll_evaluation:
			Intent intent3 = new Intent(mActivity,SequenceActivity.class);			
	        startActivityForResult(intent3, 2);
	        break;
		default:
			break;
		}
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		this.mActivity = activity;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 1:
			if(data!=null){
//				mLocationClient.unRegisterLocationListener(myListener);
//				String eString =data.getStringExtra("province");
				String hString =data.getStringExtra("city");
//				localinspection_button1.setText(eString);
				localinspection_button1.setText(hString);
			}
			break;
		case 2:
			if(data!=null){
				ai_tv_evaluation.setText(data.getStringExtra("province"));
			}
			break;
		default:
			break;
		}
	}

}
