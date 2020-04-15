package activity.fragments.cheshenghuo;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.adapter.SeekHelpAdapter;
import cn.ieauto.autogroup.android.app.MyActivity;
import cn.ieauto.autogroup.android.entity.SeekHelpEntity;

/**
 * 我要向附近车友求助/我要救援 3120
 * 
 * @author Administrator
 * 
 */
public class SeekHelpActivity extends MyActivity implements OnClickListener {
	private ImageView img_Back;
	private CheckBox checkbox;
	private ListView listview;
	private Button but_submit;
	Bitmap bitmap;
	private SeekHelpAdapter mAdapter;
	private List<SeekHelpEntity> list_data;
	private int checkNum; // 记录选中的条目数量

	private String[] name = { "张三", "李四", "王尔曼", };
	private String[] sex = { "男", "女", "男" };
	private String[] age = { "34", "23", "43" };
	private String[] phone = { "021-88888888", "021-88888888", "021-88888888" };
	// 距离
	private String[] distance = { "500米", "500米", "500米" };

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_seek_help);
		initViews();
		initEvents();
	}

	@Override
	protected void initViews() {
		img_Back = (ImageView) this.findViewById(R.id.view_top_img_back);
		img_Back.setBackgroundResource(R.drawable.button_return);
		checkbox = (CheckBox) findViewById(R.id.seekhelp_checkBox);
		listview = (ListView) findViewById(R.id.seekhelp_listview);
		but_submit = (Button) findViewById(R.id.seekhelp_but_submit);
	}

	@Override
	protected void initEvents() {
		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.iwanttohelp_title);
		img_Back.setOnClickListener(this);
		but_submit.setOnClickListener(this);
		checkbox.setOnClickListener(this);
		list_data = getListItems();
		mAdapter = new SeekHelpAdapter(this, list_data);
		listview.setAdapter(mAdapter);
		LayoutInflater factorys = LayoutInflater.from(SeekHelpActivity.this);
		final View textEntryView = factorys.inflate(R.layout.item_seekhelp,
				null);
		CheckBox chbox = (CheckBox) textEntryView
				.findViewById(R.id.item_seekhelp_checbox);
		chbox.setOnClickListener(this);
	}

	private List<SeekHelpEntity> getListItems() {
		List<SeekHelpEntity> data = new ArrayList<SeekHelpEntity>();
		for (int i = 0; i < name.length; i++) {
			SeekHelpEntity entity = new SeekHelpEntity();
			entity.setName(name[i]);
			entity.setAge(age[i]);
			entity.setSex(sex[i]);
			entity.setDistance(distance[i]);
			entity.setPhone(phone[i]);
			data.add(entity);
		}
		return data;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_top_img_back:
			defaultFinish();
			break;
		case R.id.seekhelp_but_submit:
			for (int i = 0; i < list_data.size(); i++) {
				SeekHelpAdapter.getIsSelected().get(i);
				checkNum = list_data.size();
				if (checkNum == 0) {
					toast("请选择求助对象");
					return;
				}
				startActivity(MySeekHelpActivity.class);
			}

			break;
		case R.id.seekhelp_checkBox:
			if (checkbox.isChecked()) {
				for (int i = 0; i < list_data.size(); i++) {
					SeekHelpAdapter.getIsSelected().put(i, true);
				}
				dataChanged();
				checkNum = list_data.size();
			} else {
				// 遍历list的长度，将已选的按钮设为未选
				for (int i = 0; i < list_data.size(); i++) {
					if (SeekHelpAdapter.getIsSelected().get(i)) {
						SeekHelpAdapter.getIsSelected().put(i, false);
						checkNum--;// 数量减1
					}
				}
				// 刷新listview
				dataChanged();
			}
			break;
		case R.id.item_seekhelp_checbox:
			checkbox.setChecked(false);

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
}
