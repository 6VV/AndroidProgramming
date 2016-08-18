package com.lyyjy.yfyb.androidprogramming.geo_quiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lyyjy.yfyb.androidprogramming.R;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG="QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final String KEY_IS_CHEATERS="isCheaters";
    private static final int REQUEST_CODE_CHEAT=0;

    private TextView mQuestionTextView;

    private Question[] mQuestions=new Question[]{
            new Question(R.string.question_mideast,false),
            new Question(R.string.question_oceans,true)
    };

    private boolean mIsCheaters[] = new boolean[mQuestions.length];

    private int mCurrentIndex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(this);

        findViewById(R.id.next_button).setOnClickListener(this);
        findViewById(R.id.true_button).setOnClickListener(this);
        findViewById(R.id.false_button).setOnClickListener(this);
        findViewById(R.id.pre_button).setOnClickListener(this);
        findViewById(R.id.cheat_button).setOnClickListener(this);

        if (savedInstanceState!=null){
            mCurrentIndex=savedInstanceState.getInt(KEY_INDEX,0);
            mIsCheaters = savedInstanceState.getBooleanArray(KEY_IS_CHEATERS);
        }

        updateQuestionTextView();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX,mCurrentIndex);
        outState.putBooleanArray(KEY_IS_CHEATERS, mIsCheaters);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode!= Activity.RESULT_OK){
            return;
        }

        if (requestCode==REQUEST_CODE_CHEAT){
            if (data==null){
                return;
            }
            mIsCheaters[mCurrentIndex] = CheatActivity.wasAnswerShown(data);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.next_button:
            case R.id.question_text_view:{
                mCurrentIndex=(mCurrentIndex+1)%mQuestions.length;
                updateQuestionTextView();
            }break;
            case R.id.pre_button:{
                mCurrentIndex=mCurrentIndex==0?mQuestions.length-1:mCurrentIndex-1;
                updateQuestionTextView();
            }break;
            case R.id.true_button:{
                checkAnswer(true);
            }break;
            case R.id.false_button:{
                checkAnswer(false);
            }break;
            case R.id.cheat_button:{
                startActivityForResult(CheatActivity.newIntent(QuizActivity.this,mQuestions[mCurrentIndex].isAnswerTrue()),REQUEST_CODE_CHEAT);
            }break;
        }
    }

    private void updateQuestionTextView() {
//        Log.e(TAG, "Updating question text for question #" + mCurrentIndex, new Exception());
        mQuestionTextView.setText(mQuestions[mCurrentIndex].getTextResId());
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue=mQuestions[mCurrentIndex].isAnswerTrue();
        int messageResId=0;
        if (mIsCheaters[mCurrentIndex]){
            messageResId=R.string.cheat_is_wrong;
        }else{
            if (userPressedTrue==answerIsTrue){
                messageResId=R.string.correct_toast;
            }else{
                messageResId=R.string.wrong_toast;
            }
        }


        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

}
