package com.appentus.ezyridedriver.apiConnect;


import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.appentus.ezyridedriver.Constant;
import com.appentus.ezyridedriver.SharedPrefData;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


public class FirebaseIDService extends FirebaseInstanceIdService {


    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e("token", refreshedToken);
        SharedPrefData.SetStringPref(FirebaseIDService.this, Constant.PREFRENCE_FirebaseToken,refreshedToken);
        Intent registrationComplete = new Intent(Constant.REGISTRATION_COMPLETE);
        registrationComplete.putExtra("token", refreshedToken);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }
}