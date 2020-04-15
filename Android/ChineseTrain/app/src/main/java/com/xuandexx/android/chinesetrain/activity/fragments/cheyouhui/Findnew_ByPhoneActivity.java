package activity.fragments.cheyouhui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
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
import cn.ieauto.autogroup.android.app.MyActivity;

/**
 * 添加手机联系人
 * 
 * @author Administrator
 * 
 */
public class Findnew_ByPhoneActivity extends MyActivity {
	private ImageView img_Back = null;
	private ListView listview;
	static SimpleAdapter adapter = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.findnew_byphone);
		initView();
		initStep();
	}

	private void initView() {
		img_Back = (ImageView) findViewById(R.id.view_top_img_back);
		img_Back.setBackgroundResource(R.drawable.button_return);
		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.findnew_byphone_title);
		listview = (ListView) findViewById(R.id.findnew_byphone_listview);
	}

	private void initStep() {
		img_Back.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				defaultFinish();
			}
		});

		List<Map<String, String>> data = getdata();
		if (TextUtils.isEmpty(data.toString())) {
			toast("查无联系人!!!");
			defaultFinish();
			return;
		}
		String[] from = new String[] { "name", "phone" };
		int[] to = new int[] { R.id.contacts_name, R.id.contacts_phones };
		SimpleAdapter adapter = new SimpleAdapter(Findnew_ByPhoneActivity.this,
				data, R.layout.item_phonecontacts, from, to);
		listview.setAdapter(adapter);

	}

	private List<Map<String, String>> getdata() {
		// 定义两个List来封装系统的联系人信息、指定联系人的电话号码、Email等详情
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		// 使用ContentResolver查找联系人数据
		Cursor cursor = getContentResolver().query(
				ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		// 遍历查询结果，获取系统中所有联系人
		Map<String, String> map = null;
		while (cursor.moveToNext()) {
			// 获取联系人ID
			String contactId = cursor.getString(cursor
					.getColumnIndex(ContactsContract.Contacts._ID));
			/** 获取联系人的名字 */
			String name = cursor.getString(cursor
					.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
			// 使用ContentResolver查找联系人的电话号码
			Cursor phones = getContentResolver().query(
					ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
					null,
					ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = "
							+ contactId, null, null);
			// 遍历查询结果，获取该联系人的多个电话号码
			while (phones.moveToNext()) {
				// 获取查询结果中电话号码列中数据。
				map = new HashMap<String, String>();
				String phoneNumber = phones
						.getString(phones
								.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
				map.put("name", name);
				map.put("phone", phoneNumber);
				list.add(map);
			}
			phones.close();
		}
		cursor.close();
		System.out.println(list);
		return list;
	}

	OnItemClickListener ocl = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View v, int pos, long id) {

		}
	};

	@Override
	protected void initViews() {

	}

	@Override
	protected void initEvents() {

	}

}
