package com.example.gdg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;

public class splashActivity extends AppCompatActivity {

    private FirebaseAuth mauth;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.splash );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
                              WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        mauth = FirebaseAuth.getInstance();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                  Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    if (mauth.getCurrentUser()==null){
                        startActivity(new Intent( splashActivity.this, loginActivity.class));
                        finish();
                    }else {
                        startActivity( new Intent( splashActivity.this,MainActivity.class ) );
                        finish();
                    }
                }
            }
        }).start();
    }

}