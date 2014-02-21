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

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import de.fluchtwege.weatherhistory.R;
import de.fluchtwege.weatherhistory.Util;
import de.fluchtwege.weatherhistory.provider.BitmapLruCache;
import de.fluchtwege.weatherhistory.provider.WeatherHistoryContract;

/**
 * Created by jkettner on 20.02.14.
 */
public class CurrentWeatherFragment extends BaseFragment  implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String WEB_CAM_URL = "http://icons.wunderground.com/webcamramdisk/w/e/webcamstraveldot/7722/current.jpg";
    private static final String LOG_TAG = "CurrentWeatherFragment";
    private TextView mFeelsLikeTV = null;
    private TextView mWindSpeedTV = null;
    private TextView mWindDirTV = null;
    private TextView mHumidityTV = null;
    private TextView mVisibilityTV = null;
    private TextView mTempTV = null;
    private NetworkImageView mIconNIV = null;

    private Loader<Cursor> mLoader = null;
    private RequestQueue mRequestQueue = null;
    private ImageLoader mImageLoader = null;

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.currentweather, container, false);
        if (getActivity().isFinishing()) {
            return root;
        }
        mTempTV = (TextView) root.findViewById(R.id.current_temp);
        mFeelsLikeTV = (TextView) root.findViewById(R.id.current_feelslike);
        mWindSpeedTV = (TextView) root.findViewById(R.id.current_windspeed);
        mWindDirTV = (TextView) root.findViewById(R.id.current_winddir);
        mHumidityTV= (TextView) root.findViewById(R.id.current_humidity);
        mVisibilityTV = (TextView) root.findViewById(R.id.current_visibility);
        mIconNIV = (NetworkImageView) root.findViewById(R.id.current_image);
        NetworkImageView mWebcamNIV = (NetworkImageView) root.findViewById(R.id.webcam_image);


        mLoader = getActivity().getSupportLoaderManager().restartLoader(WeatherHistoryContract.WeatherDataQuery._TOKEN_ALL, null,
                this);

        mRequestQueue = Volley.newRequestQueue(getActivity());
        mImageLoader = new ImageLoader(mRequestQueue, BitmapLruCache.getInstance());
        mWebcamNIV.setImageUrl(WEB_CAM_URL, mImageLoader);
        showProgress();

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

                    String winddir = cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.WIND_DIR));
                    String windspeed = cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.WIND_KPH));
                    String feelslike = cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.FEELSLIKE_C));
                    String iconurl = cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.ICON_URL));
                    String visibility = cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.VISIBILITY_KM));
                    String humidity = cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.RELATIVE_HUMIDITY));
                    String tempc = cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.TEMP_C));

                    mWindSpeedTV.setText(windspeed);
                    mWindDirTV.setText(winddir);
                    mFeelsLikeTV.setText(feelslike);
                    mVisibilityTV.setText(visibility);
                    mHumidityTV.setText(humidity);
                    mTempTV.setText(tempc+ " °C");
                    mIconNIV.setImageUrl(iconurl, mImageLoader);

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
