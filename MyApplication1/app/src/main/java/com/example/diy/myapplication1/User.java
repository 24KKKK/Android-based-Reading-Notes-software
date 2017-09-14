package com.example.diy.myapplication1;

/**
 * Created by Administrator on 2015/11/10.
 */
public class User {

    private String id;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpass() {
        return userpwd;
    }

    public void setUserpass(String userpwd) {
        this.userpwd = userpwd;
    }

    private String username;
    private String userpwd;

}
