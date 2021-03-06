package com.storm.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.transition.TransitionInflater;
import android.support.v4.app.Fragment;
import android.support.v4.app.SharedElementCallback;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.storm.Adapters.FullScreenPagerAdapter;
import com.storm.R;
import com.storm.Utilities.PicassoCaching;
import com.storm.Utilities.StoringData;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class FullScreenDisplay extends Fragment {

    private FullScreenPagerAdapter adapter;
    private ViewPager mViewPager;
    private int position;
    private static ArrayList<String> ImageUrls = new ArrayList<>();
    private Context activityContext;
    private static StoringData storingData;

    public FullScreenDisplay(Context extActivityContext, int Position, ArrayList<String> ImageUrls) {
        this.activityContext = extActivityContext;
        this.position = Position;
        FullScreenDisplay.ImageUrls = ImageUrls;
        storingData = new StoringData(extActivityContext);

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
        // Transition is done here. Using Android Default Animation FADE

        postponeEnterTransition();
        setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(storingData.getAnimation()).setDuration(800));
        setSharedElementReturnTransition(TransitionInflater.from(getContext()).inflateTransition(storingData.getAnimation()).setDuration(800));

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
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            postponeEnterTransition();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_full_screen_image, container, false);

            Bundle bundle = getArguments();
            int index = bundle.getInt(ARG_IMAGE_INDEX);

            ImageView imageView = (ImageView) rootView.findViewById(R.id.display_image_view);
            imageView.setTransitionName(getActivity().getApplicationContext().getString(R.string.image_transition)+index);

            Picasso picasso = PicassoCaching.getPicassoInstance(getContext().getApplicationContext());
            picasso.get().load(ImageUrls.get(index)).placeholder(R.drawable.placeholder).into(imageView, new Callback() {
                @Override
                public void onSuccess() {
                    startPostponedEnterTransition();
                }

                @Override
                public void onError(Exception e) {
                    startPostponedEnterTransition();
                }

            });

            return rootView;
        }

    }

}
