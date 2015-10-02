package de.fluchtwege.whtest;

import android.util.DisplayMetrics;

import org.junit.Before;
import org.robolectric.Robolectric;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.util.ActivityController;

import de.fluchtwege.weatherhistory.ui.MainActivity;

public class BaseTest {

    private static final int LARGE_PHONE_WIDTH = 1280;
    private static final int LARGE_PHONE_HEIGHT = 752;
    private static final int LARGE_TABLET_WIDTH = 640;
    private static final int LARGE_TABLET_HEIGHT = 384;

    private MainActivity activity;
    private int screenWidth = LARGE_PHONE_WIDTH;
    private int screenHeight = LARGE_PHONE_HEIGHT;

    public enum TestDeviceType {
        LargeTablet,
        LargePhone
    }

    protected ActivityController<MainActivity> controller;



    @Before
    public void setUp() throws Exception {

        DisplayMetrics displayMetrics = ShadowApplication.getInstance().getResources().getDisplayMetrics();
        displayMetrics.density = 2;
        displayMetrics.widthPixels = screenWidth;
        displayMetrics.heightPixels = screenHeight;

        activity = Robolectric.setupActivity(MainActivity.class);
    }

}