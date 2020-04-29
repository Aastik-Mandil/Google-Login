package com.game.gmailapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactActivity extends AppCompatActivity {

    TextView phone_call, send_email;
    ImageView facebook, linkedin, twitter, instragram;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        phone_call = findViewById(R.id.phone_call);
        send_email = findViewById(R.id.send_email);
        facebook = findViewById(R.id.facebook);
        linkedin = findViewById(R.id.linkedin);
        twitter = findViewById(R.id.twitter);
        instragram = findViewById(R.id.instragram);

        phone_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int perm = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE);
                if(perm == PackageManager.PERMISSION_GRANTED){
                    String telNo = (String) phone_call.getText();
                    Uri uri = Uri.parse("tel:"+telNo);
                    Intent i = new Intent(Intent.ACTION_CALL,uri);
                    startActivity(i);
                }
                else{
                    ActivityCompat.requestPermissions(ContactActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 123);
                }
            }
        });
        send_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.putExtra(Intent.EXTRA_EMAIL,send_email.getText());
                i.setType("message/rfc822");
                startActivity(Intent.createChooser(i,"choose an email client"));
            }
        });
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.facebook.com/peacocksolar/"));
                startActivity(intent);
            }
        });
        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.linkedin.com/authwall?trk=gf&trkInfo=AQGGIGw-7EjyGQAAAXF9c-cAqdsF409mQZvZKgkfnE1GrNwjWM8Zoiyem-Xbg9yLhDtnV5KPJ1ruGi7XZTWNHC5lu-c0YVNcXNZp8WGa4O3JiwWh7tre0munqpsGEBhyedbFPDw=&originalReferer=&sessionRedirect=https%3A%2F%2Fin.linkedin.com%2Fcompany%2Fpsolar"));
                startActivity(intent);
            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://twitter.com/peacocksolar/"));
                startActivity(intent);
            }
        });
        instragram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.instagram.com/peacocksolar/"));
                startActivity(intent);
            }
        });
    }
}
