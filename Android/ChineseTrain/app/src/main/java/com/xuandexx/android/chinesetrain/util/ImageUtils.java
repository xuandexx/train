package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;

import com.easemob.util.EMLog;
import com.easemob.util.PathUtil;

/**
 * 图片工具
 * 
 * @author Simon
 */
public class ImageUtils {

	/**
	 * 从网络获取网络图片路径
	 * 
	 * @param URL_PATH
	 * @return
	 * @throws IOException
	 */
	public static Bitmap getBitmap(String URL_PATH) {
		InputStream inputStream = null;
		try {
			URL url = new URL(URL_PATH); // 服务器地址
			Bitmap bitmap = null;
			if (url != null) {
				// 打开连接
				HttpURLConnection httpURLConnection = (HttpURLConnection) url
						.openConnection();
				httpURLConnection.setConnectTimeout(3000);// 设置网络连接超时的时间为3秒
				httpURLConnection.setRequestMethod("GET"); // 设置请求方法为GET
				httpURLConnection.setDoInput(true); // 打开输入流
				int responseCode = httpURLConnection.getResponseCode(); // 获取服务器响应值
				if (responseCode == HttpURLConnection.HTTP_OK) { // 正常连接
					inputStream = httpURLConnection.getInputStream(); // 获取输入流
					bitmap = BitmapFactory.decodeStream(inputStream);
					return bitmap;
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 把bitmap转换成drawable
	 * 
	 * @param bitmap
	 * @return
	 */
	public static Drawable convertBitmapToDrawable(Bitmap bitmap) {
		@SuppressWarnings("deprecation")
		BitmapDrawable bd = new BitmapDrawable(bitmap);
		// 因为BtimapDrawable是Drawable的子类，最终直接使用bd对象即可。
		return bd;
	}

	@SuppressWarnings("unused")
	public static void sendBitmap(String fileName) {
		FileInputStream in = null;
		try {
			in = new FileInputStream(fileName);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		byte buffer[] = null;
		try {
			buffer = ImageStreamUtil.read(in);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}// 把图片文件流转成byte数组
		byte[] encod = Base64.encode(buffer, Base64.DEFAULT);// 使用base64编码

		InputStream inputStream = null;
		try {
			URL url = new URL("http://192.168.2.30:8080/chat_server/chat"); // 服务器地址
			Bitmap bitmap = null;
			if (url != null) {
				// 打开连接
				HttpURLConnection httpURLConnection = (HttpURLConnection) url
						.openConnection();
				httpURLConnection.setConnectTimeout(3000);// 设置网络连接超时的时间为3秒
				httpURLConnection.setRequestMethod("GET"); // 设置请求方法为GET
				httpURLConnection.setDoInput(true); // 打开输入流
				int responseCode = httpURLConnection.getResponseCode(); // 获取服务器响应值
				if (responseCode == HttpURLConnection.HTTP_OK) { // 正常连接
					inputStream = httpURLConnection.getInputStream(); // 获取输入流
					bitmap = BitmapFactory.decodeStream(inputStream);
				}
			}
		} catch (MalformedURLException e) {
		} catch (ProtocolException e) {
		} catch (IOException e) {
		}
	}

	// public static String getThumbnailImagePath(String imagePath) {
	// String path = imagePath.substring(0, imagePath.lastIndexOf("/") + 1);
	// path += "th" + imagePath.substring(imagePath.lastIndexOf("/") + 1,
	// imagePath.length());
	// EMLog.d("msg", "original image path:" + imagePath);
	// EMLog.d("msg", "thum image path:" + path);
	// return path;
	// }

	public static String getImagePath(String remoteUrl)
	{
		String imageName= remoteUrl.substring(remoteUrl.lastIndexOf("/") + 1, remoteUrl.length());
		String path =PathUtil.getInstance().getImagePath()+"/"+ imageName;
        EMLog.d("msg", "image path:" + path);
        return path;
		
	}
	
	
	public static String getThumbnailImagePath(String thumbRemoteUrl) {
		String thumbImageName= thumbRemoteUrl.substring(thumbRemoteUrl.lastIndexOf("/") + 1, thumbRemoteUrl.length());
		String path =PathUtil.getInstance().getImagePath()+"/"+ "th"+thumbImageName;
        EMLog.d("msg", "thum image path:" + path);
        return path;
    }
	

}