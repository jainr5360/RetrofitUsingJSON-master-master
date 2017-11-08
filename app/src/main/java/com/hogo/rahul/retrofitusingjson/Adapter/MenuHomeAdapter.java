package com.hogo.rahul.retrofitusingjson.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hogo.rahul.retrofitusingjson.Activity.Userdatum;
import com.hogo.rahul.retrofitusingjson.Database.AddToCartDB;
import com.hogo.rahul.retrofitusingjson.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by rohit on 7/18/16.
 */
public class MenuHomeAdapter extends RecyclerView.Adapter<MenuHomeAdapter.ViewHolder> {

    Context context;
    List<Userdatum> homeDetailsList = new ArrayList<>();
    LayoutInflater inflater;
    int categoryId;
    AddToCartDB addToCartDB;

    public MenuHomeAdapter(Context context, List<Userdatum> homeDetailsList1) {
        this.context = context;
        this.homeDetailsList = homeDetailsList1;
        inflater = LayoutInflater.from(context);
        addToCartDB = new AddToCartDB(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.menu_home_items_row, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final Userdatum details = homeDetailsList.get(position);
        Log.d("..>", "" + details);
        holder.tv.setText(details.getName());
        String url = details.getImg().toString();
        Picasso.with(context).load(url).into(holder.ivBgRow);

        boolean checkexit = addToCartDB.insertToCartTable(details.getId(), details.getName());

        if (checkexit == false) {

            Toast.makeText(context, "This Product Is Already Exit", Toast.LENGTH_SHORT).show();

        }
        if (checkexit == true) {
            Toast.makeText(context, "Product Added Successfully", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public int getItemCount() {
        return homeDetailsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        ImageView ivBgRow;

        public ViewHolder(View itemView) {
            super(itemView);


            tv = (TextView) itemView.findViewById(R.id.txtview_menu_home);
            ivBgRow = itemView.findViewById(R.id.iv_bg);
        }
    }
}
