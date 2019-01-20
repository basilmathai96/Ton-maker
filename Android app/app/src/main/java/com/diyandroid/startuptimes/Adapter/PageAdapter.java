package com.diyandroid.startuptimes.Adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.diyandroid.startuptimes.Class.ListItem;
import com.diyandroid.startuptimes.R;

import java.util.List;
import java.util.Random;

public class PageAdapter extends RecyclerView.Adapter<PageAdapter.MyViewHolder> {

    private List<ListItem> moviesList;
    View itemView;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, message, timestamp;
        ImageView featured_image;

        ProgressBar truthnessBar;

        MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title_in);
            message = (TextView) view.findViewById(R.id.message_in);
            featured_image = (ImageView) view.findViewById(R.id.featured_image);
            timestamp = (TextView) view.findViewById(R.id.timestamp_in);

            truthnessBar = (ProgressBar) view.findViewById(R.id.truthnessBar);
        }
    }


    public PageAdapter(List<ListItem> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_items_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ListItem listItem = moviesList.get(position);
        holder.title.setText(listItem.getTitle());
        holder.message.setText(listItem.getDescription());
        holder.timestamp.setText(listItem.getTimestamp());

//        holder.featured_image.setBackgroundColor(getRandomColor());

        holder.truthnessBar.setProgress(listItem.getTruthness());
        Glide.with(itemView)
                .load(listItem.getFeatured_image())
                .into(holder.featured_image);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    private int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}