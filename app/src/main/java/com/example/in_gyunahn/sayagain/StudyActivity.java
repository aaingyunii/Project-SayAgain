package com.example.in_gyunahn.sayagain;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class StudyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        Button testBtn = (Button) findViewById(R.id.testBtn);

        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent micIntent = new Intent(StudyActivity.this,MicTest.class);
                StudyActivity.this.startActivity(micIntent);
        }
        });

        Button testBtn2 = (Button) findViewById(R.id.testBtn2);

        testBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent micIntent2 = new Intent(StudyActivity.this,MicTest2.class);
                StudyActivity.this.startActivity(micIntent2);
            }
        });

        Button testBtn3 = (Button) findViewById(R.id.testBtn3);

        testBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ttsIntent = new Intent(StudyActivity.this,TtsTest.class);
                StudyActivity.this.startActivity(ttsIntent);
            }
        });
    }
}
