package com.example.in_gyunahn.sayagain;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

import static android.speech.tts.TextToSpeech.ERROR;

public class TtsTest extends AppCompatActivity {
    private TextToSpeech tts;
    private EditText textInsert;
    private Button textInserting;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tts_test);

        Button textInserting = (Button) findViewById(R.id.textInserting);
        final TextView textShow = (TextView) findViewById(R.id.textShow);
        final EditText textInsert =(EditText) findViewById(R.id.textInsert);
        Button speak1 = (Button) findViewById(R.id.speak1);
        Button speak2 = (Button) findViewById(R.id.speak2);
        Button speak3 =(Button) findViewById(R.id.speak3);


        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status !=ERROR){
                    tts.setLanguage(Locale.ENGLISH);
                }
            }
        });

        textInserting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textShow.setText(textInsert.getText());
            }
        });

        textShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tts.setPitch(1.0f);
                tts.setSpeechRate(1.0f);
                tts.speak(textShow.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);
            }
        });
        speak1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tts.setPitch(1.0f);
                tts.setSpeechRate(0.7f);
                tts.speak(textShow.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);
            }
        });
        speak2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tts.setPitch(1.5f);
                tts.setSpeechRate(0.5f);
                tts.speak(textShow.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);
            }
        });
        speak3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tts.setPitch(2.0f);
                tts.setSpeechRate(2.0f);
                tts.speak(textShow.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);

            }
        });



    }
}
