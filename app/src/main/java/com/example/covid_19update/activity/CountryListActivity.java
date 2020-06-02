package com.example.covid_19update.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.covid_19update.R;
import com.example.covid_19update.adapter.CountryAdapter;
import com.example.covid_19update.helper.PrefManager;
import com.example.covid_19update.model.Countries;
import com.example.covid_19update.service.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryListActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://corona.lmao.ninja/v2/";
    private EditText searchView;
    private ListView countryListview;
    private CountryAdapter countryAdapter;
    private List<Countries> countriesList;
    private ApiService apiService;
    private PrefManager prefManager;
    private LinearLayout linearlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);

        countryListview = findViewById(R.id.countryList);
        searchView = findViewById(R.id.inputSearch);
        linearlayout = findViewById(R.id.countryListLayout);

        countriesList = new ArrayList<>();
        prefManager = new PrefManager(this);
        String config = prefManager.get("config","color");

        if(config.equals("Color.BLACK")) {
            linearlayout.setBackgroundColor(Color.BLACK);
            searchView.setBackgroundColor(Color.WHITE);
        }
        else {
            linearlayout.setBackgroundColor(Color.WHITE);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
        Call<List<Countries>> countries = apiService.getAllCountry();

        countries.enqueue(new Callback<List<Countries>>() {
            @Override
            public void onResponse(Call<List<Countries>> call, Response<List<Countries>> response) {
                if(response.code() == 200) {
                    countriesList = response.body();
                    countryAdapter = new CountryAdapter(CountryListActivity.this, countriesList);
                    countryListview.setAdapter(countryAdapter);
                }
                //Toast.makeText(CovidCountry.this, countryDetailsList.size()+"",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Countries>> call, Throwable t) {
                Toast.makeText(CountryListActivity.this,"Failed To Load",Toast.LENGTH_SHORT).show();
            }
        });
        searchView.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                countryAdapter.getFilter().filter(cs);
                //Toast.makeText(CovidCountry.this,cs,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });

    }
}
