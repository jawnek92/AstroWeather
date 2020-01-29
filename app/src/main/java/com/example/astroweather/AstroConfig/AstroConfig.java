package com.example.astroweather.AstroConfig;

import android.os.Handler;
import android.util.Log;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class AstroConfig {
    private static final AstroConfig instance = new AstroConfig();

    private AstroCalculator.Location location;
    private AstroCalculator astroCalculator;
    private Runnable updateAstro;
    private Set<AstroCallback> subscribers = new HashSet<>();
    private long refreshRate = 5000;
    final Handler handler = new Handler();

    public AstroConfig(){
        this.location = new AstroCalculator.Location(51, 36);
        Log.d("ewnejak", "method : AstroConfig constructor called.");
        this.astroCalculator = new AstroCalculator(getAstroDateTime(), this.location);
        updateAstro = new Runnable() {
            @Override
            public void run() {
                updateLocationToAstroCalc();
                notifySubs();
                handler.postDelayed(this, refreshRate);
            }
        };
        handler.post(updateAstro);
    }
    private AstroDateTime getAstroDateTime() {
        long deviceDate = System.currentTimeMillis();
        int year = Integer.parseInt(new SimpleDateFormat("yyyy", Locale.ENGLISH).format(deviceDate));
        int month = Integer.parseInt(new SimpleDateFormat("MM", Locale.ENGLISH).format(deviceDate));
        int day = Integer.parseInt(new SimpleDateFormat("dd", Locale.ENGLISH).format(deviceDate));
        int hour = Integer.parseInt(new SimpleDateFormat("hh", Locale.GERMANY).format(deviceDate));
        int minute = Integer.parseInt(new SimpleDateFormat("mm", Locale.GERMANY).format(deviceDate));
        int second = Integer.parseInt(new SimpleDateFormat("ss", Locale.GERMANY).format(deviceDate));
        int timeZoneOffset = 1;
        boolean dayLightSaving = true;
        return new AstroDateTime(year, month, day, hour, minute, second, timeZoneOffset, dayLightSaving);
    }

    public static AstroConfig getInstance() {
        return instance;
    }

    public void updateLocationToAstroCalc(){
        this.astroCalculator = new AstroCalculator(getAstroDateTime(), this.location);
    }

    public AstroCalculator.Location getLocation() {
        return location;
    }
    public void setLocation(AstroCalculator.Location location){
        this.location = location;
    }

    public AstroCalculator getAstroCalculator() {
        return astroCalculator;
    }

    public void registerForUpdates(AstroCallback subscriber) {
        subscribers.add(subscriber);
    }

    public void unregisterForUpdates(AstroCallback subscriber) {
        subscribers.remove(subscriber);
    }

    public void notifySubs(){
        for(AstroCallback sub : subscribers){
            sub.onSettingsUpdate();
        }
    }

    public void setRefreshRate(String refreshRate){
        this.refreshRate = Long.parseLong(refreshRate);
        updateLocationToAstroCalc();
        notifySubs();
    }
}