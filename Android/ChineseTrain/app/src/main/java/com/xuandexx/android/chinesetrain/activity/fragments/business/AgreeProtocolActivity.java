package activity.fragments.business;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.app.MyActivity;

/**
 * 同意使用条款和隐私政策
 * 
 * @author Administrator
 * 
 */
public class AgreeProtocolActivity extends MyActivity {
	private ImageView img_Back;
	private TextView title;
	private CheckBox readandagre_Ck;
	private Button readandagree_but1;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.readandagree);
		initViews();
		initEvents();
	}

	@Override
	protected void initViews() {
		title = (TextView) findViewById(R.id.view_top_tv_title);
		img_Back = (ImageView) this.findViewById(R.id.view_top_img_back);

		readandagre_Ck = (CheckBox) findViewById(R.id.readandagre_Ck);
		readandagree_but1 = (Button) findViewById(R.id.readandagree_but1);
	}

	@Override
	protected void initEvents() {
		title.setText(R.string.title_readandagree);
		img_Back.setBackgroundResource(R.drawable.button_return);
		String visibility = this.getIntent().getExtras()
				.getString("visibility");
		if ("1".equals(visibility)) {

			readandagre_Ck.setVisibility(View.GONE);
			readandagree_but1.setVisibility(View.GONE);

		}
		img_Back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				defaultFinish();
			}
		});

	}
}
