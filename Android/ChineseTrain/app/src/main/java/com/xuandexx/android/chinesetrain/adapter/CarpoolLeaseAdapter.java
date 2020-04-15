package adapter;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.dialog.CustomDialog;
import cn.ieauto.autogroup.android.entity.CarpoolLeaseEntity;
import cn.ieauto.autogroup.android.entity.EmergencyRescue;

public class CarpoolLeaseAdapter extends BaseAdapter {

	private Context mContext;
	private List<CarpoolLeaseEntity> list_data;
	private LayoutInflater mInflater;
	private ViewHolder holder;
	private EmergencyRescue rescue;
	private CustomDialog.Builder ibuilder;
	// 用来控制CheckBox的选中状况
	private static HashMap<Integer, Boolean> isSelected;

	public CarpoolLeaseAdapter(Context context, List<CarpoolLeaseEntity> data) {
		this.list_data = data;
		this.mContext = context;
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
	public View getView(int position, View convertView, ViewGroup arg2) {
		ViewHolder holder;
		CarpoolLeaseEntity entity = list_data.get(position);
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater
					.inflate(R.layout.item_carabouttabhost, null);
			holder.icon = (ImageView) convertView
					.findViewById(R.id.er_img_icon);
			holder.er_cb = (CheckBox) convertView.findViewById(R.id.er_cb);
			// 车品牌
			holder.er_tv_name_shop = (TextView) convertView
					.findViewById(R.id.er_tv_name_shop);
			// 档位牌照
			holder.er_tv_distance = (TextView) convertView
					.findViewById(R.id.er_tv_distance);
			// 年限
			holder.er_tv_map = (TextView) convertView
					.findViewById(R.id.er_tv_map);
			// 租借或驾驶
			holder.er_tv_address = (TextView) convertView
					.findViewById(R.id.er_tv_address);
			// 自由时间
			holder.er_tv_name_phone = (TextView) convertView
					.findViewById(R.id.er_tv_phone);
			holder.er_tv_name_phone.setVisibility(View.VISIBLE);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.er_tv_name_shop.setText(entity.getPp());
		holder.er_tv_distance.setText(entity.getPz());
		holder.er_tv_map.setText(entity.getDate());
		holder.er_tv_address.setText(entity.getZj());
		holder.er_tv_name_phone.setText(entity.getTime());
		// 根据isSelected来设置checkbox的选中状况
		holder.er_cb.setChecked(getIsSelected().get(position));
		return convertView;
	}

	public static HashMap<Integer, Boolean> getIsSelected() {
		return isSelected;
	}

	public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
		CarpoolLeaseAdapter.isSelected = isSelected;
	}

	class ViewHolder {
		private ImageView icon, er_img_phone;// 照片
		private CheckBox er_cb;
		private TextView er_tv_name_shop;// 店名
		private TextView er_tv_distance;// 距离
		private TextView er_tv_map;// 地图
		private TextView er_tv_address;// 地址
		private TextView er_tv_name_phone;// 电话
	}

}
