package com.example.gavin.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
//    code 1-7
    private Button mTrueButton;
    private Button mfalseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

//        code 1-8
        mTrueButton = (Button) findViewById(R.id.true_button);
        mfalseButton = (Button) findViewById(R.id.false_button);

//        code 1-9
        mTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                code 1-12
                Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
            }
        });
//        code 1-10
        mfalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                code 1-12
                Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();

            }
        });

    }
}
