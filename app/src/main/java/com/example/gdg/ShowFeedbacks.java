package com.example.gdg;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gdg.Adapters.ShowFeedbackAdapters;
import com.example.gdg.Models.ShowFeedbackModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowFeedbacks extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    ShowFeedbackAdapters showFeedbackAdapters;
    ArrayList<ShowFeedbackModel> list;
    RatingBar ratingBar;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_show_feedbacks );
        getSupportActionBar().setTitle("Reviews Panel");

        ProgressDialog progressDialog = new ProgressDialog( this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Please Wait While We Load Reviews From Database...");
        progressDialog.show();


        recyclerView = findViewById(R.id.recview_feedback);
        database = FirebaseDatabase.getInstance().getReference( "reviews");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );
        list = new ArrayList<>();
        showFeedbackAdapters= new ShowFeedbackAdapters(this,list );
        recyclerView.setAdapter(showFeedbackAdapters);
        database.addValueEventListener( new ValueEventListener( ) {
            @Override
            public void onDataChange (@NonNull DataSnapshot snapshot) {
                for (DataSnapshot datasnapshot:snapshot.getChildren()){
                    ShowFeedbackModel showFeedbackModel = datasnapshot.getValue(ShowFeedbackModel.class);
                    list.add(showFeedbackModel);
                }
                showFeedbackAdapters.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled (@NonNull DatabaseError error) {
                Toast.makeText( ShowFeedbacks.this, ""+error.getMessage(), Toast.LENGTH_SHORT ).show( );
            }
        } );
    }
}