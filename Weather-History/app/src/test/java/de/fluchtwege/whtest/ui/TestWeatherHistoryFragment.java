package de.fluchtwege.whtest.ui;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import de.fluchtwege.weatherhistory.BuildConfig;
import de.fluchtwege.whtest.BaseTest;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class TestWeatherHistoryFragment extends BaseTest {

}
