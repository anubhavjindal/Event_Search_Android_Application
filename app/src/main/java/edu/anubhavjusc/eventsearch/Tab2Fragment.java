package edu.anubhavjusc.eventsearch;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class Tab2Fragment extends Fragment {

    TextView nameText,followersText,popularityText,checkAtText,headingTitle;
    TextView nameText2,followersText2,popularityText2,checkAtText2,headingTitle2;

    ImageView artistImage1,artistImage2,artistImage3,artistImage4,artistImage5,artistImage6,artistImage7,artistImage8;
    ImageView artistImage12,artistImage22,artistImage32,artistImage42,artistImage52,artistImage62,artistImage72,artistImage82;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_fragment,container,false);

        nameText = view.findViewById(R.id.nameText);
        followersText = view.findViewById(R.id.followersText);
        popularityText = view.findViewById(R.id.popularityText);
        checkAtText = view.findViewById(R.id.checkAtText);
        headingTitle = view.findViewById(R.id.headingTitle);
        artistImage1 = view.findViewById(R.id.artistImage1);
        artistImage2 = view.findViewById(R.id.artistImage2);
        artistImage3 = view.findViewById(R.id.artistImage3);
        artistImage4 = view.findViewById(R.id.artistImage4);
        artistImage5 = view.findViewById(R.id.artistImage5);
        artistImage6 = view.findViewById(R.id.artistImage6);
        artistImage7 = view.findViewById(R.id.artistImage7);
        artistImage8 = view.findViewById(R.id.artistImage8);

        nameText2 = view.findViewById(R.id.nameText2);
        followersText2 = view.findViewById(R.id.followersText2);
        popularityText2 = view.findViewById(R.id.popularityText2);
        checkAtText2 = view.findViewById(R.id.checkAtText2);
        headingTitle2 = view.findViewById(R.id.headingTitle2);
        artistImage12 = view.findViewById(R.id.artistImage12);
        artistImage22 = view.findViewById(R.id.artistImage22);
        artistImage32 = view.findViewById(R.id.artistImage32);
        artistImage42 = view.findViewById(R.id.artistImage42);
        artistImage52 = view.findViewById(R.id.artistImage52);
        artistImage62 = view.findViewById(R.id.artistImage62);
        artistImage72 = view.findViewById(R.id.artistImage72);
        artistImage82 = view.findViewById(R.id.artistImage82);

        String name = getActivity().getIntent().getStringExtra("artist");
        String name2 = getActivity().getIntent().getStringExtra("images");

        String name3 = null;
        String name4 = null;

        try{
            name3 = getActivity().getIntent().getStringExtra("artist2");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        try{
            name4 = getActivity().getIntent().getStringExtra("images2");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try {
            LinearLayout lt = view.findViewById(R.id.musicArtistLayout);
            lt.setVisibility(View.VISIBLE);
            JSONObject artist = new JSONObject(name);
            headingTitle.setText(artist.getJSONObject("artists").getJSONArray("items").getJSONObject(0).getString("name"));
            nameText.setText(artist.getJSONObject("artists").getJSONArray("items").getJSONObject(0).getString("name"));
            popularityText.setText(artist.getJSONObject("artists").getJSONArray("items").getJSONObject(0).getString("popularity"));
            followersText.setText(artist.getJSONObject("artists").getJSONArray("items").getJSONObject(0).getJSONObject("followers").getString("total"));
            checkAtText.setClickable(true);
            checkAtText.setMovementMethod(LinkMovementMethod.getInstance());
            String text = "<a href='"+artist.getJSONObject("artists").getJSONArray("items").getJSONObject(0).getJSONObject("external_urls").getString("spotify")+"'>Spotify</a>";
            checkAtText.setText(Html.fromHtml(text));

        } catch (JSONException e) {
            LinearLayout lt = view.findViewById(R.id.musicArtistLayout);
            lt.setVisibility(View.GONE);
        }
        try {
            headingTitle.setText(getActivity().getIntent().getStringExtra("artistName"));
            JSONObject images = new JSONObject(name2);
            Glide.with(this).load(images.getJSONArray("items").getJSONObject(0).getString("link")).into(artistImage1);
            Glide.with(this).load(images.getJSONArray("items").getJSONObject(1).getString("link")).into(artistImage2);
            Glide.with(this).load(images.getJSONArray("items").getJSONObject(2).getString("link")).into(artistImage3);
            Glide.with(this).load(images.getJSONArray("items").getJSONObject(3).getString("link")).into(artistImage4);
            Glide.with(this).load(images.getJSONArray("items").getJSONObject(4).getString("link")).into(artistImage5);
            Glide.with(this).load(images.getJSONArray("items").getJSONObject(5).getString("link")).into(artistImage6);
            Glide.with(this).load(images.getJSONArray("items").getJSONObject(6).getString("link")).into(artistImage7);
            Glide.with(this).load(images.getJSONArray("items").getJSONObject(7).getString("link")).into(artistImage8);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Artist 2
        if(name3!=null)
        {
            try {
                LinearLayout lt2 = view.findViewById(R.id.musicArtistLayout2);
                lt2.setVisibility(View.VISIBLE);
                JSONObject artist = new JSONObject(name3);
                headingTitle2.setText(artist.getJSONObject("artists").getJSONArray("items").getJSONObject(0).getString("name"));
                nameText2.setText(artist.getJSONObject("artists").getJSONArray("items").getJSONObject(0).getString("name"));
                popularityText2.setText(artist.getJSONObject("artists").getJSONArray("items").getJSONObject(0).getString("popularity"));
                followersText2.setText(artist.getJSONObject("artists").getJSONArray("items").getJSONObject(0).getJSONObject("followers").getString("total"));
                checkAtText2.setClickable(true);
                checkAtText2.setMovementMethod(LinkMovementMethod.getInstance());
                String text = "<a href='"+artist.getJSONObject("artists").getJSONArray("items").getJSONObject(0).getJSONObject("external_urls").getString("spotify")+"'>Spotify</a>";
                checkAtText2.setText(Html.fromHtml(text));

            } catch (JSONException e) {
                LinearLayout lt2 = view.findViewById(R.id.musicArtistLayout2);
                lt2.setVisibility(View.GONE);
            }
        }
        else{
            LinearLayout lt2 = view.findViewById(R.id.musicArtistLayout2);
            lt2.setVisibility(View.GONE);
        }
        if(name4!=null)
        {
            try {
                LinearLayout lt2 = view.findViewById(R.id.artistImagesLayout2);
                lt2.setVisibility(View.VISIBLE);
                headingTitle2.setVisibility(View.VISIBLE);
                headingTitle2.setText(getActivity().getIntent().getStringExtra("artistName2"));
                JSONObject images = new JSONObject(name4);
                Glide.with(this).load(images.getJSONArray("items").getJSONObject(0).getString("link")).into(artistImage12);
                Glide.with(this).load(images.getJSONArray("items").getJSONObject(1).getString("link")).into(artistImage22);
                Glide.with(this).load(images.getJSONArray("items").getJSONObject(2).getString("link")).into(artistImage32);
                Glide.with(this).load(images.getJSONArray("items").getJSONObject(3).getString("link")).into(artistImage42);
                Glide.with(this).load(images.getJSONArray("items").getJSONObject(4).getString("link")).into(artistImage52);
                Glide.with(this).load(images.getJSONArray("items").getJSONObject(5).getString("link")).into(artistImage62);
                Glide.with(this).load(images.getJSONArray("items").getJSONObject(6).getString("link")).into(artistImage72);
                Glide.with(this).load(images.getJSONArray("items").getJSONObject(7).getString("link")).into(artistImage82);

            } catch (JSONException e) {
                LinearLayout lt2 = view.findViewById(R.id.artistImagesLayout2);
                lt2.setVisibility(View.GONE);
                headingTitle2.setVisibility(View.GONE);

            }
        }
        else{
            LinearLayout lt2 = view.findViewById(R.id.artistImagesLayout2);
            lt2.setVisibility(View.GONE);
            headingTitle2.setVisibility(View.GONE);
        }
        return view;
    }
}
