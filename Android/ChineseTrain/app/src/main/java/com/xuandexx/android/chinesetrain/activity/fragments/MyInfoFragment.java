package activity.fragments;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.ieauto.autogroup.android.MyApplication;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.activity.fragments.business.MyBussinessMainActivity;
import cn.ieauto.autogroup.android.activity.fragments.wo.MyCollectionActivity;
import cn.ieauto.autogroup.android.activity.fragments.wo.MyPhotoActivity;
import cn.ieauto.autogroup.android.activity.fragments.wo.MySettingActivity;
import cn.ieauto.autogroup.android.activity.fragments.wo.MycarsActivity;
import cn.ieauto.autogroup.android.activity.fragments.wo.PersonalInfoActivity;
import cn.ieauto.autogroup.android.app.MyFragment;
import cn.ieauto.autogroup.android.httpTask.PersonalRequest;

/**
 * 主Fragment:我(一级)
 * 
 * @author Lampo
 */
public class MyInfoFragment extends MyFragment implements OnClickListener {
	private TextView nicknameTextView;
	private View view;
	String result = null;
	Intent intent;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.myinformetion, container, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initViews(view);
		initEvents(view);
	}

	protected void initViews(View view) {
		view.findViewById(R.id.view_top_img_back).setVisibility(8);// 后退键
		// view.findViewById(R.id.view_top_tv_linear).setVisibility(8);// 分隔图
		((TextView) view.findViewById(R.id.view_top_tv_title))
				.setText(R.string.wo);
		view.findViewById(R.id.cyh_add_friend).setVisibility(8);// 最右边加号
		view.findViewById(R.id.view_top_img_right).setVisibility(8);// 最右边图标
		nicknameTextView = (TextView) view.findViewById(R.id.mf_ll_nickname);
	}

	@Override
	protected void initEvents(View view) {
		String nickname = MyApplication.getInstance().getNickName().toString();
		if (!TextUtils.isEmpty(nickname)) {
			nicknameTextView.setText(nickname);
		} else {
			nicknameTextView.setText("请设置你的昵称!");
		}
		view.findViewById(R.id.mf_ll_info).setOnClickListener(this);
		view.findViewById(R.id.mf_ll_photo).setOnClickListener(this);
		view.findViewById(R.id.mf_ll_collection).setOnClickListener(this);
		view.findViewById(R.id.mf_ll_carinfo).setOnClickListener(this);
		view.findViewById(R.id.mf_ll_business).setOnClickListener(this);
		view.findViewById(R.id.mi_ll_setting).setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		/* 个人信息:头像,设置昵称 */
		case R.id.mf_ll_info:
			startActivity(PersonalInfoActivity.class);
			break;
		/* 我的相册 */
		case R.id.mf_ll_photo:
			startActivity(MyPhotoActivity.class);
			break;
		/* 我的收藏 */
		case R.id.mf_ll_collection:
			startActivity(MyCollectionActivity.class);
			break;
		/* 我的座驾 */
		case R.id.mf_ll_carinfo:
			doMyCars();
			intent = new Intent(getActivity(), MycarsActivity.class);
			intent.putExtra("result", result);
			startActivity(intent);
			break;
		/* 我的生意 */
		case R.id.mf_ll_business:
			startActivity(MyBussinessMainActivity.class);
			break;
		/* 设置 */
		case R.id.mi_ll_setting:
			startActivity(MySettingActivity.class);
			break;
		}
	}

	private void doMyCars() {
		System.out.println("对不起，当前网络不可用!");
		new Thread(new Runnable() {
			@Override
			public void run() {

				try {
					result = PersonalRequest.requestCarInfo(MyApplication
							.getInstance().getUserPhone());
					Log.e("lilongbing", result);
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
}
