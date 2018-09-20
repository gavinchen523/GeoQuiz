
//Lesson 2 add new class
package com.example.gavin.geoquiz;


public class Question {
    /* code 2-1
       File -> setting -> Editor -> Code Style -> Java -> Name prefix , Field:m, Static field:s
     */
    private int mTextResId;
    private boolean mAnswerTrue;

    public Question(int textResId, boolean answerTrue) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }
    // Code 2-2
    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
