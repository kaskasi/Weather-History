package de.fluchtwege.weatherhistory.ui;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import de.fluchtwege.weatherhistory.R;
import de.fluchtwege.weatherhistory.Util;
import de.fluchtwege.weatherhistory.provider.WeatherHistoryContract;

/**
 * Created by jkettner on 20.02.14.
 */
public class StationFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor> {



    private static final String LOG_TAG = "StationFragment";

    private TextView mCountryTV = null;
    private TextView mCityTV = null;
    private TextView mLonTV = null;
    private TextView mLatTV = null;
    private NetworkImageView mStationNIV = null;

    private Loader<Cursor> mLoader = null;


    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.weatherstation, container, false);
        if (getActivity().isFinishing()) {
            return root;
        }

        mCountryTV = (TextView) root.findViewById(R.id.station_country);
        mCityTV = (TextView) root.findViewById(R.id.station_city);
        mLatTV= (TextView) root.findViewById(R.id.station_lat);
        mLonTV = (TextView) root.findViewById(R.id.station_lon);


        mLoader = getActivity().getSupportLoaderManager().restartLoader(WeatherHistoryContract.WeatherDataQuery._TOKEN_STATION, null,
                this);
        showProgress();
        return root;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
        switch (id) {
            case WeatherHistoryContract.WeatherDataQuery._TOKEN_STATION: {

                String selection = WeatherHistoryContract.WeatherDataColumns.DATE + " = ?";
                String[] selectionArgs = new String[]{"" + Util.getCurrentDateFormatted()};
                mLoader = new CursorLoader(getActivity(), WeatherHistoryContract.WeatherStation.buildRegistrationUri(),
                        WeatherHistoryContract.WeatherDataQuery.PROJECTION_STATION, selection, selectionArgs, null);
                break;
            }
        }
        return mLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        Log.i(LOG_TAG, "onLoadFinished id=" + loader.getId());
        switch (loader.getId()) {
            case WeatherHistoryContract.WeatherDataQuery._TOKEN_STATION: {
                if (cursor.moveToFirst()) {
                    String lat = cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.LAT));
                    String lon = cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.LON));
                    String city = cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.CITY));
                    String country = cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.COUNTRY));

                    mCountryTV.setText(country);
                    mCityTV.setText(city);
                    mLonTV.setText(lon);
                    mLatTV.setText(lat);



                    hideProgress();
                }
                break;
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> arg0) {
    }
}
