package activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.app.MyActivity;

/**
 * 好友个人信息
 * 
 * @author Simon
 * 
 */
public class FriendInfoActivity extends MyActivity {
	// 头像，性别，个人相册
	private ImageView image1, image3;
	private ImageView img_Back;
	// 添加好友、发信息
	private Button button;
	// 姓名， 备注名，年龄，地区，个性签名，手机号，他的生意
	private TextView tv_name, tv_nickname, tv_where, tv5, tv_singnature,
			tv_age;
	private RelativeLayout layout_sex;
	private long exitTime = 0;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.passme_personalinformation);
		initViews();
		initEvents();
	}

	@Override
	protected void initViews() {
		img_Back = (ImageView) this.findViewById(R.id.view_top_img_back);

		image1 = (ImageView) findViewById(R.id.Personalinformation_imageview);
		image3 = (ImageView) findViewById(R.id.Personalinformation_imageview3);
		tv_name = (TextView) findViewById(R.id.Personalinformation_name);
		tv_nickname = (TextView) findViewById(R.id.Personalinformation_Remarksname);
		tv_age = (TextView) findViewById(R.id.Personalinformation_tv_age);
		layout_sex = (RelativeLayout) findViewById(R.id.Personalinformation_layout_sex);
		tv_where = (TextView) findViewById(R.id.Personalinformation_where);
		tv5 = (TextView) findViewById(R.id.Personalinformation_jm);
		tv_singnature = (TextView) findViewById(R.id.Personalinformation_BUSS);
		button = (Button) findViewById(R.id.Personalinformation_button2);

	}

	@Override
	protected void initEvents() {
		Intent intent = this.getIntent();
		tv_name.setText(intent.getExtras().getString("name"));

		img_Back.setBackgroundResource(R.drawable.button_return);
		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.Personalinformation_title);
		img_Back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				defaultFinish();

			}
		});
	}
}
