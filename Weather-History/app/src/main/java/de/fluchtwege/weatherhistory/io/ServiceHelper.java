package de.fluchtwege.weatherhistory.io;

import android.content.Context;

public interface ServiceHelper {

    void loadCurrentForecast(Context ctx);

    void loadHistoricalData(final Context ctx);
}
