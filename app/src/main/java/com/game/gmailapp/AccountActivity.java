package com.game.gmailapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountActivity extends AppCompatActivity {

    String name="Json Bose", email="abcxyz123@gmail.com", phoneNo="0000000000";
    TextView name_auth, email_auth, phoneNo_auth;
    Button logout;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        name_auth = findViewById(R.id.name_auth);
        email_auth = findViewById(R.id.email_auth);
        phoneNo_auth = findViewById(R.id.phoneNo_auth);
        logout = findViewById(R.id.logout);

//        name = getIntent().getStringExtra("display_name");
//        email = getIntent().getStringExtra("email");
//        phoneNo = getIntent().getStringExtra("phone_number");

        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null){
            name = user.getDisplayName();
            email = user.getEmail();
            phoneNo = user.getPhoneNumber();
        }

        name_auth.setText(name);
        email_auth.setText(email);
        phoneNo_auth.setText(phoneNo);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() == null){
                    Toast.makeText(AccountActivity.this, "Log out successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AccountActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
            }
        });
    }
}
