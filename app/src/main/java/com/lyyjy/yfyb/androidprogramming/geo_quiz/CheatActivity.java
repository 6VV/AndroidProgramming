package com.lyyjy.yfyb.androidprogramming.geo_quiz;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.lyyjy.yfyb.androidprogramming.R;

public class CheatActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String EXTRA_ANSWER_IS_TRUE = "com.lyyjy.yfyb.androidprogramming.geo_quiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN = "com.lyyjy.yfyb.androidprogramming.geo_quiz.answer_shown";

    private static final String KEY_IS_CHEAT="isCheat";
    private boolean mIsCheat=false;

    private Button mShowAnswerButton;

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

        mShowAnswerButton= (Button) findViewById(R.id.showAnswerButton);
        mShowAnswerButton.setOnClickListener(this);

        ((TextView)findViewById(R.id.apiLevelTextView)).setText("API level "+ Build.VERSION.SDK_INT);

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

                int cx=mShowAnswerButton.getWidth()/2;
                int cy=mShowAnswerButton.getHeight()/2;
                float radius=mShowAnswerButton.getWidth();
                Animator animator = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    animator = ViewAnimationUtils.createCircularReveal(mShowAnswerButton, cx, cy, radius, 0);
                    animator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            mShowAnswerButton.setVisibility(View.INVISIBLE);
                        }
                    });
                    animator.start();
                }else{
                    mShowAnswerButton.setVisibility(View.INVISIBLE);
                }

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
