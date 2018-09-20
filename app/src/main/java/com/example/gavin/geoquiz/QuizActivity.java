package com.example.gavin.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    //    code 1-7
    private Button mTrueButton;
    private Button mfalseButton;

    //    code 2-6
    private Button mNextButton;
    private TextView mQuestionTextView;

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
//                code 2-9
                updateQuestion();
                /* remove gy code 2-9
                int question = mQuestionBank[mCurrentIndex].getTextResId();
                mQuestionTextView.setText(question); */
            }
        });

        updateQuestion();

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

        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
        ;
    }

}
