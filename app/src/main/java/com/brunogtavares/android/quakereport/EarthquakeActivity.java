/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.brunogtavares.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.brunogtavares.android.quakereport.model.Earthquake;
import com.brunogtavares.android.quakereport.utils.QueryUtils;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public final String LOG_TAG = EarthquakeActivity.class.getName();
    public final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";
    private ArrayList<Earthquake> mEarthquakes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // temporarily set
        mEarthquakes = null;

        // Create an {@link EarthquakeAdapter}, whose data source is a list of
        // {@link Earthquake}s. The adapter knows how to create list item views for each
        // in the list.
        final EarthquakeAdapter adapter = new EarthquakeAdapter(this, mEarthquakes);

        // Get a reference to the listView, and attach the adapter to the listView.
        ListView earthquakeListView = (ListView) findViewById(R.id.list);
        earthquakeListView.setAdapter(adapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // Find the current earthquake that was clicked on
                Earthquake currentEarthquake = adapter.getItem(i);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

                // Create a new implicit intent to take to earthquake website with more info
                Intent earthquakeIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Send the intent to launch a new activity
                startActivity(earthquakeIntent);
            }
        });
    }

    // TODO: Refactor by adding a helper method to populate the view and Add AsyncTask
}
