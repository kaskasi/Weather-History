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

public class CurrentWeatherFragment extends BaseFragment  implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String LOG_TAG = "CurrentWeatherFragment";

    private static final String WEB_CAM_URL = "http://icons.wunderground.com/webcamramdisk/w/e/webcamstraveldot/7722/current.jpg";

    private TextView feelsLikeTemperatureTV = null;
    private TextView windSpeedText = null;
    private TextView windDirText = null;
    private TextView humidityText = null;
    private TextView visibilityText = null;
    private TextView temperatureText = null;
    private NetworkImageView iconImage = null;
    private NetworkImageView webcamImage;

    private Loader<Cursor> loader = null;
    private RequestQueue requestQueue = null;
    private ImageLoader imageLoader = null;

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.currentweather, container, false);
        if (getActivity().isFinishing()) {
            return root;
        }
        initializeViews(root);
        loader = getActivity().getSupportLoaderManager().restartLoader(WeatherHistoryContract.WeatherDataQuery._TOKEN_ALL, null, this);

        requestQueue = Volley.newRequestQueue(getActivity());
        imageLoader = new ImageLoader(requestQueue, BitmapLruCache.getInstance());
        webcamImage.setImageUrl(WEB_CAM_URL, imageLoader);
        showProgress();

        return root;
    }

    private void initializeViews(ViewGroup root) {
        temperatureText = (TextView) root.findViewById(R.id.current_temp);
        feelsLikeTemperatureTV = (TextView) root.findViewById(R.id.current_feelslike);
        windSpeedText = (TextView) root.findViewById(R.id.current_windspeed);
        windDirText = (TextView) root.findViewById(R.id.current_winddir);
        humidityText = (TextView) root.findViewById(R.id.current_humidity);
        visibilityText = (TextView) root.findViewById(R.id.current_visibility);
        iconImage = (NetworkImageView) root.findViewById(R.id.current_image);
        webcamImage = (NetworkImageView) root.findViewById(R.id.webcam_image);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
        switch (id) {
            case WeatherHistoryContract.WeatherDataQuery._TOKEN_ALL: {
                String selection = WeatherHistoryContract.WeatherDataColumns.DATE + " = ?";
                String[] selectionArgs = new String[]{"" + Util.getCurrentDateFormatted()};
                loader = new CursorLoader(getActivity(), WeatherHistoryContract.WeatherData.buildRegistrationUri(),
                        WeatherHistoryContract.WeatherDataQuery.PROJECTION, selection, selectionArgs, null);
                break;
            }
        }
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        Log.i(LOG_TAG, "onLoadFinished id=" + loader.getId());
        switch (loader.getId()) {
            case WeatherHistoryContract.WeatherDataQuery._TOKEN_ALL:  {
                if (cursor.moveToFirst()) {
                    bindDataToViews(cursor);
                    hideProgress();
                }
                break;
            }
        }
    }

    private void bindDataToViews(Cursor cursor) {
        windSpeedText.setText(parseWindSpeed(cursor));
        windDirText.setText(parseWindDir(cursor));
        feelsLikeTemperatureTV.setText(parseFeelsLikeTemperature(cursor));
        visibilityText.setText(parseVisibility(cursor));
        humidityText.setText(parseHumidity(cursor));
        temperatureText.setText(parseTemperature(cursor) + " °C");
        iconImage.setImageUrl(parseIconUrl(cursor), imageLoader);
    }

    private String parseHumidity(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.RELATIVE_HUMIDITY));
    }

    private String parseTemperature(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.TEMP_C));
    }

    private String parseVisibility(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.VISIBILITY_KM));
    }

    private String parseIconUrl(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.ICON_URL));
    }

    private String parseFeelsLikeTemperature(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.FEELSLIKE_C));
    }

    private String parseWindSpeed(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.WIND_KPH));
    }

    private String parseWindDir(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.WIND_DIR));
    }

    @Override
    public void onLoaderReset(Loader<Cursor> arg0) {
    }
}
