package com.brunogtavares.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.brunogtavares.android.quakereport.model.Earthquake;
import com.brunogtavares.android.quakereport.utils.QueryUtils;

import java.util.List;

/**
 * Created by brunogtavares on 5/16/18.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    private String mUrl;

    public EarthquakeLoader(Context context, String url) {
        super(context);
        this.mUrl = url;

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground() {
        if (mUrl == null) return null;
        return QueryUtils.extractEarthquakes(mUrl);
    }
}
