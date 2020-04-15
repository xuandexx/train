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
 * 我要卖车
 * 
 * @author Administrator
 * 
 */
public class SellCar extends MyActivity implements OnClickListener {
	private Button but_submit;
	private EditText et1, et2, et3, et4, et5, et6, et7;
	String[] cardealers = new String[7];
	private ImageView img_Back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.cardealers);
		initViews();
		initEvents();
	}

	@Override
	protected void initViews() {
		et7 = (EditText) findViewById(R.id.cardealers_et7);
		img_Back = (ImageView) this.findViewById(R.id.view_top_img_back);
		img_Back.setBackgroundResource(R.drawable.button_return);
		but_submit = (Button) findViewById(R.id.cardealers_but_submit);

	}

	@Override
	protected void initEvents() {
		but_submit.setOnClickListener(this);
		img_Back.setBackgroundResource(R.drawable.button_return);
		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.cardealers_title);
		cardealers[0] = et1.getText().toString();
		cardealers[1] = et2.getText().toString();
		cardealers[2] = et3.getText().toString();
		cardealers[3] = et4.getText().toString();
		cardealers[4] = et5.getText().toString();
		cardealers[5] = et6.getText().toString();
		cardealers[6] = et7.getText().toString();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_top_img_back:
			defaultFinish();
			break;
		case R.id.cardealers_but_submit:
			defaultFinish();
			break;
		default:
			break;
		}
	}

}
