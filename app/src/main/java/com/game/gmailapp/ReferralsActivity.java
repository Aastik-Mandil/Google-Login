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

public class ReferralsActivity extends AppCompatActivity {

    ListView referrals_info;
    ArrayList<ReferFriend> friend;
    FirebaseDatabase database;
    DatabaseReference myRef, demoRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referrals);

        referrals_info = findViewById(R.id.referrals_info);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        demoRef = myRef.child("ReferFriend");
        friend = getRefferalsFriendInfo();

        ReferralInfoAdapter referralInfoAdapter = new ReferralInfoAdapter(this, R.layout.referral_view, friend);
        referrals_info.setAdapter(referralInfoAdapter);
    }

    public ArrayList<ReferFriend> getRefferalsFriendInfo(){
        final ArrayList<ReferFriend> friendArrayList = new ArrayList<>();
        demoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    HashMap<String, Object> dataMap = (HashMap<String, Object>) dataSnapshot.getValue();
                    for(String key : dataMap.keySet()){
                        Object data = dataMap.get(key);
                        Log.d("Checking", key);
                        try{
                            HashMap<String, Object> friendData = (HashMap<String, Object>) data;
                            String name = (String) friendData.get("full_name");
                            String email = (String) friendData.get("email_id");
                            String phone = (String) friendData.get("phone_no");
                            String relation = (String) friendData.get("relation");
                            String address = (String) friendData.get("address");
                            Log.d("Checking", name+" "+email+" "+phone+" "+relation+" "+address);
                            friendArrayList.add(new ReferFriend(name, email, phone, relation, address));
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ReferralsActivity.this, "data fetch cancelled", Toast.LENGTH_SHORT).show();
                Log.d("Checking", "data fetch cancelled "+ databaseError.getMessage());
            }
        });

        return friendArrayList;
    }

    public class ReferralInfoAdapter extends BaseAdapter {

        Context context;
        int layout;
        ArrayList<ReferFriend> referFriends;

        public ReferralInfoAdapter(Context context, int layout, ArrayList<ReferFriend> referFriends) {
            this.context = context;
            this.layout = layout;
            this.referFriends = referFriends;
        }

        @Override
        public int getCount() { return friend.size(); }

        @Override
        public ReferFriend getItem(int position) { return friend.get(position); }

        @Override
        public long getItemId(int position) { return 0; }

        private class ViewHolder{
            TextView Name, Email, Phone, Relation, Address;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            ViewHolder holder = new ViewHolder();
            if(row == null){
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(layout, null);
                holder.Name = row.findViewById(R.id.name);
                holder.Email = row.findViewById(R.id.email);
                holder.Phone = row.findViewById(R.id.phone);
                holder.Relation = row.findViewById(R.id.relation);
                holder.Address = row.findViewById(R.id.address);
                row.setTag(holder);
            } else{
                holder = (ViewHolder) row.getTag();
            }
            ReferFriend friend = referFriends.get(position);
            holder.Name.setText(friend.getFull_name());
            holder.Email.setText(friend.getEmail_id());
            holder.Phone.setText(friend.getPhone_no());
            holder.Relation.setText(friend.getRelation());
            holder.Address.setText(friend.getAddress());
            return row;
        }
    }
}
