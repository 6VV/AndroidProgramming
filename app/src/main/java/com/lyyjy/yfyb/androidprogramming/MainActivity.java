package com.lyyjy.yfyb.androidprogramming;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lyyjy.yfyb.androidprogramming.GeoQuiz.QuizActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.quizActivity_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.quizActivity_button:{
                startActivity(new Intent(MainActivity.this, QuizActivity.class));
            }break;
        }
    }
}
