package activity.fragments.cheshenghuo;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.adapter.MyPagerAdapter;
import cn.ieauto.autogroup.android.app.MyActivity;
import cn.ieauto.autogroup.android.dialog.CustomDialog;
/*
 * 车辆详情
 */
public class TransferFragmentAssignment extends MyActivity{

	private ViewPager viewpager;
	private LayoutInflater inflater;
	private MyPagerAdapter mypagerAdapter;
	private ArrayList<View> views;
	private TextView Name, Certification, Type, Impression;
	private TextView Address, Hours, Phone, Price, readme, Number;
	private Button IwantToeValuate, viewEvaluationDetails,
			ViewPersonalInformation;
	private ImageView ViewLocation;
	// 评分
	private RatingBar ratingbar;
	String mResult, mPhone = null;
	// 返回
	private ImageView img_back;
	// 拨打电话
	private CustomDialog.Builder ibuilder;

	// serviceproviders_service
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.assignment);
		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.serviceproviders_service);

		inflater = LayoutInflater.from(this);
		// Bundle bundle = this.getIntent().getExtras();
		// mResult = bundle.getString("result");
		initView();
	}

	private void initView() {
		img_back = (ImageView) this.findViewById(R.id.view_top_img_back);
		img_back.setBackgroundResource(R.drawable.button_return);
		ViewLocation = (ImageView) findViewById(R.id.serviceproviders_viewLocation);
		viewEvaluationDetails = (Button) findViewById

		(R.id.serviceproviders_viewEvaluationDetails);
		IwantToeValuate = (Button) findViewById

		(R.id.serviceproviders_IwantToeValuate);
		ViewPersonalInformation = (Button) findViewById

		(R.id.serviceproviders_ViewPersonalInformation);
		img_back.setOnClickListener(l);
		ViewLocation.setOnClickListener(l);
		viewEvaluationDetails.setOnClickListener(l);
		IwantToeValuate.setOnClickListener(l);
		ViewPersonalInformation.setOnClickListener(l);

		Name = (TextView) findViewById(R.id.serviceproviders_name);
		Certification = (TextView) findViewById

		(R.id.serviceproviders_Certification);
		Type = (TextView) findViewById(R.id.serviceproviders_type);
		Impression = (TextView) findViewById(R.id.serviceproviders_impression);
		Address = (TextView) findViewById(R.id.serviceproviders_address);
		Phone = (TextView) findViewById(R.id.serviceproviders_Phone);
		Phone.setOnClickListener(l);
		Hours = (TextView) findViewById(R.id.serviceproviders_Hours);
		Price = (TextView) findViewById(R.id.serviceproviders_price);
		readme = (TextView) findViewById(R.id.serviceproviders_readme);
		Number = (TextView) findViewById(R.id.serviceproviders_number);

		ratingbar = (RatingBar) findViewById(R.id.small_ratingbar);
		// laction
		viewpager = (ViewPager) findViewById(R.id.viewpager);
		JSONObject json = new JSONObject();
		try {
			// 店名
			Name.setText(json.getString("name"));
			// Certification =1 营业执照已认证
			int mCertification = Integer.parseInt(json
					.getString("Certification"));
			if (mCertification == 1) {
				Certification.setText("营业执照已认证");
				Certification.setTextColor(Color.RED);
			} else {
				Certification.setText("营业执照未认证");
				Certification.setTextColor(Color.GRAY);
			}
			// 服务类型
			Type.setText(json.getString("type"));
			// rating 好友评分 值 在1~5之间
			ratingbar.setRating(Float.parseFloat(json.getString("rating")));
			// 车友印象
			Impression.setText(json.getString("impression"));
			// 地址
			Address.setText(json.getString("address"));
			// 查看位置

			// laction
			// 联系电话
			mPhone = json.getString("phone");

			Phone.setText(mPhone);
			// 营业时间
			Hours.setText(Integer.parseInt(json.getString("hours")) + "小时");
			// 服务价格
			Price.setText(json.getString("price"));
			// 店友自述
			readme.setText(json.getString("readme"));
			// 网友评价个数
			Number.setText(json.getString("number"));

		} catch (JSONException e) {
			e.printStackTrace();
		}
		// ratingbar.setRating("");
		views = createViews();
		mypagerAdapter = new MyPagerAdapter(views);
		viewpager.setAdapter(mypagerAdapter);

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

	private OnClickListener l = new OnClickListener() {

		@Override
		public void onClick(View V) {
			switch (V.getId()) {
			case R.id.view_top_img_back:
				defaultFinish();
				break;
			case R.id.serviceproviders_viewLocation:
				Intent intent = new Intent();
				intent.setClass

				(TransferFragmentAssignment.this, MapActivity.class);
				startActivity(intent);
				break;
			case R.id.serviceproviders_viewEvaluationDetails:
				startActivity(EvaluationActivity.class);
				break;
			case R.id.serviceproviders_IwantToeValuate:
				startActivity(CommentActivity.class);
				break;
			case R.id.serviceproviders_ViewPersonalInformation:
				
				
				break;
			case R.id.serviceproviders_Phone:
				ibuilder = new CustomDialog.Builder

				(TransferFragmentAssignment.this);
				ibuilder.setTitle("提示");
				ibuilder.setMessage("即将拨打电话：4000-622-688");
				ibuilder.setPositiveButton("拨打", new

				DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int

					which) {
						Uri uri = Uri.parse("tel:" + Phone);
						Intent intent = new Intent

						(Intent.ACTION_CALL, uri);
						startActivity(intent);
						dialog.dismiss();
					}
				});
				ibuilder.setNegativeButton("取消", null);
				ibuilder.create().show();
				break;
			}
		}
	};

	@Override
	protected void initViews() {

	}

	@Override
	protected void initEvents() {

	}

}
