package com.plunge.myplunge.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ${pradeep} on 24/6/16.
 */
public class SharedPreference {

    public static final String mypreference = "mypref";

    public SharedPreference() {
        super();
    }

      /* singleton implementation for shared pref values*/

    private static SharedPreference pref = new SharedPreference();

    public static SharedPreference getInstance() {

        return pref;
    }

    public void save(Context context, String Keyvalue, String text) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(mypreference, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2
        editor.putString(Keyvalue, text); //3
        editor.commit(); //4

    }
    public String getValue(Context context, String Keyvalue) {
        SharedPreferences settings;
        String text;
        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        text = settings.getString(Keyvalue,null);
        return text;
    }


    public void clearSharedPreference(Context context) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.clear();
        editor.commit();
    }

    public void removeValue(Context context, String Keyvalue) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.remove(Keyvalue);
        editor.commit();
    }

    public void saveBool(Context context, String Keyvalue, boolean status) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(mypreference, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2
        editor.putBoolean(Keyvalue, status); //3
        editor.commit(); //4
    }

    public boolean getBool(Context context, String Keyvalue) {

        SharedPreferences settings;
        boolean text;
        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        text = settings.getBoolean(Keyvalue, false);
        return text;
    }

    public void saveInt(Context context, String Keyvalue, int text) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(mypreference, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2
        editor.putInt("theme", text); //3
        editor.commit(); //4
    }

    public int getInt(Context context, String Keyvalue) {
        SharedPreferences settings;
        int text;
        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        text = settings.getInt(Keyvalue, 0);
        return text;
    }


}
