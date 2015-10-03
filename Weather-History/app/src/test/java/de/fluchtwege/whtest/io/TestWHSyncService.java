package de.fluchtwege.whtest.io;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import de.fluchtwege.weatherhistory.BuildConfig;
import de.fluchtwege.whtest.BaseTest;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class TestWHSyncService extends BaseTest {

    @Test
    public void A_Service_Is_Registered_For_Service_Intent() {

    }

    @Test
    public void SyncService_Called_With_Forecast_Intent_Creates_Forecast_Handler() {

    }

    @Test
    public void SyncService_Called_With_History_Intent_Creates_Batch_History_Handler() {

    }
}
