package com.example.covid_19update.ui.blog;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.covid_19update.R;
import com.example.covid_19update.helper.PrefManager;
import com.example.covid_19update.model.Blog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BlogFragment extends Fragment {

    private BlogViewModel galleryViewModel;
    private Button saveBlog;
    private EditText title, body;
    private PrefManager prefManager;
    private LinearLayout linearLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(BlogViewModel.class);
        View root = inflater.inflate(R.layout.fragment_blog, container, false);
        final TextView textView = root.findViewById(R.id.text_about);
//        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        linearLayout = root.findViewById(R.id.fr_blog);

        title = root.findViewById(R.id.blogTitle);
        body = root.findViewById(R.id.blogBody);
        saveBlog = root.findViewById(R.id.saveBlogBtn);

        prefManager = new PrefManager(getContext());
        String config = prefManager.get("config","color");

        if(config.equals("Color.BLACK")) {
            linearLayout.setBackgroundColor(Color.BLACK);
            title.setBackgroundColor(Color.WHITE);
            body.setBackgroundColor(Color.WHITE);
        }
        else {
            linearLayout.setBackgroundColor(Color.WHITE);
        }

        saveBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String blogTitle = title.getText().toString();
                String blogBody = body.getText().toString();

                Blog blog = new Blog(blogTitle,blogBody);
                // Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("message");

                myRef.push().setValue(blog);
                Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }
}
