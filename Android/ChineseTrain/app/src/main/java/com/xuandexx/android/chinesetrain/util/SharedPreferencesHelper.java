package util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {

	private final static String TAG = "SharedPreferencesHelper";
	private SharedPreferences prefs;
	private SharedPreferences.Editor editor;

	public SharedPreferencesHelper(Context context) {
		prefs = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
		editor = prefs.edit();
	}

	/**
	 * 根据键字符串，存储一个字符串值
	 * 
	 * @param key
	 * @param value
	 * @return 返回提交是否成功
	 */
	public boolean putString(String key, String value) {
		editor.putString(key, value);
		return editor.commit();
	}

	/**
	 * 根据key值得到存储结果，如果没有找到value就返回null
	 * 
	 * @param key
	 * @return
	 */
	public String getString(String key) {
		return prefs.getString(key, null);
	}

	/**
	 * 根据键字符串，存储一个字符串值
	 * 
	 * @param key
	 * @param value
	 * @return 返回提交是否成功
	 */
	public boolean putInt(String key,int value) {
		editor.putInt(key, value);
		return editor.commit();
	}

	/**
	 * 根据key值得到存储结果，如果没有找到value就返回-1
	 * 
	 * @param key
	 * @return
	 */
	public int getInt(String key) {
		return prefs.getInt(key, -1);
	}

	/**
	 * 清空数据
	 * 
	 * @return
	 */
	public boolean clear() {
		editor.clear();
		return editor.commit();
	}

	/**
	 * 关闭当前对象
	 * 
	 * @return
	 */
	public void close() {
		prefs = null;
	}
}
