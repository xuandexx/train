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
package activity.fragments.chat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.ieauto.autogroup.android.MyApplication;
import cn.ieauto.autogroup.android.R;
import cn.ieauto.autogroup.android.app.MyActivity;
import cn.ieauto.autogroup.android.dialog.AlertDialog;

import com.easemob.chat.EMContactManager;

public class AddContactActivity extends MyActivity {
	private EditText editText;
	private LinearLayout searchedUserLayout;
	private TextView nameText;
	private Button searchBtn;
	private String toAddUsername;
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_contact);
		editText = (EditText) findViewById(R.id.ac_et_username);
		searchedUserLayout = (LinearLayout) findViewById(R.id.ac_ll_user);
		nameText = (TextView) findViewById(R.id.ac_tv_name);
		searchBtn = (Button) findViewById(R.id.ac_btn_search);
	}

	/**
	 * 查找contact
	 * 
	 * @param v
	 */
	public void searchContact(View v) {
		final String name = editText.getText().toString();
		String saveText = searchBtn.getText().toString();

		if (getString(R.string.button_search).equals(saveText)) {
			toAddUsername = name;
			if (TextUtils.isEmpty(name)) {
				startActivity(new Intent(this, AlertDialog.class).putExtra(
						"msg", "请输入用户名"));
				return;
			}

			// TODO 从服务器获取此contact,如果不存在提示不存在此用户

			// 服务器存在此用户，显示此用户和添加按钮
			searchedUserLayout.setVisibility(0);
			nameText.setText(toAddUsername);

		}
	}

	/**
	 * 添加contact
	 * 
	 * @param view
	 */
	public void addContact(View view) {
		if (nameText.getText().toString()
				.equals(MyApplication.getInstance().getHuanxinUser())) {
			startActivity(new Intent(this, AlertDialog.class).putExtra("msg",
					"不能添加自己"));
			return;
		}

		if (MyApplication.getInstance().getContactList()
				.containsKey(nameText.getText().toString())) {
			startActivity(new Intent(this, AlertDialog.class).putExtra("msg",
					"此用户已是你的好友"));
			return;
		}

		progressDialog = new ProgressDialog(this);
		progressDialog.setMessage("正在发送请求...");
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.show();

		new Thread(new Runnable() {
			public void run() {

				try {
					// demo写死了个reason，实际应该让用户手动填入
					EMContactManager.getInstance().addContact(toAddUsername,
							"加个好友呗");
					runOnUiThread(new Runnable() {
						public void run() {
							progressDialog.dismiss();
							toast("发送请求成功,等待对方验证");
						}
					});
				} catch (final Exception e) {
					runOnUiThread(new Runnable() {
						public void run() {
							progressDialog.dismiss();
							toast("请求添加好友失败:" + e.getMessage());
						}
					});
				}
			}
		}).start();
	}

	public void back(View v) {
		finish();
	}

	@Override
	protected void initViews() {

	}

	@Override
	protected void initEvents() {

	}
}
