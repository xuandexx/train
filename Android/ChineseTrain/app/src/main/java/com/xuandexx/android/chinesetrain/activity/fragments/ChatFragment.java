package activity.fragments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.ieauto.autogroup.android.MyApplication;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.activity.MainActivity;
import cn.ieauto.autogroup.android.activity.fragments.chat.ChatActivity;
import cn.ieauto.autogroup.android.adapter.ChatAllHistoryAdapter;
import cn.ieauto.autogroup.android.app.MyFragment;
import cn.ieauto.autogroup.android.db.InviteMessgeDao;
import cn.ieauto.autogroup.android.entity.User;

import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMContact;
import com.easemob.chat.EMConversation;
import com.easemob.chat.EMGroup;
import com.easemob.chat.EMGroupManager;
import com.easemob.chat.EMMessage;

/**
 * 聊天记录Fragment
 * 
 */
public class ChatFragment extends MyFragment {

	/** 事件控件 */
	private InputMethodManager inputMethodManager;
	private ListView listView;
	private EditText query;// 搜索框
	private ImageButton clearSearch;// 搜索框中清除button
	public RelativeLayout errorItem;// 联网通知
	RelativeLayout searchbar;
	/** 非事件控件 */
	public TextView errorText;
	/** 数据变/常量 */
	private Map<String, User> contactList;
	private List<EMGroup> groups;
	/** 控制标记,适配器 */
	private ChatAllHistoryAdapter adapter;
	private boolean hidden;

	// private ImageView img_back;
	// private TextView tv_title;
	View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_chat, container, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initViews(view);
		initEvents(view);
	}

	protected void initViews(View view) {
		// 设置头布局布局可见
		view.findViewById(R.id.view_top_img_back).setVisibility(8);// 后退键
		//view.findViewById(R.id.view_top_tv_linear).setVisibility(8);// 分隔图
		//view.findViewById(R.id.view_top_img_left).setVisibility(0);// 公司logo
		((TextView) view.findViewById(R.id.view_top_tv_title))
				.setText(R.string.chat);// 标题
		view.findViewById(R.id.cyh_add_friend).setVisibility(8);// 最右边加号
		view.findViewById(R.id.view_top_img_right).setVisibility(8);// 最右边图标
		// 设置键盘
		inputMethodManager = (InputMethodManager) getActivity()
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		errorItem = (RelativeLayout) view.findViewById(R.id.chat_rl_error_item);
		errorText = (TextView) errorItem.findViewById(R.id.tv_connect_errormsg);
		// contact list
		contactList = MyApplication.getInstance().getContactList();
		listView = (ListView) view.findViewById(R.id.chatf_lv_list);

		adapter = new ChatAllHistoryAdapter(getActivity(), 1,
				loadConversationsWithRecentChat());
		groups = EMGroupManager.getInstance().getAllGroups();
		// 设置adapter
		listView.setAdapter(adapter);
		// 搜索框中清除button
		searchbar = (RelativeLayout) view.findViewById(R.id.chat_rl_search);
	};

	@Override
	protected void initEvents(View view) {
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				EMConversation conversation = adapter.getItem(position);
				String username = conversation.getUserName();
				if (username.equals(MyApplication.getInstance().getUserPhone())) {
					toast("不能和自己聊天");
				} else {
					// 进入聊天页面
					Intent intent = new Intent(getActivity(),
							ChatActivity.class);
					EMContact emContact = null;
					groups = EMGroupManager.getInstance().getAllGroups();
					for (EMGroup group : groups) {
						if (group.getGroupId().equals(username)) {
							emContact = group;
							break;
						}
					}
					if (emContact != null && emContact instanceof EMGroup) {
						// it is group chat
						intent.putExtra("chatType", ChatActivity.CHATTYPE_GROUP);
						intent.putExtra("groupId",
								((EMGroup) emContact).getGroupId());
					} else {
						// it is single chat
						intent.putExtra("userId", username);
					}
					startActivity(intent);
				}
			}
		});
		// 注册上下文菜单
		registerForContextMenu(listView);
		listView.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// 隐藏软键盘
				if (getActivity().getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
					if (getActivity().getCurrentFocus() != null)
						inputMethodManager.hideSoftInputFromWindow(
								getActivity().getCurrentFocus()
										.getWindowToken(),
								InputMethodManager.HIDE_NOT_ALWAYS);
				}
				return false;
			}
		});

		// 搜索框
		query = (EditText) getActivity().findViewById(R.id.query);
		clearSearch = (ImageButton) searchbar.findViewById(R.id.search_clear);
		query.addTextChangedListener(new TextWatcher() {
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				adapter.getFilter().filter(s);
				if (s.length() > 0) {
					clearSearch.setVisibility(View.VISIBLE);
				} else {
					clearSearch.setVisibility(View.INVISIBLE);
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void afterTextChanged(Editable s) {
			}
		});
		clearSearch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				query.getText().clear();
			}
		});
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		getActivity().getMenuInflater().inflate(R.menu.delete_message, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.delete_message) {
			EMConversation tobeDeleteCons = adapter
					.getItem(((AdapterContextMenuInfo) item.getMenuInfo()).position);
			// 删除此会话
			EMChatManager.getInstance().deleteConversation(
					tobeDeleteCons.getUserName(), tobeDeleteCons.isGroup());
			InviteMessgeDao inviteMessgeDao = new InviteMessgeDao(getActivity());
			inviteMessgeDao.deleteMessage(tobeDeleteCons.getUserName());
			adapter.remove(tobeDeleteCons);
			adapter.notifyDataSetChanged();

			// 更新消息未读数
			((MainActivity) getActivity()).updateUnreadLabel();

			return true;
		}
		return super.onContextItemSelected(item);
	}

	/** 刷新页面 */
	public void refresh() {
		adapter = new ChatAllHistoryAdapter(getActivity(),
				R.layout.row_chat_history, loadConversationsWithRecentChat());
		listView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

	/** 获取所有会话 */
	private List<EMConversation> loadConversationsWithRecentChat() {
		// 获取所有会话，包括陌生人
		Hashtable<String, EMConversation> conversations = EMChatManager
				.getInstance().getAllConversations();
		List<EMConversation> conversationList = new ArrayList<EMConversation>();
		// 过滤掉messages seize为0的conversation
		for (EMConversation conversation : conversations.values()) {
			if (conversation.getAllMessages().size() != 0)
				conversationList.add(conversation);
		}
		// 排序
		sortConversationByLastChatTime(conversationList);
		return conversationList;
	}

	/**
	 * 根据最后一条消息的时间排序
	 * 
	 * @param usernames
	 */

	private void sortConversationByLastChatTime(
			List<EMConversation> conversationList) {
		Collections.sort(conversationList, new Comparator<EMConversation>() {

			@Override
			public int compare(final EMConversation con1,
					final EMConversation con2) {

				EMMessage con2LastMessage = con2.getLastMessage();
				EMMessage con1LastMessage = con1.getLastMessage();
				if (con2LastMessage.getMsgTime() == con1LastMessage
						.getMsgTime()) {
					return 0;
				} else if (con2LastMessage.getMsgTime() > con1LastMessage
						.getMsgTime()) {
					return 1;
				} else {
					return -1;
				}
			}

		});
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		this.hidden = hidden;
		if (!hidden) {
			refresh();
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		if (!hidden) {
			refresh();
		}
	}

}
