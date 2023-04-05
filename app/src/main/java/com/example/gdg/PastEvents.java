package com.example.gdg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.gdg.Adapters.PastAdapter;
import com.example.gdg.Models.PastModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PastEvents extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    PastAdapter pastAdapter;
    ArrayList<PastModel> list;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_past_events );
        getSupportActionBar().setTitle("Past Events");

        ProgressDialog progressDialog = new ProgressDialog( this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Please Wait While We Load Data...");
        progressDialog.show();

        recyclerView = findViewById(R.id.past_recview);
        database = FirebaseDatabase.getInstance().getReference( "PASTEVENTS");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );

        list = new ArrayList<>();
        pastAdapter = new PastAdapter(this,list );
        recyclerView.setAdapter(pastAdapter);

        database.addValueEventListener( new ValueEventListener( ) {
            @Override
            public void onDataChange (@NonNull DataSnapshot snapshot) {
                for (DataSnapshot datasnapshot:snapshot.getChildren()){
                    PastModel pastModel = datasnapshot.getValue(PastModel.class);
                    list.add( pastModel );
                }
                pastAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled (@NonNull DatabaseError error) {
                Toast.makeText( PastEvents.this, ""+error.getMessage(), Toast.LENGTH_SHORT ).show( );
            }
        } );
    }
}