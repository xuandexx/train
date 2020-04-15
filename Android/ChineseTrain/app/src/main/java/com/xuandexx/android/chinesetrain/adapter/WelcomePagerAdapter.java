package adapter;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.activity.LoginActivity;

/**
 * 自定义PagerAdapter，实现引导页
 * 
 * 
 */
public class WelcomePagerAdapter extends PagerAdapter{

	/** 界面列表 */
	private List<View> views = null;
	/** 上下文内容 */
	private Activity context = null;

	public WelcomePagerAdapter(List<View> views, Activity context) {
		this.views = views;
		this.context = context;
	}
	/*
	 * 销毁arg1位置的界面(non-Javadoc)
	 * 
	 * @see android.support.v4.view.PagerAdapter#destroyItem(android.view.View,
	 * int, java.lang.Object)
	 */
	@Override
	public void destroyItem(View container, int position, Object object) {
		((ViewPager) container).removeView(views.get(position));
	}

	/*
	 * 获得当前界面数(non-Javadoc)
	 * 
	 * @see android.support.v4.view.PagerAdapter#getCount()
	 */
	@Override
	public int getCount() {
		return views.size();
	}
	/*
	 * 初始化arg1位置的界面(non-Javadoc)
	 * 
	 * @see android.support.v4.view.PagerAdapter#instantiateItem(android.view
	 * .View, int)
	 */
	@Override
	public Object instantiateItem(View container, int position) {
		((ViewPager) container).addView(views.get(position), 0);
		if (position == views.size() - 1) {
			/* 若是最后一个界面 */
			Button guide_btn = (Button) container.findViewById(R.id.btEnter);
			guide_btn.setVisibility(View.VISIBLE);
			/* 设置图片按钮监听，做跳转操作 */
			guide_btn.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent = new Intent(context, LoginActivity.class);
					context.startActivity(intent);
					context.finish();
				}
			});
		}
		return views.get(position);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return (arg0 == arg1);
	}

}
