package com.red_folder.phonegap.plugin.backgroundservice.sample;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Telephony;
import android.telephony.SmsManager;
import java.util.ArrayList;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
public class SmsSentReceiver extends BroadcastReceiver {
@Override
public void onReceive(Context context, Intent arg1) {
    switch (getResultCode()) {
    case Activity.RESULT_OK:
        

        break;
    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
        
        break;
    case SmsManager.RESULT_ERROR_NO_SERVICE:
        
        break;
    case SmsManager.RESULT_ERROR_NULL_PDU:
        
        break;
    case SmsManager.RESULT_ERROR_RADIO_OFF:
        
        break;
    }
}
}