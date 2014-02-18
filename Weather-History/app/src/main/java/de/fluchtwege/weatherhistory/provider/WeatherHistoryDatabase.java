package de.fluchtwege.weatherhistory.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import de.fluchtwege.weatherhistory.provider.WeatherHistoryContract.WeatherDataColumns;

public class WeatherHistoryDatabase extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "weatherhistory.db";

	interface Tables {
		String WEATHER_DATA = "weather_data";
	}

	public WeatherHistoryDatabase(Context ctx) {
		super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS " + Tables.WEATHER_DATA + " ( " + WeatherDataColumns.ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + WeatherDataColumns.MAX_CELSIUS + " INTEGER DEFAULT 0, "
				+ WeatherDataColumns.MIN_CELSIUS + " INTEGER DEFAULT 0, " + WeatherDataColumns.DATE + " STRING ) ");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
