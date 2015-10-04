package de.fluchtwege.whtest.ui;

import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.LinearLayout;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import de.fluchtwege.weatherhistory.BuildConfig;
import de.fluchtwege.weatherhistory.R;
import de.fluchtwege.whtest.BaseTest;

//will not create tests for external library
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class TestWeatherHistoryFragment extends BaseTest {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        TabLayout tabs = (TabLayout) activity.findViewById(R.id.tabs);
        activity.onTabSelected(tabs.getTabAt(2));
    }

    @Test
    public void Station_Fragment_Shows_History_Container() {
        LinearLayout container = (LinearLayout) activity.findViewById(R.id.graph_container);
        Assert.assertEquals(LinearLayout.VISIBLE, container.getVisibility());
    }
}
