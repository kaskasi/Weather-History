package de.fluchtwege.weatherhistory.ui;

import android.os.Bundle;
import android.support.v4.content.CursorLoader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import de.fluchtwege.weatherhistory.R;
import de.fluchtwege.weatherhistory.Util;
import de.fluchtwege.weatherhistory.model.Station;
import de.fluchtwege.weatherhistory.provider.Otto;
import de.fluchtwege.weatherhistory.provider.WeatherHistoryContract;

public class StationFragment extends BaseFragment {

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
        Otto.getBus().register(this);
        Otto.getBus().post(createLoader());

        showProgress();
        return root;
    }

    private void initializeViews(ViewGroup root) {
        countryText = (TextView) root.findViewById(R.id.station_country);
        cityText = (TextView) root.findViewById(R.id.station_city);
        latitudeText = (TextView) root.findViewById(R.id.station_lat);
        longitudeText = (TextView) root.findViewById(R.id.station_lon);
    }

    public CursorLoader createLoader() {
        String selection = WeatherHistoryContract.WeatherDataColumns.DATE + " = ?";
        String[] selectionArgs = new String[]{"" + Util.getCurrentDateFormatted()};
        CursorLoader cursorLoader = new CursorLoader(getActivity(), WeatherHistoryContract.WeatherStation.buildRegistrationUri(),
                WeatherHistoryContract.WeatherDataQuery.PROJECTION_STATION, selection, selectionArgs, null);
        return cursorLoader;
    }

    @Subscribe
    public void subscribe(Station station) {
        bindDataToViews(station);
        hideProgress();
    }

    private void bindDataToViews(Station station) {
        countryText.setText(station.getCountry());
        cityText.setText(station.getCity());
        longitudeText.setText(station.getLongitude());
        latitudeText.setText(station.getLatitude());
    }
}
