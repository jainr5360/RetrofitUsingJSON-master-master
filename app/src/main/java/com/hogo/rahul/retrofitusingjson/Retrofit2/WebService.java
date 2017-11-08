package com.hogo.rahul.retrofitusingjson.Retrofit2;

import com.hogo.rahul.retrofitusingjson.RerofitModel.LoginModel;
import com.hogo.rahul.retrofitusingjson.RerofitModel.MyResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface WebService {

    @POST("api/app_controller/bh_menu_item/{itemId}")
    Call<MyResponse> getData(@Path("itemId") String itemId);

    @POST("app_controller/login")
//    @FormUrlEncoded
    Call<LoginModel> getDataLogin(@Field("email") String email,
                                  @Field("password") String password,
                                  @Field("fcm_token") String userId);


}
