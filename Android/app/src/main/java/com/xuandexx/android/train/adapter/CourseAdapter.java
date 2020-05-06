package com.xuandexx.android.train.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xuandexx.android.train.R;
import com.xuandexx.android.train.model.Course;

import java.util.List;

public class CourseAdapter<Course> extends KSAdapter<Course> {

    public CourseAdapter(Context context, List<Course> list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_course_detail, null);
            holder = new ViewHolder();
            holder.cover =  convertView.findViewById(R.id.text);
            holder.courseName =  convertView.findViewById(R.id.text);
            holder.lessionNum =  convertView.findViewById(R.id.text);
            holder.forks =  convertView.findViewById(R.id.text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Course course = list.get(position);
        if (holder != null) {
            holder.cover.setText(provinceBean.getName());
            holder.courseName.setBackgroundResource(provinceBean.getColor());
            holder.lessionNum.setBackgroundResource(provinceBean.getColor());
            holder.forks.setBackgroundResource(provinceBean.getColor());
        }
        return convertView;
    }

    class ViewHolder {
        //封面
        ImageView cover;
        //课程名称
        TextView courseName;
        //课程节数
        TextView lessionNum;
        //课程评论数
        TextView forks;
    }
}