package com.example.brgyresponseapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private EditText etEmail, etPassword;

    private String email, password, info;

    private final String URL = "http://"+Final_IP.IP_ADDRESS+"/br/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = password = "";
        etEmail = findViewById(R.id.loginEmail);
        etPassword = findViewById(R.id.loginPassword);

        //etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
    }
    public void login(View view) {
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        if(!email.equals("") && !password.equals("")){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, response -> {
                Log.d("res", response);


                if (response.equals("success")) {
                    FormData.email = email;

                    Intent intent = new Intent(Login.this, Home.class);
                    startActivity(intent);
                    finish();


                } else if (response.equals("failure")) {
                    Toast.makeText(Login.this, "Invalid Login Id/Password", Toast.LENGTH_SHORT).show();
                }
            }, error -> Toast.makeText(Login.this, error.toString().trim(), Toast.LENGTH_SHORT).show()){
                @Override
                public Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("email", email);
                    data.put("password", password);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }else{
            Toast.makeText(this, "Fields can not be empty!", Toast.LENGTH_SHORT).show();
        }
    }

    public void backIntro(View view) {

        Intent intent = new Intent(Login.this, Intro.class);
        startActivity(intent);
        finish();
    }




}