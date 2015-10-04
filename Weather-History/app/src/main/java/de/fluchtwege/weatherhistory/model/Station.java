package de.fluchtwege.weatherhistory.model;

public class Station {

    private String country;
    private String city;
    private String longitude;
    private String latitude;

    public Station(String country, String city, String longitude, String latitude) {
        this.country = country;
        this.city = city;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }
}
