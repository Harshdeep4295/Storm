package com.storm.Adapters;


import android.annotation.SuppressLint;
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {

        final ImageViewHolder holder = (ImageViewHolder) viewHolder;

        holder.setImageDisplayView(imageUrls.get(i), activityContext);

        String transitionName = activityContext.getApplicationContext().getString(R.string.image_transition)+i;

        holder.imageDisplayView.setTransitionName(transitionName);
        ViewCompat.setTransitionName(holder.imageDisplayView, transitionName);

        holder.imageDisplayView.setHasTransientState(true);
        holder.imageDisplayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FullScreenDisplay fullScreenDisplay = new FullScreenDisplay(activityContext, i, imageUrls);

                if (activityContext instanceof MainActivity) {
                    MainActivity mainActivity = (MainActivity) activityContext;
                    mainActivity.switchFragment(fullScreenDisplay, true, holder.imageDisplayView);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }


}
