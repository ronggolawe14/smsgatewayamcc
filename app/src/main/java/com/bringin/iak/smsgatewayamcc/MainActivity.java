package com.bringin.iak.smsgatewayamcc;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText _txtapi, _txtmess, _txtsender, _txtnum;
    Button _btnsend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _txtapi = findViewById(R.id.txtapi);
        _txtmess = findViewById(R.id.txtmess);
        _txtsender = findViewById(R.id.txtsender);
        _txtnum = findViewById(R.id.txtphone);
        _btnsend = findViewById(R.id.btnsend);


        _btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _btnsend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            // Construct data
                            String apiKey = "apikey=" + _txtapi.getText().toString();
                            String message = "&amp;amp;message=" + _txtmess.getText().toString();
                            String sender = "&amp;amp;sender=" + _txtsender.getText().toString();
                            String numbers = "&amp;amp;numbers=" + _txtnum.getText().toString();

                            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();

                            String data = apiKey + numbers + message + sender;
                            conn.setDoOutput(true);
                            conn.setRequestMethod("POST");
                            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                            conn.getOutputStream().write(data.getBytes("UTF-8"));
                            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                            final StringBuffer stringBuffer = new StringBuffer();
                            String line;
                            while ((line = rd.readLine()) != null) {
                                stringBuffer.append(line); Toast.makeText(getApplicationContext(), "the message is "+line, Toast.LENGTH_LONG).show();
                            }
                            rd.close();
                            Log.d("myTag", "coba cah");
                            //return stringBuffer.toString();
                        } catch (Exception e) {
                            System.out.println("Error SMS " + e);
                            Toast.makeText(getApplicationContext(), "the error message is" + e, Toast.LENGTH_LONG).show();
                            //return "Error " + e;
                        }
                    }
                });

                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);



            }
        });


    }
}
