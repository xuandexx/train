package adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.app.MyActivity;

/**
 * 填写个人信息
 * 
 * @author Lampo
 * 
 */
public class DataFillAdapter extends MyActivity implements OnClickListener {
	/** 完成 */
	private Button but_submit;
	/** 返回 */
	private ImageView img_back;
	/** 内容 */
	private EditText ed_text;
	/** 标题 */
	private TextView tv_title;
	Intent intent = new Intent();
	String string;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data_fill_adapter);
		initViews();
		initEvents();
	}

	@Override
	protected void initViews() {
		but_submit = (Button) findViewById(R.id.datafill_but_ok);
		img_back = (ImageView) findViewById(R.id.datafill_img_back);
		ed_text = (EditText) findViewById(R.id.datafill_ed_text);
		tv_title = (TextView) findViewById(R.id.datafill_tv_title);
	}

	@Override
	protected void initEvents() {
		Bundle bundle = this.getIntent().getExtras();
		string = bundle.getString("title");
		System.out.println("title=" + string);
		tv_title.setText(string);
		but_submit.setOnClickListener(this);
		img_back.setOnClickListener(this);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		setResult(DataFillAdapter.RESULT_CANCELED, intent);
		finish();
		return super.onKeyDown(keyCode, event);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.datafill_img_back:
			setResult(DataFillAdapter.RESULT_CANCELED, intent);
			finish();
			break;
		case R.id.datafill_but_ok:

			String mTextString = ed_text.getText().toString().trim();
			intent.putExtra("text", mTextString);
			if (string == "号码" && (mTextString.length() > 5)) {
				return;
			}
			setResult(1, intent);
			defaultFinish();
			break;

		}
	}
}
