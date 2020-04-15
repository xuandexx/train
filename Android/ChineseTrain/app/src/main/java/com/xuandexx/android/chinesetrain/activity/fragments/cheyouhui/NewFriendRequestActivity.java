package activity.fragments.cheyouhui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.app.MyActivity;

/**
 * 新朋友验证请求
 * 
 * @author Lampo
 * 
 */
public class NewFriendRequestActivity extends MyActivity {
	private ImageView img_Back;
	private ListView listview;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chat_authentication_request);
		initViews();
	}

	@Override
	protected void initViews() {
		img_Back = (ImageView) findViewById(R.id.view_top_img_back);
		img_Back.setBackgroundResource(R.drawable.button_return);
		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.authenticationrequest_ar);
		img_Back.setOnClickListener(butlistener);
		listview = (ListView) findViewById(R.id.authenticationrequest_listview);
		/**
		 * listView 适配器
		 */
		List<Map<String, Object>> data = getdata();
		String[] from = new String[] { "picturcs", "name" };
		int[] to = new int[] { R.id.chat_messag_face, R.id.chat_messag_title };
		SimpleAdapter adapter = new SimpleAdapter(this, data,
				R.layout.adapter_chat_message, from, to);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(listener);
	}

	private List<Map<String, Object>> getdata() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("picturcs", R.drawable.of_line_face);
		map.put("name", "1312");
		list.add(map);
		return list;
	}

	private OnItemClickListener listener = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			startActivity(CheckRequestActivity.class);
		}
	};

	private OnClickListener butlistener = new OnClickListener() {
		public void onClick(View v) {
			defaultFinish();
		}
	};

	@Override
	protected void initEvents() {
		// TODO Auto-generated method stub

	}
}
