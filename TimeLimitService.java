package com.example.gftlhackathon.timetosleep;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;
import android.view.MotionEvent;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Sms;
import com.twilio.sdk.resource.list.SmsList;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 * Created by Franktastin4 on 10/24/15.
 */
public class TimeLimitService extends Service {

    public static final String TAG = "Alarm";

    Alarm alarm = new Alarm();
    public void onCreate()
    {
        super.onCreate();
        Log.d(TAG, "Service has started");
        // implement the on click listener
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);

        if (pm.isScreenOn()){
            Log.d(TAG, "Your Screen is on!");

            SMSMessage message = new SMSMessage();
            try {
                String[] test = {"3","4","5"};
                message.main(test);
            }catch (Exception e){
                e.printStackTrace();
            }

        }



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
