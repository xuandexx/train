package adapter;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class MyPagerAdapter extends PagerAdapter {

	private ArrayList<View> views;

	public MyPagerAdapter(ArrayList<View> views) {
		if (views != null)
			this.views = views;
		else
			this.views = new ArrayList<View>();
	}

	/**
	 * 移除当前图片
	 * 
	 * @param container
	 * @param position
	 * @param object
	 */
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		View v = views.get(position);
		container.removeView(v);
	}

	@Override
	public void finishUpdate(ViewGroup container) {
		// Log.i("info", "finishUpdate");
	}

	/**
	 * 加载显示图片
	 * 
	 * @param container
	 * @param position
	 * @return
	 */
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View v = views.get(position);
		container.addView(v);
		return v;
	}

	@Override
	public int getCount() {
		return views.size();
	}

	@Override
	public boolean isViewFromObject(View v, Object key) {
		return v == key;
	}

}
