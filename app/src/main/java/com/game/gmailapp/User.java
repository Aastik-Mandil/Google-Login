package com.game.gmailapp;

import java.util.ArrayList;

public class User {
    String desc, name, post;

    public User(String desc, String name, String post) {
        this.desc = desc;
        this.name = name;
        this.post = post;
    }

    public String getDesc() {return desc; }

    public void setDesc(String desc) {this.desc = desc; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPost() { return post; }

    public void setPost(String post) { this.post = post; }

    public static ArrayList<User> getUser(){
        ArrayList<User> user = new ArrayList<>();
        user.add(new User("The solar panels system installed has exceeded our expectations in terms of the electricity savings so we’re really happy with performance. Their quality services make it even better.", "DR. VIVEK RATHORE - KOTA", "Retired Professor"));
        user.add(new User("We were very happy with the installation and service acquired from Peacock Solar. They made the whole process hassle free. Definitely recommend them.", "MRS. VIJETA BAJPAI", "Grocery storekeeper"));
        user.add(new User("From the initial application process though to design and installation, Peacock solar has done top notch job. They were genuinely interested in making me a happy customer.", "MR. SHAMBHU SATYANARAYAN", "PWD Officer"));
        return user;
    }
}
