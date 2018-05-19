package com.brunogtavares.android.quakereport;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.brunogtavares.android.quakereport.model.Earthquake;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.graphics.drawable.GradientDrawable;

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
    public EarthquakeAdapter(Activity context, List<Earthquake> earthquakeList) {
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

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquakeItem.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        // Get the magnitude from the current Earthquake object and
        // set text to the magnitude TextView
        DecimalFormat formatter = new DecimalFormat("0.0");
        String magnitudeValue = formatter.format(currentEarthquakeItem.getMagnitude());
        magnitudeTextView.setText(magnitudeValue);

        // Find the TextView in the earthquake_list_item.xmlml layout with the ID location
        TextView locationTextView = (TextView) listItemView.findViewById(R.id.tv_location_name);
        TextView offsetLocationTextView = (TextView) listItemView.findViewById(R.id.tv_offset_location);

        // Get the location from the current Earthquake object and
        // set text to the location TextView
        String location = currentEarthquakeItem.getLocation();
        int endOffset = location.indexOf("of");

        if(endOffset > -1) {
            String offSetLocation = location.substring(0, endOffset + 2);
            String locationName = location.substring(endOffset + 3, location.length());
            offsetLocationTextView.setText(offSetLocation);
            locationTextView.setText(locationName);

        }
        else {
            offsetLocationTextView.setText("Near the");
            locationTextView.setText(location);
        }


        // Get the time in milliseconds and pass it to create a date object to be used
        // with two date formats.
        long timeInMilliSeconds = currentEarthquakeItem.getDate();
        Date dateObject = new Date(timeInMilliSeconds);

        // Find the TextView in the earthquake_list_item.xmlml layout with the ID date
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.tv_date);

        // Get the date from the current Earthquake object and
        // set text to the date TextView
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM d, yyyy");
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

    /**
     * This helper method gets the magnitude color according with magnitude scale.
     * @param magnitude value in double.
     * @return integer resource id value.
     */
    private int getMagnitudeColor( double magnitude ) {
        int magnitudeColorResourceId;

        // The switch statement cannot accept a double value, so we should convert our decimal magnitude value into an integer
        int magnitudeFloor = (int) Math.floor(magnitude);

        switch(magnitudeFloor) {

            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;

        }

        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
