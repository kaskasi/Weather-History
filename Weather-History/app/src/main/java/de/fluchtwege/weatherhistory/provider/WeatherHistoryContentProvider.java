package de.fluchtwege.weatherhistory.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import java.util.Arrays;

import de.fluchtwege.weatherhistory.provider.WeatherHistoryDatabase.Tables;

public class WeatherHistoryContentProvider extends ContentProvider {

	protected final UriMatcher sUriMatcher = buildUriMatcher();

	private static final int WEATHER_DATA = 100;

	private static final String LOG_TAG = "WeatherHistoryContentProvider";

	private static WeatherHistoryDatabase mOpenHelper = null;

	public UriMatcher buildUriMatcher() {
		final String authority = WeatherHistoryContract.CONTENT_AUTHORITY;
		final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
		matcher.addURI(authority, WeatherHistoryContract.PATH_WEATHER_DATA, WEATHER_DATA);
		return matcher;
	}

	@Override
	public boolean onCreate() {
		mOpenHelper = new WeatherHistoryDatabase(getContext());
		return true;
	}

	@Override
	public String getType(Uri uri) {
		Log.i(LOG_TAG, "uri: " + uri);
		final int match = sUriMatcher.match(uri);
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
		Log.i(LOG_TAG, "insert( uri: " + uri + " value: " + values + ")");
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		final int match = sUriMatcher.match(uri);
		switch (match) {
		case WEATHER_DATA: {
			String[] selectionArgs = new String[] { ((String) values
					.get(WeatherHistoryContract.WeatherDataColumns.DATE)) };
			String selection = WeatherHistoryContract.WeatherDataColumns.DATE + " =? ";
			Cursor cursor = db.query(Tables.WEATHER_DATA, null, selection, selectionArgs, null, null, null);
			if (cursor.moveToFirst()) {

				db.update(Tables.WEATHER_DATA, values, selection, selectionArgs);
			} else {
				db.insert(Tables.WEATHER_DATA, null, values);
			}
			cursor.close();
			getContext().getContentResolver().notifyChange(uri, null);
			return WeatherHistoryContract.WeatherData.buildRegistrationUri();
		}
		default: {
			throw new UnsupportedOperationException("Unknown uri " + uri);
		}
		}
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		Log.i(LOG_TAG, "query( uri= " + uri + " proj= " + Arrays.toString(projection) + " selection= " + selection
				+ " selectionArgs= " + Arrays.toString(selectionArgs) + ")");
		final SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		final int match = sUriMatcher.match(uri);
		switch (match) {
		case WEATHER_DATA: {

			Cursor cursor = db.query(Tables.WEATHER_DATA, null, selection, selectionArgs, null, null, null);
			if (cursor.moveToFirst()) {

			} else {
				WeatherHistoryServiceHelper.loadCurrentForecast(getContext());
			}
			return cursor;
		}
		default: {
			throw new UnsupportedOperationException("Unknown uri " + uri);
		}
		}
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		return 0;
	}

}
