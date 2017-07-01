package com.example.myosr.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    // TODO: Declare member variables here:
    Button trueBTN , falseBTN;
    TextView textView , scoreTXT;
    ProgressBar bar;
    int index;
    int score;
    int mQuestion;

    // TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };
    // TODO: Declare constants here
    final int INCREASE_PROGRESSBAR =(int) Math.ceil(100 % mQuestionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null){
            score = savedInstanceState.getInt("score");
            index = savedInstanceState.getInt("index");
        }
        else {
            score=0;
            index=0;
        }

        trueBTN = (Button) findViewById(R.id.true_button);
        falseBTN = (Button) findViewById(R.id.false_button);
        textView = (TextView) findViewById(R.id.question_text_view);
        scoreTXT = (TextView) findViewById(R.id.score);
        bar = (ProgressBar) findViewById(R.id.progress_bar);

        mQuestion = mQuestionBank[index].getmId();
        textView.setText(mQuestion);
        scoreTXT.setText("Score"+ score + "/"+mQuestionBank.length);

        trueBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnwser(true);
                updateQ();
            }
        });

        falseBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnwser(false);
                updateQ();
            }
        });

    }
    private void updateQ(){
        index = (index+1)% mQuestionBank.length;

        // Create AlertDialog
        if (index == 0){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Game Over");
            alert.setCancelable(false);
            alert.setMessage("Your score : "+score+" points!");
            alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();
        }

        mQuestion = mQuestionBank[index].getmId();
        textView.setText(mQuestion);
        bar.incrementProgressBy(INCREASE_PROGRESSBAR);
        scoreTXT.setText("Score"+ score + "/"+mQuestionBank.length);
    }
    private void checkAnwser(boolean answer){
        boolean userAnswer = mQuestionBank[index].getmAnswer();
        if (userAnswer == answer){
            Toast.makeText(getApplicationContext(),
                    R.string.correct_toast,Toast.LENGTH_SHORT).show();
            score = score+1 ;
        }
        else {
            Toast.makeText(getApplicationContext(),
                    R.string.incorrect_toast,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("score",score);
        outState.putInt("index",index);
    }
}
