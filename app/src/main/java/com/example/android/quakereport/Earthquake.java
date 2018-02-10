package com.example.android.quakereport;

import java.util.Date;

/**
 * Created by israe on 1/16/2018.
 */

public class Earthquake {
    /*magnitude of the earthquake*/
    private double mMagnitude;

    private String mLocation;

    private  long mDate;

//  website url to the earthquake
    private String mUrl;

    /**
     constructs a new {@link Earthquake} object
     @param magnitude is the magnitude of the earthquake
     @param  location is the loction of the earthquake
     @param date is the date of the earthquake
     */
    public Earthquake (double magnitude, String location, long date, String url) {
        mMagnitude = magnitude;
        mLocation = location;
        mDate = date;
        mUrl = url;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getTimeInMilliSeconds() {
        return mDate;
    }

    public String getUrl() {
        return mUrl;
    }
}
