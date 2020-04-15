package activity.fragments.business;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.adapter.SpinnerAdapter;
import cn.ieauto.autogroup.android.app.MyActivity;
import cn.ieauto.autogroup.android.entity.Ruser;
import cn.ieauto.autogroup.android.util.DataAcquisitionUtil;

/**
 * 申请服务 公司
 * 
 * @author Administrator 洗车美容,道路救援,维修保养,装潢改装,代办理赔,租赁公司,车辆收购
 */
public class Mybusiness_CompanyActivity extends MyActivity implements
		OnClickListener {
	// 返回， 申请提交
	private ImageView img_Back;
	private Button Submit;
	// 营业执照，标记地图，上传图片
	private Button button2, button3, button4;
	// 公司，营业执照编号，地址，电话，营业时间，收费标准，商家自述]'
	private EditText CompanyName, Businesslicensenumber, Address, Phone,
			Charges, Readme;
	private String mCompanyName, mBusinesslicensenumber, mAddress, mPhone,
			mServiceHours, mCharges, mReadme;
	private Spinner spinner1, spinner2, spinner3, spinner4;
	private TextView tv_title;
	private String mSpinner1, mSpinner2, mSpinner3, mSpinner4;
	String message = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mybusiness_company);
		initViews();
//		initEvents();
//		tv_title.setText(this.getIntent().getExtras().getString("title"));
	}

	@Override
	protected void initViews() {
		
		img_Back = (ImageView) this.findViewById(R.id.view_top_img_back);
		img_Back.setBackgroundResource(R.drawable.button_return);
		tv_title = (TextView) findViewById(R.id.view_top_tv_title);

		button2 = (Button) findViewById(R.id.mybusiness_company_button2);
		button3 = (Button) findViewById(R.id.mybusiness_company_button3);
		button4 = (Button) findViewById(R.id.mybusiness_company_button4);
		Submit = (Button) findViewById(R.id.mybusiness_company_Submit);
		CompanyName = (EditText) findViewById(R.id.mybusiness_company_CompanyName);
		Businesslicensenumber = (EditText) findViewById(R.id.mybusiness_company_Businesslicensenumber);
		Address = (EditText) findViewById(R.id.mybusiness_company_Address);
		Phone = (EditText) findViewById(R.id.mybusiness_company_phone);
		Charges = (EditText) findViewById(R.id.mybusiness_company_Charges);
		Readme = (EditText) findViewById(R.id.mybusiness_company_Readme);
		spinner1 = (Spinner) findViewById(R.id.mybusiness_company_spinner1);
		spinner2 = (Spinner) findViewById(R.id.mybusiness_company_spinner2);
		spinner3 = (Spinner) findViewById(R.id.mybusiness_company_spinner3);
		spinner4 = (Spinner) findViewById(R.id.mybusiness_company_spinner4);
		// tv_title = (TextView) findViewById(R.id.mybusiness_company_tv);

	}

	@Override
	protected void initEvents() {
		img_Back.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
		button4.setOnClickListener(this);
		Submit.setOnClickListener(this);

		SpinnerAdapter adapter1 = new SpinnerAdapter(this,
				R.id.mybusiness_company_spinner1, Ruser.string.ServiceHours);
		spinner1.setAdapter(adapter1);
		spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				mSpinner1 = spinner1.getItemAtPosition(arg2).toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		SpinnerAdapter adapter2 = new SpinnerAdapter(this,
				R.id.mybusiness_company_spinner2, Ruser.string.ServiceMinutes);
		spinner2.setAdapter(adapter2);
		spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				mSpinner2 = spinner2.getItemAtPosition(arg2).toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		SpinnerAdapter adapter3 = new SpinnerAdapter(this,
				R.id.mybusiness_company_spinner3, Ruser.string.ServiceHours);
		spinner3.setAdapter(adapter3);
		spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				mSpinner3 = spinner3.getItemAtPosition(arg2).toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		SpinnerAdapter adapter4 = new SpinnerAdapter(this,
				R.id.mybusiness_company_spinner4, Ruser.string.ServiceMinutes);
		spinner4.setAdapter(adapter4);
		spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				mSpinner4 = spinner4.getItemAtPosition(arg2).toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
	}

	private void initEdit() {
		mCompanyName = CompanyName.getText().toString();
		mBusinesslicensenumber = Businesslicensenumber.getText().toString();
		mAddress = Address.getText().toString();
		mPhone = Phone.getText().toString();
		mCharges = Charges.getText().toString();
		mReadme = Readme.getText().toString();

	}

	public void onClick(View v) {
		switch (v.getId()) {
		/*
		 * case R.id.mybusiness_company_Back: defaultFinish(); break;
		 */
		case R.id.view_top_img_back:
			defaultFinish();
			break;
		case R.id.mybusiness_company_button2:

			break;
		case R.id.mybusiness_company_button3:

			break;
		case R.id.mybusiness_company_button4:

			break;
		case R.id.mybusiness_company_Submit:

			initEdit();
			DataAcquisitionUtil.DataAcquisition(message, 109 - 5,
					Mybusiness_CompanyActivity.this);
			break;

		}

	}

}
