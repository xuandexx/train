package activity.fragments.cheshenghuo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.activity.fragments.business.Mybusiness_Personal_1Activity;
import cn.ieauto.autogroup.android.app.MyActivity;

//免责申明
public class StatementActivity extends MyActivity implements
		View.OnClickListener {
	private Button statement_btnq, statement_btnn;
	private CheckBox statement_cb;
	private String result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statement);
		initViews();
		initEvents();
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
		result = intent.getStringExtra("result");
		statement_btnq = (Button) findViewById(R.id.statement_btnq);
		statement_btnn = (Button) findViewById(R.id.statement_btnn);
		statement_cb = (CheckBox) findViewById(R.id.statement_cb);
	}

	@Override
	protected void initEvents() {
		// // TODO Auto-generated method stub
		statement_btnq.setOnClickListener(this);
		statement_btnn.setOnClickListener(this);
		statement_cb.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.statement_btnq:
			if (statement_cb.isChecked()) {
				Intent intent = new Intent();
				 //判断接受的参数是否宪法通
				 if(result.equals(result)){
				 intent.setClass(StatementActivity.this,Mybusiness_Personal_1Activity.class);
				 startActivity(intent);
				 defaultFinish();
				 }
				
			} else {
				Toast.makeText(getApplicationContext(), "请先同意", 0).show();
			}
			break;
		case R.id.statement_btnn:
			defaultFinish();
			break;
		default:
			break;
		}
	}
}
