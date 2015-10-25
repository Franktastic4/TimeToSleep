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

        Log.d(TAG, "Intent received");
        SharedPreferences prefs = context.getSharedPreferences("Time", Context.MODE_PRIVATE);

        // If its already on

        if(prefs.getInt("LockedBoolean", 0) == 1){

            Log.d(TAG, "Ending the service");

            prefs.edit().putInt("LockedBoolean", 0).apply();
            context.stopService(new Intent(context, TimeLimitService.class));
            Log.d(TAG, "Service Stopped");

            CancelAlarm(context);
            Log.d(TAG, "Alarm Canceled");



        }else{ // If it is off
            prefs.edit().putInt("LockedBoolean", 1).apply();
            Log.d(TAG, "Starting Serivce");
            context.startService(new Intent(context, TimeLimitService.class));

        }




        wl.release();
    }

    public void SetAlarm(Context context)
    {

        // Get shared preference
        SharedPreferences prefs = context.getSharedPreferences("Time", Context.MODE_PRIVATE);
        int hour = prefs.getInt("Time_hours", new Date().getHours());
        int minutes = prefs.getInt("Time_mins", new Date().getMinutes());
        Calendar calender = Calendar.getInstance();

        Log.d(TAG, "Time is : " + calender.getTime().toString());

        Date date = calender.getTime();
        date.setHours(hour);
        date.setMinutes(minutes);
        date.setSeconds(0);
        calender.setTime(date);

        // Run if not equal to or in past
        if(calender.getTimeInMillis() > System.currentTimeMillis()){
            Log.d(TAG, "Alarm Created and set to: " + calender.getTime().toString() );
            AlarmManager am =( AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
            Intent i = new Intent(context, Alarm.class);
            PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
            am.set(AlarmManager.RTC_WAKEUP, calender.getTimeInMillis(), pi);

            //reset preferences, won't accidentally create more alarms
            prefs.edit().putInt("Time_hour", Calendar.getInstance().getTime().getHours()).apply();
            prefs.edit().putInt("Time_mins", Calendar.getInstance().getTime().getMinutes()).apply();
        }

    }

    public void CancelAlarm(Context context)
    {

        Log.d(TAG, "Alarm canceled");
        Intent intent = new Intent(context, Alarm.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }
}
