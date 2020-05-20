package com.example.broadcastproj;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String BROADCAST_ACTION = "com.example.broadcastproj.BROADCAST_MESSAGE";

    Button btnStart;
    public TextView txtViewMsg;
    private Receiver localListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtViewMsg = findViewById(R.id.txtViewMessage);
        btnStart = findViewById(R.id.btnStartBroadcast);
        btnStart.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        localListener = new Receiver();
        IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);
        this.registerReceiver(localListener,intentFilter);      // 2. Registering broadcast receiver
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.unregisterReceiver(localListener);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnStartBroadcast){
            BackgroundService.startAction(this.getApplicationContext());
        }
    }


    // 1. Create the broadcast receiver
    public class Receiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String currentText = txtViewMsg.getText().toString();
            String message = intent.getStringExtra("value");
            currentText += "\nReceived " + message;
            txtViewMsg.setText(currentText);
        }
    }
}

