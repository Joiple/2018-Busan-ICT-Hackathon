package com.slack.joiple.a2018_busan_ict_hackerton_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class NfcTagging extends AppCompatActivity {
    private Intent cardService;
    @Override
    protected void onCreate(Bundle saveInstanceBundle){
        super.onCreate(saveInstanceBundle);
        this.setContentView(R.layout.activity_nfc_tagging);
        cardService=new Intent(this,NfcService.class);
        startService(cardService);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(cardService);
    }
}
