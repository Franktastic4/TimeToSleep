package com.example.gftlhackathon.timetosleep;
import android.util.Log;
import android.widget.TextView;

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
 * Created by Franktastin4 on 10/25/15.
 */
public class Message {

    // Find your Account Sid and Token at twilio.com/user/account
    public static final String ACCOUNT_SID = "AC177eacda37327f6a589fc592cdc5efa0";
    public static final String AUTH_TOKEN = "{{ c1cea8d87cf075b802ebfe0c89d9cdb5 }}";

    public static void main(String[] args) throws TwilioRestException {
        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

// Build a filter for the SmsList
        List params = new ArrayList();

        Log.d("idk", args[1]);

        params.add(new BasicNameValuePair("Body", "HW please?! I love you <3"));
        params.add(new BasicNameValuePair("To", "+9255226233"));
        params.add(new BasicNameValuePair("From", "+14082284478"));

        SmsFactory smsFactory = client.getAccount().getSmsFactory();
        Sms sms = smsFactory.create(params);
        System.out.println(sms.getSid());
    }
}
