package com.example.covid_19update.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covid_19update.R;
import com.example.covid_19update.helper.PrefManager;
import com.example.covid_19update.service.ApiService;
import com.example.covid_19update.ui.profile.ProfileFragment;
import com.example.covid_19update.ui.setting.SettingFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private PrefManager prefManager;
    private TextView name,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                view.setBackgroundColor(0x000);
//            }
//        });
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_about,R.id.nav_setting,R.id.nav_profile)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        prefManager = new PrefManager(this);
        //final String config = prefManager.get("config","color");
        //name.setText(prefManager.get("profile","name"));
       // name.setText(prefManager.get("profile","email"));
        View headerView = navigationView.getHeaderView(0);
        name = headerView.findViewById(R.id.nameTv);
        email = headerView.findViewById(R.id.emailTv);
        name.setText(prefManager.get("profile","profileName"));
        email.setText(prefManager.get("profile","profileEmail"));

        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View view, float v) {
                final String config = prefManager.get("config","color");
                if(config.equals("Color.BLACK")) {
                    navigationView.setBackgroundColor(Color.BLACK);
                    navigationView.setItemTextColor(ColorStateList.valueOf(Color.WHITE));
                }
                else {
                    navigationView.setBackgroundColor(Color.WHITE);
                    navigationView.setItemTextColor(ColorStateList.valueOf(Color.BLACK));
                }
            }

            @Override
            public void onDrawerOpened(View view) {
                final String config = prefManager.get("config","color");
                if(config.equals("Color.BLACK")) {
                    navigationView.setBackgroundColor(Color.BLACK);
                    navigationView.setItemTextColor(ColorStateList.valueOf(Color.WHITE));
                }
                else {
                    navigationView.setBackgroundColor(Color.WHITE);
                    navigationView.setItemTextColor(ColorStateList.valueOf(Color.BLACK));
                }
            }

            @Override
            public void onDrawerClosed(View view) {
                final String config = prefManager.get("config","color");
                if(config.equals("Color.BLACK")) {
                    navigationView.setBackgroundColor(Color.BLACK);
                }
                else {
                    navigationView.setBackgroundColor(Color.WHITE);
                }
            }

            @Override
            public void onDrawerStateChanged(int i) {
                final String config = prefManager.get("config","color");
                if(config.equals("Color.BLACK")) {
                    navigationView.setBackgroundColor(Color.BLACK);
                }
                else {
                    navigationView.setBackgroundColor(Color.WHITE);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
