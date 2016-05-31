package com.task.vasilyevanton.weathertask.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {
    public static final String APP_PREFERENCES = "Settings";
    // properties
    public static final String APP_PREFERENCES_CITY = "City";
    private static SharedPreferencesManager sInstance;
    private final SharedPreferences mPref;

    private SharedPreferencesManager(Context context) {
        mPref = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    public static synchronized void initializeInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SharedPreferencesManager(context);
        }
    }

    public static synchronized SharedPreferencesManager getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException(SharedPreferencesManager.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }
        return sInstance;
    }

    public void setCity(String city) {
        mPref.edit()
                .putString(APP_PREFERENCES_CITY, city)
                .commit();
    }

    public String getCity() {
        return mPref.getString(APP_PREFERENCES_CITY, null);
    }

    public void remove(String key) {
        mPref.edit()
                .remove(key)
                .commit();
    }

    public boolean clear() {
        return mPref.edit()
                .clear()
                .commit();
    }
}
