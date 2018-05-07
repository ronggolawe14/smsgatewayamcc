package com.bringin.iak.smsgatewayamcc;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nomerHp, pesan;
    Button kirim;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nomerHp = findViewById(R.id.edt1);
        pesan = findViewById(R.id.edt2);
        kirim = findViewById(R.id.btn_kirim);

        kirim.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                String no = nomerHp.getText().toString();
                String psn = pesan.getText().toString();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(no, null, psn, pi, null);

                Toast.makeText(getApplicationContext(), "Pesan Terkirim", 10);

            }
        });



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}

