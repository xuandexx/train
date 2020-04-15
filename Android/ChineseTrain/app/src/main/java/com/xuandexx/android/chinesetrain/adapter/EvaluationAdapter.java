package adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.entity.EvaluationEntity;

public class EvaluationAdapter extends BaseAdapter{

	private Context mContext;
	private List<EvaluationEntity> list_data;
	private LayoutInflater mInflater;
	
	public EvaluationAdapter(Context context,List<EvaluationEntity> data){
		this.mContext = context;
		this.list_data = data;
		mInflater = LayoutInflater.from(mContext);
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
		EvaluationEntity entity = list_data.get(position);
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.item_ridersevaliation_listview, null);
			holder.et_img_icon = (ImageView) convertView.findViewById(R.id.et_img_icon);
			holder.et_tv_l = (TextView) convertView.findViewById(R.id.et_tv_l);
			holder.et_tv_content = (TextView) convertView.findViewById(R.id.et_tv_content);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.et_tv_l.setText(entity.getLevel());
		holder.et_tv_content.setText(entity.getContent());
		return convertView;
	}
    class ViewHolder{
    	private ImageView et_img_icon;
    	private TextView et_tv_l,et_tv_content;
    }
}
