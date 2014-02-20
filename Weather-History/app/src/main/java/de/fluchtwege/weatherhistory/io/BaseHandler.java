package de.fluchtwege.weatherhistory.io;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public abstract class BaseHandler implements Listener<JSONObject>, ErrorListener {

	protected JsonObjectRequest mRequest = null;
	protected Context mCtx = null;

	public BaseHandler(Context ctx) {
		mCtx = ctx;
	}

	protected abstract String getUrl();

	protected abstract int getMethod();

	public void handle() {
		mRequest = new JsonObjectRequest(getMethod(), getUrl(), null, this, this);
		RequestQueue queue = Volley.newRequestQueue(mCtx);
		queue.add(mRequest);
	}




}
