package activity.fragments.cheshenghuo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.app.MyActivity;

/**
 * 求助的四级界面,即点击求助列表详情信息
 * 
 * @author Administrator
 * 
 */
public class MySeekHelpActivity extends MyActivity implements OnClickListener {
	private Button Submit;
	private EditText ed;
	private CheckBox cbox1, cbox2, cbox3, cbox4, cbox5, cbox6;
	private AlertDialog dialog;
	String K = "";
	String phone = null, myphone = null;
	// 返回
	private ImageView img_back;
	private String content;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_seek_help_e4);
      getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); 

		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.iwanttohelp_title);

		// Intent intent = this.getIntent();
		// myphone = intent.getExtras().getString("myphone");
		// phone = intent.getExtras().getString("phone");
		initViews();
	}

	@Override
	protected void initViews() {
		img_back = (ImageView) this.findViewById(R.id.view_top_img_back);
		img_back.setBackgroundResource(R.drawable.button_return);
		img_back.setOnClickListener(this);
		Submit = (Button) findViewById(R.id.iwanttohelp_e4_button2);
		ed = (EditText) findViewById(R.id.iwanttohelp_e4_editText1);
		cbox1 = (CheckBox) findViewById(R.id.iwanttohelp_e4_checkBox1);
		cbox2 = (CheckBox) findViewById(R.id.iwanttohelp_e4_checkBox2);
		cbox3 = (CheckBox) findViewById(R.id.iwanttohelp_e4_checkBox3);
		cbox4 = (CheckBox) findViewById(R.id.iwanttohelp_e4_checkBox4);
		cbox5 = (CheckBox) findViewById(R.id.iwanttohelp_e4_checkBox5);
		cbox6 = (CheckBox) findViewById(R.id.iwanttohelp_e4_checkBox6);
		Submit.setOnClickListener(this);
		cbox1.setOnClickListener(this);
		cbox2.setOnClickListener(this);
		cbox3.setOnClickListener(this);
		cbox4.setOnClickListener(this);
		cbox5.setOnClickListener(this);
		cbox6.setOnClickListener(this);
	}

//	private void initcheckbox() {
////		cbox1.setChecked(true);
////		cbox2.setChecked(false);
////		cbox3.setChecked(false);
////		cbox4.setChecked(false);
////		cbox5.setChecked(false);
////		cbox6.setChecked(false);
//		if (cbox1.isChecked()) {
//			cbox1.setChecked(true);
//			cbox2.setChecked(false);
//			cbox3.setChecked(false);
//			cbox4.setChecked(false);
//			cbox5.setChecked(false);
//			cbox6.setChecked(false);
//			//K = K + "," + cbox1.getText();
//			cbox2.setChecked(true);
//			content = "事故遇险需急救";
//		}else if (cbox2.isChecked()) {
//			cbox2.setChecked(true);
//			cbox1.setChecked(false);
//			cbox3.setChecked(false);
//			cbox4.setChecked(false);
//			cbox5.setChecked(false);
//			cbox6.setChecked(false);
//			//K = K + "," + cbox2.getText();
//			content = "车辆自然";
//		}else if (cbox3.isChecked()) {
//			cbox3.setChecked(true);
//			cbox1.setChecked(false);
//			cbox2.setChecked(false);
//			cbox4.setChecked(false);
//			cbox5.setChecked(false);
//			cbox6.setChecked(false);
//			//K = K + "," + cbox3.getText();
//			content = "车辆换胎";
//		}else if (cbox4.isChecked()) {
//			cbox4.setChecked(true);
//			cbox1.setChecked(false);
//			cbox3.setChecked(false);
//			cbox2.setChecked(false);
//			cbox5.setChecked(false);
//			cbox6.setChecked(false);
//			K = K + "," + cbox4.getText();
//		}else if (cbox5.isChecked()) {
//			cbox5.setChecked(true);
//			cbox1.setChecked(false);
//			cbox3.setChecked(false);
//			cbox4.setChecked(false);
//			cbox2.setChecked(false);
//			cbox6.setChecked(false);
//			K = K + "," + cbox5.getText();
//		}else if (cbox6.isChecked()) {
//			cbox6.setChecked(true);
//			cbox1.setChecked(false);
//			cbox3.setChecked(false);
//			cbox4.setChecked(false);
//			cbox5.setChecked(false);
//			cbox2.setChecked(false);
//		}
//	}

	private void initdialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("确认内容");
		builder.setMessage(content);
		builder.setPositiveButton("确定", dialistener);
		builder.setNegativeButton("取消", dialistener);
		dialog = builder.create();
	}

	private DialogInterface.OnClickListener dialistener = new DialogInterface.OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			switch (which) {
			case DialogInterface.BUTTON_POSITIVE:

				break;
			case DialogInterface.BUTTON_NEGATIVE:

				break;
			}
		}
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_top_img_back:
			defaultFinish();
			break;
		case R.id.iwanttohelp_e4_button2:
			//initcheckbox();
//			K = K + "," + ed.getText();
			// MText[1]=ed.getText().toString();
			content=content+":留言为："+ed.getText();
			initdialog();
			dialog.show();
			break;
		case R.id.iwanttohelp_e4_checkBox1:
			if(cbox1.isChecked()) {
				cbox1.setChecked(true);
				cbox2.setChecked(false);
				cbox3.setChecked(false);
				cbox4.setChecked(false);
				cbox5.setChecked(false);
				cbox6.setChecked(false);
				cbox2.setChecked(false);
				content = "事故遇险需急救";
			}
			break;
		case R.id.iwanttohelp_e4_checkBox2:
			if(cbox2.isChecked()) {
				cbox2.setChecked(true);
				cbox1.setChecked(false);
				cbox3.setChecked(false);
				cbox4.setChecked(false);
				cbox5.setChecked(false);
				cbox6.setChecked(false);
				content = "车辆自燃";
			}
			break;
		case R.id.iwanttohelp_e4_checkBox3:
			if(cbox3.isChecked()) {
				cbox3.setChecked(true);
				cbox2.setChecked(false);
				cbox1.setChecked(false);
				cbox4.setChecked(false);
				cbox5.setChecked(false);
				cbox6.setChecked(false);
				cbox2.setChecked(false);
				content = "车辆换胎帮助";
			}
			break;
		case R.id.iwanttohelp_e4_checkBox4:
			if(cbox4.isChecked()) {
				cbox4.setChecked(true);
				cbox2.setChecked(false);
				cbox1.setChecked(false);
				cbox3.setChecked(false);
				cbox5.setChecked(false);
				cbox6.setChecked(false);
				cbox2.setChecked(false);
				content = "车辆换胎工具";
			}
			break;
		case R.id.iwanttohelp_e4_checkBox5:
			if(cbox5.isChecked()) {
				cbox5.setChecked(true);
				cbox2.setChecked(false);
				cbox1.setChecked(false);
				cbox4.setChecked(false);
				cbox6.setChecked(false);
				cbox3.setChecked(false);
				cbox2.setChecked(false);
				content = "车辆没油";
			}
			break;
		case R.id.iwanttohelp_e4_checkBox6:
			if(cbox6.isChecked()) {
				cbox6.setChecked(true);
				cbox2.setChecked(false);
				cbox1.setChecked(false);
				cbox4.setChecked(false);
				cbox5.setChecked(false);
				cbox3.setChecked(false);
				cbox2.setChecked(false);
				content = "车辆抛锚";
			}
			break;
		}
		
	}

	@Override
	protected void initEvents() {

	}
}
