package com.example.in_gyunahn.sayagain;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SingerItemView extends LinearLayout {
    TextView textView;
    TextView textView2;
//    ImageView imageView;

    public SingerItemView(Context context) {
        super(context);
        init(context);
    }

    public SingerItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        //inflate
        // system service에서 LayoutInflater를 가져다 사용하겠다.
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // singer_item xml을 inflate함
        inflater.inflate(R.layout.activity_singer_item,this, true);

        textView = (TextView) findViewById(R.id.textShow);
        textView2 = (TextView) findViewById(R.id.textView2);
//        imageView = (ImageView) findViewById(R.id.imageView);

    }

    public void setName(String name){
        textView.setText(name);
    }

    public void setMobile(String mobile){
        textView2.setText(mobile);
    }

//    public void setImage(int resId){
//        imageView.setImageResource(resId);
//    }

}