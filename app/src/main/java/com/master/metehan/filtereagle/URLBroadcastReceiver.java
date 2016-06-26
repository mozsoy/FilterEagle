package com.master.metehan.filtereagle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
/**
 * Created by Metehan on 6/9/2016.
 */

/**
 * Broadcast receiver to send url map periodically
 */
public class URLBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Intent urlService = new Intent(context, URLAlarmService.class);
        context.startService(urlService);
    }
}
