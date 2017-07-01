package com.example.myosr.quizzler;

/**
 * Created by myosr on 6/11/2017.
 */

public class TrueFalse {
    private int mId ;
    private Boolean mAnswer;
    public TrueFalse( int id , Boolean answer) {
        mId = id;
        mAnswer = answer;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public Boolean getmAnswer() {
        return mAnswer;
    }

    public void setmAnswer(Boolean mAnswer) {
        this.mAnswer = mAnswer;
    }
}
