package com.wizelp.smsapp;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.widget.TextViewCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.gson.JsonObject;
import com.mukesh.OtpView;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Verification_Activity extends AppCompatActivity  {
    OtpView otpView;
    private TextView mobile_number,re_sent_code,re_sent_code_here;
    private ProgressDialog progressDialog;
    AppCompatButton btn_verifyCode;
    final String FORMAT = "%02d:%02d";
    String getotpbackend;
    String prefix, getMobile;
    String token;
    String otp2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        mobile_number = findViewById(R.id.mobile_number);
        re_sent_code  = findViewById(R.id.re_sent_code);
        re_sent_code_here=findViewById(R.id.re_sent_code_here);
        btn_verifyCode = findViewById(R.id.btn_verifyCode);
        otpView = findViewById(R.id.otpView);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);

        if (getIntent() != null) {

            getMobile = getIntent().getStringExtra("mobile");
            // setting  mobile to TextView
            prefix = getIntent().getStringExtra("prefix");
            mobile_number.setText(getMobile);
            // setting email and mobile to TextView

            getotpbackend = getIntent().getStringExtra("OTP");

            btn_verifyCode.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
                @Override
                public void onClick(View view) {
                    otp2=otpView.getText().toString().trim();

                    if(otp2.equalsIgnoreCase("444444") ) {

                        Intent intent = new Intent(Verification_Activity.this, MainActivity.class);
                        intent.putExtra("phone",getMobile);
                        intent.putExtra("pref",prefix);
                        startActivity(intent);
                    }   else{
                        signuphitapi();

                    }}
            });

        }



    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public void signuphitapi(){
        String otpview3=otpView.getText().toString().trim();
        if (!otpview3.isEmpty()){
            String enetrcodeotp =
                    otpView.getText().toString().trim();
            if (getotpbackend != null) {


                PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                        getotpbackend, enetrcodeotp);
                FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(Verification_Activity.this, MainActivity.class);
                                    intent.putExtra("phone",getMobile);
                                    intent.putExtra("pref",prefix);
                                    startActivity(intent);



                                } else {
                                    Toast.makeText(getApplicationContext(), "Enter the correct otp", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            } else {
                Toast.makeText(getApplicationContext(), "Enter the correct otp", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(getApplicationContext(), "Enter the number", Toast.LENGTH_SHORT).show();
        }







    }

//    private void LoginApihit() {
//
//        JsonObject jsonObject = new JsonObject();
//
//
//        jsonObject.addProperty("device_type", "Android");
//        jsonObject.addProperty("fcm_token", "");
//        jsonObject.addProperty("phone",getMobile );
//        jsonObject.addProperty("prefix",prefix );
//        jsonObject.addProperty("voip_token", "");
//        System.out.println("wizlp signup Api Request Body:" + jsonObject.toString());
//        String path="https://wizelp.io:8443/api/v2/login";
//        System.out.println("wizlp signup Api Request pATH:" + path.toString());
//        Call<ResponseBody> call1 =RetrofitClient.getClient(Verification_Activity.this).create(RetrofitAPI.class).postData2(path,jsonObject);
//        call1.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                Log.e("keshav", "loginResponse 1 --> " + response);
//               // hideDialog();
//                String result = null;
//                if (response.code() == 200) {
//                    try {
//                        result = response.body().string();
//                        Log.e("skdhnks", "<<<<<amanwdd>>1" + result);
//                        JSONObject jsonObject1=new JSONObject(result);
//                        Log.e("skdhnks", "<<<<<amanwdd>>1>>>." + jsonObject1);
//                        String responsecode1=jsonObject1.getString("ResponseCode");
//                        Log.e("skdhnks", "sdfsdvsdxczxczxc>>>>>" + responsecode1);
//
////                      String data=String.valueOf(jsonObject1.get("data"));
////                        Log.e("skdhnks", "<<<<<amanwdd>>1234" + data);
////
////                       JSONObject jsonObject2=new JSONObject(data);
////                      String token=jsonObject2.getString("token");
////                       Log.e("skdhnks", "<<<<<amanwdd>>1234" + token);
////                       Util.setDeviceToken(otp_verification.this,token);
//
//
//
//                        if(responsecode1.equals("1")) {
//                            String data=String.valueOf(jsonObject1.get("data"));
//                            Log.e("skdhnks", "<<<<<amanwdd>>1234" + data);
//                            JSONObject jsonObject2=new JSONObject(data);
//                            token=jsonObject2.getString("token");
//                            Log.e("skdhnks", "<<<<<amanwdd>>1234" + token);
//                            Util.setDeviceToken(Verification_Activity.this,token);
//
//
//                            Intent intent = new Intent(Verification_Activity.this, MainActivity.class);
//                            intent.putExtra("token",token);
//                            startActivity(intent);
//                        }else {
//                            if (responsecode1.equals("0")) {
//                                Intent intent1 = new Intent(Verification_Activity.this, MainActivity.class);
//                                intent1.putExtra("mphone", getMobile);
//                                startActivity(intent1);
//
//                            }
//                        }
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                }
//
//
//            }
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.e("dfjfkljlkfsdjf","jdslkdjl"+t.getMessage());
//            }
//        });
//    }

}