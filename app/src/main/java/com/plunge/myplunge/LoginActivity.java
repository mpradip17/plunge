package com.plunge.myplunge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;


import com.plunge.myplunge.helper.AppConstants;
import com.plunge.myplunge.helper.RetrofitClient;
import com.plunge.myplunge.helper.SharedPreference;
import com.plunge.myplunge.helper.Validation;
import com.plunge.myplunge.helper.WebServicesAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

//import ru.dimorinny.floatingtextbutton.FloatingTextButton;

/**
 * Created by root on 16/3/17.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "LoginActivity";
    private  Context mContext;
    private Activity mActivity;
    //private  FloatingTextButton mRegisterButton;
    @BindView(R.id.client_id_input_layout) EditText mEmail;
    @BindView(R.id.authenticate) EditText mPassword;
    @BindView(R.id.login) Button mLoginButton;
    boolean mCheckNetwork,mValidate;
    Validation mValid;
    private WebServicesAPI mApiInterface;
    private Map<String, String> queryParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = LoginActivity.this;
        mActivity= LoginActivity.this;
        setContentView(R.layout.activity_login);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        ButterKnife.bind(mActivity);
        mCheckNetwork= AppConstants.isNetworkAvailable(mContext);
        mValid  = new Validation(mContext);
        mLoginButton.setOnClickListener(this);
    }





    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.login:
                //To check whether the email is valid
                if (mCheckNetwork) {
                    mValidate=mValid.loginVal(mEmail,mEmail.getText().toString().trim(),mPassword.getText().toString().trim());
                    if (mValidate){
                        loginoath();
                    }
                }
                break;
         }
    }
   //  queryParams.put("name","demo");
    //        queryParams.put("password","demo@123");
    private void loginoath() {
      AppConstants.showProgres(mContext);
            mApiInterface = RetrofitClient.getClient().create(WebServicesAPI.class);
            queryParams=new HashMap<>();
            queryParams.put("name",mEmail.getText().toString().trim());
            queryParams.put("password",mPassword.getText().toString().trim());
            Call<ResponseBody> call = mApiInterface.loginoauth("XMLHttpRequest", queryParams);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                    AppConstants.hidepDialog();
                    Log.e("Retrofit NetworkSuccess", call.request().url().toString()+response.code());
                    if (response.isSuccessful()) {
                        try {
                            String json = response.body().string();
                            Log.e("Sucesss",""+ response.code()+"Response"+json+queryParams.get("email")+queryParams.get("password"));
                            JSONObject successObj = null;
                            try {
                                successObj = new JSONObject(json);
                                String token = successObj.optJSONObject("data").getString("token");
                                SharedPreference.getInstance().save(mContext, "header",token);
                                Intent intent=new Intent(mContext,MainActivity.class);
                                startActivity(intent);
                                AppConstants.showtoast(mContext,successObj.getString("message"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else if (response.code() == 400|| response.code() == 405 || response.code() == 500) {
                        // Handle unauthorized
                        try {

                            String errorresponse = response.errorBody().string();
                            Log.e("Error",""+ response.code()+errorresponse);

                            JSONObject jObjError = new JSONObject(errorresponse);
                      //      AppHelper.displayMessage(mView,mContext,jObjError.getString("message"));
                        } catch (Exception e) {
                        //    AppHelper.displayMessage(mView,mContext,(e.getMessage()));
                        }

                    } else if (response.code() == 401) {
                        try {
                            Log.e("Error",""+ response.code());
                            String errorresponse = response.errorBody().string();
                            JSONObject jObjError = new JSONObject(errorresponse);
AppConstants.showtoast(mContext,"Invalid Password or Username");
                            if (jObjError.optString("message").equalsIgnoreCase("invalid_token")) {
                                //Call Refresh token
                            } else {
                         //       AppHelper.displayMessage(mView,mContext,jObjError.optString("message"));
                            }
                        } catch (Exception e) {
                           // AppHelper.displayMessage(mView,mContext,getString(R.string.something_went_wrong));
                        }

                    } else if (response.code() == 422) {
                        try {
                            Log.e("Error",""+ response.code());
                            String json = null;
                            String errorresponse = response.errorBody().string();
                            if (json != "" && json != null) {
                               // AppHelper.displayMessage(mView,mContext,errorresponse);
                            } else {
                             //   AppHelper.displayMessage(mView,mContext,mContext.getResources().getString(R.string.please_try_again));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    AppConstants.hidepDialog();
                    Log.e("Failure", "Retrofit NetworkError" + t.getMessage());
                    Log.e("Retrofit NetworkError", call.request().url().toString());

                }
            });
        }



}

