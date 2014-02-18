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

	public ForecastHandler(Context ctx) {
		super(ctx);
		handle();
	}

	@Override
	protected void handleDone(JSONObject object) {

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
		String date = Util.getCurrentDateFormatted();

		try {
			JSONObject forecast = object.getJSONObject(TAG_FORECAST);
			JSONObject simpleForecast = forecast.getJSONObject(TAG_SIMPLE_FORECAST);
			JSONArray forecastDay = simpleForecast.getJSONArray(TAG_FORECAST_DAY);
			JSONObject high = forecastDay.getJSONObject(0).getJSONObject(TAG_HIGH);
			highCelsius = high.getInt(TAG_CELSIUS);
			JSONObject low = forecastDay.getJSONObject(0).getJSONObject("low");
			lowCelsius = low.getInt(TAG_CELSIUS);
			Log.i(LOG_TAG, "low:" + lowCelsius);
			Log.i(LOG_TAG, "high:" + highCelsius);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		final ContentProviderOperation.Builder builder = ContentProviderOperation
				.newInsert(WeatherHistoryContract.WeatherData.CONTENT_URI)
				.withValue(WeatherHistoryContract.WeatherDataColumns.MAX_CELSIUS, highCelsius)
				.withValue(WeatherHistoryContract.WeatherDataColumns.MIN_CELSIUS, lowCelsius).withValue(WeatherHistoryContract.WeatherDataColumns.DATE, date);
		return builder.build();
	}

	@Override
	protected void handleFailed(VolleyError error) {

	}

	@Override
	protected String getUrl() {
		return BASE_URL;
	}

	@Override
	protected int getMethod() {
		return Request.Method.GET;
	}

}
