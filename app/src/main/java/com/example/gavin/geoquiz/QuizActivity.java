package com.example.gavin.geoquiz;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    //    code 3-1
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";

    //    code 5-13
    private static final int REQUEST_CODE_CHEAT = 0;

    //    code 1-7
    private Button mTrueButton;
    private Button mfalseButton;

    //    code 2-6
    private Button mNextButton;
    private TextView mQuestionTextView;

    //    code 5-6
    private Button mCheatButton;

    //    code 5-16
    private boolean mIsCheater;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.Q1, true),
            new Question(R.string.Q2, false),
            new Question(R.string.Q3, true),
            new Question(R.string.Q4, false),
            new Question(R.string.Q5, true),
            new Question(R.string.Q6, false),
    };
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        code 3-2
        Log.d(TAG, "onCreate(Bundle) called");

        setContentView(R.layout.activity_quiz);

//        code 1-8
        mTrueButton = (Button) findViewById(R.id.true_button);
        mfalseButton = (Button) findViewById(R.id.false_button);
//        code 2-7
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        /* remove gy code 2-9
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question); */
//        code 1-9
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                code 2-11
                checkAnswer(true);
//                code 1-12, remove by code 2-11
//                Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
            }
        });
//        code 1-10
        mfalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                code 2-11
                checkAnswer(false);
//                code 1-12, remove by code 2-11
//                Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();

            }
        });
//        code 2-8
        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
//                code 5-17
                mIsCheater = false;
//                code 2-9
                updateQuestion();
                /* remove gy code 2-9
                int question = mQuestionBank[mCurrentIndex].getTextResId();
                mQuestionTextView.setText(question); */
            }
        });

//        code 5-6
        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                        code 5-7, remove by code 5-10
//                                Intent intent = new Intent(QuizActivity.this, CheatActivity.class);
//                        code 5-10
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent intent = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);

//                remove by code 5-13
//                startActivity(intent);
//                code 5-13
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
                ;
            }
        });
        updateQuestion();

    }

    //    code 5-16
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
            mIsCheater = CheatActivity.wasAnswerShown(data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //    code 2-9
    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    //    code 2-10
    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;

//        5-17
        if (mIsCheater) {
            messageResId = R.string.judgment_toast;
        } else {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
        ;
    }

}
