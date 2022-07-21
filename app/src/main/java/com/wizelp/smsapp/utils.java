package com.wizelp.smsapp;

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.prefs.Preferences;

public class utils {
    public static final String Phonekey = "aman";
    public static final String prefixkey = "dhiman";
    public static String token = "";
    public static String Count = "";
    public static String INSTANCE_NAMEPHONE = "Wizelp34";
    public static String INSTANCE_NAMEPHONEToKEN = "Wizelp3498";
    public static String INSTANCE_Login = "Wizelpppp";
    public static String INSTANCE_Count= "COUNTNN";
    private SharedPreferences mPreferences;
    public static Context context = null;
    private static final String APP_SHARED_PREFS = Preferences.class.getSimpleName();
    public static String PH_NO = "com.AmanTel.AmanTel.PH_NO";
    public static String Prefix1 = "com.AmanTel.AmanTel.Prefix";
    public static String SHIV = "shiv";
    private static SharedPreferences.Editor editor;

    private utils(Context context) {
        this.mPreferences = context.getSharedPreferences(APP_SHARED_PREFS, MODE_PRIVATE);
    }

    public static void setDeviceToken(Context context, String token) {
        SharedPreferences pref = context.getSharedPreferences(
                INSTANCE_NAMEPHONEToKEN, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Dev_token", token);
        editor.commit();
    }

    public static String getDeviceToken(Context context) {
        SharedPreferences pref = context.getSharedPreferences(
                INSTANCE_NAMEPHONEToKEN, 0);
        String Dev_Token = pref.getString("Dev_token", "null");
        if (!Dev_Token.equals("null") && Dev_Token.length() > 0 && !Dev_Token.equalsIgnoreCase("")) {
            token = Dev_Token;
            return token;
        } else {
            return "";
        }
    }

    public static void setLoginCredentials(String Prefix, String Phone, Context context) {

        SharedPreferences prefs = context.getSharedPreferences(INSTANCE_Login, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("abcd", Phone);
        editor.putString("efgh", Prefix);

        // editor.putString(Settings.Prefs.ACCOUNT_NUM, Accountno);
        editor.commit();

    }

//    public static int savedcount(int Phone, Context context) {
//
//        SharedPreferences prefs = context.getSharedPreferences(INSTANCE_NAME, 0);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putInt(SHIV, Phone);
//
//        editor.apply();
//        // editor.putString(Settings.Prefs.ACCOUNT_NUM, Accountno);
//        editor.commit();
//
//        return Phone;
//    }
//
//    public static Integer getcount(Context context) {
//
//        SharedPreferences prefs =
//                context.getSharedPreferences(INSTANCE_NAME, 0);
//
//        int Phone = Integer.parseInt(prefs.getString(SHIV, "null"));
//
//
//        return Integer.valueOf(Phone);
//
//    }

//    public static void savenumber(String Phone, Context context) {
//
//        SharedPreferences prefs = context.getSharedPreferences(INSTANCE_NAMEPHONE, MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putString(PH_NO, Phone);
//editor.apply();
//        // editor.putString(Settings.Prefs.ACCOUNT_NUM, Accountno);
//        editor.commit();
//
//    }

//    public static String getsavednumber(Context context) {
//        SharedPreferences prefs =
//                context.getSharedPreferences(INSTANCE_NAMEPHONE, MODE_PRIVATE);
//
//        String Phone = prefs.getString(PH_NO, "");
//
//        return Phone;
//
//    }

    public static boolean getLoginCredentials(Context context) {
        SharedPreferences prefs =
                context.getSharedPreferences(INSTANCE_Login, 0);

        String Phone = prefs.getString("abcd", "null");
        String Prefix = prefs.getString("efgh", "null");


        if (Phone.equalsIgnoreCase("null")
                || Prefix.equalsIgnoreCase("null")


        )
            return false;
        else {

            PH_NO = Phone;
            Prefix1 = Prefix;
            return true;
        }
    }

    public static void setLogOut(Context context) {

        final Dialog dialog;
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_error);

        TextView error_text=dialog.findViewById(R.id.error_text);
        Button btnReopenId =dialog.findViewById(R.id.btnReopenId);
        Button btnCancelId = dialog.findViewById(R.id.btnCancelId);
        btnReopenId.setText("Logout");
        if (context == null && utils.context != null) {
            context = utils.context;
        }

        SharedPreferences prefs =
                context.getSharedPreferences(INSTANCE_Login, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
        SharedPreferences prefs1 = context.getSharedPreferences("image_data", 0);
        SharedPreferences.Editor editor1 = prefs1.edit();
        editor1.clear();
        editor1.commit();

        if (utils.editor != null) {
            utils.editor.clear();
            utils.editor.commit();
        }


        Prefix1 = "";
        PH_NO = "";


        btnCancelId.setVisibility(View.VISIBLE);
        error_text.setText(context.getString(R.string.logout_));
        Context finalContext = context;
        btnReopenId.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(finalContext,Login_Activity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                finalContext.startActivity(intent);
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
    public static void setCountLogout(Context context) {

        if (context == null && utils.context != null) {
            context = utils.context;
        }

        SharedPreferences prefs = context.getSharedPreferences(INSTANCE_Count, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
        SharedPreferences prefs1 = context.getSharedPreferences("image_data", 0);
        SharedPreferences.Editor editor1 = prefs1.edit();
        editor1.clear();
        editor1.commit();

        if (utils.editor != null) {
            utils.editor.clear();
            utils.editor.commit();
        }

        SHIV = "";


    }
    public static void count(int Phone, Context context) {

        SharedPreferences pref = context.getSharedPreferences(
                INSTANCE_Count, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("dhiman", Phone);
        editor.commit();
        editor.apply();
//        SharedPreferences prefs = context.getSharedPreferences(INSTANCE_Count, MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putInt(SHIV,Phone);
//editor.apply();
//        // editor.putString(Settings.Prefs.ACCOUNT_NUM, Accountno);
//        editor.commit();


    }
    public static int getcount(Context context) {

        SharedPreferences pref = context.getSharedPreferences(
                INSTANCE_Count, 0);
        int Dev_Token = pref.getInt("dhiman", 0);

            return Dev_Token;
        }


//        SharedPreferences prefs =
//                context.getSharedPreferences(INSTANCE_Count, MODE_PRIVATE);
//
//        int Phone = prefs.getInt(SHIV, 0);
//
//        return Phone;


    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) { e.printStackTrace();}
    }
    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }
}