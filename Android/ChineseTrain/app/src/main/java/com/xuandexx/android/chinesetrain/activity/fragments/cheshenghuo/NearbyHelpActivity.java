package activity.fragments.cheshenghuo;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.adapter.NearbyHelpAdapter;
import cn.ieauto.autogroup.android.app.MyActivity;
import cn.ieauto.autogroup.android.entity.NearbyHelpEntity;

/**
 * 收到的附近车友求助 3110
 * 
 * @author Administrator
 * 
 */
public class NearbyHelpActivity extends MyActivity implements OnClickListener {
	private ListView listview;
	// 返回
	private ImageView img_back;
	private NearbyHelpAdapter mAdapter;
	private List<NearbyHelpEntity> list_data;
	
	
	private String icon;
	private String[] name = { "张三", "李四", "王尔曼",  };
	private String []sex={"男","女","男"};
	private String []age={"34","23","43","24","30"};
	private String[] phone = { "021-88888888", "021-88888888", "021-88888888" };
	// 距离
	private String []distance={ "500米", "500米", "500米" };
	// 求助的主要内容
	private String []theme={"事故遇险需急救！","事故遇险需急救！","事故遇险需急救！"};
	// 求助人自己填写的内容
	private String othertheme;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_nearby_help);

		initViews();
		initEvents();
	}

	@Override
	protected void initViews() {
		img_back = (ImageView) this.findViewById(R.id.view_top_img_back);
		listview = (ListView) findViewById(R.id.nearbyhelpriders_listview);
	}

	@Override
	protected void initEvents() {
		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.carlife_fargment_top_2);
		img_back.setBackgroundResource(R.drawable.button_return);
		img_back.setOnClickListener(this);
		list_data = getListItems();
		mAdapter = new NearbyHelpAdapter(this, list_data);
		listview.setAdapter(mAdapter);
	}

	private List<NearbyHelpEntity> getListItems() {
		List<NearbyHelpEntity> data = new ArrayList<NearbyHelpEntity>();
		for (int i = 0; i < name.length; i++) {
			NearbyHelpEntity rescue = new NearbyHelpEntity();
			rescue.setName(name[i]);
			rescue.setSex(sex[i]);
			rescue.setAge(age[i]);
			rescue.setPhone(phone[i]);
			rescue.setDistance(distance[i]);
			rescue.setTheme(theme[i]);
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
		}

	}

}
