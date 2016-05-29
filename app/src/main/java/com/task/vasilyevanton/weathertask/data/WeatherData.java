package com.task.vasilyevanton.weathertask.data;

public class WeatherData {

    private String maxTemperature;
    private String minTemperature;
    private String curPressure;
    private String countryName;
    private String cityName;
    private String curTemperature;
    private String curHumidity;
    private String refreshTime;
    private String clouds;
    private String windSpeed;
    private String windDeg;
    private String sunrise;
    private String sunset;
    private String weatherMain;
    private String weatherDescription;
    private String weatherIcon;


    public WeatherData() {
        this.minTemperature = null;
        this.maxTemperature = null;
        this.countryName = null;
        this.cityName = null;
        this.curHumidity = null;
        this.curPressure = null;
        this.curTemperature = null;
        this.clouds = null;
        this.windSpeed = null;
        this.windDeg = null;
        this.sunrise = null;
        this.sunset = null;

    }

    public String getMaxTemperature() {
        if (Float.valueOf(maxTemperature).intValue() > 0) {
            return "+" + Float.valueOf(maxTemperature).intValue() + "°";

        } else {
            return Float.valueOf(maxTemperature).intValue() + "°";
        }
    }

    public void setMaxTemperature(String maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public String getMinTemperature() {
        if (Float.valueOf(minTemperature).intValue() > 0) {
            return "+" + Float.valueOf(minTemperature).intValue() + "°";

        } else {
            return Float.valueOf(minTemperature).intValue() + "°";
        }
    }

    public void setMinTemperature(String minTemperature) {
        this.minTemperature = minTemperature;
    }

    public String getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(String temp) {
        this.refreshTime = temp;
    }

    public String getCurTemperature() {
        if (Float.valueOf(curTemperature).intValue() > 0) {
            return "+" + Float.valueOf(curTemperature).intValue() + "°";
        } else {
            return Float.valueOf(curTemperature).intValue() + "°";
        }
    }

    public void setCurTemperature(String temperature) {
        this.curTemperature = temperature;
    }

    public String getCurHumidity() {
        return curHumidity;
    }

    public void setCurHumidity(String humidity) {
        this.curHumidity = humidity;
    }

    public String getCurPressure() {
        return curPressure;
    }

    public void setCurPressure(String pressure) {
        this.curPressure = pressure;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String country) {
        this.countryName = country;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String city) {
        this.cityName = city;
    }


    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(String windDeg) {
        this.windDeg = windDeg;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getWeatherMain() {
        return weatherMain;
    }

    public void setWeatherMain(String weatherMain) {
        this.weatherMain = weatherMain;
    }

    public String getWeatherDescription() {
        return weatherDescription.substring(0, 1).toUpperCase() + weatherDescription.substring(1);
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public String getWeatherIcon() {
        return "http://openweathermap.org/img/w/" + weatherIcon + ".png";
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }
}
