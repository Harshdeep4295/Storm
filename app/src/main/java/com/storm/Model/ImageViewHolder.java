package com.storm.Model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.storm.Utilities.PicassoCaching;
import com.storm.R;

public class ImageViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageDisplayView;
    public String imageUrl;
    public Context activityContext;

    public ImageViewHolder(@NonNull View itemView) {
        super(itemView);

        imageDisplayView= (ImageView) itemView.findViewById(R.id.display_image_view);

    }

    public void setImageDisplayView(String extImageUrl, Context extActivityContext) {

        this.imageUrl=extImageUrl;
        this.activityContext=extActivityContext;

        Picasso picasso=PicassoCaching.getPicassoInstance(activityContext.getApplicationContext());
        picasso.get().load(imageUrl).placeholder(R.drawable.placeholder).into(imageDisplayView);

    }
}
