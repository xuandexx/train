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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.adapter.AssementAdapter;
import cn.ieauto.autogroup.android.app.ComConfig;
import cn.ieauto.autogroup.android.entity.CarcxEnity;
import cn.ieauto.autogroup.android.entity.ComPanyEnity;

/**
 * 收车商家 3252
 * 
 * @author Administrator
 * 
 */
public class CarMerchantFragment extends Fragment implements OnClickListener {
	Bitmap bitmap;
	private Activity mactivity;
	private View view;
	private ListView listView;
	private List<ComPanyEnity>list_data;
	private String[] name={"奥迪A7 3.2USU 11.3万里","奥迪A7 3.2USU 11.3万里","奥迪A7 3.2USU 11.3万里","奥迪A7 3.2USU 11.3万里","奥迪A7 3.2USU 11.3万里"}	;
	private String [] priece={"65万元","54万元","54万元","45万元","54万元"};
	private int currentPageIndex=0;
	private CheckBox cbox;
	private int checkNum;
	private AssementAdapter adapter;
	public ArrayList<CarcxEnity> mListData;
	private Button ls_btn_ok;
	
	private String city;
//	private TextView te_City;//城市
//	private TextView te_Px;//排序ext1
	private TextView text2,text1,text3,text4;
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		this.mactivity = activity;
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
//		decoratingmodification_loadedpond
		view = inflater.inflate(R.layout.dobucarlist,null);
		initview();
		return view;
	}

	private void initview() {
		// TODO Auto-generated method stub
		listView = (ListView) view.findViewById(R.id.abbuycar_list);
		list_data = getListItems();
		
    	adapter = new AssementAdapter(mactivity, list_data);
    	listView.setAdapter(adapter);
    	ls_btn_ok = (Button) view.findViewById(R.id.at_btnbuycar_ok);
    	ls_btn_ok.setOnClickListener(this);
    	cbox = (CheckBox) view.findViewById(R.id.aibuycar_cb);
    	cbox.setOnClickListener(this);
    	text1=(TextView)view.findViewById(R.id.text1);
    	text1.setOnClickListener(this);
    	
    	text2=(TextView)view.findViewById(R.id.text2);
    	text2.setOnClickListener(this);
    	text2.setText("选择车型");
    	
    	text3=(TextView)view.findViewById(R.id.text3);
    	text3.setOnClickListener(this);
    	
    	text4=(TextView)view.findViewById(R.id.text4);
    	text4.setOnClickListener(this);
    	
	}
	private List<ComPanyEnity>getListItems(){
		List<ComPanyEnity>data=new ArrayList<ComPanyEnity>();
		for(int i=0;i<name.length;i++){
			ComPanyEnity enity=new ComPanyEnity();
			enity.setName(name[i]);
			enity.setPriece(priece[i]);
			data.add(enity);
		}
		return data;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.at_btnbuycar_ok:
			if(list_data.isEmpty()){
				Toast.makeText(getActivity(), "列表是空的刷新试一试", 0).show();
				cbox.setChecked(false);
			}
			else if(list_data.size()==checkNum){
//				cbox.setChecked();
			
			}
			else{
				Intent intent=new Intent(mactivity,BuyToCarActivity.class);
				startActivity(intent);
			}
			break;

		case R.id.aibuycar_cb:
			if (cbox.isChecked()) {
				for (int i = 0; i < list_data.size(); i++) {
					AssementAdapter.getIsSelected().put(i, true);
				}
				dataChanged(); 
				checkNum = list_data.size();
			}else{
				 // 遍历list的长度，将已选的按钮设为未选  
                for (int i = 0; i < list_data.size(); i++) {  
                    if (AssementAdapter.getIsSelected().get(i)) {  
                    	AssementAdapter.getIsSelected().put(i, false);  
                        checkNum--;// 数量减1  
                    }  
                }  
             // 刷新listview
                dataChanged(); 
			}
			break;
		case R.id.text1:
			Intent intent1=new Intent();
			intent1.setClass(mactivity,AddressCityActivity.class);
			 startActivityForResult(intent1, ComConfig.INTENT_CITY);
			 break;
//		case R.id.ai_tv_evaluation:
//			//此处的是显示的是排序的方
		case R.id.text2:
			Intent intent2=new Intent(mactivity,CarTypeActivity.class);
			startActivityForResult(intent2, ComConfig.INTENT_MODELS);
			break;
		case R.id.text3:
			Intent intent3=new Intent(mactivity,PriectActivity.class);
			startActivityForResult(intent3, ComConfig.INTENT_PRICE_TANGE);
			break;
		case R.id.text4:
			Intent intent4=new Intent(mactivity,YearActivity.class);
			startActivityForResult(intent4, ComConfig.INTENT_YEAR_RANGE);
			break;
		default:
			break;
		}
	}
	// 刷新listview
    private void dataChanged() {  
        // 通知listView刷新  
        adapter.notifyDataSetChanged();  
    };
    //把选中的值传递
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case ComConfig.INTENT_CITY:
			if(data!=null){
//				mLocationClient.unRegisterLocationListener(myListener);
//				String eString =data.getStringExtra("province");
				String hString =data.getStringExtra("city");
//				localinspection_button1.setText(eString);
				text1.setText(hString);
			}
			break;
		case ComConfig.INTENT_MODELS:
			if(data!=null){
				text2.setText(data.getStringExtra("province"));
			}
			break;
		case ComConfig.INTENT_PRICE_TANGE:
			if(data!=null){
				text3.setText(data.getStringExtra("province"));
			}
			break;
		case ComConfig.INTENT_YEAR_RANGE:
			if(data!=null){
				text4.setText(data.getStringExtra("province"));
			}
			break;
		}
	}

}
