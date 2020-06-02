package com.example.covid_19update.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public PrefManager(Context context) {
        this.context = context;
    }

    public PrefManager(Context context, SharedPreferences sharedPreferences, SharedPreferences.Editor editor) {
        this.context = context;
        this.sharedPreferences = sharedPreferences;
        this.editor = editor;
    }

    public void set(String sharedPrefName,String keyName,String value) {
        sharedPreferences = context.getSharedPreferences(sharedPrefName,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(keyName,value);
        editor.apply();
    }
    public String get(String sharedPrefName,String keyName) {
        sharedPreferences = context.getSharedPreferences(sharedPrefName,Context.MODE_PRIVATE);
        return sharedPreferences.getString(keyName,"No Value");
    }
}
