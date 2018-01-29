package com.task.vasilyevanton.weathertask.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.task.vasilyevanton.weathertask.R;
import com.task.vasilyevanton.weathertask.utils.SharedPreferencesManager;
import com.task.vasilyevanton.weathertask.asynctasks.WeatherAsyncTask;
import com.task.vasilyevanton.weathertask.data.WeatherData;

import java.net.InetAddress;
import java.util.Timer;
import java.util.TimerTask;

public class WeatherFragment extends Fragment implements WeatherAsyncTask.OnGetWeatherListener, View.OnClickListener {
    private TextView location, temperature, weatherDescription, minTemperature, maxTemperature;
    private ImageView weatherIcon;
    private ProgressBar progressBar;
    private Timer timer;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.weather_layout, container, false);
        fragmentInit(view);
        setRetainInstance(true);
        return view;
    }

    private void fragmentInit(View view) {
        FloatingActionButton updateWeatherBtn = (FloatingActionButton) view.findViewById(R.id.fab);
        updateWeatherBtn.setOnClickListener(this);
        weatherIcon = (ImageView) view.findViewById(R.id.weather_icon);
        minTemperature = (TextView) view.findViewById(R.id.temperature_evening);
        maxTemperature = (TextView) view.findViewById(R.id.temperature_morning);
        location = (TextView) view.findViewById(R.id.location);
        temperature = (TextView) view.findViewById(R.id.temperature);
        weatherDescription = (TextView) view.findViewById(R.id.weather_description);
        progressBar = (ProgressBar) view.findViewById(R.id.get_weather_progress_bar);
        progressBar.setVisibility(View.INVISIBLE);
        getActivity().setTitle("Weather App");
    }

    private void getWeather() {
        timer = new Timer();
        final Handler handler = new Handler();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            progressBar.setVisibility(View.VISIBLE);
                            WeatherAsyncTask weather = new WeatherAsyncTask();
                            weather.execute("http://api.openweathermap.org/data/2.5/weather?q=" + SharedPreferencesManager.getInstance().getCity() + "&mode=json&lang=ru&units=metric&appid=825a320e06b97ec7b281f12109173ec3");
                            weather.setOnGetWeatherListener(WeatherFragment.this);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 1800000); //execute in every 30 min
    }

    public void setWeather(WeatherData weatherData) {
        temperature.setText(weatherData.getCurTemperature());
        weatherDescription.setText(weatherData.getWeatherDescription());
        location.setText(SharedPreferencesManager.getInstance().getCity());
        minTemperature.setText(weatherData.getMinTemperature());
        maxTemperature.setText(weatherData.getMaxTemperature());
        Picasso.with(getContext()).load(weatherData.getWeatherIcon()).into(weatherIcon);
    }

    @Override
    public void onResponse(WeatherData weatherData, int success) {
        progressBar.setVisibility(View.INVISIBLE);
        if (success == 0) {
            timer.cancel();
            Snackbar.make(view, R.string.server_error, Snackbar.LENGTH_LONG).setAction("Action", null).show();

        } else {
            setWeather(weatherData);
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        timer.cancel();
    }

    @Override
    public void onResume() {
        super.onResume();
        getWeather();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab: {
                timer.cancel();
                getWeather();
                break;
            }
        }
    }
}

