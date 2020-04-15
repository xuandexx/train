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
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.SercivePersonActivity;
import cn.ieauto.autogroup.android.entity.IllegalManagementEntity;

public class IllegalManagementAdapter extends BaseAdapter{

	private Context mContext;
	private List<IllegalManagementEntity> list_data;
	private LayoutInflater mInflater;
	// 用来控制CheckBox的选中状况  
    private static HashMap<Integer, Boolean> isSelected;  
	
	public IllegalManagementAdapter(Context context,List<IllegalManagementEntity> data){
		this.mContext = context;
		this.list_data = data;
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
		
		IllegalManagementEntity entity = list_data.get(position);
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.item_lllegalhandl_listview, null);
			holder.lh_img_icon = (ImageView) convertView.findViewById(R.id.lllegalhandl_imageView1);
			holder.lh_img_level = (ImageView) convertView.findViewById(R.id.lh_img_level);
			holder.name = (TextView) convertView.findViewById(R.id.lllegalhandl_name);
			holder.lh_tv_business = (TextView) convertView.findViewById(R.id.lh_tv_business);
			holder.lh_tv_monery = (TextView) convertView.findViewById(R.id.lh_tv_monery);
			holder.lh_cb = (CheckBox) convertView.findViewById(R.id.lh_cb);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.name.setText(entity.getName());
		holder.lh_tv_business.setText(entity.getBusiness());
		holder.lh_tv_monery.setText(entity.getMonery());
//		holder.lh_cb.setChecked(getIsSelected().get(position));  
		holder.lh_img_icon.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(mContext, SercivePersonActivity.class);
				mContext.startActivity(intent);
			}
		});
		return convertView;
	}
	public static HashMap<Integer, Boolean> getIsSelected() {
		return isSelected;
	}
	public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
		IllegalManagementAdapter.isSelected = isSelected;
	}
    class ViewHolder{
    	private ImageView lh_img_icon,lh_img_level;
    	private TextView name,lh_tv_business,lh_tv_monery;
    	private CheckBox lh_cb;
    }
}
