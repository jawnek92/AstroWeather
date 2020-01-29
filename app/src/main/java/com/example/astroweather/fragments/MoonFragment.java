package com.example.astroweather.fragments;

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

public class MoonFragment extends Fragment implements AstroCallback {

    private AstroConfig astroConfig;

    private TextView latitude;
    private TextView longitude;
    private TextView moonrise;
    private TextView moonset;
    private TextView newMoon;
    private TextView fullMoon;
    private TextView lunarPhase;
    private TextView dayMonthLunar;


    private StringBuilder stringBuilder;

    void initTextViews(View view) {
        latitude = view.findViewById(R.id.latitude);
        longitude = view.findViewById(R.id.longitude);
        moonrise = view.findViewById(R.id.moonrise);
        moonset = view.findViewById(R.id.moonset);
        newMoon = view.findViewById(R.id.newmoon);
        fullMoon = view.findViewById(R.id.fullmoon);
        lunarPhase = view.findViewById(R.id.lunarphase);
        dayMonthLunar = view.findViewById(R.id.daylunar);
    }



    private void viewContent(){
        String temp = null;
        stringBuilder = new StringBuilder();

        this.latitude.setText(String.valueOf(astroConfig.getLocation().getLatitude()));
        this.longitude.setText(String.valueOf(astroConfig.getLocation().getLongitude()));
        AstroCalculator.MoonInfo moonInfo = astroConfig.getAstroCalculator().getMoonInfo();

        temp = stringBuilder.append(moonInfo.getMoonrise().getHour()).append(":").append(moonInfo.getMoonrise().getMinute()).toString();
        this.moonrise.setText(temp);

        stringBuilder = new StringBuilder();
        temp = stringBuilder.append(moonInfo.getMoonset().getHour()).append(":").append(moonInfo.getMoonset().getMinute()).toString();
        this.moonset.setText(temp);

        stringBuilder = new StringBuilder();
        temp = stringBuilder.append(moonInfo.getNextNewMoon().getDay()).append(".").append(moonInfo.getNextNewMoon().getMonth()).append(".").append(moonInfo.getNextNewMoon().getYear()).toString();
        this.newMoon.setText(temp);

        stringBuilder = new StringBuilder();
        temp = stringBuilder.append(moonInfo.getNextFullMoon().getDay()).append(".").append(moonInfo.getNextFullMoon().getMonth()).append(".").append(moonInfo.getNextFullMoon().getYear()).toString();
        this.fullMoon.setText(temp);

        this.lunarPhase.setText(String.valueOf(SunFragment.round(moonInfo.getIllumination(), 2)));
        this.dayMonthLunar.setText(String.valueOf((int)moonInfo.getAge()));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_moon, container, false);
        astroConfig = AstroConfig.getInstance();
        astroConfig.registerForUpdates(this);
        initTextViews(view);
        viewContent();
        return view;
    }

    @Override
    public void onDestroy() {
        astroConfig.unregisterForUpdates(this);
        super.onDestroy();
    }

    @Override
    public void onSettingsUpdate() {
        viewContent();
    }
}
