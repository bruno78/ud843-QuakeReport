package com.brunogtavares.android.quakereport.model;

/**
 * Created by brunogtavares on 4/22/18.
 */

public class Earthquake {

    private String magnitude;
    private String location;
    private long date;

    public Earthquake(String magnitude, String location, long date) {
        this.magnitude = magnitude;
        this.location = location;
        this.date = date;
    }


    public String getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
