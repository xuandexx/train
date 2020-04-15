package activity.fragments.cheshenghuo;

import java.util.Calendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.adapter.BrandAdapter;
import cn.ieauto.autogroup.android.app.ComConfig;
import cn.ieauto.autogroup.android.app.MyActivity;
import cn.ieauto.autogroup.android.dialog.MyDatePickDialog;

public class BuyToCarActivity extends MyActivity implements OnClickListener {
	private TextView ih_tv_carType, ih_tv_carColr, ih_tv_carNumberAddress,
			ih_tv_carTime, ih_tv_carlichen;
	private TextView buyCarType, buyCarColor, buyCarAddress,editTime,
			buylichen;
	private EditText text_car_ks, car_question;
	private Button buy_car_submit;
	private Button buyCarTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.buy_to_car);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.buy_to_car);
		initViews();
		// initEvents();
	}
	@Override
	protected void initViews() {
//		 TODO Auto-generated method stub
		 ih_tv_carType=(TextView)findViewById(R.id.ih_tv_carType);
		ih_tv_carColr = (TextView) findViewById(R.id.ih_tv_carColr);
		 ih_tv_carNumberAddress=(TextView)findViewById(R.id.ih_tv_carNumberAddress);
		 ih_tv_carlichen=(TextView)findViewById(R.id.ih_tv_carlichen);
		buyCarType = (TextView) findViewById(R.id.buyCarType);
		buyCarColor = (TextView) findViewById(R.id.buyCarColor);
		buyCarAddress = (TextView) findViewById(R.id.buyCarAddress);
		ih_tv_carNumberAddress = (TextView) findViewById(R.id.ih_tv_carNumberAddress);
		buylichen = (TextView) findViewById(R.id.buylichen);
		buyCarTime = (Button) findViewById(R.id.buyCarTime);
		buyCarTime.setText("选择的s时间是:");
		buyCarType.setOnClickListener(this);
		buyCarColor.setOnClickListener(this);
		buyCarAddress.setOnClickListener(this);
		buylichen.setOnClickListener(this);
		buyCarTime.setOnClickListener(this);

	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.buyCarType:
			//车系
			Intent intent1=new Intent();
			intent1.setClass(BuyToCarActivity.this, BrandAdapter.class);
			startActivityForResult(intent1, ComConfig.INTENT_BRAND_CARS);
			break;
		case R.id.buyCarColor:
			Intent intent2 = new Intent();
			intent2.setClass(BuyToCarActivity.this, ColorActivity.class);
			startActivityForResult(intent2, ComConfig.INTENT_CARS_COLORS);
			break;
		case R.id.buyCarAddress:
			Intent intent3 = new Intent(BuyToCarActivity.this,
					AddressActivity.class);
			startActivityForResult(intent3, ComConfig.INTENT_CITY);
			break;
		case R.id.buylichen:
			Intent intent4 = new Intent(BuyToCarActivity.this,
					CourseActivity.class);
			startActivityForResult(intent4, ComConfig.INTENT_CARS_ASSMENT);
			break;
		case R.id.buyCarTime:
			Calendar calendar;
			calendar=Calendar.getInstance();
			String initDate = calendar.get(Calendar.YEAR) + "年"
					+ calendar.get(Calendar.MONTH) + "月"
					+ calendar.get(Calendar.DAY_OF_MONTH) + "日";
			MyDatePickDialog myDatePickDialog = new MyDatePickDialog(
					BuyToCarActivity.this, initDate);
			myDatePickDialog.datePicKDialog(buyCarTime);
			myDatePickDialog.toast("请选择正确的上牌时间?");
//			mCarTime = but_CarTime.getText().toString().trim();
			break;
		default:
			break;
		}
	}

	// 把选中的值传递
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case ComConfig.INTENT_BRAND_CARS:
			if (data != null) {
				ih_tv_carType.setText(data.getStringExtra("province")+ data.getStringExtra("city"));
			}
			break;
		case ComConfig.INTENT_CARS_COLORS:
			if (data != null) {
				ih_tv_carColr.setText(data.getStringExtra("province"));
			}
			break;
		case ComConfig.INTENT_CITY:
			if (data != null) {
				ih_tv_carNumberAddress.setText(data.getStringExtra("province")
						+ data.getStringExtra("city")
						+ data.getStringExtra("town"));
			}
			break;
		case ComConfig.INTENT_CARS_ASSMENT:
			if (data != null) {
				ih_tv_carlichen.setText(data.getStringExtra("province"));
			}
			break;
		}
	}
	@Override
	protected void initEvents() {
		// TODO Auto-generated method stub
		
	}

}
