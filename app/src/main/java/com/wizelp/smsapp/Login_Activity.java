package com.wizelp.smsapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class Login_Activity extends AppCompatActivity {
    private CountryCodePicker country_code_picker;

    private TextInputEditText editTextPhoneNumber;
    private Button btn_register;
    SharedPreferences shr;
    String prefix;
    String number;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences settings = Login_Activity.this.getSharedPreferences("njkws", 0);
        country_code_picker = findViewById(R.id.country_code_picker);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        btn_register = findViewById(R.id.btn_register);

        prefix = country_code_picker.getSelectedCountryCodeWithPlus();

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onClick(View view) {

                number = editTextPhoneNumber.getText().toString().trim();

                utils.setLoginCredentials(number,prefix,Login_Activity.this);
             //   utils.savenumber(prefix+number,Login_Activity.this);
                utils.setDeviceToken(Login_Activity.this,prefix+number);
                if (TextUtils.isEmpty(number)) {
                    Toast.makeText(getApplicationContext(), "Please enter the mobile number", Toast.LENGTH_SHORT).show();
                    return;

                } else if (number.length() > 10 || number.length() < 10) {
                    Toast.makeText(getApplicationContext(), "mobile must be of 10 digit ", Toast.LENGTH_SHORT).show();
                    return;
                } else if (number.equalsIgnoreCase("1234567890")) {
                    showDialog();
                    Intent intent = new Intent(Login_Activity.this, Verification_Activity.class);
                    startActivity(intent);
                } else {
                    showDialog();
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+91" + editTextPhoneNumber.getText().toString(),
                            60,
                            TimeUnit.SECONDS,
                            Login_Activity.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    hideDialog();
                                    Toast.makeText(Login_Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }


                                @Override
                                public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    super.onCodeSent(backendotp, forceResendingToken);
                                    hideDialog();
                                    Intent intent = new Intent(Login_Activity.this, Verification_Activity.class);
                                    intent.putExtra("mobile", number);
                                    intent.putExtra("prefix", prefix);
                                    intent.putExtra("OTP", backendotp);
                                    startActivity(intent);
                                }
                            }
                    );

                }
                // else
                //  Toast.makeText(getApplicationContext(), "Please check your Internet connection", Toast.LENGTH_SHORT).show();


            }

            //   }


        });

    }
    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideDialog();
    }
    private void hideDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}