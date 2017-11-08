package com.hogo.rahul.retrofitusingjson.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hogo.rahul.retrofitusingjson.R;
import com.hogo.rahul.retrofitusingjson.RerofitModel.MyResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rahul jain on 06-11-2017.
 */

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.ViewHolder> {

    Context context;
    List<MyResponse.UserdataBean> homeDetailsList = new ArrayList<>();
    LayoutInflater inflater;
    int categoryId;
    String urlitem;

    public MenuItemAdapter(Context context, List<MyResponse.UserdataBean> homeDetailsList1) {
        this.context = context;
        this.homeDetailsList = homeDetailsList1;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public MenuItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_item_layput, parent, false);
        MenuItemAdapter.ViewHolder holder = new MenuItemAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MenuItemAdapter.ViewHolder holder, int position) {
        final MyResponse.UserdataBean details = homeDetailsList.get(position);
        holder.tv.setText(details.getName());

    }

    @Override
    public int getItemCount() {
        return homeDetailsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv;


        public ViewHolder(View itemView) {
            super(itemView);

            tv = itemView.findViewById(R.id.tv_itemtitle);

        }
    }
}
