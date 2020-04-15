package util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import cn.ieauto.autogroup.android.app.MyActivity;
import cn.ieauto.autogroup.android.entity.MessageContent;


public class DataAcquisitionUtil extends MyActivity{
	/**
	 * 用于页面跳转时的数据获取
	 * @param message
	 * @param code
	 * @param bundle
	 * @param context
	 * @param cls
	 */
	public  static void DataAcquisition(String message, int code,final Context context, final Class<?> cls) {
		if (CommonUtils.isNetworkConnected(context)) {
			MessageContent loginMessage = new MessageContent(message, "",
					code);
			MyAsyncTask asyncTask = new MyAsyncTask(loginMessage) {

				protected void onPostExecute(String result) {
					if ("20".equals(result)) {
						shMg(context, "网络连接失败");

						return;
					} else if ("".equals(result)) {
						shMg(context, "数据获取失败....");
						return;
					} else {
						Intent intent=new Intent(context, cls);
						Bundle bundle =new Bundle();
						bundle.putString("result", result);
						intent.putExtras(bundle);
						context.startActivity(intent);
					}
				}

				protected void onPreExecute() {
										shMg(context, "数据获取中...");

				}
			};
			asyncTask.execute();
		} else {
			shMg(context, "对不起，当前网络不可用!");
		}
	}

	/**
	 * 本页面的数据获取
	 * @param message
	 * @param code
	 * @param context
	 * @return
	 */
	 static String mResult=null;
	public  static String  DataAcquisition(String message, int code,final Context context) {

		if (CommonUtils.isNetworkConnected(    context    )) {
			MessageContent loginMessage = new MessageContent("", "",code);
			MyAsyncTask asyncTask = new MyAsyncTask(loginMessage) {
				protected void onPostExecute( String result) {
//					dismissLoadingDialog();
					if ("20".equals(result)) {
						shMg(context, "网络连接失败");
						return;
					} else if ("".equals(result)) {
						shMg(context, "数据获取失败....");
						return;
					}else {
				mResult=result;
					}
				}

				protected void onPreExecute() {
//					showLoadingDialog("正在登录,请稍后...");
				}
			};
			asyncTask.execute();
		} else {
			shMg(context, "对不起，当前网络不可用!");
		}
		return mResult;
	}
	/**
	 * 向服务器单方向提交数据
	 * @param message
	 * @param code
	 * @param context
	 * @param cls
	 * @return 
	 */
/*	public  static   void DataAcquisition(String message, int code,final Context context,final Class<?> cls) {

		if (NetworkConnected.isNetworkConnected(    context    )) {
			MessageContent loginMessage = new MessageContent(message, "","",code);
			MyAsyncTask asyncTask = new MyAsyncTask(loginMessage) {
				protected void onPostExecute( String result) {
					dismissLoadingDialog();
					if ("20".equals(result)) {
						shMg(context, "网络连接失败");
						return;
					}  else {
						shMg(context, "提交成功!");
						Intent intent=new Intent(context, cls);
						context.startActivity(intent);
						
					}
				}

				protected void onPreExecute() {
					showLoadingDialog("正在提交中,请稍后...");
				}
			};
			asyncTask.execute();
		} else {
			shMg(context, "对不起，当前网络不可用!");
		}
	}*/


	public static void shMg(Context context, String text){
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}
	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void initEvents() {
		// TODO Auto-generated method stub
		
	}

}
