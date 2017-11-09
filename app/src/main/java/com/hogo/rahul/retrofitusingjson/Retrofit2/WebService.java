package com.hogo.rahul.retrofitusingjson.Retrofit2;

import com.hogo.rahul.retrofitusingjson.RerofitModel.LoginModel;
import com.hogo.rahul.retrofitusingjson.RerofitModel.MyResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;


public interface WebService {

    @POST("api/app_controller/bh_menu_item/{itemId}")
    Call<MyResponse> getData(@Path("itemId") String itemId);

    @Multipart
    @POST("app_controller/login")
    Call<LoginModel> getDataLogin(@Part("email") String email,
                                  @Part("password") String password,
                                  @Part("fcm_token") String userId);


}
