package com.appentus.ezyridedriver.activities.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.appentus.ezyridedriver.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgotPassword extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        ButterKnife.bind(this);
    }

    @OnClick({R.id.btReset})
    public void onButtonClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btReset:
                startActivity(new Intent(ForgotPassword.this, Login.class));
                break;
        }
    }
}
