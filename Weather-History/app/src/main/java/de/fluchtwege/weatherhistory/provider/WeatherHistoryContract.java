package de.fluchtwege.weatherhistory.provider;

import android.net.Uri;

public class WeatherHistoryContract {

	public static final String PATH_WEATHER_DATA = "weather_data";

	public static final String CONTENT_AUTHORITY = "de.fluchtwege.weatherhistory";

	public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

	public interface WeatherDataColumns {
		String ID = "_id";
		String MAX_CELSIUS = "max_celsius";
		String MIN_CELSIUS = "min_celsius";
		String DATE = "date";
	}

	public interface WeatherDataQuery {

		int _TOKEN_ALL = 0x00;

		String[] PROJECTION = { WeatherDataColumns.ID, WeatherDataColumns.MAX_CELSIUS, WeatherDataColumns.MIN_CELSIUS,
				WeatherDataColumns.DATE };
	}

	public static class WeatherData implements WeatherDataColumns {
		public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_WEATHER_DATA).build();
		public static final String CONTENT_TYPE = "vnd.android.cursor.item/vnd.fluchtwege.weatherdata";

		public static Uri buildRegistrationUri() {
			return CONTENT_URI.buildUpon().build();
		}
	}

}
