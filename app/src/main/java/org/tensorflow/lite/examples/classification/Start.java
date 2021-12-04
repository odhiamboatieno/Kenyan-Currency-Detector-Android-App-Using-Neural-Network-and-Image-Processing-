package org.tensorflow.lite.examples.classification;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class Start extends AppCompatActivity {
    //set time to 14 seconds
    private static int Home_Timeout = 14000;
    TextToSpeech textToSpeech;
    String prevStarted = "prevStarted";
    //Welcome speech
    static String speak = "Welcome to Kenyan Currency Detector , the Currency Detector Application for the visually impaired people. To detect the currency with high accuracy , place the note over a flat surface and scan it using the camera.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Show content declared in activity_start.xml
        setContentView(R.layout.activity_start);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //proceed to scanner activity at the end of splash screen
                Intent home_intent = new Intent(Start.this,ClassifierActivity.class);
                startActivity(home_intent);
                finish();
            }
        },Home_Timeout);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.ENGLISH);
                    textToSpeech.speak(speak,TextToSpeech.QUEUE_FLUSH,null);
                }
            }
        });

//        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
//        if (!sharedpreferences.getBoolean(prevStarted, false)) {
//            SharedPreferences.Editor editor = sharedpreferences.edit();
//            editor.putBoolean(prevStarted, Boolean.TRUE);
//            editor.apply();
//        } else {
//            finish();
//        }
        }

    @Override
    protected void onPause() {
        super.onPause();
        if(textToSpeech != null){
            textToSpeech.stop();
        }
    }
}


