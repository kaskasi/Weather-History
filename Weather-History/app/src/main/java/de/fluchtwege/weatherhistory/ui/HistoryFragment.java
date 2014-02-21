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

/**
 * Created by jkettner on 20.02.14.
 */
public class HistoryFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor> {


    private static final String LOG_TAG = "HistoryFragment";
    private Loader<Cursor> mLoader = null;

    private LinearLayout mGraphLL = null;

    private GraphView.GraphViewData[] mHighData = null;
    private GraphView.GraphViewData[] mLowData = null;
    private String mHorizontalLabels[] = null;


    private void showGraph() {
        GraphViewSeries highSeries = new GraphViewSeries(getString(R.string.max_series), new GraphViewSeries.GraphViewSeriesStyle(Color.rgb(250, 0, 0), 3), mHighData);
        GraphViewSeries lowSeries = new GraphViewSeries(getString(R.string.min_series), new GraphViewSeries.GraphViewSeriesStyle(Color.rgb(0, 0, 250), 3), mLowData);
        GraphView graphView = new LineGraphView(getActivity(), "");
        graphView.addSeries(highSeries);
        graphView.addSeries(lowSeries);
        graphView.setHorizontalLabels(mHorizontalLabels);
        graphView.getGraphViewStyle().setTextSize(getResources().getDimension(R.dimen.small_text));
        mGraphLL.removeAllViews();
        mGraphLL.addView(graphView);
    }


    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.history, container, false);
        if (getActivity().isFinishing()) {
            return root;
        }
        mGraphLL = (LinearLayout) root.findViewById(R.id.graph_container);
        mLoader = getActivity().getSupportLoaderManager().restartLoader(WeatherHistoryContract.WeatherDataQuery._TOKEN_HISTORY, null,
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
                mLoader = new CursorLoader(getActivity(), WeatherHistoryContract.WeatherHistory.buildRegistrationUri(),
                        WeatherHistoryContract.WeatherDataQuery.PROJECTION, selection, selectionArgs, sortOrder);
                break;
            }
        }
        return mLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        Log.i(LOG_TAG, "onLoadFinished id=" + loader.getId());
        switch (loader.getId()) {
            case WeatherHistoryContract.WeatherDataQuery._TOKEN_HISTORY: {
                mHighData = new GraphView.GraphViewData[cursor.getCount()];
                if (cursor.getCount() > 1) {
                    mHighData = new GraphView.GraphViewData[cursor.getCount()];
                    mLowData = new GraphView.GraphViewData[cursor.getCount()];
                    mHorizontalLabels = new String[cursor.getCount()];
                    cursor.moveToFirst();
                    for (int i = 0; i < cursor.getCount(); i++) {
                        int high = 0;
                        int low = 0;
                        String date = cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.DATE));
                        try {
                            low = Integer.parseInt(cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.MIN_CELSIUS)));
                            high = Integer.parseInt(cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.MAX_CELSIUS)));
                        } catch (NumberFormatException nfe) {
                        }
                        mHighData[i] = new GraphView.GraphViewData(i, high);
                        mLowData[i] = new GraphView.GraphViewData(i, low);
                        mHorizontalLabels[i] = date.substring(0, 4);
                        cursor.moveToNext();
                    }
                    hideProgress();
                    showGraph();
                }
                break;
            }
        }
    }


    @Override
    public void onLoaderReset(Loader<Cursor> arg0) {
    }

    private class HistoryAdapter extends BaseAdapter {

        String[] content = null;

        HistoryAdapter(String[] contentA) {
            content = contentA;
        }

        @Override
        public int getCount() {
            if (content == null) {
                return 0;
            }
            return content.length;
        }

        @Override
        public Object getItem(int position) {
            return content[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv = new TextView(getActivity());
            tv.setText(content[position]);
            return tv;
        }
    }
}
