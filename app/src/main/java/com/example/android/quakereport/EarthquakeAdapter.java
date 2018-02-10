package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Created by israe on 1/16/2018.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR = " of ";

    EarthquakeAdapter(Context context, List<Earthquake> earthquakes){
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listViewItem = convertView;
        if(listViewItem == null){
            listViewItem = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item,
                    parent, false);
        }

        //find current earthquake
        Earthquake currentEarthQuake = getItem(position);

        //format the earthquake magnitude before setting it to the textView
        String formattedMag = formatMagnitude(currentEarthQuake.getMagnitude());

        //get the magnitude textView
        TextView magView = (TextView) listViewItem.findViewById(R.id.magnitude);
        magView.setText(formattedMag);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthQuake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        //get the location of the earthquake
        String originalLocation = currentEarthQuake.getLocation();
        String primaryLocation;
        String locationOffset;

        //split it into 2 separate views if possible
        if(originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        //get the offset location textView
        TextView offsetLocationTextView = (TextView) listViewItem.findViewById(R.id.location_offset);
        offsetLocationTextView.setText(locationOffset);

        //get primary location textView
        TextView primaryLocationTextView = (TextView) listViewItem.findViewById(R.id.primary_location);
        primaryLocationTextView.setText(primaryLocation);

        Date dateObject = new Date(currentEarthQuake.getTimeInMilliSeconds());

        //find date textView
        TextView dateTextView = (TextView) listViewItem.findViewById(R.id.date);
        String formattedDate = formatDate(dateObject);
        dateTextView.setText(formattedDate);

        //find time TextView
        TextView timeTextView = (TextView) listViewItem.findViewById(R.id.time);
        String formattedTime = formatTime(dateObject);
        timeTextView.setText(formattedTime);

        //return the list item that is now showing the appropriate data
        return listViewItem;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloored = (int) Math.floor(magnitude);

        switch(magnitudeFloored){
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;

            case 3:
                magnitudeColorResourceId = R.color.magnitude3;

            case 4:
                magnitudeColorResourceId = R.color.magnitude4;

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
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
