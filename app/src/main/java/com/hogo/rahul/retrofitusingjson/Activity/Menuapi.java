package com.hogo.rahul.retrofitusingjson.Activity;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

/**
 * Created by rahul on 14/10/17.
 */

public class Menuapi {

    public static final String KEY_BASE_URL = "http://biryanihouseuganda.com/api/app_controller/bh_menu/";
//    public static final String key = "bh_menu";

    // singlton object
    public static PostServie postServie = null;

    public static PostServie getService() {

        if (postServie == null) {

            // create only for once
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(KEY_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            postServie = retrofit.create(PostServie.class);
        }
        return postServie;
    }

    // Interface fo retrofit
    public interface PostServie {

        @POST(KEY_BASE_URL)
        Call<PostGsonModel> getPostList();
    }
}
