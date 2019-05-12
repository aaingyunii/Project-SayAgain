package com.example.in_gyunahn.sayagain;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import static android.speech.tts.TextToSpeech.ERROR;


public class MicTest2 extends AppCompatActivity {
    private TextView speechResult2;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mic_test2);

        speechResult2 = (TextView) findViewById(R.id.speechResult2);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status !=ERROR){
                    tts.setLanguage(Locale.ENGLISH);
                }
            }
        });

        speechResult2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tts.setPitch(1.0f);
                tts.setSpeechRate(1.0f);
                tts.speak(speechResult2.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);
            }
        });
    }
    public void getSpeech2(View view){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-ER");
        Toast.makeText(this,"start speak",Toast.LENGTH_SHORT).show();
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
                    speechResult2.setText(speechText.get(0));
                }
                break;
        }
    }
}
