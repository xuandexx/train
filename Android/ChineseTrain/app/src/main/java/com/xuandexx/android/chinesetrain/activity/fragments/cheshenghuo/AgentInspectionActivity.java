package activity.fragments.cheshenghuo;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.adapter.MyFragmentPagerAdapter;
import cn.ieauto.autogroup.android.app.MyActivity;
import cn.ieauto.autogroup.android.entity.MessageContent;
import cn.ieauto.autogroup.android.util.MyAsyncTask;

/**
 * 代办验车
 * 
 * @author Administrator
 * 
 */
/*
 * LocalInspection 本地车代验 FieldInspection 外牌车代验 OpenproxyFragment 代开委托书
 */
@SuppressLint("ResourceAsColor")
public class AgentInspectionActivity extends MyActivity implements
		OnClickListener, OnPageChangeListener {

	private ImageView img_back;
	private TextView tv_title;
	private ViewPager viewPager;
	private ArrayList<Fragment> fglist;
	private Button[] btns = new Button[3];
	private TextView[] tvs=new TextView[3];
	private int currentPageIndex = 0;
	
	MessageContent content;
	MyAsyncTask asyncTask;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agent_inspection);
		initViews();
		initEvents();
	}

	@Override
	protected void initViews() {
		
		img_back = (ImageView) this.findViewById(R.id.view_top_img_back);
		img_back.setBackgroundResource(R.drawable.button_return);
		tv_title = (TextView) this.findViewById(R.id.view_top_tv_title);
		tv_title.setText("代办验车");
		btns[0] = (Button) findViewById(R.id.viewpager_title_but_1);
		btns[1] = (Button) findViewById(R.id.viewpager_title_but_2);
		btns[2] = (Button) findViewById(R.id.viewpager_title_but_3);
		tvs[0]=(TextView) findViewById(R.id.viewpager_title_tv_1);
		tvs[1]=(TextView) findViewById(R.id.viewpager_title_tv_2);
		tvs[2]=(TextView) findViewById(R.id.viewpager_title_tv_3);
		viewPager = (ViewPager) findViewById(R.id.vehicletnspection_viewPager);
		// 本地车代验:LocalInspection
		LocalInspection A = new LocalInspection();
		// 外牌车代验:FieldInspection
		FieldInspection B = new FieldInspection();
		// 代开委托书:OpenproxyFragment
		OpenproxyFragment C = new OpenproxyFragment();
		fglist = new ArrayList<Fragment>();
		fglist.add(A);
		fglist.add(B);
		fglist.add(C);
		MyFragmentPagerAdapter mainFragmentPagerAdapter = new MyFragmentPagerAdapter(
				this.getSupportFragmentManager(), fglist);
		viewPager.setAdapter(mainFragmentPagerAdapter);
	}

	protected void initEvents() {
		img_back.setOnClickListener(this);
		btns[0].setOnClickListener(this);
		btns[1].setOnClickListener(this);
		btns[2].setOnClickListener(this);
		viewPager.setOnPageChangeListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_top_img_back:
			defaultFinish();
			break;
		// 本地车代验
		case R.id.viewpager_title_but_1:
			currentPageIndex = 0;
			break;
		// 外牌车代验
		case R.id.viewpager_title_but_2:
			currentPageIndex = 1;
			break;
		// 代开委托书
		case R.id.viewpager_title_but_3:
			currentPageIndex = 2;
			break;
		}

		viewPager.setCurrentItem(currentPageIndex);
		updateButton();

	}

	public void onPageScrollStateChanged(int arg0) {

	}

	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	public void onPageSelected(int pageIndex) {
		currentPageIndex = pageIndex;
		updateButton();
	}

	private void updateButton() {
		for (int i = 0; i < btns.length; i++) {
			if (i == this.currentPageIndex) {
				//btns[i].setTextColor(R.color.title_now);
				btns[i].setTextColor(Color.RED);
				tvs[i].setBackgroundResource(R.color.title_now);
			} else {
				//btns[i].setTextColor(R.color.black);
				btns[i].setTextColor(Color.BLACK);
				tvs[i].setBackgroundResource(R.color.title_next);
			}
		}
	}
}
