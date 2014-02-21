package de.fluchtwege.weatherhistory.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.widget.FrameLayout;

import de.fluchtwege.weatherhistory.R;


public class MainActivity extends BaseActivity implements ActionBar.TabListener {

    private static final String LOG_TAG = "MainActivity";
    private static final String TAG_CURRENT = "current";
    private static final String TAG_HISTORY = "history";
    private static final String TAG_STATION = "station";
    private boolean isTablet = false;
    private FrameLayout mTabContainerFL = null;
    private StationFragment mStationSF = null;
    private HistoryFragment mHistoryHF = null;
    private CurrentWeatherFragment mCurrentCWF = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isTablet = findViewById(R.id.main_container) != null;
        if (!isTablet) {
            mTabContainerFL = (FrameLayout) findViewById(R.id.tab_container);

            ActionBar actionBar = getSupportActionBar();

            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
            actionBar.addTab(actionBar.newTab().setText(getString(R.string.current_header)).setTabListener(this));
            actionBar.addTab(actionBar.newTab().setText(getString(R.string.station_header)).setTabListener(this));
            actionBar.addTab(actionBar.newTab().setText(getString(R.string.history_header)).setTabListener(this));

        }

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (tab.getText().equals(getString(R.string.station_header))) {
            mCurrentCWF = (CurrentWeatherFragment) getSupportFragmentManager().findFragmentByTag(TAG_CURRENT);
            if (mCurrentCWF != null && !mCurrentCWF.isDetached()) {
                ft.hide(mCurrentCWF);
            }
            mHistoryHF = (HistoryFragment) getSupportFragmentManager().findFragmentByTag(TAG_HISTORY);
            if (mHistoryHF != null && !mHistoryHF.isDetached()) {
                fragmentTransaction.hide(mHistoryHF);
            }
            mStationSF = (StationFragment) getSupportFragmentManager().findFragmentByTag(TAG_STATION);
            if (mStationSF != null && !mStationSF.isDetached()) {
                ft.show(mStationSF);
            } else {
                mStationSF = new StationFragment();
                ft.add(R.id.tab_container, mStationSF, TAG_STATION);
            }

        } else if (tab.getText().equals(getString(R.string.current_header))) {
            mStationSF = (StationFragment) getSupportFragmentManager().findFragmentByTag(TAG_STATION);
            if (mStationSF != null && !mStationSF.isDetached()) {
                ft.hide(mStationSF);
            }
            mHistoryHF = (HistoryFragment) getSupportFragmentManager().findFragmentByTag(TAG_HISTORY);
            if (mHistoryHF != null && !mHistoryHF.isDetached()) {
                ft.hide(mHistoryHF);
            }
            mCurrentCWF = (CurrentWeatherFragment) getSupportFragmentManager().findFragmentByTag(TAG_CURRENT);
            if (mCurrentCWF != null && !mCurrentCWF.isDetached()) {
                ft.show(mCurrentCWF);
            } else {
                mCurrentCWF = new CurrentWeatherFragment();
                ft.add(R.id.tab_container, mCurrentCWF, TAG_CURRENT);
            }
        } else if (tab.getText().equals(getString(R.string.history_header))) {
            mStationSF = (StationFragment) getSupportFragmentManager().findFragmentByTag(TAG_STATION);
            if (mStationSF != null && !mStationSF.isDetached()) {
                ft.hide(mStationSF);
            }
            mCurrentCWF = (CurrentWeatherFragment) getSupportFragmentManager().findFragmentByTag(TAG_CURRENT);
            if (mCurrentCWF != null && !mCurrentCWF.isDetached()) {
                ft.hide(mCurrentCWF);
            }
            mHistoryHF = (HistoryFragment) getSupportFragmentManager().findFragmentByTag(TAG_HISTORY);
            if (mHistoryHF != null && !mHistoryHF.isDetached()) {
                ft.show(mHistoryHF);
            } else {
                mHistoryHF = new HistoryFragment();
                ft.add(R.id.tab_container, mHistoryHF, TAG_HISTORY);
            }
        }
        ft.commit();
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }
}
