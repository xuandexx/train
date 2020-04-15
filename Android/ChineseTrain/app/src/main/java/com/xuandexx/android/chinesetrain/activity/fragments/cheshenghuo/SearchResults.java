package activity.fragments.cheshenghuo;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.app.MyActivity;

/**
 * 查询结果 Search results
 * 
 * @author Administrator
 * 
 */
public class SearchResults extends MyActivity {
	String mResult = null;
	private TextView Total_score = null, Total_money = null, Count = null;
	private ImageView img_Back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_results);
		Bundle bundle = this.getIntent().getExtras();
		mResult = bundle.getString("result");
		initViews();
		initEvents();

	}

	@Override
	protected void initViews() {

		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.SearchResults_title);
		Total_score = (TextView) findViewById(R.id.SearchResults_mTotal_score);
		Total_money = (TextView) findViewById(R.id.SearchResults_mTotal_money);
		Count = (TextView) findViewById(R.id.SearchResults_mCount);
		img_Back = (ImageView) this.findViewById(R.id.view_top_img_back);
		img_Back.setBackgroundResource(R.drawable.button_return);
		img_Back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				defaultFinish();
			}
		});
	}

	@Override
	protected void initEvents() {

		Log.i("result+1", mResult);
		try {
			JSONObject json = new JSONObject(mResult);
			Log.i("result+4", mResult);
			// mPhone = json2.getString("phone");
			Total_score.setText(json.getInt("total_score"));
			Total_money.setText(json.getInt("total_money"));
			Count.setText(json.getInt("count"));
		} catch (JSONException e) {
			doLog(e.toString());
		}
	}

}
