package de.fluchtwege.weatherhistory.io;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.OperationApplicationException;
import android.os.RemoteException;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.fluchtwege.weatherhistory.Util;
import de.fluchtwege.weatherhistory.provider.WeatherHistoryContract;

public class ForecastHandler extends BaseHandler {

    private static final String LOG_TAG = "WeatherHistory.ForecastHandler";

    private static final String BASE_URL = "http://api.wunderground.com/api/56b55db73f36509e/geolookup/conditions/forecast/q/Germany/Helgoland.json";

    private static final String TAG_FORECAST = "forecast";
    private static final String TAG_SIMPLE_FORECAST = "simpleforecast";
    private static final String TAG_FORECAST_DAY = "forecastday";
    private static final String TAG_HIGH = "high";
    private static final String TAG_LOW = "low";
    private static final String TAG_CELSIUS = "celsius";

    private static final String TAG_LOCATION = "location";
    private static final String TAG_LAT = "lat";
    private static final String TAG_LON = "lon";
    private static final String TAG_CITY = "city";
    private static final String TAG_COUNTRY = "country_name";

    private static final String TAG_CURRENT_OBSERVATION = "current_observation";
    private static final String TAG_TEMP_C = "temp_c";
    private static final String TAG_WIND_KPH = "wind_kph";
    private static final String TAG_WIND_DIR = "wind_dir";
    private static final String TAG_ICON_URL = "icon_url";
    private static final String TAG_VISIBILITY_KM = "visibility_km";
    private static final String TAG_FEELSLIKE_C = "feelslike_c";
    private static final String TAG_RELATIVE_HUMIDITY = "relative_humidity";

    public ForecastHandler(Context ctx) {
        super(ctx);
        Log.d(LOG_TAG, "Constructor");
        handle();
    }


    @Override
    public void onResponse(JSONObject object) {
        Log.d(LOG_TAG, "onResponse");
        final ArrayList<ContentProviderOperation> batch = new ArrayList<ContentProviderOperation>();
        batch.add(parseForecast(object));
        final ContentResolver resolver = mCtx.getContentResolver();
        try {
            resolver.applyBatch(WeatherHistoryContract.CONTENT_AUTHORITY, batch);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }
    }

    private ContentProviderOperation parseForecast(JSONObject object) {
        int highCelsius = 0;
        int lowCelsius = 0;
        String lat = null;
        String lon = null;
        String city = null;
        String country = null;
        String winddir = null;
        String windspeed = null;
        String feelslike = null;
        String iconurl = null;
        String visibility = null;
        String humidity = null;
        String tempc = null;
        String date = Util.getCurrentDateFormatted();

        try {
            if (object.has(TAG_FORECAST)) {
                JSONObject forecast = object.getJSONObject(TAG_FORECAST);
                if (forecast.has(TAG_SIMPLE_FORECAST)) {
                    JSONObject simpleForecast = forecast.getJSONObject(TAG_SIMPLE_FORECAST);
                    if (simpleForecast.has(TAG_FORECAST_DAY)) {
                        JSONArray forecastDay = simpleForecast.getJSONArray(TAG_FORECAST_DAY);

                        JSONObject high = forecastDay.getJSONObject(0).getJSONObject(TAG_HIGH);
                        highCelsius = high.getInt(TAG_CELSIUS);
                        JSONObject low = forecastDay.getJSONObject(0).getJSONObject(TAG_LOW);
                        lowCelsius = low.getInt(TAG_CELSIUS);
                    }
                }
            }
            if (object.has(TAG_LOCATION)) {
                JSONObject location = object.getJSONObject(TAG_LOCATION);
                if (location.has(TAG_COUNTRY)) {
                    country = location.getString(TAG_COUNTRY);
                }
                if (location.has(TAG_CITY)) {
                    city = location.getString(TAG_CITY);
                }
                if (location.has(TAG_LAT)) {
                    lat = location.getString(TAG_LAT);
                }
                if (location.has(TAG_LON)) {
                    lon = location.getString(TAG_LON);
                }
            }
            if (object.has(TAG_CURRENT_OBSERVATION)) {
                JSONObject currentObservation = object.getJSONObject(TAG_CURRENT_OBSERVATION);
                if (currentObservation.has(TAG_TEMP_C)) {
                    tempc = currentObservation.getString(TAG_TEMP_C);
                }
                if (currentObservation.has(TAG_RELATIVE_HUMIDITY)) {
                    humidity = currentObservation.getString(TAG_RELATIVE_HUMIDITY);
                }
                if (currentObservation.has(TAG_WIND_DIR)) {
                    winddir = currentObservation.getString(TAG_WIND_DIR);
                }
                if (currentObservation.has(TAG_WIND_KPH)) {
                    windspeed = currentObservation.getString(TAG_WIND_KPH);
                }
                if (currentObservation.has(TAG_FEELSLIKE_C)) {
                    feelslike = currentObservation.getString(TAG_FEELSLIKE_C);
                }
                if (currentObservation.has(TAG_VISIBILITY_KM)) {
                    visibility = currentObservation.getString(TAG_VISIBILITY_KM);
                }
                if (currentObservation.has(TAG_ICON_URL)) {
                    iconurl = currentObservation.getString(TAG_ICON_URL);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        final ContentProviderOperation.Builder builder = ContentProviderOperation
                .newInsert(WeatherHistoryContract.WeatherData.CONTENT_URI)
                .withValue(WeatherHistoryContract.WeatherDataColumns.MAX_CELSIUS, highCelsius)
                .withValue(WeatherHistoryContract.WeatherDataColumns.MIN_CELSIUS, lowCelsius).withValue(WeatherHistoryContract.WeatherDataColumns.DATE, date)
                .withValue(WeatherHistoryContract.WeatherDataColumns.COUNTRY, country).withValue(WeatherHistoryContract.WeatherDataColumns.CITY, city).withValue(WeatherHistoryContract.WeatherDataColumns.LAT, lat)
                .withValue(WeatherHistoryContract.WeatherDataColumns.LON, lon).withValue(WeatherHistoryContract.WeatherDataColumns.TEMP_C, tempc).withValue(WeatherHistoryContract.WeatherDataColumns.RELATIVE_HUMIDITY, humidity)
                .withValue(WeatherHistoryContract.WeatherDataColumns.WIND_DIR, winddir).withValue(WeatherHistoryContract.WeatherDataColumns.WIND_KPH, windspeed).withValue(WeatherHistoryContract.WeatherDataColumns.FEELSLIKE_C, feelslike)
                .withValue(WeatherHistoryContract.WeatherDataColumns.VISIBILITY_KM, visibility).withValue(WeatherHistoryContract.WeatherDataColumns.ICON_URL, iconurl);
        return builder.build();
    }


    @Override
    protected String getUrl() {
        return BASE_URL;
    }

    @Override
    protected int getMethod() {
        return Request.Method.GET;
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        Log.d(LOG_TAG, "onErrorResponse");
    }
}
