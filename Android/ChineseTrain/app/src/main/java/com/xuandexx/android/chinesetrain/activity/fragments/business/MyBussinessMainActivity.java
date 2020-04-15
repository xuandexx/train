package activity.fragments.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.ieauto.autogroup.android.R;

public class MyBussinessMainActivity extends Activity implements OnClickListener
{
	private Handler mHandler = new Handler();
	private ProgressDialog progDialog;
	private View mContentView;
	private ListView mListView;
	private MyListViewAdapter myListViewAdapter;
	private TextView mPersonBussinessTv,mCompanyBussinessTv;
	
	private String[] mBussinessName = new String[]{"代驾","私车租借","拼车","陪练","私车转让",
			"车辆求购","代办违章","代办过户","代办验车","车险销售","理赔指导","洗车","美容","道路救援",
			"维修保养","装潢","改装","代办理赔","租赁公司","车辆收购"			
	};
	
	private String[] mBussinessStatus = new String[]{"申请开通","审核中","已开通","申请开通","审核未通过",
			"申请开通","申请开通","申请开通","申请开通","申请开通","申请开通","申请开通","申请开通","申请开通",
			"申请开通","申请开通","申请开通","申请开通","申请开通","申请开通"		
	};
	
	private List<HashMap<String,Object>> mListViewData = new ArrayList<HashMap<String,Object>>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		showProgressDialog(this);
		mContentView = LinearLayout.inflate(this,R.layout.modules__home__my_bussiness_activity,null);
		setContentView(mContentView);		
		init();
		mHandler.postDelayed(mUpdateDataRunnable, 100);		
	}

	private void init() {
		mListView = (ListView)mContentView.findViewById(R.id.listView_mybussiness);
		ViewGroup.LayoutParams lp = mListView.getLayoutParams();
		lp.height =  DisplayUtil.getScreenHeight(this)-DisplayUtil.dip2px(this, 120);
		mListView.setLayoutParams(lp);
		
		myListViewAdapter = new MyListViewAdapter(this);
		mListView.setAdapter(myListViewAdapter);
		mContentView.findViewById(R.id.view_top_img_back).setOnClickListener(this);
		
		mPersonBussinessTv = (TextView) this.mContentView.findViewById(R.id.personnal_bussiness_tv);
		mCompanyBussinessTv = (TextView) this.mContentView.findViewById(R.id.company_bussiness_tv);
		
		mPersonBussinessTv.setOnClickListener(this);
		mCompanyBussinessTv.setOnClickListener(this);
	}

	private Runnable mUpdateDataRunnable = new Runnable() {
		    @Override
		    public void run() {
		    	mHandler.removeCallbacks(mUpdateDataRunnable);
		    	progDialog.dismiss();		    	
		     }
     };
	/**
     * 显示进度框
     */
    private void showProgressDialog(Context context) {
        if (progDialog == null)
            progDialog = new ProgressDialog(context);
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDialog.setIndeterminate(false);
        progDialog.setCancelable(false);
        progDialog.setMessage("数据加载中");
        progDialog.show();
    }
    
    public void onDetails(int position,boolean isNew,boolean isCompany)
	{
		Intent intent = new Intent();
        intent.putExtra("isNew", isNew);
        intent.putExtra("isCompany", isCompany);
        intent.putExtra("bussinessName", mBussinessName[position]);
        intent.setClass(mContentView.getContext(), MyBussinessDetails.class);
        this.startActivityForResult(intent, 0);
	}
    
	private class MyListViewAdapter extends BaseAdapter
	{

		private MyBussinessMainActivity mContext;
		private int start=0,end=12;
		private boolean mSelectedCompany = false;
		
		public MyListViewAdapter(MyBussinessMainActivity context)
		{
			mContext = context;
			prepareData();
		}

		private void prepareData()
		{
			HashMap<String,Object> item =null;			
			if (mSelectedCompany)
			{
				start = 12;
				end = mBussinessName.length;
			}
			else
			{
				start = 0;
				end = 12;
			}
			mListViewData.clear();
			for (int i=start;i<end;i++)
			{
				item = new HashMap<String,Object>();
				item.put("name", mBussinessName[i]);
				item.put("status", mBussinessStatus[i]);
				mListViewData.add(item);
			}
		}

		public void resetData(boolean isCompany)
		{
			mSelectedCompany = isCompany;
			prepareData();
			this.notifyDataSetChanged();
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mListViewData.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHoider holder;
			if (convertView == null) {
				 convertView =LinearLayout.inflate(mContext,R.layout.modules__home__my_bussiness_listitem,null);
				holder = new ViewHoider();
				convertView.setTag(holder);
				holder.bussiness_name = (TextView) convertView.findViewById(R.id.mybussiness_details_name);
				holder.bussiness_status= (TextView) convertView.findViewById(R.id.mybussiness_details_status);
				holder.bussiness_op_modify= (TextView) convertView.findViewById(R.id.mybussiness_details_op_modify);
				holder.bussiness_op_pause= (TextView) convertView.findViewById(R.id.mybussiness_details_op_pause);
				holder.bussiness_op_terminate= (TextView) convertView.findViewById(R.id.mybussiness_details_op_terminate);
				
			}
			else
			{
				holder = (ViewHoider) convertView.getTag();
			}			
			HashMap<String,Object> item = mListViewData.get(position);
			holder.bussiness_name.setText(item.get("name").toString());
			holder.bussiness_status.setText(item.get("status").toString());

			String status = holder.bussiness_status.getText().toString();
			setControllerStatus(status, holder, position);

			return convertView;
		}
		
		private void setControllerStatus(String status,final ViewHoider holder,final int position)
		{
			
			holder.bussiness_status.setTextColor(Color.parseColor("#333333"));	
			holder.bussiness_status.setId(position+start);
			holder.bussiness_status.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					int id = arg0.getId();
					onDetails(id,mSelectedCompany);
				}

			});
			holder.bussiness_status.setClickable(false);
			holder.bussiness_op_modify.setId(position+start);
			holder.bussiness_op_modify.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View arg0) {
					int id = arg0.getId();
					// TODO Auto-generated method stub
					onModify(holder,id);
				}
				
			});
			holder.bussiness_op_pause.setId(position+start);
			holder.bussiness_op_pause.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Toast.makeText(mContext, "修改", Toast.LENGTH_LONG)
							.show();
					onModify(holder, position);
				}

			});
			holder.bussiness_op_modify.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							Toast.makeText(mContext, "修改", Toast.LENGTH_LONG)
									.show();
							onModify(holder, position);
						}

					});
			holder.bussiness_op_pause.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					int id = arg0.getId();
					if (id<0 || id > mBussinessName.length)
						return;	
					String str =holder.bussiness_op_pause.getText().toString();
					NotifyDialog("业务"+str,"您确认"+str+"您的"+mBussinessName[id]+"业务吗？",2,holder,id);
				}

			});
			
			holder.bussiness_op_terminate.setId(position+start);
			holder.bussiness_op_terminate.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					int id = arg0.getId();
					if (id<0 || id > mBussinessName.length)
						return;
					NotifyDialog("业务终止","您确认终止您的"+mBussinessName[id]+"业务吗？",3,holder,id);									
				}
				
			});
			
			if (status.equals("已开通"))
			{
				holder.bussiness_op_terminate.setOnClickListener(new OnClickListener(){
						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							Toast.makeText(mContext, "终止", Toast.LENGTH_LONG)
									.show();
							onTerminate(holder, position);
						}

				});
				
		    	holder.bussiness_op_modify.setClickable(true);
				holder.bussiness_op_pause.setClickable(true);
				holder.bussiness_op_terminate.setClickable(true);

				holder.bussiness_op_modify.setTextColor(Color
						.parseColor("#333333"));
				holder.bussiness_op_pause.setTextColor(Color
						.parseColor("#333333"));
				holder.bussiness_op_terminate.setTextColor(Color
						.parseColor("#333333"));
			} else {
				holder.bussiness_op_modify.setClickable(false);
				holder.bussiness_op_pause.setClickable(false);
				holder.bussiness_op_terminate.setClickable(false);

				holder.bussiness_op_modify.setTextColor(Color
						.parseColor("#999999"));
				holder.bussiness_op_pause.setTextColor(Color
						.parseColor("#999999"));
				holder.bussiness_op_terminate.setTextColor(Color
						.parseColor("#999999"));
			}

			if (status.equals("审核未通过"))
				holder.bussiness_status.setTextColor(Color.RED);
			else if (status.equals("审核中")) {
				holder.bussiness_status.setTextColor(Color
						.parseColor("#999999"));
			} else if (status.equals("申请开通"))
				holder.bussiness_status.setClickable(true);
			else
				; // do nothing

		}
		
		private void onDetails(int position,boolean isNew)
		{
			mContext.onDetails(position,isNew,mSelectedCompany);
		}
		
		private void onPause(ViewHoider holder,int position)
		{
			String str = holder.bussiness_op_pause.getText().toString();
			if (str.equals("恢复"))
			   holder.bussiness_op_pause.setText("暂停");
			else
			   holder.bussiness_op_pause.setText("恢复");	

		}

		
		private void onModify(ViewHoider holder,int position)
		{
			onDetails(position,false);
		}

		private void onTerminate(ViewHoider holder,int position)
		{
			holder.bussiness_status.setText("申请开通");
			holder.bussiness_op_modify.setClickable(false);
			holder.bussiness_op_pause.setClickable(false);
			holder.bussiness_op_terminate.setClickable(false);				
			
			holder.bussiness_op_modify.setTextColor(Color.parseColor("#999999"));
			holder.bussiness_op_pause.setTextColor(Color.parseColor("#999999"));
			holder.bussiness_op_terminate.setTextColor(Color.parseColor("#999999"));
			
			mListViewData.get(position).put("status", "申请开通");
			//this.notifyDataSetChanged();
		}

		public class ViewHoider {
			TextView bussiness_name; // 生意名称
			TextView bussiness_status; // 申请状态
			TextView bussiness_op_modify; // 修改操作
			TextView bussiness_op_pause; // 暂停操作
			TextView bussiness_op_terminate; // 终止操作
		}
		
		private void NotifyDialog(String title,String notify,final int type,final ViewHoider holder,final int position)
	    {
	    	Builder builder = new Builder(mContext);
	        builder.setTitle(title);
	        builder.setMessage(notify);
	        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) {
	                dialog.dismiss();
	                if (type == 2)
	                	onPause(holder,position);
	                else
	                	onTerminate(holder,position);	
	            }

	        });
	        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

	            public void onClick(DialogInterface dialog, int which) {
	            }
	        });
	        builder.create().show();
	    }
	}


	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId())
		{
		case R.id.view_top_img_back:
			finish();
			break;
		case R.id.personnal_bussiness_tv:
			this.mPersonBussinessTv.setTextColor(Color.parseColor("#6ec850"));
			this.mCompanyBussinessTv.setTextColor(Color.parseColor("#333333"));
			//showProgressDialog(this);
			//mHandler.postDelayed(mUpdateDataRunnable, 100);
			myListViewAdapter.resetData(false);
			break;
		case R.id.company_bussiness_tv:
			this.mPersonBussinessTv.setTextColor(Color.parseColor("#333333"));
			this.mCompanyBussinessTv.setTextColor(Color.parseColor("#6ec850"));
			//showProgressDialog(this);
			//mHandler.postDelayed(mUpdateDataRunnable, 100);
			myListViewAdapter.resetData(true);
			break;
		}
	}
}