package com.appentus.ezyridedriver.activities.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appentus.ezyridedriver.ActivityHome;
import com.appentus.ezyridedriver.apiConnect.ApiClient;
import com.appentus.ezyridedriver.apiConnect.Model.ApiInterface;
import com.appentus.ezyridedriver.apiConnect.Model.LoginModel;
import com.appentus.ezyridedriver.Constant;
import com.appentus.ezyridedriver.R;
import com.appentus.ezyridedriver.SharedPrefData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends BaseActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.etMobileNo)
    EditText etMobileNo;
    @BindView(R.id.etPassword)
    EditText etPassword;
    ApiInterface apiService;
    String strMobileNo="",strPassword="",deviceToken="";
    private GPSTracker gpsTracker;
    private String fireBaseToken="";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // bind the view using butterknife
        ButterKnife.bind(this);
        title.setText("Login");
        gpsTracker=new GPSTracker(Login.this);
        apiService=ApiClient.getClient().create(ApiInterface.class);
        fireBaseToken=SharedPrefData.GetStringPref(Login.this,Constant.PREFRENCE_FirebaseToken,"");
        Log.e("deviceToken",""+fireBaseToken);


    }



    @OnClick({R.id.btForgotPswd, R.id.ivBack,R.id.btLogin})
    public void onButtonClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btLogin:

                loginValidation();
                break;
            case R.id.btForgotPswd:
                startActivity(new Intent(Login.this, ForgotPassword.class));
                break;
            case R.id.ivBack:
                onBackPressed();
                break;

        }

    }





    //login field validation
    public void loginValidation(){

        progressDialog.show();
        strMobileNo=etMobileNo.getText().toString();
        strPassword=etPassword.getText().toString();
        if (strMobileNo.equals("")){
            progressDialog.dismiss();
            etMobileNo.setError("can't empty");
        }
        else if (strPassword.equals("")){
            progressDialog.dismiss();
            etPassword.setError("can't empty");
        }
        else {
            loginApi(Constant.LOGIN_API_TOKEN,fireBaseToken,Constant.DEVICE_TYPE,strMobileNo,strPassword,gpsTracker.getLatitude()+"",gpsTracker.getLongitude()+"");
        }
    }

    //this below fun call api and get driver data//
    public void loginApi(final String apiToken,final String device_token,final String deviceType, final String strMobileNo, final String strPassword,final String driverLat,final String driverLong){

        Call<LoginModel> call = apiService.login(apiToken,device_token,deviceType,strMobileNo,strPassword,driverLat,driverLong);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel>call, Response<LoginModel> response) {

                if (response.isSuccessful()) {
                    LoginModel loginModel = response.body();
                    if (response.body().getStatus().equals(Constant.API_STATUS)) {
                        Log.e("loginModel", response.body().getStatus()+"");
                        SharedPrefData.SetStringPref(Login.this, Constant.LOGIN_SESSION, "1");
                        SharedPrefData.SetStringPref(Login.this, Constant.LOGIN_MOBILE, strMobileNo);
                        SharedPrefData.SetStringPref(Login.this, Constant.LOGIN_PASSWORD, strPassword);
                        SharedPrefData.SetStringPref(Login.this, Constant.Driver_Id, response.body().getResult().getDriverId());

                        progressDialog.dismiss();

                        startActivity(new Intent(Login.this, ActivityHome.class));
                    }
                    else {
                        progressDialog.dismiss();
                        Toast.makeText(Login.this,"Invalid credential or not register",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginModel>call, Throwable t) {
                // Log error here since request failed
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Network issue",Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
