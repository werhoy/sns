package com.example.werho.sns;

import android.Manifest;
import android.content.pm.PackageManager;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.alhazmy13.gota.Gota;
import net.alhazmy13.gota.GotaResponse;

public class MainActivity extends AppCompatActivity implements Gota.OnRequestPermissionsBack {

    Button button;
    EditText phone_number;
    EditText sns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Gota.Builder(this)
                .withPermissions()
                .requestId(1)
                .setListener(this)
                .check();
        button = (Button) findViewById(R.id.button);
        phone_number = (EditText) findViewById(R.id.phone_number);
        sns = (EditText) findViewById(R.id.sns);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED){
                    SmsManager.getDefault().sendTextMessage(phone_number.getText().toString(),null,sns.getText().toString(),null,null);
                }
            }
        });

    }

    @Override
    public void onRequestBack(int requestId, @NonNull GotaResponse gotaResponse) {
        if(gotaResponse.isGranted(Manifest.permission.SEND_SMS)){
            // Your code
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
