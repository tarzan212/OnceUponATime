package com.example.user.onceuponatime.other;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;

import java.util.Map;

/**
 * Created by User on 11/03/2017.
 */

public class UserHelper {

    private String userID;
    private String username;
    private String email;
    private long date;
    private Map<String,String> stories;
    private Map<String,Map<String,String>> invStories;

    public UserHelper() {};

    public UserHelper(@NonNull String userid, String usnme, @NonNull String email,
                      long date,Map<String,String> stories,Map<String,Map<String,String>> involving) {
        this.userID = userid;
        this.username = usnme;
        this.email = email;
        this.date = date;
        this.stories = stories;
        this.invStories = involving;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public Map<String, String> getStories() {
        return stories;
    }

    public void setStories(Map<String, String> stories) {
        this.stories = stories;
    }

    public Map<String, Map<String, String>> getInvStories() {
        return invStories;
    }

    public void setInvStories(Map<String, Map<String, String>> invStories) {
        this.invStories = invStories;
    }
}
