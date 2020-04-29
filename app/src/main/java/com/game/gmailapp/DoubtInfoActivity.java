package com.game.gmailapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class DoubtInfoActivity extends AppCompatActivity {

    EditText doubt_res;
    ImageView doubt_send;
    TextView doubt_que;
    ListView response_list;
    String question = "";
    ArrayList<Response> responses;
    FirebaseDatabase database;
    DatabaseReference myRef, dataRef, demoRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doubt_info);

        doubt_res = findViewById(R.id.doubt_res);
        doubt_send = findViewById(R.id.doubt_send);
        doubt_que = findViewById(R.id.doubt_que);
        response_list = findViewById(R.id.response_list);

        question = getIntent().getStringExtra("doubt");
        doubt_que.setText(question);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        demoRef = myRef.child("Response");
        responses = getAllResponseInfo();

        ResponseAdapter adapter = new ResponseAdapter(this,R.layout.response, responses);
        response_list.setAdapter(adapter);

        doubt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(doubt_res.getText().toString().isEmpty()){
                    doubt_res.setError("Please write your reply here");
                    doubt_res.requestFocus();
                    return;
                }
                Response res = new Response(doubt_res.getText().toString());
                demoRef.child(question).push().setValue(res);
                Toast.makeText(DoubtInfoActivity.this, "response send", Toast.LENGTH_SHORT).show();
                doubt_res.setText("");
            }
        });
    }

    private ArrayList<Response> getAllResponseInfo() {
        final ArrayList<Response> responseOfDoubt = new ArrayList<>();
        demoRef.child(question).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    HashMap<String, Object> dataMap = (HashMap<String, Object>) dataSnapshot.getValue();
                    for(String key: dataMap.keySet()){
                        Object data = dataMap.get(key);
                        try{
                            HashMap<String, Object> responseData = (HashMap<String, Object>) data;
                            String res = responseData.values().toString();
                            Log.d("Cheking", res);
                            responseOfDoubt.add(new Response(res.substring(1,res.length()-1)));
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(DoubtInfoActivity.this, "reply fetch error", Toast.LENGTH_SHORT).show();
                Log.d("Checking", "reply fetch error");
            }
        });
        return responseOfDoubt;
    }
    public class ResponseAdapter extends BaseAdapter {

        Context context;
        int layout;
        ArrayList<Response> responseArrayList;

        public ResponseAdapter(Context context, int layout, ArrayList<Response> responseArrayList) {
            this.context = context;
            this.layout = layout;
            this.responseArrayList = responseArrayList;
        }

        @Override
        public int getCount() { return responses.size(); }

        @Override
        public Response getItem(int i) { return responses.get(i); }

        @Override
        public long getItemId(int i) { return 0; }

        private class ViewHolder { TextView res;}

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View row = view;
            ViewHolder holder = new ViewHolder();
            if(row == null){
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(layout, null);
                holder.res = row.findViewById(R.id.response);
                row.setTag(holder);
            }
            else{
                holder = (ViewHolder) row.getTag();
            }
            Response res_val = responseArrayList.get(i);
            holder.res.setText(res_val.getResponse());
            return row;
        }
    }
}
