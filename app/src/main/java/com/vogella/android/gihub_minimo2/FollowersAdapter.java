package com.vogella.android.gihub_minimo2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class FollowersAdapter extends RecyclerView.Adapter<FollowersAdapter.MyViewHolder>{
    private List<Followers> values = new ArrayList<>();
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        ImageView imageView;

        public MyViewHolder(View v) {
            super(v);
            name = (TextView)v.findViewById(R.id.name);
            imageView = (ImageView)itemView.findViewById(R.id.followerPicture);
        }
    }

    public void add(int position, Followers item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public FollowersAdapter(List<Followers> myDataset, Context context) {
        values = myDataset;
        this.context = context;
    }

    @NonNull
    @Override
    public FollowersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.recycleview_layout, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Followers follower = values.get(position);
        holder.name.setText(follower.getLogin());
        Glide.with(this.context).load(values.get(position).getAvatar_url()).into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return values.size();
    }


}
