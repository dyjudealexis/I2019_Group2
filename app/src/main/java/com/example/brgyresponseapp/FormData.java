package com.example.brgyresponseapp;


import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class FormData {
    public static String[] suspect_data = {"", "", "", "", ""};
    public static String[] victim_data = {"", "", "", "", ""};
    public static String[] incident_data = {"", "", "", "", ""};

    public static int FilledSus = 0;
    public static int FilledVic = 0;
    public static int FilledInc = 0;

    public static String from = "";
    public static String to = "";
    public static int incdata_id;
    public static int incdata_en;

    public static boolean showOnly = false;

    public static String[] en = {""}, ia = {""}, toi = {""}, dt = {""}, tm = {""};

    public static void ClearData(){
        suspect_data[0] = "";
        suspect_data[1] = "";
        suspect_data[2] = "";
        suspect_data[3] = "";
        suspect_data[4] = "";

        victim_data[0] = "";
        victim_data[1] = "";
        victim_data[2] = "";
        victim_data[3] = "";
        victim_data[4] = "";

        incident_data[0] = "";
        incident_data[1] = "";
        incident_data[2] = "";
        incident_data[3] = "";
        incident_data[4] = "";

        FilledSus = 0;
        FilledInc = 0;
        FilledVic = 0;
    }

    public static int mt = 0;
    public static int mb = 0;
    public static int ms = 0;
    public static int me = 0;

    public static int rd = 0;

    public static String[] personal_data = {"","","","",""};
    public static String st = "";
    public static String email = "";

    public static void saveList(){


    }
}
