package com.storm.Adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.storm.Activities.MainActivity;
import com.storm.Fragment.FullScreenDisplay;
import com.storm.Model.ImageViewHolder;
import com.storm.R;

import java.util.ArrayList;

public class ImageListAdapter extends RecyclerView.Adapter {


    public ArrayList<String> imageUrls;
    Context activityContext;

    public ImageListAdapter(ArrayList<String> extImageUrls, Context extActivityContext) {
        this.imageUrls = extImageUrls;
        activityContext = extActivityContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(activityContext).inflate(R.layout.single_item_view, viewGroup, false);

        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {

        final ImageViewHolder holder = (ImageViewHolder) viewHolder;

        holder.setImageDisplayView(imageUrls.get(i), activityContext);

        final int position = i;
        ViewCompat.setTransitionName(holder.itemView, activityContext.getString(R.string.image_transition));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FullScreenDisplay fullScreenDisplay = new FullScreenDisplay(activityContext, position, imageUrls);

                if (activityContext instanceof MainActivity) {
                    MainActivity mainActivity = (MainActivity) activityContext;
                    mainActivity.switchFragment(fullScreenDisplay,true,holder.itemView);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }


}
