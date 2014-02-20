package de.fluchtwege.weatherhistory.ui;

import android.os.Bundle;

import de.fluchtwege.weatherhistory.R;


public class MainActivity extends BaseActivity  {

    private static final String LOG_TAG = "MainActivity";

//    TextView highTV = null;
//    TextView lowTV = null;
//    ListView historyLV = null;
//    private Loader<Cursor> mLoader = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        highTV = (TextView) findViewById(R.id.high);
//        lowTV = (TextView) findViewById(R.id.low);
//        historyLV = (ListView) findViewById(R.id.list);
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        mLoader = getSupportLoaderManager().restartLoader(WeatherHistoryContract.WeatherDataQuery._TOKEN_ALL, null,
//                this);
//
//    }



//    @Override
//    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
//        switch (id) {
//            case WeatherHistoryContract.WeatherDataQuery._TOKEN_ALL: {
//
//                String selection = WeatherHistoryContract.WeatherDataColumns.DATE + " = ?";
//                String[] selectionArgs = new String[]{"" + Util.getCurrentDateFormatted()};
//                mLoader = new CursorLoader(this, WeatherHistoryContract.WeatherData.buildRegistrationUri(),
//                        WeatherHistoryContract.WeatherDataQuery.PROJECTION, selection, selectionArgs, null);
//                break;
//            }
//            case WeatherHistoryContract.WeatherDataQuery._TOKEN_HISTORY: {
//                String selection = WeatherHistoryContract.WeatherDataColumns.DATE + " LIKE ?";
//                String[] selectionArgs = new String[]{"%" + Util.getDateForHistory()};
//                String sortOrder = WeatherHistoryContract.WeatherDataColumns.DATE;
//                mLoader = new CursorLoader(this, WeatherHistoryContract.WeatherHistory.buildRegistrationUri(),
//                        WeatherHistoryContract.WeatherDataQuery.PROJECTION, selection, selectionArgs, sortOrder);
//                break;
//            }
//        }
//        return mLoader;
//    }
//
//    @Override
//    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
//        Log.i(LOG_TAG, "onLoadFinished id="+loader.getId());
//        switch (loader.getId()) {
//            case WeatherHistoryContract.WeatherDataQuery._TOKEN_ALL:  {
//                mLoader = getSupportLoaderManager().restartLoader(WeatherHistoryContract.WeatherDataQuery._TOKEN_HISTORY, null,
//                        this);
//
//                if (cursor.moveToFirst()) {
//                    int high = 0;
//                    int low = 0;
//                    high = cursor.getInt(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.MAX_CELSIUS));
//                    low = cursor.getInt(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.MIN_CELSIUS));
//                    highTV.setText("" + high);
//                    lowTV.setText("" + low);
//                }
//                break;
//            }
//            case WeatherHistoryContract.WeatherDataQuery._TOKEN_HISTORY: {
//                if (cursor.getCount() > 1){
//                    cursor.moveToFirst();
//                    String[] content = new String[cursor.getCount()];
//
//                    for (int i = 0; i < content.length; i++){
//                        String helper = cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.DATE));
//                                helper = helper + " min: "+cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.MIN_CELSIUS));
//                        helper = helper + " max: "+cursor.getString(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.MAX_CELSIUS));
//                        content[i] = helper ;
//                        cursor.moveToNext();
//                    }
//                    historyLV.setAdapter(new HistoryAdapter(content));
//                }
//                break;
//            }
//        }
//
//    }
//
//    @Override
//    public void onLoaderReset(Loader<Cursor> arg0) {
//    }

//    private class HistoryAdapter extends BaseAdapter {
//
//        String[] content = null;
//
//        HistoryAdapter(String[] contentA) {
//            content = contentA;
//        }
//
//        @Override
//        public int getCount() {
//            if (content == null) {
//                return 0;
//            }
//            return content.length;
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return content[position];
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            TextView tv = new TextView(MainActivity.this);
//            tv.setText(content[position]);
//            return tv;
//        }
//    }

}
