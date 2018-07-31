package com.slack.joiple.a2018_busan_ict_hackerton_android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.transition.TransitionManager;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class ProfileActivityDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Button changeComp,editButton,okBtn,cancelBtn,compProfileBtn;
    TextView nameView,companyView,birthdayView,phoneView,emailView,wageView;
    EditText nameEdit,phoneEdit,emailEdit,wageEdit;
    ImageButton logout, menu, option, logofont;
    DatePicker birthdayEdit;
    Intent o,g;
    SharedPreferences user,pref;
    SharedPreferences.Editor editor;

    boolean isEditing=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        user=getSharedPreferences("user",MODE_PRIVATE);
        nameView=findViewById(R.id.nameView);
        companyView=findViewById(R.id.companyView);
        birthdayView=findViewById(R.id.birthView);
        phoneView=findViewById(R.id.phoneView);
        emailView=findViewById(R.id.emailView);
        wageView=findViewById(R.id.wageView);
        nameEdit=findViewById(R.id.nameEdit);
        logout=findViewById(R.id.logoutBtn);
        menu=findViewById(R.id.slideBtn);
        option=findViewById(R.id.statusBtn);
        logofont=findViewById(R.id.logofontBtn);
        birthdayEdit=findViewById(R.id.birthEdit);
        phoneEdit=findViewById(R.id.phoneEdit);
        emailEdit=findViewById(R.id.emailEdit);
        wageEdit=findViewById(R.id.wageEdit);
        editButton=findViewById(R.id.editBtn);
        okBtn=findViewById(R.id.modBtn);

        cancelBtn=findViewById(R.id.cancelModBtn);
        changeComp=findViewById(R.id.changeCompBtn);
        compProfileBtn=findViewById(R.id.compProfileBtn);
        changeComp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                o=new Intent(ProfileActivityDrawer.this,CompanyChanger.class);
                startActivityForResult(o,0);
            }
        });
        pref=getSharedPreferences("user",MODE_PRIVATE);
        loadText();
        NetworkManager nw=new NetworkManager(getString(R.string.serverURL),"getProfile");
        nw.in.addItem("id",pref.getString("id","null"));
        nw.in.addItem("password",pref.getString("password","null"));
        editButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                TransitionManager.beginDelayedTransition((ViewGroup)findViewById(R.id.profileLayout));
                if(isEditing){
                    nameView.setVisibility(VISIBLE);
                    birthdayView.setVisibility(VISIBLE);
                    phoneView.setVisibility(VISIBLE);
                    emailView.setVisibility(VISIBLE);
                    wageView.setVisibility(VISIBLE);
                    nameEdit.setVisibility(GONE);
                    birthdayEdit.setVisibility(GONE);
                    phoneEdit.setVisibility(GONE);
                    emailEdit.setVisibility(GONE);
                    wageEdit.setVisibility(GONE);
                    okBtn.setVisibility(GONE);
                    cancelBtn.setVisibility(GONE);
                    view.setVisibility(VISIBLE);
                    isEditing=false;
                }else {
                    nameView.setVisibility(GONE);
                    birthdayView.setVisibility(GONE);
                    phoneView.setVisibility(GONE);
                    emailView.setVisibility(GONE);
                    wageView.setVisibility(GONE);
                    nameEdit.setVisibility(VISIBLE);
                    birthdayEdit.setVisibility(VISIBLE);
                    phoneEdit.setVisibility(VISIBLE);
                    emailEdit.setVisibility(VISIBLE);
                    wageEdit.setVisibility(VISIBLE);
                    okBtn.setVisibility(VISIBLE);
                    cancelBtn.setVisibility(VISIBLE);
                    view.setVisibility(GONE);
                    isEditing=true;
                }
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor= user.edit();
                editor.putBoolean("isUsing",false);
                editor.commit();
                o=new Intent(ProfileActivityDrawer.this,SignIn.class);
                startActivity(o);
                finish();
            }
        });
        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                o=new Intent(ProfileActivityDrawer.this,Option.class);
                startActivityForResult(o,0);
            }
        });
        logofont.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                o=new Intent(ProfileActivityDrawer.this,MainActivityDrawer.class);
                startActivityForResult(o,0);
                setResult(RESULT_OK);
                finish();

            }
        });

        compProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                o=new Intent(ProfileActivityDrawer.this,CompanyProfile.class);
                startActivityForResult(o,10);
            }
        });
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=pref.edit();
                String name=nameEdit.getText().toString();
                String birth=birthdayEdit.getYear()+"-"+(birthdayEdit.getMonth()+1)+"-"+birthdayEdit.getDayOfMonth();
                String email=emailEdit.getText().toString(),wage=wageEdit.getText().toString(),phone=phoneEdit.getText().toString();
                editor.putString("name",name);
                editor.putString("birth",birth);
                editor.putString("email",email);
                editor.putString("wage",wage);
                editor.putString("phone",phone);
                editor.putBoolean("isUsing",true);
                editor.commit();
                cancelBtn.performClick();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadText();
                editButton.performClick();
            }
        });
    }
    public void loadText(){
        nameView.setText(pref.getString("name","null"));

        birthdayView.setText(pref.getString("birth","null"));
        phoneView.setText(pref.getString("phone","null"));
        emailView.setText(pref.getString("email","null"));
        wageView.setText(pref.getString("wage","null"));
        nameEdit.setText(pref.getString("name","null"));
        phoneEdit.setText(pref.getString("phone","null"));
        emailEdit.setText(pref.getString("email","null"));
        wageEdit.setText(pref.getString("wage","null"));
        String date=pref.getString("birth","1900-01-01");
        String[] dateField=date.split("-");
        birthdayEdit.updateDate(Integer.valueOf(dateField[0]),Integer.valueOf(dateField[1])-1,Integer.valueOf(dateField[2]));
        SharedPreferences comp=getSharedPreferences("comp",MODE_PRIVATE);
        if(comp.getBoolean("isHost",false)){
            companyView.setText(comp.getString("name","null"));
            compProfileBtn.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    protected void onActivityResult(int request,int result,Intent data){
        if(result==RESULT_CANCELED)return;
        loadText();
        switch(request){
            case 0:
                if(result==RESULT_OK) finish();
                break;
        }
    }
}
