package com.wizelp.smsapp;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Handler;
import android.os.Looper;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Smsfragment extends Fragment implements MainAdapter.QuantityListener {
    ArrayList<String> arrayList_0 = new ArrayList<>();
    public Menu menu;
    TextView navtext, mymessage, header_text, hello;
    TextView msgcount, Counter1, tv_phn;
    Spinner spinner2;
    String Textml;
    RelativeLayout progressBar;
    String randomValue;
    public static final int SEND_SMS = 1000;
    boolean clicked;
    private int mCounter = 0;
    private int count = 0;
    private ProgressDialog progressDialog;
    String str;
    String mobileNo;
    private Object String;
    private Handler handler;
    String aman;
    Button btn_verifyCode;
    DrawerLayout drawer;
    ImageView back, setting_icon, crashButton;
   RoomDB database1;
    View view;
    EditText editText;
    Button btAdd,btReset;
    RecyclerView recyclerView;
    RoomDB database;
    List<MainData> dataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    MainAdapter adapter;
    String data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_smsfragment, container, false);

        Counter1 = view.findViewById(R.id.Counter);
        progressBar = view.findViewById(R.id.progressBar);
        //tv_phn = view.findViewById(R.id.tv_phn);
        btn_verifyCode = view.findViewById(R.id.btn_verifyCode23);
      //  spinner2 = view.findViewById(R.id.mkl);
        editText = view.findViewById(R.id.edit_text);

        hideKeyboardFrom(getActivity(),view);
        recyclerView= view.findViewById(R.id.recycler_view);

        RoomDB db= Room.databaseBuilder(getActivity(),
                RoomDB.class, "database").allowMainThreadQueries().build();
        MainDao userDao=db.MainDao();
       List<MainData>user=userDao.getAll();

        Log.e("Hiiiiii","<<<<<<<<Shiv>>>>>>>>>>>>"+dataList);
        //Initilaize linear layout manager
        linearLayoutManager = new LinearLayoutManager(getActivity());
        //set layout manager
        recyclerView.setLayoutManager(linearLayoutManager);
        // initialize adapter
        adapter =  new MainAdapter(getActivity(),user ,(MainAdapter.QuantityListener) this);
        // set adapter
        recyclerView.setAdapter(adapter);


        // ADD BUTTON
//
//        btAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // get string from edit text
//                String sText  = (editText.getText().toString().trim());
//
//
//                if(editText.getText().toString().isEmpty()) {
//                    Toast.makeText(getActivity(),"Please Enter Valid Phone Number ",Toast.LENGTH_SHORT).show();
//                }
//
//                else if (sText.length() > 10 || sText.length() < 10){
//                    Toast.makeText(getActivity(), "Phone Number  must be of 10 digit ", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                // check condition
//                else  if (!sText.equals("")){
//                    Toast.makeText(getActivity(), "Phone Number is Added Successfully ", Toast.LENGTH_SHORT).show();
//                    //when text is not empty
//                    //Intialize main data
//                    MainData data =  new MainData();
//
//                    // set text on main data
//                    data.setText(sText);
//
//                    Log.e("Heloooooo","<<<<<<<<<<<<shiv>>>>>>>>>"+data.text);
//
//                    // insert text in database
//                    database.mainDao().insert(data);
//                    //clear edit text
//                    editText.setText("");
//                    //Notify when data is inserted
//                    dataList.clear();
//                    dataList.addAll(database.mainDao().getAll());
//                    adapter.notifyDataSetChanged();
//                }
//
//            }
//        });


      //  List<String> list = new ArrayList<String>();

//        list.add("Please Select Contact");
//        list.add("8814076024");
//        list.add("9896682259");
//        list.add("8814894068");
//        list.add("8894352639");
//        list.add("9041418585");
        List<MainData> list = new ArrayList<>();

//        list = database1.mainDao().getAll();


//        ArrayAdapter<MainData> dataAdapter = new ArrayAdapter<MainData>(getActivity(), android.R.layout.simple_spinner_item, list);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner2.setAdapter(dataAdapter);
      //  spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Textml = spinner2.getSelectedItem().toString();
//
//                Log.e("hellooo", "<<<<<<<hii>>>>>1123" + Textml);
//            }

//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
        btn_verifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // checkAndroidVersion(Textml);
                aman();

            }
        });

//      count= utils.showPreferences(getActivity());
       // Log.e("mksd","jmdskd"+count);
       // Counter1.setText(count);
//        SharedPreferences sh = getActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE);
//
////        String s1 = sh.getString("name", "");
//        count = sh.getInt("age", 0);
//        Log.e("jdsks","dksdsd"+count);

//       String count= (utils.getcount(getActivity()));
//        Log.e("jdsks","dksdsd"+count);
//
//        Counter1.setText(count);

//       count= Integer.parseInt((utils.getcount(getActivity())));
//        Log.e("jdsks","dksdsd"+count);

//         Counter.setText(a);
     count= utils.getcount(getActivity());
     Log.e("mksd","jmdskd"+count);
     Counter1.setText(Integer.toString(count));



        return view;
    }
    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }







        // Creating a shared pref object
        // with a file name "MySharedPref"
        // in private mode


        public void aman() {
//        if (Textml.equalsIgnoreCase("Please Select Contact")) {
//            final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
//            View mView = getLayoutInflater().inflate(R.layout.custom_dialog1, null);
//            Button okay = (Button) mView.findViewById(R.id.okay);
//            TextView mohone = mView.findViewById(R.id.number1);
//            mohone.setText("Please Select Contact");
//            alert.setView(mView);
//            final AlertDialog alertDialog = alert.create();
//            alertDialog.setCanceledOnTouchOutside(false);
//            okay.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    alertDialog.dismiss();
//                }
//            });
//            alertDialog.show();
//        } else {
//            checkAndroidVersion(data);
//          // btn_verifyCode.setVisibility(View.INVISIBLE);

//
//     }


            if( arrayList_0.size()<=0){
                Toast.makeText(getActivity(), "Please Select the Contact", Toast.LENGTH_SHORT).show();

            }
            else {
                Log.e("SMS", "Button" + arrayList_0);
                for (int i = 0; i < arrayList_0.size(); i++) {
                    data = arrayList_0.get(i);
                    checkAndroidVersion(data);
                }
            }
        }


    public void checkAndroidVersion(String mobile) {
        this.Textml = mobile;
        if (Build.VERSION.SDK_INT >= 23) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.SEND_SMS);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.SEND_SMS}, SEND_SMS);
                return;
            } else {

                sendSms(mobile);
            }
        } else {
            sendSms(mobile);
        }
    }
    //
    private void sendSms(String mobileNo) {
        try {
            randomValue = chooseRandomString();
            progressBar.setVisibility(View.VISIBLE);
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(mobileNo, null, randomValue, null, null);
//            utils.SavePreferences(count);

            //  smsManager.sendTextMessage(number,null,matn,null,null);
            int time = new Random().nextInt(4) + 2;
            Log.e("Timespace", java.lang.String.valueOf(time));

            handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    try {
                        Random r = new Random();
                        int low = 1;
                        int high = 7;
                        int result = r.nextInt(high - low) + low;
                        Log.e("randomNumber", java.lang.String.valueOf(result));

                        if (mCounter < result) {
                            mCounter++;
                            count++;
//                            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE);
//                            SharedPreferences.Editor myEdit = sharedPreferences.edit();

                           // count=utils.savedcount(count,getActivity());
                            // write all the data entered by the user in SharedPreference and apply
                          utils.count(count,getActivity());
//                            myEdit.putInt("age", count);
//                            myEdit.apply();
                            Counter1.setText(Integer.toString(count));
                            checkAndroidVersion(Textml);
                          //  utils.SavePreferences(mCounter);
                        } else {
                            handler.removeCallbacks(this);
                            progressBar.setVisibility(View.GONE);
                            mCounter=0;
                            btn_verifyCode.setVisibility(View.VISIBLE);
                        }

                    } catch (Exception e) {
                    }
                }
            }, time * 1000);


        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error" + e, Toast.LENGTH_LONG).show();
        }
    }


    private String convertStringArrayToString(String[] names, String s) {
        StringBuilder sb = new StringBuilder();
        for (String str : names)
            sb.append(str).append(s);
        return sb.substring(0, sb.length() - 1);


    }


    private String chooseRandomString() {
        String[] names = getResources().getStringArray(R.array.name);

        str = Arrays.toString(names);
        int l = str.length();
        Log.e("<<<<<hellllo>>>>>>>>", ">>>>>>>>shiv<<<<<<<<<<" + l);
        str = convertStringArrayToString(names, ",");
        //int my = names.length;

        //Log.e("hellllo","shiv"+my);
        Random random = new Random();
        int randomMessage = random.nextInt(names.length);
        return names[randomMessage];


    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case SEND_SMS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    sendSms(Textml);
                } else {
                    Toast.makeText(getActivity(), "SEND_SMS Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


//    @Override
//    public void onItemClick(View view, int position) {
//        int pos = position ;
//
//         data = dataList.get(pos).text;
//
//        Log.e("dshkgd", ""+data);
//    }


    @Override
    public void onQuantityChange(ArrayList<String> data) {
      Log.e("SHIVH","HUM"+data.toString()) ;
//        for(int i =0 ; i<data.size() ; i++){
//               data.get(0).toString();
//            String s  =
//        }
           arrayList_0 = data;
           Log.e("dnsdsdsd","kss<<<<<buttongetdata>>>"+arrayList_0);

    }
}