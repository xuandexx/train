package entity;

/**
 * 和服务器交互的消息封装
 * 
 * @author Simon
 */
public class MessageContent {

	// 发送消息的内容
	private String message;
	// 接受消息的目标,可能是群体,也可能是个体,个体用手机号码表示,群体用集合或者特定的数据描述
	private String from_tel;
	// 发送内容标识码:可能代表聊天信息,可能代表求助信息等等
	/**
	 * code:{1:表示聊天消息,}
	 */
	private int code;

	/**
	 * 发送信息的封装
	 * 
	 * @param content
	 *            :消息内容
	 * @param aim_tel
	 *            :目标
	 * @param from_tel
	 *            :消息源
	 * @param code
	 *            :消息模式
	 */
	public MessageContent(String request, String from_tel, int code) {
		super();
		this.message = request;
		this.from_tel = from_tel;
		this.code = code;
	}

	@Override
	public String toString() {
		return "{\"content\":\"" + message + "\",\"from_tel\":\"" + from_tel
				+ "\",\"code\":\"" + code + "\"}";
	}

}
