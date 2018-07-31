package com.slack.joiple.a2018_busan_ict_hackerton_android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Intent o,g;
    Button register;
    ImageButton option,start,end,status,logout,menu,logofont;
    LinearLayout attLayout;
    TextView[] times, events;
    SharedPreferences user, pref, settings;
    SharedPreferences.Editor editor;
    LinearLayout holder;
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //from mainactivity
        user=getSharedPreferences("user",MODE_PRIVATE);
        g=getIntent();
        register=findViewById(R.id.changeCompBtn);
        start=findViewById(R.id.workingBtn);
        end=findViewById(R.id.stopworkingBtn);
        option=findViewById(R.id.statusBtn);

        logout=findViewById(R.id.logoutBtn);
        menu=findViewById(R.id.slideBtn);
        logofont=findViewById(R.id.logofontBtn);
        loadItems();
        times=new TextView[5];
        events=new TextView[5];
        SharedPreferences record=getSharedPreferences("record",MODE_PRIVATE);
        int N=record.getInt("number",0);
        for(int i=0;i<N;i++){
            LinearLayout tmpLayout=new LinearLayout(this);
            tmpLayout.setOrientation(LinearLayout.HORIZONTAL);
            tmpLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            times[i] = new TextView(this);
            times[i].setText(record.getString("time" + i, "null"));
            times[i].setGravity(Gravity.START);
            times[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
            events[i] = new TextView(this);
            events[i].setText(record.getString("event" + i, "null"));
            events[i].setGravity(Gravity.END);
            events[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
            tmpLayout.addView(times[i]);
            tmpLayout.addView(events[i]);
            attLayout.addView(tmpLayout);
        }
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                o = new Intent(MainActivityDrawer.this, CompanyChanger.class);
                startActivityForResult(o, 0);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor = user.edit();
                editor.putBoolean("isUsing", false);
                editor.commit();
                o = new Intent(MainActivityDrawer.this, SignIn.class);
                startActivity(o);
                finish();
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (settings.getBoolean("mode", true)) {
                    o = new Intent(MainActivityDrawer.this, QrReader.class);
                    o.setAction("start");
                } else {
                    o = new Intent(MainActivityDrawer.this, NfcTagging.class);
                    o.setAction("start");
                }
                startActivity(o);
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (settings.getBoolean("mode", true)) {
                    o = new Intent(MainActivityDrawer.this, QrReader.class);
                    o.setAction("end");
                } else {
                    o = new Intent(MainActivityDrawer.this, NfcTagging.class);
                    o.setAction("end");
                }
                startActivity(o);
            }
        });
        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                o = new Intent(MainActivityDrawer.this, Option.class);
                startActivityForResult(o, 0);
            }
        });
        SharedPreferences spec=getSharedPreferences("spec",MODE_PRIVATE);
        SharedPreferences.Editor editor=spec.edit();
        if(spec.getInt("number",-1)==-1) {
            editor.putInt("number", 2);
            editor.putInt("type0", 0);
            editor.putString("name0", "토익");
            editor.putString("date0", "2018.01.01~2020.01.01");
            editor.putString("data0", "990점");
            editor.putInt("type1", 1);
            editor.putString("name1", "부산 ICT 해카톤");
            editor.putString("date1", "2018.07.29~2018.08.01");
            editor.putString("data1", "대상");
            editor.putInt("type2", 2);
            editor.putString("name2", "기상청");
            editor.putString("date2", "2018.01.02");
            editor.putString("data2", "주소:duties/weather");
            editor.commit();
        }
        for(int i=0;i<spec.getInt("number",0);i++){

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            long tempTime = System.currentTimeMillis();
            long intervalTime = tempTime - backPressedTime;

            if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
                super.onBackPressed();
            } else {
                backPressedTime = tempTime;
                Toast.makeText(getApplicationContext(), "종료하실려면 한번더 뒤로가기를 누르세요.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int request, int result, Intent data) {
        if (result == RESULT_CANCELED) return;
        String action = data.getAction();
        if (action.equals("logout")) {
            o = new Intent(MainActivityDrawer.this, SignIn.class);
            startActivity(o);
            finish();
        } else if (action.equals("changeComp")) {

        } else if (action.equals("end")) {//TODO check why I set this in else if
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_drawer, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_attend) {
            // Handle the camera action
        } else if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_pay) {

        } else if (id == R.id.nav_spec) {

        } else if (id == R.id.nav_act) {

        } else if (id == R.id.nav_etc) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void loadItems(){
        pref=getSharedPreferences("user",MODE_PRIVATE);
        settings=getSharedPreferences("settings",MODE_PRIVATE);
        String companyName=pref.getString("company","null");
    }
    class CustCard extends LinearLayout{
        public CustCard(Context c){
            super(c);
            this.setOrientation(HORIZONTAL);
            LinearLayout tmpLin=new LinearLayout(c);
            tmpLin.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,1));
            tmpLin.setOrientation(VERTICAL);
            typeV=new TextView(c,null,R.style.custCardType);
            this.addView(typeV);
            this.addView(tmpLin);
            subject=new TextView(c,null,R.style.custCardItem);
            tmpLin.addView(subject);
            date=new TextView(c,null,R.style.custCardItem);
            tmpLin.addView(date);
            data=new TextView(c,null,R.style.custCardItem);
            tmpLin.addView(data);
        }
        int type=0;
        TextView typeV,subject,date,data;
        public void setdata(int type,String subject,String date,String data){
            switch(type){
                default:
                    typeV.setText("기타");
                    break;
            }
            this.subject.setText(subject);
            this.date.setText(date);
            this.data.setText(data);
        }
    }
}
