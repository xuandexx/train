package adapter;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.SerciveCompanyActivity;
import cn.ieauto.autogroup.android.entity.ComPanyEnity;

public class AutoBeautyAdapter extends BaseAdapter {

	private Context mContext;
	private List<ComPanyEnity> list_data;
	// 用来控制CheckBox的选中状况
	private static HashMap<Integer, Boolean> isSelected;

	public AutoBeautyAdapter(Context context, List<ComPanyEnity> data) {
		this.list_data = data;
		this.mContext = context;
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
	public View getView(int position, View convertView, ViewGroup arg2) {
		ComPanyEnity entity = list_data.get(position);
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_vehicleownership_listview, null);
			holder.ls_img_icon = (ImageView) convertView
					.findViewById(R.id.ls_img_icon);
			holder.ls_img_level = (ImageView) convertView
					.findViewById(R.id.ls_img_level);
			holder.name = (TextView) convertView
					.findViewById(R.id.ls_name_shop);
			holder.priece = (TextView) convertView
					.findViewById(R.id.ls_tv_shange);
			holder.ls_cb = (CheckBox) convertView.findViewById(R.id.ls_cb);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.name.setText(entity.getName());
		holder.priece.setText("收费:" + entity.getPriece());
		// 根据isSelected来设置checkbox的选中状况
		holder.ls_cb.setChecked(getIsSelected().get(position));
		holder.ls_img_icon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(mContext,
						SerciveCompanyActivity.class);
				mContext.startActivity(intent);
			}
		});
		return convertView;
	}

	public static HashMap<Integer, Boolean> getIsSelected() {
		return isSelected;
	}

	public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
		AutoBeautyAdapter.isSelected = isSelected;
	}

	class ViewHolder {
		private ImageView ls_img_icon, ls_img_level;
		private TextView name, priece;
		private CheckBox ls_cb;
	}
}
