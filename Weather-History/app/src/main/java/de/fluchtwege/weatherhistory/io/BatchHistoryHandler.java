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

    private static final String TAG_HISTORY = "history";
    private static final String TAG_DAILY_SUMMARY = "dailysummary";
    private static final String TAG_MAX_TEMP = "maxtempm";
    private static final String TAG_MIN_TEMP = "mintempm";
    private static final String DATE = "[DATE]";
    private static final String BASE_URL = "http://api.wunderground.com/api/56b55db73f36509e/history_[DATE]/q/DE/EDXH.json";
    private static final String LOG_TAG = "WeatherHistory.HistoryHandler";
    private static final String TAG_DATE = "date";
    private static final String TAG_MDAY = "mday";
    private static final String TAG_MON = "mon";
    private static final String TAG_YEAR = "year";

    ArrayList<ContentProviderOperation> batch = null;
    int numberOfCalls = 0;
    private String[] mUrls = null;

    public BatchHistoryHandler(Context ctx) {
        super(ctx);
        batch = new ArrayList<ContentProviderOperation>();
        mUrls = new String[8];

        for (int i =0; i < 8; i++) {
            mUrls[i] = new StringBuilder("").append(2013-i).append(Util.getDateForHistory()).toString();
            handle(i);
            numberOfCalls++;
        }
    }

    @Override
    public void onResponse(JSONObject object) {
        Log.i(LOG_TAG,"onResponse");
        numberOfCalls--;
        batch.add(parseHistory(object));
        try {
            if (numberOfCalls == 0) {
                ContentResolver resolver = mCtx.getContentResolver();
                resolver.applyBatch(WeatherHistoryContract.CONTENT_AUTHORITY, batch);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        Log.i(LOG_TAG,"onErrorResponse");
        try {
            numberOfCalls--;
            if (numberOfCalls == 0 && batch.size() > 0) {
                ContentResolver resolver = mCtx.getContentResolver();
                resolver.applyBatch(WeatherHistoryContract.CONTENT_AUTHORITY, batch);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }
    }

    private ContentProviderOperation parseHistory(JSONObject object) {
        int max = 0;
        int min = 0;
        String date = null;
        try {
            if (object.has(TAG_HISTORY)) {
                JSONObject history = object.getJSONObject(TAG_HISTORY);
                if (history.has(TAG_DATE)){
                    JSONObject datum = history.getJSONObject(TAG_DATE);
                    if (datum.has(TAG_YEAR)){
                        date = datum.getString(TAG_YEAR);
                    }
                    if (datum.has(TAG_MON)){
                        date = date.concat(datum.getString(TAG_MON));
                    }
                    if (datum.has(TAG_MDAY)){
                        date = date.concat(datum.getString(TAG_MDAY));
                    }
                }
                if (history.has(TAG_DAILY_SUMMARY)) {
                    JSONArray dailysummary = history.getJSONArray(TAG_DAILY_SUMMARY);
                    if (dailysummary.length() > 0) {
                        JSONObject summary = dailysummary.getJSONObject(0);
                        if (summary.has(TAG_MAX_TEMP)) {
                            max = summary.getInt(TAG_MAX_TEMP);
                        }
                        if (summary.has(TAG_MIN_TEMP)) {
                            min = summary.getInt(TAG_MIN_TEMP);
                        }
                    }

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i(LOG_TAG, "max: " + max + " min: " + min);
        final ContentProviderOperation.Builder builder = ContentProviderOperation
                .newInsert(WeatherHistoryContract.WeatherHistory.CONTENT_URI)
                .withValue(WeatherHistoryContract.WeatherDataColumns.MAX_CELSIUS, max)
                .withValue(WeatherHistoryContract.WeatherDataColumns.MIN_CELSIUS, min).withValue(WeatherHistoryContract.WeatherDataColumns.DATE, date);
        return builder.build();
    }

    public void handle(int id) {
        mRequest = new JsonObjectRequest(getMethod(), getUrl(id), null, this, this);
        RequestQueue queue = Volley.newRequestQueue(mCtx);
        queue.add(mRequest);

    }

    private String getUrl(int id) {
        return BASE_URL.replace(DATE, mUrls[id]);
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
