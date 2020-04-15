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

public class CarpoolCompanyAdapter extends BaseAdapter {

	private Context mContext;
	private List<CarpoolLeaseEntity> list_data;
	private LayoutInflater mInflater;
	// 用来控制CheckBox的选中状况
	private static HashMap<Integer, Boolean> isSelected;

	public CarpoolCompanyAdapter(Context context, List<CarpoolLeaseEntity> data) {
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
		// TODO Auto-generated method stub
		ViewHolder holder;
		CarpoolLeaseEntity entity = list_data.get(position);
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.item_leasingc_listview,
					null);
			holder.icon = (ImageView) convertView
					.findViewById(R.id.item_leasingc_imageView1);
			holder.img_level = (ImageView) convertView
					.findViewById(R.id.item_img_level);
			holder.er_cb = (CheckBox) convertView.findViewById(R.id.item_cb);
			holder.er_tv_name_shop = (TextView) convertView
					.findViewById(R.id.item_leasingc_name);
			holder.er_tv_zp = (TextView) convertView
					.findViewById(R.id.item_leasingc_type);
			holder.er_tv_pp = (TextView) convertView
					.findViewById(R.id.item_leasingc_rating);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.er_tv_name_shop.setText(entity.getName());
		holder.er_tv_zp.setText(entity.getZj());
		holder.er_tv_pp.setText(entity.getPp());
		// 根据isSelected来设置checkbox的选中状况
		holder.er_cb.setChecked(getIsSelected().get(position));
		return convertView;
	}

	public static HashMap<Integer, Boolean> getIsSelected() {
		return isSelected;
	}

	public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
		CarpoolCompanyAdapter.isSelected = isSelected;
	}

	class ViewHolder {
		private ImageView icon, er_img_phone, img_level;// 照片
		private CheckBox er_cb;
		private TextView er_tv_name_shop;// 店名
		private TextView er_tv_zp;// 单车租貧
		private TextView er_tv_pp;// 品牌
	}
}
