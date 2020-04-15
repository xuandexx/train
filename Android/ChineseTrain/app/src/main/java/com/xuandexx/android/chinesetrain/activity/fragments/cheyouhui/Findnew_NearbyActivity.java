package activity.fragments.cheyouhui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.activity.FriendInfoActivity;
import cn.ieauto.autogroup.android.app.MyActivity;

/**
 * 查找附近的车友
 * 
 * @author Administrator
 * 
 */
public class Findnew_NearbyActivity extends MyActivity {
	private ImageView img_Back;
	private ListView listview;
	private Intent intent;

	// private ImageView imageview ;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.findnew_nearby);
		initView();
		initStep();
	}

	private void initView() {
		img_Back = (ImageView) findViewById(R.id.view_top_img_back);
		img_Back.setBackgroundResource(R.drawable.button_return);
		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.findnew_nearby_title);
		listview = (ListView) findViewById(R.id.findnew_nearby_listview);
		img_Back.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				defaultFinish();
			}
		});

	}

	private void initStep() {
		List<Map<String, Object>> data = getdata();
		String[] from = new String[] { "picturcs", "name", "sax", "signature" };
		int[] to = new int[] { R.id.findnew_nearby_imageView1,
				R.id.findnew_nearby_tv1, R.id.findnew_nearby_tv2,
				R.id.findnew_nearby_tv3 };
		SimpleAdapter adapter = new SimpleAdapter(this, data,
				R.layout.item_findnew_nearby, from, to);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(listviewlistener);
		/*
		 * SimpleAdapter.ViewBinder binder = new SimpleAdapter.ViewBinder() {
		 * 
		 * @Override public boolean setViewValue(View view, Object data, String
		 * textRepresentation) { if (view instanceof Button) { final View
		 * buttonview = view; //
		 * button.setBackgroundDrawable(getResources().getDrawable
		 * (R.drawable.ic_launcher)); view.setOnClickListener(new
		 * OnClickListener() { LinearLayout listItem = (LinearLayout)
		 * buttonview.getParent(); ImageView imageview = (ImageView)
		 * listItem.findViewById(R.id.findnew_nearby_imageView1); Button
		 * button1=(Button)listItem.findViewById(R.id.findnew_nearby_button1);
		 * 
		 * @Override public void onClick(View v) {
		 * CommonUtils.toast(Findnew_NearbyActivity.this, "text"); switch
		 * (v.getId()) { case R.id.findnew_nearby_imageView1: // Intent
		 * intent=new
		 * Intent(Findnew_NearbyActivity.this,Passme_PersonalActivity.class); //
		 * startActivity(intent); break; }
		 * 
		 * } }); return false; } return false; } };
		 * adapter.setViewBinder(binder);
		 */

	}

	private List<Map<String, Object>> getdata() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("picturcs", R.drawable.of_line_face);
		map.put("name", "12321");
		map.put("sax", "Ů");
		map.put("signature", "rejhgiohgiohjgio");
		list.add(map);
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("picturcs", R.drawable.of_line_face);
		map1.put("name", "12321");
		map1.put("sax", "��");
		map1.put("signature", "rejhgiohgiohjgio");
		list.add(map1);
		return list;
	}

	private OnItemClickListener listviewlistener = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			intent = new Intent(Findnew_NearbyActivity.this,
					FriendInfoActivity.class);
			startActivity(intent);
		}
	};

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initEvents() {
		// TODO Auto-generated method stub
		
	}
}
