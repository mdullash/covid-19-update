package com.example.covid_19update.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.covid_19update.R;
import com.example.covid_19update.adapter.BlogAdapter;
import com.example.covid_19update.helper.PrefManager;
import com.example.covid_19update.model.Blog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BlogActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BlogAdapter blogAdapter;
    private List<Blog> blogList;
    private DatabaseReference databaseReference;
    private PrefManager prefManager;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);
        this.setTitle("Covid-19 Blog");

        linearLayout = findViewById(R.id.coronaBlogLayout);

        recyclerView = findViewById(R.id.coronaBlog);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //recyclerView.setHasFixedSize(true);
        blogList = new ArrayList<>();

        prefManager = new PrefManager(this);
        String config = prefManager.get("config","color");

        if(config.equals("Color.BLACK")) {
            linearLayout.setBackgroundColor(Color.BLACK);
        }
        else {
            linearLayout.setBackgroundColor(Color.WHITE);
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("message");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Blog blog = dataSnapshot1.getValue(Blog.class);
                    blogList.add(blog);
                }
                blogAdapter = new BlogAdapter(BlogActivity.this,blogList);
                recyclerView.setAdapter(blogAdapter);
                //Toast.makeText(CoronaBlogActivity.this,blogList.size()+"",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(BlogActivity.this,"Failed to load",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
