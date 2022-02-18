package com.example.brgyresponseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class VicData extends AppCompatActivity {
    private EditText vname, vgender, vaddress, vage, vcitizen;

    private String vnames, vgenders, vaddresss, vages, vcitizens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vic_data);

        vname = findViewById(R.id.vic_name);
        vgender = findViewById(R.id.vic_gender);
        vaddress = findViewById(R.id.vic_address);
        vage = findViewById(R.id.vic_age);
        vcitizen = findViewById(R.id.vic_citizen);

        vname.setText(FormData.victim_data[0]);
        vgender.setText(FormData.victim_data[1]);
        vaddress.setText(FormData.victim_data[2]);
        vage.setText(FormData.victim_data[3]);
        vcitizen.setText(FormData.victim_data[4]);


    }

    public void vicBack(View view){
        vnames = vname.getText().toString().trim();
        vgenders = vgender.getText().toString().trim();
        vaddresss = vaddress.getText().toString().trim();
        vages = vage.getText().toString().trim();
        vcitizens = vcitizen.getText().toString().trim();

        FormData.victim_data[0] = vnames;
        FormData.victim_data[1] = vgenders;
        FormData.victim_data[2] = vaddresss;
        FormData.victim_data[3] = vages;
        FormData.victim_data[4] = vcitizens;

        Intent intent = new Intent(VicData.this, SusData.class);
        startActivity(intent);
        finish();

    }

    public void vicNext(View view){
        try {
            vnames = vname.getText().toString().trim();
            vgenders = vgender.getText().toString().trim();
            vaddresss = vaddress.getText().toString().trim();
            vages = vage.getText().toString().trim();
            vcitizens = vcitizen.getText().toString().trim();

            FormData.victim_data[0] = vnames;
            FormData.victim_data[1] = vgenders;
            FormData.victim_data[2] = vaddresss;
            FormData.victim_data[3] = vages;
            FormData.victim_data[4] = vcitizens;



            if(vnames.isEmpty() || vgenders.isEmpty() || vaddresss.isEmpty() || vages.isEmpty() || vcitizens.isEmpty()){
                Toast.makeText(VicData.this, "Please fill up the form.", Toast.LENGTH_SHORT).show();
            }

            if(vgenders.equals("Male") || vgenders.equals("Female")){
                if(vcitizens.equals("Filipino") || vcitizens.equals("Foreigner")){
                    if(vages.length()>=4){
                        Toast.makeText(VicData.this, "Age up to 3 digits only.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent intent = new Intent(VicData.this, IncData.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else {
                    Toast.makeText(VicData.this, "Citizens are Filipino and Foreigner only.", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(VicData.this, "Genders are Male and Female only.", Toast.LENGTH_SHORT).show();
            }

        }
        catch (Exception e){
            Toast.makeText(VicData.this, "Please fill up the form.", Toast.LENGTH_SHORT).show();
        }


    }
}