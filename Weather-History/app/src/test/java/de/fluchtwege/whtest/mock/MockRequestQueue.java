package de.fluchtwege.whtest.mock;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;

public class MockRequestQueue extends RequestQueue {

    public MockRequestQueue() {
        super(null, null);
    }

    @Override
    public Request add(Request request) {
        return null;
    }
}
