package de.fluchtwege.weatherhistory.ui;

import android.os.Bundle;

import de.fluchtwege.weatherhistory.R;


public class MainActivity extends BaseActivity  {

    private static final String LOG_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
