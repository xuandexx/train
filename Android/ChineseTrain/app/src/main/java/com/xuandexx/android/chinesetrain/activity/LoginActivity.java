package activity;

/** 用户登录 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import cn.ieauto.autogroup.android.MyApplication;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.app.ComConfig;
import cn.ieauto.autogroup.android.app.MyActivity;
import cn.ieauto.autogroup.android.db.UserDao;
import cn.ieauto.autogroup.android.entity.MessageContent;
import cn.ieauto.autogroup.android.entity.User;
import cn.ieauto.autogroup.android.util.CommonUtils;
import cn.ieauto.autogroup.android.util.MyAsyncTask;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMContactManager;
import com.easemob.chat.EMGroupManager;
import com.easemob.util.EMLog;
import com.easemob.util.HanziToPinyin;

@SuppressLint("DefaultLocale")
public class LoginActivity extends MyActivity {
	/** 点击控件 */
	private TextView forgot, register;
	private Button logon;
	/** 非点击控件 */
	private EditText userPhoneEditText, passwordEditText;
	/** 数据变/常量 */
	public static final int REQUEST_CODE_SETNICK = 1;
	private String userPhone = null;
	private String password = null;
	private String huanxinUser = null;
	/** 控制标记 */
	private boolean progressShow;
	private boolean autoLogin = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyApplication.isOnLine = true;
		setContentView(R.layout.activity_login);
		initViews();
		initEvents();
	}

	protected void initViews() {
		logon = (Button) findViewById(R.id.login);
		userPhoneEditText = (EditText) findViewById(R.id.login_userphone);
		passwordEditText = (EditText) findViewById(R.id.login_password);
		forgot = (TextView) findViewById(R.id.login_forgot);
		register = (TextView) findViewById(R.id.login_register);
		userPhone = MyApplication.getInstance().getUserPhone();
		password = MyApplication.getInstance().getPassword();
		if (!TextUtils.isEmpty(userPhone)) {
			userPhoneEditText.setText(userPhone);
		}
	}

	protected void initEvents() {
		logon.setOnClickListener(ocl);
		register.setOnClickListener(ocl);
		forgot.setOnClickListener(ocl);
		// 设置头像
		// DownloadPictureTask pageTask = new DownloadPictureTask(uri, bitmap) {
		// @Override
		// protected void onPostExecute(String result) {
		// faceImageView.setImageBitmap(bitmap);
		// }
		// };
		// pageTask.execute();
		// 如果用户名改变，清空密码
		userPhoneEditText.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				passwordEditText.setText("");
			}

			@Override
			public void afterTextChanged(Editable arg0) {

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {

			}
		});
	};

	@Override
	protected void onResume() {
		super.onResume();
		if (autoLogin) {
			return;
		}
		if (MyApplication.getInstance().getUserPhone() != null) {
			userPhoneEditText.setText(MyApplication.getInstance()
					.getUserPhone());
		}
	}

	// 登录方法
	private void dologin() {
		userPhone = userPhoneEditText.getText().toString().trim();
		password = passwordEditText.getText().toString().trim();
		if (TextUtils.isEmpty(userPhone)) {
			// 隐藏进度框
			toast("手机号码不能为空!");
			userPhoneEditText.requestFocus();
			return;
		} else if (!CommonUtils.validatePhone(userPhone)) {
			// 隐藏进度框
			toast("请输入正确的手机号码");
			userPhoneEditText.requestFocus();
			return;
		} else if (TextUtils.isEmpty(password)) {
			// 隐藏进度框
			toast("密码不能为空！");
			passwordEditText.requestFocus();
			return;
		} else if (!CommonUtils.isNetworkConnected(this)) {
			// 隐藏进度框
			toast(R.string.network_isnot_available);
			return;
		}
		// --------------------------------------------------------
		// 如果有用户名密码,点击登录即开始登录流程
		progressShow = true;
		// try {
		// doLog(PersonalRequest.doLogin(userPhone, password) + "");
		// } catch (ClientProtocolException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		showLoadingDialog("正在登录,请稍后...");

		String message = "password:" + password;
		// code=5010,表示登录时,更新cid数据
		MessageContent loginMessage = new MessageContent(message, userPhone, 2);
		/** 如果返回结果为登录成功,存储记录到Shared preference中,便于下一次登陆时直接获得 */
		MyAsyncTask asyncTask = new MyAsyncTask(loginMessage) {
			@Override
			protected void onPostExecute(String result) {
				if ("20".equals(result)) {
					dismissLoadingDialog();
					toast("服务器异常...");
					return;
				} else if ("0".equals(result)) {
					dismissLoadingDialog();
					toast("用户不存在!");
					return;
				} else if ("1".equals(result)) {
					dismissLoadingDialog();
					toast("密码不正确");
					return;
				} else if (TextUtils.isEmpty(result)) {
					dismissLoadingDialog();
					toast("服务器代码异常");
					return;
				} else if ("2".equals(result)) {
					dismissLoadingDialog();
					// 表示资料填写不完全
					MyApplication.getInstance().saveBaseInfo(userPhone,
							password, "");
					dismissLoadingDialog();
					// 填补资料
					startActivity(RegisterSecondActivity.class);
					defaultFinish();
					return;
				} else if ("ok".equals(result)) {
					huanxinUser = "cyx" + userPhone;
					MyApplication.getInstance().saveBaseInfo(userPhone,
							password, huanxinUser);
					doLog(result);
					// 调用登录方法
					login_huanxin(huanxinUser, password);
				}
			}

			protected void onPreExecute() {

			}
		};

		asyncTask.execute();

	}

	/**
	 * 设置hearder属性，方便通讯中对联系人按header分类显示，以及通过右侧ABCD...字母栏快速定位联系人
	 */
	protected void setUserHearder(String username, User user) {
		String headerName = null;
		if (!TextUtils.isEmpty(user.getNick())) {
			headerName = user.getNick();
		} else {
			headerName = user.getUsername();
		}
		if (username.equals(ComConfig.NEW_FRIENDS_USERNAME)) {
			user.setHeader("");
		} else if (Character.isDigit(headerName.charAt(0))) {
			user.setHeader("#");
		} else {
			user.setHeader(HanziToPinyin.getInstance()
					.get(headerName.substring(0, 1)).get(0).target.substring(0,
					1).toUpperCase());
			char header = user.getHeader().toLowerCase().charAt(0);
			if (header < 'a' || header > 'z') {
				user.setHeader("#");
			}
		}
	}

	// 定义上下文点击事件
	private OnClickListener ocl = new OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.login:
				dologin();
				break;
			case R.id.login_forgot:
				startActivity(FrogetPasswordActivity.class);
				break;
			case R.id.login_register:
				startActivity(RegisterFirstActivity.class);
				break;
			}
		}
	};

	// private void login_huanjin1(final String huanxinUser, final String
	// password) {
	// new EMCallBack() {
	// @Override
	// public void onSuccess() {
	// if (!progressShow) {
	// doLog("login success!");
	// return;
	// }
	// // 登陆成功，保存用户名密码
	// MyApplication.getInstance().setUserName(userPhone);
	// MyApplication.getInstance().setHuanxinUser(huanxinUser);
	// MyApplication.getInstance().setPassword(password);
	// runOnUiThread(new Runnable() {
	// public void run() {
	// }
	// });
	// try {
	// // ** 第一次登录或者之前logout后，加载所有本地群和回话
	// // ** manually load all local groups and
	// // conversations in case we are auto login
	// EMGroupManager.getInstance().loadAllGroups();
	// EMChatManager.getInstance().loadAllConversations();
	//
	// // demo中简单的处理成每次登陆都去获取好友username，开发者自己根据情况而定
	// List<String> usernames = EMContactManager.getInstance()
	// .getContactUserNames();
	// EMLog.d("roster", "contacts size: " + usernames.size());
	// Map<String, User> userlist = new HashMap<String, User>();
	// for (String username : usernames) {
	// User user = new User();
	// user.setUsername(username);
	// setUserHearder(username, user);
	// userlist.put(username, user);
	// }
	// // 添加user"申请与通知"
	// User newFriends = new User();
	// newFriends.setUsername(ComConfig.NEW_FRIENDS_USERNAME);
	// newFriends.setNick("申请与通知");
	// newFriends.setHeader("");
	// userlist.put(ComConfig.NEW_FRIENDS_USERNAME, newFriends);
	// // 添加"群聊"
	// User groupUser = new User();
	// groupUser.setUsername(ComConfig.GROUP_USERNAME);
	// groupUser.setNick("群聊");
	// groupUser.setHeader("");
	// userlist.put(ComConfig.GROUP_USERNAME, groupUser);
	//
	// // 存入内存
	// MyApplication.getInstance().setContactList(userlist);
	// // 存入db
	// UserDao dao = new UserDao(LoginActivity.this);
	// List<User> users = new ArrayList<User>(userlist.values());
	// dao.saveContactList(users);
	//
	// // 获取群聊列表(群聊里只有groupid和groupname的简单信息),sdk会把群组存入到内存和db中
	// EMGroupManager.getInstance().getGroupsFromServer();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// boolean updatenick = EMChatManager.getInstance()
	// .updateCurrentUserNick(MyApplication.currentUserNick);
	// if (!updatenick) {
	// EMLog.e("LoginActivity", "update current user nick fail");
	// }
	// // 隐藏进度框
	// dismissLoadingDialog();
	// if (!LoginActivity.this.isFinishing())
	// // 进入主页面
	// startActivity(MainActivity.class);
	// finish();
	// }
	//
	// @Override
	// public void onProgress(int progress, String status) {
	// }
	//
	// @Override
	// public void onError(int code, final String message) {
	// if (!progressShow) {
	// return;
	// }
	// }
	// };
	// }

	public void login_huanxin(final String huanxinUser, final String password) {
		EMChatManager.getInstance().login(huanxinUser, password,
				new EMCallBack() {
					@Override
					public void onSuccess() {
						if (!progressShow) {
							doLog("login success!");
							return;
						}
						// 登陆成功，保存用户名密码
						MyApplication.getInstance().saveBaseInfo(userPhone,
								password, huanxinUser);
						runOnUiThread(new Runnable() {
							public void run() {
							}
						});
						try {
							// ** 第一次登录或者之前logout后，加载所有本地群和回话
							// ** manually load all local groups and
							// conversations in case we are auto login
							EMGroupManager.getInstance().loadAllGroups();
							EMChatManager.getInstance().loadAllConversations();
							// demo中简单的处理成每次登陆都去获取好友username，开发者自己根据情况而定
							List<String> usernames = EMContactManager
									.getInstance().getContactUserNames();
							EMLog.d("roster",
									"contacts size: " + usernames.size());
							Map<String, User> userlist = new HashMap<String, User>();
							for (String username : usernames) {
								User user = new User();
								user.setUsername(username);
								// user.setNick(username);
								setUserHearder(username, user);
								userlist.put(username, user);
							}
							// 添加新朋友验证请求
							User newFriends = new User();
							newFriends
									.setUsername(ComConfig.NEW_FRIENDS_USERNAME);
							newFriends.setNick("新朋友验证请求");
							newFriends.setHeader("");
							userlist.put(ComConfig.NEW_FRIENDS_USERNAME,
									newFriends);
							// 添加寻找新朋友
							User searchFriends = new User() {
								@Override
								public String getUsername() {
									return "find_new_friends";
								}
							};
							searchFriends
									.setUsername(ComConfig.FIND_NEW_FRIENDS);
							searchFriends.setNick("寻找新朋友 ");
							searchFriends.setHeader("");
							userlist.put(ComConfig.FIND_NEW_FRIENDS,
									searchFriends);
							// 添加"群聊"
							User groupUser = new User();
							groupUser.setUsername(ComConfig.GROUP_USERNAME);
							groupUser.setNick("群聊");
							groupUser.setHeader("");
							userlist.put(ComConfig.GROUP_USERNAME, groupUser);
							// 存入内存
							MyApplication.getInstance()
									.setContactList(userlist);
							// 存入db
							UserDao dao = new UserDao(LoginActivity.this);
							List<User> users = new ArrayList<User>(userlist
									.values());
							dao.saveContactList(users);
							// 获取群聊列表(群聊里只有groupid和groupname的简单信息),sdk会把群组存入到内存和db中
							EMGroupManager.getInstance().getGroupsFromServer();
						} catch (Exception e) {
							e.printStackTrace();
						}
						boolean updatenick = EMChatManager.getInstance()
								.updateCurrentUserNick(MyApplication.nickName);
						if (!updatenick) {
							EMLog.e("LoginActivity",
									"update current user nick fail");
						}
						// 隐藏进度框
						dismissLoadingDialog();
						if (!LoginActivity.this.isFinishing())
							// 进入主页面
							startActivity(MainActivity.class);
						finish();
					}

					@Override
					public void onProgress(int progress, String status) {
					}

					@Override
					public void onError(int code, final String message) {
						if (!progressShow) {
							return;
						}
					}
				});
	}
}
