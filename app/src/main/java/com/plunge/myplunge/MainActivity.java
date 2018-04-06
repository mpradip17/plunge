package com.plunge.myplunge;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.plunge.myplunge.adapter.ServiceListAdapter;
import com.plunge.myplunge.helper.AppConstants;
import com.plunge.myplunge.helper.DatabaseHelper;
import com.plunge.myplunge.helper.RetrofitClient;
import com.plunge.myplunge.helper.SharedPreference;
import com.plunge.myplunge.helper.WebServicesAPI;
import com.plunge.myplunge.model.Datum;
import com.plunge.myplunge.model.DraftList;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {
    private WebServicesAPI mApiInterface;
    private static final String TAG = "MainActivity";
    private Context mContext;
    private Activity mActivity;
RecyclerView mRecyclerview;
RelativeLayout mErrorLayout;
ServiceListAdapter mAdapter;
    DatabaseHelper myDb;

   // private ArrayList<Da> mImageList = new ArrayList<String>();
   private List<Datum> mPageList;
    private List<Datum> mCoachDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        mActivity= MainActivity.this;
        findViewById();
        myDb = new DatabaseHelper(mContext);


        Cursor res = myDb.getAllData();
        if(res.getCount() == 0) {
            // show message
//AppConstants.showtoast(mContext,"No Record Found");
            getlist();
            return;

        }else {
            mCoachDatabase=new ArrayList<>();
            res.moveToFirst();
            Datum datum=new Datum();
            while(res.moveToNext()) {
                datum.setInventoryType(res.getString(1));
                datum.setCreatedAt(res.getString(2));
                datum.setArthiyaName(res.getString(3));
                mCoachDatabase.add(datum);
            }
            mAdapter = new ServiceListAdapter(mActivity, mContext, mCoachDatabase);
            LinearLayoutManager llm = new LinearLayoutManager(mContext);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            mRecyclerview.setLayoutManager(llm);
            mRecyclerview.setAdapter(mAdapter);

        }

    }




    private void findViewById() {
        mRecyclerview = (RecyclerView) findViewById(R.id.recycler_view);
        mErrorLayout = (RelativeLayout) findViewById(R.id.no_family_list);
    }

    private void getlist() {
        AppConstants.showProgres(mContext);
        mApiInterface = RetrofitClient.getClient().create(WebServicesAPI.class);
        Call<ResponseBody> call = mApiInterface.getlist("XMLHttpRequest", SharedPreference.getInstance().getValue(mContext, "header"));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                AppConstants.hidepDialog();
                Log.e("Retrofit NetworkSuccess", call.request().url().toString()+response.code());
                if (response.isSuccessful()) {
                    try {
                        String json = response.body().string();
                        mPageList=new ArrayList<>();
                        Gson gson = new Gson();
                        DraftList mPageResponse = (gson.fromJson(json, DraftList.class));
                        mPageList.addAll(mPageResponse.getData());
                        if (mPageList.size() > 0) {
                            mRecyclerview.setVisibility(View.VISIBLE);
                            mErrorLayout.setVisibility(View.GONE);
                            mAdapter = new ServiceListAdapter(mActivity, mContext, mPageList);
                            LinearLayoutManager llm = new LinearLayoutManager(mContext);
                            llm.setOrientation(LinearLayoutManager.VERTICAL);
                            mRecyclerview.setLayoutManager(llm);
                            mRecyclerview.setAdapter(mAdapter);
                        for (int i=0;i<mPageList.size();i++){
                            myDb.insertData(mPageList.get(i).getArthiyaName(),
                                    mPageList.get(i).getCreatedAt(),
                                    mPageList.get(i).getInventoryType());
                        }

                        } else {
                            mRecyclerview.setVisibility(View.GONE);
                            mErrorLayout.setVisibility(View.VISIBLE);
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
                        Log.e("Error",""+ response.code()+jObjError);

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
