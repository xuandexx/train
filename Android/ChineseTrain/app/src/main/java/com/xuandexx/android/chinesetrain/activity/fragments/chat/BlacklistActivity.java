package activity.fragments.chat;

import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.app.MyActivity;

import com.easemob.chat.EMContactManager;
import com.easemob.exceptions.EaseMobException;

/**
 * 黑名单列表页面
 * 
 */
public class BlacklistActivity extends MyActivity {
	private ListView listView;
	private BlacklistAdapater adapter;
	private ImageView img_back;
	private TextView tv_title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_black_list);
		initViews();
		initEvents();
		// 从本地获取黑名单
		List<String> blacklist = EMContactManager.getInstance()
				.getBlackListUsernames();

		// 显示黑名单列表

		if (blacklist != null) {
			Collections.sort(blacklist);
			adapter = new BlacklistAdapater(this, 1, blacklist);
			listView.setAdapter(adapter);
		}

		// 注册上下文菜单
		registerForContextMenu(listView);

	}

	@Override
	protected void initViews() {
		// 设置头布局布局可见
		findViewById(R.id.view_top_img_left).setVisibility(0);// 公司logo
		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.chat);// 标题
		findViewById(R.id.cyh_add_friend).setVisibility(8);// 最右边加号
		findViewById(R.id.view_top_img_right).setVisibility(8);// 最右边图标
		img_back = (ImageView) findViewById(R.id.view_top_img_back);
		tv_title = (TextView) findViewById(R.id.view_top_tv_title);
		listView = (ListView) findViewById(R.id.bl_lv_list);
	}

	@Override
	protected void initEvents() {
		tv_title.setText(R.string.Blacklist_1);
		img_back.setBackgroundResource(R.drawable.button_return);
		img_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				defaultFinish();
			}
		});
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.remove) {
			final String tobeRemoveUser = adapter
					.getItem(((AdapterContextMenuInfo) item.getMenuInfo()).position);
			// 把目标user移出黑名单
			removeOutBlacklist(tobeRemoveUser);
			return true;
		}
		return super.onContextItemSelected(item);
	}

	/**
	 * 移出黑民单
	 * 
	 * @param tobeRemoveUser
	 */
	void removeOutBlacklist(final String tobeRemoveUser) {
		try {
			// 移出黑民单
			EMContactManager.getInstance().deleteUserFromBlackList(
					tobeRemoveUser);
			adapter.remove(tobeRemoveUser);
		} catch (EaseMobException e) {
			e.printStackTrace();
			runOnUiThread(new Runnable() {
				public void run() {
					toast("移出失败");
				}
			});
		}
	}

	/**
	 * adapter
	 * 
	 */
	class BlacklistAdapater extends ArrayAdapter<String> {

		public BlacklistAdapater(Context context, int textViewResourceId,
				List<String> objects) {
			super(context, textViewResourceId, objects);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = View.inflate(getContext(), R.layout.row_contact,
						null);
			}

			TextView name = (TextView) convertView.findViewById(R.id.name);
			name.setText(getItem(position));

			return convertView;
		}

	}

}
