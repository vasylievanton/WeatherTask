package com.task.vasilyevanton.weathertask;

import android.content.Context;
import android.content.SharedPreferences;

public class Application extends android.app.Application {

    public static final String APP_PREFERENCES = "Settings";
    public static final String APP_PREFERENCES_CITY = "City";

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if(mSettings.getString(Application.APP_PREFERENCES_CITY, "").equals("")){
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putString(APP_PREFERENCES_CITY, getResources().getStringArray(R.array.cities)[0]);
            editor.apply();
        }
    }
}
