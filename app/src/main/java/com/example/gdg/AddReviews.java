package com.example.gdg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.gdg.Models.USERREVIEWMODEL;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddReviews extends AppCompatActivity {


    EditText addfeedback;
    FirebaseDatabase adatabase;
    DatabaseReference adatabaseReference;
    FirebaseUser fuser;
    Button submitfeedbackbtn;
    private EditText  editTextReview;
    private RatingBar ratingBar;
    private  FirebaseAuth auth;
    private DatabaseReference reviewdatabaseReference;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_reviews );
        getSupportActionBar().hide();

        fuser = FirebaseAuth.getInstance( ).getCurrentUser( );
        reviewdatabaseReference = FirebaseDatabase.getInstance().getReference().child("reviews");

        addfeedback = findViewById(R.id.addfeedbackid);
        submitfeedbackbtn = findViewById(R.id.btnSendfeedback);
        submitfeedbackbtn.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                if (TextUtils.isEmpty(addfeedback.getText().toString())){
                    addfeedback.setError("PLEASE ADD REVIEW HERE....");
                    Toast.makeText( AddReviews.this, "Please Add Review First", Toast.LENGTH_SHORT ).show( );
                }else {
                    submitreview();
                }
            }
        } );



        editTextReview = findViewById(R.id.addfeedbackid);
        ratingBar = findViewById(R.id.ratingBar);

//        fab = findViewById(R.id.floatingActionButton3);
//        fab.setOnClickListener( new View.OnClickListener( ) {
//            @Override
//            public void onClick (View v) {
//                startActivity( new Intent( AddReviews.this,ChatActivity.class ) );
//            }
//        } );



    }

    private void submitreview ( ) {
        String emailp = fuser.getEmail();
        String review = editTextReview.getText().toString();
        float rating = ratingBar.getRating();

        auth = FirebaseAuth.getInstance();
        String uid = auth.getCurrentUser().getUid();



        USERREVIEWMODEL userreviewModel = new USERREVIEWMODEL( emailp, review, rating);
        reviewdatabaseReference.child(uid).setValue(userreviewModel)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddReviews.this, "Review Added Successfully", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent( AddReviews.this,MainActivity.class  );
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddReviews.this, "Error submitting review", Toast.LENGTH_SHORT).show();
                    }
                });
    }


}