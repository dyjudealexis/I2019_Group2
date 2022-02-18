package com.example.brgyresponseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ResponseList extends AppCompatActivity {

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response_list);

        String url = "http://"+Final_IP.IP_ADDRESS+"/reslist1/";

        WebView view = (WebView) this.findViewById(R.id.showList);

        view.loadUrl(url);
        view.getSettings().setJavaScriptEnabled(true);

        view.addJavascriptInterface(new JavaScriptInterface(ResponseList.this),"Android");

        //if(FormData.rd == 1){
            //Toast.makeText(ResponseList.this,"Accepted", Toast.LENGTH_SHORT).show();
        //}
    }

    public class JavaScriptInterface {
        Context mContext;

        /** Instantiate the interface and set the context */
        JavaScriptInterface(Context c) {
            mContext = c;
        }

        /** Show a toast from the web page */
        @JavascriptInterface
        public void showToast(String toast, int id) {
            //FormData.rd = Integer.parseInt(toast);
            //Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();

            FormData.to = toast;
            FormData.incdata_id = id;
            FormData.showOnly = true;

            Intent intent = new Intent(ResponseList.this, ShowMap.class);
            startActivity(intent);
            finish();
        }
    }


}