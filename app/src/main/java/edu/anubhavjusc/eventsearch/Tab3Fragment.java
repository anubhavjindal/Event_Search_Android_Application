package edu.anubhavjusc.eventsearch;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

public class Tab3Fragment extends Fragment implements OnMapReadyCallback {

    TextView venueNameText;
    TextView addressText;
    TextView cityText;
    TextView phoneNumberText;
    TextView openHoursText;
    TextView generalRuleText;
    TextView childRuleText;

    double lat = 34.0266;
    double lon = -118.283;
    String name ="";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3_fragment,container,false);

        venueNameText = view.findViewById(R.id.venueNameText);
        addressText = view.findViewById(R.id.addressText);
        cityText = view.findViewById(R.id.cityText);
        phoneNumberText = view.findViewById(R.id.phoneNumberText);
        openHoursText = view.findViewById(R.id.openHoursText);
        generalRuleText = view.findViewById(R.id.generalRuleText);
        childRuleText = view.findViewById(R.id.childRuleText);


        String name = getActivity().getIntent().getStringExtra("venue");

        try {
            JSONObject venue = new JSONObject(name);
            try {
                venueNameText.setText(venue.getJSONObject("_embedded").getJSONArray("venues").getJSONObject(0).getString("name"));
                LinearLayout lt = view.findViewById(R.id.t3l1);
                lt.setVisibility(View.VISIBLE);
            }
            catch (Exception e)
            {
                LinearLayout lt = view.findViewById(R.id.t3l1);
                lt.setVisibility(View.GONE);
            }
            try {
                addressText.setText(venue.getJSONObject("_embedded").getJSONArray("venues").getJSONObject(0).getJSONObject("address").getString("line1"));
                LinearLayout lt = view.findViewById(R.id.t3l2);
                lt.setVisibility(View.VISIBLE);
            }
            catch (Exception e)
            {
                LinearLayout lt = view.findViewById(R.id.t3l2);
                lt.setVisibility(View.GONE);
            }
            try {
                cityText.setText(venue.getJSONObject("_embedded").getJSONArray("venues").getJSONObject(0).getJSONObject("city").getString("name")
                        + ", "+venue.getJSONObject("_embedded").getJSONArray("venues").getJSONObject(0).getJSONObject("state").getString("name"));
                LinearLayout lt = view.findViewById(R.id.t3l3);
                lt.setVisibility(View.VISIBLE);
            }
            catch (Exception e)
            {
                LinearLayout lt = view.findViewById(R.id.t3l3);
                lt.setVisibility(View.GONE);
            }
            try {
                phoneNumberText.setText(venue.getJSONObject("_embedded").getJSONArray("venues").getJSONObject(0).getJSONObject("boxOfficeInfo").getString("phoneNumberDetail"));
                LinearLayout lt = view.findViewById(R.id.t3l4);
                lt.setVisibility(View.VISIBLE);
            }
            catch (Exception e)
            {
                LinearLayout lt = view.findViewById(R.id.t3l4);
                lt.setVisibility(View.GONE);
            }
            try {
                openHoursText.setText(venue.getJSONObject("_embedded").getJSONArray("venues").getJSONObject(0).getJSONObject("boxOfficeInfo").getString("openHoursDetail"));
                LinearLayout lt = view.findViewById(R.id.t3l5);
                lt.setVisibility(View.VISIBLE);
            }
            catch (Exception e)
            {
                LinearLayout lt = view.findViewById(R.id.t3l5);
                lt.setVisibility(View.GONE);
            }
            try {
                generalRuleText.setText(venue.getJSONObject("_embedded").getJSONArray("venues").getJSONObject(0).getJSONObject("generalInfo").getString("generalRule"));
                LinearLayout lt = view.findViewById(R.id.t3l6);
                lt.setVisibility(View.VISIBLE);
            }
            catch (Exception e)
            {
                LinearLayout lt = view.findViewById(R.id.t3l6);
                lt.setVisibility(View.GONE);
            }
            try {
                childRuleText.setText(venue.getJSONObject("_embedded").getJSONArray("venues").getJSONObject(0).getJSONObject("generalInfo").getString("childRule"));
                LinearLayout lt = view.findViewById(R.id.t3l7);
                lt.setVisibility(View.VISIBLE);
            }
            catch (Exception e)
            {
                LinearLayout lt = view.findViewById(R.id.t3l7);
                lt.setVisibility(View.GONE);
            }

            lat = Double.parseDouble(venue.getJSONObject("_embedded").getJSONArray("venues").getJSONObject(0).getJSONObject("location").getString("latitude"));
            lon = Double.parseDouble(venue.getJSONObject("_embedded").getJSONArray("venues").getJSONObject(0).getJSONObject("location").getString("longitude"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Google Map Fragment
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng sydney = new LatLng(lat, lon);
        googleMap.addMarker(new MarkerOptions().position(sydney).title(name));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15.0f));
    }
}
