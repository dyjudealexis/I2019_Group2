package com.example.brgyresponseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SusData extends AppCompatActivity {
    private EditText sname, sgender, saddress, sage, scitizen;

    private String snames, sgenders, saddresss, sages, scitizens;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sus_data);

        sname = findViewById(R.id.sus_name);
        sgender = findViewById(R.id.sus_gender);
        saddress = findViewById(R.id.sus_address);
        sage = findViewById(R.id.sus_age);
        scitizen = findViewById(R.id.sus_citizen);

        sname.setText(FormData.suspect_data[0]);
        sgender.setText(FormData.suspect_data[1]);
        saddress.setText(FormData.suspect_data[2]);
        sage.setText(FormData.suspect_data[3]);
        scitizen.setText(FormData.suspect_data[4]);

    }

    public void susBack(View view){
        FormData.ClearData();

        Intent intent = new Intent(SusData.this, Home.class);
        startActivity(intent);
        finish();
    }

    public void susNext(View view){
        try {
            snames = sname.getText().toString().trim();
            sgenders = sgender.getText().toString().trim();
            saddresss = saddress.getText().toString().trim();
            sages = sage.getText().toString().trim();
            scitizens = scitizen.getText().toString().trim();

            FormData.suspect_data[0] = snames;
            FormData.suspect_data[1] = sgenders;
            FormData.suspect_data[2] = saddresss;
            FormData.suspect_data[3] = sages;
            FormData.suspect_data[4] = scitizens;



            if(snames.isEmpty() || sgenders.isEmpty() || saddresss.isEmpty() || sages.isEmpty() || scitizens.isEmpty()){
                Toast.makeText(SusData.this, "Please fill up the form.", Toast.LENGTH_SHORT).show();
            }


            if(sgenders.equals("Male") || sgenders.equals("Female")){
                if(scitizens.equals("Filipino") || scitizens.equals("Foreigner")){
                    if(sages.length()>=4){
                        Toast.makeText(SusData.this, "Age up to 3 digits only.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent intent = new Intent(SusData.this, VicData.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else {
                    Toast.makeText(SusData.this, "Citizens are Filipino and Foreigner only.", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(SusData.this, "Genders are Male and Female only.", Toast.LENGTH_SHORT).show();
            }

        }
        catch (Exception e){
            Toast.makeText(SusData.this, "Please fill up the form.", Toast.LENGTH_SHORT).show();
        }
    }

}