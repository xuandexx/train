package entity;

import java.util.Calendar;

import android.graphics.Bitmap;

/**
 * 聊天:包含头像.聊天对方名字,信息内容,信息时间,信息种类code,对方手机号码
 * 
 * @author Liting
 * 
 */
public class ChatMessage {

	public Bitmap face;
	public String other_tel;
	public String chat_message_content;
	public String chat_time = getTime();
	public String code;

	

	public ChatMessage(Bitmap face, String other_tel,
			String chat_message_content, String code) {
		super();
		this.face = face;
		this.other_tel = other_tel;
		this.chat_message_content = chat_message_content;
		this.code = code;
	}

	public String getChat_time() {
		return chat_time;
	}

	public void setChat_time(String chat_time) {
		this.chat_time = chat_time;
	}

	public String getTime() {
		Calendar rightNow = Calendar.getInstance();
		int hour = rightNow.get(Calendar.HOUR_OF_DAY);
		int minute = rightNow.get(Calendar.MINUTE);
		return hour + ":" + minute;
	}

}
