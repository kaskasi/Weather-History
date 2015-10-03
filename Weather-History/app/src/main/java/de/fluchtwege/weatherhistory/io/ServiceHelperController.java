package de.fluchtwege.weatherhistory.io;

import android.content.Context;

public class ServiceHelperController {

    private static ServiceHelper serviceHelper = new WeatherHistoryServiceHelper();

    public static void setServiceHelper(ServiceHelper helper) {
        ServiceHelperController.serviceHelper = helper;
    }

    public static void loadCurrentForecast(Context ctx) {
        serviceHelper.loadCurrentForecast(ctx);
    }


    public static void loadHistoricalData(final Context ctx) {
        serviceHelper.loadHistoricalData(ctx);
    }
}
