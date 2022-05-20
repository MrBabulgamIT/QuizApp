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

public class MathActivity extends AppCompatActivity {
    private TextView math_optionaA, math_optionaB, math_optionaC, math_optionaD;
    private TextView math_cheekout1, math_cheekout2;
    private TextView math_questionNumber, math_question, math_score;
    int math_currentIndex;
    int math_msScore =0;
    int math_qn =1;
    Dialog dialog;
    private ProgressBar math_progressBar;
    private ImageView matharrow;
    int math_CurrentQuestion, math_CurrentOptionA, math_CurrentOptionB, math_CurrentOptionC, math_CurrentOptionD;


    AnswerQustion[] math_questionBank =new AnswerQustion[] {
            new AnswerQustion(R.string.math_question_1,R.string.math_question_1_A,R.string.math_question_1_B,R.string.math_question_1_C,R.string.math_question_1_D,R.string.math_answer_1),
            new AnswerQustion(R.string.math_question_2,R.string.math_question_2_A,R.string.math_question_2_B,R.string.math_question_2_C,R.string.math_question_2_D,R.string.math_answer_2),
            new AnswerQustion(R.string.math_question_3,R.string.math_question_3_A,R.string.math_question_3_B,R.string.math_question_3_C,R.string.math_question_3_D,R.string.math_answer_3),
            new AnswerQustion(R.string.math_question_4,R.string.math_question_4_A,R.string.math_question_4_B,R.string.math_question_4_C,R.string.math_question_4_D,R.string.math_answer_4),
            new AnswerQustion(R.string.math_question_5,R.string.math_question_5_A,R.string.math_question_5_B,R.string.math_question_5_C,R.string.math_question_5_D,R.string.math_answer_5),

    };

    final int math_Progress_Bar = (int) Math.ceil(100/ math_questionBank.length);

    private TextView timermath;
    private CountDownTimer countDownTimermath;
    private long timeLeftMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);

        math_optionaA =findViewById(R.id.math_optionA);
        math_optionaB =findViewById(R.id.math_optionB);
        math_optionaC =findViewById(R.id.math_optionC);
        math_optionaD =findViewById(R.id.math_optionD);
        matharrow =findViewById(R.id.math_arrow_id);
        timermath=findViewById(R.id.math_secound_id);


        math_question =findViewById(R.id.math_question_id);
        math_score =findViewById(R.id.math_score_id);
        math_questionNumber =findViewById(R.id.math_questionNumber_id);

        math_cheekout1 =findViewById(R.id.math_selectedOption_id);
        math_cheekout2 =findViewById(R.id.math_correctAnswer_id);
        math_progressBar =findViewById(R.id.math_progressBar_id);

        //set Question
        math_CurrentQuestion = math_questionBank[math_currentIndex].getQuestionId();
        math_question.setText(math_CurrentQuestion);

        //set Option A
        math_CurrentOptionA = math_questionBank[math_currentIndex].getOptionA();
        math_optionaA.setText(math_CurrentOptionA);

        //set Option B
        math_CurrentOptionB = math_questionBank[math_currentIndex].getOptionB();
        math_optionaB.setText(math_CurrentOptionB);
        mathTimerCounter();

        //set Option C
        math_CurrentOptionC = math_questionBank[math_currentIndex].getOptionC();
        math_optionaC.setText(math_CurrentOptionC);

        //set Option D
        math_CurrentOptionD = math_questionBank[math_currentIndex].getOptionD();
        math_optionaD.setText(math_CurrentOptionD);

        math_optionaA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                math_CheekAnswer(math_CurrentOptionA);
                math_updateQuestion();
            }
        });

        math_optionaB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                math_CheekAnswer(math_CurrentOptionB);
                math_updateQuestion();
            }
        });
        matharrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),PracticeActivity.class));
            }
        });
        math_optionaC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                math_CheekAnswer(math_CurrentOptionC);
                math_updateQuestion();
            }
        });

        math_optionaD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                math_CheekAnswer(math_CurrentOptionD);
                math_updateQuestion();
            }
        });


    }
    private void mathTimerCounter() {

        countDownTimermath = new CountDownTimer(61000,1000) {
            @Override
            public void onTick(long l) {

                timeLeftMillis =l;
                int minute =(int) (timeLeftMillis / 1000) / 60;
                int secount =(int) (timeLeftMillis / 1000) % 60;

                String timeGkFormattedmath = String.format(Locale.getDefault(),"%02d:%02d",minute,secount);
                timermath.setText(timeGkFormattedmath);


            }

            @Override
            public void onFinish() {
                timeLeftMillis =0;
                math_updateQuestion();

            }
        }.start();


    }
    private void math_updateQuestion() {

        math_currentIndex =(math_currentIndex +1)% math_questionBank.length;
        mathTimerCounter();

        if (math_currentIndex ==0)
        {

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Start Another Quiz ?");

            alert.setCancelable(false);
            alert.setMessage("Your Score : " + math_msScore + " / " + math_questionBank.length+ " Point");
            alert.setPositiveButton("Yes ", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(new Intent(getApplicationContext(),PracticeActivity.class));
                }
            });
            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    math_msScore =0;
                    math_qn =1;
                    math_progressBar.setProgress(0);
                    math_score.setText("Score " + math_msScore + "/" + math_questionBank.length);
                    math_questionNumber.setText(math_qn +"/" + math_questionBank.length+"  নম্বর প্রশ্ন");

                }
            });
            alert.show();
        }

        //set Question
        math_CurrentQuestion = math_questionBank[math_currentIndex].getQuestionId();
        math_question.setText(math_CurrentQuestion);

        //set Option A
        math_CurrentOptionA = math_questionBank[math_currentIndex].getOptionA();
        math_optionaA.setText(math_CurrentOptionA);

        //set Option B
        math_CurrentOptionB = math_questionBank[math_currentIndex].getOptionB();
        math_optionaB.setText(math_CurrentOptionB);

        //set Option C
        math_CurrentOptionC = math_questionBank[math_currentIndex].getOptionC();
        math_optionaC.setText(math_CurrentOptionC);

        //set Option D
        math_CurrentOptionD = math_questionBank[math_currentIndex].getOptionD();
        math_optionaD.setText(math_CurrentOptionD);

        math_qn = math_qn +1;
        if (math_qn <= math_questionBank.length)
        {
            math_questionNumber.setText(math_qn +"/" + math_questionBank.length+" নম্বর প্রশ্ন");
        }
        math_score.setText("Score " + math_msScore + "/" + math_questionBank.length);
        math_progressBar.incrementProgressBy(math_Progress_Bar);
    }

    private void math_CheekAnswer(int userSelection) {

        int math_correctanswer = math_questionBank[math_currentIndex].getAnswerId();
        countDownTimermath.cancel();


        math_cheekout1.setText(userSelection);

        math_cheekout2.setText(math_correctanswer);

        String m= math_cheekout1.getText().toString().trim();
        String n= math_cheekout2.getText().toString().trim();

        if (m.equals(n))
        {
            Toast.makeText(getApplicationContext(), "সঠিক উত্তর", Toast.LENGTH_SHORT).show();
            math_msScore = math_msScore +1;

        }else
        {
            math_msScore=math_msScore-1;
            Toast.makeText(getApplicationContext(), "!Ops  ভুল উত্তর", Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(getApplicationContext(),PracticeActivity.class);
        finish();
        startActivity(intent);

    }
}

