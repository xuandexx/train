/**
 * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xuandexx.android.train.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.xuandexx.android.train.R;
import com.xuandexx.android.train.common.CommonUtils;

import org.xutils.view.annotation.ContentView;

/**
 * 内容提供
 */
public class ContentFragment extends BaseFragment {

    private TextView errorText;

    private ImageButton clearSearch;

    private EditText query;

    private TextView searchView;

    private ListView listView;

    private TextView cancleView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root;
        TAG = this.getClass().getSimpleName();
        if (CommonUtils.isNetWorkConnected(this.getActivity())) {
            root = inflater.inflate(R.layout.fragment_content, container, false);
        } else {
            root = inflater.inflate(R.layout.em_chat_neterror_item, container, false);
            errorText = (TextView) root.findViewById(R.id.tv_connect_errormsg);
            errorText.setText("网络连接中断");
        }
        return root;
    }


    @Override
    protected void initView() {
        TAG = this.getClass().getSimpleName();
        clearSearch = (ImageButton) findViewById(R.id.search_clear);
        query = (EditText) findViewById(R.id.query);
        searchView = (TextView) findViewById(R.id.tv_search);
        listView = (ListView) findViewById(R.id.listview);
        cancleView = (TextView) findViewById(R.id.tv_cancel);
    }

    @Override
    protected void initEvent() {
        query.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    clearSearch.setVisibility(View.VISIBLE);
                } else {
                    clearSearch.setVisibility(View.INVISIBLE);
                }
                searchView.setVisibility(View.VISIBLE);
                listView.setVisibility(View.INVISIBLE);
                searchView.setHint(String.format(getString(R.string.search_contanier), s));
            }
        });
        clearSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard();
                searchMessages();
            }
        });
        cancleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    private void searchMessages() {

    }
}