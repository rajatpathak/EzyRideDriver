package com.appentus.ezyridedriver.activities.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appentus.ezyridedriver.ActivityHome;
import com.appentus.ezyridedriver.apiConnect.ApiClient;
import com.appentus.ezyridedriver.apiConnect.Model.ApiInterface;
import com.appentus.ezyridedriver.apiConnect.Model.VehicleDetailModel;
import com.appentus.ezyridedriver.Constant;
import com.appentus.ezyridedriver.R;
import com.appentus.ezyridedriver.RealPathUtil;
import com.appentus.ezyridedriver.SharedPrefData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadDL extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.imDlImage)
    ImageView imDlImage;
    @BindView(R.id.btSubmit)
    Button btSubmit;
    private ApiInterface apiService;
    private String filePath="",driverId="";
    private File file;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_dl);
        ButterKnife.bind(this);
        title.setText("Upload Driving Licence");
        btSubmit.setClickable(false);
        apiService = ApiClient.getClient().create(ApiInterface.class);

        driverId= SharedPrefData.GetStringPref(this,"driverId","");

        Log.e("uploadDlDriverId",driverId);

    }

    @OnClick({R.id.btSubmit,R.id.ivBack,R.id.imDlImage})
    public void onButtonClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btSubmit:
                uploadDL();
                break;
            case R.id.imDlImage:
                openGallary();
                break;
            case R.id.ivBack:
                onBackPressed();
                break;

        }

    }

    private void openGallary() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }
    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK)  {
            final Uri imageUri = data.getData();

            setImageView(imageUri);


            getImageFile(imageUri);

            btSubmit.setClickable(true);


        }
        else {
            Toast.makeText(UploadDL.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }

    public void setImageView(Uri imageUri){
        //set image in imageview
        InputStream imageStream = null;
        try {
            imageStream = getContentResolver().openInputStream(imageUri);

            if (imageStream!=null){

                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imDlImage.setImageBitmap(selectedImage);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    public void getImageFile(Uri imageUri){
        filePath= RealPathUtil.getRealPathFromURI_API19(this,imageUri); // to get real path from selected image
        Log.e("UploadDLimageFilePath",filePath);

        file = new File(filePath); //convert into file using filePath
        Log.e("UploadDLimageFile",file.getPath());


    };


    public void uploadDL(){

        progressDialog.show();
//        prepare data
        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("driver_licence", file.getName(), reqFile);
        RequestBody licence_number = RequestBody.create(MediaType.parse("text/plain"), "1");
        RequestBody valid_vehicle_type = RequestBody.create(MediaType.parse("text/plain"), "1");
        RequestBody issue_on = RequestBody.create(MediaType.parse("text/plain"), "1");
        RequestBody expiry_date = RequestBody.create(MediaType.parse("text/plain"), "1");
        RequestBody driver_id = RequestBody.create(MediaType.parse("text/plain"), driverId);



        //call api
        Call<VehicleDetailModel> call = apiService.uploadDL(driver_id,licence_number,valid_vehicle_type,issue_on,expiry_date,body);
        Log.e("UploadDLimageFilePath",licence_number+" "+valid_vehicle_type+" "+issue_on+" "+expiry_date+" "+body+"");
        call.enqueue(new Callback<VehicleDetailModel>() {
            @Override
            public void onResponse(Call<VehicleDetailModel> call, Response<VehicleDetailModel> response) {

                if (response.isSuccessful()) {
                    VehicleDetailModel vehicleModel = response.body();

                    if (vehicleModel .getStatus().equals(Constant.API_STATUS)) {
                        progressDialog.dismiss();
                        Log.e("Vehicle"," "+vehicleModel.getStatus()+" "+vehicleModel.getMessage());
                        startActivity(new Intent(UploadDL.this, ActivityHome.class));
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
            public void onFailure(Call<VehicleDetailModel> call, Throwable t) {
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
