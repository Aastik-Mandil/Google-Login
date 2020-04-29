package com.game.gmailapp;

import java.util.ArrayList;

public class ReferFriend {
    String full_name, email_id, phone_no, relation, address;

    public ReferFriend(String full_name, String email_id, String phone_no, String relation, String address) {
        this.full_name = full_name;
        this.email_id = email_id;
        this.phone_no = phone_no;
        this.relation = relation;
        this.address = address;
    }

    public String getFull_name() { return full_name; }

    public void setFull_name(String full_name) { this.full_name = full_name; }

    public String getEmail_id() { return email_id; }

    public void setEmail_id(String email_id) { this.email_id = email_id; }

    public String getPhone_no() { return phone_no; }

    public void setPhone_no(String phone_no) { this.phone_no = phone_no; }

    public String getRelation() { return relation; }

    public void setRelation(String relation) { this.relation = relation; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

}
