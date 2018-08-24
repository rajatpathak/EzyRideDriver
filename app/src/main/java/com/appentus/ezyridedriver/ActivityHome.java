package com.appentus.ezyridedriver;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.appentus.ezyridedriver.apiConnect.ApiClient;
import com.appentus.ezyridedriver.apiConnect.Model.ApiInterface;
import com.appentus.ezyridedriver.apiConnect.Model.LoginModel;
import com.appentus.ezyridedriver.activities.activities.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityHome extends BaseActivity{
    private static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    //This is our tablayout
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;

    @BindView(R.id.simpleSwitch)
    Switch onOffSwitch;

    @BindView(R.id.switchText)
    TextView switchText;
    private ApiInterface apiService;

    private String strDriverId="",strDutyStatus="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        checkAndRequestPermissions();

        ButterKnife.bind(this);

        switchAction();
        apiService = ApiClient.getClient().create(ApiInterface.class);
        strDriverId= SharedPrefData.GetStringPref(this,"driverId","");
        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager=(ViewPager)findViewById(R.id.pager);
        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("HOME").setIcon(R.drawable.ic_action_home));
        tabLayout.addTab(tabLayout.newTab().setText("EARNINGS").setIcon(R.drawable.ic_ern));
        tabLayout.addTab(tabLayout.newTab().setText("RATINGS").setIcon(R.drawable.ic_rating));
        tabLayout.addTab(tabLayout.newTab().setText("ACCOUNT").setIcon(R.drawable.ic_account));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final MyAdapter adapter = new MyAdapter(this,getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    //switch button for online & offline duty
    private void switchAction() {

        onOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switchText.setText("Online");

                    Drawable img= getApplicationContext().getResources().getDrawable(R.drawable.switch_thum);
                    onOffSwitch.setThumbDrawable(img);

                    strDutyStatus="0";
                    driverDutyStatus(strDriverId,strDutyStatus);

                }
                else{
                    switchText.setText("Offline");
                    Drawable img = getApplicationContext().getResources().getDrawable( R.drawable.switch_thum_off);
                    onOffSwitch.setThumbDrawable(img);

                    strDutyStatus="1";
                    driverDutyStatus(strDriverId,strDutyStatus);

                }

            }
        });
    }

    private boolean checkAndRequestPermissions() {
        int ACCESS_NETWORK_STATE = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE);
        int ACCESS_FINE_LOCATION = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int ACCESS_COARSE_LOCATION = ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION);
        int INTERNET = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);

        List<String> listPermissionsNeeded = new ArrayList<>();

        if (ACCESS_NETWORK_STATE != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_NETWORK_STATE);
        }
        if (INTERNET != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.INTERNET);
        }
        if (ACCESS_FINE_LOCATION != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ACCESS_COARSE_LOCATION != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }


    public void driverDutyStatus(String strDriverId,String strDutyStatus){

//        prepare data


        progressDialog.show();


        //call api
        Call<LoginModel> call = apiService.live_driver_status(strDriverId,strDutyStatus);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                if (response.isSuccessful()) {
                    LoginModel vehicleModel = response.body();

                    if (vehicleModel .getStatus().equals(Constant.API_STATUS)) {
                        Log.e("Vehicle"," "+vehicleModel.getStatus()+" "+vehicleModel.getMessage());
                        progressDialog.dismiss();
                    }
                    else {
                        progressDialog.dismiss();
                        Log.e("Vehicle"," Not success");

                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                // Log error here since request failed
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Network issue", Toast.LENGTH_SHORT).show();
            }
        });


    }


}