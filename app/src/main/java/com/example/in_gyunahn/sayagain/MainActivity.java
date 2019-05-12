package com.example.in_gyunahn.sayagain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView studyBtn = (TextView) findViewById(R.id.studyBtn);
        TextView dicBtn = (TextView) findViewById(R.id.dicBtn);
        TextView recordBtn = (TextView) findViewById(R.id.recordBtn);

        studyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent studyIntent = new Intent(MainActivity.this, StudyActivity.class);
                MainActivity.this.startActivity(studyIntent);
            }
        });
        dicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dicIntent = new Intent(MainActivity.this, DictionaryActivity.class);
                MainActivity.this.startActivity(dicIntent);
            }
        });
        recordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent recordIntent = new Intent(MainActivity.this, RecordActivity.class);
                MainActivity.this.startActivity(recordIntent);
            }
        });
    }
}