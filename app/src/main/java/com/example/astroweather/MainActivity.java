package com.example.astroweather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.astrocalculator.AstroCalculator;
import com.example.astroweather.AstroConfig.AstroConfig;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.astroweather.ui.main.SectionsPagerAdapter;




public class MainActivity extends AppCompatActivity {
    private AstroConfig astroConfig;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        astroConfig = AstroConfig.getInstance();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        if(viewPager != null){
            viewPager.setAdapter(sectionsPagerAdapter);
            TabLayout tabs = findViewById(R.id.tabs);
            tabs.setupWithViewPager(viewPager);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        synchAstroConfig();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }
    }

    public void synchAstroConfig(){
        astroConfig.setRefreshRate(preferences.getString(getString(R.string.pref_refreshing_time), "5000"));
        double latitude = Double.parseDouble(preferences.getString("latitude", "52"));
        double longitude = Double.parseDouble(preferences.getString("longitude", "35"));
        astroConfig.setLocation(new AstroCalculator.Location(latitude,longitude));
    }
}