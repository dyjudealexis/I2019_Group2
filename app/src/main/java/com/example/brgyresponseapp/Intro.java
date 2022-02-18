package com.example.brgyresponseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
    }

    public void gotoLogin(View view) {

        Intent intent = new Intent(Intro.this, Login.class);
        startActivity(intent);
        finish();
    }

    public void gotoRegister(View view) {

        Intent intent = new Intent(Intro.this, Register.class);
        startActivity(intent);
        finish();
    }
}