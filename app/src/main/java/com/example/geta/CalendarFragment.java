package com.example.geta;

import static android.content.ContentValues.TAG;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarFragment extends Fragment {

    public CalendarFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

            CompactCalendarView compactCalendar = view.findViewById(R.id.compact_calendar);
            // Set first day of week to Monday, defaults to Monday so calling setFirstDayOfWeek is not necessary
            // Use constants provided by Java Calendar class
            compactCalendar.setFirstDayOfWeek(Calendar.MONDAY);

            // Add event 1 on Sun, 07 Jun 2015 18:20:51 GMT
            Event ev1 = new Event(Color.GREEN, 1433701251000L, "Some extra data that I want to store.");
            compactCalendar.addEvent(ev1);

            // Added event 2 GMT: Sun, 07 Jun 2015 19:10:51 GMT
            Event ev2 = new Event(Color.GREEN, 1433704251000L);
            compactCalendar.addEvent(ev2);

            // Query for events on Sun, 07 Jun 2015 GMT.
            // Time is not relevant when querying for events, since events are returned by day.
            // So you can pass in any arbitary DateTime and you will receive all events for that day.
            List<Event> events = compactCalendar.getEvents(1433701251000L); // can also take a Date object

            // events has size 2 with the 2 events inserted previously
            Log.d(TAG, "Events: " + events);

            // define a listener to receive callbacks when certain events happen.
            compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
                @Override
                public void onDayClick(Date dateClicked) {
                    List<Event> events = compactCalendar.getEvents(dateClicked);
                    Log.d(TAG, "Day was clicked: " + dateClicked + " with events " + events);
                }

                @Override
                public void onMonthScroll(Date firstDayOfNewMonth) {
                    Log.d(TAG, "Month was scrolled to: " + firstDayOfNewMonth);
                }
            });
    }
}
