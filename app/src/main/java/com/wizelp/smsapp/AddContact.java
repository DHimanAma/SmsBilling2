package com.wizelp.smsapp;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class AddContact extends Fragment {
    View view;
    EditText editText;
    Button btAdd,btReset;
    RecyclerView recyclerView;
    List<MainData> dataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    MainAdapter adapter;
    String sText;
    List<MainData> user ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_contact, container, false);
       editText = view.findViewById(R.id.edit_text);
       btAdd= view.findViewById(R.id.bt_add);
        RoomDB db= Room.databaseBuilder(getActivity(),
                RoomDB.class, "database").allowMainThreadQueries().build();
        MainDao userDao=db.MainDao();
        user=userDao.getAll();
        Log.e("dsjdj","dsdsjd"+user);
//       btReset= view.findViewById(R.id.bt_reset);
//        recyclerView= view.findViewById(R.id.recycler_view);

        // initialize database
//        database = RoomDB.getInstance(getActivity());
//        // store database value in datalist
//        dataList = database.mainDao().getAll();
//        Log.e("Hiiiiii","<<<<<<<<Shiv>>>>>>>>>>>>"+dataList);
//        //Initilaize linear layout manager
//        linearLayoutManager = new LinearLayoutManager(getActivity());
//        //set layout manager
//        recyclerView.setLayoutManager(linearLayoutManager);
//        // initialize adapter
//        adapter =  new MainAdapter(getActivity(),dataList);
//        // set adapter
//        recyclerView.setAdapter(adapter);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get string from edit text
                sText  = (editText.getText().toString().trim());


                if(editText.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(),"Please Enter Valid Phone Number ",Toast.LENGTH_SHORT).show();
                }


                else if (sText.length() > 10 || sText.length() < 10){
                    Toast.makeText(getActivity(), "Phone Number  must be of 10 digit ", Toast.LENGTH_SHORT).show();
                    return;
                }

                // check condition
              else  if (!sText.isEmpty()){
                   Adddatatolist();
                }
            }
        });


        return view;
    }

    private void Adddatatolist() {
        editText.onEditorAction(EditorInfo.IME_ACTION_DONE);
        //when text is not empty
        //Intialize main data
        RoomDB db= Room.databaseBuilder(getActivity(),
                RoomDB.class, "database").allowMainThreadQueries().build();
        MainDao userDao=db.MainDao();
        List<MainData> data =userDao.is_exist(sText.toString());
        Boolean check =data.size() > 0 ? true : false;
        Log.e("bjhhkjj","fkdf"+check);

       // Boolean b = Boolean.valueOf(data.getText());
        if(!check){
           // sText = String.valueOf(aman.get(i));
                 MainData user = new MainData();
                user.setText(sText);
                userDao.insert(user);
                //Notify when data is inserted
                // dataList.clear();
                //  dataList.addAll(database.mainDao().getAll());
                //  adapter.notifyDataSetChanged();
                Fragment duedateFrag = new Smsfragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame_container, duedateFrag);
                ft.addToBackStack(null);
                ft.commit();
           }else{


            final Dialog dialog;
            dialog = new Dialog(getActivity());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.contact_already_exist_dialog);

            //   TextView error_text=dialog.findViewById(R.id.error_text);
            Button ok =dialog.findViewById(R.id.ok);
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 dialog.dismiss();
                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            dialog.show();

            //Toast.makeText(getActivity(), "Record is already exist", Toast.LENGTH_SHORT).show();
             }

//        for (int i = 0; i < user.size(); i++) {
//            if (user.get(i).text.equals(sText)) {
//
//                Toast.makeText(getActivity(), "Name Already Exists", Toast.LENGTH_SHORT).show();
//                break;
//            }
//            else{
//
//                // sText = String.valueOf(aman.get(i));
//
//                RoomDB db = Room.databaseBuilder(getActivity(),
//                        RoomDB.class, "database").allowMainThreadQueries().build();
//                MainDao userDao = db.MainDao();
//                //  Boolean check =userDao.is_exist(Integer.parseInt(t3.getText().toString()));
//                //Boolean check = userDao.is_exist(Integer.parseInt(String.valueOf(sText)));
//                // Log.e("bjhhkjj","fkdf"+check);
//
//                MainData user = new MainData();
//                user.setText(sText);
//                userDao.insert(user);
//
//                //Notify when data is inserted
//                // dataList.clear();
//                //  dataList.addAll(database.mainDao().getAll());
//                //  adapter.notifyDataSetChanged();
//                Fragment duedateFrag = new Smsfragment();
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.replace(R.id.frame_container, duedateFrag);
//                ft.addToBackStack(null);
//                ft.commit();
//            }}
}
}