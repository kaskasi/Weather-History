package de.fluchtwege.weatherhistory.provider;

import android.content.ContentValues;
import android.database.Cursor;

import de.fluchtwege.weatherhistory.model.CurrentWeather;
import de.fluchtwege.weatherhistory.model.Station;

public class ContentProviderHelper {

    public static void parseAndPostWeatherData(Cursor cursor) {
        String humidity = cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.RELATIVE_HUMIDITY));
        String visibility = cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.VISIBILITY_KM));
        String temperature = cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.TEMP_C));
        String feelsLike = cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.FEELSLIKE_C));
        String windDir = cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.WIND_DIR));
        String windSpeed = cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.WIND_KPH));
        String iconURL = cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.ICON_URL));

        CurrentWeather currentWeather = new CurrentWeather(windSpeed, windDir, feelsLike, visibility, humidity,
                temperature, iconURL);
        Otto.getBus().post(currentWeather);
    }

    public static void parseAndPostStation(Cursor cursor) {
        String country = cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.COUNTRY));
        String city = cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.CITY));
        String longitude = cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.LON));
        String latitude = cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.LAT));

        Station station = new Station(country, city, longitude, latitude);
        Otto.getBus().post(station);
    }

    public static void postCurrentWeather(ContentValues values) {
        String windDir = (String) values.get(WeatherHistoryContract.WeatherDataColumns.WIND_DIR);
        String windSpeed = (String) values.get(WeatherHistoryContract.WeatherDataColumns.WIND_KPH);
        String humidity = (String) values.get(WeatherHistoryContract.WeatherDataColumns.RELATIVE_HUMIDITY);
        String visibility = (String) values.get(WeatherHistoryContract.WeatherDataColumns.VISIBILITY_KM);
        String temperature = (String) values.get(WeatherHistoryContract.WeatherDataColumns.TEMP_C);
        String feelsLike = (String) values.get(WeatherHistoryContract.WeatherDataColumns.FEELSLIKE_C);
        String iconURL = (String) values.get(WeatherHistoryContract.WeatherDataColumns.ICON_URL);

        CurrentWeather currentWeather = new CurrentWeather(windSpeed, windDir, feelsLike, visibility, humidity, temperature, iconURL);
        Otto.getBus().post(currentWeather);
    }
}
