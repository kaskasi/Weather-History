package de.fluchtwege.weatherhistory.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.CursorLoader;
import android.util.Log;

import com.squareup.otto.Subscribe;

import java.util.Arrays;

import de.fluchtwege.weatherhistory.io.IOController;
import de.fluchtwege.weatherhistory.provider.WeatherHistoryDatabase.Tables;

public class WeatherHistoryContentProvider extends ContentProvider {

    private static final int WEATHER_DATA = 100;
    private static final int WEATHER_HISTORY = 101;
    private static final int WEATHER_STATION = 102;

    private static final String LOG_TAG = "WHContentProvider";

    private static WeatherHistoryDatabase database = null;
    protected final UriMatcher uriMatcher = buildUriMatcher();

    public UriMatcher buildUriMatcher() {
        final String authority = WeatherHistoryContract.CONTENT_AUTHORITY;
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(authority, WeatherHistoryContract.PATH_WEATHER_DATA, WEATHER_DATA);
        matcher.addURI(authority, WeatherHistoryContract.PATH_WEATHER_HISTORY, WEATHER_HISTORY);
        matcher.addURI(authority, WeatherHistoryContract.PATH_WEATHER_STATION, WEATHER_STATION);
        return matcher;
    }

    @Override
    public boolean onCreate() {
        database = new WeatherHistoryDatabase(getContext());
        Otto.getBus().register(this);
        return true;
    }

    @Override
    public String getType(Uri uri) {
        Log.d(LOG_TAG, "uri: " + uri);
        final int match = uriMatcher.match(uri);
        switch (match) {
            case WEATHER_DATA: {
                return WeatherHistoryContract.WeatherData.CONTENT_TYPE;
            }
            default: {
                throw new UnsupportedOperationException("Unknown uri " + uri);
            }
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final int match = uriMatcher.match(uri);
        Log.d(LOG_TAG, "insert( match:" + match + " uri: " + uri + " value: " + values + ")");
        final SQLiteDatabase db = database.getWritableDatabase();
        insertWeatherData(uri, values, db);

        switch (match) {
            case WEATHER_DATA: {
                return WeatherHistoryContract.WeatherData.buildRegistrationUri();
            }
            case WEATHER_STATION: {
                return WeatherHistoryContract.WeatherStation.buildRegistrationUri();
            }
            case WEATHER_HISTORY: {
                return WeatherHistoryContract.WeatherHistory.buildRegistrationUri();
            }
            default: {
                throw new UnsupportedOperationException("Unknown uri " + uri);
            }
        }
    }

    private void insertWeatherData(Uri uri, ContentValues values, SQLiteDatabase db) {
        String[] selectionArgs = new String[]{((String) values
                .get(WeatherHistoryContract.WeatherDataColumns.DATE))};
        String selection = WeatherHistoryContract.WeatherDataColumns.DATE + " =? ";
        Cursor cursor = db.query(Tables.WEATHER_DATA, null, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            db.update(Tables.WEATHER_DATA, values, selection, selectionArgs);
        } else {
            db.insert(Tables.WEATHER_DATA, null, values);
        }
        ContentProviderHelper.postCurrentWeather(values);

        cursor.close();
        getContext().getContentResolver().notifyChange(uri, null);
    }

    @Subscribe
    public void subscribe(CursorLoader loader) {
        query(loader.getUri(), loader.getProjection(), loader.getSelection(), loader.getSelectionArgs(), loader.getSortOrder());
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final int match = uriMatcher.match(uri);
        Log.d(LOG_TAG, "query( match= " + match + " uri= " + uri + " proj= " + Arrays.toString(projection) + " selection= " + selection
                + " selectionArgs= " + Arrays.toString(selectionArgs) + ")");
        final SQLiteDatabase db = database.getReadableDatabase();

        switch (match) {
            case WEATHER_DATA: {
                return createWeatherDataQuery(uri, selection, selectionArgs, db);
            }
            case WEATHER_STATION: {
                return createWeatherStationQuery(uri, selection, selectionArgs, db);
            }
            case WEATHER_HISTORY: {
                return createWeatherHistoryQuery(uri, selection, selectionArgs, db, sortOrder);
            }
            default: {
                throw new UnsupportedOperationException("Unknown uri " + uri);
            }
        }
    }

    private Cursor createWeatherHistoryQuery(Uri uri, String selection, String[] selectionArgs, SQLiteDatabase db, String sortOrder) {
        Cursor cursor = db.query(Tables.WEATHER_DATA, null, selection, selectionArgs, null, null, sortOrder);
        Log.i(LOG_TAG, "cursor.getCount:" + cursor.getCount());
        if (cursor.getCount() <= 1) {
            IOController.loadHistoricalData(getContext());
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    private Cursor createWeatherStationQuery(Uri uri, String selection, String[] selectionArgs, SQLiteDatabase db) {
        Cursor cursor = db.query(Tables.WEATHER_DATA, null, selection, selectionArgs, null, null, null);
        if (!cursor.moveToFirst()) {
            IOController.loadCurrentForecast(getContext());
        } else {
            ContentProviderHelper.parseAndPostStation(cursor);
        }
        return cursor;
    }

    @NonNull
    private Cursor createWeatherDataQuery(Uri uri, String selection, String[] selectionArgs, SQLiteDatabase db) {
        Cursor cursor = db.query(Tables.WEATHER_DATA, null, selection, selectionArgs, null, null, null);
        if (!cursor.moveToFirst()) {
            IOController.loadCurrentForecast(getContext());
        } else {
            ContentProviderHelper.parseAndPostWeatherData(cursor);
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

}
