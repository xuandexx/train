package com.xuandexx.android.train.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.xuandexx.android.train.R;
import com.xuandexx.android.train.adapter.HomePageADAdapter;
import com.xuandexx.android.train.common.CommonUtils;
import com.xuandexx.android.train.model.BannerItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 内容提供
 */
public class ContentFragment extends BaseFragment {

    private TextView errorText;

    private ImageButton clearSearch;

    private EditText query;

    private TextView searchView;

    private ListView listView;

    private TextView cancleView;

    //首页浮动广告
    private ViewPager adViewPager;

    // 当前图片的索引号
    private int currentItem = 0;
    // 滑动的图片集合
    private List<ImageView> imageViews;

    // 异步加载图片
    private ImageLoader mImageLoader;
    private DisplayImageOptions options;
    // 图片标题正文的那些点
    private List<View> dots;
    private List<View> dotList;
    // 定义的六个指示点
    private View dot0;
    private View dot1;
    private View dot2;
    private View dot3;
    private View dot4;
    private View dot5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root;
        TAG = this.getClass().getSimpleName();
        if (CommonUtils.isNetWorkConnected(this.getActivity())) {
            root = inflater.inflate(R.layout.fragment_content, container, false);
        } else {
            root = inflater.inflate(R.layout.em_chat_neterror_item, container, false);
            errorText = root.findViewById(R.id.tv_connect_errormsg);
            errorText.setText("网络连接中断");
        }
        return root;
    }

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            adViewPager.setCurrentItem(currentItem);
        }
    };


    @Override
    protected void initView() {
        TAG = this.getClass().getSimpleName();
        clearSearch = (ImageButton) findViewById(R.id.search_clear);
        query = (EditText) findViewById(R.id.query);
        searchView = (TextView) findViewById(R.id.tv_search);
        listView = (ListView) findViewById(R.id.listview);
        cancleView = (TextView) findViewById(R.id.tv_cancel);
        adViewPager = (ViewPager) findViewById(R.id.vp);
        mImageLoader = ImageLoader.getInstance();

        ArrayList<BannerItem> list = new ArrayList<BannerItem>();
        for (int i = 0; i < 6; i++) {
            BannerItem tempItem = new BannerItem();
            tempItem.setAd(false);
            tempItem.setImg("http://i0.hdslb.com/promote/1f451b6b07a1984be5619f865edd5449.jpg");
            tempItem.setLink("http://www.bilibili.com");
            tempItem.setTitle("[示例数据]");
            list.add(tempItem);
        }
        list.get(0).setAd(true);

        imageViews = new ArrayList<ImageView>();

        options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.top_banner_android)
                .showImageForEmptyUri(R.drawable.top_banner_android)
                .showImageOnFail(R.drawable.top_banner_android)
                .cacheInMemory(true).cacheOnDisc(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY).build();
        // 点
        dots = new ArrayList<View>();
        dotList = new ArrayList<View>();
        dot0 = findViewById(R.id.v_dot0);
        dot1 = findViewById(R.id.v_dot1);
        dot2 = findViewById(R.id.v_dot2);
        dot3 = findViewById(R.id.v_dot3);
        dot4 = findViewById(R.id.v_dot4);
        dot5 = findViewById(R.id.v_dot5);
        dots.add(dot0);
        dots.add(dot1);
        dots.add(dot2);
        dots.add(dot3);
        dots.add(dot4);
        dots.add(dot5);

        for (int i = 0; i < list.size(); i++) {
            ImageView imageView = new ImageView(this.getActivity());
            // 异步加载图片
            mImageLoader.displayImage(list.get(i).getImg(), imageView,
                    options);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageViews.add(imageView);
            dots.get(i).setVisibility(View.VISIBLE);
            dotList.add(dots.get(i));
        }
        adViewPager.setAdapter(new HomePageADAdapter(list, (BaseActivity) getActivity(), imageViews));//
        // 设置填充ViewPager页面的适配器
    }

    @Override
    protected void initEvent() {
        query.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    clearSearch.setVisibility(View.VISIBLE);
                } else {
                    clearSearch.setVisibility(View.INVISIBLE);
                }
                searchView.setVisibility(View.VISIBLE);
                listView.setVisibility(View.INVISIBLE);
                searchView.setHint(String.format(getString(R.string.search_contanier), s));
            }
        });
        clearSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query.getText().clear();
                hideSoftKeyboard();
            }
        });
        cancleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}