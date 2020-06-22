package com.ernestyes.mrsimpatia.fiverr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;

import androidx.core.app.NotificationCompat;

public class AlertReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(10000);

        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder notificationBuilder = notificationHelper.getChannel1Notification();
        notificationHelper.getManager().notify(1, notificationBuilder.build());

    }
}
