package activity.fragments.cheshenghuo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;

/**
 * 服务详情
 * 
 * @author Administrator
 * 
 */
public class ServiceDetails extends Activity implements OnClickListener {
	private ImageView img_Back;
	//暂停,终止
	private Button but_Pause, but_Annulment;
	private TextView tv_exp;
	String title = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service_details);
		title = this.getIntent().getExtras().getString("title");

		initView();
		initStep();
	}

	private void initView() {
		img_Back = (ImageView) this.findViewById(R.id.view_top_img_back);
		img_Back.setBackgroundResource(R.drawable.button_return);
		((TextView) findViewById(R.id.view_top_tv_title)).setText(title);
		but_Pause = (Button) findViewById(R.id.ServiceDetails_but_pause);
		but_Annulment = (Button) findViewById(R.id.ServiceDetails_but_annulment);
		tv_exp = (TextView) findViewById(R.id.ServiceDetails_tv_Exp);
		but_Pause.setOnClickListener(this);
		but_Annulment.setOnClickListener(this);
	}

	private void initStep() {
		tv_exp.setText("");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ServiceDetails_but_pause:

			break;
		case R.id.ServiceDetails_but_annulment:
			
			break;
		}
	}

}
