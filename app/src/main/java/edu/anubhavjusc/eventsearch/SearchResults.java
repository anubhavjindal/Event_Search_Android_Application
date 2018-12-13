package edu.anubhavjusc.eventsearch;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchResults extends AppCompatActivity {

    String jsonResult="";
    private List<SearchItem> lstItem;
    private RecyclerView recyclerView;
    private LinearLayout progress;
    private ProgressBar mProgressBar;
    private Handler mHandler = new Handler();
    private int mProgressStatus = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        RelativeLayout myrel = findViewById(R.id.searchResult);
        final TextView txt = findViewById(R.id.noResultError);
        recyclerView = findViewById(R.id.recyclerView);
        progress = findViewById(R.id.progressLayout);
        mProgressBar = findViewById(R.id.progressBar1);
        progress = findViewById(R.id.progressLayout);

        progress.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
        txt.setVisibility(View.GONE);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(mProgressStatus<100){
                    mProgressStatus++;
                    android.os.SystemClock.sleep(10);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mProgressBar.setProgress(mProgressStatus);
                        }
                    });
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            JSONObject searchResult = new JSONObject(jsonResult);
                            JSONObject embedded = searchResult.getJSONObject("_embedded");
                            JSONArray events = embedded.getJSONArray("events");
                            if(events!=null)
                                recyclerView.setVisibility(View.VISIBLE);
                            if(events==null ||events.length()<1 || events.length()==0)
                                txt.setVisibility(View.VISIBLE);
                        }
                        catch (Exception e)
                        {
                            txt.setVisibility(View.VISIBLE);
                        }

                        progress.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }).start();

        lstItem = new ArrayList<>();

        jsonResult = getIntent().getStringExtra("jsonResult");
        try {
            txt.setVisibility(View.GONE);

            JSONObject searchResult = new JSONObject(jsonResult);
            JSONObject embedded = searchResult.getJSONObject("_embedded");
            JSONArray events = embedded.getJSONArray("events");

            if(events!=null)
            {
                Log.d("Event Data", events.toString());
                for(int i=0;i<events.length();i++)
                {
                    SearchItem item = new SearchItem();
                    JSONObject eventObj = events.getJSONObject(i);
                    item.setTitle(eventObj.getString("name"));
                    item.setEventId(eventObj.getString("id"));
                    item.setCategory(eventObj.getJSONArray("classifications").getJSONObject(0).getJSONObject("segment").getString("name"));
                    JSONObject eventDate = eventObj.getJSONObject("dates").getJSONObject("start");
                    item.setDatetime(eventDate.getString("localDate")+" "+ eventDate.getString("localTime"));
                    item.setLocation(eventObj.getJSONObject("_embedded").getJSONArray("venues").getJSONObject(0).getString("name"));
                    try{
                        item.setArtist(eventObj.getJSONObject("_embedded").getJSONArray("attractions").getJSONObject(0).getString("name"));

                    }
                    catch (Exception e){
                        continue;
                    }
                    try{
                        if(eventObj.getJSONObject("_embedded").getJSONArray("attractions").length()>1)
                        item.setArtist2(eventObj.getJSONObject("_embedded").getJSONArray("attractions").getJSONObject(1).getString("name"));
                    else
                        item.setArtist2("");
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    lstItem.add(item);
                }
            }

        }
        catch (JSONException e) {

//            txt.setVisibility(View.VISIBLE);
        }

        setupRecyclerView(lstItem);
    }


    private void setupRecyclerView(List<SearchItem> lstItem){

        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,lstItem);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);

    }


}