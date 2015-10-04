package de.fluchtwege.whtest.io;

import android.content.Intent;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import de.fluchtwege.weatherhistory.BuildConfig;
import de.fluchtwege.weatherhistory.io.WeatherHistoryServiceHelper;
import de.fluchtwege.weatherhistory.io.WeatherHistorySyncService;
import de.fluchtwege.whtest.BaseTest;


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)

public class TestWHServiceHelper extends BaseTest {

    @Test
    public void Calling_Load_Forecast_Starts_Sync_Service() {
        WeatherHistoryServiceHelper serviceHelper = new WeatherHistoryServiceHelper();
        serviceHelper.loadCurrentForecast(activity);

        Intent serviceIntent = ShadowApplication.getInstance().peekNextStartedService();
        Assert.assertEquals(WeatherHistorySyncService.class.getCanonicalName(),
                serviceIntent.getComponent().getClassName());
    }

    @Test
    public void Calling_Load_Historical_Data_Starts_Sync_Service() {
        WeatherHistoryServiceHelper serviceHelper = new WeatherHistoryServiceHelper();
        serviceHelper.loadHistoricalData(activity);

        Intent serviceIntent = ShadowApplication.getInstance().peekNextStartedService();
        Assert.assertEquals(WeatherHistorySyncService.class.getCanonicalName(),
                serviceIntent.getComponent().getClassName());
    }

}
