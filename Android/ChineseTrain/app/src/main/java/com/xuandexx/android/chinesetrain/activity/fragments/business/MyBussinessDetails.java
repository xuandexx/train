package activity.fragments.business;

import java.util.Calendar;

import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;
import cn.ieauto.autogroup.android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

public class MyBussinessDetails extends Activity implements OnClickListener
{
	private int minYear = 1970;  //最小年份
	private int fontSize = 13; 	 //字体大小
	private WheelView yearWheel,monthWheel,dayWheel,hourWheel,minuteWheel,secondWheel;
	public static String[] hourContent = null;
	public static String[] minuteContent=null;
	public static String[] secondContent=null;
	
	private View mContentView;
	private boolean isNew=true,isCompany=true;
	private String mBussinessName="代驾";
	//声明TimePicker对象  
	private  TextView  m_TimePicker01,m_TimePicker02;
	
	LinearLayout mBussinessDetailsLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mContentView = LinearLayout.inflate(this,R.layout.moduels__home__my_bussiness_details,null);
		setContentView(mContentView);		
		init();			
	}
	
	private void init()
	{
		Bundle bd = this.getIntent().getExtras();

        if (bd != null) {
        	isNew = bd.getBoolean("isNew");
        	isCompany = bd.getBoolean("isCompany");
        	mBussinessName = bd.getString("bussinessName");
        }
        
        initContent();
        
		mBussinessDetailsLayout = (LinearLayout) mContentView.findViewById(R.id.bussiness_details_layout);
		View child = null;
		
		if (isCompany)
		{
			child = LinearLayout.inflate(this, R.layout.modules__home__my_bussiness_company_header,null);
		}
		else
		{
		   child = LinearLayout.inflate(this, R.layout.modules__home__my_bussiness_personalinfo_header,null);
		   if (mBussinessName.equals("车险销售") || mBussinessName.equals("理赔指导"))
		   {
			   child.findViewById(R.id.mybussiness_mingpian_phote_layout).setVisibility(View.VISIBLE);
			   child.findViewById(R.id.mybussiness_baoxian_company_layout).setVisibility(View.VISIBLE);
		   }
		   else if (mBussinessName.equals("陪练")||mBussinessName.equals("代驾"))
		   {
			   child.findViewById(R.id.mybussiness_driver_cards_layout).setVisibility(View.VISIBLE);
			   child.findViewById(R.id.mybussiness_driver_years_layout).setVisibility(View.VISIBLE);
			   if (mBussinessName.equals("陪练"))
			      child.findViewById(R.id.mybussiness_master_cards_layout).setVisibility(View.VISIBLE);;
		   }
		   m_TimePicker01 = (TextView) child.findViewById(R.id.TimePicker01);
		   m_TimePicker01.setOnClickListener(this);		   
		   
		   m_TimePicker02 = (TextView) child.findViewById(R.id.TimePicker02);
		   m_TimePicker02.setOnClickListener(this);
		}
		mBussinessDetailsLayout.addView(child);
		this.mContentView.findViewById(R.id.view_top_img_back).setOnClickListener(this);		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId())
		{
		case R.id.TimePicker01:
			buttonClick(arg0,1);
			break;
		case R.id.TimePicker02:
			buttonClick(arg0,2);
			break;
		case R.id.view_top_img_back:
			finish();
			break;
		}
	}
	
	public void initContent()
	{		
		hourContent = new String[24];
		for(int i=0;i<24;i++)
		{
			hourContent[i]= String.valueOf(i);
			if(hourContent[i].length()<2)
	        {
				hourContent[i] = "0"+hourContent[i];
	        }
		}
			
		minuteContent = new String[60];
		for(int i=0;i<60;i++)
		{
			minuteContent[i]=String.valueOf(i);
			if(minuteContent[i].length()<2)
	        {
				minuteContent[i] = "0"+minuteContent[i];
	        }
		}
		secondContent = new String[60];
		for(int i=0;i<60;i++)
		{
			secondContent[i]=String.valueOf(i);
			if(secondContent[i].length()<2)
	        {
				secondContent[i] = "0"+secondContent[i];
	        }
		}
	}
    
    public void buttonClick(View v,final int type)
    {
    	int id = v.getId();
    	//if(id==R.id.pick_bt)
    	{
    		View view = LayoutInflater.from(this).inflate(R.layout.time_picker, null); 
    			
    		Calendar calendar = Calendar.getInstance();
    	    int curHour = calendar.get(Calendar.HOUR_OF_DAY);
            int curMinute = calendar.get(Calendar.MINUTE);
            int curSecond = calendar.get(Calendar.SECOND);
     	    
    	    hourWheel = (WheelView)view.findViewById(R.id.hourwheel);
    	    minuteWheel = (WheelView)view.findViewById(R.id.minutewheel);
    	    secondWheel = (WheelView)view.findViewById(R.id.secondwheel);
    	    
    	   
            AlertDialog.Builder builder = new AlertDialog.Builder(this);  
            builder.setView(view); 
            
            hourWheel.setViewAdapter(new ArrayWheelAdapter<String>(this,hourContent));
	        hourWheel.setCurrentItem(curHour);
	        hourWheel.setVisibleItems(1);
	        //hourWheel.setCyclic(true);
	        //hourWheel.setInterpolator(new AnticipateOvershootInterpolator());
	        
	        minuteWheel.setViewAdapter(new ArrayWheelAdapter<String>(this,minuteContent));
	        minuteWheel.setCurrentItem(curMinute);
	        minuteWheel.setVisibleItems(1);
	        //minuteWheel.setCyclic(true);
	        //minuteWheel.setInterpolator(new AnticipateOvershootInterpolator());
	        
	        secondWheel.setViewAdapter(new ArrayWheelAdapter<String>(this,secondContent));
	        secondWheel.setCurrentItem(curSecond);
	        secondWheel.setVisibleItems(1);
	        //secondWheel.setCyclic(true);
	        //secondWheel.setInterpolator(new AnticipateOvershootInterpolator());
			 
	        builder.setTitle("选取时间");  
	        builder.setPositiveButton("确  定", new DialogInterface.OnClickListener() {  

	        	@Override  
            	public void onClick(DialogInterface dialog, int which) {  
            	
	        		StringBuffer sb = new StringBuffer();  
	        		sb.append(hourWheel.getCurrentItem())  
	        		.append(":").append(minuteWheel.getCurrentItem())
	        		.append(":").append(secondWheel.getCurrentItem());  
	        		dialog.cancel(); 
	        		if (type == 1) m_TimePicker01.setText(sb);
	        		else m_TimePicker02.setText(sb);
	        			
            	}  
	        });  
       
	        builder.show();
    	}
    }
}