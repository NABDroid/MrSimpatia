package com.ernestyes.mrsimpatia.fiverr;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.ernestyes.mrsimpatia.fiverr.activities.PlayerHomeActivity;

public class NotificationHelper extends ContextWrapper {
    public static final String ch1id = "channel1ID";
    public static final String ch1Name = "Channel1 Name";


    private NotificationManager mManager;
    Context context;

    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
        context = base;
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel1 = new NotificationChannel(ch1id, ch1Name, NotificationManager.IMPORTANCE_HIGH);
        channel1.enableLights(true);
        channel1.enableVibration(true);
        getManager().createNotificationChannel(channel1);

    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return mManager;
    }

    public NotificationCompat.Builder getChannel1Notification() {
        Uri ringToneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        Intent clickOnNotificationIntent = new Intent(this, PlayerHomeActivity.class);
        PendingIntent onClickpendingIntent = PendingIntent.getActivity(this, 1, clickOnNotificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        return new NotificationCompat.Builder(getApplicationContext(), ch1id)
                .setContentTitle("MrSimpatia")
                .setContentText("Draw a tattoo for 1000 points")
                .setSmallIcon(R.drawable.machine_5)
                .setAutoCancel(true)
                .setContentIntent(onClickpendingIntent)
                .setSound(ringToneUri);
    }

}
