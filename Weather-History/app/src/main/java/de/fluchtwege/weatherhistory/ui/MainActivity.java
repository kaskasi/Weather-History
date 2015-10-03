package de.fluchtwege.weatherhistory.ui;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;

import de.fluchtwege.weatherhistory.R;


public class MainActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {

    private static final String LOG_TAG = "MainActivity";
    private static final String TAG_CURRENT = "current";
    private static final String TAG_HISTORY = "history";
    private static final String TAG_STATION = "station";

    private StationFragment stationFragment = null;
    private HistoryFragment historyFragment = null;
    private CurrentWeatherFragment currentWeatherFragment = null;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        if (tabLayout != null) {
            initializeTabLayout(tabLayout);
        }
    }

    private void initializeTabLayout(TabLayout tabLayout) {
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.current_header)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.station_header)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.history_header)));
        tabLayout.setOnTabSelectedListener(this);
        onTabSelected(tabLayout.getTabAt(0));
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (tab.getText().equals(getString(R.string.station_header))) {
            onStationTabSelected(ft);
        } else if (tab.getText().equals(getString(R.string.current_header))) {
            onCurrentWeatherTabSelected(ft);
        } else if (tab.getText().equals(getString(R.string.history_header))) {
            onHistoryTabSelected(ft);
        }
        ft.commit();
    }

    private void onHistoryTabSelected(FragmentTransaction ft) {
        stationFragment = (StationFragment) getSupportFragmentManager().findFragmentByTag(TAG_STATION);
        if (stationFragment != null && !stationFragment.isDetached()) {
            ft.hide(stationFragment);
        }
        currentWeatherFragment = (CurrentWeatherFragment) getSupportFragmentManager().findFragmentByTag(TAG_CURRENT);
        if (currentWeatherFragment != null && !currentWeatherFragment.isDetached()) {
            ft.hide(currentWeatherFragment);
        }
        historyFragment = (HistoryFragment) getSupportFragmentManager().findFragmentByTag(TAG_HISTORY);
        if (historyFragment != null && !historyFragment.isDetached()) {
            ft.show(historyFragment);
        } else {
            historyFragment = new HistoryFragment();
            ft.add(R.id.tab_container, historyFragment, TAG_HISTORY);
        }
    }

    private void onCurrentWeatherTabSelected(FragmentTransaction ft) {
        stationFragment = (StationFragment) getSupportFragmentManager().findFragmentByTag(TAG_STATION);
        if (stationFragment != null && !stationFragment.isDetached()) {
            ft.hide(stationFragment);
        }
        historyFragment = (HistoryFragment) getSupportFragmentManager().findFragmentByTag(TAG_HISTORY);
        if (historyFragment != null && !historyFragment.isDetached()) {
            ft.hide(historyFragment);
        }
        currentWeatherFragment = (CurrentWeatherFragment) getSupportFragmentManager().findFragmentByTag(TAG_CURRENT);
        if (currentWeatherFragment != null && !currentWeatherFragment.isDetached()) {
            ft.show(currentWeatherFragment);
        } else {
            currentWeatherFragment = new CurrentWeatherFragment();
            ft.add(R.id.tab_container, currentWeatherFragment, TAG_CURRENT);
        }
    }

    private void onStationTabSelected(FragmentTransaction ft) {
        currentWeatherFragment = (CurrentWeatherFragment) getSupportFragmentManager().findFragmentByTag(TAG_CURRENT);
        if (currentWeatherFragment != null && !currentWeatherFragment.isDetached()) {
            ft.hide(currentWeatherFragment);
        }
        historyFragment = (HistoryFragment) getSupportFragmentManager().findFragmentByTag(TAG_HISTORY);
        if (historyFragment != null && !historyFragment.isDetached()) {
            ft.hide(historyFragment);
        }
        stationFragment = (StationFragment) getSupportFragmentManager().findFragmentByTag(TAG_STATION);
        if (stationFragment != null && !stationFragment.isDetached()) {
            ft.show(stationFragment);
        } else {
            stationFragment = new StationFragment();
            ft.add(R.id.tab_container, stationFragment, TAG_STATION);
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    public TabLayout getTabLayout() {
        return tabLayout;
    }
}
