package com.red_folder.phonegap.plugin.backgroundservice;

import java.text.SimpleDateFormat;
import java.util.Date;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.Notification;
import android.app.NotificationManager;
//import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.PowerManager;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.KeyEvent;
import java.util.ArrayList;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dhruv on 2/6/15.
 */
public class SettingsContentObserver extends ContentObserver {
    int previousVolume;
    Context context;
    int count1, count2;
    private static final String TAG = BackgroundService.class.getSimpleName();
    public SettingsContentObserver(Context c, Handler handler) {
        super(handler);
        context=c;

        AudioManager audio = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        previousVolume = audio.getStreamVolume(AudioManager.STREAM_RING);
    }

    @Override
    public boolean deliverSelfNotifications() {
        return super.deliverSelfNotifications();
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);

        AudioManager audio = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int currentVolume = audio.getStreamVolume(AudioManager.STREAM_RING);

        int delta=previousVolume-currentVolume;

        if(delta>0)
        {
            count1++;
            Log.i(TAG,"Ściszył!");
            previousVolume=currentVolume;
        }
        else if(delta<0)
        {
            count2++;
            Log.i(TAG, "Zrobił głośniej!");
            previousVolume=currentVolume;
        }

        if(count1==10||count2==10)
            startSequence();
    }

    public void startSequence()
    {
        Intent i = new Intent();
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.setClass(context, com.phonegap.helloworld.EmergencyInterface.class);
        context.startActivity(i);
    }
}