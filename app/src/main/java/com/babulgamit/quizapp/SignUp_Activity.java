package com.babulgamit.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp_Activity extends AppCompatActivity implements View.OnClickListener {

    private EditText signupEmailET,signupPasswordET,signupname,signuprepassword;
    private Button pracemailbuttonET;
    private ImageView arrowemailsignup;
    String names,emails,passwords,repasswords,key;
    private FirebaseAuth mAuth;
    private TextView gotologin;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signupEmailET=findViewById(R.id.emailsignup_id);
        signupPasswordET=findViewById(R.id.passwordsignup_id);
        signupname=findViewById(R.id.namedsignup_id);
        signuprepassword=findViewById(R.id.repasswordsignup_id);
        pracemailbuttonET=findViewById(R.id.signUpButton_id);
        gotologin=findViewById(R.id.gotologin_text_id);
        arrowemailsignup=findViewById(R.id.arrowsignupEmail_id);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        mAuth = FirebaseAuth.getInstance();


        pracemailbuttonET.setOnClickListener(this);
        arrowemailsignup.setOnClickListener(this);
        gotologin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.arrowsignupEmail_id:
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                break;

            case R.id.signUpButton_id:

                registerNewUser();

                break;

            case  R.id.gotologin_text_id:
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }
    }



        private void registerNewUser()
        {

            names = signupname.getText().toString().trim();
            emails = signupEmailET.getText().toString().trim();
            passwords = signupPasswordET.getText().toString().trim();
            repasswords = signuprepassword.getText().toString().trim();

            if (names.isEmpty()) {
                signupname.setError("Enter Your Name!!");
                signupname.requestFocus();
                return;
            }
           else
            // Validations for input details
            if (emails.isEmpty()) {
                signupEmailET.setError("Please enter email!!");
                signupEmailET.requestFocus();
                return;
            }
            else
            if (emails.length() < 10) {

                signupEmailET.setError("Please enter valid mail");
                signupEmailET.requestFocus();
                return;

            }
            else
            if (passwords.isEmpty()) {
                signupPasswordET.setError("Please enter password!!");
                signupPasswordET.requestFocus();
                return;
            }
            else
            if (passwords.length() < 8) {
                signupPasswordET.setError("Minimum length of a password should be 9");
                signupPasswordET.requestFocus();
                return;

            }else

            if (repasswords.isEmpty()) {
                signuprepassword.setError("Please enter Same Password!!");
                signuprepassword.requestFocus();
                return;
            }
            else
            if (!repasswords.equals(passwords)) {
                signuprepassword.setError("Please enter Same Password!!");
                signuprepassword.requestFocus();
                return;

            }else

                // create new user or register new user
                mAuth.createUserWithEmailAndPassword(emails, passwords)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful()) {


                                    key= databaseReference.push().getKey();
                                    UserRegistration users=new UserRegistration( names,emails,passwords,repasswords);
                                    databaseReference.child(key).setValue(users);

                                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(intent);

                                    Toast.makeText(getApplicationContext(),
                                            "Registration successful!",
                                            Toast.LENGTH_LONG)
                                            .show();
                                    signupname.setText("");
                                    signuprepassword.setText("");
                                    signupEmailET.setText("");
                                    signupPasswordET.setText("");

                                    // if the user created intent to login activity

                                }
                                else {
                                    signupname.setText("");
                                    signuprepassword.setText("");
                                    signupEmailET.setText("");
                                    signupPasswordET.setText("");
                                    // Registration failed
                                    Toast.makeText(
                                            getApplicationContext(),
                                            "Registration failed!!"
                                                    + " Please try again later",
                                            Toast.LENGTH_LONG)
                                            .show();

                                }
                            }
                        });
        }
    }
