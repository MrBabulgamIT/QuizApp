package com.babulgamit.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class ICT_Quiz extends AppCompatActivity {
    private TextView optionaA,optionaB,optionaC,optionaD;
    private TextView cheekout1,cheekout2;
    private TextView questionNumber,question,score;
    int currentIndex;
    int msScore=0;
    private ImageView ictarrow;
    int qn=1;

    Dialog dialog;
    private ProgressBar progressBar;
    int CurrentQuestion,CurrentOptionA,CurrentOptionB,CurrentOptionC,CurrentOptionD;

    TextView timerICT;
    CountDownTimer countDownTimerICT;
    private long timeLeftMillis;


    AnswerQustion[] ict_questionBank =new AnswerQustion[] {
            new AnswerQustion(R.string.ict_question_1,R.string.ict_question_1_A,R.string.ict_question_1_B,R.string.ict_question_1_C,R.string.ict_question_1_D,R.string.ict_answer_1),
            new AnswerQustion(R.string.ict_question_2,R.string.ict_question_2_A,R.string.ict_question_2_B,R.string.ict_question_2_C,R.string.ict_question_2_D,R.string.ict_answer_2),
            new AnswerQustion(R.string.ict_question_3,R.string.ict_question_3_A,R.string.ict_question_3_B,R.string.ict_question_3_C,R.string.ict_question_3_D,R.string.ict_answer_3),
            new AnswerQustion(R.string.ict_question_4,R.string.ict_question_4_A,R.string.ict_question_4_B,R.string.ict_question_4_C,R.string.ict_question_4_D,R.string.ict_answer_4),
            new AnswerQustion(R.string.ict_question_5,R.string.ict_question_5_A,R.string.ict_question_5_B,R.string.ict_question_5_C,R.string.ict_question_5_D,R.string.ict_answer_5),
    };

    final int Progress_Bar= (int) Math.ceil(100/ ict_questionBank.length);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ict_quiz);

        optionaA=findViewById(R.id.optionA);
        optionaB=findViewById(R.id.optionB);
        optionaC=findViewById(R.id.optionC);
        optionaD=findViewById(R.id.optionD);
        ictarrow=findViewById(R.id.ict_arrow_id);
        timerICT =findViewById(R.id.ict_secound_id);

        question=findViewById(R.id.question_id);
        score=findViewById(R.id.score_id);
        questionNumber=findViewById(R.id.questionNumber_id);

        cheekout1=findViewById(R.id.selectedOption_id);
        cheekout2=findViewById(R.id.correctAnswer_id);
        progressBar=findViewById(R.id.progressBar_id);

        //set Question
        CurrentQuestion= ict_questionBank[currentIndex].getQuestionId();
        question.setText(CurrentQuestion);

        //set Option A
        CurrentOptionA= ict_questionBank[currentIndex].getOptionA();
        optionaA.setText(CurrentOptionA);

        //set Option B
        CurrentOptionB= ict_questionBank[currentIndex].getOptionB();
        optionaB.setText(CurrentOptionB);

        //set Option C
        CurrentOptionC= ict_questionBank[currentIndex].getOptionC();
        optionaC.setText(CurrentOptionC);

        //set Option D
        CurrentOptionD= ict_questionBank[currentIndex].getOptionD();
        optionaD.setText(CurrentOptionD);


      ictTimerCounter();

        optionaA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheekAnswer(CurrentOptionA);
                updateQuestion();

            }
        });

        optionaB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheekAnswer(CurrentOptionB);
                updateQuestion();

            }
        });

        optionaC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheekAnswer(CurrentOptionC);
                updateQuestion();

            }
        });

        optionaD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheekAnswer(CurrentOptionD);
                updateQuestion();

            }
        });
        ictarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),PracticeActivity.class));
            }
        });


    }

    private void updateQuestion() {

        currentIndex=(currentIndex+1)% ict_questionBank.length;

        if (currentIndex==0)
        {

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Start Another Quiz ?");


            alert.setCancelable(false);
            alert.setMessage("Your Score : "+ msScore + " / " + ict_questionBank.length+ " Point" );
            alert.setPositiveButton("Yes ", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(new Intent(getApplicationContext(),PracticeActivity.class));
                }
            });
            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    msScore=0;
                    qn=1;
                    progressBar.setProgress(0);
                    score.setText("Score " + msScore + " / " + ict_questionBank.length+ " Point" );
                    questionNumber.setText(qn +"/" + ict_questionBank.length+" Question");

                }
            });
            alert.show();
        }

        //set Question
        CurrentQuestion= ict_questionBank[currentIndex].getQuestionId();
        question.setText(CurrentQuestion);

        //set Option A
        CurrentOptionA= ict_questionBank[currentIndex].getOptionA();
        optionaA.setText(CurrentOptionA);

        //set Option B
        CurrentOptionB= ict_questionBank[currentIndex].getOptionB();
        optionaB.setText(CurrentOptionB);

        //set Option C
        CurrentOptionC= ict_questionBank[currentIndex].getOptionC();
        optionaC.setText(CurrentOptionC);

        //set Option D
        CurrentOptionD= ict_questionBank[currentIndex].getOptionD();
        optionaD.setText(CurrentOptionD);

        qn =qn+1;
        if (qn<= ict_questionBank.length)
        {
            ictTimerCounter();
            questionNumber.setText(qn +"/" + ict_questionBank.length+" নম্বর প্রশ্ন");
        }
        score.setText("Score " + msScore + "/" + ict_questionBank.length);
        progressBar.incrementProgressBy(Progress_Bar);
    }

    private void ictTimerCounter() {

        countDownTimerICT = new CountDownTimer(21000,1000) {
            @Override
            public void onTick(long l) {

                timeLeftMillis =l;
                int minute =(int) (timeLeftMillis / 1000) / 60;
                int secount =(int) (timeLeftMillis / 1000) % 60;

                String timeGkFormatted = String.format(Locale.getDefault(),"%02d:%02d",minute,secount);
                timerICT.setText(timeGkFormatted);


            }

            @Override
            public void onFinish() {
                timeLeftMillis =0;
                updateQuestion();

            }
        }.start();


    }

    private void CheekAnswer(int userSelection) {



        int correctanswer = ict_questionBank[currentIndex].getAnswerId();


        countDownTimerICT.cancel();
        cheekout1.setText(userSelection);

        cheekout2.setText(correctanswer);

        String m=cheekout1.getText().toString().trim();
        String n=cheekout2.getText().toString().trim();

        if (m.equals(n))
        {
            Toast.makeText(getApplicationContext(), "সঠিক উত্তর", Toast.LENGTH_SHORT).show();
            msScore=msScore+1;

        }else
        {
            Toast.makeText(getApplicationContext(), "!Ops ভুল উত্তর", Toast.LENGTH_SHORT).show();

        }

    }

}

