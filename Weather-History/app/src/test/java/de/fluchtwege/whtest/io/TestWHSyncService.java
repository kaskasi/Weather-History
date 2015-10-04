package de.fluchtwege.whtest.io;

import android.content.Intent;

import com.android.volley.RequestQueue;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import de.fluchtwege.weatherhistory.BuildConfig;
import de.fluchtwege.weatherhistory.io.IOController;
import de.fluchtwege.weatherhistory.io.Messages;
import de.fluchtwege.weatherhistory.io.WeatherHistorySyncService;
import de.fluchtwege.whtest.BaseTest;
import de.fluchtwege.whtest.mock.MockRequestQueue;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class TestWHSyncService extends BaseTest {

    MockRequestQueue requestQueue;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        requestQueue = (MockRequestQueue) IOController.getRequestQueue(activity);
        requestQueue.clearQueue();
    }

    //Since Robolectric only works with Shadows, no Service can be started by an Intent
    @Test
    public void SyncService_Called_With_Forecast_Intent_Creates_Forecast_Handler() {
        final Intent forecast = new Intent(Intent.ACTION_SYNC, null, activity, WeatherHistorySyncService.class);
        forecast.putExtra(WeatherHistorySyncService.SERVICE_TO_CALL, Messages.INTENT_FORECAST);

        WeatherHistorySyncService service = Robolectric.buildService(WeatherHistorySyncService.class).create().get();
        service.onStartCommand(forecast, 0, 42);

        Assert.assertEquals(1, requestQueue.getQueueSize());
    }

    @Test
    public void SyncService_Called_With_History_Intent_Creates_Batch_History_Handler() {
        Intent history = new Intent(Intent.ACTION_SYNC, null, activity, WeatherHistorySyncService.class);
        history.putExtra(WeatherHistorySyncService.SERVICE_TO_CALL, Messages.INTENT_HISTORY);

        WeatherHistorySyncService service = Robolectric.buildService(WeatherHistorySyncService.class).create().get();
        service.onStartCommand(history, 0, 42);

        Assert.assertEquals(1, requestQueue.getQueueSize());

    }
}
