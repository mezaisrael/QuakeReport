package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by israe on 1/23/2018.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>>{

    /** Tag for log messages */
    private static final String LOG_TAG = EarthquakeLoader.class.getName();

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link EarthquakeLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "Inside onStartLoading");
        forceLoad();
    }


    @Override
    public List<Earthquake> loadInBackground() {
        Log.i(LOG_TAG, "inside loadInBackground");
        //do not perform the request if there is no url, or if the first url is null
        if( mUrl == null){
            return null;
        }

        ArrayList<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(mUrl);
        return earthquakes;
    }

}
