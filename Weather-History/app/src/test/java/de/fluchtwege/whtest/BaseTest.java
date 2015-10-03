package de.fluchtwege.whtest;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.pm.ProviderInfo;
import android.util.DisplayMetrics;

import org.junit.Before;
import org.robolectric.Robolectric;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.internal.Shadow;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowContentResolver;

import de.fluchtwege.weatherhistory.io.VolleyController;
import de.fluchtwege.weatherhistory.provider.WeatherHistoryContentProvider;
import de.fluchtwege.weatherhistory.provider.WeatherHistoryContract;
import de.fluchtwege.weatherhistory.ui.MainActivity;
import de.fluchtwege.whtest.mock.MockRequestQueue;

/* Since it is not really possible to do TDD after the fact, I will create some POUT,
   to show which tests I would have created
 */
public class BaseTest {

    private static final int LARGE_PHONE_WIDTH = 1280;
    private static final int LARGE_PHONE_HEIGHT = 768;
    private static final int LARGE_TABLET_WIDTH = 3000;
    private static final int LARGE_TABLET_HEIGHT = 4048;

    protected MainActivity activity;
    private WeatherHistoryContentProvider mProvider;
    private ContentResolver mContentResolver;
    private ShadowContentResolver mShadowContentResolver;

    public enum TestDevice {
        LargeTablet,
        LargePhone
    }


    private TestDevice deviceType = TestDevice.LargePhone;

    @Before
    public void setUp() throws Exception {
        setDisplayMetricsForDeviceType(deviceType);

        MockRequestQueue queue = new MockRequestQueue();
        VolleyController.setRequestQueue(queue);

        setupShadowContentResolver();

        activity = Robolectric.setupActivity(MainActivity.class);
    }



    public void setDisplayMetricsForDeviceType(TestDevice testDeviceType) {
        DisplayMetrics displayMetrics = ShadowApplication.getInstance().getResources().getDisplayMetrics();
        if (testDeviceType == TestDevice.LargePhone) {
            displayMetrics.widthPixels = LARGE_PHONE_WIDTH;
            displayMetrics.heightPixels = LARGE_PHONE_HEIGHT;
        } else if (testDeviceType == TestDevice.LargeTablet){
            displayMetrics.widthPixels = LARGE_TABLET_WIDTH;
            displayMetrics.heightPixels = LARGE_TABLET_HEIGHT;
        }
    }

    private void setupShadowContentResolver() {
        final ContentProvider contentProvider = new WeatherHistoryContentProvider();
        ProviderInfo providerInfo = new ProviderInfo();
        providerInfo.authority = WeatherHistoryContract.CONTENT_AUTHORITY;
        contentProvider.attachInfo(RuntimeEnvironment.application, providerInfo);
        contentProvider.onCreate();
        ShadowContentResolver.registerProvider(WeatherHistoryContract.CONTENT_AUTHORITY, contentProvider);
    }


    public void setDeviceType(TestDevice deviceType) {
        this.deviceType = deviceType;
    }
}