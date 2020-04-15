package activity.fragments.cheshenghuo;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.app.MyActivity;

/**
 * 求购详情
 * 
 * @author Administrator
 * 
 */
public class BuyDetails extends MyActivity {
	// 车型,车龄,排挡,排放标准,颜色
	private TextView tv1, tv2, tv3, tv4, tv5;
	// 价格,公里数,排量, 牌照地, 配置
	private TextView tv6, tv7, tv8, tv9, tv10;
	// 车款优先序,联系电话,买家自述
	private TextView tv11, tv12, tv13;
	// 查看评价详情, 我要评价,查 看 车 友 个 人 信 息
	private TextView tvbut1, tvbut2, tvbut3;
	private Button button;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.buy_details);
		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.buy_details_title);
		initView();
		initStep();
	}

	private void initView() {
		tv1 = (TextView) findViewById(R.id.buy_details_tv1);
		tv2 = (TextView) findViewById(R.id.buy_details_tv2);
		tv3 = (TextView) findViewById(R.id.buy_details_tv3);
		tv4 = (TextView) findViewById(R.id.buy_details_tv4);
		tv5 = (TextView) findViewById(R.id.buy_details_tv5);
		tv6 = (TextView) findViewById(R.id.buy_details_tv6);
		tv7 = (TextView) findViewById(R.id.buy_details_tv7);
		tv8 = (TextView) findViewById(R.id.buy_details_tv8);
		tv9 = (TextView) findViewById(R.id.buy_details_tv9);
		tv10 = (TextView) findViewById(R.id.buy_details_tv10);
		tv11 = (TextView) findViewById(R.id.buy_details_tv11);
		tv12 = (TextView) findViewById(R.id.buy_details_tv12);
		tv13 = (TextView) findViewById(R.id.buy_details_tv13);
		tvbut1 = (TextView) findViewById(R.id.buy_details_tvbut1);
		tvbut2 = (TextView) findViewById(R.id.buy_details_tvbut2);
		tvbut3 = (TextView) findViewById(R.id.buy_details_tvbut3);
		button = (Button) findViewById(R.id.view_top_img_back);
		tvbut1.setOnClickListener(listener);
		tvbut2.setOnClickListener(listener);
		tvbut3.setOnClickListener(listener);
		button.setOnClickListener(listener);
	}

	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.view_top_img_back:
				defaultFinish();
				finish();
				break;
			case R.id.buy_details_tvbut1:
				break;
			case R.id.buy_details_tvbut2:
				break;
			case R.id.buy_details_tvbut3:
				break;
			}
		}
	};

	private void initStep() {

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
