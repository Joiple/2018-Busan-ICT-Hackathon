package com.slack.joiple.a2018_busan_ict_hackerton_android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.Constraints;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Intent o,g;
    Button register,start,end,option,profile,recordDetail;
    TextView company;
    LinearLayout attLayout;
    TextView[] times,events;
    SharedPreferences pref,settings;
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;
    @Override
    public void onCreate(Bundle saveInstanceBundle){
        super.onCreate(saveInstanceBundle);
        this.setContentView(R.layout.activity_main);
        g=getIntent();
        register=findViewById(R.id.changeCompBtn);
        start=findViewById(R.id.workBtn);
        end=findViewById(R.id.offBtn);
        profile=findViewById(R.id.profileBtn);
        option=findViewById(R.id.optionBtn);
        company=findViewById(R.id.companyView);
        recordDetail=findViewById(R.id.recordBtn);
        attLayout=findViewById(R.id.recordLayout);
        loadItems();
        times=new TextView[5];
        events=new TextView[5];
        NetworkManager nm=new NetworkManager(getString(R.string.serverURL),"post");//TODO change action properly for getting attendance records
        nm.in.addItem("number","5");
        //nm.execute();
        for(int i=0;i<5;i++){
            LinearLayout tmpLayout=new LinearLayout(this);
            tmpLayout.setOrientation(LinearLayout.HORIZONTAL);
            tmpLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            times[i]=new TextView(this);
            times[i].setText("times"+i);
            times[i].setGravity(Gravity.START);
            times[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,1));
            events[i]=new TextView(this);
            events[i].setText("events"+i);
            events[i].setGravity(Gravity.END);
            events[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,1));
            tmpLayout.addView(times[i]);
            tmpLayout.addView(events[i]);
            attLayout.addView(tmpLayout);
        }
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                o=new Intent(MainActivity.this,CompanyChanger.class);
                startActivityForResult(o,0);
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(settings.getBoolean("mode",true)){
                    o=new Intent(MainActivity.this,QrReader.class);
                    o.setAction("start");
                }else{
                    o=new Intent(MainActivity.this,NfcTagging.class);
                    o.setAction("start");
                }
                startActivity(o);
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(settings.getBoolean("mode",true)){
                    o=new Intent(MainActivity.this,QrReader.class);
                    o.setAction("end");
                }else{
                    o=new Intent(MainActivity.this,NfcTagging.class);
                    o.setAction("end");
                }
                startActivity(o);
            }
        });
        recordDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                o=new Intent(MainActivity.this,AttendanceView.class);
                startActivity(o);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                o=new Intent(MainActivity.this,Profile.class);
                startActivity(o);
            }
        });
        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                o=new Intent(MainActivity.this,Option.class);
                startActivityForResult(o,0);
            }
        });
    }
    @Override
    protected void onActivityResult(int request,int result,Intent data){
        if(result==RESULT_CANCELED)return;
        String action=data.getAction();
        if(action.equals("logout")){
            o=new Intent(MainActivity.this,SignIn.class);
            startActivity(o);
            finish();
        }else if(action.equals("changeComp")){
            loadItems();
        }else if(action.equals("end")){//TODO check why I set this in else if
        }
    }
    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime)
        {
            super.onBackPressed();
        }
        else
        {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "종료하실려면 한번더 뒤로가기를 누르세요.", Toast.LENGTH_SHORT).show();
        }
    }
    public void loadItems(){
        pref=getSharedPreferences("user",MODE_PRIVATE);
        settings=getSharedPreferences("settings",MODE_PRIVATE);
        String companyName=pref.getString("company","null");
        company.setText(companyName);
        if(companyName.equals("null")){
            register.setVisibility(View.VISIBLE);
            findViewById(R.id.attendance).setVisibility(View.GONE);
        }else{
            register.setVisibility(View.GONE);
            findViewById(R.id.attendance).setVisibility(View.VISIBLE);
        }
    }


}
