package adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.entity.NearbyInfo;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class MyListAdapter extends BaseAdapter {

	private Context context;
	private List<NearbyInfo> list;
	private ImageLoader imageLoader;
	public RatingBar ratingbar;// 五角星评分

	public MyListAdapter(Context context, List<NearbyInfo> list,
			ImageLoader imageLoader) {
		this.context = context;
		this.list = list;
		this.imageLoader = imageLoader;
	}

	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	}

	@Override
	public NearbyInfo getItem(int position) {
		return list == null ? null : list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// return getItem(position).getId();
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		if (rowView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			rowView = inflater.inflate(R.layout.decorating_show, null);
			ViewHolder viewHolder = new ViewHolder();
			// 洗车店名
			viewHolder.name = (TextView) rowView
					.findViewById(R.id.decorating_name);
			// 距离
			viewHolder.title = (TextView) rowView
					.findViewById(R.id.decorating_distance);
			// 洗车店地址
			viewHolder.content = (TextView) rowView
					.findViewById(R.id.decorating_address_xml);
			// 洗车店logo
			viewHolder.image = (NetworkImageView) rowView
					.findViewById(R.id.image);
			// viewHolder.ratingbar = rowView.findViewById(R.id.ratingBar);
			rowView.setTag(viewHolder);
		}

		ViewHolder holder = (ViewHolder) rowView.getTag();

		NearbyInfo item = getItem(position);

		holder.name.setText(item.getName());
		holder.title.setText(item.getDistance());
		holder.content.setText(item.getAddress());
		String url = item.getPicUrl();
		holder.image.setImageUrl(url, imageLoader);
		return rowView;
	}

	static class ViewHolder {
		// public View ratingbar;
		public NetworkImageView image;
		public TextView name;
		public TextView title;
		public TextView content;
	}
}
