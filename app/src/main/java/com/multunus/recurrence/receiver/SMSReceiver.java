package com.multunus.recurrence.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {
    public SmsReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Bundle bundle = intent.getExtras();
            SmsMessage[] messages = null;
            String messageFrom = null;
            if (bundle != null) {
                try {
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    messages = new SmsMessage[pdus.length];
                    for (int i = 0; i < messages.length; i++) {
                        messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        messageFrom = messages[i].getOriginatingAddress();
                        String messageBody = messages[i].getMessageBody();
                        Log.d("recurrence", messageFrom);
                        Log.d("recurrence", messageBody);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("recurrence", e.getMessage());
                }
            }
        }
    }
}
