package edu.anubhavjusc.eventsearch;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class EventDetails extends AppCompatActivity {

    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    String eventName ="";
    String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);

        String event = getIntent().getStringExtra("event");
        try {
            JSONObject json = new JSONObject(event);
            eventName = json.getString("name");
            url = json.getString("url");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ActionBar ab = getSupportActionBar();
        ab.setTitle(eventName);

        TabLayout tabLayout = findViewById(R.id.tabs2);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.eventtab);
        tabLayout.getTabAt(0).setText("EVENT");
        tabLayout.getTabAt(1).setIcon(R.drawable.artiststab);
        tabLayout.getTabAt(1).setText("ARTIST(S)");
        tabLayout.getTabAt(2).setIcon(R.drawable.venuetab);
        tabLayout.getTabAt(2).setText("VENUE");
        tabLayout.getTabAt(3).setIcon(R.drawable.upcomingtab);
        tabLayout.getTabAt(3).setText("UPCOMING");
    }

    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment(),"TAB1");
        adapter.addFragment(new Tab2Fragment(),"TAB2");
        adapter.addFragment(new Tab3Fragment(),"TAB3");
        adapter.addFragment(new Tab4Fragment(),"TAB4");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
            case R.id.favorite:

                return true;

            case R.id.tweet:
                Uri uri = Uri.parse("https://twitter.com/intent/tweet?text=Check out "+eventName+" located at "+getIntent().getStringExtra("venueName")+". Website: "+url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        getMenuInflater().inflate( R.menu.menu_event_details, menu );
        return true;
    }

}
