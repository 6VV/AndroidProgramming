package com.lyyjy.yfyb.androidprogramming.geo_quiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lyyjy.yfyb.androidprogramming.R;

public class CheatActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String EXTRA_ANSWER_IS_TRUE = "com.lyyjy.yfyb.androidprogramming.geo_quiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN = "com.lyyjy.yfyb.androidprogramming.geo_quiz.answer_shown";

    private static final String KEY_IS_CHEAT="isCheat";
    private boolean mIsCheat=false;

    public static Intent newIntent(Context packageContext,boolean answerIsTure){
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTure);
        return intent;
    }

    public static boolean wasAnswerShown(Intent result){
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    private boolean mAnswerIsTure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTure = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);

        findViewById(R.id.showAnswerButton).setOnClickListener(this);

        if (savedInstanceState!=null){
            setAnswerShownResult(savedInstanceState.getBoolean(KEY_IS_CHEAT));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_IS_CHEAT,mIsCheat);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.showAnswerButton:{
                if (mAnswerIsTure){
                    ((TextView)findViewById(R.id.answerTextView)).setText(R.string.true_button);
                }else {
                    ((TextView)findViewById(R.id.answerTextView)).setText(R.string.false_button);
                }
                setAnswerShownResult(true);
            }break;
        }
    }

    private void setAnswerShownResult(boolean isAnswerShown){
        mIsCheat=isAnswerShown;
        Intent intent=new Intent();
        intent.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK,intent);
    }
}
