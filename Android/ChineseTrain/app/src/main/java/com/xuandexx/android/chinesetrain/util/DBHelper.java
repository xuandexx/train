package util;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import cn.ieauto.autogroup.android.MyApplication;
import cn.ieauto.autogroup.android.entity.ChatMessage;

public class DBHelper extends SQLiteOpenHelper {

	public static final String TB_NAME = "chat_record";
	public static final String MY_PHONE = "user_phone";
	public static final String ID = "_id";
	public static final String FACE = "face_path";
	public static final String OTHER_TEL = "other_tel";
	public static final String MSG_CONTENT = "message_content";
	public static final String MSG_CODE = "message_code";
	public static final String MSG_TIME = "message_time";
	public static final String ISCHECK = "ischecked";
	// 数据库版本
	public static int version = 1;
	public Context context;

	/**
	 * 构造函数
	 * 
	 * @param context
	 *            Context类型，上下文对象。
	 * @param name
	 *            String类型，数据库的名称
	 * @param factory
	 *            CursorFactory类型
	 * @param version
	 *            int类型，数据库版本
	 */
	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		this.getWritableDatabase();
	}

	/**
	 * 关闭数据库
	 */
	public void Close() {
		this.getWritableDatabase().close();
	}

	/**
	 * 创建数据库
	 */
	public void onCreate(SQLiteDatabase db) {
		db.beginTransaction();
		try {
			db.execSQL("CREATE TABLE IF NOT EXISTS " + TB_NAME + " (" + ID
					+ " INTEGER PRIMARY KEY," + MY_PHONE + " VARCHAR," + FACE
					+ " VARCHAR," + OTHER_TEL + " VARCHAR," + MSG_CONTENT
					+ " VARCHAR," + MSG_CODE + " VARCHAR," + MSG_TIME
					+ " VARCHAR)");
			db.setTransactionSuccessful();
		} catch (Exception e) {

		} finally {
			db.endTransaction();// 此时不能关闭数据库，不然创建数据库完成后，数据库不能使用
		}
	}

	/**
	 * 删除数据库
	 */
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TB_NAME);
		onCreate(db);
	}

	/**
	 * 存储新纪录
	 * 
	 * @param face_path
	 *            :头像路径
	 * @param other_name
	 *            :聊天对方姓名
	 * @param other_tel
	 *            :对方手机号码
	 * @param msg_content
	 *            :消息内容
	 * @param msg_code
	 *            :消息类型,系统消息,或者是个人消息
	 * @param msg_time
	 *            :消息时间
	 */
	public void addChatRecord(String face_path, String other_tel,
			String msg_content, String msg_code, String msg_time) {
		ContentValues values = new ContentValues();
		/**
		 * 如果一部手机有多个用户,那么,区分聊天记录的归属就用注册手机号作为判别
		 */
		values.put(DBHelper.MY_PHONE, MyApplication.getInstance().getUserPhone());
		values.put(DBHelper.FACE, face_path);
		values.put(DBHelper.OTHER_TEL, other_tel);
		values.put(DBHelper.MSG_CONTENT, msg_content);
		values.put(DBHelper.MSG_CODE, msg_code);
		values.put(DBHelper.MSG_TIME, msg_time);
		this.getWritableDatabase()
				.insert(DBHelper.TB_NAME, DBHelper.ID, values);
	}

	/**
	 * 删除单条记录
	 * 
	 * @param id
	 */
	public void delUser(int id) {
		this.getWritableDatabase().delete(DBHelper.TB_NAME,
				DBHelper.ID + " = " + id, null);
	}

	/**
	 * 清空聊天记录
	 */
	public void delAllUser() {
		this.getWritableDatabase().delete(DBHelper.TB_NAME, null, null);
	}

	/**
	 * 通过手机号码查询聊天记录
	 * 
	 * @param tel
	 * @return
	 */
	@SuppressWarnings("null")
	public List<ChatMessage> findByTel(String tel) {
		/*
		 * 如果数据库空间还充足，则会调用getWitableDatabase();
		 * 返回的数据库对象可以执行写操作；如果数据库磁盘空间已满，则会返回一个只读的数据库对象
		 */
		List<ChatMessage> chatMessages = new ArrayList<ChatMessage>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(DBHelper.TB_NAME, null, MY_PHONE + "=?",
				new String[] { tel }, null, null, null);

		ChatMessage cm = null;
		if (cursor.moveToFirst()) {
			// cm = new ChatMessage(ImageUtils.getBitmap(cursor.getString(cursor
			// .getColumnIndex(FACE))), cursor.getString(cursor
			// .getColumnIndex(OTHER_TEL)), cursor.getString(cursor
			// .getColumnIndex(MSG_CONTENT)), cursor.getString(cursor
			// .getColumnIndex(MSG_CODE)));
			cm.setChat_time(cursor.getString(cursor.getColumnIndex(MSG_TIME)));
			chatMessages.add(cm);
		}
		return chatMessages;
	}
}