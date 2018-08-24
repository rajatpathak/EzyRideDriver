package com.appentus.ezyridedriver.activities.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.appentus.ezyridedriver.apiConnect.ApiClient;
import com.appentus.ezyridedriver.apiConnect.Model.ApiInterface;
import com.appentus.ezyridedriver.apiConnect.Model.VehicleDetailModel;
import com.appentus.ezyridedriver.Constant;
import com.appentus.ezyridedriver.R;
import com.appentus.ezyridedriver.RealPathUtil;
import com.appentus.ezyridedriver.SharedPrefData;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadDocument extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.btSubmit)
    TextView btSubmit;
    @BindView(R.id.llDlImages)
    LinearLayout llDlImages;
    @BindView(R.id.llViImages)
    LinearLayout llViImages;
    @BindView(R.id.llPermitImages)
    LinearLayout llPermitImages;
    @BindView(R.id.llRcImages)
    LinearLayout llRcImages;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.imDlFront)
    ImageView imDlFront;

    @BindView(R.id.imDlBack)
    ImageView imDlBack;

    @BindView(R.id.imViFront)
    ImageView imViFront;

    @BindView(R.id.imViBack)
    ImageView imViBack;

    @BindView(R.id.imPermitFront)
    ImageView imPermitFront;

    @BindView(R.id.imPermitBack)
    ImageView imPermitBack;

    @BindView(R.id.imRcFront)
    ImageView imRcFront;

    @BindView(R.id.imRcBack)
    ImageView imRcBack;

    private int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;


    private ApiInterface apiService;
    private String filePath = "", driverId = "";
    private File file;
    private List<File> fileList = new ArrayList<>();
    HashMap<String, File> myMap = new HashMap<String, File>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_document);
        ButterKnife.bind(this);
        title.setText("Upload Documents");
        checkAndRequestPermissions();
        apiService = ApiClient.getClient().create(ApiInterface.class);

        driverId = SharedPrefData.GetStringPref(this, "driverId", "");

//        setClickableFalse();
        Log.e("uploadDlDriverId", driverId);
    }

    public void setClickableFalse() {
        btSubmit.setClickable(false);
    }


    @OnClick({R.id.llDlPannel, R.id.llViPannel, R.id.llPermitPannel, R.id.llRcPannel, R.id.btSubmit, R.id.ivBack})
    public void onButtonClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btSubmit:
                Log.e("MapDL", String.valueOf(myMap.get("DL")));
                Log.e("MapVef", String.valueOf(myMap.get("Vehicle")));
                callApi();
                break;
            case R.id.llDlPannel:
                llDlImages.setVisibility(View.VISIBLE);
                break;
            case R.id.llViPannel:
                visiblityView(llViImages,llPermitImages,llRcImages,llDlImages);
                break;
            case R.id.llPermitPannel:
                visiblityView(llPermitImages,llViImages,llRcImages,llDlImages);
                break;
            case R.id.llRcPannel:
                visiblityView(llRcImages,llPermitImages,llViImages,llDlImages);
                break;
//            case R.id.btVRegistration:
//                openGallary(4);
//                break;
            case R.id.ivBack:
                onBackPressed();
                break;

        }

    }
 @OnClick({R.id.imDlFront, R.id.imDlBack, R.id.imViFront, R.id.imViBack, R.id.imPermitFront, R.id.imPermitBack,R.id.imRcFront,R.id.imRcBack,})
    public void onImageClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.imDlFront:
                openGallary(1);
                break;
            case R.id.imDlBack:
                openGallary(2);
                break;
            case R.id.imViFront:
                openGallary(3);
                break;
            case R.id.imViBack:
                openGallary(4);
                break;
            case R.id.imPermitFront:
                openGallary(5);
                break;
            case R.id.imPermitBack:
                openGallary(6);
                break;
            case R.id.imRcFront:
                openGallary(7);
                break;
            case R.id.imRcBack:
                openGallary(8);
                break;
        }

    }

    private void openGallary(int requestCode) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (resultCode == RESULT_OK && reqCode == 1) {
            final Uri imageUri = data.getData();
            imDlFront.setImageURI(imageUri);
            getImageFile(imageUri, "DlFron");
            Log.e("Request Code", reqCode + " ");
        } else if (resultCode == RESULT_OK && reqCode == 2) {
            final Uri imageUri = data.getData();
            imDlBack.setImageURI(imageUri);
            getImageFile(imageUri, "DlBack");
            Log.e("Request Code", reqCode + " ");

        } else if (resultCode == RESULT_OK && reqCode == 3) {
            final Uri imageUri = data.getData();
            imViFront.setImageURI(imageUri);
            getImageFile(imageUri, "InsuranceFront");
            Log.e("Request Code", reqCode + " ");
        } else if (resultCode == RESULT_OK && reqCode == 4) {
            final Uri imageUri = data.getData();
            imViBack.setImageURI(imageUri);
            getImageFile(imageUri, "InsuranceBack");
            Log.e("Request Code", reqCode + " ");

        } else if (resultCode == RESULT_OK && reqCode == 5) {
            final Uri imageUri = data.getData();
            imPermitFront.setImageURI(imageUri);
            getImageFile(imageUri, "PermitFront");
            Log.e("Request Code", reqCode + " ");

        } else if (resultCode == RESULT_OK && reqCode == 6) {
            final Uri imageUri = data.getData();
            imPermitBack.setImageURI(imageUri);
            getImageFile(imageUri, "PermitBack");
            Log.e("Request Code", reqCode + " ");

        } else if (resultCode == RESULT_OK && reqCode == 7) {
            final Uri imageUri = data.getData();
            imRcFront.setImageURI(imageUri);
            getImageFile(imageUri, "RcFront");
            Log.e("Request Code", reqCode + " ");

        } else if (resultCode == RESULT_OK && reqCode == 8) {
            final Uri imageUri = data.getData();
            imRcBack.setImageURI(imageUri);
            getImageFile(imageUri, "RcBack");
            Log.e("Request Code", reqCode + " ");
            btSubmit.setClickable(true);
        } else {
            Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }

    public void getImageFile(Uri imageUri, String key) {
        filePath = RealPathUtil.getRealPathFromURI_API19(this, imageUri); // to get real path from selected image
//        Log.e("uploadDocPath",filePath);

        file = new File(filePath); //convert into file using filePath
        Log.e("uploadDocFile", file + "");


        fileList.add(file);
//        Log.e("uploadDocFileSze",fileList.size()+"");
        addInHashmap(key, file);
    }

    public void addInHashmap(String key, File file) {
        myMap.put(key, file);
        Log.e("addKey", key);
    }

    public void visiblityView(View visible,View invisible1,View invisible2,View invisible3){
        visible.setVisibility(View.VISIBLE);
        invisible1.setVisibility(View.GONE);
        invisible2.setVisibility(View.GONE);
        invisible3.setVisibility(View.GONE);
    }

    private void callApi() {
        progressDialog.show();
        MultipartBody.Part[] vehicle_doc = new MultipartBody.Part[myMap.size()]; //Multipart Array

        //loop to get file from MAP
        int i = 0;
        for (Map.Entry<String, File> entry : myMap.entrySet()) {

            String key = entry.getKey();
            Log.e("retrieve1", key + " -->" + myMap.get(key));

            file = new File(myMap.get(key).getAbsolutePath()); //convert into file using filePath

            RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
            vehicle_doc[i] = MultipartBody.Part.createFormData("vehicle_doc[]", file.getName(), reqFile);

            Log.e("Docs " + i, " " + file.getPath() + " *** " + file.getName());

            i++;
        }


        //Multipart other parameters
        RequestBody vehicleId = RequestBody.create(MediaType.parse("text/plain"), "1");
        RequestBody driver_id = RequestBody.create(MediaType.parse("text/plain"), driverId);


        //call api
        Call<VehicleDetailModel> call = apiService.uploadDriverDoc(driver_id, vehicleId, vehicle_doc);

        call.enqueue(new Callback<VehicleDetailModel>() {
            @Override
            public void onResponse(Call<VehicleDetailModel> call, Response<VehicleDetailModel> response) {

                if (response.isSuccessful()) {
                    VehicleDetailModel vehicleModel = response.body();

                    if (vehicleModel.getStatus().equals(Constant.API_STATUS)) {

                        Log.e("Vehicle", " " + vehicleModel.getStatus() + " " + vehicleModel.getMessage());
                        progressDialog.dismiss();
                        startActivity(new Intent(UploadDocument.this, UploadDL.class));
                    } else {
                        progressDialog.dismiss();
                        Log.e("Vehicle", " Not success");
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


    private boolean checkAndRequestPermissions() {
        int READ_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int WRITE_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        List<String> listPermissionsNeeded = new ArrayList<>();

        if (READ_EXTERNAL_STORAGE != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if (WRITE_EXTERNAL_STORAGE != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
