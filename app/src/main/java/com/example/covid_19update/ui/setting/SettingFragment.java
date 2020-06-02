package com.example.covid_19update.ui.setting;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.covid_19update.R;
import com.example.covid_19update.helper.PrefManager;

public class SettingFragment extends Fragment {

    private SettingViewModel settingViewModel;
    private Switch sw;
    private PrefManager prefManager;
    private LinearLayout linearLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingViewModel =
                ViewModelProviders.of(this).get(SettingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_setting, container, false);
//        final TextView textView = root.findViewById(R.id.text_slideshow);
//        settingViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        linearLayout = root.findViewById(R.id.fr_setting);
        prefManager = new PrefManager(getContext());
        final String config = prefManager.get("config","color");
        sw = (Switch) root.findViewById(R.id.theme);

        if(config.equals("Color.BLACK")) {
            sw.setChecked(true);
            linearLayout.setBackgroundColor(Color.BLACK);
            sw.setTextColor(Color.WHITE);
            prefManager.set("config","color","Color.BLACK");
        }
        else {
            sw.setChecked(false);
            linearLayout.setBackgroundColor(Color.WHITE);
            sw.setTextColor(Color.BLACK);
            prefManager.set("config","color","Color.WHITE");
        }

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //Toast.makeText(getContext(), isChecked+"", Toast.LENGTH_SHORT).show();

                    getView().setBackgroundColor(Color.BLACK);
                    sw.setTextColor(Color.WHITE);
                    prefManager.set("config","color","Color.BLACK");

                } else {
                    getView().setBackgroundColor(Color.WHITE);
                    sw.setTextColor(Color.BLACK);
                    prefManager.set("config","color","Color.WHITE");
                }
            }
        });
        return root;
    }
}
