package com.example.footest;

import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;

public class HistoryHandler extends BaseHandler {

	public HistoryHandler(Context ctx, String date) {
		super(ctx);
		mDate = date;
		handle();
	}

	private static final String DATE = "[DATE]";
	private static final String BASE_URL = "http://api.wunderground.com/api/56b55db73f36509e/history_[DATE]/q/DE/EDXH.json";
	private static final String LOG_TAG = "WeatherHistory.HistoryHandler";
	private String mDate = null;

	@Override
	protected void handleDone(JSONObject object) {
		Log.i(LOG_TAG, "object:" + object.toString());
	}

	@Override
	protected void handleFailed(VolleyError error) {
	}

	@Override
	protected String getUrl() {
		return BASE_URL.replace(DATE, mDate);
	}

	@Override
	protected int getMethod() {
		return Request.Method.GET;
	}

}
