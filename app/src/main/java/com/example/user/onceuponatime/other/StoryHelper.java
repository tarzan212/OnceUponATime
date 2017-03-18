package com.example.user.onceuponatime.other;

import java.util.Map;

/**
 * Created by User on 11/03/2017.
 */

public class StoryHelper {

    private String storyID;
    private String userCreator;
    private Map<String,String> paragraphs;

    public StoryHelper() {}

    public StoryHelper(String storyid ,String founder,Map<String,String> parag) {
        this.storyID = storyid;
        this.userCreator = founder;
        this.paragraphs = parag;
    }

    public String getStoryID() {
        return storyID;
    }

    public void setStoryID(String storyID) {
        this.storyID = storyID;
    }

    public String getUserCreator() {
        return userCreator;
    }

    public void setUserCreator(String userCreator) {
        this.userCreator = userCreator;
    }

    public Map<String, String> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(Map<String, String> paragraphs) {
        this.paragraphs = paragraphs;
    }
}
