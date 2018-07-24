package com.slack.joiple.a2018_busan_ict_hackerton_android;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QrReader extends AppCompatActivity {
    //view Objects
    private Button rescanBtn,sendBtn, nfcBtn,cancelBtn;
    private TextView infoText;
    private String value;
    //qr code scanner object
    private IntentIntegrator qrScan;
    private Intent g;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_reader);
        g=getIntent();
        rescanBtn=findViewById(R.id.rescanBtn);
        sendBtn=findViewById(R.id.sendBtn);
        nfcBtn =findViewById(R.id.nfcModeBtn);
        infoText=findViewById(R.id.infoView);
        cancelBtn=findViewById(R.id.cancelBtn);
        //intializing scan object
        qrScan = new IntentIntegrator(this);
        //button onClick
        //scan option
        sendBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                NetworkManager nw=new NetworkManager(getString(R.string.serverURL));

                //TODO send to server
            }
        });
        nfcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(QrReader.this,NfcTagging.class);
                i.setAction(g.getAction());
                startActivity(i);
                QrReader.this.finish();
            }
        });
        rescanBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                qrScan.setOrientationLocked(false);
                qrScan.initiateScan();

            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        qrScan.setPrompt(getString(R.string.scanning));
        qrScan.initiateScan();
    }

    @Override
    public void onBackPressed(){
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }
    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //qrcode 가 없으면
            if (result.getContents() == null) {
                Toast.makeText(QrReader.this, "failed!", Toast.LENGTH_SHORT).show();
            } else {
                //qrcode 결과가 있으면

                value=result.getContents();
                //TODO send starting work data to server with type(g.getAction())
                if(true) {//result
                    Toast.makeText(QrReader.this,"success!", Toast.LENGTH_SHORT).show();
                }
                setResult(RESULT_OK);
                finish();
                /*
                try {
                    //data를 json으로 변환
                    JSONObject obj = new JSONObject(result.getContents());
                    obj.get("value");
                } catch (JSONException e) {
                    e.printStackTrace();
                    //Toast.makeText(QrReader.this, result.getContents(), Toast.LENGTH_LONG).show();
                }
                */
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}