package com.appentus.ezyridedriver.activities.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

public class BaseActivity extends AppCompatActivity{
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    public ProgressDialog progressDialog;
    public BaseActivity() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please wait");
    }


    @Override
    public void onStop() {
        super.onStop();
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.hide();
            }
            if (isFinishing()) {
                progressDialog.dismiss();
            }
            //AppUtility.hideKeyboard(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
