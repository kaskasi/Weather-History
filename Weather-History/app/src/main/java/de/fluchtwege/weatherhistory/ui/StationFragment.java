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

import de.fluchtwege.weatherhistory.R;
import de.fluchtwege.weatherhistory.Util;
import de.fluchtwege.weatherhistory.provider.WeatherHistoryContract;

public class StationFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String LOG_TAG = "StationFragment";

    private TextView countryText;
    private TextView cityText;
    private TextView longitudeText;
    private TextView latitudeText;

    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.weatherstation, container, false);
        if (getActivity().isFinishing()) {
            return root;
        }
        initializeViews(root);

        cursorLoader = getActivity().getSupportLoaderManager().restartLoader(WeatherHistoryContract.WeatherDataQuery._TOKEN_STATION, null,
                this);
        showProgress();
        return root;
    }

    private void initializeViews(ViewGroup root) {
        countryText = (TextView) root.findViewById(R.id.station_country);
        cityText = (TextView) root.findViewById(R.id.station_city);
        latitudeText = (TextView) root.findViewById(R.id.station_lat);
        longitudeText = (TextView) root.findViewById(R.id.station_lon);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
        switch (id) {
            case WeatherHistoryContract.WeatherDataQuery._TOKEN_STATION: {
                String selection = WeatherHistoryContract.WeatherDataColumns.DATE + " = ?";
                String[] selectionArgs = new String[]{"" + Util.getCurrentDateFormatted()};
                cursorLoader = new CursorLoader(getActivity(), WeatherHistoryContract.WeatherStation.buildRegistrationUri(),
                        WeatherHistoryContract.WeatherDataQuery.PROJECTION_STATION, selection, selectionArgs, null);
                break;
            }
        }
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        Log.i(LOG_TAG, "onLoadFinished id=" + loader.getId());
        switch (loader.getId()) {
            case WeatherHistoryContract.WeatherDataQuery._TOKEN_STATION: {
                if (cursor.moveToFirst()) {
                    bindDataToViews(cursor);
                    hideProgress();
                }
                break;
            }
        }
    }

    private void bindDataToViews(Cursor cursor) {
        countryText.setText(parseCountry(cursor));
        cityText.setText(parseCity(cursor));
        longitudeText.setText(parseLongitude(cursor));
        latitudeText.setText(parseLatitude(cursor));
    }

    private String parseLatitude(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.COUNTRY));
    }

    private String parseLongitude(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.CITY));
    }

    private String parseCity(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.LON));
    }

    private String parseCountry(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.LAT));
    }

    @Override
    public void onLoaderReset(Loader<Cursor> arg0) {
    }
}
