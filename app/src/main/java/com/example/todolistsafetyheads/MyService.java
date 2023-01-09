package com.example.todolistsafetyheads;

import static com.example.todolistsafetyheads.MainActivity.arrayItemList;
import static com.example.todolistsafetyheads.MainActivity.writeData;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("SERVICE", String.valueOf(1));
        if (writeData){
            FileHelper.writeData(arrayItemList, getApplicationContext());
            Log.d("SERVICE", String.valueOf(2));
        }
        else {
            arrayItemList = FileHelper.readData(this);
            Log.d("SERVICE", String.valueOf(arrayItemList.size()));
        }

        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}