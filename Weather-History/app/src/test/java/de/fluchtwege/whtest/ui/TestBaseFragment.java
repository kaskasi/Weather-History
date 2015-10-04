package de.fluchtwege.whtest.ui;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import de.fluchtwege.weatherhistory.BuildConfig;
import de.fluchtwege.weatherhistory.ui.CurrentWeatherFragment;
import de.fluchtwege.whtest.BaseTest;
import de.fluchtwege.whtest.mock.TestLoaderHandler;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class TestBaseFragment extends BaseTest {

    @Test
    public void While_Loader_Is_Not_Finished_Fragment_Shows_ProgressBar() {
        CurrentWeatherFragment currentWeatherFragment = activity.getCurrentWeatherFragment();
        TestLoaderHandler handler = new TestLoaderHandler();
        handler.waitForLoadFinished(currentWeatherFragment);
        Assert.assertTrue(currentWeatherFragment.isShowingProgress());
    }

    @Test
    public void When_Loader_Is_Done_Fragment_Hides_Progress() {

    }
}
