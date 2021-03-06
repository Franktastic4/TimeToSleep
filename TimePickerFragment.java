package com.example.gftlhackathon.timetosleep;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Franktastin4 on 10/24/15.
 */
public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    //public TimePickerFragmentCallback mTimePickerFragmentCallback;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Put into shared preference
        SharedPreferences prefs = getActivity().getSharedPreferences("Time", Context.MODE_PRIVATE);

        Log.d("TimePickerFragment", "This is the current state: " + prefs.getInt("LockedBoolean", 0));

        prefs.edit().putInt("Time_hour", hourOfDay).apply();
        prefs.edit().putInt("Time_mins", minute).apply();

        Alarm alarm = new Alarm();
        alarm.SetAlarm(getActivity().getApplicationContext());

       //mTimePickerFragmentCallback.timeSet();


    }

    //static interface TimePickerFragmentCallback {
    //    void timeSet();
    //}




}
