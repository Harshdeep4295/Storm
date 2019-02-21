package com.storm.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.transition.TransitionInflater;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.storm.Adapters.FullScreenPagerAdapter;
import com.storm.R;
import com.storm.Utilities.PicassoCaching;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class FullScreenDisplay extends Fragment {

    private FullScreenPagerAdapter adapter;
    private ViewPager mViewPager;
    private int position;
    private static ArrayList<String> ImageUrls = new ArrayList<>();
    private Context activityContext;

    public FullScreenDisplay(Context extActivityContext, int Position, ArrayList<String> ImageUrls) {
        this.activityContext = extActivityContext;
        this.position = Position;
        FullScreenDisplay.ImageUrls = ImageUrls;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_full_screen, container, false);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postponeEnterTransition();
        setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));
        setSharedElementReturnTransition(null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        adapter = new FullScreenPagerAdapter(getChildFragmentManager(), ImageUrls);

        mViewPager = (ViewPager) view.findViewById(R.id.container);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(position);

    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_IMAGE_INDEX = "image__index";

        public PlaceholderFragment() {
        }


        public static FullScreenDisplay.PlaceholderFragment newInstance(int imageIndex) {

            FullScreenDisplay.PlaceholderFragment fragment = new FullScreenDisplay.PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_IMAGE_INDEX, imageIndex);
            fragment.setArguments(args);
            return fragment;

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_full_screen_image, container, false);

            Bundle bundle = getArguments();
            int index = bundle.getInt(ARG_IMAGE_INDEX);

            ImageView imageView = (ImageView) rootView.findViewById(R.id.section_label);
            Picasso picasso = PicassoCaching.getPicassoInstance(getContext().getApplicationContext());
            picasso.get().load(ImageUrls.get(index)).placeholder(R.drawable.placeholder).into(imageView, new Callback() {
                @Override
                public void onSuccess() {
                    getParentFragment().startPostponedEnterTransition();
                }

                @Override
                public void onError(Exception e) {
                    getParentFragment().startPostponedEnterTransition();
                }

            });

            return rootView;
        }

    }

}
