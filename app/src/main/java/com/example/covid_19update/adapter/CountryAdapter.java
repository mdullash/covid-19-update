package com.example.covid_19update.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.covid_19update.R;
import com.example.covid_19update.activity.CountryDetailsActivity;
import com.example.covid_19update.model.Countries;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CountryAdapter extends ArrayAdapter<Countries> implements Filterable, Comparator<Countries> {
    private LinearLayout linearLayout,bdDetailsLayout;
    private final Activity context;
    private final List<Countries> countries;
    private final List<Countries> countriesCopy;
    private TextView countryName;
    private ImageView imageView;

    public CountryAdapter(Activity context, List<Countries> countries) {
        super(context, R.layout.countrylist,countries);
        this.context = context;
        this.countries = countries;
        countriesCopy = new ArrayList<>(countries);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View row = inflater.inflate(R.layout.countrylist, parent, false);

        linearLayout = row.findViewById(R.id.linearLayoutId);
        bdDetailsLayout = row.findViewById(R.id.bdDetailsLayout);
        imageView = row.findViewById(R.id.countryImage);
        countryName = row.findViewById(R.id.countryNameTv);

        countryName.setText(countries.get(position).getCountry());
        Picasso.get().load(countries.get(position).getCountryInfo().getFlag()).into(imageView);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CountryDetailsActivity.class);
                intent.putExtra("cname",countries.get(position).getCountry());
                intent.putExtra("ccase",countries.get(position).getCases());
                intent.putExtra("crecovered",countries.get(position).getRecovered());
                intent.putExtra("cdeath",countries.get(position).getDeaths());
                intent.putExtra("ccritical",countries.get(position).getCritical());
                intent.putExtra("cimage",countries.get(position).getCountryInfo().getFlag());
                context.startActivity(intent);
            }
        });

        return row;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Countries> filteredList = new ArrayList<>();
            if(constraint == null || constraint.length()==0) {
                filteredList.addAll(countriesCopy);
            }
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Countries country : countriesCopy) {
                    if (country.getCountry().toLowerCase().contains(filterPattern)) {
                        filteredList.add(country);
                    }
                }
            }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;

                return filterResults;
        }


        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            countries.clear();
            countries.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    @Override
    public int compare(Countries o1, Countries o2) {
        return o1.getDeaths().compareTo(o2.getDeaths());
    }
}
