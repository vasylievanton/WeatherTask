package com.task.vasilyevanton.weathertask.asynctasks;


import android.os.AsyncTask;
import android.util.Log;

import com.task.vasilyevanton.weathertask.data.WeatherData;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherAsyncTask extends AsyncTask<String, Void, Integer> {
    public OnGetWeatherListener onGetWeather;
    private WeatherData weatherData;
    private Integer success;

    public WeatherAsyncTask() {
        this.weatherData = new WeatherData();
        this.success = 0;
        this.onGetWeather = null;
    }

    @Override
    public void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Integer doInBackground(String... params) {
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpContext httpContext = new BasicHttpContext();
            HttpPost httpPost = new HttpPost(params[0]);
            HttpResponse response = httpClient.execute(httpPost, httpContext);
            if (response.getStatusLine().getStatusCode() == 200) {
                success = 200;
                HttpEntity entity = response.getEntity();
                String data = EntityUtils.toString(entity, "UTF-8");
                JSONObject j_object = new JSONObject(data);
                Log.w("cur_weather", j_object.toString());
                weatherData.setRefreshTime(j_object.getString("dt"));
                Log.w("hhj", j_object.getString("dt"));
                JSONObject j_object_temp = j_object.getJSONObject("clouds");
                weatherData.setClouds(j_object_temp.getString("all"));
                Log.w("hhj", j_object_temp.getString("all"));
                j_object_temp = j_object.getJSONObject("sys");
                weatherData.setSunset(j_object_temp.getString("sunset"));
                weatherData.setSunrise(j_object_temp.getString("sunrise"));
                weatherData.setCountryName(j_object_temp.getString("country"));

                weatherData.setCityName(j_object.getString("name"));

                JSONArray weather = j_object.getJSONArray("weather");
                j_object_temp = weather.getJSONObject(0);
                weatherData.setWeatherIcon(j_object_temp.getString("icon"));
                weatherData.setWeatherDescription(j_object_temp.getString("description"));
                weatherData.setWeatherMain(j_object_temp.getString("main"));

                j_object_temp = j_object.getJSONObject("main");
                weatherData.setMinTemperature(j_object_temp.getString("temp_min"));
                weatherData.setMaxTemperature(j_object_temp.getString("temp_max"));
                weatherData.setCurTemperature(j_object_temp.getString("temp"));
                weatherData.setCurHumidity(j_object_temp.getString("humidity"));
                weatherData.setCurPressure(j_object_temp.getString("pressure"));
            } else {
                success = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public void onPostExecute(Integer success) {
        super.onPostExecute(success);
        if (onGetWeather != null) {
            onGetWeather.onResponse(this.weatherData, success);
        }
    }

    public void setOnGetWeatherListener(OnGetWeatherListener listener) {
        onGetWeather = listener;
    }

    public interface OnGetWeatherListener {
        void onResponse(WeatherData weatherData, int success);
    }
}


