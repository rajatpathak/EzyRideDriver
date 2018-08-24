package com.appentus.ezyridedriver.activities.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.appentus.ezyridedriver.Constant;
import com.appentus.ezyridedriver.R;
import com.appentus.ezyridedriver.SharedPrefData;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private String newToken ="",fireBaseToken="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseToken();
        String fireBaseToken=  SharedPrefData.GetStringPref(MainActivity.this, Constant.PREFRENCE_FirebaseToken,"");
        Log.e("firebaseIdEEE",""+fireBaseToken);
        // bind the view using butterknife
        ButterKnife.bind(this);
    }
    public void firebaseToken(){

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener( MainActivity.this,  new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String newToken = instanceIdResult.getToken();
                Log.e("newToken",instanceIdResult.getToken());
                SharedPrefData.SetStringPref(MainActivity.this, Constant.PREFRENCE_FirebaseToken,instanceIdResult.getToken());
               }
        });
    }
    @OnClick({R.id.btLogin, R.id.btRegister})
    public void onButtonClick(View view) {
        int id=view.getId();
        switch (id){
            case R.id.btLogin:
                startActivity(new Intent(MainActivity.this,Login.class));
                break;
            case R.id.btRegister:
                startActivity(new Intent(MainActivity.this,Register.class));
                break;
        }

    }
}
