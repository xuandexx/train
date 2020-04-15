package activity.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.AgentInspectionActivity;
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.AutoBeautyActivity;
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.CarBusinessActivity;
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.CarModificationActivity;
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.CheyouquanActivity;
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.DrivingSchoolActivity;
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.DrivingServiceActivity;
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.EmergencyRescueActivity;
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.GasStationActivity;
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.IllegalManagementActivity;
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.InsuranceClaimsActivity;
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.MRActivity;
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.MybusinessActivity;
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.NearbyHelpActivity;
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.RentalCarActivity;
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.SeekHelpActivity;
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.TransferAgentActivity;
import cn.ieauto.autogroup.android.activity.fragments.cheshenghuo.ViolationQueriesActivity;
import cn.ieauto.autogroup.android.app.MyFragment;

/**
 * 车生活
 * 
 * @author Simon,Lampo
 */
public class CheshenghuoFragment extends MyFragment {
	private Intent intent;
	private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10,
			btn12, btn13, btn14, btn15;
	private final String message = "";
	private LinearLayout linear2, linear3, linear4;

	private static View view = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater
				.inflate(R.layout.fragment_cheshenghuo, container, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initViews(view);
		initEvents(view);
	}

	protected void initViews(View view) {
		// 设置头布局布局可见
		view.findViewById(R.id.view_top_img_back).setVisibility(8);// 后退键
		//view.findViewById(R.id.view_top_tv_linear).setVisibility(8);// 分隔图
		((TextView) view.findViewById(R.id.view_top_tv_title))
				.setText(R.string.cheshenghuo);
		view.findViewById(R.id.cyh_add_friend).setVisibility(8);// 最右边加号
		view.findViewById(R.id.view_top_img_right).setVisibility(8);// 最右边图标
		linear2 = (LinearLayout) view
				.findViewById(R.id.carlifeFargment_linear2);
		linear3 = (LinearLayout) view
				.findViewById(R.id.carlifeFargment_linear3);
		linear4 = (LinearLayout) view
				.findViewById(R.id.carlifeFargment_linear4);

		// 紧急救援
		btn1 = (Button) view.findViewById(R.id.carlifeFargment_button1);
		// 违章查询
		btn2 = (Button) view.findViewById(R.id.carlifeFargment_button2);
		// 加 油 站
		btn3 = (Button) view.findViewById(R.id.carlifeFargment_button3);
		// 违章办理
		btn4 = (Button) view.findViewById(R.id.carlifeFargment_button4);
		// 维修保养
		btn5 = (Button) view.findViewById(R.id.carlifeFargment_button5);
		// 代办验车
		btn6 = (Button) view.findViewById(R.id.carlifeFargment_button6);
		// 洗车美容
		btn7 = (Button) view.findViewById(R.id.carlifeFargment_button7);
		// 车险理赔
		btn8 = (Button) view.findViewById(R.id.carlifeFargment_button8);
		// 装潢改装
		btn9 = (Button) view.findViewById(R.id.carlifeFargment_button9);
		// 找 代 驾
		btn10 = (Button) view.findViewById(R.id.carlifeFargment_button10);
		// 租车拼车
		btn12 = (Button) view.findViewById(R.id.carlifeFargment_button12);
		// 买车卖车
		btn13 = (Button) view.findViewById(R.id.carlifeFargment_button13);
		// 代办过户
		btn14 = (Button) view.findViewById(R.id.carlifeFargment_button14);
		// 陪练驾校
		btn15 = (Button) view.findViewById(R.id.carlifeFargment_button15);

	}

	@Override
	protected void initEvents(View view) {

		view.findViewById(R.id.ll_cheshenghuo_cheyouquan).setOnClickListener(
				ocl);
		linear2.setOnClickListener(ocl);
		linear3.setOnClickListener(ocl);
		linear4.setOnClickListener(ocl);
		btn1.setOnClickListener(ocl);
		btn2.setOnClickListener(ocl);
		btn3.setOnClickListener(ocl);
		btn4.setOnClickListener(ocl);
		btn5.setOnClickListener(ocl);
		btn6.setOnClickListener(ocl);
		btn7.setOnClickListener(ocl);
		btn8.setOnClickListener(ocl);
		btn9.setOnClickListener(ocl);
		btn10.setOnClickListener(ocl);
		btn12.setOnClickListener(ocl);
		btn13.setOnClickListener(ocl);
		btn14.setOnClickListener(ocl);
		btn15.setOnClickListener(ocl);
	}

	OnClickListener ocl = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			// CarCircleActivity 车友圈
			case R.id.ll_cheshenghuo_cheyouquan:
				startActivity(CheyouquanActivity.class);
				break;
			/**
			 * NearbyhelpridersActivity 收到的附近车友求助
			 */
			case R.id.carlifeFargment_linear2:
				/*
				 * DataAcquisitionUtil.DataAcquisition(message, 3110,
				 * getActivity(), NearbyHelpActivity.class);
				 */
				startActivity(NearbyHelpActivity.class);
				break;
			/**
			 * SeekHelpActivity 我要向附近车友求助
			 */
			case R.id.carlifeFargment_linear3:
				startActivity(SeekHelpActivity.class);
				/*
				 * DataAcquisitionUtil.DataAcquisition(message, 3120,
				 * getActivity(), SeekHelpActivity.class);
				 */
				break;
			/**
			 * CaraboutTabhost_ApplicationActivity 我要申请提供车生活服务
			 */
			case R.id.carlifeFargment_linear4:
				intent = new Intent(getActivity(), MybusinessActivity.class);
				getActivity().startActivity(intent);
				// DataAcquisitionUtil.DataAcquisition(message, 3130,
				// getActivity(),
				// EmergencyRescueActivity.class);
				break;
			/**
			 * CaraboutTabhost_WanttorescueActivity 紧急救援
			 */
			case R.id.carlifeFargment_button1:
				// DataAcquisitionUtil.DataAcquisition(message, 3130,
				// getActivity(),
				// EmergencyRescueActivity.class);
				intent = new Intent(getActivity(),
						EmergencyRescueActivity.class);
				getActivity().startActivity(intent);
				break;
			/**
			 * Violation queries 违章查询
			 */
			case R.id.carlifeFargment_button2:
				intent = new Intent(getActivity(),
						ViolationQueriesActivity.class);
				getActivity().startActivity(intent);
				break;
			/**
			 * GasStationActivity 加 油 站
			 */
			case R.id.carlifeFargment_button3:
				intent = new Intent(getActivity(), GasStationActivity.class);
				// intent = new Intent(getActivity(),
				// ViolationQueriesActivity.class);
				getActivity().startActivity(intent);
				break;
			/**
			 * IllegalManagementActivity 违章办理
			 */
			case R.id.carlifeFargment_button4:
				Intent IMintent = new Intent(getActivity(),
						IllegalManagementActivity.class);
				getActivity().startActivity(IMintent);
				break;
			/**
			 * MRActivity:M/R Maintenance and repair 维修保养
			 */
			case R.id.carlifeFargment_button5:
				Intent MRintent = new Intent(getActivity(), MRActivity.class);
				getActivity().startActivity(MRintent);
				/*
				 * DataAcquisitionUtil.DataAcquisition(message, 3170,
				 * getActivity(), MRActivity.class);
				 */
				break;
			/**
			 * AgentInspectionActivity 代办验车
			 */
			case R.id.carlifeFargment_button6:
				intent = new Intent(getActivity(),
						AgentInspectionActivity.class);
				getActivity().startActivity(intent);
				break;
			/**
			 * AutoBeautyActivity 洗车美容
			 */
			case R.id.carlifeFargment_button7:
				intent = new Intent(getActivity(), AutoBeautyActivity.class);
				getActivity().startActivity(intent);
				break;
			/**
			 * AutoInsuranceClaimsActivity 车险理赔
			 */
			case R.id.carlifeFargment_button8:
				intent = new Intent(getActivity(),
						InsuranceClaimsActivity.class);
				getActivity().startActivity(intent);
				break;
			/**
			 * CarModificationActivity 装潢改装
			 */
			case R.id.carlifeFargment_button9:
				intent = new Intent(getActivity(),
						CarModificationActivity.class);
				getActivity().startActivity(intent);
				break;
			/**
			 * DrivingServiceActivity 找 代 驾
			 */
			case R.id.carlifeFargment_button10:
				Intent dsintent = new Intent(getActivity(),
						DrivingServiceActivity.class);
				getActivity().startActivity(dsintent);
				break;

			/**
			 * RentalCarActivity 租车拼车
			 */
			case R.id.carlifeFargment_button12:
				intent = new Intent(getActivity(), RentalCarActivity.class);
				getActivity().startActivity(intent);
				break;
			/**
			 * CarBusinessActivity 买车卖车 <br/>
			 */
			case R.id.carlifeFargment_button13:
				intent = new Intent(getActivity(), CarBusinessActivity.class);
				getActivity().startActivity(intent);
				break;
			/**
			 * TransferAgentActivity 代办过户
			 */
			case R.id.carlifeFargment_button14:
				/*
				 * DataAcquisitionUtil.DataAcquisition(message, 3260,
				 * getActivity(), TransferAgentActivity.class);
				 */
				Intent tfintent = new Intent(getActivity(),
						TransferAgentActivity.class);
				getActivity().startActivity(tfintent);
				break;
			/**
			 * DrivingSchoolActivity 陪练驾校
			 */
			case R.id.carlifeFargment_button15:
				intent = new Intent(getActivity(), DrivingSchoolActivity.class);
				getActivity().startActivity(intent);
				break;
			}
		}
	};

	@Override
	public void onDetach() {
		super.onDetach();
	}

}