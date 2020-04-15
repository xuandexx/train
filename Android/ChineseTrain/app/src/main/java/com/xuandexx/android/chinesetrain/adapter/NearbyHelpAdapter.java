package adapter;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.Nearbyhelpriders;
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.SerciveCompanyActivity;
import cn.ieauto.autogroup.android.dialog.CustomDialog;
import cn.ieauto.autogroup.android.entity.NearbyHelpEntity;

/**
 * 
 * @author Lampo
 * 
 */
public class NearbyHelpAdapter extends BaseAdapter {

	private Context mContext;
	private List<NearbyHelpEntity> data;
	private LayoutInflater mInflater;
	private CustomDialog.Builder ibuilder;
	private NearbyHelpAdapter mAdapter;
	private ViewHolder Hodler;
	NearbyHelpEntity entity;

	public NearbyHelpAdapter(Context context, List<NearbyHelpEntity> data) {
		this.data = data;
		this.mContext = context;
		mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		NearbyHelpEntity entity = data.get(position);

		if (convertView == null) {
			Hodler = new ViewHolder();
			convertView = mInflater.inflate(R.layout.item_nearbyhelp, null);
			Hodler.img_icon = (ImageView) convertView
					.findViewById(R.id.item_nearbyhelp_img_iocn);
			Hodler.tv_name = (TextView) convertView
					.findViewById(R.id.item_nearbyhelp_tv_name);
			Hodler.tv_age = (TextView) convertView
					.findViewById(R.id.item_nearbyhelp_tv_age);
			Hodler.tv_phone = (TextView) convertView
					.findViewById(R.id.item_nearbyhelp_tv_phone);
			Hodler.tv_distance = (TextView) convertView
					.findViewById(R.id.item_nearbyhelp_tv_distance);
			Hodler.tv_theme = (TextView) convertView
					.findViewById(R.id.item_nearbyhelp_tv_theme);
			Hodler.tv_othertheme = (TextView) convertView
					.findViewById(R.id.item_nearbyhelp_tv_othertheme);

			Hodler.layout_sex = (RelativeLayout) convertView
					.findViewById(R.id.item_nearbyhelp_layout_sex);
			Hodler.but_submit = (Button) convertView
					.findViewById(R.id.item_nearbyhelp_but_ok);
			Hodler.tv_callphone = (TextView) convertView
					.findViewById(R.id.item_nearbyhelp_tv_callphone);
			convertView.setTag(Hodler);
		} else {
			Hodler = (ViewHolder) convertView.getTag();
		}

		Hodler.tv_name.setText(entity.getName());
		Hodler.tv_age.setText(entity.getAge());
		Hodler.tv_phone.setText(entity.getPhone());
		Hodler.tv_theme.setText(entity.getTheme());
		Hodler.tv_distance.setText(entity.getDistance());
		Hodler.tv_othertheme.setText(entity.getOthertheme());
		if ("男".equals(entity.getSex())) {
			Hodler.layout_sex.setBackgroundResource(R.drawable.man_1);
		} else if ("女".equals(entity.getSex())) {
			Hodler.layout_sex.setBackgroundResource(R.drawable.woman_1);
		}
		Hodler.but_submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mContext.startActivity(new Intent(mContext,
						Nearbyhelpriders.class));
			}
		});

		final String phone = entity.getPhone();
		Hodler.tv_callphone.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ibuilder = new CustomDialog.Builder(mContext);
				ibuilder.setTitle("提示");
				ibuilder.setMessage("即将拨打电话:" + phone);
				ibuilder.setPositiveButton("拨打",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								Uri uri = Uri.parse("tel:" + phone);
								Intent intent = new Intent(Intent.ACTION_CALL,
										uri);
								mContext.startActivity(intent);
								dialog.dismiss();
							}
						});
				ibuilder.setNegativeButton("取消", null);
				ibuilder.create().show();
			}
		});
		Hodler.img_icon.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(mContext,
						SerciveCompanyActivity.class);
				mContext.startActivity(intent);
			}
		});
		return convertView;
	}



	class ViewHolder {
		private ImageView img_icon;
		private RelativeLayout layout_sex;
		private TextView tv_name, tv_age, tv_phone, tv_distance;
		// 打电话
		private TextView tv_callphone;
		private TextView tv_theme, tv_othertheme;
		// 提交
		private Button but_submit;
	}
}
