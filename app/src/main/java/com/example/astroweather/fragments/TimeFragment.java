package com.example.astroweather.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.astroweather.R;


import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TimeFragment extends Fragment {

    private TextView timer;
    private Handler handler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time, container, false);
        initTime(view);
        return view;
    }


    private void initTime(View view) {
        timer = view.findViewById(R.id.textClock);
        this.handler = new Handler();
        this.handler.postDelayed(m_Runnable, 2000);
    }

    private final Runnable m_Runnable = new Runnable() {
        @Override
        public void run() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            timer.setText(dateFormat.format(Calendar.getInstance(TimeZone.getTimeZone("Poland")).getTime()));
            TimeFragment.this.handler.postDelayed(m_Runnable, 1000);
        }
    };
}
