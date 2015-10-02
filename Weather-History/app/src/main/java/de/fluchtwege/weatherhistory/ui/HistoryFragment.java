package de.fluchtwege.weatherhistory.ui;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

import de.fluchtwege.weatherhistory.R;
import de.fluchtwege.weatherhistory.Util;
import de.fluchtwege.weatherhistory.provider.WeatherHistoryContract;

public class HistoryFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String LOG_TAG = "HistoryFragment";

    private Loader<Cursor> loader = null;

    private LinearLayout graphContainer = null;
    private GraphView.GraphViewData[] temperatureMaximums = null;
    private GraphView.GraphViewData[] temperatureMinimums = null;
    private String horizontalLabels[] = null;


    private void showGraph() {
        GraphViewSeries highSeries = new GraphViewSeries(getString(R.string.max_series), new GraphViewSeries.GraphViewSeriesStyle(Color.rgb(250, 0, 0), 3), temperatureMaximums);
        GraphViewSeries lowSeries = new GraphViewSeries(getString(R.string.min_series), new GraphViewSeries.GraphViewSeriesStyle(Color.rgb(0, 0, 250), 3), temperatureMinimums);
        GraphView graphView = new LineGraphView(getActivity(), "");
        graphView.addSeries(highSeries);
        graphView.addSeries(lowSeries);
        graphView.setHorizontalLabels(horizontalLabels);
        graphView.getGraphViewStyle().setTextSize(getResources().getDimension(R.dimen.small_text));
        graphContainer.removeAllViews();
        graphContainer.addView(graphView);
    }


    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.history, container, false);
        if (getActivity().isFinishing()) {
            return root;
        }
        graphContainer = (LinearLayout) root.findViewById(R.id.graph_container);
        loader = getActivity().getSupportLoaderManager().restartLoader(WeatherHistoryContract.WeatherDataQuery._TOKEN_HISTORY, null,
                this);
        showProgress();
        return root;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
        switch (id) {

            case WeatherHistoryContract.WeatherDataQuery._TOKEN_HISTORY: {
                String selection = WeatherHistoryContract.WeatherDataColumns.DATE + " LIKE ?";
                String[] selectionArgs = new String[]{"%" + Util.getDateForHistory()};
                String sortOrder = WeatherHistoryContract.WeatherDataColumns.DATE;
                loader = new CursorLoader(getActivity(), WeatherHistoryContract.WeatherHistory.buildRegistrationUri(),
                        WeatherHistoryContract.WeatherDataQuery.PROJECTION, selection, selectionArgs, sortOrder);
                break;
            }
        }
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        Log.i(LOG_TAG, "onLoadFinished id=" + loader.getId());
        switch (loader.getId()) {
            case WeatherHistoryContract.WeatherDataQuery._TOKEN_HISTORY: {
                temperatureMaximums = new GraphView.GraphViewData[cursor.getCount()];
                if (cursor.getCount() > 1) {
                    bindDataToViews(cursor);
                    hideProgress();
                    showGraph();
                }
                break;
            }
        }
    }

    private void bindDataToViews(Cursor cursor) {
        temperatureMaximums = new GraphView.GraphViewData[cursor.getCount()];
        temperatureMinimums = new GraphView.GraphViewData[cursor.getCount()];
        horizontalLabels = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            addHistoryEntry(cursor, i);
            cursor.moveToNext();
        }
    }

    private void addHistoryEntry(Cursor cursor, int index) {
        int high = 0;
        int low = 0;
        String date = cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.DATE));
        try {
            low = parseMinTemperature(cursor);
            high = parseMaxTemperature(cursor);
        } catch (NumberFormatException nfe) {
        }
        temperatureMaximums[index] = new GraphView.GraphViewData(index, high);
        temperatureMinimums[index] = new GraphView.GraphViewData(index, low);
        horizontalLabels[index] = date.substring(0, 4);
    }

    private int parseMaxTemperature(Cursor cursor) {
        return Integer.parseInt(cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.MAX_CELSIUS)));
    }

    private int parseMinTemperature(Cursor cursor) {
        return Integer.parseInt(cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.MIN_CELSIUS)));
    }

    @Override
    public void onLoaderReset(Loader<Cursor> arg0) {
    }

}
