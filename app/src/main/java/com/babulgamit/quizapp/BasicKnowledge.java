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

public class BasicKnowledge extends AppCompatActivity {
    private TextView gk_optionaA, gk_optionaB, gk_optionaC, gk_optionaD;
    private TextView gk_cheekout1, gk_cheekout2;
    private TextView gk_questionNumber, gk_question,score;
    int gk_currentIndex;
    private ImageView gkarrow;
    int gk_msScore =0;
    int gk_qn =1;
    Dialog dialog;
    private ProgressBar gk_progressBar;
    int gk_CurrentQuestion, gk_CurrentOptionA, gk_CurrentOptionB, gk_CurrentOptionC, gk_CurrentOptionD;


    AnswerQustion[] gk_questionBank =new AnswerQustion[] {
            new AnswerQustion(R.string.gk_question_1,R.string.gk_question_1_A,R.string.gk_question_1_B,R.string.gk_question_1_C,R.string.gk_question_1_D,R.string.gk_answer_1),
            new AnswerQustion(R.string.gk_question_2,R.string.gk_question_2_A,R.string.gk_question_2_B,R.string.gk_question_2_C,R.string.gk_question_2_D,R.string.gk_answer_2),
            new AnswerQustion(R.string.gk_question_3,R.string.gk_question_3_A,R.string.gk_question_3_B,R.string.gk_question_3_C,R.string.gk_question_3_D,R.string.gk_answer_3),
            new AnswerQustion(R.string.gk_question_4,R.string.gk_question_4_A,R.string.gk_question_4_B,R.string.gk_question_4_C,R.string.gk_question_4_D,R.string.gk_answer_4),
            new AnswerQustion(R.string.gk_question_5,R.string.gk_question_5_A,R.string.gk_question_5_B,R.string.gk_question_5_C,R.string.gk_question_5_D,R.string.gk_answer_5),
     };

    final int gk_Progress_Bar = (int) Math.ceil(100/ gk_questionBank.length);




    private TextView timerGk;
    private CountDownTimer countDownTimergk;
    private long timeLeftMillis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_knowledge);

        gk_optionaA =findViewById(R.id.gk_optionA);
        gk_optionaB =findViewById(R.id.gk_optionB);
        gk_optionaC =findViewById(R.id.gk_optionC);
        gkarrow=findViewById(R.id.gk_arrow_id);
        gk_optionaD =findViewById(R.id.gk_optionD);

        gk_question =findViewById(R.id.gk_question_id);
        score=findViewById(R.id.gk_score_id);
        gk_questionNumber =findViewById(R.id.gk_questionNumber_id);

        gk_cheekout1 =findViewById(R.id.gk_selectedOption_id);
        gk_cheekout2 =findViewById(R.id.gk_correctAnswer_id);
        gk_progressBar =findViewById(R.id.gk_progressBar_id);
        timerGk=findViewById(R.id.gk_secound_id);

        //set Question
        gk_CurrentQuestion = gk_questionBank[gk_currentIndex].getQuestionId();
        gk_question.setText(gk_CurrentQuestion);

        //set Option A
        gk_CurrentOptionA = gk_questionBank[gk_currentIndex].getOptionA();
        gk_optionaA.setText(gk_CurrentOptionA);

        //set Option B
        gk_CurrentOptionB = gk_questionBank[gk_currentIndex].getOptionB();
        gk_optionaB.setText(gk_CurrentOptionB);

        //set Option C
        gk_CurrentOptionC = gk_questionBank[gk_currentIndex].getOptionC();
        gk_optionaC.setText(gk_CurrentOptionC);

        //set Option D
        gk_CurrentOptionD = gk_questionBank[gk_currentIndex].getOptionD();
        gk_optionaD.setText(gk_CurrentOptionD);


        gkTimerCounter();
        gk_optionaA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gk_CheekAnswer(gk_CurrentOptionA);
                gk_updateQuestion();

            }
        });

        gkarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),PracticeActivity.class));
            }
        });

        gk_optionaB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gk_CheekAnswer(gk_CurrentOptionB);
                gk_updateQuestion();

            }
        });

        gk_optionaC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gk_CheekAnswer(gk_CurrentOptionC);
                gk_updateQuestion();

            }
        });

        gk_optionaD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gk_CheekAnswer(gk_CurrentOptionD);
                gk_updateQuestion();

            }
        });


    }
    private void gkTimerCounter() {

        countDownTimergk = new CountDownTimer(21000,1000) {
            @Override
            public void onTick(long l) {

                timeLeftMillis =l;
                int minute =(int) (timeLeftMillis / 1000) / 60;
                int secount =(int) (timeLeftMillis / 1000) % 60;

                String timeGkFormatted = String.format(Locale.getDefault(),"%02d:%02d",minute,secount);
                timerGk.setText(timeGkFormatted);


            }

            @Override
            public void onFinish() {
                timeLeftMillis =0;
                gk_updateQuestion();

            }
        }.start();


    }

    private void gk_updateQuestion() {

        gk_currentIndex =(gk_currentIndex +1)% gk_questionBank.length;


        if (gk_currentIndex ==0)
        {

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Start Another Quiz ?");

            alert.setCancelable(false);
            alert.setMessage("Your Score : " + gk_msScore + " / " + gk_questionBank.length+ " Point");
            alert.setPositiveButton("Yes ", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(new Intent(getApplicationContext(),PracticeActivity.class));
                }
            });
            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    gk_msScore =0;
                    gk_qn =1;
                    gk_progressBar.setProgress(0);
                    score.setText("Score " + gk_msScore + "/" + gk_questionBank.length);
                    gk_questionNumber.setText(gk_qn +"/" + gk_questionBank.length+" Question");

                }
            });
            alert.show();
        }

        //set Question
        gk_CurrentQuestion = gk_questionBank[gk_currentIndex].getQuestionId();
        gk_question.setText(gk_CurrentQuestion);

        //set Option A
        gk_CurrentOptionA = gk_questionBank[gk_currentIndex].getOptionA();
        gk_optionaA.setText(gk_CurrentOptionA);

        //set Option B
        gk_CurrentOptionB = gk_questionBank[gk_currentIndex].getOptionB();
        gk_optionaB.setText(gk_CurrentOptionB);

        //set Option C
        gk_CurrentOptionC = gk_questionBank[gk_currentIndex].getOptionC();
        gk_optionaC.setText(gk_CurrentOptionC);

        //set Option D
        gk_CurrentOptionD = gk_questionBank[gk_currentIndex].getOptionD();
        gk_optionaD.setText(gk_CurrentOptionD);

        gk_qn = gk_qn +1;
        if (gk_qn <= gk_questionBank.length)
        {
            gk_questionNumber.setText(gk_qn +"/" + gk_questionBank.length+"  নম্বর প্রশ্ন");
            gkTimerCounter();

        }
        score.setText("Score " + gk_msScore + "/" + gk_questionBank.length);
        gk_progressBar.incrementProgressBy(gk_Progress_Bar);
    }



    private void gk_CheekAnswer(int userSelection) {

        int correctanswer = gk_questionBank[gk_currentIndex].getAnswerId();

        countDownTimergk.cancel();
        gk_cheekout1.setText(userSelection);

        gk_cheekout2.setText(correctanswer);

        String m= gk_cheekout1.getText().toString().trim();
        String n= gk_cheekout2.getText().toString().trim();

        if (m.equals(n))
        {
            Toast.makeText(getApplicationContext(), "সঠিক উত্তর", Toast.LENGTH_SHORT).show();
            gk_msScore = gk_msScore +1;

        }else
        {
            Toast.makeText(getApplicationContext(), "!Ops ভুল উত্তর", Toast.LENGTH_SHORT).show();

        }


    }


}

