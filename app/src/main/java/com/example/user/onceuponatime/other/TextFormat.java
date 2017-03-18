package com.example.user.onceuponatime.other;

import java.util.regex.Pattern;

/**
 * Created by User on 18/03/2017.
 */

public class TextFormat {

    public static final int PASSWORD_CHECK = 555695;
    public static final int USERNAME_CHECK = 87962575;
    public static final int EMAIL_CHECK = 87996;
    public static final int PARAGRAPH_CHECK = 8799632;
    public static final int TITLE_CHECK = 112458;

    private String strToCheck;


    public TextFormat(String text,int checkType) {
        this.strToCheck = text;
    }

    public boolean passwordChecker() {

        //Password must be at least 6 characs long and contains only num , chars and -_
        String pattern = "^[a-zA-Z0-9\\-_]{6,}$";

        boolean matches = Pattern.matches(strToCheck,pattern);

        return matches;

    }
}
