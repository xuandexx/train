package activity.fragments.cheshenghuo;

import java.util.ArrayList;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import cn.ieauto.autogroup.android.app.MyActivity;

/**
 * 洗车美容
 * 
 * @author Administrator
 * 
 */
/*
 * Beautycarwash_Carwash 洗车 Beautycarwash_Cosmetology 美容
 */
public class AutoBeautyActivity extends MyActivity implements OnClickListener,
		OnPageChangeListener {
	private ViewPager viewPager;
	private ArrayList<Fragment> list;
	private Button[] btns = new Button[3];
	private TextView[] tvs=new TextView[3];
	private int currentPageIndex = 0;
	//反回
	private ImageView img_back;
	private TextView tv_title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_auto_beauty);
		initView();
		initEvents();
	}

	private void initView() {
		img_back = (ImageView) this.findViewById(R.id.view_top_img_back);
		img_back.setBackgroundResource(R.drawable.button_return);
		tv_title = (TextView) this.findViewById(R.id.view_top_tv_title);
		tv_title.setText("洗车美容");
		viewPager = (ViewPager) findViewById(R.id.beautycarwash_viewPager);
		btns[0] = (Button) findViewById(R.id.viewpager_title_but_1);
		btns[0].setText("洗车");
		
		btns[1] = (Button) findViewById(R.id.viewpager_title_but_2);
		btns[1].setText("美容");
		btns[2] = (Button) findViewById(R.id.viewpager_title_but_3);
		btns[2].setVisibility(View.GONE);
		tvs[0]=(TextView) findViewById(R.id.viewpager_title_tv_1);
		tvs[1]=(TextView) findViewById(R.id.viewpager_title_tv_2);
		tvs[2]=(TextView) findViewById(R.id.viewpager_title_tv_3);
		tvs[2].setVisibility(View.GONE);
		Auto_Wash A = new Auto_Wash();//洗车
		Auto_Beauty C = new Auto_Beauty();//美容
		list = new ArrayList<Fragment>();

		list.add(A);
		list.add(C);
		MyFragmentPagerAdapter mainFragmentPagerAdapter = new MyFragmentPagerAdapter(
				this.getSupportFragmentManager(), list);
		viewPager.setAdapter(mainFragmentPagerAdapter);
	}

	protected void initEvents() {
		btns[0].setOnClickListener(this);
		btns[1].setOnClickListener(this);
		viewPager.setOnPageChangeListener(this);
		img_back.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_top_img_back:
			defaultFinish();
			break;
		case R.id.viewpager_title_but_1:
			currentPageIndex = 0;
			break;
		case R.id.viewpager_title_but_2:
			currentPageIndex = 1;
			break;

		}
		viewPager.setCurrentItem(currentPageIndex);
		updateButton();
	}

	private void setData() {

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
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

	@Override
	protected void initViews() {
		
	}

}
