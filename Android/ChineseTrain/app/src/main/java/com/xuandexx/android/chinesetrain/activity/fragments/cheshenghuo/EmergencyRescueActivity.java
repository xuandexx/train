package activity.fragments.cheshenghuo;

import java.util.ArrayList;
import java.util.List;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.adapter.EmergencyRescueAdapter;
import cn.ieauto.autogroup.android.adapter.EmergencyRescueAdapter.ErViewHolder;
import cn.ieauto.autogroup.android.app.MyActivity;
import cn.ieauto.autogroup.android.dialog.CustomDialog;
import cn.ieauto.autogroup.android.entity.EmergencyRescue;

/**
 * 紧急救援
 * 
 * @author Administrator
 * 
 */
public class EmergencyRescueActivity extends MyActivity implements
		OnClickListener {
	private Button Submit;
	String mCompanyName = null, mPhone = null, mAddress = null;
	String mResult;
	Bitmap bitmap;
	String mPicture = null;
	// 返回
	private ImageView img_back;
	private ListView listview;
	private EmergencyRescueAdapter mAdapter;
	private List<EmergencyRescue> list_data;
	// 全选
	private CheckBox er_cb;
	// 单选
	private CheckBox no_er_cb;

	private int checkNum; // 记录选中的条目数量
	private ArrayList<String> list;
	// 电话
	private RelativeLayout er_rl_tel;
	private CustomDialog.Builder ibuilder;
	private String[] name = { "好好修理店", "好好修理店", "好好修理店", "好好修理店", "好好修理店" };
	private String[] jl = { "500米", "500米", "500米", "500米", "500米" };
	private String[] addres = { "上海市浦东大道880号", "上海市浦东大道880号", "上海市浦东大道880号",
			"上海市浦东大道880号", "上海市浦东大道880号" };
	private String[] phone = { "021-88888888", "021-88888888", "021-88888888",
			"021-88888888", "021-88888888" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_emergency_rescue);
		TextView title = (TextView) findViewById(R.id.view_top_tv_title);
		title.setText(R.string.wanttorescue_title);

		initViews();
		initEvents();

	}

	private List<EmergencyRescue> getListItems() {
		List<EmergencyRescue> data = new ArrayList<EmergencyRescue>();
		for (int i = 0; i < name.length; i++) {
			EmergencyRescue rescue = new EmergencyRescue();
			rescue.setCompanyname(name[i]);
			rescue.setDistance(jl[i]);
			rescue.setAddress(addres[i]);
			rescue.setPhone(phone[i]);
			data.add(rescue);
		}
		return data;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_top_img_back:
			defaultFinish();
			break;
		case R.id.er_rl_tel:
			ibuilder = new CustomDialog.Builder(EmergencyRescueActivity.this);
			ibuilder.setTitle("提示");
			ibuilder.setMessage("即将拨打电话：4000-622-688");
			ibuilder.setPositiveButton("拨打",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							Uri uri = Uri.parse("tel:" + "4000-622-688");
							Intent intent = new Intent(Intent.ACTION_CALL, uri);
							startActivity(intent);
							dialog.dismiss();
						}
					});
			ibuilder.setNegativeButton("取消", null);
			ibuilder.create().show();
			break;
		case R.id.wanttorescue_button2:
//			ErViewHolder hoder = new ErViewHolder();
//			hoder.no_er_cb.toggle();111111
//			if(hoder.no_er_cb.isChecked()){
//				startActivity(MySeekHelpActivity.class);
//			}
//			else{
//			
//			}
			if(er_cb.isChecked()){
				Toast.makeText(getApplicationContext(), "选中的条数" + checkNum, 0)
				.show();
				startActivity(MySeekHelpActivity.class);
			}
			if(checkNum<list_data.size()){
				er_cb.setChecked(false);
				if(checkNum<0){
					Toast.makeText(getApplicationContext(), "没有选中", 0).show();
				}
				else{
					startActivity(MySeekHelpActivity.class);
				}
			}
			break;
		case R.id.er_cb:
			// 全选的按钮
			if (er_cb.isChecked()) {
				for (int i = 0; i < list_data.size(); i++) {
					EmergencyRescueAdapter.getIsSelected().put(i, true);
				}
				checkNum = list_data.size();
				dataChanged();
			} else {
				// 遍历list的长度，将已选的按钮设为未选
				for (int i = 0; i < list_data.size(); i++) {
					if (EmergencyRescueAdapter.getIsSelected().get(i)) {
						EmergencyRescueAdapter.getIsSelected().put(i, false);
						checkNum--;// 数量减1
					} else {
						EmergencyRescueAdapter.getIsSelected().put(i, true);
						checkNum++;
					}
				}
				// 刷新listview
				dataChanged();
			}
			break;
		}
	}

	@Override
	protected void initViews() {
		img_back = (ImageView) this.findViewById(R.id.view_top_img_back);
		img_back.setBackgroundResource(R.drawable.button_return);
		er_rl_tel = (RelativeLayout) findViewById(R.id.er_rl_tel);
		listview = (ListView) findViewById(R.id.wanttorescue_listview);
		Submit = (Button) findViewById(R.id.wanttorescue_button2);
		list_data = getListItems();
		mAdapter = new EmergencyRescueAdapter(this, list_data);
		listview.setAdapter(mAdapter);
		er_cb = (CheckBox) this.findViewById(R.id.er_cb);

		// LayoutInflater factorys = LayoutInflater
		// .from(EmergencyRescueActivity.this);
		// final View textEntryView = factorys.inflate(
		// R.layout.item_carabouttabhost, null);
		// CheckBox no_er_cb = (CheckBox) textEntryView
		// .findViewById(R.id.no_er_cb);
		// no_er_cb.setOnClickListener(this);
	}

	@Override
	protected void initEvents() {
		Submit.setOnClickListener(this);
		er_rl_tel.setOnClickListener(this);
		er_cb.setOnClickListener(this);
		img_back.setOnClickListener(this);
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int poistion, long arg3) {
				// TODO Auto-generated method stub
				ErViewHolder holder = (ErViewHolder) view.getTag();
				// 改变CheckBox的状态
				holder.no_er_cb.toggle();
				// 将checkbox的选中的庄康记录下来的
				EmergencyRescueAdapter.getIsSelected().put(poistion,
						holder.no_er_cb.isChecked());
				// 调整选定条目
				if (holder.no_er_cb.isChecked() == true) {
					checkNum++;
				} else {
					checkNum--;
				}
			}
		});
	}

	// 刷新listview
	private void dataChanged() {
		// 通知listView刷新
		mAdapter.notifyDataSetChanged();
//		Toast.makeText(getApplicationContext(), "选中的条数" + checkNum, 0).show();
	};
}
