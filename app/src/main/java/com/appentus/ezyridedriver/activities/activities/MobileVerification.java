package com.appentus.ezyridedriver.activities.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.appentus.ezyridedriver.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MobileVerification extends AppCompatActivity {
    @BindView(R.id.title)
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_verification);
        ButterKnife.bind(this);
        title.setText("OTP Verification");
    }

    @OnClick({R.id.btSubmit,R.id.ivBack,R.id.title})
    public void onButtonClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btSubmit:
                startActivity(new Intent(MobileVerification.this, SelectVehicleType.class));
                break;
            case R.id.ivBack:
                onBackPressed();
                break;

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
