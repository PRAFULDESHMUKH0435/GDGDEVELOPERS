package com.example.gdg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {


    EditText enteremailfld;
    FirebaseAuth auth;
    Button sendmail;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_forgot_password );
        getSupportActionBar().hide();
        enteremailfld = findViewById(R.id.enteremail);
        sendmail = findViewById(R.id.resetbtnid);

        sendmail.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                if (enteremailfld.getText().toString().isEmpty()){
                    Toast.makeText( ForgotPassword.this, "Please Enter Email Address", Toast.LENGTH_SHORT ).show( );
                }else {
                    String email =enteremailfld.getText().toString().trim();
                    auth = FirebaseAuth.getInstance();
                    auth.sendPasswordResetEmail(email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText( ForgotPassword.this, "Password Reset Link Has Been\n" +
                                                "Send To "+email+"Kindly Check", Toast.LENGTH_LONG ).show( );
                                    }else {
                                        Toast.makeText( ForgotPassword.this, ""+task.getException().getMessage(), Toast.LENGTH_LONG ).show( );
                                    }
                                }
                            });
                }
            }
        } );
    }
}