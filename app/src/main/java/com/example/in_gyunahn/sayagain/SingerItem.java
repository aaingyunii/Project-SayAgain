package com.example.in_gyunahn.sayagain;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SingerItem extends AppCompatActivity{

    public TextView textView;
    public  TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    String name;
    String mobile;
//    int resId;


    public SingerItem(String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
//        this.resId = resId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
//    public int getResId() {
//        return resId;
//    }

//    public void setResId(int resId) {
//        this.resId = resId;
//    }

    @Override
    public String toString() {
        return "SingerItem{" +
                "name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}