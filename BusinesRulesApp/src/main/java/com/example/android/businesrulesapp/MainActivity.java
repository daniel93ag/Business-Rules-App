package com.example.android.businesrulesapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import android.content.Intent;
import android.net.ConnectivityManager;

import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void  mostrar(View view){
        if(estaConectado())
        {
            Intent intent = new Intent(MainActivity.this, webActivity.class);

            startActivity(intent);
        }
    }

    public boolean isUsingWiFi() {
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiInfo = connectivity
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (wifiInfo != null && wifiInfo.getState() == NetworkInfo.State.CONNECTED
                || wifiInfo.getState() == NetworkInfo.State.CONNECTING) {
            return true;
        }

        return false;
    }

    protected Boolean conectadoWifi(){
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity != null) {


            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            //NetworkInfo info2 = connectivity.getNetworkInfo(connectivity.getActiveNetwork());

             if(info != null) {

                if (info.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }
    protected Boolean estaConectado(){
        if(conectadoWifi()){

            return true;
        }else{
            if(conectadoRedMovil()){

                return true;
            }else{
                showAlertDialog(this, "Conexion a Internet",
                        "Tu Dispositivo no tiene Conexion a Internet.", false);
                return false;
            }
        }
    }



    protected Boolean conectadoRedMovil(){
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (info != null) {
                if (info.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void showAlertDialog(Context context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        alertDialog.setTitle(title);

        alertDialog.setMessage(message);

        //alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);

        alertDialog.setButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        alertDialog.show();

    }
}



