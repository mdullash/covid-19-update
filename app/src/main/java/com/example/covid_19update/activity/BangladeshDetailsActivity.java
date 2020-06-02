package com.example.covid_19update.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.covid_19update.R;
import com.example.covid_19update.helper.PrefManager;
import com.example.covid_19update.model.WorldBd;
import com.example.covid_19update.service.ApiService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BangladeshDetailsActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://corona.lmao.ninja/v2/";
    private LinearLayout linearLayout;
    private ApiService apiService;
    private ImageView cflag;
    private PrefManager prefManager;
    private TextView webLink,continent,country,population,totalCases,todayCases,totalRecovered,totalDeath,todayDeath,critical,updated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bangladesh_details);

        linearLayout = findViewById(R.id.bdDetailsLayout);
        cflag = findViewById(R.id.bdFlag);
        continent = findViewById(R.id.continentTv);
        totalCases = findViewById(R.id.totalCasesBdTV);
        todayCases = findViewById(R.id.todayCasesBdTV);
        totalRecovered = findViewById(R.id.totalRecoveredBdTV);
        totalDeath = findViewById(R.id.totalBdDeathTV);
        todayDeath = findViewById(R.id.todayBdDeathTV);
        critical = findViewById(R.id.criticalBdTV);
        updated = findViewById(R.id.lastUpdatedBdTV);
        webLink = findViewById(R.id.webLinkTv);

        webLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BangladeshDetailsActivity.this, CoronaBdWebActivity.class);
                intent.putExtra("url",webLink.getText().toString());
                startActivity(intent);
            }
        });

        prefManager = new PrefManager(this);
        String config = prefManager.get("config","color");

        if(config.equals("Color.BLACK")) {
            linearLayout.setBackgroundColor(Color.BLACK);
        }
        else {
            linearLayout.setBackgroundColor(Color.WHITE);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
        //for world
        Call<WorldBd> country= apiService.getWorldInfo();
        //for Bd
        Call<WorldBd> bd = apiService.getBdInfo();

        bd.enqueue(new Callback<WorldBd>() {
            @Override
            public void onResponse(Call<WorldBd> call, Response<WorldBd> response) {
                if(response.code() == 200) {
                    //Picasso.get().load(response.body().g).into(cflag);
                    //continent.append(response.body().getcon);
                    totalCases.append(response.body().getCases()+"");
                    todayCases.append(response.body().getTodayCases()+"");
                    totalRecovered.append(response.body().getRecovered()+"");
                    totalDeath.append(response.body().getDeaths()+"");
                    todayDeath.append(response.body().getTodayDeaths()+"");
                    critical.append(response.body().getCritical()+"");
                    // Creating date format
                    DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
                    // Creating date from milliseconds
                    // using Date() constructor
                    Date updatedDate = new Date(response.body().getUpdated());
                    // Formatting Date according to the
                    // given format
                    updated.append(simple.format(updatedDate)+"");
                }
            }

            @Override
            public void onFailure(Call<WorldBd> call, Throwable t) {
            }
        });

    }
}
