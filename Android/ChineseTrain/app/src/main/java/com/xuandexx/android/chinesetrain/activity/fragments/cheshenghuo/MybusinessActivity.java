package activity.fragments.cheshenghuo;

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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.activity.fragments.business.Mybusiness_CompanyActivity;
import cn.ieauto.autogroup.android.activity.fragments.business.Mybusiness_Personal_2Activity;
import cn.ieauto.autogroup.android.activity.fragments.business.Mybusiness_Personal_3Activity;
import cn.ieauto.autogroup.android.activity.fragments.business.Mybusiness_Personal_4Activity;
import cn.ieauto.autogroup.android.app.MyActivity;

/**
 * 我的生意
 * 
 * @author Administrator
 * 
 */
/*
 * 个人的Mybusiness_Personal_1Activity 个人的Mybusiness_Personal_2Activity
 * 个人的Mybusiness_Personal_3Activity 个人的Mybusiness_Personal_4Activity
 * 公司的Mybusiness_CompanyActivity
 */
public class MybusinessActivity extends MyActivity implements OnClickListener {

	private Intent intent;
	String title;

	/*
	 * 已开通 Hasopened 未开通 Notopened
	 */
	private GridView Hasopened_gridview, Notopened_gridview;
	private LinearLayout Hasopened_linear;
	/*
	 * 0表示未开通; 1表示已开通; 2表示申请中
	 */
	private final static int LABEL = 0;

	ImageView imageView;
	// int[] image_Hasopened=new int[19];
	// String[] text_Hasopened=new String[19];
	int[] image_Hasopened;
	String[] text_Hasopened;
	// 返回
	private ImageView img_back;

	int[] image_Notopened = new int[] { R.drawable.e21_0_071_1,
			R.drawable.e21_0_071_2, R.drawable.e21_0_071_3,
			R.drawable.e21_0_071_4, R.drawable.e21_0_071_5,
			R.drawable.e21_0_071_6, R.drawable.e21_0_071_7,
			R.drawable.e21_0_071_8, R.drawable.e21_0_071_9,
			R.drawable.e21_0_071_10, R.drawable.e21_0_071_11,
			R.drawable.e21_0_071_12, R.drawable.e21_0_071_13,
			R.drawable.e21_0_071_14, R.drawable.e21_0_071_15,
			R.drawable.e21_0_071_16, R.drawable.e21_0_071_17,
			R.drawable.e21_0_071_18, R.drawable.e21_0_071_19,
			R.drawable.e21_0_071_20 };
	String[] text_Notopened = new String[] { "代驾", "私车租借", "拼车 ", "陪练 ",
			"私车转让 ", "车辆求购", "代办违章 ", "代办过户 ", "代办验车", "车险销售", "理赔指导", "洗车 ",
			"美容   ", "道路救援", "维修保养 ", "装潢 ", "改装", "代办理赔", "租赁公司", "车辆收购 " };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_mybusiness);
		TextView Title = (TextView) findViewById(R.id.view_top_tv_title);
		Title.setText(R.string.my_fargment_business);
		initView();
		initStep();
	}

	private void initView() {
		img_back = (ImageView) this.findViewById(R.id.view_top_img_back);
		img_back.setBackgroundResource(R.drawable.button_return);
		img_back.setOnClickListener(this);
		imageView = (ImageView) findViewById(R.id.imageview);
		// Hasopened_gridview = (GridView)
		// findViewById(R.id.Hasopened_GridView);
		Notopened_gridview = (GridView) findViewById(R.id.Notopened_GridView);
		// Hasopened_linear = (LinearLayout)
		// findViewById(R.id.Hasopened_linearlayout);
		// Hasopened_linear.setVisibility(8);

	}

	private void initStep() {
		final List<Map<String, Object>> Notopened_list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < image_Notopened.length; i++) {
			Map<String, Object> Notopened_map = new HashMap<String, Object>();
			Notopened_map.put("image", image_Notopened[i]);
			Notopened_map.put("text", text_Notopened[i]);
			Notopened_list.add(Notopened_map);
		}

		SimpleAdapter simpleAdapter = new SimpleAdapter(this, Notopened_list,
				R.layout.cell, new String[] { "image", "text" }, new int[] {
						R.id.cell_image1, R.id.cell_text });

		Notopened_gridview.setAdapter(simpleAdapter);
		Notopened_gridview
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});
		Notopened_gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				title = ((TextView) view.findViewById(R.id.cell_text))
						.getText().toString();
				Intent intent=new Intent();
				intent.setClass(MybusinessActivity.this, StatementActivity.class);
				intent.putExtra("result", "代驾");
				startActivity(intent);
			}
		});
	}
//	private void intentpersonal1() {
//		intent = new Intent(MybusinessActivity.this,
//				Mybusiness_Personal_1Activity.class);
//		Bundle bundle = new Bundle();
//		bundle.putString("title", title);
//		intent.putExtras(bundle);
//		startActivity(intent);
//	}

	private void intentpersonal2() {
		intent = new Intent(MybusinessActivity.this,
				Mybusiness_Personal_2Activity.class);
		Bundle bundle = new Bundle();
		bundle.putString("title", title);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	private void intentpersonal3() {
		intent = new Intent(MybusinessActivity.this,
				Mybusiness_Personal_3Activity.class);
		startActivity(intent);
	}

	private void intentpersonal4() {
		intent = new Intent(MybusinessActivity.this,
				Mybusiness_Personal_4Activity.class);
		startActivity(intent);
	}

	private void intentcompany() {
		intent = new Intent(MybusinessActivity.this,
				Mybusiness_CompanyActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString("title", title);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_top_img_back:
			defaultFinish();
			break;

		}
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initEvents() {
		// TODO Auto-generated method stub

	}
}
