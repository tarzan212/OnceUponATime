package com.example.user.onceuponatime.other;

/**
 * Created by User on 12/03/2017.
 */

public class ParagraphHelper {

    private String paragraphID;
    private String userID;
    private String storyID;
    private String paragraphText;

    public ParagraphHelper() {}

    public ParagraphHelper(String paragID,String uID,String storyID,String paragraph) {
        this.paragraphID = paragID;
        this.userID = uID;
        this.storyID = storyID;
        this.paragraphText = paragraph;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getParagraphID() {
        return paragraphID;
    }

    public void setParagraphID(String paragraphID) {
        this.paragraphID = paragraphID;
    }

    public String getStoryID() {
        return storyID;
    }

    public void setStoryID(String storyID) {
        this.storyID = storyID;
    }

    public String getParagraphText() {
        return paragraphText;
    }

    public void setParagraphText(String paragraphText) {
        this.paragraphText = paragraphText;
    }
}
