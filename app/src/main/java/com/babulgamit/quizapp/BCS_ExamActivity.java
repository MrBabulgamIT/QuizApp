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

public class BCS_ExamActivity extends AppCompatActivity {
    private TextView bcs_optionaA, bcs_optionaB, bcs_optionaC, bcs_optionaD;
    private TextView bcs_cheekout1, bcs_cheekout2;
    private TextView bcs_questionNumber, bcs_question, bcs_score;
    int bcs_currentIndex;
    int bcs_msScore =0;
    private ImageView bcsarrow;
    int bcs_qn =1;
    private ProgressBar bcs_progressBar;
    int bcs_CurrentQuestion, bcs_CurrentOptionA, bcs_CurrentOptionB, bcs_CurrentOptionC, bcs_CurrentOptionD;


    AnswerQustion[] bcs_questionBank =new AnswerQustion[] {
            new AnswerQustion(R.string.ict_question_1,R.string.ict_question_1_A,R.string.ict_question_1_B,R.string.ict_question_1_C,R.string.ict_question_1_D,R.string.ict_answer_1),
            new AnswerQustion(R.string.gk_question_2,R.string.gk_question_2_A,R.string.gk_question_2_B,R.string.gk_question_2_C,R.string.gk_question_2_D,R.string.gk_answer_2),
            new AnswerQustion(R.string.math_question_5,R.string.math_question_5_A,R.string.math_question_5_B,R.string.math_question_5_C,R.string.math_question_5_D,R.string.math_answer_5),
            new AnswerQustion(R.string.ict_question_4,R.string.ict_question_4_A,R.string.ict_question_4_B,R.string.ict_question_4_C,R.string.ict_question_4_D,R.string.ict_answer_4),
            new AnswerQustion(R.string.gk_question_5,R.string.gk_question_5_A,R.string.gk_question_5_B,R.string.gk_question_5_C,R.string.gk_question_5_D,R.string.gk_answer_5),
    };

    final int bcs_Progress_Bar = (int) Math.ceil(100/ bcs_questionBank.length);

    private TextView timerBCS;
    private CountDownTimer countDownTimerBCS;
    private long timeLeftMillis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bcs_exam);

        bcs_optionaA =findViewById(R.id.bcs_optionA);
        bcs_optionaB =findViewById(R.id.bcs_optionB);
        bcs_optionaC =findViewById(R.id.bcs_optionC);
        bcs_optionaD =findViewById(R.id.bcs_optionD);
        bcsarrow =findViewById(R.id.bcs_arrow_id);
        timerBCS=findViewById(R.id.bcs_secound_id);

        bcs_question =findViewById(R.id.bcs_question_id);
        bcs_score =findViewById(R.id.bcs_score_id);
        bcs_questionNumber =findViewById(R.id.bcs_questionNumber_id);

        bcs_cheekout1 =findViewById(R.id.bcs_selectedOption_id);
        bcs_cheekout2 =findViewById(R.id.bcs_correctAnswer_id);
        bcs_progressBar =findViewById(R.id.bcs_progressBar_id);

        //set Question
        bcs_CurrentQuestion = bcs_questionBank[bcs_currentIndex].getQuestionId();
        bcs_question.setText(bcs_CurrentQuestion);
        bcsTimerCounter();
        //set Option A
        bcs_CurrentOptionA = bcs_questionBank[bcs_currentIndex].getOptionA();
        bcs_optionaA.setText(bcs_CurrentOptionA);

        //set Option B
        bcs_CurrentOptionB = bcs_questionBank[bcs_currentIndex].getOptionB();
        bcs_optionaB.setText(bcs_CurrentOptionB);

        //set Option C
        bcs_CurrentOptionC = bcs_questionBank[bcs_currentIndex].getOptionC();
        bcs_optionaC.setText(bcs_CurrentOptionC);

        //set Option D
        bcs_CurrentOptionD = bcs_questionBank[bcs_currentIndex].getOptionD();
        bcs_optionaD.setText(bcs_CurrentOptionD);

        bcs_optionaA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bcs_CheekAnswer(bcs_CurrentOptionA);
                bcs_updateQuestion();
            }
        });

        bcs_optionaB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bcs_CheekAnswer(bcs_CurrentOptionB);
                bcs_updateQuestion();
            }
        });
        bcsarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),PracticeActivity.class));
            }
        });

        bcs_optionaC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bcs_CheekAnswer(bcs_CurrentOptionC);
                bcs_updateQuestion();
            }
        });

        bcs_optionaD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bcs_CheekAnswer(bcs_CurrentOptionD);
                bcs_updateQuestion();
            }
        });


    }
    private void bcsTimerCounter() {

        countDownTimerBCS = new CountDownTimer(21000,1000) {
            @Override
            public void onTick(long l) {

                timeLeftMillis =l;
                int minute =(int) (timeLeftMillis / 1000) / 60;
                int secount =(int) (timeLeftMillis / 1000) % 60;

                String timeGkFormatted = String.format(Locale.getDefault(),"%02d:%02d",minute,secount);
                timerBCS.setText(timeGkFormatted);


            }

            @Override
            public void onFinish() {
                timeLeftMillis =0;
                bcs_updateQuestion();

            }
        }.start();


    }
    private void bcs_updateQuestion() {

        bcs_currentIndex =(bcs_currentIndex +1)% bcs_questionBank.length;


        if (bcs_currentIndex ==0)
        {

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Start Another Quiz ?");

            alert.setCancelable(false);
            alert.setMessage("Your Score : "+ bcs_msScore + " / " + bcs_questionBank.length+ " Point");
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(new Intent(getApplicationContext(),PracticeActivity.class));
                }
            });
            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    bcs_msScore =0;
                    bcs_qn =1;
                    bcs_progressBar.setProgress(0);
                    bcs_score.setText("Score " + bcs_msScore + "/" + bcs_questionBank.length);
                    bcs_questionNumber.setText(bcs_qn +"/" + bcs_questionBank.length+" ");

                }
            });
            alert.show();
        }

        //set Question
        bcs_CurrentQuestion = bcs_questionBank[bcs_currentIndex].getQuestionId();
        bcs_question.setText(bcs_CurrentQuestion);

        //set Option A
        bcs_CurrentOptionA = bcs_questionBank[bcs_currentIndex].getOptionA();
        bcs_optionaA.setText(bcs_CurrentOptionA);

        //set Option B
        bcs_CurrentOptionB = bcs_questionBank[bcs_currentIndex].getOptionB();
        bcs_optionaB.setText(bcs_CurrentOptionB);

        //set Option C
        bcs_CurrentOptionC = bcs_questionBank[bcs_currentIndex].getOptionC();
        bcs_optionaC.setText(bcs_CurrentOptionC);

        //set Option D
        bcs_CurrentOptionD = bcs_questionBank[bcs_currentIndex].getOptionD();
        bcs_optionaD.setText(bcs_CurrentOptionD);

        bcs_qn = bcs_qn +1;
        if (bcs_qn <= bcs_questionBank.length)
        {
            bcsTimerCounter();
            bcs_questionNumber.setText(bcs_qn +"/" + bcs_questionBank.length+" নম্বর প্রশ্ন");
        }
        bcs_score.setText("Score " + bcs_msScore + "/" + bcs_questionBank.length);
        bcs_progressBar.incrementProgressBy(bcs_Progress_Bar);
    }

    private void bcs_CheekAnswer(int userSelection) {

        int correctanswer = bcs_questionBank[bcs_currentIndex].getAnswerId();
        countDownTimerBCS.cancel();



        bcs_cheekout1.setText(userSelection);

        bcs_cheekout2.setText(correctanswer);

        String m= bcs_cheekout1.getText().toString().trim();
        String n= bcs_cheekout2.getText().toString().trim();

        if (m.equals(n))
        {
            Toast.makeText(getApplicationContext(), "সঠিক উত্তর", Toast.LENGTH_SHORT).show();
            bcs_msScore = bcs_msScore +1;

        }else
        {
            Toast.makeText(getApplicationContext(), "!Ops  ভুল উত্তর", Toast.LENGTH_SHORT).show();

        }


    }




}

