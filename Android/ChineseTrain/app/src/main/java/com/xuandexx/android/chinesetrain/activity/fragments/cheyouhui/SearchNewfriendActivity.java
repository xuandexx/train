package activity.fragments.cheyouhui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.ieauto.autogroup.android.MyApplication;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.app.MyActivity;
import cn.ieauto.autogroup.android.app.MySharedpreference;

/**
 * 寻找新朋友
 * 
 * @author Administrator
 * 
 */
/*
 * 添加手机联系人 Findnew_ByPhoneActivity 按条件查找车友 Findnew_ConditionsActivity
 * 查找附近的车友Findnew_NearbyActivity
 */
public class SearchNewfriendActivity extends MyActivity implements
		OnClickListener {

	private EditText ed1;
	private TextView tv_phone;
	String mEd;
	// 返回， 搜索
	private ImageView img_Back;
	private Button but_Search;

	private RelativeLayout addphonecontacts, relat2, relat3;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_newfriend);
		initViews();
		initEvents();
	}

	@Override
	protected void initViews() {
		img_Back = (ImageView) findViewById(R.id.view_top_img_back);
		ed1 = (EditText) findViewById(R.id.fnf_et_phone);
		tv_phone = (TextView) findViewById(R.id.findnewfriends_tv_phone);
		but_Search = (Button) findViewById(R.id.ffs_addcontracts);
		addphonecontacts = (RelativeLayout) findViewById(R.id.SearchNewfriend_addphonecontacts);
		relat2 = (RelativeLayout) findViewById(R.id.findnewfriends_RelativeLayout2);
		relat3 = (RelativeLayout) findViewById(R.id.findnewfriends_RelativeLayout3);

	}

	@Override
	protected void initEvents() {

		img_Back.setOnClickListener(this);
		but_Search.setOnClickListener(this);
		addphonecontacts.setOnClickListener(this);
		relat2.setOnClickListener(this);
		relat3.setOnClickListener(this);
		img_Back.setBackgroundResource(R.drawable.button_return);
		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.search_friend);
		tv_phone.setText(MyApplication.getInstance().getUserPhone());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_top_img_back:
			defaultFinish();
			break;
			/**
			 * 手机号码搜索
			 */
		case R.id.ffs_addcontracts:
			mEd = ed1.getText().toString();
			break;
			/**
			 * 添加手机联系人
			 */
		case R.id.SearchNewfriend_addphonecontacts:
			startActivity(Findnew_ByPhoneActivity.class);
			break;
			/**
			 * 查找附近的车友
			 */
		case R.id.findnewfriends_RelativeLayout2:
			intent = new Intent(SearchNewfriendActivity.this,
					Findnew_NearbyActivity.class);
			startActivity(intent);
			break;
			/**
			 * 按条件查找
			 */
		case R.id.findnewfriends_RelativeLayout3:
			intent = new Intent(SearchNewfriendActivity.this,
					Findnew_ConditionsActivity.class);
			startActivity(intent);
			break;
		}
	}

}
