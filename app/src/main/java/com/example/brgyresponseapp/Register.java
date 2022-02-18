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

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    private EditText fname, address, email, password;
    private String sfname, saddress, semail, spassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fname = findViewById(R.id.r_name);
        address = findViewById(R.id.r_add);
        email = findViewById(R.id.r_em);
        password = findViewById(R.id.r_pw);
    }

    public void register(View view){
        sfname = fname.getText().toString().trim();
        saddress = address.getText().toString().trim();
        semail = email.getText().toString().trim();
        spassword = password.getText().toString().trim();

        if(sfname.isEmpty() || saddress.isEmpty() || semail.isEmpty() || spassword.isEmpty()){
            Toast.makeText(Register.this, "Please fill up the form.", Toast.LENGTH_SHORT).show();
        }

        else {
            final String URL = "http://" + Final_IP.IP_ADDRESS + "/br/register.php";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, response -> {
                Log.d("res", response);
                if (response.equals("success")) {
                    Toast.makeText(Register.this, "Register Successfully.", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Register.this, Login.class);
                    startActivity(intent);
                    finish();
                } else if (response.equals("failure")) {
                    Toast.makeText(Register.this, "Something wrong, try again.", Toast.LENGTH_SHORT).show();
                }
            }, error -> Toast.makeText(Register.this, error.toString().trim(), Toast.LENGTH_SHORT).show()) {
                @Override
                public Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("fname", sfname);
                    data.put("address", saddress);
                    data.put("email", semail);
                    data.put("password", spassword);

                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }
    public void backIntro1(View view) {

        Intent intent = new Intent(Register.this, Intro.class);
        startActivity(intent);
        finish();
    }

}