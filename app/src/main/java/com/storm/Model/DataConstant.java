package com.storm.Model;

import java.util.ArrayList;

public class DataConstant {

    public ArrayList<String> imageUrls;


    public ArrayList<String> getImageUrls()
    {
        imageUrls=new ArrayList<>();

        imageUrls.add("https://images.unsplash.com/photo-1522827218865-6f92388c094a?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&ixid=eyJhcHBfaWQiOjU3MjQ1fQ");
        /*https://api.androidhive.info/images/sample.jpg");
        imageUrls.add("https://api.androidhive.info/images/sample.jpg");
        imageUrls.add("https://api.androidhive.info/images/sample.jpg");
        imageUrls.add("https://api.androidhive.info/images/sample.jpg");
        imageUrls.add("https://api.androidhive.info/images/sample.jpg");*/

        return imageUrls;
    }

}
