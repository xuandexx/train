package com.xuandexx.android.chinesetrain.base;

public class ComConfig {
	// 地图key
	public static final String mapkey = "9116IGVvuwqRej5rfA2eiNPv";
	// poi附近范围
	public static final int RANFGE = 5000;
	/** 保存是否是第一次运行程序的标记 */
	public final static String IS_FIRST_RUN = "isFirstRun";
	/** 不是第一次运行标识 */
	public final static int NOT_FIRST = 1;
	/** 是第一次运行标识 */
	public final static int IS_FIRST = -1;
	// 服务器IP地址
//	private static String IEAUTO_URL = "http://www.ieauto.cn/";
//	private static String IEAUTO_URL = "http://www.ieauto.cn/";
	// 测试
	private static String IEAUTO_URL = "http://192.168.1.241:8080/";
	/****************************************************************/
	// 环信中需要使用的常量,暂时未具体注释
	public static final String NEW_FRIENDS_USERNAME = "item_new_friends";
	public static final String GROUP_USERNAME = "item_groups";
	public static final String MESSAGE_ATTR_IS_VOICE_CALL = "is_voice_call";
	/** intent 城市 */
	public static final int INTENT_CITY = 1;
	/** 车品牌,车系 */
	public static final int INTENT_BRAND_CARS = 2;
	/** 车型 */
	public static final int INTENT_MODELS = 3;
	/** 排序方式 */
	public static final int INTENT_SORT_BY = 4;
	/** 价格区间 */
	public static final int INTENT_PRICE_TANGE = 5;
	/** 保险公司 */
	public static final int INTENT_INSURANCE_COMPANY = 6;
	/** 年份区间 */
	public static final int INTENT_YEAR_RANGE = 7;
	// 车型颜色选择
	public static final int INTENT_CARS_COLORS = 8;
	// 车辆行驶里程
	public static final int INTENT_CARS_ASSMENT = 9;
	/** 拍照 */
	public static final int INTENT_PHOTOGRAPH = 10;
	/** 车架号 */
	public static final int INTENT_CHASSIS_NUMBER = 11;
	/** 发动机号 */
	public static final int INTENT_ENGINE_NO = 12;
	/** 车牌数字 */
	public static final int INTENT_LICENSE_NUMBER = 13;
	//车排挡
	public static final int INTENT_CARS_GEAR=14;
	//保险的种类
	public static final int INTENT_CARS_SAFE=15;
	//行驶里程区间
	public static final int INTENT_CARS_COURS=16;
	//排放标准
	public static final int INTENT_CARS_BLOWFF=17;
	/***/
	/***/
	/***/

	public static final String FIND_NEW_FRIENDS = "find_new_friends";// 寻找新朋友
	/** 登录 5010 */
	public static final String LOGIN_URL = IEAUTO_URL + "server/login";
	/**
	 * 注册第一步 5022
	 */
	public static final String RIGISTER_URL = IEAUTO_URL + "";
	/**
	 * 注册第二步 5020
	 */
	public static final String RIGISTER_S_URL = IEAUTO_URL + "";
	// /**
	// * 我的车友圈
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 收到车友求助信息 3120
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 发出车友求助信息 3110
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 紧急救援（服务商详情，评论） 3130
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 违章查询 3140
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 违章办理 3160
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 维修保养 3170
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 代办验车 代办验车-本地车代验 3180
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 代办验车-外地车代验 3181
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 我要验车 3161
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 汽车美容-洗车 3190
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 汽车美容-美容 3191
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 车险理赔 3200
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 代办理赔 3201
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 装潢改装 装潢 3210
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 改装 3211
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 找代驾 3220
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 车辆质保
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 租车拼车 私人租车 3240
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 租赁公司 3241
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 拼车 3242
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 我要租车 3243
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 买车卖车 3250
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 车友求购 3251
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 二手车商 3252
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 我要卖车 3253
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 代办过户 帮人过户 3260
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 我要过户 3261
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 陪练驾校 陪练 3270
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 驾校 3271
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 我要申请提供车生活服务
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 我的生意（状态、修改、暂停、停止）
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 个人信息
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 我的相册
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 我的座驾 4120
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 我的生意
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 我的帐号
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 新消息提示
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 帮助
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 我的收藏
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 我要评论 3290
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * IM
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 百度地图
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 短信验证码 5021
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 忘记密码--发送验证码 5030
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 忘记密码--验证验证码 5031
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
	// /**
	// * 密码重置 5032
	// */
	// public static final String LOGIN_URL = IEAUTO_URL + "";
}
