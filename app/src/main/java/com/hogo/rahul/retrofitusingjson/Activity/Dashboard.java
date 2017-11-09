package com.hogo.rahul.retrofitusingjson.Activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.hogo.rahul.retrofitusingjson.Adapter.MenuHomeAdapter;
import com.hogo.rahul.retrofitusingjson.Database.AddToCartDB;
import com.hogo.rahul.retrofitusingjson.R;
import com.hogo.rahul.retrofitusingjson.Retrofit2.RestJsonClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class Dashboard extends AppCompatActivity {

    ArrayList<Userdatum> mlist = new ArrayList<>();
    MenuHomeAdapter adapter;
    RecyclerView rvMenu;
    String name;
    String image;
    Context context = this;
    String id;
    ArrayList<Userdatum> listArrlist;
    AddToCartDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        rvMenu = (RecyclerView) findViewById(R.id.rv_menu);
        db = new AddToCartDB(context);
        getData();

        if (RestJsonClient.isNetworkAvailable1(Dashboard.this))
            getData();
        else
            getDatabase();

//        getData();

        //
        rvMenu.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), rvMenu, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                String id = mlist.get(position).getId();

                Intent intent = new Intent(getApplicationContext(), SubItemActivity.class);
                intent.putExtra("key", id);

                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void getData() {

        retrofit2.Call<PostGsonModel> postGsonModelCall = Menuapi.getService().getPostList();
        postGsonModelCall.enqueue(new Callback<PostGsonModel>() {
            @Override
            public void onResponse(retrofit2.Call<PostGsonModel> call, Response<PostGsonModel> response) {

                PostGsonModel gsonModel = response.body();
                Toast.makeText(Dashboard.this, "sucess" + gsonModel, Toast.LENGTH_SHORT).show();
                mlist = (ArrayList<Userdatum>) gsonModel.getUserdata();
                try {
                    if (!mlist.isEmpty()) {

                        for (int i = 0; i < mlist.size(); i++) {

//                            Boolean checkexit = db.insertToCartTable(mlist.get(i).getId().toString(),
//                                    mlist.get(i).getName().toString(), mlist.get(i).getImg().toString()
//                            );
//                            if (checkexit == false) {
//
//                                Toast.makeText(context, "This Product Is Already Exit", Toast.LENGTH_SHORT).show();
//
//                            }
//                            if (checkexit == true) {
//                                Toast.makeText(context, "Product Added Successfully", Toast.LENGTH_SHORT).show();
//                            }

                            adapter = new MenuHomeAdapter(getApplicationContext(), mlist);
                            rvMenu.setAdapter(adapter);
                            rvMenu.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "List is empty", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<PostGsonModel> call, Throwable t) {

                Toast.makeText(Dashboard.this, "error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    void getDatabase() {

        Cursor cursor = db.queueArticlesDetails();

        for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()) {

            Userdatum list = new Userdatum();
            list.setId(cursor.getString(cursor.getColumnIndex(AddToCartDB.ITEM_ID)));
            list.setName(cursor.getString(cursor.getColumnIndex(AddToCartDB.ITEM_NAME)));
//            list.setImg(cursor.getString(cursor.getColumnIndex(AddToCartDB.ITEM_IMG)));
            mlist.add(list);
        }

        try {
//            customImageAdapter = new CustomImageAdapter(getApplicationContext(), listArrlist);
//            listView.setAdapter(customImageAdapter);


            adapter = new MenuHomeAdapter(getApplicationContext(), mlist);
            rvMenu.setAdapter(adapter);
            rvMenu.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static interface ClickListener {

        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    private class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        GestureDetector detector;
        Dashboard.ClickListener clickListener;

        public RecyclerTouchListener(Context context, RecyclerView view, final ClickListener listener) {

            this.clickListener = listener;
            detector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    super.onLongPress(e);

                    View child = rvMenu.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {

                        clickListener.onLongClick(child, rvMenu.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && detector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

}
