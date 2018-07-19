package com.slack.joiple.a2018_busan_ict_hackerton_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class NfcTagging extends AppCompatActivity {
    private Intent cardService;
    private Button cancelBtn;
    @Override
    protected void onCreate(Bundle saveInstanceBundle){
        super.onCreate(saveInstanceBundle);
        this.setContentView(R.layout.activity_nfc_tagging);
        cancelBtn=findViewById(R.id.cancelBtn);
        cardService=new Intent(this,NfcService.class);
        startService(cardService);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(cardService);
    }
}
