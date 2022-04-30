package com.babulgamit.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PracticeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView arrowBackMain;
    private CardView somprotikcard,projuktecard,scienceCard,bcsCard,gonitCard,sadharonKno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        arrowBackMain=findViewById(R.id.backArrowPrac_id);
        somprotikcard=findViewById(R.id.cvSomprotik_id);
        projuktecard=findViewById(R.id.cvICT_id);
        scienceCard=findViewById(R.id.cvSciencequestion_id);
        bcsCard=findViewById(R.id.cvBCSquestion_id);
        gonitCard=findViewById(R.id.cvMath_id);
        sadharonKno=findViewById(R.id.cvgk_id);


        arrowBackMain.setOnClickListener(this);
        somprotikcard.setOnClickListener(this);
        projuktecard.setOnClickListener(this);
        scienceCard.setOnClickListener(this);
        bcsCard.setOnClickListener(this);
        gonitCard.setOnClickListener(this);
        sadharonKno.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.backArrowPrac_id:

                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                break;

            case R.id.cvSomprotik_id:

                startActivity(new Intent(getApplicationContext(),BasicKnowledge.class));
                break;

            case R.id.cvICT_id:

                startActivity(new Intent(getApplicationContext(),ICT_Quiz.class));
                break;

            case R.id.cvMath_id:

                startActivity(new Intent(getApplicationContext(),MathActivity.class));
                break;

            case R.id.cvBCSquestion_id:

                startActivity(new Intent(getApplicationContext(),BCS_ExamActivity.class));
                break;


            case R.id.cvSciencequestion_id:

                startActivity(new Intent(getApplicationContext(),ScienceActivity.class));
                break;

            case R.id.cvgk_id:

                startActivity(new Intent(getApplicationContext(),BasicKnowledge.class));
                break;

        }
    }
}