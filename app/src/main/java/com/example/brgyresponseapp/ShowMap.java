package com.example.brgyresponseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class ShowMap extends AppCompatActivity {
    Button rj, ac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_map);

        String url = "http://"+Final_IP.IP_ADDRESS+"/maps_p/map1.php?add1="+FormData.from.trim().replace(" ","%20")+"&add2="+
                FormData.to.trim().replace(" ","%20")+"";

        WebView view = (WebView) this.findViewById(R.id.webView);

        view.loadUrl(url);
        view.getSettings().setJavaScriptEnabled(true);

        rj = findViewById(R.id.rjctMap);
        ac = findViewById(R.id.acptMap);

        if(FormData.showOnly==false){
            rj.setEnabled(false);
            ac.setEnabled(false);
        }
        else {
            rj.setEnabled(true);
            ac.setEnabled(true);
        }
    }

    public void bckHm1(View view){
        FormData.showOnly=false;
        Intent intent = new Intent(ShowMap.this, Home.class);
        startActivity(intent);
        finish();
    }

    public void mapAccept(View view){
        String URL = "http://"+Final_IP.IP_ADDRESS+"/br/approval.php?id="+FormData.incdata_id+"&stat=Accepted";

        new ProfileInfo.RequestTask().execute(URL);
        Toast.makeText(ShowMap.this, "Selected List Accepted.", Toast.LENGTH_SHORT).show();

        FormData.showOnly=false;
        Intent intent = new Intent(ShowMap.this, Home.class);
        startActivity(intent);
        finish();
    }
    public void mapReject(View view){
        String URL = "http://"+Final_IP.IP_ADDRESS+"/br/approval.php?id="+FormData.incdata_id+"&stat=Rejected";

        new ProfileInfo.RequestTask().execute(URL);
        Toast.makeText(ShowMap.this, "Selected List Rejected.", Toast.LENGTH_SHORT).show();

        FormData.showOnly=false;
        Intent intent = new Intent(ShowMap.this, Home.class);
        startActivity(intent);
        finish();
    }
}
