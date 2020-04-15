package adapter;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
	ArrayList<Fragment> list;

	public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
		super(fm);
		this.list = list;
	}

	public Fragment getItem(int arg0) {
		return list.get(arg0);
	}

	public int getCount() {
		return list.size();
	}
}
