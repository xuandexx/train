package adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SpinnerAdapter extends ArrayAdapter<String> {
	Context context;
	String []items=new String []{};

	public SpinnerAdapter(Context context, 
			int textViewResourceId, String[] objects) {
		super(context,  textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		this.context=context;
		this.items=objects;
	}
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView==null){
			LayoutInflater inflater=LayoutInflater.from(context);
			convertView=inflater.inflate(android.R.layout.simple_spinner_item,parent,false );
		}
		  TextView tv = (TextView) convertView
	                .findViewById(android.R.id.text1);
		    tv.setText(items[position]);
	        tv.setTextColor(Color.BLACK);
	        tv.setGravity(Gravity.CENTER_HORIZONTAL);
	        tv.setTextSize(15);
	        return convertView;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		 if (convertView == null) {
	            LayoutInflater inflater = LayoutInflater.from(context);
	            convertView = inflater.inflate(
	                    android.R.layout.simple_spinner_item, parent, false);
	        }
		 
		  TextView tv = (TextView) convertView
	                .findViewById(android.R.id.text1);
		    tv.setText(items[position]);
	        tv.setTextColor(Color.WHITE);
	        tv.setGravity(Gravity.CENTER_HORIZONTAL);
	        tv.setTextSize(15);
	        return convertView;
	}
	

}
