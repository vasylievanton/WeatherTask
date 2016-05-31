package com.task.vasilyevanton.weathertask;

import com.task.vasilyevanton.weathertask.utils.SharedPreferencesManager;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferencesManager.initializeInstance(this);
        if(SharedPreferencesManager.getInstance().getCity().equals("")){
            SharedPreferencesManager.getInstance().setCity(getResources().getStringArray(R.array.cities)[0]);
        }
    }
}
