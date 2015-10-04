package de.fluchtwege.whtest.mock;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import java.util.ArrayList;

public class MockRequestQueue extends RequestQueue {

    private ArrayList<Request> requests;

    public MockRequestQueue() {
        super(null, null);
        requests = new ArrayList<Request>();
    }

    @Override
    public Request add(Request request) {
        requests.add(request);
        return null;
    }

    public int getQueueSize() {
        return requests.size();
    }

    public void clearQueue() {
        requests.clear();
    }

}
