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
import com.squareup.otto.Subscribe;

import de.fluchtwege.weatherhistory.R;
import de.fluchtwege.weatherhistory.Util;
import de.fluchtwege.weatherhistory.io.IOController;
import de.fluchtwege.weatherhistory.model.CurrentWeather;
import de.fluchtwege.weatherhistory.provider.BitmapLruCache;
import de.fluchtwege.weatherhistory.provider.Otto;
import de.fluchtwege.weatherhistory.provider.WeatherHistoryContract;

public class CurrentWeatherFragment extends BaseFragment {

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

    private RequestQueue requestQueue = null;
    private ImageLoader imageLoader = null;

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.currentweather, container, false);
        if (getActivity().isFinishing()) {
            return root;
        }
        initializeViews(root);
        initializeNetworkImageView(root);

        Otto.getBus().register(this);
        Otto.getBus().post(createLoader());

        showProgress();
        return root;
    }

    private void initializeNetworkImageView(ViewGroup root) {
        webcamImage = (NetworkImageView) root.findViewById(R.id.webcam_image);
        requestQueue = IOController.getRequestQueue(getActivity());
        imageLoader = new ImageLoader(requestQueue, BitmapLruCache.getInstance());
        webcamImage.setImageUrl(WEB_CAM_URL, imageLoader);
    }

    private void initializeViews(ViewGroup root) {
        temperatureText = (TextView) root.findViewById(R.id.current_temp);
        feelsLikeTemperatureTV = (TextView) root.findViewById(R.id.current_feelslike);
        windSpeedText = (TextView) root.findViewById(R.id.current_windspeed);
        windDirText = (TextView) root.findViewById(R.id.current_winddir);
        humidityText = (TextView) root.findViewById(R.id.current_humidity);
        visibilityText = (TextView) root.findViewById(R.id.current_visibility);
        iconImage = (NetworkImageView) root.findViewById(R.id.current_image);
    }

    public CursorLoader createLoader() {
        String selection = WeatherHistoryContract.WeatherDataColumns.DATE + " = ?";
        String[] selectionArgs = new String[]{"" + Util.getCurrentDateFormatted()};
        CursorLoader cursorLoader = new CursorLoader(getActivity(), WeatherHistoryContract.WeatherData.buildRegistrationUri(),
                WeatherHistoryContract.WeatherDataQuery.PROJECTION, selection, selectionArgs, null);
        return cursorLoader;
    }

    @Subscribe
    public void subscribe(CurrentWeather currentWeather) {
        bindDataToViews(currentWeather);
        hideProgress();
    }

    private void bindDataToViews(CurrentWeather currentWeather) {
        windSpeedText.setText(currentWeather.getWindSpeed());
        windDirText.setText(currentWeather.getWindDirection());
        feelsLikeTemperatureTV.setText(currentWeather.getFeelsLikeTemp());
        visibilityText.setText(currentWeather.getVisibility());
        humidityText.setText(currentWeather.getHumidity());
        temperatureText.setText(currentWeather.getTemperature() + " °C");
        iconImage.setImageUrl(currentWeather.getIconURL(), imageLoader);
    }


}
