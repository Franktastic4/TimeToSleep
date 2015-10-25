package com.example.gftlhackathon.timetosleep;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Franktastin4 on 10/24/15.
 */
public class Alarm extends BroadcastReceiver
{

    public static final String TAG = "Alarm";

    @Override
    public void onReceive(Context context, Intent intent)
    {

        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
        wl.acquire();

        // Put here YOUR code.
        Log.d(TAG, "Locking Now!");

        wl.release();
    }

    public void SetAlarm(Context context)
    {

        /*
        Log.d(TAG, "Set Alarm called");
        AlarmManager am =(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, Alarm.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        */

        // Get shared preference
        SharedPreferences prefs = context.getSharedPreferences("Time", Context.MODE_PRIVATE);
        int hour = prefs.getInt("Time_hours", new Date().getHours());
        int minutes = prefs.getInt("Time_mins", new Date().getMinutes());
        Calendar calender = Calendar.getInstance();

        Log.d(TAG, "Time is : " + calender.getTime().toString());

        Date date = calender.getTime();
        date.setHours(hour);
        date.setMinutes(minutes);
        calender.setTime(date);

        Log.d(TAG, "Set Alarm to: " + calender.getTime().toString());


        AlarmManager am =( AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, Alarm.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);

        // Only runs it once
        am.set(AlarmManager.RTC_WAKEUP, calender.getTimeInMillis(), pi);

        // Cancel if alarm is in past
        if(calender.getTimeInMillis() < System.currentTimeMillis()){
            CancelAlarm(context);
        }

        /*
        AlarmManager amm =( AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent ii = new Intent(context, Alarm.class);
        PendingIntent pii = PendingIntent.getBroadcast(context, 0, ii, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 60 * 1, pii);
        */

    }

    public void CancelAlarm(Context context)
    {

        Log.d(TAG, "Alarm cancled");
        Intent intent = new Intent(context, Alarm.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }
}
