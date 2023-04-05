package com.example.gdg;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.gdg.Models.Registermodel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mauth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    EditText regname,regemail,regphone,regpassword,regconfrmpassword;
    TextView already;
    Button register;



    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.registration );
        getSupportActionBar( ).hide( );
        ProgressDialog progressDialog = new ProgressDialog( RegisterActivity.this);



        regname = findViewById(R.id.inputUsername);
        regemail = findViewById(R.id.inputEmail);
        regphone = findViewById(R.id.inputphone);
        regpassword = findViewById(R.id.inputPassword);
        regconfrmpassword = findViewById(R.id.inputConformPassword);
        register = findViewById(R.id.btnRegister);
        register.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                if (TextUtils.isEmpty( regname.getText().toString()) || TextUtils.isEmpty( regemail.getText().toString()) || TextUtils.isEmpty( regphone.getText().toString()) || TextUtils.isEmpty( regpassword.getText().toString()) || TextUtils.isEmpty( regconfrmpassword.getText().toString())){
                    Toast.makeText( RegisterActivity.this, "ALL FIELDS ARE REQUIRED", Toast.LENGTH_SHORT ).show( );
                } else if (regphone.getText().toString().length()!=10){
                    regphone.setError("INVALID PHONE NUMBER\n Phone Number Length Should Be 10 Digit Long");
                } else  if (!(regconfrmpassword.getText().toString().equals(regpassword.getText().toString()))){
                    regpassword.setError("Password and Confirm Password Doesn't Matched");
                    regconfrmpassword.setError("Password and Confirm Password Doesn't Matched");
                }else{
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.setMessage("Please Wait While We Register Yourself To Our Database...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    registerusertodatabse(progressDialog);
                }
            }
        } );




        already = findViewById(R.id.alreadyHaveAccount);
        already.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                startActivity( new Intent( RegisterActivity.this, loginActivity.class));
                finish();
            }
        } );

    }

    private void registerusertodatabse (ProgressDialog pd) {
        final  String useremail = regemail.getText().toString().trim();
        final  String userpassword = regpassword.getText().toString().trim();

        mauth = FirebaseAuth.getInstance();
        mauth.createUserWithEmailAndPassword(useremail,userpassword).addOnCompleteListener( new OnCompleteListener<AuthResult>( ) {
            @Override
            public void onComplete (@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    senddataofuser(regname.getText().toString(),regemail.getText().toString(),regphone.getText().toString(),regpassword.getText().toString(),userid,pd);
//                    startActivity( new Intent(RegisterActivity.this,MainActivity.class) );
//                    finish();
                }else {
                    Toast.makeText( RegisterActivity.this, ""+task.getException().getMessage(), Toast.LENGTH_SHORT ).show( );
                }
            }
        } );

    }

    private void senddataofuser (String a, String b, String c, String d,String userid,ProgressDialog pd) {
       database = FirebaseDatabase.getInstance();
       databaseReference = database.getReference("USERDATA").child(userid);
       Registermodel registermodel = new Registermodel();
       registermodel.setUsername(a);
       registermodel.setEmail( b );
       registermodel.setPhoneno( c );
       registermodel.setPassword(d);

       databaseReference.addValueEventListener( new ValueEventListener( ) {
           @Override
           public void onDataChange (@NonNull DataSnapshot snapshot) {
               databaseReference.setValue(registermodel);
               Toast.makeText( RegisterActivity.this, "USER CREATED SUCCESSFULLY\nDATA ADDED SUCCESSFULLY TO OUR SERVER", Toast.LENGTH_SHORT ).show( );
               Intent i = new Intent( RegisterActivity.this,MainActivity.class  );
               i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
               startActivity(i);
               pd.dismiss();
           }

           @Override
           public void onCancelled (@NonNull DatabaseError error) {
               Toast.makeText( RegisterActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT ).show( );
           }
       } );

    }


}