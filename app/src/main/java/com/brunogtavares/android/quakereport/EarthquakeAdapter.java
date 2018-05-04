package com.brunogtavares.android.quakereport;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.brunogtavares.android.quakereport.model.Earthquake;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by brunogtavares on 4/22/18.
 * {@link EarthquakeAdapter} is an {@link ArrayAdapter} that can provide the layout for each list
 * based on a data source, which is a list of {@link Earthquake} objects.
 **/
public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOG_TAG = EarthquakeAdapter.class.getSimpleName();

    /**
     * This is our own custom constructor (it doesn't mirror the superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want to populate into the lists.
     * @param context The current context. Used to inflate the layout file.
     * @param earthquakeList A list of Earthquake objects to display in a list.
     */
    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakeList) {
        super(context, 0, earthquakeList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list_item, parent, false);
        }

        // Get the {@link Earthquake} object located at this position in the list
        Earthquake currentEarthquakeItem = getItem(position);

        // Find the TextView in the earthquake_list_itemem.xml layout with the ID magnitude
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.tv_magnitude);

        // Get the magnitude from the current Earthquake object and
        // set text to the magnitude TextView
        magnitudeTextView.setText(currentEarthquakeItem.getMagnitude().toString());

        // Find the TextView in the earthquake_list_item.xmlml layout with the ID location
        TextView locationTextView = (TextView) listItemView.findViewById(R.id.tv_location_name);

        // Get the location from the current Earthquake object and
        // set text to the location TextView
        locationTextView.setText(currentEarthquakeItem.getLocation());

        // Find the TextView in the earthquake_list_item.xmlml layout with the ID date
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.tv_date);

        // Get the time in milliseconds and pass it to create a date object to be used
        // with two date formats.
        long timeInMilliSeconds = currentEarthquakeItem.getDate();
        Date dateObject = new Date(timeInMilliSeconds);

        // Get the date from the current Earthquake object and
        // set text to the date TextView
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
        String dateToDisplay = dateFormatter.format(dateObject);
        dateTextView.setText(dateToDisplay);

        // Find the TextView in the earthquake_list_item.xmlml layout with the ID dateTime
        TextView dateTimeTextView = (TextView) listItemView.findViewById(R.id.tv_dateTime);

        // Get the time from the current Earthquake object and
        // set text to the dateTime TextView
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("h:mm a");
        String dateTimeToDisplay = dateTimeFormatter.format(dateObject);
        dateTimeTextView.setText(dateTimeToDisplay);

        // Return the whole list item layout (3 views: magnitude, location, and date)
        // so that it can be shown in the list view
        return listItemView;

    }
}
