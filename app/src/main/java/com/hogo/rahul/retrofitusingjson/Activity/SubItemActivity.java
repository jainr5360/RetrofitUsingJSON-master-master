package com.hogo.rahul.retrofitusingjson.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.hogo.rahul.retrofitusingjson.Adapter.MenuItemAdapter;
import com.hogo.rahul.retrofitusingjson.R;
import com.hogo.rahul.retrofitusingjson.RerofitModel.MyResponse;
import com.hogo.rahul.retrofitusingjson.Retrofit2.Utils;

import retrofit2.Callback;
import retrofit2.Response;

public class SubItemActivity extends AppCompatActivity {

    RecyclerView rvSubitemId;
    MenuItemAdapter itemAdapter;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_item);

        rvSubitemId = findViewById(R.id.rv_subitem);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        id = intent.getStringExtra("key");
        Toast.makeText(getApplicationContext(), "" + id, Toast.LENGTH_LONG).show();
        getDependentList(id);

    }

    private void getDependentList(String PrimaryID) {

        retrofit2.Call<MyResponse> call = null;
        call = Utils.getWebService().getData(id);
        Log.e("115 ", ": :" + call.request().url().toString());
        call.enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(retrofit2.Call<MyResponse> call, Response<MyResponse> response) {
                Log.e("DependentList", " : " + new GsonBuilder().create().toJson(response.body()));
                Log.e("DependentList", " : " + response.code());

                MyResponse myResponse = response.body();

                itemAdapter = new MenuItemAdapter(getApplicationContext(), myResponse.getUserdata());
                rvSubitemId.setAdapter(itemAdapter);
                rvSubitemId.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


            }

            @Override
            public void onFailure(retrofit2.Call<MyResponse> call, Throwable t) {

            }
        });
    }

}
