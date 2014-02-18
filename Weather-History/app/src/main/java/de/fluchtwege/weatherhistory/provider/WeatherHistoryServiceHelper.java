package com.example.footest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class WeatherHistoryServiceHelper {

	public static void loadCurrentForecast(Context ctx) {
		final Intent forecast = new Intent(Intent.ACTION_SYNC, null, ctx, WeatherHistorySyncService.class);
		forecast.putExtra(WeatherHistorySyncService.SERVICE_TO_CALL, Messages.INTENT_FORECAST);
		ctx.startService(forecast);
	}

	public static void loadHistoricalData(Context ctx, String date) {
		final Intent history = new Intent(Intent.ACTION_SYNC, null, ctx, WeatherHistorySyncService.class);
		history.putExtra(WeatherHistorySyncService.SERVICE_TO_CALL, Messages.INTENT_HISTORY);
		Bundle bundle = new Bundle();
		bundle.putString(Messages.HISTORY_DATE, date);
		history.putExtra(Messages.PARAMETERS, bundle);
		ctx.startService(history);
	}

}
