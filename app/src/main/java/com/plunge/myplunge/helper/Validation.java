package com.plunge.myplunge.helper;

import android.content.Context;
import android.view.View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pradeep on 26/8/16.
 */
public class Validation {

    Context mContext;

    public Validation(Context mContext){
        this.mContext= mContext;
    }
    public static boolean isValidEmaillId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();

    }
    Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public boolean isEmpty(String testString) {
        return (testString == null || testString.isEmpty());
    }
    final Pattern USER_NAME_PATTERN = Pattern.compile("^[a-zA-Z0-9@.#$%^&*_&\\\\]+$");

    public boolean validPassword(String string) {
        boolean is = false;
        if (string.length() > 7) {
            is = true;
        } else {
            is = false;
        }
        return (is);
    }

    public boolean validateEmail(String emailStr) {
        Matcher matcher = emailPattern.matcher(emailStr);
        return matcher.find();
    }






    //Login validation
    public boolean loginVal(View v, String email, String password) {
        boolean isValid = true;
        if (isEmpty(email)) {
            AppConstants.showtoast(mContext,"Please Enter your Name");
            isValid = false;
        } else if (isEmpty(password)) {
            AppConstants.showtoast(mContext,"Please Enter your Password");
            isValid = false;
        }  else if (!validPassword(password)) {
            AppConstants.showtoast(mContext,"Please Enter a Valid Password");
            isValid = false;
        }  else {
            isValid = true;
        }
        return isValid;
    }



}
