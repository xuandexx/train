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
import cn.ieauto.autogroup.android.entity.DrivingSchoolEntity;

public class DrivingSchoolAdapter extends BaseAdapter{

	private Context mContext;
	private List<DrivingSchoolEntity> list_data;
	private LayoutInflater mInflater;
	// 用来控制CheckBox的选中状况  
    private static HashMap<Integer, Boolean> isSelected;  
	
	public DrivingSchoolAdapter(Context context,List<DrivingSchoolEntity> data){
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
		// TODO Auto-generated method stub
		return list_data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		
		DrivingSchoolEntity entity = list_data.get(position);
		ViewHolder Holder;
		if (convertView == null) {
			Holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.item_drivingschool_list,null);
			Holder.ds_img_icon = (ImageView) convertView.findViewById(R.id.ds_img_icon);
			Holder.ds_img_level = (ImageView) convertView.findViewById(R.id.ds_img_level);
			Holder.ds_img_level.setVisibility(View.VISIBLE);
			Holder.ds_tv_name = (TextView) convertView.findViewById(R.id.ds_tv_name);
			Holder.ds_tv_address = (TextView) convertView.findViewById(R.id.ds_tv_address);
			Holder.ds_tv_regional = (TextView) convertView.findViewById(R.id.ds_tv_regional);
			Holder.cb = (CheckBox) convertView.findViewById(R.id.dsa_cb);
			convertView.setTag(Holder);
		}else{
			Holder = (ViewHolder) convertView.getTag();
		}
		
		Holder.ds_tv_name.setText(entity.getName());
		Holder.ds_tv_address.setText(entity.getAddress());
		Holder.ds_tv_regional.setText(entity.getRegional());
		// 根据isSelected来设置checkbox的选中状况  
		Holder.cb.setChecked(getIsSelected().get(position)); 
		
		Holder.ds_img_icon.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(mContext,SerciveCompanyActivity.class);
				mContext.startActivity(intent);
			}
		});
		
		return convertView;
	}
	public static HashMap<Integer, Boolean> getIsSelected() {
		return isSelected;
	}

	public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
		DrivingSchoolAdapter.isSelected = isSelected;
	}
    class ViewHolder{
    	private ImageView ds_img_icon,ds_img_level;
    	private TextView ds_tv_name,ds_tv_address,ds_tv_regional;
    	private CheckBox cb;
    }
}
