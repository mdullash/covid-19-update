package com.example.covid_19update.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19update.R;
import com.example.covid_19update.model.Blog;

import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.MyViewHolder> {

    private Context context;
    private List<Blog> list;

    public BlogAdapter(Context context, List<Blog> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.blog_layout,parent,false);

        MyViewHolder myViewHolder = new MyViewHolder(root);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.blogTitle.append(list.get(position).getTitle());
        holder.blogBody.append(list.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView blogTitle,blogBody;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            blogTitle = itemView.findViewById(R.id.blogTitleTv);
            blogBody = itemView.findViewById(R.id.blogBodyTv);
        }
    }
}

