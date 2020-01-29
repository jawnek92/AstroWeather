package com.example.astroweather;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.preference.EditTextPreference;

import androidx.preference.Preference;
import android.widget.Toast;

import com.example.astroweather.AstroConfig.AstroConfig;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;


public class SettingsActivity extends AppCompatActivity {


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getDelegate().onPostCreate(savedInstanceState);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat //implements SharedPreferences.OnSharedPreferenceChangeListener
     {


        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            /*preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            astroConfig = AstroConfig.getInstance();
            addPreferencesFromResource(R.xml.root_preferences);
            latitude = findPreference("latitude");
            longitude = findPreference("longitude");

            latitude.setText(String.valueOf(astroConfig.getLocation().getLatitude()));
            longitude.setText(String.valueOf(astroConfig.getLocation().getLongitude()));
            setupPreferenceListener(latitude);
            setupPreferenceListener(longitude);
            PreferenceManager.getDefaultSharedPreferences(getActivity()).registerOnSharedPreferenceChangeListener(this);
            */
            //preferences = getPreferenceScreen().getSharedPreferences();
            //preferences.registerOnSharedPreferenceChangeListener(this);

        }/*

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            Preference pref = findPreference(key);
            //String summaryString = findPreference(key).getSummary().toString();

            String input = sharedPreferences.getString(key, "");
            double doubleValue;
            if(key.equals("latitude")){
                    doubleValue = Double.valueOf(input);
                if(doubleValue> 90 || doubleValue < -90) {
                    Toast.makeText(getActivity(), "Incorrect data.", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    pref.setSummaryProvider(null);
                    pref.setSummary(input);
                }
            }else if(key.equals("longitude")){
                doubleValue = Double.valueOf(input);
                if(doubleValue> 180 || doubleValue < -180){
                    Toast.makeText(getActivity(), "Incorrect data.", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    pref.setSummaryProvider(null);
                    pref.setSummary(input);
                }
            }
        }

        private void setupPreferenceListener(Preference preference){
            preference.setOnPreferenceChangeListener(this);
            onPreferenceChange(preference, preferences.getString(preference.getKey(), null));
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            String value = newValue.toString();
            if(preference.getKey().equals("latitude")){
                try{
                    double doubleValue = Double.valueOf(value);
                    if(doubleValue> 90 || doubleValue < -90){
                        throw new Exception();
                    }
                }catch(Exception e){
                    Toast.makeText(getActivity(), "Incorrect data.", Toast.LENGTH_SHORT).show();
                    return false;
                }
                preference.setSummary(value);
            }else if(preference.getKey().equals("longitude")){
                try{
                    double doubleValue = Double.valueOf(value);
                    if(doubleValue> 90 || doubleValue < -90){
                        throw new Exception();
                    }
                }catch(Exception e){
                    Toast.makeText(getActivity(), "Incorrect data.", Toast.LENGTH_SHORT).show();
                    return false;
                }
                preference.setSummary(value);
            }

            return true;
        }*/
    }
}