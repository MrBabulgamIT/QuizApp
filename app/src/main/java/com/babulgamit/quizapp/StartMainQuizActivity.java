package com.babulgamit.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class StartMainQuizActivity extends AppCompatActivity {

    private CardView payment;
    private ImageView arrowstartQuiz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_main_quiz);

        payment=findViewById(R.id.paymentconfromCV_id);
        arrowstartQuiz=findViewById(R.id.backArrowstartQiz_id);

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),PaymentActivityNew.class));
            }
        });
        arrowstartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        finish();
        startActivity(intent);

    }
}