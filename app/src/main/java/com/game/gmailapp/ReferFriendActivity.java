package com.game.gmailapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReferFriendActivity extends AppCompatActivity {

    EditText full_name, email_id, phone_no, relationship_with_refree, address;
    TextView refer_now, skip;
    FirebaseDatabase database;
    DatabaseReference myRef, demoRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referal_friend);

        full_name = findViewById(R.id.full_name);
        email_id = findViewById(R.id.email_id);
        phone_no = findViewById(R.id.phone_no);
        relationship_with_refree = findViewById(R.id.relationship_with_refree);
        address = findViewById(R.id.address);
        refer_now = findViewById(R.id.refer_now);
        skip = findViewById(R.id.skip);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        demoRef = myRef.child("ReferFriend");

        refer_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(full_name.getText().toString().isEmpty()) {
                    full_name.setError("Name is required");
                    full_name.requestFocus();
                    return;
                }
                if(full_name.getText().toString().indexOf(" ") == -1){
                    full_name.setError("Enter a full name");
                    full_name.requestFocus();
                    return ;
                }
                if (email_id.getText().toString().isEmpty()) {
                    email_id.setError("Email is required");
                    email_id.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email_id.getText().toString()).matches()) {
                    email_id.setError("Enter a valid email");
                    email_id.requestFocus();
                    return;
                }
                if(phone_no.getText().toString().length() != 10){
                    phone_no.setError("Enter a valid Phone number");
                    phone_no.requestFocus();
                    return;
                }
                if(address.getText().toString().length() > 10){
                    address.setError("Enter a full address");
                    address.requestFocus();
                    return;
                }
                ReferFriend friend = new ReferFriend(full_name.getText().toString(), email_id.getText().toString(), phone_no.getText().toString(), relationship_with_refree.getText().toString(), address.getText().toString());
                demoRef.push().setValue(friend);
                Toast.makeText(ReferFriendActivity.this, "Data uploaded successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ReferFriendActivity.this, OperationalActivity.class);
                startActivity(intent);
            }
        });
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReferFriendActivity.this, OperationalActivity.class);
                startActivity(intent);
            }
        });
    }
}
