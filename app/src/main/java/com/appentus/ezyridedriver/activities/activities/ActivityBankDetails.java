package com.appentus.ezyridedriver.activities.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.appentus.ezyridedriver.apiConnect.ApiClient;
import com.appentus.ezyridedriver.apiConnect.Model.ApiInterface;
import com.appentus.ezyridedriver.apiConnect.Model.LoginModel;
import com.appentus.ezyridedriver.Constant;
import com.appentus.ezyridedriver.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityBankDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @BindView(R.id.spBank)
    Spinner spBank;
    @BindView(R.id.etACname)
    EditText etACname;
    @BindView(R.id.etACno)
    EditText etACno;
    @BindView(R.id.etIFCcode)
    EditText etIFCcode;
    @BindView(R.id.title)
    TextView title;
    ApiInterface apiService;
    String driverId = "";
    String currency = "IN";
    private String strAcName = "", strAcNo = "", strBank = "", strBankCode = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_details);
        ButterKnife.bind(this);
        apiService = ApiClient.getClient().create(ApiInterface.class);
        driverId = getIntent().getExtras().getString("driverId", "");
        Log.e("BankDetialsDriverId", driverId);

        title.setText("Bank Detail");
        List<String> bankList = new ArrayList<String>();
        bankList.add("SBI Bank");
        bankList.add("HDFC Bank");
        bankList.add("ICICI Bank");
        bankList.add("PNB Bank");
        bankList.add("IDBI Bank");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, bankList);
        spBank.setAdapter(dataAdapter);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
// Showing selected spinner item
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

        strBank=item;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        strBank="SBI Bank";
    }




    @OnClick({R.id.btSubmit})
    public void onButtonClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btSubmit:
                bankDetials();
                break;

        }

    }


    public void bankDetials() {

        strAcName=etACname.getText().toString();
        strAcNo=etACno.getText().toString();
        strBankCode=etIFCcode.getText().toString();



        Call<LoginModel> call = apiService.bankDetails(Constant.LOGIN_API_TOKEN, driverId, currency, strAcName, strAcNo, strBank, strBankCode);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                if (response.isSuccessful()) {
                    LoginModel loginModel = response.body();

                    if (loginModel.getStatus().equals(Constant.API_STATUS)) {
                        Log.e("loginModel", loginModel.getStatus() + " " + loginModel.getResult().getDriverAcName() + " " + loginModel.getResult().getDriverAcNumber());

                        startActivity(new Intent(ActivityBankDetails.this, SelectVehicleType.class));
                    }
                    else {
                        Log.e("Register"," Not success");
                    }
                } else {

                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                // Log error here since request failed

                Toast.makeText(getApplicationContext(), "Network issue", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
