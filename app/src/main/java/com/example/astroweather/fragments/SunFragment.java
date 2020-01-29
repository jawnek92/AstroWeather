package com.example.astroweather.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astrocalculator.AstroCalculator;
import com.example.astroweather.AstroConfig.AstroConfig;
import com.example.astroweather.AstroConfig.AstroCallback;
import com.example.astroweather.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SunFragment extends Fragment implements AstroCallback {

    private AstroConfig astroConfig;
    private TextView latitude;
    private TextView longitude;
    private TextView sunrise;
    private TextView azimuthRise;
    private TextView sunset;
    private TextView azimuthSet;
    private TextView twilightEvening;
    private TextView twilightMorning;

    private StringBuilder stringBuilder;
    //private DecimalFormat decimalFormat;

    private SharedPreferences preferences;
    private SharedPreferences.Editor prefEditor;

    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sun, container, false);
        astroConfig = AstroConfig.getInstance();
        astroConfig.registerForUpdates(this);
        initTextViews(view);
        viewContent();
        return view;
    }

    void initTextViews(View view) {
        latitude = view.findViewById(R.id.latitude);
        longitude = view.findViewById(R.id.longitude);
        sunrise = view.findViewById(R.id.sunrise);
        azimuthRise = view.findViewById(R.id.azimuth_rise);
        sunset = view.findViewById(R.id.sunset);
        azimuthSet = view.findViewById(R.id.azimuth_set);
        twilightEvening = view.findViewById(R.id.twilightevening);
        twilightMorning = view.findViewById(R.id.twilightmorning);
    }

    private void viewContent(){
        String temp = null;
        stringBuilder = new StringBuilder();

        this.latitude.setText(String.valueOf(astroConfig.getLocation().getLatitude()));
        this.longitude.setText(String.valueOf(astroConfig.getLocation().getLongitude()));

        AstroCalculator.SunInfo sunInfo = astroConfig.getAstroCalculator().getSunInfo();

        temp = stringBuilder.append(sunInfo.getSunrise().getHour()).append(":").append(sunInfo.getSunrise().getMinute()).toString();

        this.sunrise.setText(temp);

        stringBuilder = new StringBuilder();
        temp = stringBuilder.append(sunInfo.getSunset().getHour()).append(":").append(sunInfo.getSunset().getMinute()).toString();
        this.sunset.setText(temp);

        this.azimuthSet.setText(String.valueOf(round(sunInfo.getAzimuthSet(),2)));
        this.azimuthRise.setText(String.valueOf(round(sunInfo.getAzimuthRise(),2)));

        stringBuilder = new StringBuilder();
        temp = stringBuilder.append(sunInfo.getTwilightEvening().getHour()).append(":").append(sunInfo.getTwilightEvening().getMinute()).toString();
        this.twilightEvening.setText(temp);

        stringBuilder = new StringBuilder();
        temp = stringBuilder.append(sunInfo.getTwilightMorning().getHour()).append(":").append(sunInfo.getTwilightMorning().getMinute()).toString();
        this.twilightMorning.setText(temp);
    }
    @Override
    public void onSettingsUpdate() {
        viewContent();
    }

    @Override
    public void onDestroy() {
        astroConfig.unregisterForUpdates(this);
        super.onDestroy();
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
