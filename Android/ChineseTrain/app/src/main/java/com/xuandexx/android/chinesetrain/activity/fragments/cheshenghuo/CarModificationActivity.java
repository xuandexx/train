package activity.fragments.cheshenghuo;

import java.util.ArrayList;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.adapter.MyFragmentPagerAdapter;

/**
 * 装潢改装
 * 
 * @author Administrator
 */
public class CarModificationActivity extends FragmentActivity implements
		OnClickListener, OnPageChangeListener {
	private ViewPager viewPager;
	private ArrayList<Fragment> list;
	Button[] btns = new Button[3];
	private TextView[] tvs=new TextView[3];
	private int currentPageIndex = 0;
	private ImageView img_back;
	private TextView tv_title;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_car_modification);
		initView();
		initEvents();
//		setData();
	}

	private void initView() {
		img_back = (ImageView) this.findViewById(R.id.view_top_img_back);
		img_back.setBackgroundResource(R.drawable.button_return);
		tv_title = (TextView) this.findViewById(R.id.view_top_tv_title);
		tv_title.setText("装潢改装");
		
		viewPager = (ViewPager) findViewById(R.id.beautycarwash_viewPager);
		btns[0] = (Button) findViewById(R.id.viewpager_title_but_1);
		btns[0].setText("装潢");
		btns[1] = (Button) findViewById(R.id.viewpager_title_but_2);
		btns[1].setText("改装");
		btns[2] = (Button) findViewById(R.id.viewpager_title_but_3);
		btns[2].setVisibility(View.GONE);
		tvs[0]=(TextView) findViewById(R.id.viewpager_title_tv_1);
		tvs[1]=(TextView) findViewById(R.id.viewpager_title_tv_2);
		tvs[2]=(TextView) findViewById(R.id.viewpager_title_tv_3);
		tvs[2].setVisibility(View.GONE);
//		btns[0] = (Button) findViewById(R.id.carabouttabhost_decoratingmodification_but1);
//		btns[1] = (Button) findViewById(R.id.carabouttabhost_decoratingmodification_but2);
		viewPager = (ViewPager) findViewById(R.id.carabouttabhost_decoratingmodification_viewPager);
		setData();
	}

	private void initEvents() {
		btns[0].setOnClickListener(this);
		btns[1].setOnClickListener(this);
		viewPager.setOnPageChangeListener(this);
		img_back.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_top_img_back:
			finish();
		break;
		case R.id.viewpager_title_but_1:
			currentPageIndex = 0;
			break;
		/*
		 * case R.id.autoinsuranceclaims_but2: currentPageIndex=1; break;
		 */
		case R.id.viewpager_title_but_2:
			currentPageIndex = 1;
			break;
		}
		viewPager.setCurrentItem(currentPageIndex);
		updateButton();
	}

	private void setData() {
		list = new ArrayList<Fragment>();

		CarModify A = new CarModify();
		CarMount C = new CarMount();

		list.add(A);
		list.add(C);
		MyFragmentPagerAdapter mainFragmentPagerAdapter = new MyFragmentPagerAdapter(
				this.getSupportFragmentManager(), list);
		viewPager.setAdapter(mainFragmentPagerAdapter);
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
				//btns[i].setBackgroundResource(R.drawable.b1_004_1);
				btns[i].setTextColor(Color.RED);
				tvs[i].setBackgroundResource(R.color.title_now);
			} else {
				//btns[i].setBackgroundResource(R.drawable.b1_004_03);
				btns[i].setTextColor(Color.BLACK);
				tvs[i].setBackgroundResource(R.color.title_next);
			}
		}
	}

}
