package edu.anubhavjusc.eventsearch;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;


public class Tab4Fragment extends Fragment {

    private Spinner categorySpinner;
    private Spinner orderSpinner;
    private TextView eventName1,eventName2,eventName3,eventName4,eventName5;
    private TextView artistName1,artistName2,artistName3,artistName4,artistName5;
    private TextView dateTime1,dateTime2,dateTime3,dateTime4,dateTime5;
    private TextView type1,type2,type3,type4,type5;

    String data[][] = new String[5][4];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab4_fragment,container,false);

        eventName1 = view.findViewById(R.id.eventName1);
        eventName2 = view.findViewById(R.id.eventName2);
        eventName3 = view.findViewById(R.id.eventName3);
        eventName4 = view.findViewById(R.id.eventName4);
        eventName5 = view.findViewById(R.id.eventName5);
        artistName1 = view.findViewById(R.id.artistName1);
        artistName2 = view.findViewById(R.id.artistName2);
        artistName3 = view.findViewById(R.id.artistName3);
        artistName4 = view.findViewById(R.id.artistName4);
        artistName5 = view.findViewById(R.id.artistName5);
        dateTime1 = view.findViewById(R.id.dateTime1);
        dateTime2 = view.findViewById(R.id.dateTime2);
        dateTime3 = view.findViewById(R.id.dateTime3);
        dateTime4 = view.findViewById(R.id.dateTime4);
        dateTime5 = view.findViewById(R.id.dateTime5);
        type1 = view.findViewById(R.id.type1);
        type2 = view.findViewById(R.id.type2);
        type3 = view.findViewById(R.id.type3);
        type4 = view.findViewById(R.id.type4);
        type5 = view.findViewById(R.id.type5);

        categorySpinner = view.findViewById(R.id.categorySortSpinner);
        orderSpinner = view.findViewById(R.id.orderSortSpinner);

        LinearLayout lt = view.findViewById(R.id.sortingLayout);
        ScrollView sv = view.findViewById(R.id.cardScroll);
        TextView tv = view.findViewById(R.id.noRecordText);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.sortingCategories, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),R.array.sortingOrder, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        categorySpinner.setAdapter(adapter);
        orderSpinner.setAdapter(adapter2);

        try {

            sv.setVisibility(View.VISIBLE);
            lt.setVisibility(View.VISIBLE);
            tv.setVisibility(View.GONE);

            String name = getActivity().getIntent().getStringExtra("upcoming");

            JSONObject upcoming = new JSONObject(name);

            data[0][0] = "<a href='"+(upcoming.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("event")
                    .getJSONObject(0).getString("uri"))+"'>"+(upcoming.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("event")
                    .getJSONObject(0).getString("displayName"))+"</a>";
            data[1][0] = "<a href='"+(upcoming.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("event")
                    .getJSONObject(1).getString("uri"))+"'>"+(upcoming.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("event")
                    .getJSONObject(1).getString("displayName"))+"</a>";
            data[2][0] = "<a href='"+(upcoming.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("event")
                    .getJSONObject(2).getString("uri"))+"'>"+(upcoming.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("event")
                    .getJSONObject(2).getString("displayName"))+"</a>";
            data[3][0] = "<a href='"+(upcoming.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("event")
                    .getJSONObject(3).getString("uri"))+"'>"+(upcoming.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("event")
                    .getJSONObject(3).getString("displayName"))+"</a>";
            data[4][0] = "<a href='"+(upcoming.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("event")
                    .getJSONObject(4).getString("uri"))+"'>"+(upcoming.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("event")
                    .getJSONObject(4).getString("displayName"))+"</a>";


            data[0][1]=(upcoming.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("event")
                    .getJSONObject(0).getJSONArray("performance").getJSONObject(0).getString("displayName"));
            data[1][1]=(upcoming.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("event")
                    .getJSONObject(1).getJSONArray("performance").getJSONObject(0).getString("displayName"));
            data[2][1]=(upcoming.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("event")
                    .getJSONObject(2).getJSONArray("performance").getJSONObject(0).getString("displayName"));
            data[3][1]=(upcoming.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("event")
                    .getJSONObject(3).getJSONArray("performance").getJSONObject(0).getString("displayName"));
            data[4][1]=(upcoming.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("event")
                    .getJSONObject(4).getJSONArray("performance").getJSONObject(0).getString("displayName"));

            if(!upcoming.getJSONObject("resultsPage").getJSONObject("results")
                    .getJSONArray("event").getJSONObject(0).getJSONObject("start").getString("time").equals("null"))
            {
                data[0][2]=(upcoming.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("event").getJSONObject(0)
                        .getJSONObject("start").getString("date")+" "+upcoming.getJSONObject("resultsPage").getJSONObject("results")
                        .getJSONArray("event").getJSONObject(0).getJSONObject("start").getString("time"));
            }
            else
            {
                data[0][2]=(upcoming.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("event").getJSONObject(0)
                        .getJSONObject("start").getString("date"));
            }
            if(!upcoming.getJSONObject("resultsPage").getJSONObject("results")
                    .getJSONArray("event").getJSONObject(1).getJSONObject("start").getString("time").equals("null"))
            {
                data[1][2]=(upcoming.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("event").getJSONObject(1)
                        .getJSONObject("start").getString("date")+" "+upcoming.getJSONObject("resultsPage").getJSONObject("results")
                        .getJSONArray("event").getJSONObject(1).getJSONObject("start").getString("time"));
            }
            else
            {
                data[1][2]=(upcoming.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("event").getJSONObject(1)
                        .getJSONObject("start").getString("date"));
            }
            if(!upcoming.getJSONObject("resultsPage").getJSONObject("results")
                    .getJSONArray("event").getJSONObject(2).getJSONObject("start").getString("time").equals("null"))
            {
                data[2][2]=(upcoming.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("event").getJSONObject(2)
                        .getJSONObject("start").getString("date")+" "+upcoming.getJSONObject("resultsPage").getJSONObject("results")
                        .getJSONArray("event").getJSONObject(2).getJSONObject("start").getString("time"));
            }
            else
            {
                data[2][2]=(upcoming.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("event").getJSONObject(2)
                        .getJSONObject("start").getString("date"));
            }
            if(!upcoming.getJSONObject("resultsPage").getJSONObject("results")
                    .getJSONArray("event").getJSONObject(3).getJSONObject("start").getString("time").equals("null"))
            {
                data[3][2]=(upcoming.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("event").getJSONObject(3)
                        .getJSONObject("start").getString("date")+" "+upcoming.getJSONObject("resultsPage").getJSONObject("results")
                        .getJSONArray("event").getJSONObject(3).getJSONObject("start").getString("time"));
            }
            else
            {
                data[3][2]=(upcoming.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("event").getJSONObject(3)
                        .getJSONObject("start").getString("date"));
            }
            if(!upcoming.getJSONObject("resultsPage").getJSONObject("results")
                    .getJSONArray("event").getJSONObject(4).getJSONObject("start").getString("time").equals("null"))
            {
                data[4][2]=(upcoming.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("event").getJSONObject(4)
                        .getJSONObject("start").getString("date")+" "+upcoming.getJSONObject("resultsPage").getJSONObject("results")
                        .getJSONArray("event").getJSONObject(4).getJSONObject("start").getString("time"));
            }
            else
            {
                data[4][2]=(upcoming.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("event").getJSONObject(4)
                        .getJSONObject("start").getString("date"));
            }
//            data[1][2]=(upcoming.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("event").getJSONObject(1)
//                    .getJSONObject("start").getString("date")+" "+upcoming.getJSONObject("resultsPage").getJSONObject("results")
//                    .getJSONArray("event").getJSONObject(0).getJSONObject("start").getString("time"));
//            data[2][2]=(upcoming.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("event").getJSONObject(2)
//                    .getJSONObject("start").getString("date")+" "+upcoming.getJSONObject("resultsPage").getJSONObject("results")
//                    .getJSONArray("event").getJSONObject(0).getJSONObject("start").getString("time"));
//            data[3][2]=(upcoming.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("event").getJSONObject(3)
//                    .getJSONObject("start").getString("date")+" "+upcoming.getJSONObject("resultsPage").getJSONObject("results")
//                    .getJSONArray("event").getJSONObject(0).getJSONObject("start").getString("time"));
//            data[4][2]=(upcoming.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("event").getJSONObject(4)
//                    .getJSONObject("start").getString("date")+" "+upcoming.getJSONObject("resultsPage").getJSONObject("results")
//                    .getJSONArray("event").getJSONObject(0).getJSONObject("start").getString("time"));

            data[0][3]=("Type: "+upcoming.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("event").getJSONObject(0).getString("type"));
            data[1][3]=("Type: "+upcoming.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("event").getJSONObject(1).getString("type"));
            data[2][3]=("Type: "+upcoming.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("event").getJSONObject(2).getString("type"));
            data[3][3]=("Type: "+upcoming.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("event").getJSONObject(3).getString("type"));
            data[4][3]=("Type: "+upcoming.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("event").getJSONObject(4).getString("type"));

            setView();

        } catch (JSONException e) {

            sv.setVisibility(View.GONE);
            lt.setVisibility(View.GONE);
            tv.setVisibility(View.VISIBLE);
        }

        orderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sort(categorySpinner.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                try{
                    sort(position);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });

        return view;
    }


    public static void removeUnderlines(Spannable p_Text) {
        URLSpan[] spans = p_Text.getSpans(0, p_Text.length(), URLSpan.class);

        for(URLSpan span:spans) {
            int start = p_Text.getSpanStart(span);
            int end = p_Text.getSpanEnd(span);
            p_Text.removeSpan(span);
            span = new URLSpanNoUnderline(span.getURL());
            p_Text.setSpan(span, start, end, 0);
        }
    }

    private void sort(int position) {
        if(position==0)
        {
                orderSpinner.setEnabled(false);
                Arrays.sort(data, new Comparator<String[]>() {
                    @Override
                    public int compare(String[] o1, String[] o2) {
                        return o1[2].compareTo(o2[2]);
                    }
                });

            setView();
        }

        if(position==1)
        {
            if(orderSpinner.getSelectedItem().toString().equals("Ascending")) {

                orderSpinner.setEnabled(true);
                Arrays.sort(data, new Comparator<String[]>() {
                    @Override
                    public int compare(String[] o1, String[] o2) {
                        return (o1[0].split(">")[1]).compareTo(o2[0].split(">")[1]);
                    }
                });
            }
            else if(orderSpinner.getSelectedItem().toString().equals("Descending")) {

                orderSpinner.setEnabled(true);
                Arrays.sort(data, new Comparator<String[]>() {
                    @Override
                    public int compare(String[] o1, String[] o2) {
                        return (o2[0].split(">")[1]).compareTo(o1[0].split(">")[1]);
                    }
                });
            }

            setView();
        }
        if(position==2)
        {
            if(orderSpinner.getSelectedItem().toString().equals("Ascending")) {

                orderSpinner.setEnabled(true);
                Arrays.sort(data, new Comparator<String[]>() {
                    @Override
                    public int compare(String[] o1, String[] o2) {
                        return o1[1].compareTo(o2[1]);

                    }
                });
            }
            else if(orderSpinner.getSelectedItem().toString().equals("Descending")) {

                orderSpinner.setEnabled(true);
                Arrays.sort(data, new Comparator<String[]>() {
                    @Override
                    public int compare(String[] o1, String[] o2) {
                        return o2[1].compareTo(o1[1]);

                    }
                });
            }

            setView();
        }
        if(position==3)
        {
            if(orderSpinner.getSelectedItem().toString().equals("Ascending")) {

                orderSpinner.setEnabled(true);
                Arrays.sort(data, new Comparator<String[]>() {
                    @SuppressLint("SimpleDateFormat")
                    @Override
                    public int compare(String[] o1, String[] o2) {
                        return o1[2].compareTo(o2[2]);
                    }
                });
            }
            else if(orderSpinner.getSelectedItem().toString().equals("Descending")) {

                orderSpinner.setEnabled(true);
                Arrays.sort(data, new Comparator<String[]>() {
                    @SuppressLint("SimpleDateFormat")
                    @Override
                    public int compare(String[] o1, String[] o2) {
                        return o2[2].compareTo(o1[2]);
                    }
                });
            }

            setView();
        }
        if(position==4)
        {
            if(orderSpinner.getSelectedItem().toString().equals("Ascending")) {

                orderSpinner.setEnabled(true);
                Arrays.sort(data, new Comparator<String[]>() {
                    @Override
                    public int compare(String[] o1, String[] o2) {
                        return o1[3].compareTo(o2[3]);
                    }
                });
            }
            else if(orderSpinner.getSelectedItem().toString().equals("Descending")) {

                orderSpinner.setEnabled(true);
                Arrays.sort(data, new Comparator<String[]>() {
                    @Override
                    public int compare(String[] o1, String[] o2) {
                        return o2[3].compareTo(o1[3]);
                    }
                });
            }
            setView();
        }
    }

    private void setView() {

//        buyAtText.setClickable(true);
//        buyAtText.setMovementMethod(LinkMovementMethod.getInstance());
//        String text = "<a href='"+event.getString("url")+"'>Ticketmaster</a>";
//        buyAtText.setText(Html.fromHtml(text));
        eventName1.setClickable(true);
        eventName1.setMovementMethod(LinkMovementMethod.getInstance());
        eventName1.setText(Html.fromHtml(data[0][0]));
        removeUnderlines((Spannable)eventName1.getText());

        eventName2.setClickable(true);
        eventName2.setMovementMethod(LinkMovementMethod.getInstance());
        eventName2.setText(Html.fromHtml(data[1][0]));
        removeUnderlines((Spannable)eventName2.getText());


        eventName3.setClickable(true);
        eventName3.setMovementMethod(LinkMovementMethod.getInstance());
        eventName3.setText(Html.fromHtml(data[2][0]));
        removeUnderlines((Spannable)eventName3.getText());


        eventName4.setClickable(true);
        eventName4.setMovementMethod(LinkMovementMethod.getInstance());
        eventName4.setText(Html.fromHtml(data[3][0]));
        removeUnderlines((Spannable)eventName4.getText());


        eventName5.setClickable(true);
        eventName5.setMovementMethod(LinkMovementMethod.getInstance());
        eventName5.setText(Html.fromHtml(data[4][0]));
        removeUnderlines((Spannable)eventName5.getText());

//        eventName1.setText(data[0][0]);
//        eventName2.setText(data[1][0]);
//        eventName3.setText(data[2][0]);
//        eventName4.setText(data[3][0]);
//        eventName5.setText(data[4][0]);

        artistName1.setText(data[0][1]);
        artistName2.setText(data[1][1]);
        artistName3.setText(data[2][1]);
        artistName4.setText(data[3][1]);
        artistName5.setText(data[4][1]);

        dateTime1.setText(data[0][2]);
        dateTime2.setText(data[1][2]);
        dateTime3.setText(data[2][2]);
        dateTime4.setText(data[3][2]);
        dateTime5.setText(data[4][2]);

        type1.setText(data[0][3]);
        type2.setText(data[1][3]);
        type3.setText(data[2][3]);
        type4.setText(data[3][3]);
        type5.setText(data[4][3]);

    }
}
