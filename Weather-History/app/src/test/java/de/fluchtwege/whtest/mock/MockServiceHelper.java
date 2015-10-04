package de.fluchtwege.whtest.mock;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import de.fluchtwege.weatherhistory.io.ForecastHandler;
import de.fluchtwege.weatherhistory.io.ServiceHelper;

public class MockServiceHelper implements ServiceHelper {

    private boolean blockRequests = false;

    @Override
    public void loadCurrentForecast(Context ctx) {
        if (blockRequests) { return; }

        ForecastHandler handler = new ForecastHandler(ctx);
        JSONObject jsonResonse = null;
        try {
            jsonResonse = new JSONObject(MockMessages.FORECAST_RESULT);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        handler.onResponse(jsonResonse);
    }

    @Override
    public void loadHistoricalData(Context ctx) {

    }

    public void setBlockRequests(boolean blockRequests) {
        this.blockRequests = blockRequests;
    }
}
