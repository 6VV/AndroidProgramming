package com.lyyjy.yfyb.androidprogramming.GeoQuiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lyyjy.yfyb.androidprogramming.R;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mQuestionTextView;

    private Question[] mQuestions=new Question[]{
            new Question(R.string.question_mideast,false),
            new Question(R.string.question_oceans,true)
    };

    private int mCurrentIndex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(this);
        updateQuestionTextView();

        findViewById(R.id.next_button).setOnClickListener(this);
        findViewById(R.id.true_button).setOnClickListener(this);
        findViewById(R.id.false_button).setOnClickListener(this);
        findViewById(R.id.pre_button).setOnClickListener(this);

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
        }
    }

    private void updateQuestionTextView() {
        mQuestionTextView.setText(mQuestions[mCurrentIndex].getTextResId());
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue=mQuestions[mCurrentIndex].isAnswerTrue();
        int messageResId=0;
        if (userPressedTrue==answerIsTrue){
            messageResId=R.string.correct_toast;
        }else{
            messageResId=R.string.wrong_toast;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }
}
