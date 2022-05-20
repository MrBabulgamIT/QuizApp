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

public class ScienceActivity extends AppCompatActivity {

    private TextView science_optionaA, science_optionaB, science_optionaC, science_optionaD;
    private TextView science_cheekout1, science_cheekout2;
    private TextView science_questionNumber, science_question, science_score;
    int science_currentIndex;
    int science_msScore =0;
    int science_qn =1;
    private ImageView sciencearrow;
    Dialog dialog;
    private ProgressBar science_progressBar;
    int science_CurrentQuestion, science_CurrentOptionA, science_CurrentOptionB, science_CurrentOptionC, science_CurrentOptionD;


    AnswerQustion[] science_questionBank =new AnswerQustion[] {
            new AnswerQustion(R.string.science_question_1,R.string.science_question_1_A,R.string.science_question_1_B,R.string.science_question_1_C,R.string.science_question_1_D,R.string.science_answer_1),
            new AnswerQustion(R.string.science_question_2,R.string.science_question_2_A,R.string.science_question_2_B,R.string.science_question_2_C,R.string.science_question_2_D,R.string.science_answer_2),
            new AnswerQustion(R.string.science_question_3,R.string.science_question_3_A,R.string.science_question_3_B,R.string.science_question_3_C,R.string.science_question_3_D,R.string.science_answer_3),
            new AnswerQustion(R.string.science_question_4,R.string.science_question_4_A,R.string.science_question_4_B,R.string.science_question_4_C,R.string.science_question_4_D,R.string.science_answer_4),
            new AnswerQustion(R.string.science_question_5,R.string.science_question_5_A,R.string.science_question_5_B,R.string.science_question_5_C,R.string.science_question_5_D,R.string.science_answer_5),



            new AnswerQustion(R.string.ict_question_2,R.string.ict_question_2_A,R.string.ict_question_2_B,R.string.ict_question_2_C,R.string.ict_question_2_D,R.string.ict_answer_2),
            new AnswerQustion(R.string.ict_question_3,R.string.ict_question_3_A,R.string.ict_question_3_B,R.string.ict_question_3_C,R.string.ict_question_3_D,R.string.ict_answer_3),
            new AnswerQustion(R.string.ict_question_4,R.string.ict_question_4_A,R.string.ict_question_4_B,R.string.ict_question_4_C,R.string.ict_question_4_D,R.string.ict_answer_4),
            new AnswerQustion(R.string.ict_question_5,R.string.ict_question_5_A,R.string.ict_question_5_B,R.string.ict_question_5_C,R.string.ict_question_5_D,R.string.ict_answer_5),
    };

    final int science_Progress_Bar = (int) Math.ceil(100/ science_questionBank.length);

    private TextView timerScience;
    private CountDownTimer countDownTimerscience;
    private long timeLeftMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_science);

        science_optionaA =findViewById(R.id.science_optionA);
        science_optionaB =findViewById(R.id.science_optionB);
        science_optionaC =findViewById(R.id.science_optionC);
        science_optionaD =findViewById(R.id.science_optionD);
        sciencearrow=findViewById(R.id.science_arrow_id);
        timerScience=findViewById(R.id.science_secound_id);
        science_question =findViewById(R.id.science_question_id);
        science_score =findViewById(R.id.science_score_id);
        science_questionNumber =findViewById(R.id.science_questionNumber_id);

        science_cheekout1 =findViewById(R.id.science_selectedOption_id);
        science_cheekout2 =findViewById(R.id.science_correctAnswer_id);
        science_progressBar =findViewById(R.id.science_progressBar_id);

        //set Question
        science_CurrentQuestion = science_questionBank[science_currentIndex].getQuestionId();
        science_question.setText(science_CurrentQuestion);

        //set Option A
        science_CurrentOptionA = science_questionBank[science_currentIndex].getOptionA();
        science_optionaA.setText(science_CurrentOptionA);

        //set Option B
        science_CurrentOptionB = science_questionBank[science_currentIndex].getOptionB();
        science_optionaB.setText(science_CurrentOptionB);

        //set Option C
        science_CurrentOptionC = science_questionBank[science_currentIndex].getOptionC();
        science_optionaC.setText(science_CurrentOptionC);

        //set Option D
        science_CurrentOptionD = science_questionBank[science_currentIndex].getOptionD();
        science_optionaD.setText(science_CurrentOptionD);

        scienceTimerCounter();

        science_optionaA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheekAnswer(science_CurrentOptionA);
                updateQuestionScience();
            }
        });

        science_optionaB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheekAnswer(science_CurrentOptionB);
                updateQuestionScience();
            }
        });

        science_optionaC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheekAnswer(science_CurrentOptionC);
                updateQuestionScience();
            }
        });

        science_optionaD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheekAnswer(science_CurrentOptionD);
                updateQuestionScience();
            }
        });
        sciencearrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),PracticeActivity.class));
            }
        });


    }

    private void scienceTimerCounter() {

        countDownTimerscience = new CountDownTimer(21000,1000) {
            @Override
            public void onTick(long l) {

                timeLeftMillis =l;
                int minute =(int) (timeLeftMillis / 1000) / 60;
                int secount =(int) (timeLeftMillis / 1000) % 60;

                String timeGkFormatted = String.format(Locale.getDefault(),"%02d:%02d",minute,secount);
                timerScience.setText(timeGkFormatted);


            }

            @Override
            public void onFinish() {
                timeLeftMillis =0;
                updateQuestionScience();

            }
        }.start();


    }

    private void updateQuestionScience() {

        science_currentIndex =(science_currentIndex +1)% science_questionBank.length;

        if (science_currentIndex ==0)
        {

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Start Another Quiz ?");

            alert.setCancelable(false);
            alert.setMessage("Your Score : " + science_msScore + " / " + science_questionBank.length+ " Point");
            alert.setPositiveButton("Yes ?", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(new Intent(getApplicationContext(),PracticeActivity.class));
                }
            });
            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    science_msScore =0;
                    science_qn =1;
                    science_progressBar.setProgress(0);
                    science_score.setText("Score " + science_msScore + "/" + science_questionBank.length);
                    science_questionNumber.setText(science_qn +"/" + science_questionBank.length+" Question");

                }
            });
            alert.show();
        }

        //set Question
        science_CurrentQuestion = science_questionBank[science_currentIndex].getQuestionId();
        science_question.setText(science_CurrentQuestion);

        //set Option A
        science_CurrentOptionA = science_questionBank[science_currentIndex].getOptionA();
        science_optionaA.setText(science_CurrentOptionA);

        //set Option B
        science_CurrentOptionB = science_questionBank[science_currentIndex].getOptionB();
        science_optionaB.setText(science_CurrentOptionB);

        //set Option C
        science_CurrentOptionC = science_questionBank[science_currentIndex].getOptionC();
        science_optionaC.setText(science_CurrentOptionC);

        //set Option D
        science_CurrentOptionD = science_questionBank[science_currentIndex].getOptionD();
        science_optionaD.setText(science_CurrentOptionD);

        science_qn = science_qn +1;
        if (science_qn <= science_questionBank.length)
        {
            scienceTimerCounter();
            science_questionNumber.setText(science_qn +"/" + science_questionBank.length+" নম্বর প্রশ্ন");
        }
        science_score.setText("Score " + science_msScore + "/" + science_questionBank.length);
        science_progressBar.incrementProgressBy(science_Progress_Bar);
    }

    private void CheekAnswer(int userSelection) {

        int correctanswer = science_questionBank[science_currentIndex].getAnswerId();

        countDownTimerscience.cancel();

        science_cheekout1.setText(userSelection);

        science_cheekout2.setText(correctanswer);

        String m= science_cheekout1.getText().toString().trim();
        String n= science_cheekout2.getText().toString().trim();

        if (m.equals(n))
        {
            Toast.makeText(getApplicationContext(), "সঠিক উত্তর", Toast.LENGTH_SHORT).show();
            science_msScore = science_msScore +1;

        }else
        {
            science_msScore=science_msScore-1;
            Toast.makeText(getApplicationContext(), "!Ops ভুল উত্তর", Toast.LENGTH_SHORT).show();

        }




    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(getApplicationContext(),PracticeActivity.class);
        finish();
        startActivity(intent);

    }
}

