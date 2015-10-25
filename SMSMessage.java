package com.example.gftlhackathon.timetosleep;
import android.util.Log;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Message;
import com.twilio.sdk.resource.instance.Sms;
import com.twilio.sdk.resource.list.MessageList;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
/**
 * Created by Franktastin4 on 10/25/15.
 */
public class SMSMessage{

    // Find your Account Sid and Token at twilio.com/user/account
    public static final String ACCOUNT_SID = "AC177eacda37327f6a589fc592cdc5efa0";
    public static final String AUTH_TOKEN = "c1cea8d87cf075b802ebfe0c89d9cdb5";

    public static void main(String[] args) throws TwilioRestException {
        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

// Build a filter for the SmsList
        Log.d("idk", args[1]);

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Body", "Jenny please?! I love you <3"));
        params.add(new BasicNameValuePair("To", "+19255226233"));
        params.add(new BasicNameValuePair("From", "+14082284478"));
        params.add(new BasicNameValuePair("MediaUrl", "http://www.example.com/hearts.png"));

        MessageFactory messageFactory = client.getAccount().getMessageFactory();
        Message message = messageFactory.create(params);
        System.out.println(message.getSid());
    }
}
