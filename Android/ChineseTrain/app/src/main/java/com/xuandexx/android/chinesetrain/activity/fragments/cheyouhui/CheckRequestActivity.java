package activity.fragments.cheyouhui;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.app.MyActivity;

/**
 * 新朋友请求处理
 * 
 * @author Administrator
 * 
 */
public class CheckRequestActivity extends MyActivity implements OnClickListener {
	// 返回，同意，不同意
	private ImageView img_Back;
	private Button but_Agree, but_Disagree;
	// 姓名，地区,个人签名
	private TextView tv_nickname = null, tv_region, tv_signature;
	// 头像 ，性别
	private ImageView image_vatar, image_sex;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check_request);
		initViews();
		initEvents();
	}

	@Override
	protected void initViews() {
		tv_nickname = (TextView) findViewById(R.id.authentic_information_nickname);
		tv_region = (TextView) findViewById(R.id.authentic_information_textView2);
		tv_signature = (TextView) findViewById(R.id.authentic_tv_signature);
		image_vatar = (ImageView) findViewById(R.id.authentic_information_imageView1);
		image_sex = (ImageView) findViewById(R.id.authentic_information_imageView2);
		img_Back = (ImageView) findViewById(R.id.view_top_img_back);

		but_Agree = (Button) findViewById(R.id.check_request_agree);
		but_Disagree = (Button) findViewById(R.id.check_request_disagree);

	}

	@Override
	protected void initEvents() {
		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.authenticationrequest_ar);
		img_Back.setBackgroundResource(R.drawable.button_return);
		img_Back.setOnClickListener(this);
		but_Agree.setOnClickListener(this);
		but_Disagree.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_top_img_back:
			defaultFinish();
			break;
		/**
		 * 点击同意,添加该联系人到好友数据库
		 */
		case R.id.check_request_agree:
			Toast.makeText(CheckRequestActivity.this, "你已成功添加",
					Toast.LENGTH_SHORT).show();
			break;
		/**
		 * 点击不同意,删除请求列表数据库的对应条目
		 */
		case R.id.check_request_disagree:
			defaultFinish();
			break;

		}
	}

}
