package de.fluchtwege.whtest.provider;

import android.database.Cursor;
import android.net.Uri;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import de.fluchtwege.weatherhistory.BuildConfig;
import de.fluchtwege.whtest.BaseTest;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class TestWHContentProvider extends BaseTest {

    private static final String DATA_URL = "content://de.fluchtwege.weatherhistory/weather_data";
    private static final String STATION_URL = "content://de.fluchtwege.weatherhistory/weather_station";
    private static final String HISTORY_URL = "content://de.fluchtwege.weatherhistory/weather_history";

    @Test
    public void Querying_Provider_With_Weather_Data_Uri_Will_Return_Weather_Data_Cursor() {
        Uri.Builder builder = new Uri.Builder().scheme(DATA_URL);
        Cursor query = contentProvider.query(builder.build(), null, null, null, null);
        Assert.assertTrue(query.moveToFirst());
    }

    @Test
    public void Querying_Provider_With_Weather_Station_Uri_Will_Return_Weather_Station_Cursor() {
        Uri.Builder builder = new Uri.Builder().scheme(STATION_URL);
        Cursor query = contentProvider.query(builder.build(), null, null, null, null);
        Assert.assertTrue(query.moveToFirst());
    }

    @Test
    public void Querying_Provider_With_Weather_History_Uri_Will_Return_Weather_History_Cursor() {
        Uri.Builder builder = new Uri.Builder().scheme(HISTORY_URL);
        Cursor query = contentProvider.query(builder.build(), null, null, null, null);
        Assert.assertTrue(query.moveToFirst());
    }
}
