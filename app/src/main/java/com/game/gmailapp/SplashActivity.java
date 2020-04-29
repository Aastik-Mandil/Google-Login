package com.game.gmailapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread t = new Thread(){
            @Override
            public void run() {
                super.run();
                try{
                    sleep(3000);
                } catch(Exception e){
                    e.printStackTrace();
                } finally{
                    Intent i = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(i);
                }
            }
        };
        t.start();
    }
}
