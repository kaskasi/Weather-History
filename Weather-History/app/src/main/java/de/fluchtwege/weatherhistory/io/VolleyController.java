package de.fluchtwege.weatherhistory.io;


import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyController {

    private static RequestQueue requestQueue;

    public static void setRequestQueue(RequestQueue queue) {
        VolleyController.requestQueue = queue;
    }

    public static RequestQueue getRequestQueue(Context ctx) {
        if (VolleyController.requestQueue == null) {
            requestQueue = Volley.newRequestQueue(ctx);
        }
        return requestQueue;
    }
}
