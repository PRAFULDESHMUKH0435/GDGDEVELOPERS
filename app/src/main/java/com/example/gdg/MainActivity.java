package com.example.gdg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gdg.Models.profilemodel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.saadahmedsoft.popupdialog.PopupDialog;
import com.saadahmedsoft.popupdialog.Styles;
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {


    LinearLayout upcomingevents,pastevents,profile,showfeedbacks;
    FloatingActionButton AddReview;
    FirebaseAuth firebaseAuth;
    TextView namefield;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        getSupportActionBar().hide();

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()!=null){

        }
        upcomingevents = findViewById(R.id.upcoming);
        upcomingevents.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                startActivity( new Intent(MainActivity.this,UpcomingEvents.class));
            }
        } );


        pastevents = findViewById(R.id.past);
        pastevents.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                startActivity( new Intent(MainActivity.this,PastEvents.class));
            }
        } );


        profile = findViewById(R.id.profile);
        profile.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                startActivity( new Intent(MainActivity.this,ProfileScrren.class));
            }
        } );


        showfeedbacks = findViewById(R.id.showfeedback);
        showfeedbacks.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                startActivity( new Intent(MainActivity.this,ShowFeedbacks.class));
            }
        } );


        AddReview = findViewById(R.id.addreview);
        AddReview.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                startActivity( new Intent( MainActivity.this,AddReviews.class ) );
            }
        } );


        namefield = findViewById(R.id.name_mainpage);
        namefield.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());




//        CODE FOR FIREBASE MESSAGE SENDING
        FirebaseMessaging.getInstance().subscribeToTopic( "notification")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Done";
                        if (!task.isSuccessful()) {
                            msg = "failed";
                        }
                    }
                });
    }

    @Override
    public void onBackPressed ( ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Are You Sure Do you want to exit ?");
        builder.setTitle("Exit ? ");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            finish();
        });

        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });

        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();
    }
}