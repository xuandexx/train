package adapter;

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
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.MapActivity;
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.SerciveCompanyActivity;
import cn.ieauto.autogroup.android.entity.RepairEntity;

public class RepairAdapter extends BaseAdapter{

	private List<RepairEntity> list_data;
	private Context mContext;
	private LayoutInflater mInflater;
	
	public RepairAdapter(Context context,List<RepairEntity> data){
		this.list_data = data;
		this.mContext = context;
		mInflater = LayoutInflater.from(mContext);
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
		
		RepairEntity entity = list_data.get(position);
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.item_carabouttabhost, null);
			holder.er_cb = (CheckBox) convertView.findViewById(R.id.no_er_cb);
			holder.er_cb.setVisibility(View.GONE);
			holder.er_img_icon = (ImageView) convertView.findViewById(R.id.er_img_icon);
			holder.er_img_level = (ImageView) convertView.findViewById(R.id.er_img_level);
			holder.er_img_level.setVisibility(View.VISIBLE);
			holder.er_tv_name_shop = (TextView) convertView.findViewById(R.id.er_tv_name_shop);
			holder.er_tv_distance = (TextView) convertView.findViewById(R.id.er_tv_distance);
			holder.er_tv_map = (TextView) convertView.findViewById(R.id.er_tv_map);
			holder.er_tv_address = (TextView) convertView.findViewById(R.id.er_tv_address);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.er_tv_name_shop.setText(entity.getShop_name());
		holder.er_tv_distance.setText(entity.getShop_distance());
		holder.er_tv_address.setText(entity.getShop_daaress());
		holder.er_img_icon.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(mContext,
						SerciveCompanyActivity.class);
				mContext.startActivity(intent);
			}
		});
		holder.er_tv_map.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(mContext, MapActivity.class);
				mContext.startActivity(intent);
			}
		});
		return convertView;
	}
    class ViewHolder{
    	private ImageView er_img_icon,er_img_level;
    	private TextView er_tv_name_shop,er_tv_distance,er_tv_map,er_tv_address;
    	private CheckBox er_cb;
    }
}
