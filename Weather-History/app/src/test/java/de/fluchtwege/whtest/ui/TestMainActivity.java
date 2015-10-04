package de.fluchtwege.whtest.ui;


import android.support.design.widget.TabLayout;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import de.fluchtwege.weatherhistory.BuildConfig;
import de.fluchtwege.weatherhistory.R;
import de.fluchtwege.weatherhistory.ui.CurrentWeatherFragment;
import de.fluchtwege.weatherhistory.ui.HistoryFragment;
import de.fluchtwege.weatherhistory.ui.StationFragment;
import de.fluchtwege.whtest.BaseTest;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)

public class TestMainActivity extends BaseTest {

    @Test
    public void On_Phone_MainActivity_Will_Show_TabLayout() {
        TabLayout tabLayout = (TabLayout) activity.findViewById(R.id.tabs);
        Assert.assertNotNull(tabLayout);
        Assert.assertEquals(TabLayout.VISIBLE, tabLayout.getVisibility());
    }

    //TODO: failing - need to call measure in BaseTest, else deviceType is ignored
    @Test
    public void On_Tablet_MainActivity_Will_Not_Show_TabLayout() {
        setDeviceType(TestDevice.LargeTablet);
        TabLayout tabLayout = (TabLayout) activity.findViewById(R.id.tabs);
        Assert.assertNull(tabLayout);
    }

    @Test
    public void Selecting_Tab_StationHistory_Will_Show_Only_Station_Fragment() {
        TabLayout tabLayout = (TabLayout) activity.findViewById(R.id.tabs);
        activity.onTabSelected(tabLayout.getTabAt(1));
        StationFragment stationFragment = activity.getStationFragment();
        Assert.assertNotNull(stationFragment);

        CurrentWeatherFragment currentWeatherFragment = activity.getCurrentWeatherFragment();
        Assert.assertTrue(currentWeatherFragment.isHidden());
    }

    @Test
    public void Selecting_Tab_History_Will_Show_Only_History_Fragment() {
        TabLayout tabLayout = (TabLayout) activity.findViewById(R.id.tabs);
        activity.onTabSelected(tabLayout.getTabAt(2));
        HistoryFragment historyFragment = activity.getHistoryFragment();
        Assert.assertNotNull(historyFragment);
        Assert.assertTrue(historyFragment.isAdded());
        Assert.assertTrue(historyFragment.isVisible());

        CurrentWeatherFragment currentWeatherFragment = activity.getCurrentWeatherFragment();
        Assert.assertTrue(currentWeatherFragment.isHidden());
    }

    @Test
    public void Selecting_Tab_Current_Weather_Will_Instantiate_Current_Weather_Fragment() {
        TabLayout tabLayout = (TabLayout) activity.findViewById(R.id.tabs);
        activity.onTabSelected(tabLayout.getTabAt(0));
        CurrentWeatherFragment currentWeatherFragment = activity.getCurrentWeatherFragment();

        Assert.assertNotNull(currentWeatherFragment);
        Assert.assertTrue(currentWeatherFragment.isResumed());
    }



}
