package de.fluchtwege.whtest.ui;

import android.support.design.widget.TabLayout;
import android.widget.TextView;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import de.fluchtwege.weatherhistory.BuildConfig;
import de.fluchtwege.weatherhistory.R;
import de.fluchtwege.whtest.BaseTest;

//these tests are failing
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class TestStationFragment extends BaseTest {


    @Before
    @Override
    public void setUp() throws Exception {
        setDeviceType(TestDevice.LargeTablet);
        super.setUp();
        TabLayout.Tab stationTab = activity.getTabLayout().getTabAt(1);
        activity.onTabSelected(stationTab);
    }

    @Test
    public void Station_Fragment_Shows_Country_Of_Station() {
        TextView stationCountry = (TextView) activity.findViewById(R.id.station_country);
        String countryValue = stationCountry.getText().toString();
        Assert.assertEquals("Germany", countryValue);
    }

    @Test
    public void Station_Fragment_Shows_City_Of_Station() {
        TextView stationCity = (TextView) activity.findViewById(R.id.station_city);
        String cityValue = stationCity.getText().toString();
        Assert.assertEquals("Helgoland", cityValue);
    }

    @Test
    public void Station_Fragment_Shows_Longitude_Of_Station() {
        TextView longitude = (TextView) activity.findViewById(R.id.station_lon);
        String longitudeValue = longitude.getText().toString();
        Assert.assertEquals("54.18", longitudeValue);
    }

    @Test
    public void Station_Fragment_Shows_Latitude_Of_Station() {
        TextView latitude = (TextView) activity.findViewById(R.id.station_lat);
        String latitudeValue = latitude.getText().toString();
        Assert.assertEquals("7.9", latitudeValue);
    }

}
