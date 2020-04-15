package util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;

import android.os.AsyncTask;
import cn.ieauto.autogroup.android.entity.MessageContent;

public abstract class MyAsyncTask extends AsyncTask<Object, Object, String> {

	private MessageContent messageContent = null;
	private HttpUtils httpUtils = null;

	public MyAsyncTask(MessageContent request) {
		this.messageContent = request;
	}

	/**
	 * 这里的Integer参数对应AsyncTask中的第一个参数 这里的String返回值对应AsyncTask的第三个参数
	 * 该方法并不运行在UI线程当中，主要用于异步操作，所有在该方法中不能对UI当中的空间进行设置和修改
	 * 但是可以调用publishProgress方法触发onProgressUpdate对UI进行操作 如果网络状况问题,返回20
	 * <p>
	 * 如果异步数据为空,仅仅是为了获得一个进度条,返回10;
	 */
	protected String doInBackground(Object... arg0) {
		httpUtils = new HttpUtils();
		String result = null;
		try {
			return result = httpUtils.dosend(messageContent);
		} catch (UnsupportedEncodingException e) {
			// 编码错误
		} catch (ClientProtocolException e) {
			//
		} catch (IOException e) {
			// 线程处理返回20表示有网时,发生了连接异常,服务器异常等原因
			result = "20";
		}
		return result;

	}

	/**
	 * 该方法运行在UI线程当中,并且运行在UI线程当中 可以对UI空间进行设置,在主线程中显示进度条
	 */
	protected abstract void onPreExecute();

	/**
	 * 这里的String参数对应AsyncTask中的第三个参数（也就是接收doInBackground的返回值）
	 * 在doInBackground方法执行结束之后在运行，并且运行在UI线程当中 可以对UI空间进行设置
	 */
	protected abstract void onPostExecute(String result);
}
