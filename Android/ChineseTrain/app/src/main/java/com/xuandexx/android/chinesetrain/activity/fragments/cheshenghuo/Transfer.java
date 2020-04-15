package activity.fragments.cheshenghuo;

import java.util.Calendar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.MyApplication;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.app.MyActivity;
import cn.ieauto.autogroup.android.dialog.MyCarTimeDialog;

/**
 * 我要过户
 * 
 * @author Administrator
 * 
 */
public class Transfer extends MyActivity implements OnClickListener {
	// 返回
	private ImageView img_back;
	private TextView tv_title;
	private EditText es_ed_yj, es_ed_gf_type;
	private Button es_ed_year;
	private String yj, gf_type, year;
	// 提交
	private Button vehicleownership_button2;
	/**
	 * 时间
	 */
	private Calendar calendar = Calendar.getInstance();
	String initDate = calendar.get(Calendar.YEAR) + "年"
			+ calendar.get(Calendar.MONTH) + "月"
			+ calendar.get(Calendar.DAY_OF_MONTH) + "日";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.vehicleownership);
		initViews();
		initEvents();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_top_img_back:
			defaultFinish();
			break;
		case R.id.es_ed_yj:
			Intent intent = new Intent(Transfer.this, AddressActivity.class);
			startActivityForResult(intent, 1);
			break;
		case R.id.es_ed_gf_type:
			Intent typeintent = new Intent(Transfer.this,
					TransferActivity.class);
			startActivityForResult(typeintent, 2);
			break;
		case R.id.es_ed_sp_year:
			MyCarTimeDialog myCarTimeDialog = new MyCarTimeDialog(
					Transfer.this, initDate);
			myCarTimeDialog.datePicKDialog(es_ed_year);
			myCarTimeDialog.toast("请选择正确的上牌时间?");
			break;
		case R.id.vehicleownership_button2:
			yj = es_ed_yj.getText().toString().trim();
			gf_type = es_ed_gf_type.getText().toString().trim();
			year = es_ed_gf_type.getText().toString().trim();
			if (null == yj || "".equals(yj)) {
				toast("车辆原籍不能为空");
				return;
			}
			if (null == gf_type || "".equals(gf_type)) {
				toast("过户类型不能为空");
				return;
			}
			if (null == year || "".equals(year)) {
				toast("上牌年份不能为空");
				return;
			}
			break;
		}

	}

	@Override
	protected void initViews() {
		img_back = (ImageView) this.findViewById(R.id.view_top_img_back);
		img_back.setBackgroundResource(R.drawable.button_return);
		tv_title = (TextView) this.findViewById(R.id.view_top_tv_title);
		tv_title.setText("我要过户");
		es_ed_yj = (EditText) this.findViewById(R.id.es_ed_yj);
		es_ed_gf_type = (EditText) this.findViewById(R.id.es_ed_gf_type);
		es_ed_year = (Button) this.findViewById(R.id.es_ed_sp_year);
		vehicleownership_button2 = (Button) this
				.findViewById(R.id.vehicleownership_button2);
	}

	@Override
	protected void initEvents() {
		img_back.setOnClickListener(this);
		es_ed_yj.setOnClickListener(this);
		es_ed_gf_type.setOnClickListener(this);
		es_ed_year.setOnClickListener(this);
		vehicleownership_button2.setOnClickListener(this);
		if (TextUtils.isEmpty(MyApplication.getInstance().getCartime())) {
			es_ed_year.setText(initDate);
		} else {
			es_ed_year.setText(MyApplication.getInstance().getCartime());
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 1:
			if (data != null) {
				String hString = data.getStringExtra("province")
						+ data.getStringExtra("city")
						+ data.getStringExtra("town");
				es_ed_yj.setText(hString);
			}
			break;
		case 2:
			if (data != null) {
				es_ed_gf_type.setText(data.getStringExtra("province"));
			}
			break;
		default:
			break;
		}
	}
}
