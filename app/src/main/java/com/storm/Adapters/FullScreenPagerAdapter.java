package com.storm.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.storm.Fragment.FullScreenDisplay;

import java.util.ArrayList;

public class FullScreenPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<String> imageUrls;

    public FullScreenPagerAdapter(FragmentManager fm, ArrayList<String> extImageUrls) {

        super(fm);
        this.imageUrls=extImageUrls;

    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return FullScreenDisplay.PlaceholderFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return imageUrls.size();
    }
}