package com.example.covid_19update.ui.profile;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.covid_19update.R;
import com.example.covid_19update.helper.PrefManager;

public class ProfileFragment extends Fragment {

    private LinearLayout linearLayout;
    private Switch sw;
    private PrefManager prefManager;
    private EditText nameEt,emailEt;
    private String name,email;
    private Button saveBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
//        final TextView textView = root.findViewById(R.id.text_slideshow);
//        settingViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        linearLayout = root.findViewById(R.id.fr_profile);
        nameEt = root.findViewById(R.id.nameET);
        emailEt = root.findViewById(R.id.emailET);

        prefManager = new PrefManager(getContext());
        String config = prefManager.get("config","color");

        if(config.equals("Color.BLACK")) {
            linearLayout.setBackgroundColor(Color.BLACK);
            nameEt.setBackgroundColor(Color.WHITE);
            emailEt.setBackgroundColor(Color.WHITE);
        }
        else {
            linearLayout.setBackgroundColor(Color.WHITE);
        }

        nameEt = root.findViewById(R.id.nameET);
        emailEt = root.findViewById(R.id.emailET);
        saveBtn = root.findViewById(R.id.saveProfileBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameEt.getText().toString();
                email = emailEt.getText().toString();
                prefManager = new PrefManager(getContext());
                prefManager.set("profile","profileName",name);
                prefManager.set("profile","profileEmail",email);
            }
        });

        return root;
    }
}
