package activity.fragments.cheshenghuo;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.entity.Merchants;

/**
 * 拼 车 3242
 * 
 * @author Administrator
 * 
 */
public class Carpool extends Fragment {
	private Spinner spinner1, spinner2, spinner3;
	private ListView listview;
	private Button button;
	private EditText ed;
	String mPhone = null;
	Bitmap bitmap;
	String mPicture = null;

	/** 集合数据 */
	public ArrayList<Merchants> mListData;
//
//	final MessageContent LocalInspectionMessage = new MessageContent("", 
//			"", 3242);
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
		View view = inflater.inflate(R.layout.carcarpool_carpool, null);
		view.findViewById(R.id.tv_carpool);
		//initView(view);
		return view;
	}

//	private void initView(View view) {
//		spinner1 = (Spinner) view.findViewById(R.id.carpool_spinner1);
//		spinner2 = (Spinner) view.findViewById(R.id.carpool_spinner2);
//		spinner3 = (Spinner) view.findViewById(R.id.carpool_spinner3);
//		button = (Button) view.findViewById(R.id.carpool_button1);
//		ed = (EditText) view.findViewById(R.id.carpool_editText1);
//		button.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				String mEd=ed.getText().toString().trim();
//			}
//		});
//	}

//	private void initStep(String result) {
//		SpinnerAdapter adapter1 = new SpinnerAdapter(getActivity(),
//				R.id.carpool_spinner1, Ruser.string.CitiesSort);
//		spinner1.setAdapter(adapter1);
//
//		SpinnerAdapter adapter2 = new SpinnerAdapter(getActivity(),
//				R.id.carpool_spinner2, Ruser.string.BikersType);
//		spinner2.setAdapter(adapter2);
//
//		SpinnerAdapter adapter3 = new SpinnerAdapter(getActivity(),
//				R.id.carpool_spinner3, Ruser.string.CarpoolType);
//		spinner3.setAdapter(adapter3);
//
//		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
//		try {
//			JSONObject json = new JSONObject(result);
//			JSONArray jsonText = json.getJSONArray("content");
//			String mResult2 = null;
//			JSONObject json2 = null;
//			for (int i = 0; i < jsonText.length(); i++) {
//				mResult2 = jsonText.getString(i);
//				json2 = new JSONObject(mResult2);
//				Map<String, Object> map = new HashMap<String, Object>();
//				// "pictures","phone", "name", "car",
//				// "carpooltype","directions", "departuretime"
//				mPhone = json2.getString("phone");
//				Log.i("res", mPhone);
//				map.put("phone", mPhone);
//				map.put("name", json2.getString("name"));
//				map.put("carpooltype", json2.getString("carpooltype"));
//				map.put("car", json2.getString("car"));
//				map.put("directions", json2.getString("directions"));
//				map.put("tv", "出发时间：");
//				map.put("departuretime", json2.getString("departuretime"));
//
//				data.add(map);
//			}
//
//		} catch (JSONException e) {
//			Log.i("JSONException", e.toString());
//
//		}

//		String[] from = new String[] { "pictures", "phone", "name", "car",
//				"carpooltype", "directions", "tv", "departuretime" };
//		int[] to = new int[] { R.id.item_leasingc_imageView1,
//				R.id.item_leasingc_phone, R.id.item_leasingc_name,
//				R.id.item_leasingc_textView1, R.id.item_leasingc_textView2,
//				R.id.item_leasingc_type, R.id.item_leasingc_Departuretime,
//				R.id.item_leasingc_car };
//		SimpleAdapter adapter = new SimpleAdapter(getActivity(), data,
//				R.layout.item_leasingc_listview, from, to);
//		listview.setAdapter(adapter);
//	}

//	@Override
//	public void onListItemClick(ListView l, View v, int position, long id) {
//		super.onListItemClick(l, v, position, id);
//	}

}
