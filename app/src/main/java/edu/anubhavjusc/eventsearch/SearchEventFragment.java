package edu.anubhavjusc.eventsearch;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.URI;
import java.net.URISyntaxException;

public class SearchEventFragment extends Fragment {

    private Button searchButton, clearButton;
    private EditText keywordText, distanceText, otherLocationText;
    private Spinner categorySpinner, unitSpinner;
    private RadioGroup fromGroup;
    private RequestQueue mQueue;
    private TextView error1,error2;
    private TextView testText;
    private RadioButton radioHere,radioOther;

    FusedLocationProviderClient fusedLocationProviderClient;
    LocationRequest locationRequest;
    LocationCallback locationCallback;

    double mylatitude = 34.0294;
    double mylongitude = -118.2871;

    String url1;
    String distance;
    URI keyword;
    String segmentId = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_page_layout, container, false);

        searchButton = view.findViewById(R.id.searchBtn);
        clearButton = view.findViewById(R.id.clearBtn);
        categorySpinner = view.findViewById(R.id.category);
        unitSpinner = view.findViewById(R.id.unit);
        keywordText = view.findViewById(R.id.keywords);
        distanceText = view.findViewById(R.id.distance);
        otherLocationText = view.findViewById(R.id.otherLocation);
        fromGroup = view.findViewById(R.id.fromRadioGroup);
        radioHere = view.findViewById(R.id.radioHere);
        radioOther = view.findViewById(R.id.radioOther);
        error1 = view.findViewById(R.id.error1);
        error2 = view.findViewById(R.id.error2);

        testText = view.findViewById(R.id.testText);

        mQueue = Volley.newRequestQueue(getActivity());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.categories, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),R.array.units, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        categorySpinner.setAdapter(adapter);
        unitSpinner.setAdapter(adapter2);


        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 101);
        } else {
            buildLocationRequest();
            buildLocationCallback();
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        }

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 101);
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());


       searchButton.setOnClickListener(
               new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       if(isValid())
                           jsonParse();
                   }
               }
       );

       clearButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               keywordText.setText(null);
               otherLocationText.setText(null);
               otherLocationText.setEnabled(false);
               radioHere.setChecked(true);
               categorySpinner.setSelection(0);
               unitSpinner.setSelection(0);
               distanceText.setText(null);
               error2.setVisibility(View.GONE);
               error1.setVisibility(View.GONE);
           }
       });

        radioOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otherLocationText.setEnabled(true);
            }
        });

        radioHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otherLocationText.setEnabled(false);
                error2.setVisibility(View.GONE);
                otherLocationText.setText(null);
            }
        });

        return view;
    }

    private boolean isValid() {
        if(keywordText.getText().toString().replaceAll(" ","")=="")
        {
            error1.setVisibility(View.VISIBLE);
            Toast.makeText(getActivity(),"Please fix all fields with errors",Toast.LENGTH_SHORT).show();
            if(radioOther.isChecked()) {
                if (otherLocationText.getText().toString().replaceAll(" ", "") == "") {
                    error2.setVisibility(View.VISIBLE);
                    return false;
                }
            }
            return false;
        }
        else if(keywordText.getText().toString().replaceAll(" ","")!="")
        {
            error1.setVisibility(View.GONE);
        }
        if(radioOther.isChecked())
        {
            if(otherLocationText.getText().toString().replaceAll(" ","")=="")
            {
                error2.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(),"Please fix all fields with errors",Toast.LENGTH_SHORT).show();
                return false;
            }
            else if(otherLocationText.getText().toString().replaceAll(" ","")!="")
            {
                error2.setVisibility(View.GONE);
            }
        }
        return true;
    }

    private void buildLocationCallback() {
        locationCallback = new LocationCallback()
        {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                for(Location location:locationResult.getLocations()) {
                    mylatitude = location.getLatitude();
                    mylongitude = location.getLongitude();
                }
            }
        };
    }

    private void buildLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setSmallestDisplacement(10);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case 101:
            {
                if(grantResults.length>0)
                {
                    if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
                    {

                    }
                    else if(grantResults[0]==PackageManager.PERMISSION_DENIED)
                    {

                    }
                }
            }
        }
    }

    private void jsonParse(){
        try {
            String category = categorySpinner.getSelectedItem().toString();
            if(category=="Music")
                segmentId = "KZFzniwnSyZfZ7v7nJ";
            else if(category=="Sports")
                segmentId = "KZFzniwnSyZfZ7v7nE";
            else if(category=="Arts and Theatre")
                segmentId = "KZFzniwnSyZfZ7v7na";
            else if(category=="Film")
                segmentId = "KZFzniwnSyZfZ7v7nn";
            else if(category=="Miscellaneous")
                segmentId = "KZFzniwnSyZfZ7v7n1";


            if(distanceText.getText().toString().isEmpty())
                distance = "10";
            else
                distance = distanceText.getText().toString();

            keyword = new URI(keywordText.getText().toString().replace(" ", "%20"));

            if(radioOther.isChecked())
            {
                URI loc = new URI(otherLocationText.getText().toString().replace(" ", "%20"));
                String url2= "http://csci-hw8-222600.appspot.com/location/"+loc;
                JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, url2, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            mylatitude = Double.parseDouble(response.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getString("lat"));
                            mylongitude = Double.parseDouble(response.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getString("lng"));
//                            testText.append("MYLAT:"+mylatitude+"MYLONG:"+mylongitude);
                            url1 = "http://csci-hw8-222600.appspot.com/search/"+keyword+"/"+distance+"/"+unitSpinner.getSelectedItem().toString()+"/"+mylatitude+"/"+mylongitude+"?segmentId="+segmentId;
                            search();
                        } catch (JSONException e) {
                            e.printStackTrace();
//                            testText.append("error");
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
                mQueue.add(request2);
            }
            else
            {
                search();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void search() {

        if(url1==null)
            url1 = "http://csci-hw8-222600.appspot.com/search/"+keyword+"/"+distance+"/"+unitSpinner.getSelectedItem().toString()+"/"+mylatitude+"/"+mylongitude+"?segmentId="+segmentId;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url1, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Intent toResults = new Intent(getActivity(),SearchResults.class);
                toResults.putExtra("jsonResult",response.toString());
                startActivity(toResults);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }
}
