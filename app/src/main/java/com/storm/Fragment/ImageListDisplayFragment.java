package com.storm.Fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.storm.Adapters.ImageListAdapter;
import com.storm.Model.DataConstant;
import com.storm.Model.Image;
import com.storm.Model.Result;
import com.storm.R;
import com.storm.Utilities.StoringUrl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressLint("ValidFragment")
public class ImageListDisplayFragment extends Fragment {

    RecyclerView imageDisplayList;
    Context activityContext;
    ImageListAdapter adapter;
    GridLayoutManager manager;
    ArrayList<String> imageUrlList;
    StoringUrl storingUrl;
    String query = "";
    HashMap<String, ArrayList<String>> saveImageCache;

    public ImageListDisplayFragment(Context extActivityContext) {
        this.activityContext = extActivityContext;
        saveImageCache = new HashMap<>();
        storingUrl = new StoringUrl(activityContext.getApplicationContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.image_list_display_fragment, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("STORM");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        postponeEnterTransition();

        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        imageDisplayList = view.findViewById(R.id.image_recycler_view);
        imageUrlList = new ArrayList<>();


        DataConstant dataConstant = new DataConstant();
        imageUrlList = dataConstant.getImageUrls();

        if (!query.isEmpty()) {

            imageUrlList = storingUrl.getImageUrl(query);
        }
        adapter = new ImageListAdapter(imageUrlList, activityContext);
        imageDisplayList.setAdapter(adapter);

        manager = new GridLayoutManager(activityContext, 2, GridLayoutManager.VERTICAL, false);
        imageDisplayList.setLayoutManager(manager);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        setHasOptionsMenu(true);
        getActivity().getMenuInflater().inflate(R.menu.fragment_menu, menu);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        setHasOptionsMenu(true);
        getActivity().getMenuInflater().inflate(R.menu.fragment_menu, menu);

        MenuItem searchImage = menu.findItem(R.id.search_image);

        SearchView searchURL = (SearchView) searchImage.getActionView();
        searchURL.setQueryHint("Search");

        searchURL.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                query = s.trim();
                ArrayList<String> getListFromShared;
                getListFromShared = storingUrl.getImageUrl(query);

                if (getListFromShared.size() > 0) {
                    adapter.imageUrls = getListFromShared;
                    adapter.notifyDataSetChanged();
                } else {
                    new GetQueryResult(activityContext, s.trim()).execute("https://api.unsplash.com/search/photos?page=1&client_id" +
                            "=6b07dd36c4542f784a7a43e395af60df4baf49d319fcafb55053974560b6e89e&per_page=12&query=" + s.toString().trim());
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.three:

                manager.setSpanCount(3);
                imageDisplayList.setLayoutManager(manager);
                adapter.notifyDataSetChanged();
                return true;

            case R.id.four:

                manager.setSpanCount(4);
                imageDisplayList.setLayoutManager(manager);
                adapter.notifyDataSetChanged();
                return true;

            default:

                manager.setSpanCount(2);
                imageDisplayList.setLayoutManager(manager);
                adapter.notifyDataSetChanged();
                return true;
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (!query.isEmpty()) {

            imageUrlList = storingUrl.getImageUrl(query);
            adapter.imageUrls = imageUrlList;
            adapter.notifyDataSetChanged();
        }
    }

    public class GetQueryResult extends AsyncTask<String, String, String> {


        ProgressDialog dialog;
        Context activityContext;
        String query;

        public GetQueryResult(Context context, String query) {
            this.activityContext = context;
            this.query = query;
            dialog = new ProgressDialog(context);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Loading");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            StringBuffer response = new StringBuffer();
            try {

                URL url = new URL(params[0]);

                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Content-Type", "application/json");
                con.setDoInput(true);
                con.connect();


                int responseCode = con.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) { // success
                    BufferedReader in = new BufferedReader(new InputStreamReader(
                            con.getInputStream()));
                    String inputLine;
                    response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                }

            } catch (UnknownHostException ex) {
                response.append("Network Issue");

            } catch (IOException e) {

                response.append("Input Output Error");
            }

            if (response != null)
                return response.toString().trim();

            return "No Result Found";
        }

        @Override
        protected void onPostExecute(String result) {

            if (dialog.isShowing())
                dialog.dismiss();


            if (result != null && result.length() > 20) {
                Gson gson = new Gson();

                Image image = gson.fromJson(result, Image.class);

                List<Result> results = image.results;

                imageUrlList = new ArrayList<>();
                for (Result loResult : results) {
                    imageUrlList.add(loResult.urls.getRegular().toString().trim() + ".jpeg");
                }

                storingUrl.setImageUrl(query.trim(), imageUrlList);

                adapter.imageUrls = imageUrlList;
                adapter.notifyDataSetChanged();

            } else {

                Toast.makeText(activityContext, result, Toast.LENGTH_LONG).show();
            }

            setRetainInstance(true);

        }

    }

}