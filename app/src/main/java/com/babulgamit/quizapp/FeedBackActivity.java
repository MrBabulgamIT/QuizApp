package com.babulgamit.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class FeedBackActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText nameEditText,messegeEditText;
    private Button sentButton,clearButton;
    private ImageView backarrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        nameEditText=findViewById(R.id.nameedittxet_id);
        messegeEditText=findViewById(R.id.messageedittxet_id);
        sentButton=findViewById(R.id.sent_id);
        clearButton=findViewById(R.id.clear_id);
        backarrow=findViewById(R.id.backArrowFeedback_id);


        sentButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

    }

    @Override
    public void onClick(View v) {

        try {

            String name=nameEditText.getText().toString();
            String message =messegeEditText.getText().toString();


            if (v.getId()==R.id.sent_id){

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("Text/email");

                intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"sajony9191@gmail.com","jony94755094@gmail.com"});

                intent.putExtra(Intent.EXTRA_SUBJECT,"Feedback From apps");
                intent.putExtra(Intent.EXTRA_TEXT,"Name :"+ name +"Message :"+ message);
                startActivity(Intent.createChooser(intent,"Feedback with"));




            }
            if (v.getId()==R.id.clear_id){

                nameEditText.setText("");
                messegeEditText.setText("");

            }


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Exception :" +e, Toast.LENGTH_SHORT).show();

        }

    }
}