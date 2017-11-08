package com.hogo.rahul.retrofitusingjson.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.hogo.rahul.retrofitusingjson.Adapter.MenuHomeAdapter;
import com.hogo.rahul.retrofitusingjson.Adapter.MenuItemAdapter;
import com.hogo.rahul.retrofitusingjson.R;
import com.hogo.rahul.retrofitusingjson.RerofitModel.LoginModel;
import com.hogo.rahul.retrofitusingjson.RerofitModel.MyResponse;
import com.hogo.rahul.retrofitusingjson.Retrofit2.Utils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    Button btnSubmit;
    EditText etEmail, etPassword;
    String email, password, fcm;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        btnSubmit = findViewById(R.id.btn_submit);
        etPassword = findViewById(R.id.et_password);
        etEmail = findViewById(R.id.et_email);

        email = etEmail.getText().toString();
        password = etPassword.getText().toString();
        fcm = "ghsdbjfsdbsdfjsb";

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPost(email, password, fcm);
            }
        });

    }

//    public void sendPost(String title, String body) {
//
//        mAPIService.savePost(title, body, 1).enqueue(new Callback<Post>() {
//            private Call<LoginModel> call;
//            private Response<LoginModel> response;
//
//            @Override
//            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
//                this.call = call;
//                this.response = response;
//
//                if (response.isSuccessful()) {
//                    showResponse(response.body().toString());
//                    Log.i(TAG, "post submitted to API." + response.body().toString());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Post> call, Throwable t) {
//                Log.e(TAG, "Unable to submit post to API.");
//            }
//        });
//    }

//    public void showResponse(String response) {
//        if (mResponseTv.getVisibility() == View.GONE) {
//            mResponseTv.setVisibility(View.VISIBLE);
//        }
//        mResponseTv.setText(response);
//    }

    private void sendPost(String email, String password, String fcm) {

        retrofit2.Call<LoginModel> call = null;
        call = Utils.getWebService().getDataLogin(email, password, fcm);
        Log.e("115 ", ": :" + call.request().url().toString());
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(retrofit2.Call<LoginModel> call, Response<LoginModel> response) {

                Log.e("DependentList", " : " + new GsonBuilder().create().toJson(response.body()));
                Log.e("DependentList", " : " + response.code());

                LoginModel myResponse = response.body();


//                rvSubitemId.setAdapter(itemAdapter);
//                rvSubitemId.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


            }

            @Override
            public void onFailure(retrofit2.Call<LoginModel> call, Throwable t) {

            }
        });
    }


}
