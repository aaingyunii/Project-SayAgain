package com.example.in_gyunahn.sayagain;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

public class MicTest extends AppCompatActivity {
    private TextView speechResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mic_test);
        speechResult = (TextView) findViewById(R.id.speechResult);
    }
    public void getSpeech(View view){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-ER");
        Toast.makeText(this,"start speeking",Toast.LENGTH_SHORT).show();
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        }else{
            Toast.makeText(this,"기기의 마이크는 작동하지 않습니다",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int request,int result,Intent data){
        switch (request){
            case 10:
                if (result == RESULT_OK && data !=null){
                    ArrayList<String> speechText = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    speechResult.setText(speechText.get(0));
                }
                break;
        }
    }
}
