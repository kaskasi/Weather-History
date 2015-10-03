package de.fluchtwege.whtest.mock;

/**
 * Created by jankettner on 03/10/15.
 */
public class MockMessages {

    public static String FORECAST_RESULT = "\n" +
            "{\n" +
            "  \"response\": {\n" +
            "  \"version\":\"0.1\",\n" +
            "  \"termsofService\":\"http://www.wunderground.com/weather/api/d/terms.html\",\n" +
            "  \"features\": {\n" +
            "  \"geolookup\": 1\n" +
            "  ,\n" +
            "  \"conditions\": 1\n" +
            "  ,\n" +
            "  \"forecast\": 1\n" +
            "  }\n" +
            "\t}\n" +
            "\t\t,\t\"location\": {\n" +
            "\t\t\"type\":\"INTLCITY\",\n" +
            "\t\t\"country\":\"DL\",\n" +
            "\t\t\"country_iso3166\":\"DE\",\n" +
            "\t\t\"country_name\":\"Germany\",\n" +
            "\t\t\"state\":\"\",\n" +
            "\t\t\"city\":\"Helgoland\",\n" +
            "\t\t\"tz_short\":\"CEST\",\n" +
            "\t\t\"tz_long\":\"Europe/Berlin\",\n" +
            "\t\t\"lat\":\"54.18000031\",\n" +
            "\t\t\"lon\":\"7.90000010\",\n" +
            "\t\t\"zip\":\"00000\",\n" +
            "\t\t\"magic\":\"1\",\n" +
            "\t\t\"wmo\":\"10015\",\n" +
            "\t\t\"l\":\"/q/zmw:00000.1.10015\",\n" +
            "\t\t\"requesturl\":\"global/stations/10015.html\",\n" +
            "\t\t\"wuiurl\":\"http://www.wunderground.com/global/stations/10015.html\",\n" +
            "\t\t\"nearby_weather_stations\": {\n" +
            "\t\t\"airport\": {\n" +
            "\t\t\"station\": [\n" +
            "\t\t{ \"city\":\"Helgoland\", \"state\":\"\", \"country\":\"Germany\", \"icao\":\"EDXH\", \"lat\":\"54.17501831\", \"lon\":\"7.89190006\" }\n" +
            "\t\t,{ \"city\":\"Nordholz\", \"state\":\"\", \"country\":\"DL\", \"icao\":\"ETMN\", \"lat\":\"53.76472092\", \"lon\":\"8.65750027\" }\n" +
            "\t\t,{ \"city\":\"Wittmundhaven\", \"state\":\"\", \"country\":\"DL\", \"icao\":\"ETNT\", \"lat\":\"53.54783249\", \"lon\":\"7.66733313\" }\n" +
            "\t\t,{ \"city\":\"Westerland\", \"state\":\"\", \"country\":\"DL\", \"icao\":\"EDXW\", \"lat\":\"54.91324997\", \"lon\":\"8.34047222\" }\n" +
            "\t\t]\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t\"pws\": {\n" +
            "\t\t\"station\": [\n" +
            "\t\t{\n" +
            "\t\t\"neighborhood\":\"Wedeler Straße\",\n" +
            "\t\t\"city\":\"Helgoland\",\n" +
            "\t\t\"state\":\"\",\n" +
            "\t\t\"country\":\"DL\",\n" +
            "\t\t\"id\":\"IHELGOLA2\",\n" +
            "\t\t\"lat\":54.181438,\n" +
            "\t\t\"lon\":7.884975,\n" +
            "\t\t\"distance_km\":0,\n" +
            "\t\t\"distance_mi\":0\n" +
            "\t\t}\n" +
            "\t\t]\n" +
            "\t\t}\n" +
            "\t\t}\n" +
            "\t}\n" +
            "  ,\t\"current_observation\": {\n" +
            "\t\t\"image\": {\n" +
            "\t\t\"url\":\"http://icons.wxug.com/graphics/wu2/logo_130x80.png\",\n" +
            "\t\t\"title\":\"Weather Underground\",\n" +
            "\t\t\"link\":\"http://www.wunderground.com\"\n" +
            "\t\t},\n" +
            "\t\t\"display_location\": {\n" +
            "\t\t\"full\":\"Helgoland, Germany\",\n" +
            "\t\t\"city\":\"Helgoland\",\n" +
            "\t\t\"state\":\"\",\n" +
            "\t\t\"state_name\":\"Germany\",\n" +
            "\t\t\"country\":\"DL\",\n" +
            "\t\t\"country_iso3166\":\"DE\",\n" +
            "\t\t\"zip\":\"00000\",\n" +
            "\t\t\"magic\":\"1\",\n" +
            "\t\t\"wmo\":\"10015\",\n" +
            "\t\t\"latitude\":\"54.18000031\",\n" +
            "\t\t\"longitude\":\"7.90000010\",\n" +
            "\t\t\"elevation\":\"8.00000000\"\n" +
            "\t\t},\n" +
            "\t\t\"observation_location\": {\n" +
            "\t\t\"full\":\"Helgoland, \",\n" +
            "\t\t\"city\":\"Helgoland\",\n" +
            "\t\t\"state\":\"\",\n" +
            "\t\t\"country\":\"Germany\",\n" +
            "\t\t\"country_iso3166\":\"DE\",\n" +
            "\t\t\"latitude\":\"54.17501831\",\n" +
            "\t\t\"longitude\":\"7.89190006\",\n" +
            "\t\t\"elevation\":\"13 ft\"\n" +
            "\t\t},\n" +
            "\t\t\"estimated\": {\n" +
            "\t\t},\n" +
            "\t\t\"station_id\":\"ETMN\",\n" +
            "\t\t\"observation_time\":\"Last Updated on October 3, 9:00 PM CEST\",\n" +
            "\t\t\"observation_time_rfc822\":\"Sat, 03 Oct 2015 21:00:00 +0200\",\n" +
            "\t\t\"observation_epoch\":\"1443898800\",\n" +
            "\t\t\"local_time_rfc822\":\"Sat, 03 Oct 2015 21:05:32 +0200\",\n" +
            "\t\t\"local_epoch\":\"1443899132\",\n" +
            "\t\t\"local_tz_short\":\"CEST\",\n" +
            "\t\t\"local_tz_long\":\"Europe/Berlin\",\n" +
            "\t\t\"local_tz_offset\":\"+0200\",\n" +
            "\t\t\"weather\":\"Overcast\",\n" +
            "\t\t\"temperature_string\":\"60 F (16 C)\",\n" +
            "\t\t\"temp_f\":60,\n" +
            "\t\t\"temp_c\":16,\n" +
            "\t\t\"relative_humidity\":\"74%\",\n" +
            "\t\t\"wind_string\":\"From the ESE at 18 MPH\",\n" +
            "\t\t\"wind_dir\":\"ESE\",\n" +
            "\t\t\"wind_degrees\":110,\n" +
            "\t\t\"wind_mph\":18,\n" +
            "\t\t\"wind_gust_mph\":0,\n" +
            "\t\t\"wind_kph\":29,\n" +
            "\t\t\"wind_gust_kph\":0,\n" +
            "\t\t\"pressure_mb\":\"1013\",\n" +
            "\t\t\"pressure_in\":\"29.93\",\n" +
            "\t\t\"pressure_trend\":\"-\",\n" +
            "\t\t\"dewpoint_string\":\"54 F (12 C)\",\n" +
            "\t\t\"dewpoint_f\":54,\n" +
            "\t\t\"dewpoint_c\":12,\n" +
            "\t\t\"heat_index_string\":\"NA\",\n" +
            "\t\t\"heat_index_f\":\"NA\",\n" +
            "\t\t\"heat_index_c\":\"NA\",\n" +
            "\t\t\"windchill_string\":\"NA\",\n" +
            "\t\t\"windchill_f\":\"NA\",\n" +
            "\t\t\"windchill_c\":\"NA\",\n" +
            "\t\t\"feelslike_string\":\"60 F (16 C)\",\n" +
            "\t\t\"feelslike_f\":\"60\",\n" +
            "\t\t\"feelslike_c\":\"16\",\n" +
            "\t\t\"visibility_mi\":\"6.0\",\n" +
            "\t\t\"visibility_km\":\"9.0\",\n" +
            "\t\t\"solarradiation\":\"--\",\n" +
            "\t\t\"UV\":\"0\",\"precip_1hr_string\":\" in ( mm)\",\n" +
            "\t\t\"precip_1hr_in\":\"\",\n" +
            "\t\t\"precip_1hr_metric\":\"--\",\n" +
            "\t\t\"precip_today_string\":\" in ( mm)\",\n" +
            "\t\t\"precip_today_in\":\"\",\n" +
            "\t\t\"precip_today_metric\":\"--\",\n" +
            "\t\t\"icon\":\"cloudy\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/nt_cloudy.gif\",\n" +
            "\t\t\"forecast_url\":\"http://www.wunderground.com/global/stations/10015.html\",\n" +
            "\t\t\"history_url\":\"http://www.wunderground.com/history/airport/ETMN/2015/10/3/DailyHistory.html\",\n" +
            "\t\t\"ob_url\":\"http://www.wunderground.com/cgi-bin/findweather/getForecast?query=54.17501831,7.89190006\",\n" +
            "\t\t\"nowcast\":\"\"\n" +
            "\t}\n" +
            "\t\t,\n" +
            "\t\"forecast\":{\n" +
            "\t\t\"txt_forecast\": {\n" +
            "\t\t\"date\":\"7:57 PM CEST\",\n" +
            "\t\t\"forecastday\": [\n" +
            "\t\t{\n" +
            "\t\t\"period\":0,\n" +
            "\t\t\"icon\":\"partlycloudy\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/partlycloudy.gif\",\n" +
            "\t\t\"title\":\"Saturday\",\n" +
            "\t\t\"fcttext\":\"Partly cloudy. Lows overnight in the upper 50s.\",\n" +
            "\t\t\"fcttext_metric\":\"Partly cloudy. Low 14C.\",\n" +
            "\t\t\"pop\":\"0\"\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"period\":1,\n" +
            "\t\t\"icon\":\"nt_partlycloudy\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/nt_partlycloudy.gif\",\n" +
            "\t\t\"title\":\"Saturday Night\",\n" +
            "\t\t\"fcttext\":\"Some clouds. Low 57F. SSE winds shifting to WSW at 10 to 20 mph.\",\n" +
            "\t\t\"fcttext_metric\":\"Some clouds. Low 14C. SSE winds shifting to WSW at 15 to 30 km/h.\",\n" +
            "\t\t\"pop\":\"0\"\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"period\":2,\n" +
            "\t\t\"icon\":\"clear\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/clear.gif\",\n" +
            "\t\t\"title\":\"Sunday\",\n" +
            "\t\t\"fcttext\":\"A few clouds early, otherwise mostly sunny. High around 60F. Winds WSW at 10 to 20 mph.\",\n" +
            "\t\t\"fcttext_metric\":\"A few clouds early, otherwise mostly sunny. High 16C. Winds WSW at 15 to 30 km/h.\",\n" +
            "\t\t\"pop\":\"10\"\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"period\":3,\n" +
            "\t\t\"icon\":\"nt_clear\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/nt_clear.gif\",\n" +
            "\t\t\"title\":\"Sunday Night\",\n" +
            "\t\t\"fcttext\":\"Clear skies with a few passing clouds. Low 56F. Winds S at 10 to 15 mph.\",\n" +
            "\t\t\"fcttext_metric\":\"Clear skies with a few passing clouds. Low 13C. Winds S at 15 to 25 km/h.\",\n" +
            "\t\t\"pop\":\"0\"\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"period\":4,\n" +
            "\t\t\"icon\":\"mostlycloudy\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/mostlycloudy.gif\",\n" +
            "\t\t\"title\":\"Monday\",\n" +
            "\t\t\"fcttext\":\"A mix of clouds and sun in the morning followed by cloudy skies during the afternoon. High 62F. Winds SE at 10 to 20 mph.\",\n" +
            "\t\t\"fcttext_metric\":\"Partly cloudy skies in the morning will give way to cloudy skies during the afternoon. High 17C. Winds SE at 15 to 30 km/h.\",\n" +
            "\t\t\"pop\":\"10\"\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"period\":5,\n" +
            "\t\t\"icon\":\"nt_partlycloudy\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/nt_partlycloudy.gif\",\n" +
            "\t\t\"title\":\"Monday Night\",\n" +
            "\t\t\"fcttext\":\"Cloudy early then partly cloudy and windy overnight. Low 57F. Winds ESE at 20 to 30 mph. Winds could occasionally gust over 40 mph.\",\n" +
            "\t\t\"fcttext_metric\":\"Cloudy during the evening followed by partly cloudy skies and gusty winds overnight. Low 14C. Winds ESE at 30 to 50 km/h. Winds could occasionally gust over 65 km/h.\",\n" +
            "\t\t\"pop\":\"20\"\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"period\":6,\n" +
            "\t\t\"icon\":\"rain\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/rain.gif\",\n" +
            "\t\t\"title\":\"Tuesday\",\n" +
            "\t\t\"fcttext\":\"Cloudy and windy with periods of rain in the afternoon. High around 60F. Winds ESE at 25 to 35 mph. Chance of rain 90%. Rainfall near a quarter of an inch. Winds could occasionally gust over 40 mph.\",\n" +
            "\t\t\"fcttext_metric\":\"Cloudy and windy with periods of rain in the afternoon. High 16C. Winds ESE at 40 to 55 km/h. Chance of rain 90%. Rainfall near 6mm. Winds could occasionally gust over 65 km/h.\",\n" +
            "\t\t\"pop\":\"90\"\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"period\":7,\n" +
            "\t\t\"icon\":\"nt_rain\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/nt_rain.gif\",\n" +
            "\t\t\"title\":\"Tuesday Night\",\n" +
            "\t\t\"fcttext\":\"Windy at times with rain likely. Low 56F. Winds ESE at 20 to 30 mph. Chance of rain 80%. Rainfall near a quarter of an inch. Winds could occasionally gust over 40 mph.\",\n" +
            "\t\t\"fcttext_metric\":\"Windy at times with rain likely. Low 14C. Winds ESE at 30 to 50 km/h. Chance of rain 80%. Rainfall around 6mm. Winds could occasionally gust over 65 km/h.\",\n" +
            "\t\t\"pop\":\"80\"\n" +
            "\t\t}\n" +
            "\t\t]\n" +
            "\t\t},\n" +
            "\t\t\"simpleforecast\": {\n" +
            "\t\t\"forecastday\": [\n" +
            "\t\t{\"date\":{\n" +
            "\t\"epoch\":\"1443891600\",\n" +
            "\t\"pretty\":\"7:00 PM CEST on October 03, 2015\",\n" +
            "\t\"day\":3,\n" +
            "\t\"month\":10,\n" +
            "\t\"year\":2015,\n" +
            "\t\"yday\":275,\n" +
            "\t\"hour\":19,\n" +
            "\t\"min\":\"00\",\n" +
            "\t\"sec\":0,\n" +
            "\t\"isdst\":\"1\",\n" +
            "\t\"monthname\":\"October\",\n" +
            "\t\"monthname_short\":\"Oct\",\n" +
            "\t\"weekday_short\":\"Sat\",\n" +
            "\t\"weekday\":\"Saturday\",\n" +
            "\t\"ampm\":\"PM\",\n" +
            "\t\"tz_short\":\"CEST\",\n" +
            "\t\"tz_long\":\"Europe/Berlin\"\n" +
            "},\n" +
            "\t\t\"period\":1,\n" +
            "\t\t\"high\": {\n" +
            "\t\t\"fahrenheit\":\"64\",\n" +
            "\t\t\"celsius\":\"17\"\n" +
            "\t\t},\n" +
            "\t\t\"low\": {\n" +
            "\t\t\"fahrenheit\":\"57\",\n" +
            "\t\t\"celsius\":\"14\"\n" +
            "\t\t},\n" +
            "\t\t\"conditions\":\"Partly Cloudy\",\n" +
            "\t\t\"icon\":\"partlycloudy\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/partlycloudy.gif\",\n" +
            "\t\t\"skyicon\":\"\",\n" +
            "\t\t\"pop\":0,\n" +
            "\t\t\"qpf_allday\": {\n" +
            "\t\t\"in\": 0.00,\n" +
            "\t\t\"mm\": 0\n" +
            "\t\t},\n" +
            "\t\t\"qpf_day\": {\n" +
            "\t\t\"in\": null,\n" +
            "\t\t\"mm\": null\n" +
            "\t\t},\n" +
            "\t\t\"qpf_night\": {\n" +
            "\t\t\"in\": 0.00,\n" +
            "\t\t\"mm\": 0\n" +
            "\t\t},\n" +
            "\t\t\"snow_allday\": {\n" +
            "\t\t\"in\": 0.0,\n" +
            "\t\t\"cm\": 0.0\n" +
            "\t\t},\n" +
            "\t\t\"snow_day\": {\n" +
            "\t\t\"in\": null,\n" +
            "\t\t\"cm\": null\n" +
            "\t\t},\n" +
            "\t\t\"snow_night\": {\n" +
            "\t\t\"in\": 0.0,\n" +
            "\t\t\"cm\": 0.0\n" +
            "\t\t},\n" +
            "\t\t\"maxwind\": {\n" +
            "\t\t\"mph\": 12,\n" +
            "\t\t\"kph\": 18,\n" +
            "\t\t\"dir\": \"East\",\n" +
            "\t\t\"degrees\": 0\n" +
            "\t\t},\n" +
            "\t\t\"avewind\": {\n" +
            "\t\t\"mph\": 6,\n" +
            "\t\t\"kph\": 9,\n" +
            "\t\t\"dir\": \"East\",\n" +
            "\t\t\"degrees\": 0\n" +
            "\t\t},\n" +
            "\t\t\"avehumidity\": 82,\n" +
            "\t\t\"maxhumidity\": 0,\n" +
            "\t\t\"minhumidity\": 0\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\"date\":{\n" +
            "\t\"epoch\":\"1443978000\",\n" +
            "\t\"pretty\":\"7:00 PM CEST on October 04, 2015\",\n" +
            "\t\"day\":4,\n" +
            "\t\"month\":10,\n" +
            "\t\"year\":2015,\n" +
            "\t\"yday\":276,\n" +
            "\t\"hour\":19,\n" +
            "\t\"min\":\"00\",\n" +
            "\t\"sec\":0,\n" +
            "\t\"isdst\":\"1\",\n" +
            "\t\"monthname\":\"October\",\n" +
            "\t\"monthname_short\":\"Oct\",\n" +
            "\t\"weekday_short\":\"Sun\",\n" +
            "\t\"weekday\":\"Sunday\",\n" +
            "\t\"ampm\":\"PM\",\n" +
            "\t\"tz_short\":\"CEST\",\n" +
            "\t\"tz_long\":\"Europe/Berlin\"\n" +
            "},\n" +
            "\t\t\"period\":2,\n" +
            "\t\t\"high\": {\n" +
            "\t\t\"fahrenheit\":\"60\",\n" +
            "\t\t\"celsius\":\"16\"\n" +
            "\t\t},\n" +
            "\t\t\"low\": {\n" +
            "\t\t\"fahrenheit\":\"56\",\n" +
            "\t\t\"celsius\":\"13\"\n" +
            "\t\t},\n" +
            "\t\t\"conditions\":\"Clear\",\n" +
            "\t\t\"icon\":\"clear\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/clear.gif\",\n" +
            "\t\t\"skyicon\":\"\",\n" +
            "\t\t\"pop\":10,\n" +
            "\t\t\"qpf_allday\": {\n" +
            "\t\t\"in\": 0.00,\n" +
            "\t\t\"mm\": 0\n" +
            "\t\t},\n" +
            "\t\t\"qpf_day\": {\n" +
            "\t\t\"in\": 0.00,\n" +
            "\t\t\"mm\": 0\n" +
            "\t\t},\n" +
            "\t\t\"qpf_night\": {\n" +
            "\t\t\"in\": 0.00,\n" +
            "\t\t\"mm\": 0\n" +
            "\t\t},\n" +
            "\t\t\"snow_allday\": {\n" +
            "\t\t\"in\": 0.0,\n" +
            "\t\t\"cm\": 0.0\n" +
            "\t\t},\n" +
            "\t\t\"snow_day\": {\n" +
            "\t\t\"in\": 0.0,\n" +
            "\t\t\"cm\": 0.0\n" +
            "\t\t},\n" +
            "\t\t\"snow_night\": {\n" +
            "\t\t\"in\": 0.0,\n" +
            "\t\t\"cm\": 0.0\n" +
            "\t\t},\n" +
            "\t\t\"maxwind\": {\n" +
            "\t\t\"mph\": 20,\n" +
            "\t\t\"kph\": 32,\n" +
            "\t\t\"dir\": \"WSW\",\n" +
            "\t\t\"degrees\": 246\n" +
            "\t\t},\n" +
            "\t\t\"avewind\": {\n" +
            "\t\t\"mph\": 15,\n" +
            "\t\t\"kph\": 24,\n" +
            "\t\t\"dir\": \"WSW\",\n" +
            "\t\t\"degrees\": 246\n" +
            "\t\t},\n" +
            "\t\t\"avehumidity\": 82,\n" +
            "\t\t\"maxhumidity\": 0,\n" +
            "\t\t\"minhumidity\": 0\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\"date\":{\n" +
            "\t\"epoch\":\"1444064400\",\n" +
            "\t\"pretty\":\"7:00 PM CEST on October 05, 2015\",\n" +
            "\t\"day\":5,\n" +
            "\t\"month\":10,\n" +
            "\t\"year\":2015,\n" +
            "\t\"yday\":277,\n" +
            "\t\"hour\":19,\n" +
            "\t\"min\":\"00\",\n" +
            "\t\"sec\":0,\n" +
            "\t\"isdst\":\"1\",\n" +
            "\t\"monthname\":\"October\",\n" +
            "\t\"monthname_short\":\"Oct\",\n" +
            "\t\"weekday_short\":\"Mon\",\n" +
            "\t\"weekday\":\"Monday\",\n" +
            "\t\"ampm\":\"PM\",\n" +
            "\t\"tz_short\":\"CEST\",\n" +
            "\t\"tz_long\":\"Europe/Berlin\"\n" +
            "},\n" +
            "\t\t\"period\":3,\n" +
            "\t\t\"high\": {\n" +
            "\t\t\"fahrenheit\":\"62\",\n" +
            "\t\t\"celsius\":\"17\"\n" +
            "\t\t},\n" +
            "\t\t\"low\": {\n" +
            "\t\t\"fahrenheit\":\"57\",\n" +
            "\t\t\"celsius\":\"14\"\n" +
            "\t\t},\n" +
            "\t\t\"conditions\":\"Mostly Cloudy\",\n" +
            "\t\t\"icon\":\"mostlycloudy\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/mostlycloudy.gif\",\n" +
            "\t\t\"skyicon\":\"\",\n" +
            "\t\t\"pop\":10,\n" +
            "\t\t\"qpf_allday\": {\n" +
            "\t\t\"in\": 0.00,\n" +
            "\t\t\"mm\": 0\n" +
            "\t\t},\n" +
            "\t\t\"qpf_day\": {\n" +
            "\t\t\"in\": 0.00,\n" +
            "\t\t\"mm\": 0\n" +
            "\t\t},\n" +
            "\t\t\"qpf_night\": {\n" +
            "\t\t\"in\": 0.00,\n" +
            "\t\t\"mm\": 0\n" +
            "\t\t},\n" +
            "\t\t\"snow_allday\": {\n" +
            "\t\t\"in\": 0.0,\n" +
            "\t\t\"cm\": 0.0\n" +
            "\t\t},\n" +
            "\t\t\"snow_day\": {\n" +
            "\t\t\"in\": 0.0,\n" +
            "\t\t\"cm\": 0.0\n" +
            "\t\t},\n" +
            "\t\t\"snow_night\": {\n" +
            "\t\t\"in\": 0.0,\n" +
            "\t\t\"cm\": 0.0\n" +
            "\t\t},\n" +
            "\t\t\"maxwind\": {\n" +
            "\t\t\"mph\": 20,\n" +
            "\t\t\"kph\": 32,\n" +
            "\t\t\"dir\": \"SE\",\n" +
            "\t\t\"degrees\": 130\n" +
            "\t\t},\n" +
            "\t\t\"avewind\": {\n" +
            "\t\t\"mph\": 16,\n" +
            "\t\t\"kph\": 26,\n" +
            "\t\t\"dir\": \"SE\",\n" +
            "\t\t\"degrees\": 130\n" +
            "\t\t},\n" +
            "\t\t\"avehumidity\": 86,\n" +
            "\t\t\"maxhumidity\": 0,\n" +
            "\t\t\"minhumidity\": 0\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\"date\":{\n" +
            "\t\"epoch\":\"1444150800\",\n" +
            "\t\"pretty\":\"7:00 PM CEST on October 06, 2015\",\n" +
            "\t\"day\":6,\n" +
            "\t\"month\":10,\n" +
            "\t\"year\":2015,\n" +
            "\t\"yday\":278,\n" +
            "\t\"hour\":19,\n" +
            "\t\"min\":\"00\",\n" +
            "\t\"sec\":0,\n" +
            "\t\"isdst\":\"1\",\n" +
            "\t\"monthname\":\"October\",\n" +
            "\t\"monthname_short\":\"Oct\",\n" +
            "\t\"weekday_short\":\"Tue\",\n" +
            "\t\"weekday\":\"Tuesday\",\n" +
            "\t\"ampm\":\"PM\",\n" +
            "\t\"tz_short\":\"CEST\",\n" +
            "\t\"tz_long\":\"Europe/Berlin\"\n" +
            "},\n" +
            "\t\t\"period\":4,\n" +
            "\t\t\"high\": {\n" +
            "\t\t\"fahrenheit\":\"60\",\n" +
            "\t\t\"celsius\":\"16\"\n" +
            "\t\t},\n" +
            "\t\t\"low\": {\n" +
            "\t\t\"fahrenheit\":\"56\",\n" +
            "\t\t\"celsius\":\"13\"\n" +
            "\t\t},\n" +
            "\t\t\"conditions\":\"Rain\",\n" +
            "\t\t\"icon\":\"rain\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/rain.gif\",\n" +
            "\t\t\"skyicon\":\"\",\n" +
            "\t\t\"pop\":90,\n" +
            "\t\t\"qpf_allday\": {\n" +
            "\t\t\"in\": 0.51,\n" +
            "\t\t\"mm\": 13\n" +
            "\t\t},\n" +
            "\t\t\"qpf_day\": {\n" +
            "\t\t\"in\": 0.24,\n" +
            "\t\t\"mm\": 6\n" +
            "\t\t},\n" +
            "\t\t\"qpf_night\": {\n" +
            "\t\t\"in\": 0.27,\n" +
            "\t\t\"mm\": 7\n" +
            "\t\t},\n" +
            "\t\t\"snow_allday\": {\n" +
            "\t\t\"in\": 0.0,\n" +
            "\t\t\"cm\": 0.0\n" +
            "\t\t},\n" +
            "\t\t\"snow_day\": {\n" +
            "\t\t\"in\": 0.0,\n" +
            "\t\t\"cm\": 0.0\n" +
            "\t\t},\n" +
            "\t\t\"snow_night\": {\n" +
            "\t\t\"in\": 0.0,\n" +
            "\t\t\"cm\": 0.0\n" +
            "\t\t},\n" +
            "\t\t\"maxwind\": {\n" +
            "\t\t\"mph\": 35,\n" +
            "\t\t\"kph\": 56,\n" +
            "\t\t\"dir\": \"ESE\",\n" +
            "\t\t\"degrees\": 110\n" +
            "\t\t},\n" +
            "\t\t\"avewind\": {\n" +
            "\t\t\"mph\": 25,\n" +
            "\t\t\"kph\": 40,\n" +
            "\t\t\"dir\": \"ESE\",\n" +
            "\t\t\"degrees\": 110\n" +
            "\t\t},\n" +
            "\t\t\"avehumidity\": 85,\n" +
            "\t\t\"maxhumidity\": 0,\n" +
            "\t\t\"minhumidity\": 0\n" +
            "\t\t}\n" +
            "\t\t]\n" +
            "\t\t}\n" +
            "\t}\n" +
            "}";


}
