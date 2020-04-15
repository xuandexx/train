package activity.fragments.wo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.app.MyActivity;

/**
 * 我的收藏
 * 
 * @author Administrator
 * 
 */
public class MyCollectionActivity extends MyActivity implements OnClickListener {
	private ImageView img_Back;
	private ListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.beautifulcar);
		initViews();
		initEvents();
	}

	@Override
	protected void initViews() {

		img_Back = (ImageView) this.findViewById(R.id.view_top_img_back);
		list = (ListView) findViewById(R.id.mycollection_list);

		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.my_fargment_collect);
	}

	@Override
	protected void initEvents() {

		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		Calendar calendar = Calendar.getInstance();
		map.put("avatar", R.drawable.of_line_face);
		map.put("name", "屋里人");
		map.put("time",
				String.valueOf(calendar.get(Calendar.YEAR) + "-"
						+ calendar.get(Calendar.MONTH) + "-"
						+ calendar.get(Calendar.DAY_OF_MONTH)));
		map.put("picture", R.drawable.of_line_face);
		map.put("content", "我看见你了");
		data.add(map);
		String[] from = new String[] { "avatar", "name", "time", "picture",
				"content" };
		int[] to = new int[] { R.id.item_mycollection_img_avatar,
				R.id.item_mycollection_tv_name, R.id.item_mycollection_tv_time,
				R.id.item_mycollection_img_picture,
				R.id.item_mycollection_tv_content };
		SimpleAdapter adapter = new SimpleAdapter(this, data,
				R.layout.item_mycollection, from, to);
		list.setAdapter(adapter);
		img_Back.setBackgroundResource(R.drawable.button_return);
		img_Back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_top_img_back:
			defaultFinish();
			break;
		}
	}

}
