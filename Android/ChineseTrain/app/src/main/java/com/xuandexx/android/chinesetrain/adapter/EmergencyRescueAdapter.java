package adapter;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.EmergencyRescueActivity;
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.MapActivity;
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.MySeekHelpActivity;
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.SerciveCarActivity;
import cn.ieauto.autogroup.android.dialog.CustomDialog;
import cn.ieauto.autogroup.android.entity.EmergencyRescue;

//我要救援
public class EmergencyRescueAdapter extends BaseAdapter {

	private Context mContext;
	private List<EmergencyRescue> list_data;
	private LayoutInflater mInflater;
	private ErViewHolder holder;
	private EmergencyRescue rescue;
	private CustomDialog.Builder ibuilder;
	// 用来控制CheckBox的选中状况  
	private static HashMap<Integer, Boolean> isSelected;  

	public EmergencyRescueAdapter(Context context,
			List<EmergencyRescue> list_data) {
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
	public View getView(int position, View convertView, ViewGroup arg2) {
		rescue = list_data.get(position);
		if (convertView == null) {
			holder = new ErViewHolder();
			convertView = mInflater
					.inflate(R.layout.item_carabouttabhost, null);
			 holder.icon = (ImageView) convertView.findViewById(R.id.er_img_icon);
			 holder.no_er_cb = (CheckBox) convertView.findViewById(R.id.no_er_cb);
			 //车品牌
			 holder.er_tv_name_shop = (TextView) convertView.findViewById(R.id.er_tv_name_shop);
			//档位牌照
			 holder.er_tv_distance = (TextView) convertView.findViewById(R.id.er_tv_distance);
			 //年限
			 holder.er_tv_map = (TextView) convertView.findViewById(R.id.er_tv_map);
			 //租借或驾驶
			 holder.er_tv_address = (TextView) convertView.findViewById(R.id.er_tv_address);
			 holder.er_tv_name_phone = (TextView) convertView.findViewById(R.id.er_tv_phone);
			 holder.er_tv_name_phone.setVisibility(View.VISIBLE);
			 holder.er_img_phone = (ImageView) convertView.findViewById(R.id.er_img_phone);
			 holder.er_tv_name_phone.setVisibility(View.VISIBLE);
			convertView.setTag(holder);
		} else {
			holder = (ErViewHolder) convertView.getTag();
		}
		holder.er_tv_name_shop.setText(rescue.getCompanyname());
		holder.er_tv_distance.setText(rescue.getDistance());
		holder.er_tv_address.setText(rescue.getAddress());
		// 根据isSelected来设置checkbox的选中状况  
        holder.no_er_cb.setChecked(getIsSelected().get(position));  
        
        
		final String shop_phone = rescue.getPhone();
		holder.er_tv_name_phone.setText(shop_phone);
		holder.er_tv_name_phone.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ibuilder = new CustomDialog.Builder(mContext);
				ibuilder.setTitle("提示");
				ibuilder.setMessage("即将拨打电话:"+shop_phone);
				ibuilder.setPositiveButton("拨打", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Uri uri = Uri.parse("tel:" + shop_phone);
						Intent intent = new Intent(Intent.ACTION_CALL, uri);
						mContext.startActivity(intent);
						dialog.dismiss();
					}
				});
				ibuilder.setNegativeButton("取消", null);
				ibuilder.create().show();
			}
		});
		holder.er_tv_map.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(mContext, MapActivity.class);
				mContext.startActivity(intent);
			}
		});
		holder.icon.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(mContext,
						SerciveCarActivity.class);
				mContext.startActivity(intent);
			}
		});
//		holder.no_er_cb.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//			}
//		});
		return convertView;
	}


	class Distance implements OnClickListener {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(mContext, MapActivity.class);
			mContext.startActivity(intent);
		}
	}
	public static HashMap<Integer, Boolean> getIsSelected() {
		return isSelected;
	}
	public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
		EmergencyRescueAdapter.isSelected = isSelected;
	}

	public final class ErViewHolder {
		public ImageView icon,er_img_phone;// 照片
		public CheckBox no_er_cb;
		public TextView er_tv_name_shop;// 店名
		public TextView er_tv_distance;// 距离
		public TextView er_tv_map;// 地图
		public TextView er_tv_address;// 地址
		public TextView er_tv_name_phone;// 电话
	}
}
