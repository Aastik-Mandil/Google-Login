package com.game.gmailapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class OffersActivity extends AppCompatActivity {

    TextView about;
    ListView offers;
    ArrayList<Offers> offersList = Offers.getOffers();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);

        about = findViewById(R.id.about);
        offers = findViewById(R.id.offers);

        about.setText("Sangam Smesco Private Limited under the brand name of Peacock solar  provides Rooftop Solar plants for homes. Sign up with us for Hassle Free Solar installation and maintenance. We currently provide services in Maharastra, UP and Rajasthan in India.");

        OfferAdapter adapter = new OfferAdapter();
        offers.setAdapter(adapter);
    }
    public class OfferAdapter extends BaseAdapter {

        @Override
        public int getCount() { return offersList.size(); }

        @Override
        public Offers getItem(int position) { return offersList.get(position); }

        @Override
        public long getItemId(int position) { return 0; }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.offers_layout,parent,false);
            TextView Title = view.findViewById(R.id.title);
            TextView Price = view.findViewById(R.id.price);
            TextView Unit = view.findViewById(R.id.unit);
            TextView Comment1 = view.findViewById(R.id.comment1);
            TextView Comment2 = view.findViewById(R.id.comment2);
            TextView Comment3 = view.findViewById(R.id.comment3);
            TextView Process = view.findViewById(R.id.process);

            Title.setText(getItem(position).getTitle());
            Price.setText(getItem(position).getPrice());
            Unit.setText(getItem(position).getUnit());
            Comment1.setText(getItem(position).getComment1());
            Comment2.setText(getItem(position).getComment2());
            Comment3.setText(getItem(position).getComment3());
            Process.setText(getItem(position).getProcess());
            return view;
        }
    }
}
