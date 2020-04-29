package com.game.gmailapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

import java.util.ArrayList;
import java.util.HashMap;

public class FAQsActivity extends AppCompatActivity {

    ListView list_doubt;
    EditText doubt;
    ImageView send;
    ArrayList<Doubt> doubtList;
    FirebaseDatabase database;
    DatabaseReference myRef, demoRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs);

        list_doubt = findViewById(R.id.list_doubt);
        doubt = findViewById(R.id.doubt);
        send = findViewById(R.id.send);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        demoRef = myRef.child("Doubts");
        doubtList = getDoubtInfo();

        final DoubtAdapter adapter = new DoubtAdapter(this, R.layout.doubt, doubtList);
        list_doubt.setAdapter(adapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(doubt.getText().toString().isEmpty()){
                    doubt.setError("Please write you doubt");
                    doubt.requestFocus();
                    return;
                }
                Doubt db = new Doubt(doubt.getText().toString());
                demoRef.push().setValue(db);
                Toast.makeText(FAQsActivity.this, "Question send\nYou will get response soon", Toast.LENGTH_SHORT).show();
                doubt.setText("");
            }
        });
        list_doubt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(FAQsActivity.this,DoubtInfoActivity.class);
                intent.putExtra("doubt", adapter.getItem(i).getDoubt());
                startActivity(intent);
            }
        });
    }

    public ArrayList<Doubt> getDoubtInfo(){
        final ArrayList<Doubt> listOfDoubt = new ArrayList<>();
        demoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    HashMap<String, Object> dataMap = (HashMap<String, Object>) dataSnapshot.getValue();
                    for (String key: dataMap.keySet()){
                        Object data = dataMap.get(key);
//                        Log.d("Checking", key);
                        try {
                            HashMap<String, Object> doubtData = (HashMap<String, Object>) data;
                            String db = (String) doubtData.get("doubt");
//                            Log.d("Checking", db);
                            listOfDoubt.add(new Doubt(db));
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(FAQsActivity.this, "doubt fetch cancelled", Toast.LENGTH_SHORT).show();
                Log.d("Checking", "doubt fetch cancelled");
            }
        });
        return listOfDoubt;
    }

    public class DoubtAdapter extends BaseAdapter {

        Context context;
        int layout;
        ArrayList<Doubt> doubtArrayList;

        public DoubtAdapter(Context context, int layout, ArrayList<Doubt> doubtArrayList) {
            this.context = context;
            this.layout = layout;
            this.doubtArrayList = doubtArrayList;
        }

        @Override
        public int getCount() { return doubtList.size(); }

        @Override
        public Doubt getItem(int i) { return doubtList.get(i); }

        @Override
        public long getItemId(int i) { return 0; }

        private class ViewHolder {
            TextView db;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View row = view;
            ViewHolder holder = new ViewHolder();
            if(row == null){
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(layout, null);
                holder.db = row.findViewById(R.id.doubt_question);
                row.setTag(holder);
            }
            else{
                holder = (ViewHolder) row.getTag();
            }
            Doubt db_val = doubtArrayList.get(i);
            holder.db.setText(db_val.getDoubt());
            return row;
        }
    }
}
