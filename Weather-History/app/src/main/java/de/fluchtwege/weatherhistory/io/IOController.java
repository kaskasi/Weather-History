package de.fluchtwege.weatherhistory.io;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class IOController {

    private static ServiceHelper serviceHelper = new WeatherHistoryServiceHelper();
    private static RequestQueue requestQueue;

    public static void setServiceHelper(ServiceHelper helper) {
        IOController.serviceHelper = helper;
    }

    public static void setRequestQueue(RequestQueue queue) {
        IOController.requestQueue = queue;
    }

    public static RequestQueue getRequestQueue(Context ctx) {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(ctx);
        }
        return requestQueue;
    }

    public static void loadCurrentForecast(Context ctx) {
        serviceHelper.loadCurrentForecast(ctx);
    }


    public static void loadHistoricalData(final Context ctx) { serviceHelper.loadHistoricalData(ctx); }
}
