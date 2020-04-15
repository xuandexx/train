package activity.fragments.cheshenghuo;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.adapter.MyPagerAdapter;
import cn.ieauto.autogroup.android.app.MyActivity;

/**
 * 车辆详情
 * 
 * @author Administrator vehicledetails_title
 */
public class CarInfoActivity extends MyActivity implements OnClickListener{
	private ViewPager viewpager;
	private LayoutInflater inflater;
	private MyPagerAdapter mypagerAdapter;
	private ArrayList<View> views;
	private TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv10, tv11;
	String mPhone = null, mCar = null, mStall = null, mCarTime = null,
			mRentalType = null, mRentalTime = null;
	private Intent intent;
	// 评分
	private RatingBar ratingbar;
	//返回
	private ImageView img_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.vehicledetails);
		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.vehicledetails_title);
		intent = this.getIntent();
		inflater = LayoutInflater.from(this);
		initView();
	}

	private void initView() {
		img_back = (ImageView) this.findViewById(R.id.view_top_img_back);
		img_back.setBackgroundResource(R.drawable.button_return);
		img_back.setOnClickListener(this);
		Bundle bundle = new Bundle();
		bundle = intent.getExtras();
		// "phone","car", "stall","cartime", "rentaltype", "rentaltime"
		mPhone = bundle.getString("phone");
		mCar = bundle.getString("car");
		mStall = bundle.getString("stall");
		mCarTime = bundle.getString("cartime");
		mRentalType = bundle.getString("rentaltype");
		mRentalTime = bundle.getString("rentaltime");

		tv1 = (TextView) findViewById(R.id.vehicledetails_tv20);// 我 要 评 价
		tv2 = (TextView) findViewById(R.id.vehicledetails_tv21);// 查 看 车 友 个 人 信
																// 息
		tv3 = (TextView) findViewById(R.id.vehicledetails_name);// 奥迪A6技术领先型
		tv4 = (TextView) findViewById(R.id.vehicledetails_textView3);// 车辆产权登记证已认证
		tv5 = (TextView) findViewById(R.id.vehicledetails_textView4);// 自排 2.4L
																		// 黑色
		tv6 = (TextView) findViewById(R.id.vehicledetails_textView13);// 车牌地：沪A
		tv7 = (TextView) findViewById(R.id.vehicledetails_tv1);// 上牌年份：2010年9月
		tv8 = (TextView) findViewById(R.id.vehicledetails_tv2);// 行驶里程：13.5万公里
		tv9 = (TextView) findViewById(R.id.vehicledetails_tv3);// 转让价格：23.4万元
		tv10 = (TextView) findViewById(R.id.vehicledetails_tv4);// 过户费：买家承担
		tv11 = (TextView) findViewById(R.id.vehicledetails_number);// 联系电话：18901330037
		// ratingbar.setRating("");
		viewpager = (ViewPager) findViewById(R.id.vehicledetails_viewpager);
		views = createViews();
		mypagerAdapter = new MyPagerAdapter(views);
		viewpager.setAdapter(mypagerAdapter);
		tv1.setOnClickListener(tv1listener);
	}

	private ArrayList<View> createViews() {
		ArrayList<View> views = new ArrayList<View>();
		views.add(createView(R.drawable.n_1));
		views.add(createView(R.drawable.n_2));
		views.add(createView(R.drawable.n_3));
		views.add(createView(R.drawable.n_4));
		views.add(createView(R.drawable.n_5));
		return views;
	}

	private View createView(int resId) {
		ImageView iv = (ImageView) inflater.inflate(R.layout.child_view, null);
		iv.setImageResource(resId);
		return iv;
	}

	private OnClickListener tv1listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(CarInfoActivity.this, CommentActivity.class);
			startActivity(intent);
		}
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_top_img_back:
			defaultFinish();
			break;

		default:
			break;
		}
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initEvents() {
		// TODO Auto-generated method stub
		
	}
}
