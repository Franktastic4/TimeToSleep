package com.example.gftlhackathon.timetosleep;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by Franktastin4 on 10/24/15.
 */
public class TimeLimitService extends Service {

    public static final String TAG = "Alarm";

    Alarm alarm = new Alarm();
    public void onCreate()
    {
        super.onCreate();
        Log.d(TAG, "Locking access");
        // impliment the on click listener


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        alarm.SetAlarm(this);
        return START_STICKY;
    }

    @Override
    public void onStart(Intent intent, int startId)
    {
        alarm.SetAlarm(this);
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }
}
