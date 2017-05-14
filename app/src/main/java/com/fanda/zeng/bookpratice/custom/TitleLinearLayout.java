package com.fanda.zeng.bookpratice.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fanda.zeng.bookpratice.R;

/**
 * Created by zfd on 2017/1/16 0016.
 * 自定义
 */

public class TitleLinearLayout extends LinearLayout implements View.OnClickListener {

    private TextView btn_back;
    private TextView btn_edit;
    private TextView tv_title;

    public TitleLinearLayout(Context context, AttributeSet attrs) {
        //在xml文件中引用的时候会调用些构造函数
        super(context, attrs);
        View titleView = LayoutInflater.from(context).inflate(R.layout.include_custom_title, this);
        btn_back = (TextView) titleView.findViewById(R.id.btn_back);
        btn_edit = (TextView) titleView.findViewById(R.id.btn_edit);
        tv_title = (TextView) titleView.findViewById(R.id.tv_title);
        btn_back.setOnClickListener(this);
        btn_edit.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                Toast.makeText(getContext(), "back", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_edit:
                Toast.makeText(getContext(), "edit", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void setTitle(String title) {
        tv_title.setText(title);
    }

}
