package com.babulgamit.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class PaymentActivityNew extends AppCompatActivity implements View.OnClickListener {
    private CardView bkashCard,dbblCard;
    private EditText nameEt,srunameET,numberET;
    private Button paymentButoon;
    private ImageView arrowpayment;
    private Dialog dialog;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_new);


        bkashCard=findViewById(R.id.bkashCardview_id);
        dbblCard=findViewById(R.id.dbblCardview_id);
        nameEt=findViewById(R.id.paymentName_id);
        srunameET=findViewById(R.id.paymentSurName_id);
        numberET=findViewById(R.id.paymentnumber_id);
        paymentButoon=findViewById(R.id.paymentButton_id);
        arrowpayment=findViewById(R.id.backarrowPayment_id);

        paymentButoon.setOnClickListener(this);
        arrowpayment.setOnClickListener(this);
        bkashCard.setOnClickListener(this);
        dbblCard.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.bkashCardview_id:
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.bkash_layout);

                dialog.show();

                break;

            case R.id.dbblCardview_id:
                final Dialog dialogg = new Dialog(context);
                dialogg.setContentView(R.layout.dbbl_layout);

                dialogg.show();

                break;

            case R.id.backarrowPayment_id:
                startActivity(new Intent(getApplicationContext(),MainActivity.class));

            case R.id.paymentButton_id:
                Toast.makeText(getApplicationContext(), "Payment Process continue", Toast.LENGTH_SHORT).show();

        }

    }
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        finish();
        startActivity(intent);

    }

}
