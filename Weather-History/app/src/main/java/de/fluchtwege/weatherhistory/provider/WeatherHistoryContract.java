package de.fluchtwege.weatherhistory.provider;

import android.net.Uri;

public class WeatherHistoryContract {

	public static final String PATH_WEATHER_DATA = "weather_data";
    public static final String PATH_WEATHER_HISTORY = "weather_history";
    public static final String PATH_WEATHER_STATION = "weather_station";

	public static final String CONTENT_AUTHORITY = "de.fluchtwege.weatherhistory";

	public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

	public interface WeatherDataColumns {

		String ID = "_id";
		String MAX_CELSIUS = "max_celsius";
		String MIN_CELSIUS = "min_celsius";
		String DATE = "date";
        String LAT = "lat";
        String LON = "lon";
        String CITY = "city";
        String COUNTRY = "country";
        String TEMP_C = "temp_c";
        String WIND_KPH ="wind_kph";
        String WIND_DIR ="wind_dir";
        String FEELSLIKE_C = "feelslike_c";
        String ICON_URL = "icon_url";
        String VISIBILITY_KM = "visibility_km";
        String RELATIVE_HUMIDITY = "relative_humidity";


	}

	public interface WeatherDataQuery {

		int _TOKEN_ALL = 0x00;
        int _TOKEN_HISTORY =0x01;
        int _TOKEN_STATION = 0x02;

		String[] PROJECTION = { WeatherDataColumns.ID, WeatherDataColumns.MAX_CELSIUS, WeatherDataColumns.MIN_CELSIUS,
				WeatherDataColumns.DATE, WeatherDataColumns.LAT, WeatherDataColumns.LON, WeatherDataColumns.CITY, WeatherDataColumns.COUNTRY
                , WeatherDataColumns.TEMP_C, WeatherDataColumns.WIND_KPH, WeatherDataColumns.WIND_DIR, WeatherDataColumns.FEELSLIKE_C
                , WeatherDataColumns.ICON_URL, WeatherDataColumns.VISIBILITY_KM, WeatherDataColumns.RELATIVE_HUMIDITY};

        String[] PROJECTION_STATION = { WeatherDataColumns.ID, WeatherDataColumns.DATE, WeatherDataColumns.LAT, WeatherDataColumns.LON,
                WeatherDataColumns.CITY, WeatherDataColumns.COUNTRY};

        String[] PROJECTION_HISTORY = { WeatherDataColumns.ID, WeatherDataColumns.MAX_CELSIUS, WeatherDataColumns.MIN_CELSIUS,
                WeatherDataColumns.DATE};

    }

    public static class WeatherHistory implements WeatherDataColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_WEATHER_HISTORY).build();
        public static final String CONTENT_TYPE = "vnd.android.cursor.item/vnd.fluchtwege.weatherhistory";

        public static Uri buildRegistrationUri() {
            return CONTENT_URI.buildUpon().build();
        }
    }

    public static class WeatherStation implements WeatherDataColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_WEATHER_STATION).build();
        public static final String CONTENT_TYPE = "vnd.android.cursor.item/vnd.fluchtwege.weatherstation";

        public static Uri buildRegistrationUri() {
            return CONTENT_URI.buildUpon().build();
        }
    }

	public static class WeatherData implements WeatherDataColumns {
		public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_WEATHER_DATA).build();
		public static final String CONTENT_TYPE = "vnd.android.cursor.item/vnd.fluchtwege.weatherdata";

		public static Uri buildRegistrationUri() {
			return CONTENT_URI.buildUpon().build();
		}
	}

}
