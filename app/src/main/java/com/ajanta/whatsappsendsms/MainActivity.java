package com.ajanta.whatsappsendsms;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edittext;
    Button btnSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edittext = findViewById(R.id.edittext);
        btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btnSend){
            openWhatsApp(edittext.getText().toString().trim(),this);
        }
    }

    //Send the order to whatsApp
    public static void openWhatsApp(String phoneno, Context mContext) {
        String message = "Hello";
        PackageManager packageManager = mContext.getPackageManager();
        Intent i = new Intent(Intent.ACTION_VIEW);
        try {
            String url = "https://api.whatsapp.com/send?phone=91"+phoneno+"&text=" + URLEncoder.encode(message, "UTF-8");
            i.setPackage("com.whatsapp");
            i.setData(Uri.parse(url));
            if (i.resolveActivity(packageManager) != null) {
                mContext.startActivity(i);
            } else {
                Toast.makeText(mContext, "No app found", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
