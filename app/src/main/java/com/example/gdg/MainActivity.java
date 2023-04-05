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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
//        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
//        DatabaseReference name = (DatabaseReference) FirebaseDatabase.getInstance().getReference().child( "USERDATA").child( uid)
//                        .addValueEventListener( new ValueEventListener( ) {
//                            @Override
//                            public void onDataChange (@NonNull DataSnapshot snapshot) {
//                                profilemodel userProfile = snapshot.getValue( profilemodel.class);
//                                namefield.setText(userProfile.getUsername());
//                            }
//
//                            @Override
//                            public void onCancelled (@NonNull DatabaseError error) {
//                                Toast.makeText( MainActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT ).show( );
//                            }
//                        } );
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