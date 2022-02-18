package com.example.brgyresponseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class ProfileInfo extends AppCompatActivity {
    private EditText fname, address, email, password;
    String sfname, saddress, semail, spassword;
    private RequestQueue queue;


    private String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_info);

        queue = Volley.newRequestQueue(this);

        fname = findViewById(R.id.profileName);
        address = findViewById(R.id.profileAddress);
        email = findViewById(R.id.profileEmail);
        password = findViewById(R.id.profilePassword);

        fname.setText(FormData.personal_data[1]);
        address.setText(FormData.personal_data[2]);
        email.setText(FormData.personal_data[3]);
        password.setText(FormData.personal_data[4]);
    }

    public static class RequestTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... uri) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response;
            String responseString = null;
            try {
                response = httpclient.execute(new HttpGet(uri[0]));
                StatusLine statusLine = response.getStatusLine();
                if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    response.getEntity().writeTo(out);
                    responseString = out.toString();
                    out.close();
                } else{
                    //Closes the connection.
                    response.getEntity().getContent().close();
                    throw new IOException(statusLine.getReasonPhrase());
                }
            } catch (ClientProtocolException e) {
                //TODO Handle problems..
            } catch (IOException e) {
                //TODO Handle problems..
            }
            return responseString;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //Do anything with response..
        }
    }

    public void profileUpdate(View view){
        sfname = fname.getText().toString().trim().replace(" ","%20");
        saddress = address.getText().toString().trim().replace(" ","%20");;
        semail = email.getText().toString().trim().replace(" ","%20");;
        spassword = password.getText().toString().trim().replace(" ","%20");;

        if(!email.equals("") || !password.equals("") || !fname.equals("") || !address.equals("")){
                URL = "http://"+Final_IP.IP_ADDRESS+"/br/update_info.php?id="+FormData.personal_data[0]+"&email="+semail+"&password="+
                        spassword+"&address="+saddress+"&fname="+sfname+"";

            new RequestTask().execute(URL);

            Toast.makeText(ProfileInfo.this, "Updated Successfully.", Toast.LENGTH_SHORT).show();

            FormData.email = semail;

            Intent intent = new Intent(ProfileInfo.this, Home.class);
            startActivity(intent);
            finish();

        }
        else {
            Toast.makeText(this, "Fields can not be empty!", Toast.LENGTH_SHORT).show();
        }

    }

    public void profileBacktohome(View view) {

        Intent intent = new Intent(ProfileInfo.this, Home.class);
        startActivity(intent);
        finish();
    }

}