package com.lyyjy.yfyb.androidprogramming;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lyyjy.yfyb.androidprogramming.criminal_intent.CrimeActivity;
import com.lyyjy.yfyb.androidprogramming.criminal_intent.CrimeListActivity;
import com.lyyjy.yfyb.androidprogramming.geo_quiz.QuizActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.quizActivityButton).setOnClickListener(this);
        findViewById(R.id.criminalIntentButton).setOnClickListener(this);

        Log.d(TAG,"onCreate");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.quizActivityButton:{
                startActivity(new Intent(MainActivity.this, QuizActivity.class));
            }break;
            case R.id.criminalIntentButton:{
                startActivity(new Intent(MainActivity.this, CrimeListActivity.class));
            }break;
        }
    }
}
