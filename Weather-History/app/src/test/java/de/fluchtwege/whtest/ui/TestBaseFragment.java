package de.fluchtwege.whtest.ui;

import android.support.design.widget.TabLayout;
import android.widget.RelativeLayout;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import de.fluchtwege.weatherhistory.BuildConfig;
import de.fluchtwege.weatherhistory.R;
import de.fluchtwege.weatherhistory.io.IOController;
import de.fluchtwege.weatherhistory.ui.CurrentWeatherFragment;
import de.fluchtwege.weatherhistory.ui.StationFragment;
import de.fluchtwege.whtest.BaseTest;
import de.fluchtwege.whtest.mock.MockServiceHelper;
import de.fluchtwege.whtest.mock.TestLoaderHandler;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class TestBaseFragment extends BaseTest {

    @Override
    public void setUp() throws Exception {
        serviceHelper = new MockServiceHelper();
        serviceHelper.setBlockRequests(true);
        super.setUp();
    }

    @Test
    public void While_Loader_Is_Not_Finished_Fragment_Shows_ProgressBar() {
        TabLayout tabs = (TabLayout) activity.findViewById(R.id.tabs);
        activity.onTabSelected(tabs.getTabAt(1));

        RelativeLayout progress = (RelativeLayout) activity.findViewById(R.id.progress);
        Assert.assertEquals(RelativeLayout.VISIBLE, progress.getVisibility());
    }

    @Test
    public void When_Loader_Is_Done_Fragment_Hides_Progress() {
        TabLayout tabs = (TabLayout) activity.findViewById(R.id.tabs);
        activity.onTabSelected(tabs.getTabAt(1));

        RelativeLayout progress = (RelativeLayout) activity.findViewById(R.id.progress);
        Assert.assertEquals(RelativeLayout.GONE, progress.getVisibility());
    }
}
