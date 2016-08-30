package com.english.howf;

/**
 * Created by HoangDuy on 27/08/2016.
 */
public class User  {
    private String uid;
    private String email;
    private String[] friendsId;
    private String[] request;
    private String gender;
    private String birthday;
    private String location;
    private String name;

    public User() {

    }

    public User(String uid, String email, String[] friendsId, String gender, String birthday, String [] request, String location) {
        this.uid = uid;
        this.email = email;
        this.friendsId = friendsId;
        this.gender = gender;
        this.birthday = birthday;
        this.request=request;
        this.location=location;
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

    public String[] getRequest() {
        return request;
    }

    public void setRequest(String[] request) {
        this.request = request;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
