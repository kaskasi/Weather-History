package de.fluchtwege.weatherhistory.io;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class WeatherHistoryServiceHelper {

    private static final String LOG_TAG = "WeatherHistoryServiceHelper";

    public static void loadCurrentForecast(Context ctx) {
        Log.d(LOG_TAG, "loadCurrentForecast");
        final Intent forecast = new Intent(Intent.ACTION_SYNC, null, ctx, WeatherHistorySyncService.class);
        forecast.putExtra(WeatherHistorySyncService.SERVICE_TO_CALL, Messages.INTENT_FORECAST);
        ctx.startService(forecast);
    }

    public static void loadHistoricalData(final Context ctx) {
        Log.d(LOG_TAG, "loadHistoricalData");
        long now = System.currentTimeMillis();

        Intent history = new Intent(Intent.ACTION_SYNC, null, ctx, WeatherHistorySyncService.class);
        history.putExtra(WeatherHistorySyncService.SERVICE_TO_CALL, Messages.INTENT_HISTORY);
        ctx.startService(history);

    }
}
