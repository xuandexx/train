package com.xuandexx.android.train.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.xuandexx.android.train.R;
import com.xuandexx.android.train.model.Course;
import com.xuandexx.android.train.model.CourseCollection;

import java.util.List;

/**
 * 主页面中所有课程列表Adapter
 */
public class CourseListAdapter extends BaseAdapter {

    private List<CourseCollection> list;
    //布局加载器：将xml转为View对象RelativeLayout
    private LayoutInflater mInflater;
    private Context context;

    public CourseListAdapter(Context context, List<CourseCollection> list) {
        this.list = list;
        //初始化布局加载器
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    //配置显示的总item数量
    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    //按照位置获取数据对象
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    //根据位置获取item的id
    @Override
    public long getItemId(int position) {
        return position;
    }

    //每个item的显示效果
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //加载每个item的布局对象（将item_user_layout转为RelativeLayout对象）
        View layout = mInflater.inflate(R.layout.item_course_collection, null);
        //初始化布局中的元素
        TextView courseTitle = layout.findViewById(R.id.courseTitle);
        //获取数据显示内容(数据绑定，将数据显示到布局中)
        CourseCollection u = list.get(position);
        courseTitle.setText(u.getTitle());
        GridView grid = layout.findViewById(R.id.courseGridView);
        List<Course> courseList = u.getList();
        CourseAdapter courseAdapter = new CourseAdapter(context, courseList);
        grid.setAdapter(courseAdapter);
        return layout;
    }
}