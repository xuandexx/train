package com.xuandexx.android.train.ui;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.viewpager.widget.ViewPager;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.xuandexx.android.train.R;
import com.xuandexx.android.train.adapter.CourseListAdapter;
import com.xuandexx.android.train.adapter.HomePageADAdapter;
import com.xuandexx.android.train.base.BaseActivity;
import com.xuandexx.android.train.model.BannerItem;
import com.xuandexx.android.train.model.CourseCollection;
import com.xuandexx.android.train.model.VideoItem;

import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 内容提供
 */
@ContentView(R.layout.fragment_content)
public class ContentFragment extends BaseFragment {

    @ViewInject(R.id.query)
    private EditText query;
    @ViewInject(R.id.search_clear)
    private ImageButton clearSearch;
    @ViewInject(R.id.listView)
    private ListView listView;

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
    // 图片缓存路径
    public static String IMAGE_CACHE_PATH = "imageloader/Cache";

    private ScheduledExecutorService scheduledExecutorService;


    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            adViewPager.setCurrentItem(currentItem);
        }
    };

    @Override
    protected void initEvent() {
        TAG = this.getClass().getSimpleName();
        adViewPager = (ViewPager) findViewById(R.id.vp);
        ArrayList<BannerItem> list = new ArrayList<BannerItem>();

        List<CourseCollection> courseCollections = new ArrayList<>();
        listView.setAdapter(new CourseListAdapter(activity, courseCollections));


        for (int i = 0; i < 6; i++) {
            BannerItem tempItem = new BannerItem();
            tempItem.setAd(false);
            tempItem.setImg("https://dss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2235388275,3809603206&fm=85&app=79&f=JPG?w=121&h=75&s=8197C732C535FA313E526557030030BB");
            tempItem.setLink("https://www.bilibili.com/");
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
        File cacheDir = com.nostra13.universalimageloader.utils.StorageUtils
                .getOwnCacheDirectory(this.getActivity().getApplicationContext(),
                        IMAGE_CACHE_PATH);
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisc(true).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                this.getActivity()).defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new LruMemoryCache(12 * 1024 * 1024))
                .memoryCacheSize(12 * 1024 * 1024)
                .discCacheSize(32 * 1024 * 1024).discCacheFileCount(100)
                .discCache(new UnlimitedDiscCache(cacheDir))
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
        mImageLoader = ImageLoader.getInstance();
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
        adViewPager.addOnPageChangeListener(new HomePageADChangeListener());
        MainTask homePageTask = new MainTask();
        homePageTask.execute("0");
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
            }
        });
    }

    //等同于@Event(value={R.id.btn_get,R.id.btn_post},type=View.OnClickListener.class)
    @Event(value = {R.id.search_clear})
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.search_clear:
                query.getText().clear();
                hideSoftKeyboard();
                break;
        }
    }

    private void startAd() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 当Activity显示出来后，每两秒切换一次图片显示
        scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 4,
                TimeUnit.SECONDS);
    }

    private class ScrollTask implements Runnable {

        @Override
        public void run() {
            synchronized (adViewPager) {
                currentItem = (currentItem + 1) % imageViews.size();
                handler.obtainMessage().sendToTarget();
            }
        }
    }

    private class HomePageADChangeListener implements ViewPager.OnPageChangeListener {

        private int oldPosition = 0;

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int position) {
            currentItem = position;
            dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
            dots.get(position).setBackgroundResource(R.drawable.dot_focused);
            oldPosition = position;
        }
    }

    private class MainTask extends AsyncTask<String, Void, Integer> {
        ArrayList<BannerItem> Listtemp = new ArrayList<BannerItem>();
        ArrayList<VideoItem> bangumiListtemp = new ArrayList<VideoItem>();
        ArrayList<VideoItem> dougaListtemp = new ArrayList<VideoItem>();
        ArrayList<VideoItem> musicListtemp = new ArrayList<VideoItem>();
        ArrayList<VideoItem> danceListtemp = new ArrayList<VideoItem>();
        ArrayList<VideoItem> entListtemp = new ArrayList<VideoItem>();
        ArrayList<VideoItem> movieListtemp = new ArrayList<VideoItem>();
        ArrayList<VideoItem> kejiListtemp = new ArrayList<VideoItem>();

        public MainTask() {
            // TODO Auto-generated constructor stub
            Log.d("T^T", "----->MainTask");
        }

        @Override
        protected Integer doInBackground(String... params) {
            Log.d("T^T", "----->doinbackgroud");
            JSONObject bannerjson;
            JSONObject bangumijson;

            try {
//                bannerjson = new JSONObject(HttpUtil.getHtmlString("http://www.bilibili.com/index/slideshow.json"));
//                JSONArray array = bannerjson.getJSONArray("list");
//                for (int i = 0; i < array.length(); i++) {
//
//                    BannerItem item = new BannerItem();
//                    item.setImg(array.getJSONObject(i).getString("img").toString());
//                    item.setTitle(array.getJSONObject(i).getString("title").toString());
//                    item.setLink(array.getJSONObject(i).getString("link").toString());
//                    item.setAd(false);
//                    Listtemp.add(item);
//                }
//
//                bangumijson = new JSONObject(HttpUtil.getHtmlString("http://www.bilibili.com/index/ding.json"));
//                //Log.i("gg",bangumijson.toString());
//                JSONObject bangumiarray = bangumijson.getJSONObject("bangumi");
//                for (int i = 0; i < 4; i++) {
//                    VideoItem item = new VideoItem();
//                    item.setAid(bangumiarray.getJSONObject(i + "").getString("aid").toString());
//                    item.setTypeid(bangumiarray.getJSONObject(i + "").getString("typeid").toString());
//                    item.setTitle(bangumiarray.getJSONObject(i + "").getString("title").toString());
//                    item.setSbutitle(bangumiarray.getJSONObject(i + "").optString("sbutitle").toString());
//                    item.setPlay(bangumiarray.getJSONObject(i + "").getString("play").toString());
//                    item.setReview(bangumiarray.getJSONObject(i + "").getString("review").toString());
//                    item.setVideo_review(bangumiarray.getJSONObject(i + "").getString("video_review").toString());
//                    item.setFavorites(bangumiarray.getJSONObject(i + "").getString("favorites").toString());
//                    item.setMid(bangumiarray.getJSONObject(i + "").getString("mid").toString());
//                    item.setAuthor(bangumiarray.getJSONObject(i + "").getString("author").toString());
//                    item.setDescription(bangumiarray.getJSONObject(i + "").getString("description").toString());
//                    item.setCreate(bangumiarray.getJSONObject(i + "").getString("create").toString());
//                    item.setPic(bangumiarray.getJSONObject(i + "").getString("pic").toString());
//                    item.setCredit(bangumiarray.getJSONObject(i + "").getString("credit").toString());
//                    item.setCoins(bangumiarray.getJSONObject(i + "").getString("coins").toString());
//                    item.setDuration(bangumiarray.getJSONObject(i + "").getString("duration").toString());
//                    bangumiListtemp.add(item);
//                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
//            Listtemp.get(Listtemp.size() - 1).
//                    setAd(true);
            return null;
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            Log.d("T^T", "----->onpostexcute");
//            adList = Listtemp;
//            videoItemList = bangumiListtemp;
//            dougaItemList = dougaListtemp;
//            musicItemList = musicListtemp;
//            danceItemList = danceListtemp;
//            entItemList = entListtemp;
//            movieItemList = movieListtemp;
//            kejiItemList = kejiListtemp;
//            initAdData();
//			initBangumiData();
            startAd();
//			adViewPager.notifyAll();

        }

    }
}