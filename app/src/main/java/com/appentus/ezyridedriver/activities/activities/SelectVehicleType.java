package com.appentus.ezyridedriver.activities.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.appentus.ezyridedriver.apiConnect.ApiClient;
import com.appentus.ezyridedriver.apiConnect.Model.ApiInterface;
import com.appentus.ezyridedriver.apiConnect.Model.VehicleModel;
import com.appentus.ezyridedriver.Constant;
import com.appentus.ezyridedriver.R;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectVehicleType extends AppCompatActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.imageIcon)
    ImageView imageIcon;
    @BindView(R.id.imageIconRight)
    ImageView imageIconRight;
    @BindView(R.id.txtCabType)
    TextView txtCabType;
    @BindView(R.id.pbHeaderProgress)
    ProgressBar pbHeaderProgress;
    private ApiInterface apiService;

    String url, strVechileId,strVehicleType;
    String vehicleTypeCode="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_vehicle_type);

        ButterKnife.bind(this);
        title.setText("Select Vechile Type");

        apiService = ApiClient.getClient().create(ApiInterface.class);
        getVehicleType();




    }


    @OnClick({R.id.btSubmit,R.id.ivBack,R.id.cardRegularcab})
    public void onButtonClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btSubmit:
                startActivity(new Intent(SelectVehicleType.this, VehicleDetails.class));
                break;
            case R.id.ivBack:
                onBackPressed();
                break;
            case R.id.cardRegularcab:
                if (imageIconRight.getVisibility()==View.VISIBLE) {
                    imageIconRight.setVisibility(View.GONE);
                    vehicleTypeCode = "" + 1;
                }
                else if (imageIconRight.getVisibility()==View.GONE) {
                    imageIconRight.setVisibility(View.VISIBLE);
                    vehicleTypeCode = "" ;
                }
                break;

        }

    }



    public void getVehicleType(){
        pbHeaderProgress.setVisibility(View.VISIBLE);
        Call<VehicleModel> call = apiService.vehicleType();
        call.enqueue(new Callback<VehicleModel>() {
            @Override
            public void onResponse(Call<VehicleModel> call, Response<VehicleModel> response) {

                if (response.isSuccessful()) {
                    VehicleModel vehicleModel = response.body();

                    if (vehicleModel .getStatus().equals(Constant.API_STATUS)) {
                        Log.e("Vehicle"," "+vehicleModel.getStatus()+vehicleModel.getResult().toString());
                        url=response.body().getResult().get(0).getVehicle_type_icon();
                        strVechileId =response.body().getResult().get(0).getVehicle_type_id()+"";
                        strVehicleType=response.body().getResult().get(0).getVehicle_type()+"";
                        setUpData(url, strVechileId,strVehicleType);
                        pbHeaderProgress.setVisibility(View.GONE);
                    }
                    else {
                        Log.e("Vehicle"," Not success");
                        pbHeaderProgress.setVisibility(View.GONE);
                    }
                } else {
                    pbHeaderProgress.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<VehicleModel> call, Throwable t) {
                // Log error here since request failed
                pbHeaderProgress.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Network issue", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setUpData(String url, String strVechileId, String strVehicleType) {
//        set title
        txtCabType.setText(strVehicleType);
        //add image in image view
        Glide.with(this)
                .load(url)
                .into(imageIcon);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
