package com.game.gmailapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class TestimonialsActivity extends AppCompatActivity {

    ListView testimony_users;
    ArrayList<User> user = User.getUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testimonials);

        testimony_users = findViewById(R.id.testimony_users);
        UserAdapter userAdapter = new UserAdapter();
        testimony_users.setAdapter(userAdapter);

    }
    public class UserAdapter extends BaseAdapter {

        @Override
        public int getCount() { return user.size(); }

        @Override
        public User getItem(int position) { return user.get(position); }

        @Override
        public long getItemId(int position) { return 0; }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.user,parent,false);
            TextView desc = view.findViewById(R.id.desc);
            TextView name = view.findViewById(R.id.name);
            TextView post = view.findViewById(R.id.post);
            desc.setText(getItem(position).getDesc());
            name.setText(getItem(position).getName());
            post.setText(getItem(position).getPost());
            return view;
        }
    }
}
