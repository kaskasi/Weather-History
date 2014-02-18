package com.example.footest;

import org.json.JSONObject;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public abstract class BaseHandler implements Listener<JSONObject>, ErrorListener {

	private JsonObjectRequest mRequest = null;
	protected Context mCtx = null;

	public BaseHandler(Context ctx) {
		mCtx = ctx;
	}

	protected abstract void handleDone(JSONObject object);

	protected abstract void handleFailed(VolleyError error);

	protected abstract String getUrl();

	protected abstract int getMethod();

	public void handle() {
		mRequest = new JsonObjectRequest(getMethod(), getUrl(), null, this, this);
		RequestQueue queue = Volley.newRequestQueue(mCtx);
		queue.add(mRequest);
	}

	@Override
	public void onResponse(JSONObject object) {
		handleDone(object);
	}

	@Override
	public void onErrorResponse(VolleyError error) {
		handleFailed(error);
	}

}
