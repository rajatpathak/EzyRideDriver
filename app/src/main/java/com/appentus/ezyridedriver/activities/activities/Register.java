package com.appentus.ezyridedriver.activities.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appentus.ezyridedriver.apiConnect.ApiClient;
import com.appentus.ezyridedriver.apiConnect.Model.ApiInterface;
import com.appentus.ezyridedriver.apiConnect.Model.LoginModel;
import com.appentus.ezyridedriver.Constant;
import com.appentus.ezyridedriver.R;
import com.appentus.ezyridedriver.SharedPrefData;
import com.hbb20.CountryCodePicker;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.ccp2)
    CountryCodePicker ccp;


    @BindView(R.id.etFname)
    EditText etFname;
    @BindView(R.id.etLname)
    EditText etLname;
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etMobileNo)
    EditText etMobileNo;
    @BindView(R.id.etState)
    EditText etState;
    @BindView(R.id.etCity)
    EditText etCity;
    @BindView(R.id.etZipcode)
    EditText etZipcode;
    @BindView(R.id.etPassword)
    EditText etPassword;


    ApiInterface apiService;
    public static final String TAG = "ActivityRegister";
    private String strFName = "", strLName = "", strEmail = "", strDefaultCountry = "", strDefaultCountryCode = "", strSelectedCountry = "", strSelectedCountryCode = "", strState = "", strCity = "", strZipcode = "", strMobileNo = "", strPassword = "", str = "", fName = "", strDriveLat = "", strDriverLong = "", strOtp = "";
    private Dialog dialog;
    private String driverId="";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
        title.setText("Register");
        strDefaultCountryCode = ccp.getDefaultCountryCode();
        strDefaultCountry = ccp.getDefaultCountryName();
        Log.e(TAG, strDefaultCountry + " " + strDefaultCountryCode);
        apiService = ApiClient.getClient().create(ApiInterface.class);

    }


    @OnClick({R.id.btSubmit, R.id.ivBack, R.id.title})
    public void onButtonClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btSubmit:

                registrationApi();
//
                break;
            case R.id.ivBack:
                onBackPressed();
                break;

        }

    }

    public void registrationApi() {
        strFName = etFname.getText().toString();
        strLName = etLname.getText().toString();
        strMobileNo = etMobileNo.getText().toString();
        strEmail = etEmail.getText().toString();
        strState = etState.getText().toString();
        strCity = etCity.getText().toString();
        strZipcode = etZipcode.getText().toString();
        strPassword = etPassword.getText().toString();
        strDriveLat = "00.00";
        strDriverLong = "00.00";
        strSelectedCountry = ccp.getSelectedCountryName();
        strSelectedCountryCode = ccp.getSelectedCountryCode();

        if (!strSelectedCountry.equals("")) {
            strDefaultCountryCode = strSelectedCountryCode;
            strDefaultCountry = strSelectedCountry;
        }
        Log.e(TAG + "2", strDefaultCountry + " " + strDefaultCountryCode + "ino" + strSelectedCountry);


        if (strFName.equals("")){
            etFname.setError("can't empty");
        }
        else if (strLName.equals("")){
            etLname.setError("can't empty");
        }

        else if (strMobileNo.equals("")){
            etMobileNo.setError("can't empty");
        }

        else if (strPassword.equals("")){
            etPassword.setError("can't empty");
        }

        else {


            progressDialog.show();
            Call<LoginModel> call = apiService.registerDriver(Constant.LOGIN_API_TOKEN, Constant.DEVICE_TOKEN, Constant.DEVICE_TYPE, strFName, strLName, strMobileNo, strEmail, strDefaultCountryCode, strDefaultCountry, strState, strCity, strZipcode, strPassword, strDriveLat, strDriverLong);
            call.enqueue(new Callback<LoginModel>() {
                @Override
                public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                    if (response.isSuccessful()) {
                        LoginModel loginModel = response.body();

                        if (loginModel.getStatus().equals(Constant.API_STATUS)) {
                            Log.e("loginModel", loginModel.getStatus() + " " + loginModel.getResult().getDriverFirstName() + " " + loginModel.getResult().getDriverEmail());
                            strOtp = loginModel.getOtpTemp().toString();
                            driverId = loginModel.getResult().getDriverId().toString();

                            SharedPrefData.SetStringPref(Register.this,"driverId",driverId);
                            progressDialog.dismiss();
                            otpDialog(strOtp, driverId);
                        } else {
                            progressDialog.dismiss();
                            Log.e("Register", " Not success");
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


    public void otpDialog(final String otp, final String driverId) {

        dialog = new Dialog(Register.this, android.R.style.Theme_Light);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_mobile_verification);
        dialog.setTitle("Enter OTP");


        dialog.setCancelable(false);
        TextView dialogButton = (TextView) dialog.findViewById(R.id.btSubmit);
        TextView tvOtp = (TextView) dialog.findViewById(R.id.tvOtp);
        final EditText et1 = (EditText) dialog.findViewById(R.id.etOtp);




        tvOtp.setText("Enter OTP " + otp);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dialogOtp = et1.getText().toString();

                if (dialogOtp.length()==4){
                otpVerification(dialogOtp, driverId);
                Log.e("OTP", "" + otp + "Get" + dialogOtp);}
                else{
                    et1.setError("enter 4 digit otp");
                }
            }
        });
        dialog.show();
    }



    //    below funcation is otp verification api call
    private void otpVerification(String dialogOtp, final String driverId) {
        progressDialog.show();
        Log.e("ddddddd", dialogOtp+" "+driverId);
        Call<LoginModel> call = apiService.otpVerificaiton(dialogOtp, driverId);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                if (response.isSuccessful()) {
                    LoginModel loginModel = response.body();
                    Log.e("loginModelOTP", loginModel.getStatus());
                    if (loginModel.getStatus().equals("success")) {
                        dialog.dismiss();
                        progressDialog.dismiss();
                        startActivity(new Intent(Register.this, ActivityBankDetails.class).putExtra("driverId", driverId));
                    }
                    else {
                        progressDialog.dismiss();
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


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
