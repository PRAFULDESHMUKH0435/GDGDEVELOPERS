package com.example.gdg;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {

    Button loginbutton;
    TextView forgot,signup;
    EditText foremail,forpassword;
    private FirebaseAuth lauth;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.login_activity );
        getSupportActionBar( ).hide( );


        lauth = FirebaseAuth.getInstance();
        foremail = findViewById(R.id.inputEmail);
        forpassword = findViewById(R.id.inputPassword);
        ProgressDialog progressDialog = new ProgressDialog( loginActivity.this);

        Button loginbutton = findViewById( R.id.btnlogin );
        loginbutton.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
               if (foremail.getText().toString().isEmpty() || forpassword.getText().toString().isEmpty()){
                   Toast.makeText( loginActivity.this, "Both Fields Required", Toast.LENGTH_LONG ).show( );
               }else if (!(foremail.getText().toString().contains("@gmail.com"))){
                   Toast.makeText( loginActivity.this, "EMAIL ADDRESS IS BADLY FORMATTED\n" +
                           "PLEASE USE PROPER EMAIL FORMAT", Toast.LENGTH_SHORT ).show( );
               }else{
                   progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                   progressDialog.setMessage("Please Wait While We Check Credentials...");
                   progressDialog.setCancelable(false);
                   progressDialog.show();
                   checkuserispresentornot(foremail.getText().toString().trim(),forpassword.getText().toString().trim(),progressDialog);
               }
            }
        } );



        forgot = findViewById( R.id.forgotPassword );
        forgot.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                startActivity( new Intent( loginActivity.this, ForgotPassword.class ) );
            }
        } );




        signup = findViewById( R.id.textViewSignUp );
        signup.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                startActivity( new Intent( loginActivity.this, RegisterActivity.class ) );
            }
        } );


    }


    private void checkuserispresentornot (String a,String b ,ProgressDialog pd) {
        lauth.signInWithEmailAndPassword(a,b).addOnCompleteListener( new OnCompleteListener<AuthResult>( ) {
            @Override
            public void onComplete (@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText( loginActivity.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT ).show( );
                    startActivity( new Intent(loginActivity.this,MainActivity.class) );
                    finish();
                }else {
//                    startActivity( new Intent(loginActivity.this,RegisterActivity.class) );
                    Toast.makeText( loginActivity.this, ""+task.getException().getMessage(), Toast.LENGTH_LONG ).show( );
//                    finish();
                }
                pd.dismiss();
            }
        } );
    }
}