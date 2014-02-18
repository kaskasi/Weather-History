package de.fluchtwege.weatherhistory.ui;

import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.Menu;
import android.widget.TextView;

import de.fluchtwege.weatherhistory.R;
import de.fluchtwege.weatherhistory.Util;
import de.fluchtwege.weatherhistory.provider.WeatherHistoryContract;


public class MainActivity extends FragmentActivity implements LoaderCallbacks<Cursor> {

	TextView highTV = null;
	TextView lowTV = null;

	private Loader<Cursor> mLoader = null;

	private final ContentObserver mWeatherDataObserver = new ContentObserver(new Handler()) {
		public void onChange(boolean selfChange) {
			mLoader.forceLoad();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		highTV = (TextView) findViewById(R.id.high);
		lowTV = (TextView) findViewById(R.id.low);
	}

	@Override
	protected void onResume() {
		super.onResume();
		getContentResolver().registerContentObserver(WeatherHistoryContract.WeatherData.buildRegistrationUri(), true,
				mWeatherDataObserver);
		mLoader = getSupportLoaderManager().restartLoader(WeatherHistoryContract.WeatherDataQuery._TOKEN_ALL, null,
				this);
	}

	@Override
	protected void onPause() {
		getContentResolver().unregisterContentObserver(mWeatherDataObserver);
		super.onPause();

	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
		String selection = WeatherHistoryContract.WeatherDataColumns.DATE + " = ?";
		String[] selectionArgs = new String[] { "" + Util.getCurrentDateFormatted() };
		mLoader = new CursorLoader(this, WeatherHistoryContract.WeatherData.buildRegistrationUri(),
				WeatherHistoryContract.WeatherDataQuery.PROJECTION, selection, selectionArgs, null);
		return mLoader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		if (cursor.moveToFirst()) {
			int high = 0;
			int low = 0;
			high = cursor.getInt(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.MAX_CELSIUS));
			low = cursor.getInt(cursor.getColumnIndex(WeatherHistoryContract.WeatherDataColumns.MIN_CELSIUS));
			highTV.setText("" + high);
			lowTV.setText("" + low);
		}
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
	}

}
