package adapter;

import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.SerciveCompanyActivity;
import cn.ieauto.autogroup.android.entity.DrivingServiceEntity;

@SuppressLint("UseSparseArrays")
public class DrivingServiceAdapter extends BaseAdapter{

	private Context mContext;
	private List<DrivingServiceEntity> list_data;
	private LayoutInflater mInflater;
	// 用来控制CheckBox的选中状况  
    private static HashMap<Integer, Boolean> isSelected;  
    
	public DrivingServiceAdapter(Context context,List<DrivingServiceEntity> data){
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
		// TODO Auto-generated method stub
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
		DrivingServiceEntity entity = list_data.get(position);
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.item_rivingservice, null);
			holder.icon = (ImageView) convertView.findViewById(R.id.ll_img_icon);
			holder.ll_rl_sex = (RelativeLayout) convertView.findViewById(R.id.ll_rl_sex);
			holder.name = (TextView) convertView.findViewById(R.id.ll_tv_name);
			holder.age = (TextView) convertView.findViewById(R.id.ll_tv_age);
			holder.distance = (TextView) convertView.findViewById(R.id.ll_tv_distance);
			holder.drivingexperience = (TextView) convertView.findViewById(R.id.ll_tv_drivingexperience);
			holder.lh_cb = (CheckBox) convertView.findViewById(R.id.lh_cb);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.name.setText(entity.getName());
		holder.age.setText(entity.getAge());
		holder.distance.setText(entity.getDistance());
		holder.drivingexperience.setText("驾龄:"+entity.getDrivingexperience());
		// 根据isSelected来设置checkbox的选中状况  
        holder.lh_cb.setChecked(getIsSelected().get(position));  
        holder.icon.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
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
		DrivingServiceAdapter.isSelected = isSelected;
	}

    class ViewHolder{
    	private ImageView icon;
    	private RelativeLayout ll_rl_sex;
    	private TextView name,age,distance,drivingexperience;
    	private CheckBox lh_cb;
    	
    }
}
