package adapter;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.activity.FriendInfoActivity;
import cn.ieauto.autogroup.android.entity.SeekHelpEntity;

public class SeekHelpAdapter extends BaseAdapter {
	private Context mContext;
	private List<SeekHelpEntity> list_data;
	private LayoutInflater mInflater;
	private ViewHolder holder;
	private SeekHelpEntity entity;
	// 用来控制CheckBox的选中状况
	private static HashMap<Integer, Boolean> isSelected;

	public SeekHelpAdapter(Context context, List<SeekHelpEntity> list_data) {
		this.mContext = context;
		this.list_data = list_data;
		mInflater = LayoutInflater.from(mContext);
		isSelected = new HashMap<Integer, Boolean>();
		initDate();
	}

	// 初始化isSelected的数据
	private void initDate() {
		for (int i = 0; i < list_data.size(); i++) {
			getIsSelected().put(i, false);
		}
	}

	@Override
	public int getCount() {
		return list_data.size();
	}

	@Override
	public Object getItem(int position) {
		return list_data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		entity = list_data.get(position);
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.item_seekhelp, null);
			holder.checkbox = (CheckBox) convertView
					.findViewById(R.id.item_seekhelp_checbox);
			holder.img_icon = (ImageView) convertView
					.findViewById(R.id.item_seekhelp_img_icon);
			holder.tv_name = (TextView) convertView
					.findViewById(R.id.item_seekhelp_tv_name);
			holder.tv_age = (TextView) convertView
					.findViewById(R.id.item_seekhelp_tv_age);
			holder.tv_distance = (TextView) convertView
					.findViewById(R.id.item_seekhelp_tv_signature);
			holder.layout_sex = (RelativeLayout) convertView
					.findViewById(R.id.item_seekhelp_layout_sex);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// 根据isSelected来设置checkbox的选中状况
		holder.checkbox.setChecked(getIsSelected().get(position));
		holder.tv_name.setText(entity.getName());
		holder.tv_age.setText(entity.getAge());
		holder.tv_distance.setText(entity.getDistance());
		if ("男".equals(entity.getSex())) {
			holder.layout_sex.setBackgroundResource(R.drawable.man_1);
		} else if ("女".equals(entity.getSex())) {
			holder.layout_sex.setBackgroundResource(R.drawable.woman_1);
		}

		holder.img_icon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, FriendInfoActivity.class);
				
				Bundle bundle=new Bundle();
				bundle.putString("name",entity.getName() );
				bundle.putString("age", entity.getAge());
				bundle.putString("sex", entity.getSex());
				intent.putExtras(bundle);
				mContext.startActivity(intent);
			}
		});

		return convertView;
	}

	public static HashMap<Integer, Boolean> getIsSelected() {
		return isSelected;
	}

	public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
		SeekHelpAdapter.isSelected = isSelected;
	}

	class ViewHolder {
		private ImageView img_icon;
		private TextView tv_name, tv_age, tv_distance;
		private RelativeLayout layout_sex;
		private CheckBox checkbox;
	}

}
