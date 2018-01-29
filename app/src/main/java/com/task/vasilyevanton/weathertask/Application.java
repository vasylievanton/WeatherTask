package com.task.vasilyevanton.weathertask;

import android.text.TextUtils;

import com.task.vasilyevanton.weathertask.utils.SharedPreferencesManager;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferencesManager.initializeInstance(this);
        if(TextUtils.isEmpty(SharedPreferencesManager.getInstance().getCity())){
            SharedPreferencesManager.getInstance().setCity(getResources().getStringArray(R.array.cities)[0]);
        }
    }
}
