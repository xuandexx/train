package httpTask;

/**
 * 异步获取网络图片资源工具类
 */
import android.graphics.Bitmap;
import android.os.AsyncTask;
import cn.ieauto.autogroup.android.util.ImageUtils;

public abstract class DownloadPictureTask extends
		AsyncTask<Bitmap, Integer, String> {

	// 图片网络地址
	String uri;
	// 承载图片的容器
	public Bitmap bitmap = null;

	/**
	 * 构造函数
	 * 
	 * @param p_uri
	 *            :网络uri资源
	 * @param p_bitmap
	 *            :bitmap交互
	 */
	public DownloadPictureTask(String p_uri, Bitmap p_bitmap) {
		this.uri = p_uri;
		this.bitmap = p_bitmap;
	}

	/**
	 * 异步加载图片资源
	 */
	@Override
	protected String doInBackground(Bitmap... arg0) {
		bitmap = ImageUtils.getBitmap(uri);
		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

	/**
	 * 得到图片
	 */
	public Bitmap getBitmap() {
		return bitmap;
	}
}
