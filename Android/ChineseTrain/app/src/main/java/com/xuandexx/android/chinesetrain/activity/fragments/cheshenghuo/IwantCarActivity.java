package activity.fragments.cheshenghuo;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.app.MyActivity;

/**
 * 我要租车
 * 
 * @author Administrator
 * 
 */
public class IwantCarActivity extends MyActivity {
	private EditText et1, et2, et3;
	private Button button2;
	private ImageView img_Back;
	String Type, Time, Other;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.iwantcar);
		initViews();
		initStep();
	}

	@Override
	protected void initViews() {

		et1 = (EditText) findViewById(R.id.iwantcar_editText1);
		et2 = (EditText) findViewById(R.id.iwantcar_editText2);
		et3 = (EditText) findViewById(R.id.iwantcar_editText3);
		img_Back = (ImageView) this.findViewById(R.id.view_top_img_back);
		img_Back.setBackgroundResource(R.drawable.button_return);
		button2 = (Button) findViewById(R.id.iwantcar_button2);
		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.iwantcar);

	}

	private void initStep() {

		Type = et1.getText().toString();
		Time = et2.getText().toString();
		Other = et3.getText().toString();
		button2.setOnClickListener(button2listener);
	}

	private OnClickListener button2listener = new OnClickListener() {

		public void onClick(View v) {

		}
	};

	@Override
	protected void initEvents() {
		// TODO Auto-generated method stub

	}
}
