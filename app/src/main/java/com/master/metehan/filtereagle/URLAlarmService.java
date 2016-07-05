package com.master.metehan.filtereagle;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;

/**
 * Created by Metehan on 6/9/2016.
 */
public class URLAlarmService extends Service {
    private NotificationManager mManager;

    @Override
    public IBinder onBind(Intent arg0)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate()
    {
        // TODO Auto-generated method stub
        super.onCreate();
    }

    @SuppressWarnings("static-access")
    @Override
    public void onStart(Intent intent, int startId)
    {
        super.onStart(intent, startId);

        /* THIS PART IS TO ADD A NOTIFICATION
        mManager = (NotificationManager) this.getApplicationContext().getSystemService(this.getApplicationContext().NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(this.getApplicationContext(),ServiceSinkhole.class);

        Notification notification = new Notification(R.mipmap.ic_launcher,"AdBlock list updated", System.currentTimeMillis());
        intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingNotificationIntent = PendingIntent.getActivity( this.getApplicationContext(),0, intent1,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        //notification.(this.getApplicationContext(), "AlarmManagerDemo", "This is a test message!", pendingNotificationIntent);

        mManager.notify(0, notification);
        */
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isLogged = prefs.getBoolean("logged", false);
        if(isLogged) {
            String server_url = this.getString(R.string.serverurl);
            String uid = prefs.getString("uid", "");
            URLMapSender urlMapSender = new URLMapSender(server_url, uid, this);
            urlMapSender.sendURLMap();
        }
    }

    @Override
    public void onDestroy()
    {
        // TODO Auto-generated method stub
        super.onDestroy();
    }
}
