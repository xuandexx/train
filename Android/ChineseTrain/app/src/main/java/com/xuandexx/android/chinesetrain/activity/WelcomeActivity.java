package activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.adapter.WelcomePagerAdapter;
import cn.ieauto.autogroup.android.app.ComConfig;
import cn.ieauto.autogroup.android.app.MyActivity;
import cn.ieauto.autogroup.android.util.SharedPreferencesHelper;

/**
 * 登录欢迎界面,由三个界面构成
 * 
 * @author liting
 * 
 */
public class WelcomeActivity extends MyActivity {
	
	/** SharedPreferences操作,用来设置不再首次运行 */
	private SharedPreferencesHelper sph;
	/**
	 * 用于承载三个view界面
	 */
	private ViewPager viewPager;
	/**
	 * 用于装载view数据
	 */
	private List<View> viewList;
	/**
	 * 界面中的三个点,装载进入到界面中以标识滑动进度
	 */
	private ImageView[] imageDots;
	/** viewPager适配器 */
	private WelcomePagerAdapter mAdapter;
	/** 已知的引导页个数 */
	private int pageCount;
	/** 保存每次引导上一种状态变量 */
	private int currentIndex;
    //引领小数点
	private LinearLayout viewGroup;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_welcome);
		initData();
	
		initLayout();
	}
	/**
	 * 初始化数据
	 */
	private void initData() {
		/* 设置标识，表示不再启动这个引导页 */
		sph = new SharedPreferencesHelper(WelcomeActivity.this);
		sph.putInt(ComConfig.IS_FIRST_RUN, ComConfig.NOT_FIRST);
		/* 小点数据源 */
		pageCount = 3;
		imageDots = new ImageView[pageCount];
		currentIndex = 0;
		/* 设置viewpager数据源 */
		viewList = new ArrayList<View>();
	}
	/**
	 * 初始化布局
	 */
	private void initLayout() {
		/* viewPager设置 */
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		LayoutInflater inflater = LayoutInflater.from(this);
		Class<R.drawable> cls = R.drawable.class;// 准备反射R.drawable下资源
		for (int i = 0; i < pageCount; i++) {
			View view = inflater.inflate(R.layout.view_welcom03, null);
			RelativeLayout linear_guide_showImg = (RelativeLayout) view.findViewById(R.id.linear_guide_showImg);
			int imageId = 0;
			try {
				imageId = cls.getDeclaredField("ptp0" + (i + 1)).getInt(R.drawable.ptp00);
			} catch (Exception e) {
				imageId = R.drawable.ptp00;
				e.printStackTrace();
			}
			linear_guide_showImg.setBackgroundResource(imageId);
			viewList.add(view);
		}
		mAdapter = new WelcomePagerAdapter(viewList, this);
		viewPager.setAdapter(mAdapter);
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				imageDots[arg0].setEnabled(false);
				imageDots[currentIndex].setEnabled(true);
				currentIndex = arg0;
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
		/* 引导小点设置 */
		viewGroup = (LinearLayout) findViewById(R.id.viewGroup);
		for (int i = 0; i < pageCount; i++) {// 循环取得小点图片
			imageDots[i] = (ImageView) viewGroup.getChildAt(i);
			if (i == 0) {
				imageDots[i].setEnabled(false);// 设置为白色，即选中状态
			} else {
				imageDots[i].setEnabled(true);// 都设为红色
			}
		}
	}

	@Override
	protected void initEvents() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		
	}

}
