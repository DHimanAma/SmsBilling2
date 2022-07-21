package com.wizelp.smsapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    //Initialize variable
    public List<MainData> dataList;
    ArrayList<String> arrayList_0 = new ArrayList<>();
    private Activity context;
    private RoomDB database;
    //private final ClickListener listener;
    QuantityListener quantityListener;


String aman;
    //Create constructor
    public MainAdapter(Activity context, List<MainData> dataList,QuantityListener quantityListener){
        this.context = context;
        this.dataList = dataList;
        //this.listener = listener;
        this.quantityListener = quantityListener;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Initialize view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_main,parent,false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
   // Initialize main data class
  // MainData data = dataList.get(position);

   //Initialize database
//        database = RoomDB.getInstance(context);
   //Set text on text view
   holder.textView.setText(dataList.get(position).getText());



   holder.textView.setOnClickListener(new View.OnClickListener() {

       @Override
       public void onClick(View view) {
           //listener.onItemClick(view , position);

//           dataList.get(position).isClick = false ;
//
//           for(int i =0 ; i<dataList.size() ; i++){
//               dataList.get(i).isClick = true ;
//               holder.isSelected.isChecked();

           }


         // String d = dataList.get(position).getText();
          // Log.e("aman","jskasjka"+d);

   });


//   holder.btEdit.setOnClickListener(new View.OnClickListener() {
//       @Override
//       public void onClick(View view) {
//              //initialize main data class
//           MainData d = dataList.get(holder.getAdapterPosition());
//
//
//           Log.e("Helooooooooooooo","<<<<<<Shiv>>>>>>>>"+d);
//
//           //get id
//           int sID = d.getID();
//           //get text
//           String sText  = d.getText();
//           //create dialog
//           Dialog dialog = new Dialog(context);
//           // set content view
//           dialog.setContentView(R.layout.dialog_update);
//
//           // initialize width
//           int width  = WindowManager.LayoutParams.MATCH_PARENT;
//           // initialize height
//            int height = WindowManager.LayoutParams.WRAP_CONTENT;
//            //set layout
//           dialog.getWindow().setLayout(width,height);
//           // show dialog
//           dialog.show();
//
//           //initialize and assign variable
//           EditText editText  = dialog.findViewById(R.id.edit_text);
//           Button btUpdate = dialog.findViewById(R.id.bt_update);
//
//
//           // set text on edit text
//           editText.setText(sText);
////           btUpdate.setOnClickListener(new View.OnClickListener() {
////               @Override
////               public void onClick(View view) {
////                   // Dismiss dialog
////                   dialog.dismiss();
////
////                   // Get update text from edit text
////
////                   String uText = editText.getText().toString().trim();
////                   //update text in database
////                   database.mainDao().update(sID,uText);
////                   // notify when data is updated
////                   dataList.clear();
////                   dataList.addAll(database.mainDao().getAll());
////                   notifyDataSetChanged();
////
////               }
////           });
//
////           holder.isSelected.setOnClickListener(
////                   new View.OnClickListener() {
////                       @Override
////                       public void onClick(View view) {
////                           holder.isSelected.isChecked() ;
////
////
////                           Toast.makeText(context.getApplicationContext(), "Contact is  Added Sucessfully", Toast.LENGTH_SHORT).show();
////                           database.mainDao().insert(data);
////                       }
////                   }
////           );
//
//        // y code check box ke uper lga hai  //
//
//
//
//
//
//
//
//
//       }
//   });

        if (dataList!= null && dataList.size()>0){

            holder.textView.setText( dataList.get(position).getText());

            holder.isSelected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holder.isSelected.isChecked()){
                        arrayList_0.add(dataList.get(position).getText());
                        Log.e("<<<<<<<<<Shiv>>>>>>>","<<<<<<Add>>>>"+arrayList_0);
                        //Toast.makeText(context.getApplication(), "Number is Selected", Toast.LENGTH_SHORT).show();
                     //   Toast.makeText(context.getApplicationContext(), "Checked", Toast.LENGTH_LONG).show();
                    }else {
                        arrayList_0.remove(dataList.get(position).getText());
                        Log.e("<<<<<<<<<Shiv>>>>>>>","<<<<<<removefromlist>>>>"+arrayList_0);
                        //Toast.makeText(context.getApplicationContext(), "Number is UnSelected", Toast.LENGTH_SHORT).show();

                    }
                    quantityListener.onQuantityChange(arrayList_0);
                }
            });


        }
        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Initialize main data

                final Dialog dialog;
                dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.delete_aleart_layout);

             //   TextView error_text=dialog.findViewById(R.id.error_text);
                Button btnReopenId =dialog.findViewById(R.id.btnReopenId1);
                Button btnCancelId = dialog.findViewById(R.id.btnCancelId1);

                btnCancelId.setVisibility(View.VISIBLE);


                btnReopenId.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        RoomDB db= Room.databaseBuilder(holder.textView.getContext(),
                                RoomDB.class, "database").allowMainThreadQueries().build();
                        MainDao userDao=db.MainDao();
                        MainData d  = dataList.get(holder.getAdapterPosition());
                        //delete text from database
                        userDao.delete(d);
                        //notify when data is deleted
                        int position=  holder.getAdapterPosition();
                        dataList.remove(position);
                        if (holder.isSelected.isChecked()){
                            arrayList_0.remove(position);
                            Log.e("<<<<<<<<<Shiv>>>>>>>","<<<<<<delete from list>>>>"+arrayList_0);
                            //  Toast.makeText(context.getApplicationContext(), "UnChecked", Toast.LENGTH_LONG).show();
                        }

                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position,dataList.size());
                        Log.e("SHIV","RANA"+d.text);

                        dialog.dismiss();
                    }
                });

                btnCancelId.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        dialog.dismiss();
                    }
                });

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                dialog.show();




            }
        });


    }

//    public interface ClickListener {
//
//
//        void onItemClick(View view, int position);
//    }
    public interface QuantityListener {
        void onQuantityChange(ArrayList<String> data);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // initialize variable
        TextView textView;
        CheckBox isSelected;
        ImageView btEdit,btDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Assign Variable
            textView = itemView.findViewById(R.id.text_view);
           // btEdit = itemView.findViewById(R.id.btn_edit);
            btDelete = itemView.findViewById(R.id.bt_delete);
            isSelected = itemView.findViewById(R.id.isSelected);
        }
    }
}
