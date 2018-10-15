package com.example.gavin.geoquiz;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.lang.annotation.Annotation;

public class CheatActivity extends AppCompatActivity {

    //    code 5-8
    private static final String EXTRA_ANSWER_IS_TRUE = "com.example.gavin.geoquiz.answer_is_true";

    //    code 5-14
    private static final String EXTRA_ANSWER_SHOWN = "com.example.gavin.geoquiz.answer_shown";


    //    code 5-11
    private boolean mAnswerIsTrue;
    //    code 5-12
    private TextView mAnswerTextView;
    private Button mShowAnswerButton;

    //    code 5-9
    public static Intent newIntent(QuizActivity packageContext, boolean answerIsTrue) {
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return intent;
    }

    //    code 5-15
    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
//        code 5-11
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
//        code 5-12
        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);
        mShowAnswerButton = (Button) findViewById(R.id.show_answer_button);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
//                code 5-14
                setAnswerShownResult(true);
                int cx = mShowAnswerButton.getWidth()/2;
                int cy = mShowAnswerButton.getHeight()/2;
                float radius = mShowAnswerButton.getWidth();
                Animator anim = ViewAnimationUtils.createCircularReveal(mShowAnswerButton, cx, cy, radius, 0);
                anim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation, boolean isReverse) {
                        mShowAnswerButton.setVisibility(View.INVISIBLE);
                    }
                });
                anim.start();
            }
        });
    }

    //    code5-14
    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }
}
