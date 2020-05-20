package com.example.broadcastproj;

import androidx.appcompat.app.AppCompatActivity;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class BackgroundService extends IntentService {

    public BackgroundService() {
        super("BackgroundService");
    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, BackgroundService.class);
        context.startService(intent);
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            for (int i = 0; i < 5; i++){
                Intent localBroadcastIntent = new Intent(MainActivity.BROADCAST_ACTION);
                localBroadcastIntent.putExtra("value", "Broadcast "+ (i + 1));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sendBroadcast(localBroadcastIntent);
            }
        }
    }
}

