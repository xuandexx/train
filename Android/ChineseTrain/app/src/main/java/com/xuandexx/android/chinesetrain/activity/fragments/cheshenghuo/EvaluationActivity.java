package activity.fragments.cheshenghuo;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.adapter.EvaluationAdapter;
import cn.ieauto.autogroup.android.app.MyActivity;
import cn.ieauto.autogroup.android.entity.EvaluationEntity;
/**
 * 
 * 车友评价
 * @author lilongbing
 *
 */
public class EvaluationActivity extends MyActivity implements OnClickListener{
	//返回
	private ImageView img_back;
	private TextView tv_title;
	private ListView listview;
	private EvaluationAdapter mAdapter;
	private List<EvaluationEntity> list_data;
    
	private String [] level = {"4","3","5","2","1"};
	private String [] content = {"服务很不错","环境不行","还可以","服务差","服务不错"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ridersevaluation);
		initView();
	
	}
	private void initView() {
		img_back = (ImageView) this.findViewById(R.id.view_top_img_back);
		img_back.setBackgroundResource(R.drawable.button_return);
		img_back.setOnClickListener(this);
		tv_title = (TextView) this.findViewById(R.id.view_top_tv_title);
		tv_title.setText("评价详情");
		listview=(ListView) findViewById(R.id.ridersevaliation_listview);
		list_data = getitems();
		mAdapter = new EvaluationAdapter(EvaluationActivity.this,list_data);
		listview.setAdapter(mAdapter);
	}
	private List<EvaluationEntity> getitems(){
		List<EvaluationEntity> item = new ArrayList<EvaluationEntity>();
		for (int i = 0; i < level.length; i++) {
			EvaluationEntity entity = new EvaluationEntity();
			entity.setLevel(level[i]);
			entity.setContent(content[i]);
			item.add(entity);
		}
		return item;
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_top_img_back:
			defaultFinish();
			break;

		default:
			break;
		}
	}
	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void initEvents() {
		// TODO Auto-generated method stub
		
	}
}
