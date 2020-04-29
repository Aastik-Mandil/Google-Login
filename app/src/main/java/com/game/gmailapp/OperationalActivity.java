package com.game.gmailapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class OperationalActivity extends AppCompatActivity {

    ImageView account, referrals, offers, why_solar, testimonials, faqs, contact;
    Button refer_a_friend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operational);

        account = findViewById(R.id.account);
        referrals = findViewById(R.id.referrals);
        offers = findViewById(R.id.offers);
        why_solar = findViewById(R.id.why_solar);
        testimonials = findViewById(R.id.testimonials);
        faqs = findViewById(R.id.faqs);
        contact = findViewById(R.id.contact);
        refer_a_friend = findViewById(R.id.refer_a_friend);

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OperationalActivity.this,AccountActivity.class);
                startActivity(intent);
            }
        });
        referrals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OperationalActivity.this,ReferralsActivity.class);
                startActivity(intent);
            }
        });
        offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OperationalActivity.this,OffersActivity.class);
                startActivity(intent);
            }
        });
        why_solar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OperationalActivity.this,WhySolarActivity.class);
                startActivity(intent);
            }
        });
        testimonials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OperationalActivity.this,TestimonialsActivity.class);
                startActivity(intent);
            }
        });
        faqs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OperationalActivity.this,FAQsActivity.class);
                startActivity(intent);
            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OperationalActivity.this,ContactActivity.class);
                startActivity(intent);
            }
        });
        refer_a_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OperationalActivity.this,ReferFriendActivity.class);
                startActivity(intent);
            }
        });
    }
}
