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
import de.fluchtwege.whtest.mock.TestLoaderHandler;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class TestStationFragment extends BaseTest {


    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();

        TabLayout tabLayout = (TabLayout) activity.findViewById(R.id.tabs);
        activity.onTabSelected(tabLayout.getTabAt(1));

        TestLoaderHandler handler = new TestLoaderHandler();
        handler.waitForLoadFinished(activity.getStationFragment());
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
        Assert.assertEquals("7.9000001", longitudeValue);
    }

    @Test
    public void Station_Fragment_Shows_Latitude_Of_Station() {
        TextView latitude = (TextView) activity.findViewById(R.id.station_lat);
        String latitudeValue = latitude.getText().toString();
        Assert.assertEquals("54.18000031", latitudeValue);
    }

}
