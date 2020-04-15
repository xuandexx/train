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
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.adapter.LocalInspectionAdapter;
import cn.ieauto.autogroup.android.entity.LocalInspectionEntity;
import cn.ieauto.autogroup.android.entity.Merchants;


/**
 * 本地车代验:LocalInspection
 * 
 * @author LiTing
 * 
 */
public class LocalInspection extends Fragment implements OnClickListener{

	private Activity mActivity;
	private View view;
	/** ListView */
	private ListView listview;
	private LocalInspectionAdapter mAdapter;
	private List<LocalInspectionEntity> list_data;
	private String [] name = {"修改","好好","好店","好好店","好店"};
	private String [] jl = {"面议","200","300","500","100"};
	private int currentPageIndex = 0;
	private CheckBox ai_cb;
	private int checkNum; // 记录选中的条目数量  
	String mPicture = null;
	Bitmap bitmap;
	/** 集合数据 */
	public ArrayList<Merchants> mListData;
	private Button ls_btn_ok;
	private TextView localinspection_button1;
	private TextView ai_tv_evaluation;
	private LinearLayout ai_ll_distance;
	private LinearLayout ai_ll_evaluation;

//	final MessageContent LocalInspectionMessage = new MessageContent("", 
//			"", 3180);

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		this.mActivity = activity;
	}

	// 加载多次
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		view = inflater.inflate(R.layout.vehicle_localinspection, null);
	    initView();
		return view;
	}
    private void initView(){
    	listview = (ListView) view.findViewById(R.id.ls_list);
    	list_data = getListItems();
    	mAdapter = new LocalInspectionAdapter(mActivity, list_data);
    	listview.setAdapter(mAdapter);
    	ls_btn_ok = (Button) view.findViewById(R.id.at_btn_ok);
    	ls_btn_ok.setOnClickListener(this);
    	ai_cb = (CheckBox) view.findViewById(R.id.ai_cb);
    	ai_cb.setOnClickListener(this);
    	localinspection_button1=(TextView)view.findViewById(R.id.localinspection_button1);
    	ai_tv_evaluation=(TextView)view.findViewById(R.id.ai_tv_evaluation);
    	ai_tv_evaluation.setOnClickListener(this);
    	ai_ll_distance=(LinearLayout) view.findViewById(R.id.ai_ll_distance);
    	ai_ll_distance.setOnClickListener(this);
    	ai_ll_evaluation=(LinearLayout)view.findViewById(R.id.ai_ll_evaluation);
    	ai_ll_evaluation.setOnClickListener(this);
    }
    private List<LocalInspectionEntity> getListItems(){
    	List<LocalInspectionEntity> data = new ArrayList<LocalInspectionEntity>();
    	for (int i = 0; i < name.length; i++) {
    		LocalInspectionEntity entity = new LocalInspectionEntity();
    		entity.setName(name[i]);
    		entity.setMonery(jl[i]);
    		data.add(entity);
		}
		return data;
    }
//	private void initStep(String result) {
//		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
//		try {
//			System.out.println(result);
//			JSONObject json = new JSONObject(result);
//			JSONArray jsonText = json.getJSONArray("content");
//			String mResult2 = null;
//			JSONObject json2 = null;
//			Map<String, Object> map = null;
//			for (int i = 0; i < jsonText.length(); i++) {
//				mResult2 = jsonText.getString(i);
//				json2 = new JSONObject(mResult2);
//				map = new HashMap<String, Object>();
//				map.put("phone", json2.getString("phone"));
//				map.put("name", json2.getString("name"));
//				map.put("rating", json2.getString("rating"));
//				map.put("price", json2.getString("price"));
//				Log.i("android", map.toString());
//				data.add(map);
//			}
//		} catch (JSONException e) {
//			Log.i("JSONException", e.toString());
//		}
//		String[] from = new String[] { "phone", "name", "rating", "price" };
//		int[] to = new int[] { R.id.vehicleownership_phone,
//				R.id.vehicleownership_name, R.id.vehicleownership_rating,
//				R.id.vehicleownership_price };
//		SimpleAdapter adapter = new SimpleAdapter(getActivity(), data,
//				R.layout.item_vehicleownership_listview, from, to);
//		listview.setAdapter(adapter);
//	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.at_btn_ok:
			Intent intent = new Intent(mActivity,
					IwantIllegalhandldispelActivity.class);
			intent.putExtra("title", "本地车代验");
			mActivity.startActivity(intent);
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
	// 刷新listview
    private void dataChanged() {  
        // 通知listView刷新  
        mAdapter.notifyDataSetChanged();  
    };
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
