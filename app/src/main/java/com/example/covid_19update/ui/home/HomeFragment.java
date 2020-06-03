package com.example.covid_19update.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.covid_19update.activity.CoronaWorldWebActivity;
import com.example.covid_19update.R;
import com.example.covid_19update.activity.BangladeshDetailsActivity;
import com.example.covid_19update.activity.BlogActivity;
import com.example.covid_19update.activity.CountryListActivity;
import com.example.covid_19update.helper.PrefManager;
import com.example.covid_19update.model.WorldBd;
import com.example.covid_19update.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    //private SwipeRefreshLayout linearLayout;
    private Button otherCountry,coronaBlog;
    private PrefManager prefManager;
    private LinearLayout linearLayout,bdLayout;
    private TextView worldCoronaWeb;

    public static final String BASE_URL = "http://corona.lmao.ninja/v2/";
    private ApiService apiService;
    private TextView totalCases,totalRecovered,totalDeath,critical,totalCasesBd,totalRecoveredBd,totalDeathBd,criticalBd;


    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        linearLayout = root.findViewById(R.id.fr_home);
        //linearLayout.setColorSchemeResources(R.color.colorAccent);

//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mSwipeRefreshLayout.setRefreshing(true);
//                    }
//                }, 2000);
//            }
//        });
        //Toast.makeText(getContext(),FirebaseApp.getInstance().getOptions().getProjectId(),Toast.LENGTH_SHORT).show();
        otherCountry = root.findViewById(R.id.otherCountryBtn);
        worldCoronaWeb = root.findViewById(R.id.worldCoronaWebTv);

        worldCoronaWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CoronaWorldWebActivity.class);
                intent.putExtra("url",worldCoronaWeb.getText().toString());
                startActivity(intent);
            }
        });
        otherCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CountryListActivity.class);
                startActivity(intent);
            }
        });
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        bdLayout = root.findViewById(R.id.bdLayout);
        coronaBlog = root.findViewById(R.id.corolaBlogBtn);
        totalCases = root.findViewById(R.id.totalCasesTV);
        totalRecovered = root.findViewById(R.id.totalRecoveredTV);
        totalDeath = root.findViewById(R.id.totalDeathTV);
        critical = root.findViewById(R.id.criticalTV);
        totalCasesBd = root.findViewById(R.id.totalCasesBdTV);
        totalRecoveredBd = root.findViewById(R.id.totalRecoveredBdTV);
        totalDeathBd = root.findViewById(R.id.totalBdDeathTV);
        criticalBd = root.findViewById(R.id.criticalBdTV);

        bdLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), BangladeshDetailsActivity.class);
                startActivity(intent);
            }
        });

        coronaBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), BlogActivity.class);
                startActivity(intent);
            }
        });

        prefManager = new PrefManager(getContext());
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

        country.enqueue(new Callback<WorldBd>() {
            @Override
            public void onResponse(Call<WorldBd> call, Response<WorldBd> response) {
                if(response.code() == 200) {
                    totalCases.append(response.body().getCases()+"");
                    totalRecovered.append(response.body().getRecovered()+"");
                    totalDeath.append(response.body().getDeaths()+"");
                    critical.append(response.body().getCritical()+"");
                }
            }

            @Override
            public void onFailure(Call<WorldBd> call, Throwable t) {
                Toast.makeText(getContext(),"Failed To Load",Toast.LENGTH_SHORT).show();
            }
        });
        bd.enqueue(new Callback<WorldBd>() {
            @Override
            public void onResponse(Call<WorldBd> call, Response<WorldBd> response) {
                if(response.code() == 200) {
                    totalCasesBd.append(response.body().getCases()+"");
                    totalRecoveredBd.append(response.body().getRecovered()+"");
                    totalDeathBd.append(response.body().getDeaths()+"");
                    criticalBd.append(response.body().getCritical()+"");
                }
            }

            @Override
            public void onFailure(Call<WorldBd> call, Throwable t) {
            }
        });

        return root;
    }
}
