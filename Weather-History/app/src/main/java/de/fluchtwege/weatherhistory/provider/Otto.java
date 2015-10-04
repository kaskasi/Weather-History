package de.fluchtwege.weatherhistory.provider;

import com.squareup.otto.Bus;

public class Otto {

    private static Bus bus;

    public static Bus getBus() {
        if (bus == null) {
            bus = new Bus();
        }
        return bus;
    }

}
