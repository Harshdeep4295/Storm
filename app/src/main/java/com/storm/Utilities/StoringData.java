package com.storm.Utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class StoringData {

    private SharedPreferences sharedPreferences;
    private static String KEY_ANIMATION = "animation_key";

    public StoringData(Context ctx) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public void setImageUrl(String KEY, ArrayList<String> imageUrl) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String values = gson.toJson(imageUrl);

        editor.putString(KEY, values);
        editor.commit();
    }

    public ArrayList<String> getImageUrl(String KEY) {

        ArrayList<String> urlsList = new ArrayList<>();

        if (sharedPreferences.contains(KEY)) {
            String urls = sharedPreferences.getString(KEY, "");

            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<String>>() {
            }.getType();
            urlsList = gson.fromJson(urls, type);

        }

        return urlsList;

    }

    public void setAnimation(int id) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ANIMATION, id);
        editor.commit();
    }

    public int getAnimation() {
        return sharedPreferences.getInt(KEY_ANIMATION, android.R.transition.fade);
    }
}
