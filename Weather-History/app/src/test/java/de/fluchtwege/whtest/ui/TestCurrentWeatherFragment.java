package de.fluchtwege.whtest.ui;

import android.widget.TextView;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import de.fluchtwege.weatherhistory.BuildConfig;
import de.fluchtwege.weatherhistory.R;
import de.fluchtwege.whtest.BaseTest;
import de.fluchtwege.whtest.mock.TestLoaderHandler;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class TestCurrentWeatherFragment extends BaseTest {

    @Override
    public void setUp() throws Exception {
        super.setUp();

    }

    @Test
    public void Current_Weather_Fragment_Shows_Humidity() {
        TestLoaderHandler handler = new TestLoaderHandler();
        handler.waitForLoadFinished(activity.getCurrentWeatherFragment());

        TextView humidity = (TextView) activity.findViewById(R.id.current_humidity);
        String humidityValue = humidity.getText().toString();
        Assert.assertEquals("100%", humidityValue);
    }

    @Test
    public void Current_Weather_Fragment_Shows_Visibility() {
        TextView visibility = (TextView) activity.findViewById(R.id.current_visibility);
        String visibilityValue = visibility.getText().toString();
        Assert.assertEquals("8", visibilityValue);
    }

    @Test
    public void Current_Weather_Fragment_Shows_Temperature() {
        TextView currentTemp = (TextView) activity.findViewById(R.id.current_temp);
        String temperatureValue = currentTemp.getText().toString();
        Assert.assertEquals("24°C", temperatureValue);
    }

    @Test
    public void Current_Weather_Fragment_Shows_WindSpeed() {
        TextView windSpeed = (TextView) activity.findViewById(R.id.current_windspeed);
        String windSpeedValue = windSpeed.getText().toString();
        Assert.assertEquals("11", windSpeedValue);
    }

    @Test
    public void Current_Weather_Fragment_Shows_WindDirection() {
        TextView windDirection = (TextView) activity.findViewById(R.id.current_winddir);
        String windDirectionValue = windDirection.getText().toString();
        Assert.assertEquals("EAST", windDirectionValue);
    }

    @Test
    public void Current_Weather_Fragment_Shows_FeelsLikeTemperature() {
        TextView feelsLike = (TextView) activity.findViewById(R.id.current_feelslike);
        String feelsLikeValue = feelsLike.getText().toString();
        Assert.assertEquals("10", feelsLikeValue);
    }
}
