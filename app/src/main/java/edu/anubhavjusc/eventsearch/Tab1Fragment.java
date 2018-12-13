package edu.anubhavjusc.eventsearch;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class Tab1Fragment extends Fragment {

    TextView artistText;
    TextView venueText;
    TextView timeText;
    TextView categoryText;
    TextView priceText;
    TextView statusText;
    TextView buyAtText;
    TextView seatMapText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_fragment,container,false);

        artistText = view.findViewById(R.id.artistText);
        venueText = view.findViewById(R.id.venueText);
        timeText = view.findViewById(R.id.timeText);
        categoryText = view.findViewById(R.id.categoryText);
        priceText = view.findViewById(R.id.priceText);
        statusText = view.findViewById(R.id.statusText);
        buyAtText = view.findViewById(R.id.buyAtText);
        seatMapText = view.findViewById(R.id.seatMapText);

        String name = getActivity().getIntent().getStringExtra("event");
        try {
            JSONObject event = new JSONObject(name);
            try {
                artistText.setText(getActivity().getIntent().getStringExtra("artistName"));
                if(getActivity().getIntent().getStringExtra("artistName2")!=null)
                    artistText.append(" | "+getActivity().getIntent().getStringExtra("artistName2"));
                LinearLayout lt = view.findViewById(R.id.t1l1);
                lt.setVisibility(View.VISIBLE);
            }
            catch (Exception e)
            {
                LinearLayout lt = view.findViewById(R.id.t1l1);
                lt.setVisibility(View.GONE);
            }
            try {
                venueText.setText(event.getJSONObject("_embedded").getJSONArray("venues").getJSONObject(0).getString("name"));
                LinearLayout lt = view.findViewById(R.id.t1l2);
                lt.setVisibility(View.VISIBLE);
            }
            catch (Exception e)
            {
                LinearLayout lt = view.findViewById(R.id.t1l2);
                lt.setVisibility(View.GONE);
            }
            try {
                timeText.setText(event.getJSONObject("dates").getJSONObject("start").getString("localDate")
                        +" "+ event.getJSONObject("dates").getJSONObject("start").getString("localTime"));
                LinearLayout lt = view.findViewById(R.id.t1l3);
                lt.setVisibility(View.VISIBLE);
            }
            catch (Exception e)
            {
                LinearLayout lt = view.findViewById(R.id.t1l3);
                lt.setVisibility(View.GONE);
            }
            try {
                categoryText.setText(event.getJSONArray("classifications").getJSONObject(0).getJSONObject("segment").getString("name")
                        +" | " +event.getJSONArray("classifications").getJSONObject(0).getJSONObject("genre").getString("name"));
                LinearLayout lt = view.findViewById(R.id.t1l4);
                lt.setVisibility(View.VISIBLE);
            }
            catch (Exception e)
            {
                LinearLayout lt = view.findViewById(R.id.t1l4);
                lt.setVisibility(View.GONE);
            }
            try {
                priceText.setText("$"+event.getJSONArray("priceRanges").getJSONObject(0).getString("min")+
                        " - "+"$"+event.getJSONArray("priceRanges").getJSONObject(0).getString("max"));
                LinearLayout lt = view.findViewById(R.id.t1l5);
                lt.setVisibility(View.VISIBLE);
            }
            catch (Exception e)
            {
                LinearLayout lt = view.findViewById(R.id.t1l5);
                lt.setVisibility(View.GONE);
            }
            try {
                statusText.setText(event.getJSONObject("dates").getJSONObject("status").getString("code"));
                LinearLayout lt = view.findViewById(R.id.t1l6);
                lt.setVisibility(View.VISIBLE);
            }
            catch (Exception e)
            {
                LinearLayout lt = view.findViewById(R.id.t1l6);
                lt.setVisibility(View.GONE);
            }
            try {
                buyAtText.setClickable(true);
                buyAtText.setMovementMethod(LinkMovementMethod.getInstance());
                String text = "<a href='"+event.getString("url")+"'>Ticketmaster</a>";
                buyAtText.setText(Html.fromHtml(text));
                LinearLayout lt = view.findViewById(R.id.t1l7);
                lt.setVisibility(View.VISIBLE);
            }
            catch (Exception e)
            {
                LinearLayout lt = view.findViewById(R.id.t1l7);
                lt.setVisibility(View.GONE);
            }
            try {
                seatMapText.setClickable(true);
                seatMapText.setMovementMethod(LinkMovementMethod.getInstance());
                String text = "<a href='"+event.getJSONObject("seatmap").getString("staticUrl")+"'>View here</a>";
                seatMapText.setText(Html.fromHtml(text));
                LinearLayout lt = view.findViewById(R.id.t1l8);
                lt.setVisibility(View.VISIBLE);
            }
            catch (Exception e)
            {
                LinearLayout lt = view.findViewById(R.id.t1l8);
                lt.setVisibility(View.GONE);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }
}
