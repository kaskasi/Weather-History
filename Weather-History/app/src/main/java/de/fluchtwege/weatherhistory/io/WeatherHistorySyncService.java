package de.fluchtwege.weatherhistory.io;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;

public class WeatherHistorySyncService extends IntentService {

	public static final String TAG = "WeatherHistorySyncService";

	public static final String SERVICE_TO_CALL = "service_to_call";

	public WeatherHistorySyncService() {
		super(TAG);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		int serviceToCall = intent.getIntExtra(SERVICE_TO_CALL, -1);
		switch (serviceToCall) {
		case Messages.INTENT_FORECAST: {
			new ForecastHandler(this).enqueueRequests();
			break;
		}
		case Messages.INTENT_HISTORY: {
			new BatchHistoryHandler(this).enqueueRequests();
			break;
		}
		default: {
			break;
		}
		}

	}

}
