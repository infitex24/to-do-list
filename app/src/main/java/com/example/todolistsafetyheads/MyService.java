package com.example.todolistsafetyheads;

import static com.example.todolistsafetyheads.MainActivity.writeData;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;


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

        ArrayList<Item> tempItemList = new ArrayList<>();

        Intent mIntent = new Intent(this, MyService.class);
        Bundle extras = mIntent.getBundleExtra("itemList");
        tempItemList = (ArrayList<Item>) extras.getSerializable("ARRAYLIST");

        if (writeData){
            SharedPreferencesHelper.writeListInPref(getApplicationContext(), tempItemList);
        }
        else {
            tempItemList = SharedPreferencesHelper.readListFromPref(this);
        }

        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}