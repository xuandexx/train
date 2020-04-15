package util;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.TextView;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.adapter.CarCityAdapter;
import cn.ieauto.autogroup.android.adapter.CarNoAdapter;

/**
 * 读取城市简称及字母
 * 
 * @author Simon1
 * 
 */
public class CarTypeKeyUtil {
	private Activity context;
	private View layout;
	private PopupWindow popCarCity;
	private PopupWindow popCarLetters;
	private GridView gv_car_pho;

	public CarTypeKeyUtil(Activity context) {
		this.context = context;
	}

	/**
	 * 获取城市简称
	 */

	@SuppressWarnings({ "static-access", "deprecation" })
	public void getCarLocation(View v, TextView city) {
		LayoutInflater inflater=context.getLayoutInflater();
		int [] pos = new int[2];  
		v.getLocationOnScreen(pos);  
	    inflater  = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        //获取弹出菜单的布局
        layout = inflater.inflate(R.layout.car_pho_activity,null);
        //设置popupWindow的布局
        popCarCity = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        popCarCity.setFocusable(true);
        popCarCity.setBackgroundDrawable(new BitmapDrawable());  
		//实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0xb0000000);
		// 设置SelectPicPopupWindow弹出窗体的背景
		popCarCity.setBackgroundDrawable(dw);
		popCarCity.showAtLocation(v, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0); //设置在屏幕中的显示位置
        
        gv_car_pho=(GridView)layout.findViewById(R.id.gv_car_pho);
        CarCityAdapter carCityAdapter=new CarCityAdapter(context,city,popCarCity);
        gv_car_pho.setAdapter(carCityAdapter);
        
        layout.setOnTouchListener(new OnTouchListener() {
			
    		public boolean onTouch(View v, MotionEvent event) {
    				
    				int height = layout.findViewById(R.id.view_gridview).getTop();
    				int y=(int) event.getY();
    				if(event.getAction()==MotionEvent.ACTION_UP){
    					if(y<height){
    						popCarCity.dismiss();
    					}
    				}				
    				return true;
    			}
    		});

	}

	/**
	 * 获取字母
	 */
	@SuppressWarnings({ "static-access", "deprecation" })
	public void getCarLetters(View v, TextView city) {
		LayoutInflater inflater=context.getLayoutInflater();
		int [] pos = new int[2];
		v.getLocationOnScreen(pos);
	    inflater  = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        //获取弹出菜单的布局
        layout = inflater.inflate(R.layout.car_pho_activity,null);
        //设置popupWindow的布局
        popCarLetters= new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        popCarLetters.setFocusable(true);
        popCarLetters.setBackgroundDrawable(new BitmapDrawable());  
		//实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0xb0000000);
		// 设置SelectPicPopupWindow弹出窗体的背景
		popCarLetters.setBackgroundDrawable(dw);
		popCarLetters.showAtLocation(v, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0); //设置在屏幕中的显示位置
        gv_car_pho=(GridView)layout.findViewById(R.id.gv_car_pho);
        CarNoAdapter carNoAdapter=new CarNoAdapter(context,city,popCarLetters);
        gv_car_pho.setAdapter(carNoAdapter);   
        popCarLetters.setFocusable(false);
 	    layout.setOnTouchListener(new OnTouchListener() {
			
   		public boolean onTouch(View v, MotionEvent event) {
   				
   				int height = layout.findViewById(R.id.view_gridview).getTop();
   				int y=(int) event.getY();
   				if(event.getAction()==MotionEvent.ACTION_UP){
   					if(y<height){
   						popCarLetters.dismiss();
   					}
   				}				
   				return true;
   			}
   		});


	}

}
