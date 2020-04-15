/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package activity.fragments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import cn.ieauto.autogroup.android.MyApplication;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.activity.fragments.chat.AddContactActivity;
import cn.ieauto.autogroup.android.activity.fragments.chat.ChatActivity;
import cn.ieauto.autogroup.android.activity.fragments.chat.GroupsActivity;
import cn.ieauto.autogroup.android.activity.fragments.chat.NewFriendsMsgActivity;
import cn.ieauto.autogroup.android.activity.fragments.cheyouhui.SearchNewfriendActivity;
import cn.ieauto.autogroup.android.adapter.ContactAdapter;
import cn.ieauto.autogroup.android.app.ComConfig;
import cn.ieauto.autogroup.android.app.MyFragment;
import cn.ieauto.autogroup.android.db.InviteMessgeDao;
import cn.ieauto.autogroup.android.db.UserDao;
import cn.ieauto.autogroup.android.entity.User;
import cn.ieauto.autogroup.android.widget.Sidebar;

import com.easemob.chat.EMContactManager;
import com.easemob.exceptions.EaseMobException;

/**
 * 联系人列表页
 * 
 */
public class CheyouhuiFragment extends MyFragment {
	private ContactAdapter adapter;
	private List<User> contactList;
	private ListView listView;
	private boolean hidden;
	private Sidebar sidebar;
	private InputMethodManager inputMethodManager;
	private List<String> blackList;
	//private ImageView addContactView;
	private View view;
	private ImageView img_back;
	private TextView tv_title;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_cheyouhui, container, false);
		initEvents(view);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// 设置键盘
		inputMethodManager = (InputMethodManager) getActivity()
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		
		tv_title = (TextView) view.findViewById(R.id.view_top_tv_title);
		tv_title.setText("车有会");
		img_back = (ImageView) view.findViewById(R.id.view_top_img_back);
		img_back.setVisibility(View.GONE);
		//addContactView = (ImageView) view.findViewById(R.id.iv_new_contact);
		listView = (ListView) view.findViewById(R.id.cyh_lv_list);
		sidebar = (Sidebar) getView().findViewById(R.id.sidebar);
		///addContactView.setVisibility(View.VISIBLE);
		sidebar.setListView(listView);
		// 黑名单列表
		blackList = EMContactManager.getInstance().getBlackListUsernames();
		contactList = new ArrayList<User>();
		// 获取设置contactlist
		getContactList();
		// 设置adapter
		adapter = new ContactAdapter(getActivity(), R.layout.row_contact,
				contactList, sidebar);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String username = adapter.getItem(position).getUsername();
				if (ComConfig.NEW_FRIENDS_USERNAME.equals(username)) {
					// 进入申请与通知页面
					User user = MyApplication.getInstance().getContactList()
							.get(ComConfig.NEW_FRIENDS_USERNAME);
					user.setUnreadMsgCount(0);
					startActivity(NewFriendsMsgActivity.class);
				} else if (ComConfig.GROUP_USERNAME.equals(username)) {
					// 进入群聊列表页面
					startActivity(GroupsActivity.class);
				} else if (ComConfig.FIND_NEW_FRIENDS.equals(username)) {
					// 进入寻找新朋友
					startActivity(SearchNewfriendActivity.class);
				} else {
					// demo中直接进入聊天页面，实际一般是进入用户详情页
					startActivity(new Intent(getActivity(), ChatActivity.class)
							.putExtra("userId", adapter.getItem(position)
									.getUsername()));
				}
			}
		});
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

		// 进入添加好友页
//		addContactView.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				startActivity(AddContactActivity.class);
//			}
//		});
//		registerForContextMenu(listView);

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		// 长按前两个不弹menu
		if (((AdapterContextMenuInfo) menuInfo).position > 2) {
			getActivity().getMenuInflater().inflate(
					R.menu.context_contact_list, menu);
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.delete_contact) {
			User tobeDeleteUser = adapter
					.getItem(((AdapterContextMenuInfo) item.getMenuInfo()).position);
			// 删除此联系人
			deleteContact(tobeDeleteUser);
			// 删除相关的邀请消息
			InviteMessgeDao dao = new InviteMessgeDao(getActivity());
			dao.deleteMessage(tobeDeleteUser.getUsername());
			return true;
		} else if (item.getItemId() == R.id.add_to_blacklist) {
			User user = adapter.getItem(((AdapterContextMenuInfo) item
					.getMenuInfo()).position);
			moveToBlacklist(user.getUsername());
			return true;
		}
		return super.onContextItemSelected(item);
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

	/**
	 * 删除联系人
	 * 
	 * @param toDeleteUser
	 */
	public void deleteContact(final User tobeDeleteUser) {
		final ProgressDialog pd = new ProgressDialog(getActivity());
		pd.setMessage("正在删除...");
		pd.setCanceledOnTouchOutside(false);
		pd.show();
		new Thread(new Runnable() {
			public void run() {
				try {
					EMContactManager.getInstance().deleteContact(
							tobeDeleteUser.getUsername());
					// 删除db和内存中此用户的数据
					UserDao dao = new UserDao(getActivity());
					dao.deleteContact(tobeDeleteUser.getUsername());
					MyApplication.getInstance().getContactList()
							.remove(tobeDeleteUser.getUsername());
					getActivity().runOnUiThread(new Runnable() {
						public void run() {
							pd.dismiss();
							adapter.remove(tobeDeleteUser);
							adapter.notifyDataSetChanged();

						}
					});
				} catch (final Exception e) {
					getActivity().runOnUiThread(new Runnable() {
						public void run() {
							pd.dismiss();
							toast("删除失败: " + e.getMessage());
						}
					});

				}

			}
		}).start();

	}

	/**
	 * 把user移入到黑名单
	 */
	private void moveToBlacklist(final String username) {
		final ProgressDialog pd = new ProgressDialog(getActivity());
		pd.setMessage("正在移入黑名单...");
		pd.setCanceledOnTouchOutside(false);
		pd.show();
		new Thread(new Runnable() {
			public void run() {
				try {
					// 加入到黑名单
					EMContactManager.getInstance().addUserToBlackList(username,
							false);
					getActivity().runOnUiThread(new Runnable() {
						public void run() {
							pd.dismiss();
							toast("移入黑名单成功");
							refresh();
						}
					});
				} catch (EaseMobException e) {
					e.printStackTrace();
					getActivity().runOnUiThread(new Runnable() {
						public void run() {
							pd.dismiss();
							toast("移入黑名单失败");
						}
					});
				}
			}
		}).start();

	}

	// 刷新ui
	public void refresh() {
		try {
			// 可能会在子线程中调到这方法
			getActivity().runOnUiThread(new Runnable() {
				public void run() {
					getContactList();
					adapter.notifyDataSetChanged();

				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取联系人列表，并过滤掉黑名单和排序
	 */
	private void getContactList() {
		contactList.clear();
		// 获取本地好友列表
		Map<String, User> users = MyApplication.getInstance().getContactList();
		Iterator<Entry<String, User>> iterator = users.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, User> entry = iterator.next();
			if (!entry.getKey().equals(ComConfig.NEW_FRIENDS_USERNAME)
					&& !entry.getKey().equals(ComConfig.GROUP_USERNAME)
					&& !entry.getKey().equals(ComConfig.FIND_NEW_FRIENDS)
					&& !blackList.contains(entry.getKey()))
				contactList.add(entry.getValue());
		}
		// 排序
		Collections.sort(contactList, new Comparator<User>() {
			@Override
			public int compare(User lhs, User rhs) {
				return lhs.getUsername().compareTo(rhs.getUsername());
			}
		});

		// 加入"群聊"
		contactList.add(0, users.get(ComConfig.GROUP_USERNAME));

		// 加入"群聊"
		contactList.add(0, users.get(ComConfig.FIND_NEW_FRIENDS));
		// 把"新朋友验证请求"添加到首位
		contactList.add(0, users.get(ComConfig.NEW_FRIENDS_USERNAME));
	}

	@Override
	protected void initEvents(View view) {
	}

	@Override
	protected void initViews(View view) {
		// TODO Auto-generated method stub
		
	}
}
