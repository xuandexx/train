package adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.ieauto.autogroup.android.MyApplication;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.entity.Ruser;

/**
 * 城市的简称适配器
 * 
 * @author Simon1
 * 
 */

public class CarCityAdapter extends BaseAdapter {
	private Activity context;
	private TextView city;
	private PopupWindow pop1;
	private PopupWindow pop2;
	String[] carPrevince = Ruser.string.ProvinceReferred;

	public CarCityAdapter(Activity context, TextView city, PopupWindow pop2) {
		this.context = context;
		this.city = city;
		// this.pop1=pop1;
		this.pop2 = pop2;
	}

	@Override
	public int getCount() {

		return carPrevince.length;
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(final int position, View v, ViewGroup arg2) {
		@SuppressWarnings("static-access")
		View view = v.inflate(context, R.layout.item_car_pho, null);
		TextView tv_car_pho = (TextView) view.findViewById(R.id.tv_car_pho);
		RelativeLayout rl_car_pho = (RelativeLayout) view
				.findViewById(R.id.rl_car_pho);
		tv_car_pho.setText(carPrevince[position]);
		rl_car_pho.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				if (pop1 != null) {
					pop1.dismiss();
				}

				if (pop2 != null) {
					pop2.dismiss();
				}
				city.setText(carPrevince[position]);
				LayoutInflater layoutInflater = LayoutInflater.from(context);
				final View view = layoutInflater.inflate(R.layout.mycar, null);
				String mProvince = ((TextView) view
						.findViewById(R.id.mycar_but_province)).getText()
						.toString().trim();
				String mCityNo = ((TextView) view
						.findViewById(R.id.mycar_but_city)).getText()
						.toString().trim();
				String mCarLicenseNo = ((TextView) view
						.findViewById(R.id.mycar_LicenseNo)).getText()
						.toString().trim();
				String mLicenseNo = mProvince + mCityNo + mCarLicenseNo;

				// TODO :数据提交,保存上牌时间,并且提交
				MyApplication.getInstance().saveLicenseNo(mLicenseNo,
						mProvince, mCityNo, mCarLicenseNo);
				System.out.println("city.getText().toString()"
						+ city.getText().toString());
			}
		});
		return view;
	}
}
