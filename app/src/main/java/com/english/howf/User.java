package com.english.howf;

/**
 * Created by HoangDuy on 27/08/2016.
 */
public class User  {
    private String uid;
    private String email;
    private String[] friendsId;
    private String gender;
    private String birthday;

    public User(String uid, String email, String[] friendsId, String gender, String birthday) {
        this.uid = uid;
        this.email = email;
        this.friendsId = friendsId;
        this.gender = gender;
        this.birthday = birthday;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String[] getFriendsId() {
        return friendsId;
    }

    public void setFriendsId(String[] friendsId) {
        this.friendsId = friendsId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
