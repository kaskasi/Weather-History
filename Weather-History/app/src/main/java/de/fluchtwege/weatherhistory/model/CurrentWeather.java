package de.fluchtwege.weatherhistory.model;

public class CurrentWeather {

    private String windSpeed;
    private String windDirection;
    private String feelsLikeTemp;
    private String visibility;
    private String humidity;
    private String temperature;
    private String iconURL;

    public CurrentWeather(String windSpeed, String windDirection, String feelsLikeTemp, String visibility, String humidity, String temperature, String iconURL) {
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.feelsLikeTemp = feelsLikeTemp;
        this.visibility = visibility;
        this.humidity = humidity;
        this.temperature = temperature;
        this.iconURL = iconURL;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public String getFeelsLikeTemp() {
        return feelsLikeTemp;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getIconURL() {
        return iconURL;
    }
}
