package com.example.covid_19update.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayout linearLayout;
    private ApiService apiService;
    private PrefManager prefManager;
    private TextView webLink,totalCases,todayCases,totalRecovered,totalDeath,todayDeath,critical,updated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bangladesh_details);
        this.setTitle("Bangladesh");

        swipeRefreshLayout = findViewById(R.id.refreshLayout);
        linearLayout = findViewById(R.id.bdDetailsLayout);
        totalCases = findViewById(R.id.totalCasesBdTV);
        todayCases = findViewById(R.id.todayCasesBdTV);
        totalRecovered = findViewById(R.id.totalRecoveredBdTV);
        totalDeath = findViewById(R.id.totalBdDeathTV);
        todayDeath = findViewById(R.id.todayBdDeathTV);
        critical = findViewById(R.id.criticalBdTV);
        updated = findViewById(R.id.lastUpdatedBdTV);
        webLink = findViewById(R.id.webLinkTv);

        loadBdInfo();

        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadBdInfo();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

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
    }

    private void loadBdInfo() {
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
                    totalCases.setText(response.body().getCases()+"");
                    todayCases.setText(response.body().getTodayCases()+"");
                    totalRecovered.setText(response.body().getRecovered()+"");
                    totalDeath.setText(response.body().getDeaths()+"");
                    todayDeath.setText(response.body().getTodayDeaths()+"");
                    critical.setText(response.body().getCritical()+"");
                    // Creating date format
                    DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
                    // Creating date from milliseconds
                    // using Date() constructor
                    Date updatedDate = new Date(response.body().getUpdated());
                    // Formatting Date according to the
                    // given format
                    updated.setText(simple.format(updatedDate)+"");
                }
            }

            @Override
            public void onFailure(Call<WorldBd> call, Throwable t) {
                Toast.makeText(BangladeshDetailsActivity.this,"Failed to load",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
