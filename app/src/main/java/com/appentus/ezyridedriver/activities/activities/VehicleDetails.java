package com.appentus.ezyridedriver.activities.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.appentus.ezyridedriver.apiConnect.ApiClient;
import com.appentus.ezyridedriver.apiConnect.Model.ApiInterface;
import com.appentus.ezyridedriver.apiConnect.Model.VehicleDetailModel;
import com.appentus.ezyridedriver.Constant;
import com.appentus.ezyridedriver.R;
import com.appentus.ezyridedriver.SharedPrefData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehicleDetails extends AppCompatActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.etBrand)
    EditText etBrand;
    @BindView(R.id.etModel)
    EditText etModel;
    @BindView(R.id.spYear)
    Spinner spYear;

    @BindView(R.id.spColor)
    Spinner spColor;

    @BindView(R.id.etInterior)
    EditText etInterior;
    private ApiInterface apiService;


    String strDriverId="",strBrand="",strModel="",strYear="",strColor="",strInterior="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_details);

       ButterKnife.bind(this);
        setSpYear();
        setSpColor();

        strDriverId=SharedPrefData.GetStringPref(VehicleDetails.this,"driverId","");


        Log.e("shared..",strDriverId);
        title.setText("Add Vechile Details");
        apiService = ApiClient.getClient().create(ApiInterface.class);
    }

    @OnClick({R.id.btSubmit,R.id.ivBack})
    public void onButtonClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btSubmit:
                vehicleDetailApi();

                break;
            case R.id.ivBack:
                onBackPressed();
                break;

        }

    }


    public void setSpYear(){
        List<String> yearList = new ArrayList<String>();
        for (int i=2000;i<=2018;i++) {
            yearList.add(""+i);
        }
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, yearList);
        spYear.setAdapter(dataAdapter);
        spYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strYear= parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                strYear="2000";
            }
        });
    }
    public void setSpColor(){
        List<String> colorList = new ArrayList<String>();
        colorList.add("Red");
        colorList.add("Black");
        colorList.add("White");
        colorList.add("Blue");
        colorList.add("Gray");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, colorList);
        spColor.setAdapter(dataAdapter);

        spColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strColor= parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                strColor="Red";
            }
        });
    }

    public void vehicleDetailApi(){


        strBrand=etBrand.getText().toString();
        strModel=etModel.getText().toString();
        strYear="";
        strColor="";
        strInterior=etInterior.getText().toString();


        Call<VehicleDetailModel> call = apiService.vehicleDetails("1",strDriverId,strBrand,strModel,strYear,strColor,strInterior);
        call.enqueue(new Callback<VehicleDetailModel>() {
            @Override
            public void onResponse(Call<VehicleDetailModel> call, Response<VehicleDetailModel> response) {

                if (response.isSuccessful()) {
                    VehicleDetailModel vehicleModel = response.body();

                    if (vehicleModel .getStatus().equals(Constant.API_STATUS)) {
                        Log.e("Vehicle"," "+vehicleModel.getStatus()+" "+vehicleModel.getResult().getVehicle_brand());
                        startActivity(new Intent(VehicleDetails.this, UploadDocument.class));
                    }
                    else {
                        Log.e("Vehicle"," Not success");

                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<VehicleDetailModel> call, Throwable t) {
                // Log error here since request failed
                Toast.makeText(getApplicationContext(), "Network issue", Toast.LENGTH_SHORT).show();
            }
        });


    }







    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
