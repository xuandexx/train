package adapter;

import java.util.Random;

public class Code {

	/**
	 * 验证码为4位
	 */
	private static final int DEFAULT_CODE_LENGTH = 4;

	private static final char[] CHARS = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
			'x', 'y', 'z' };

	private static Code bpUtil;

	private Code() {
	};

	public static Code getInstance() {
		if (bpUtil == null)
			bpUtil = new Code();
		return bpUtil;
	}

	private Random random = new Random();

	public String getCode() {
		return createCode();
	}

	private String createCode() {
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < DEFAULT_CODE_LENGTH; i++) {
			buffer.append(CHARS[random.nextInt(CHARS.length)]);
		}
		return buffer.toString();
	}

}
