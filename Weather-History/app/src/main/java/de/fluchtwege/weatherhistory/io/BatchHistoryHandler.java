package de.fluchtwege.weatherhistory.io;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.OperationApplicationException;
import android.os.RemoteException;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.fluchtwege.weatherhistory.Util;
import de.fluchtwege.weatherhistory.provider.WeatherHistoryContract;

public class BatchHistoryHandler extends BaseHandler {

    //can only retrieve data of last 8 years due to free license for wundergroung
    public static final int MAX_YEARS_OF_AVAILABLE_DATA = 8;

    public static final int START_YEAR_OF_AVAILABLE_DATA = 2013;

    private static final String LOG_TAG = "WHBaseHandler";

    private static final String TAG_HISTORY = "history";
    private static final String TAG_DAILY_SUMMARY = "dailysummary";
    private static final String TAG_MAX_TEMP = "maxtempm";
    private static final String TAG_MIN_TEMP = "mintempm";
    private static final String TAG_DATE = "date";
    private static final String TAG_MDAY = "mday";
    private static final String TAG_MON = "mon";
    private static final String TAG_YEAR = "year";

    private static final String BASE_URL = "http://api.wunderground.com/api/56b55db73f36509e/history_[DATE]/q/DE/EDXH.json";
    private static final String DATE = "[DATE]";

    private ArrayList<ContentProviderOperation> operations;
    private int numberOfCalls = 0;

    public BatchHistoryHandler(Context ctx) {
        super(ctx);
        operations = new ArrayList<ContentProviderOperation>();
    }

    @Override
    public void enqueueRequests() {
        for (int i = 0; i < MAX_YEARS_OF_AVAILABLE_DATA; i++) {
            String yearOfRequest = new StringBuilder("").append(START_YEAR_OF_AVAILABLE_DATA - i).
                    append(Util.getDateForHistory()).toString();
            enqueueRequestUrl(createRequestUrl(yearOfRequest));
            numberOfCalls++;
        }
    }

    private void enqueueRequestUrl(String requestUrl) {
        request = new JsonObjectRequest(getMethod(), requestUrl, null, this, this);
        RequestQueue queue = IOController.getRequestQueue(context);
        queue.add(request);
    }

    private String createRequestUrl(String yearOfRequest) {
        String url = BASE_URL.replace(DATE, yearOfRequest);
        Log.d(LOG_TAG, "url: " + url);
        return url;
    }

    @Override
    public void onResponse(JSONObject object) {
        Log.d(LOG_TAG, "onResponse");
        numberOfCalls--;
        operations.add(parseHistory(object));
        try {
            if (numberOfCalls == 0) {
                ContentResolver resolver = context.getContentResolver();
                resolver.applyBatch(WeatherHistoryContract.CONTENT_AUTHORITY, operations);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        Log.i(LOG_TAG, "onErrorResponse");
        try {
            numberOfCalls--;
            if (numberOfCalls == 0 && operations.size() > 0) {
                ContentResolver resolver = context.getContentResolver();
                resolver.applyBatch(WeatherHistoryContract.CONTENT_AUTHORITY, operations);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }
    }

    private ContentProviderOperation parseHistory(JSONObject object) {
        Log.i(LOG_TAG, "parseHistory "+ object.toString());
        int maxTemp = 0;
        int minTemp = 0;
        String date = null;
        try {
            if (object.has(TAG_HISTORY)) {
                JSONObject history = object.getJSONObject(TAG_HISTORY);
                date = parseHistoryDate(history);
                maxTemp = parseHistoryMax(history);
                minTemp = parseHistoryMin(history);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.v(LOG_TAG, "max: " + maxTemp + " min: " + minTemp);
        return createContentProviderOperationBuilder(maxTemp, minTemp, date).build();
    }

    private String parseHistoryDate(JSONObject history) throws JSONException {
        String date = null;
        if (history.has(TAG_DATE)) {
            JSONObject datum = history.getJSONObject(TAG_DATE);
            if (datum.has(TAG_YEAR)) {
                date = datum.getString(TAG_YEAR);
            }
            if (datum.has(TAG_MON)) {
                date = date.concat(datum.getString(TAG_MON));
            }
            if (datum.has(TAG_MDAY)) {
                date = date.concat(datum.getString(TAG_MDAY));
            }
        }
        return date;
    }
    private int parseHistoryMax(JSONObject history) throws JSONException {
        int max = 0;
        if (history.has(TAG_DAILY_SUMMARY)) {
            JSONArray dailysummary = history.getJSONArray(TAG_DAILY_SUMMARY);
            if (dailysummary.length() > 0) {
                JSONObject summary = dailysummary.getJSONObject(0);
                if (summary.has(TAG_MAX_TEMP)) {
                    max = summary.getInt(TAG_MAX_TEMP);
                }
            }
        }
        return max;
    }

    private int parseHistoryMin(JSONObject history) throws JSONException {
        int min = 0;
        if (history.has(TAG_DAILY_SUMMARY)) {
            JSONArray dailySummary = history.getJSONArray(TAG_DAILY_SUMMARY);
            if (dailySummary.length() > 0) {
                JSONObject summary = dailySummary.getJSONObject(0);
                if (summary.has(TAG_MIN_TEMP)) {
                    min = summary.getInt(TAG_MIN_TEMP);
                }
            }
        }
        return min;
    }

    private ContentProviderOperation.Builder createContentProviderOperationBuilder(int maxTemp, int minTemp, String date) {
        return ContentProviderOperation
                .newInsert(WeatherHistoryContract.WeatherHistory.CONTENT_URI)
                .withValue(WeatherHistoryContract.WeatherDataColumns.MAX_CELSIUS, maxTemp)
                .withValue(WeatherHistoryContract.WeatherDataColumns.MIN_CELSIUS, minTemp).withValue(WeatherHistoryContract.WeatherDataColumns.DATE, date);
    }

    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    protected int getMethod() {
        return Request.Method.GET;
    }


}
