package activity.fragments.wo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.MyApplication;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.app.MyActivity;

/**
 * 我的相册
 * 
 * @author Administrator
 * 
 */
/*
 * listview的item是mylistviewitem.xml listview加载更多
 */
public class MyPhotoActivity extends MyActivity implements OnClickListener {

	private ImageView img_Back, img_face, img_picture;
	private TextView tv_nickname;
	private ListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mycallphone);
		initViews();
		initEvents();
	}

	@Override
	protected void initViews() {
		img_Back = (ImageView) this.findViewById(R.id.view_top_img_back);
		((TextView) findViewById(R.id.view_top_tv_title))
				.setText(R.string.my_fargment_album);
		tv_nickname = (TextView) findViewById(R.id.myphoto_nickname);
		list = (ListView) findViewById(R.id.mycallphone_list);
		img_face = (ImageView) findViewById(R.id.mycallphone_img_face);
		img_picture = (ImageView) findViewById(R.id.mycallphone_img_picture);
	}

	@Override
	protected void initEvents() {
		img_Back.setBackgroundResource(R.drawable.button_return);
		tv_nickname.setText(MyApplication.getInstance().getNickName());
		img_face.setBackgroundResource(R.drawable.wx_2);
		img_picture.setOnClickListener(this);
		img_Back.setOnClickListener(this);
		List<Map<String, Object>> data = getdata();

		String from[] = new String[] { "month", "day", "text", "picture" };
		int to[] = new int[] { R.id.item_myphoto_tv_month,
				R.id.item_myphoto_tv_day, R.id.item_myphoto_tv_text,
				R.id.item_myphoto_gridview };

		MyPhotoAdapter adapter = new MyPhotoAdapter(MyPhotoActivity.this, data,
				from, to);
		list.setAdapter(adapter);
	}

	private List<Map<String, Object>> getdata() {
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		Calendar calendar = Calendar.getInstance();
		map.put("month", calendar.get(Calendar.MONTH));
		map.put("day", calendar.get(Calendar.DAY_OF_MONTH));
		map.put("text", "很快就都是高科技和韩国空军的时光");
		@SuppressWarnings("unused")
		int a[] = new int[] { R.drawable.e1_009_1, R.drawable.e19_5_064 };
		map.put("picture", R.drawable.e1_009_1);
		data.add(map);
		return data;

	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.view_top_img_back:
			defaultFinish();
			break;

		default:
			break;
		}
	}

	class MyPhotoAdapter extends BaseAdapter {
		Context context;
		List<Map<String, Object>> mylist = new ArrayList<Map<String, Object>>();
		String from[];
		int to[];

		public MyPhotoAdapter(Context context, List<Map<String, Object>> data,
				String from[], int to[]) {
			this.context = context;
			this.mylist = data;
			this.from = from;
			this.to = to;
		}

		@Override
		public int getCount() {
			return mylist.size();
		}

		@Override
		public Object getItem(int position) {
			return mylist.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View view, ViewGroup arg2) {
			view = LayoutInflater.from(context).inflate(R.layout.item_myphoto,
					null);
			ViewHolder viewHolder = new ViewHolder();

			viewHolder.tv_month = (TextView) view.findViewById(to[0]);
			viewHolder.tv_day = (TextView) view.findViewById(to[1]);
			viewHolder.tv_text = (TextView) view.findViewById(to[2]);
			viewHolder.gridView = (GridView) view.findViewById(to[3]);
			viewHolder.imgbut_camear = (ImageButton) view
					.findViewById(R.id.item_myphoto_imgbut_camera);

			viewHolder.tv_month.setText(mylist.get(position).get(from[0])
					.toString()
					+ "月");
			viewHolder.tv_day.setText(mylist.get(position).get(from[1])
					.toString());
			viewHolder.tv_text.setText(mylist.get(position).get(from[2])
					.toString());
			viewHolder.gridView.setBackgroundResource((Integer) mylist.get(
					position).get(from[3]));
			/*
			 * final List<Map<String, Object>> Notopened_list = new
			 * ArrayList<Map<String, Object>>(); int[] a = (int[])
			 * mylist.get(position).get(from[3]); Object
			 * b=mylist.get(position).get(from[3]); System.out.println("b="+b);
			 * System.out.println("a="+a); for (int i = 0; i < from[3].length();
			 * i++) { Map<String, Object> Notopened_map = new HashMap<String,
			 * Object>(); Notopened_map.put("image",a[i]);
			 * System.out.println("a="+a[i]); Notopened_list.add(Notopened_map);
			 * } SimpleAdapter simpleAdapter = new SimpleAdapter(context,
			 * Notopened_list, R.layout.cell, new String[] { "image" }, new
			 * int[] { R.id.cell_image1 });
			 * viewHolder.gridView.setAdapter(simpleAdapter);
			 */
			return view;
		}
	}

	static class ViewHolder {
		public TextView tv_month;
		public TextView tv_day;
		public TextView tv_text;
		public GridView gridView;
		public ImageButton imgbut_camear;

	}
}
