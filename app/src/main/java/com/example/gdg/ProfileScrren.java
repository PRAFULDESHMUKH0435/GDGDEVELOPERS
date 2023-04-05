package com.example.gdg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gdg.Models.profilemodel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class ProfileScrren extends AppCompatActivity {

    TextView namefld,password,emailfld,phonennofld,nametop,emailtop;
    FloatingActionButton fab;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_profile_scrren );
        getSupportActionBar().hide();


        ProgressDialog progressDialog = new ProgressDialog( this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Please Wait While We Load Data...");
        progressDialog.show();


        namefld = findViewById(R.id.name_fld);
        password = findViewById(R.id.pass);
        emailfld = findViewById(R.id.email);
        phonennofld = findViewById(R.id.phone);
        nametop = findViewById(R.id.textView);
        emailtop = findViewById(R.id.textView2);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uid = auth.getCurrentUser().getUid();

       if (FirebaseAuth.getInstance().getCurrentUser()==null){
           startActivity( new Intent( ProfileScrren.this,RegisterActivity.class ) );
           finish();
       }else{
           DatabaseReference userRef = ref.child("USERDATA").child(uid);
           userRef.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(DataSnapshot dataSnapshot) {
                   // Get the user profile data
                   if (FirebaseAuth.getInstance().getCurrentUser()!=null){
                       profilemodel userProfile = dataSnapshot.getValue( profilemodel.class);
                       nametop.setText(userProfile.getUsername());
                       emailtop.setText(userProfile.getEmail());
                       namefld.setText(userProfile.getUsername());
                       emailfld.setText(userProfile.getEmail());
                       password.setText(userProfile.getPassword());
                       phonennofld.setText(userProfile.getPhoneno());
                       progressDialog.dismiss();
                   }
               }

               @Override
               public void onCancelled(DatabaseError databaseError) {
                   // Handle errors here
                   Toast.makeText( ProfileScrren.this, ""+databaseError.getMessage(), Toast.LENGTH_SHORT ).show( );
               }
           });
       }


        fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProfileScrren.this);
                    builder.setMessage(" Are You Sure You Want To Logout");
                    builder.setTitle("Logout!");
                    builder.setIcon(R.drawable.baseline_logout_24);
                    builder.setCancelable(false);
                    builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                        deleteuser();
                        finish();
                });

                builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dialog.cancel();
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        } );
    }

    private void deleteuser ( ) {
        Intent intent = new Intent(this, loginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


}