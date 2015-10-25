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
public class AlarmReciever extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
        wl.acquire();

        // Put here YOUR code.
        Log.d("AlarmReciever", "Alarm!!!");

        wl.release();
    }

    public void SetAlarm(Context context)
    {

        AlarmManager am =(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, AlarmReciever.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);

        // Get shared preference
        SharedPreferences prefs = context.getSharedPreferences("Time", Context.MODE_PRIVATE);
        int hour = prefs.getInt("Time_hours", new Date().getHours());
        int minutes = prefs.getInt("Time_mins", new Date().getMinutes());
        Calendar calender = Calendar.getInstance();
        Date date = calender.getTime();
        date.setHours(hour);
        date.setMinutes(minutes);
        calender.setTime(date);

        // Only runs it once
        am.set(AlarmManager.RTC_WAKEUP, calender.getTimeInMillis(), pi);

    }

    public void CancelAlarm(Context context)
    {
        Intent intent = new Intent(context, AlarmReciever.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }
}
