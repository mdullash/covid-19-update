package com.example.covid_19update.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covid_19update.R;
import com.squareup.picasso.Picasso;

public class CountryDetailsActivity extends AppCompatActivity {

    private ImageView countryFlag;
    private TextView scases,srecovered,sdeath,scrtical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);

        this.setTitle("Covid-19 Country");

        countryFlag = findViewById(R.id.scountryFlag);
        scases = findViewById(R.id.stotalCasesTV);
        srecovered = findViewById(R.id.stotalRecoveredTV);
        sdeath = findViewById(R.id.stotalDeathTV);
        scrtical = findViewById(R.id.scriticalTV);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String cname = extras.getString("cname");
            String ccase = extras.getString("ccase");
            String crecovered = extras.getString("crecovered");
            String cdeath = extras.getString("cdeath");
            String ccritical = extras.getString("ccritical");
            String cimage = extras.getString("cimage");

            Picasso.get().load(cimage).into(countryFlag);
            scases.append(ccase);
            srecovered.append(crecovered);
            sdeath.append(cdeath);
            scrtical.append(ccritical);
        }
    }
}
