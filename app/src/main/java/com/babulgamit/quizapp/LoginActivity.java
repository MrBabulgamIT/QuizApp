package com.babulgamit.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText emaillogin,passwordlogin;
    private Button login;
    private TextView forgetTextView,gotoSignup;
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    String names,emails,passwords,repasswords,key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        emaillogin=findViewById(R.id.emaillogin_id);
        passwordlogin=findViewById(R.id.passwordlogin_id);
        login=findViewById(R.id.loginButton_id);
        forgetTextView=findViewById(R.id.forget_password_id);
        gotoSignup=findViewById(R.id.gotoRegistration_text_id);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Login");




        login.setOnClickListener(this);
        forgetTextView.setOnClickListener(this);
        gotoSignup.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {


            switch (view.getId())
            {
                case R.id.gotoRegistration_text_id:
                    startActivity(new Intent(getApplicationContext(),SignUp_Activity.class));


                case R.id.loginButton_id:

                    loginUserAccount();

                    break;

                case R.id.forget_password_id:

                    startActivity(new Intent(getApplicationContext(),ICT_Quiz.class));
                    break;



        }
    }

    private void loginUserAccount()
    {

        emails = emaillogin.getText().toString();
        passwords = passwordlogin.getText().toString();


        // validations for input email and password
        if (emails.isEmpty()) {
            emaillogin.setError("Please enter Email !!");
            emaillogin.requestFocus();
            return;
        }
        else
        if (emails.length() < 10) {
            emaillogin.setError("Enter a valid email Or Number ");
            emaillogin.requestFocus();
            return;

        }else
        if (passwords.isEmpty()) {
            passwordlogin.setError("Please enter password !!");
            passwordlogin.requestFocus();
            return;
        }
        else
        if (passwords.length() < 9) {
            passwordlogin.setError("Enter a valid password ");
            passwordlogin.requestFocus();
            return;

        }

         key= databaseReference.push().getKey();
        UserRegistration users=new UserRegistration( names,emails,passwords,repasswords);
        databaseReference.child(key).setValue(users);

        mAuth.signInWithEmailAndPassword(emails, passwords)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(
                                    @NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful()) {
                                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(intent);

                                    Toast.makeText(getApplicationContext(),
                                            "Login successful!!",
                                            Toast.LENGTH_LONG)
                                            .show();

                                }
                                else {
                                    emaillogin.setText("");
                                    passwordlogin.setText("");
                                    // sign-in failed
                                    Toast.makeText(getApplicationContext(),
                                            "Login failed!!",
                                            Toast.LENGTH_LONG)
                                            .show();
                                }
                            }
                        });

    }

}