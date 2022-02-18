package com.example.brgyresponseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class IncData extends AppCompatActivity {
    private EditText to_inc, inc_add, inc_en, inc_date, inc_time;

    private String to_incs, inc_adds, inc_ens, inc_dates, inc_times;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inc_data);

        to_inc = findViewById(R.id.to_inc);
        inc_add = findViewById(R.id.inc_add);
        inc_en = findViewById(R.id.inc_en);
        inc_date = findViewById(R.id.inc_date);
        inc_time = findViewById(R.id.inc_time);

        to_inc.setText(FormData.incident_data[0]);
        inc_add.setText(FormData.incident_data[1]);
        inc_en.setText(FormData.incident_data[2]);
        inc_date.setText(FormData.incident_data[3]);
        inc_time.setText(FormData.incident_data[4]);

        String dt = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String tm = new SimpleDateFormat("hh:mm aa", Locale.getDefault()).format(new Date());

        inc_date.setText(dt);
        inc_time.setText(tm);

        inc_en.setText(Integer.toString(FormData.incdata_en));

        inc_date.setEnabled(false);
        inc_time.setEnabled(false);
        inc_en.setEnabled(false);
    }

    public void incBack(View view){
        to_incs = to_inc.getText().toString().trim();
        inc_adds = inc_add.getText().toString().trim();
        inc_ens = inc_en.getText().toString().trim();
        inc_dates = inc_date.getText().toString().trim();
        inc_times = inc_time.getText().toString().trim();

        FormData.incident_data[0] = to_incs;
        FormData.incident_data[1] = inc_adds;
        FormData.incident_data[2] = inc_ens;
        FormData.incident_data[3] = inc_dates;
        FormData.incident_data[4] = inc_times;

        Intent intent = new Intent(IncData.this, VicData.class);
        startActivity(intent);
        finish();
    }

    public void submitSusData(){
        final String URL = "http://"+Final_IP.IP_ADDRESS+"/br/sus_data.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, response -> {
            Log.d("res", response);
            if (response.equals("success")) {
                Toast.makeText(IncData.this, "Suspect Data inserted.", Toast.LENGTH_SHORT).show();
            } else if (response.equals("failure")) {
                Toast.makeText(IncData.this, "Something wrong, try again.", Toast.LENGTH_SHORT).show();
            }
        }, error -> Toast.makeText(IncData.this, error.toString().trim(), Toast.LENGTH_SHORT).show()){
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("sus_name", FormData.suspect_data[0]);
                data.put("sus_gender", FormData.suspect_data[1]);
                data.put("sus_address", FormData.suspect_data[2]);
                data.put("sus_age", FormData.suspect_data[3]);
                data.put("sus_citizen", FormData.suspect_data[4]);

                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    public void submitVicData(){
        final String URL1 = "http://"+Final_IP.IP_ADDRESS+"/br/vic_data.php";
        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, URL1, response -> {
            Log.d("res", response);
            if (response.equals("success")) {
                Toast.makeText(IncData.this, "Victim Data inserted.", Toast.LENGTH_SHORT).show();
            } else if (response.equals("failure")) {
                Toast.makeText(IncData.this, "Something wrong, try again.", Toast.LENGTH_SHORT).show();
            }
        }, error -> Toast.makeText(IncData.this, error.toString().trim(), Toast.LENGTH_SHORT).show()){
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("vic_name", FormData.victim_data[0]);
                data.put("vic_gender", FormData.victim_data[1]);
                data.put("vic_address", FormData.victim_data[2]);
                data.put("vic_age", FormData.victim_data[3]);
                data.put("vic_citizen", FormData.victim_data[4]);

                return data;
            }
        };
        RequestQueue requestQueue1 = Volley.newRequestQueue(getApplicationContext());
        requestQueue1.add(stringRequest1);
    }

    public void incSubmit(View view){
        submitSusData();
        submitVicData();

        to_incs = to_inc.getText().toString().trim();
        inc_adds = inc_add.getText().toString().trim();
        inc_ens = inc_en.getText().toString().trim();
        inc_dates = inc_date.getText().toString().trim();
        inc_times = inc_time.getText().toString().trim();

        FormData.incident_data[0] = to_incs;
        FormData.incident_data[1] = inc_adds;
        FormData.incident_data[2] = inc_ens;
        FormData.incident_data[3] = inc_dates;
        FormData.incident_data[4] = inc_times;

        if(to_incs.isEmpty() || inc_adds.isEmpty() || inc_ens.isEmpty() || inc_dates.isEmpty() || inc_times.isEmpty()){
            Toast.makeText(IncData.this, "Please fill up the form.", Toast.LENGTH_SHORT).show();
        }

        else{
            final String URL2 = "http://"+Final_IP.IP_ADDRESS+"/br/inc_data.php";
            StringRequest stringRequest2 = new StringRequest(Request.Method.POST, URL2, response -> {
                Log.d("res", response);
                if (response.equals("success")) {
                    Toast.makeText(IncData.this, "Incident Data inserted.", Toast.LENGTH_SHORT).show();

                    Toast.makeText(IncData.this, "Filled up Successfully.", Toast.LENGTH_SHORT).show();
                    FormData.ClearData();

                    Intent intent = new Intent(IncData.this, Home.class);
                    startActivity(intent);
                    finish();
                } else if (response.equals("failure")) {
                    Toast.makeText(IncData.this, "Something wrong, try again.", Toast.LENGTH_SHORT).show();
                }
            }, error -> Toast.makeText(IncData.this, error.toString().trim(), Toast.LENGTH_SHORT).show()){
                @Override
                public Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("to_inc", FormData.incident_data[0]);
                    data.put("inc_add", FormData.incident_data[1]);
                    data.put("inc_en", FormData.incident_data[2]);
                    data.put("inc_date", FormData.incident_data[3]);
                    data.put("inc_time", FormData.incident_data[4]);

                    return data;
                }
            };
            RequestQueue requestQueue2 = Volley.newRequestQueue(getApplicationContext());
            requestQueue2.add(stringRequest2);



        }
    }


}