package ru.twoida.basket_rush_client;


import android.content.Context;

public class GCMBroadcastReceiver extends
        com.google.android.gcm.GCMBroadcastReceiver {

    @Override
    protected String getGCMIntentServiceClassName(Context context) {

        return "ru.twoida.basket_rush_client.GCMIntentService";
    }
}