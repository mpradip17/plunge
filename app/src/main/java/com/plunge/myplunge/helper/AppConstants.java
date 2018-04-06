package com.plunge.myplunge.helper;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import java.util.regex.Pattern;

/**
 * Created by ${pradeep} on 6/6/16.
 */
public class AppConstants {

    public static ProgressDialog pDialog;
    public static String BASE_URL="http://54.213.178.7/";

    final Pattern USER_NAME_PATTERN = Pattern.compile("^[a-zA-Z0-9@.#$%^&*_&\\\\]+$");

    public static void showProgres(Context context) {
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        showpDialog();
        int i = 0;
        Log.e("show progress", "show progress"+i++);
    }

    public static void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    public static void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public static void showtoast(Context contex, String message) {
        Toast ifx = Toast.makeText(contex, "" + message, Toast.LENGTH_SHORT);
        ifx.show();
    }

    public static void checknet(Context context) {
        Toast ifx = Toast.makeText(context, "Check your Internet Connection", Toast.LENGTH_SHORT);
        ifx.show();
    }

    public static void loading(Context context) {
        Toast ifx = Toast.makeText(context, "Loading please wait.", Toast.LENGTH_SHORT);
        ifx.show();
    }



    public static void checkInternet(Context context) {
        boolean isNet = AppConstants.isNetworkAvailable(context);
        if (!isNet) {
            AppConstants.checknet(context);
        }
    }
    public static boolean isEmptyString(String text) {
        return (text == null || text.trim().equals("null") || text.trim()
                .length() <= 0);
    }


}