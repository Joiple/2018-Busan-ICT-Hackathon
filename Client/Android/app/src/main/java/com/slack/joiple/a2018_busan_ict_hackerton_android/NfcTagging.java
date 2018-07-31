package com.slack.joiple.a2018_busan_ict_hackerton_android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NfcTagging extends AppCompatActivity {
    private Button cancelBtn,qrModeBtn;
    Intent o,g;
    @Override
    protected void onCreate(Bundle saveInstanceBundle){
        super.onCreate(saveInstanceBundle);
        this.setContentView(R.layout.activity_nfc_tagging);
        g=getIntent();
        cancelBtn=findViewById(R.id.cancelBtn);
        qrModeBtn=findViewById(R.id.qrModeBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        qrModeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                o=new Intent(NfcTagging.this,QrReader.class);
                o.setAction(g.getAction());
                startActivity(o);
                NfcTagging.this.finish();
            }
        });
    }
    @Override
    public boolean onKeyDown(int keycode, KeyEvent event)
    {
        if(keycode==KeyEvent.KEYCODE_VOLUME_DOWN)
        {
            Toast.makeText(this,"success!", Toast.LENGTH_SHORT).show();
            SharedPreferences rec=getSharedPreferences("record",MODE_PRIVATE);
            int num=rec.getInt("number",0);
            SharedPreferences.Editor editor=rec.edit();
            num+=1;
            editor.putInt("number",num);
            editor.putString("event"+num, getIntent().getAction());
            editor.putString("time"+num,new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date(System.currentTimeMillis())));
            editor.commit();
            setResult(RESULT_OK);
            finish();
        }
        return true;
    }
}
