package com.storm.Utilities;

import android.content.Context;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

public class PicassoCaching {

    private static Picasso picassoInstance = null;

    private PicassoCaching(Context context) {

        File httpCacheDirectory = new File(context.getCacheDir(), "picasso-cache");
        Cache cache = new Cache(httpCacheDirectory, 15 * 1024 * 1024);

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder().cache(cache);
        picassoInstance = new Picasso.Builder(context).downloader(new OkHttp3Downloader(okHttpClientBuilder.build())).build();

    }

    public static Picasso getPicassoInstance (Context context) {

        if (picassoInstance == null) {

            new PicassoCaching(context);
            return picassoInstance;
        }

        return picassoInstance;
    }
}
