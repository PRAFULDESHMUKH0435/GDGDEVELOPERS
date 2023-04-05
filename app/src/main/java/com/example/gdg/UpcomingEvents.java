package com.example.gdg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.gdg.Adapters.UpcomingAdapter;
import com.example.gdg.Models.UpcomingModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UpcomingEvents extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    UpcomingAdapter upcomingAdapter;
    ArrayList<UpcomingModel> list;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_upcoming_events );
        getSupportActionBar().setTitle("Upcoming Events");

        ProgressDialog progressDialog = new ProgressDialog( this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Please Wait While We Load Data...");
        progressDialog.show();

        recyclerView = findViewById(R.id.recview);
        database = FirebaseDatabase.getInstance().getReference("UPCOMING EVENTS");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );

        list = new ArrayList<>();
        upcomingAdapter = new UpcomingAdapter(this,list );
        recyclerView.setAdapter(upcomingAdapter);
        database.addValueEventListener( new ValueEventListener( ) {
            @Override
            public void onDataChange (@NonNull DataSnapshot snapshot) {
                for (DataSnapshot datasnapshot:snapshot.getChildren()){
                    UpcomingModel upcomingModel = datasnapshot.getValue(UpcomingModel.class);
                    list.add( upcomingModel );
                }
                upcomingAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled (@NonNull DatabaseError error) {

            }
        } );

    }
}