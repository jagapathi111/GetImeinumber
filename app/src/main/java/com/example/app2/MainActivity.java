package com.example.app2;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app2.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnStart;
    TextView varText;
    String info;
    String strPhoneType = "";
    static final int PERMISSION_READ_STATE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start(View view)  {

        int permissionCheck = ContextCompat.checkSelfPermission( this, Manifest.permission.READ_PHONE_STATE);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED)  {

            MyTelephonyManager();

        } else {

            ActivityCompat.requestPermissions( this,
                    new String[]  {Manifest.permission.READ_PHONE_STATE},
                    PERMISSION_READ_STATE);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {

            case PERMISSION_READ_STATE: {

                if (grantResults.length >= 0 && grantResults[0] == getPackageManager().PERMISSION_GRANTED) {

                    MyTelephonyManager();

                } else {

                    Toast.makeText(this,
                            "you don't have perMission",
                            Toast.LENGTH_SHORT).show();





                }
            }

        }
    }

    private void MyTelephonyManager() {

        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        int PhoneType = manager.getPhoneType();

        switch (PhoneType) {

            case (TelephonyManager.PHONE_TYPE_CDMA):
                strPhoneType = "CDMA";
                break;

            case (TelephonyManager.PHONE_TYPE_GSM):
                strPhoneType = "GSM";
                break;

            case (TelephonyManager.PHONE_TYPE_NONE):
                strPhoneType = "NONE";
                break;


        }


        boolean isRoaming = manager.isNetworkRoaming();

        String Phonetype = strPhoneType;
        String IMEINumber = manager.getDeviceId();
        String subscriberId = manager.getDeviceId();
        String SIMSerialNumber = manager.getSimSerialNumber();
        String networkCountryISO = manager.getNetworkCountryIso();
        String SIMCountryISO = manager.getSimCountryIso();
        String softwareVersion = manager.getDeviceSoftwareVersion();
        String voiceMailNumber = manager.getVoiceMailNumber();

        info = "Phone Details: \n";
        info += "\n Phone Network Type: " + PhoneType;
        info += "\n IMEI Number: " + IMEINumber;
        info += "\n SubscriberID: " + subscriberId;
        info += "\n Sim Serial Number: " + SIMSerialNumber;
        info += "\n Network Country ISO: " + networkCountryISO;
        info += "\n SIM country ISO: " + SIMCountryISO;
        info += "\n Software Version: " + softwareVersion;
        info += "\n Voice Mail Number: " + voiceMailNumber;
        info += "\n In Roaming: " + isRoaming;
        btnStart = (Button) findViewById(R.id.idBtnStart);
        varText = (TextView) findViewById(R.id.idTextView);
        varText.setText(info);


      /*  public static void main (String[]args){

           String Phonetype = strPhoneType;
            String IMEINumber = manager.getDeviceId();
            String subscriberId = manager.getDeviceId();
            String SIMSerialNumber = manager.getSimSerialNumber();
            String networkCountryISO = manager.getNetworkCountryIso();
            String SIMCountryISO = manager.getSimCountryIso();
            String softwareVersion = manager.getDeviceSoftwareVersion();
            String voiceMailNumber = manager.getVoiceMailNumber(); */

            ArrayList<String> phoneDetails = new ArrayList<String>();

            phoneDetails.add(Phonetype);
            phoneDetails.add(IMEINumber);
            phoneDetails.add(subscriberId);
            phoneDetails.add(SIMSerialNumber);
            phoneDetails.add(networkCountryISO);
            phoneDetails.add(SIMCountryISO);
            phoneDetails.add(softwareVersion);
            phoneDetails.add(voiceMailNumber);



    }

}
