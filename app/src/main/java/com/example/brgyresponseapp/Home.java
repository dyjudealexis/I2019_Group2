package com.example.brgyresponseapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Home extends AppCompatActivity {
    private RequestQueue queue;

    BottomNavigationView bottomNavigationView;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        queue = Volley.newRequestQueue(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new HomeF()).commit();

        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment = null;

            switch (item.getItemId()){
                case R.id.homeM:
                    fragment = new HomeF();
                    break;
                case R.id.adminM:
                    fragment = new AdminF();
                    break;
                case R.id.moreM:
                    fragment = new MoreF();
                    break;
            }

            assert fragment != null;
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).commit();
            return true;

            //token: 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
        });

        jsonParse();
        jsonParse1();
    }

    public void click_showMap(View view) {

        Intent intent = new Intent(Home.this, ShowMap.class);
        startActivity(intent);
        finish();

        FormData.showOnly=false;


    }

    public void click_showForm(View view) {
        FormData.ClearData();

        Intent intent = new Intent(Home.this, SusData.class);
        startActivity(intent);
        finish();
    }

    public void click_showList(View view) {

        Intent intent = new Intent(Home.this, ResponseList.class);
        startActivity(intent);
        finish();
    }

    public void gotoPI(View view) {

        Intent intent = new Intent(Home.this, ProfileInfo.class);
        startActivity(intent);
        finish();
    }

    public void logoutNow(View view) {
        Toast.makeText(Home.this, "Logged out successfully.", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(Home.this, Login.class);
        startActivity(intent);
        finish();
    }

    private void jsonParse() {

        String url = "http://"+Final_IP.IP_ADDRESS+"/br/getbyemail.php?email="+FormData.email;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray jsonArray = jsonObject.getJSONArray("users");

                        JSONObject object = jsonArray.getJSONObject(0);
                        String id = object.getString("id");
                        FormData.personal_data[0] =id;
                        String em = object.getString("email");
                        FormData.personal_data[3] =em;
                        String pw = object.getString("password");
                        FormData.personal_data[4] =pw;
                        String fn = object.getString("fname");
                        FormData.personal_data[1] =fn;
                        String add = object.getString("address");
                        FormData.personal_data[2] =add;
                        FormData.from =add;

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Home.this, "Invalid action.", Toast.LENGTH_SHORT).show();

            }
        });

        queue.add(request);
        super.onStart();
    }

    private void jsonParse1() {

        String url = "http://"+Final_IP.IP_ADDRESS+"/br/getbyentry.php";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray jsonArray = jsonObject.getJSONArray("inc_data");

                    JSONObject object = jsonArray.getJSONObject(0);
                    String id = object.getString("inc_en");
                    FormData.incident_data[2] =id;

                    int p1 = Integer.parseInt(FormData.incident_data[2]) + 1;


                    FormData.incdata_en = p1;

                    //Toast.makeText(Home.this, FormData.incident_data[2], Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Home.this, "Invalid action.", Toast.LENGTH_SHORT).show();

            }
        });

        queue.add(request);
        super.onStart();
    }
}