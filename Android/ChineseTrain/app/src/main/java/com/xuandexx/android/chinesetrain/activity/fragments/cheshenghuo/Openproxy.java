package activity.fragments.cheshenghuo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.app.ComConfig;
import cn.ieauto.autogroup.android.app.MyActivity;

/**
 * 我要开委托书
 * 
 * @author Administrator
 * 
 */
public class Openproxy extends MyActivity implements OnClickListener {
//	private TextView tv;
	private Button but_submit;
	private ImageView img_Back;
	private Button but_newcity, but_othercity;
	private Intent intent;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.openproxy);
		initViews();
		initEvents();
	}

	@Override
	protected void initViews() {
		// tv = (TextView) findViewById(R.id.openproxy_textView5);
		img_Back = (ImageView) this.findViewById(R.id.view_top_img_back);
		but_newcity = (Button) findViewById(R.id.openproxy_but_new_city);
		but_othercity = (Button) findViewById(R.id.openproxy_but_other_city);
		but_submit = (Button) findViewById(R.id.openproxy_but_submit);

	}

	@Override
	protected void initEvents() {
		img_Back.setBackgroundResource(R.drawable.button_return);
		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.vehicletnspection_commission_title);

		img_Back.setOnClickListener(this);
		but_newcity.setOnClickListener(this);
		but_othercity.setOnClickListener(this);
		but_submit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_top_img_back:
			defaultFinish();
			break;
		case R.id.openproxy_but_new_city:
			intent = new Intent(Openproxy.this, AddressCityActivity.class);
			startActivityForResult(intent, ComConfig.INTENT_CITY);
			break;
		case R.id.openproxy_but_other_city:
			intent = new Intent(Openproxy.this, AddressCityActivity.class);
			startActivityForResult(intent, ComConfig.INTENT_BRAND_CARS);
			break;
		case R.id.openproxy_but_submit:
			
			defaultFinish();
			break;
		}
	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		super.onActivityResult(arg0, arg1, arg2);
		switch (arg0) {
		case ComConfig.INTENT_CITY:
			but_newcity.setText(arg2.getStringExtra("province") + "\t"
					+ arg2.getStringExtra("city"));
			break;
		case ComConfig.INTENT_BRAND_CARS:
			but_othercity.setText(arg2.getStringExtra("province") + "\t"
					+ arg2.getStringExtra("city"));
			break;

		default:
			break;
		}
	}
}
