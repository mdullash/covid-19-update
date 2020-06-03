package com.example.covid_19update.ui.about;

import android.media.Image;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AboutViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AboutViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Covid-19 info application. You can get all update of Bangladesh including all ohters country rest of the world");
    }

    public LiveData<String> getText() {
        return mText;
    }
}