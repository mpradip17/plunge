package com.plunge.myplunge.helper;



import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by CSS on 8/27/2017.
 */

public interface WebServicesAPI {
    @GET("get_draft_order")
    Call<ResponseBody> getlist(@Header("X-Requested-With") String request, @Header("Authorization") String header);
    @POST("user_login")
    @FormUrlEncoded
    Call<ResponseBody> loginoauth(@Header("X-Requested-With") String request, @FieldMap Map<String, String> params);
}
