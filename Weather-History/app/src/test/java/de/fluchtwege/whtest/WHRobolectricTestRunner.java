package de.fluchtwege.whtest;

import android.app.Activity;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.manifest.AndroidManifest;
import org.robolectric.res.Fs;

/**
 * Created by jankettner on 02/10/15.
 */
public class WHRobolectricTestRunner extends RobolectricTestRunner {
    private static final int MAX_SDK_SUPPORTED_BY_ROBOLECTRIC = 18;

    public WHRobolectricTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    @Override
    protected AndroidManifest getAppManifest(Config config) {
        String manifestProperty = "../app/src/main/AndroidManifest.xml";
        String resProperty = "../app/src/main/res";
        String packageProperty = "de.fluchtwege.weatherhistory";
        return new AndroidManifest(Fs.fileFromPath(manifestProperty), Fs.fileFromPath(resProperty), Fs.fileFromPath(packageProperty) ) {
            @Override
            public int getTargetSdkVersion() {
                return MAX_SDK_SUPPORTED_BY_ROBOLECTRIC;
            }

            @Override
            public String getThemeRef(Class<? extends Activity> activityClass) {
                return "@style/RoboAppTheme";
            }
        };
    }
}