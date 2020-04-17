package com.xuandexx.android.train.adapter;

import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.xuandexx.android.train.R;
import com.xuandexx.android.train.model.BannerItem;
import com.xuandexx.android.train.ui.AdWebviewActivity;
import com.xuandexx.android.train.ui.BaseActivity;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class HomePageADAdapter extends PagerAdapter {

    // 轮播banner的数据
    private List<BannerItem> adList;

    private BaseActivity baseActivity;

    // 滑动的图片集合
    private List<ImageView> imageViews;

    public HomePageADAdapter(List<BannerItem> adList, BaseActivity baseActivity, List<ImageView> imageViews) {
        this.adList = adList;
        this.baseActivity = baseActivity;
        this.imageViews = imageViews;
    }

    @Override
    public int getCount() {
        return adList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView iv = imageViews.get(position);
        ((ViewPager) container).addView(iv);
        // 在这个方法里面设置图片的点击事件
        iv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 处理跳转逻辑
                BannerItem item = (BannerItem) adList.get(position);
                Intent i = new Intent();
                i.setClass(baseActivity, AdWebviewActivity.class);
                i.putExtra("bannerLink", item.getLink());
                baseActivity.startActivity(i);
                // 动画过渡
                baseActivity.overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_no);
                Log.e("position", "" + position);
            }
        });
        return iv;
    }

    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView((View) arg2);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {

    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void startUpdate(View arg0) {

    }

    @Override
    public void finishUpdate(View arg0) {

    }
}
	