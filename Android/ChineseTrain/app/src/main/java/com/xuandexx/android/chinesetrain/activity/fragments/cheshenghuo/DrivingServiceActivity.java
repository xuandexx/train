package activity.fragments.cheshenghuo;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.adapter.DrivingServiceAdapter;
import cn.ieauto.autogroup.android.app.MyActivity;
import cn.ieauto.autogroup.android.entity.DrivingServiceEntity;

/**
 * 找代驾
 * 
 * @author Administrator
 * 
 */
public class DrivingServiceActivity extends MyActivity implements
		OnClickListener{
	String mName = null, mPhone = null, mAge = null;
	String mResult;
	Bitmap bitmap;
	String mPicture = null;
	
	private ImageView img_Back;
	private List<DrivingServiceEntity> list_data;
	private DrivingServiceAdapter mAdapter;
	private ListView listview;
	private CheckBox ds_cb;
	private int checkNum; // 记录选中的条目数量  
	private Button dsa_btn_ok;
	
	private String [] name = {"张三","李四","人人","天天","妍妍"};
	private String [] jl = {"100米","200米","500米","800米","1500米"};
	private String [] age = {"45","58","33","20","66"};
	private String [] jialing = {"15","20","11","0","39"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_driving_service);
		initViews();
		initEvents();
	}


	private List<DrivingServiceEntity> getListItems() {
		List<DrivingServiceEntity> data = new ArrayList<DrivingServiceEntity>();
		for (int i = 0; i < name.length; i++) {
			DrivingServiceEntity rescue = new DrivingServiceEntity();
			rescue.setName(name[i]);
			rescue.setDistance(jl[i]);
			rescue.setAge(age[i]);
			rescue.setDrivingexperience(jialing[i]);
			data.add(rescue);
		}
		return data;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_top_img_back:
			defaultFinish();
			break;
		case R.id.ds_cb:
			if (ds_cb.isChecked()) {
				for (int i = 0; i < list_data.size(); i++) {
					DrivingServiceAdapter.getIsSelected().put(i, true);
				}
				dataChanged(); 
				checkNum = list_data.size();
			}else{
				 // 遍历list的长度，将已选的按钮设为未选  
                for (int i = 0; i < list_data.size(); i++) {  
                    if (DrivingServiceAdapter.getIsSelected().get(i)) {  
                    	DrivingServiceAdapter.getIsSelected().put(i, false);  
                        checkNum--;// 数量减1  
                    }  
                }  
                // 刷新listview
                dataChanged(); 
			}
			break;
		case R.id.dsa_btn_ok:
			Intent intent = new Intent(DrivingServiceActivity.this, DrivingServiceActy.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	@Override
	protected void initViews() {
		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.findsubstitutedriv_tirle);
		listview = (ListView) findViewById(R.id.findsubstitutedriv_listview);
		img_Back = (ImageView) this.findViewById(R.id.view_top_img_back);
		img_Back.setBackgroundResource(R.drawable.button_return);
		img_Back.setOnClickListener(this);
		list_data = getListItems();
		mAdapter = new DrivingServiceAdapter(DrivingServiceActivity.this,list_data);
		listview.setAdapter(mAdapter);
		ds_cb = (CheckBox) this.findViewById(R.id.ds_cb);
		dsa_btn_ok = (Button) this.findViewById(R.id.dsa_btn_ok);
	}

	@Override
	protected void initEvents() {
		img_Back.setOnClickListener(this);
		ds_cb.setOnClickListener(this);
		dsa_btn_ok.setOnClickListener(this);
	}
	 // 刷新listview
    private void dataChanged() {  
        // 通知listView刷新  
        mAdapter.notifyDataSetChanged();  
    };
}
