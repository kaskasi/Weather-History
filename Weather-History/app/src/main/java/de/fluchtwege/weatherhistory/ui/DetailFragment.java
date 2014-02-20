package de.fluchtwege.weatherhistory.ui;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

/**
 * Created by jkettner on 20.02.14.
 */
public class DetailFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String LOG_TAG = "DetailFragment";
    TextView highTV = null;
    TextView lowTV = null;
    private Loader<Cursor> mLoader = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.detail, container, false);
        if (getActivity().isFinishing()) {
            return root;
        }
        highTV = (TextView) root.findViewById(R.id.high);
        lowTV = (TextView) root.findViewById(R.id.low);
        mLoader = getActivity().getSupportLoaderManager().restartLoader(WeatherHistoryContract.WeatherDataQuery._TOKEN_ALL, null,
                this);
        return root;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
        switch (id) {
            case WeatherHistoryContract.WeatherDataQuery._TOKEN_ALL: {

                String selection = WeatherHistoryContract.WeatherDataColumns.DATE + " = ?";
                String[] selectionArgs = new String[]{"" + Util.getCurrentDateFormatted()};
                mLoader = new CursorLoader(getActivity(), WeatherHistoryContract.WeatherData.buildRegistrationUri(),
                        WeatherHistoryContract.WeatherDataQuery.PROJECTION, selection, selectionArgs, null);
                break;
            }
        }
        return mLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        Log.i(LOG_TAG, "onLoadFinished id=" + loader.getId());
        switch (loader.getId()) {
            case WeatherHistoryContract.WeatherDataQuery._TOKEN_ALL:  {
                if (cursor.moveToFirst()) {
                    int high = 0;
                    int low = 0;
                    high = cursor.getInt(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.MAX_CELSIUS));
                    low = cursor.getInt(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.MIN_CELSIUS));
                    highTV.setText("" + high);
                    lowTV.setText("" + low);
                }
                break;
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> arg0) {
    }
}
