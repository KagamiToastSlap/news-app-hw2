package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class NewsRecyclerViewAdapter  extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder> {


    private final LayoutInflater mInflater;
    private List<NewsItem> mNews;
    private NewsItemViewModel viewModel;

    public NewsRecyclerViewAdapter(Context context, NewsItemViewModel viewModel) {
        this.viewModel = viewModel;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View viewing = mInflater.inflate(R.layout.news_item,parent, false);
        NewsViewHolder newshold = new NewsViewHolder(viewing);
        return newshold;

    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, final int position)
    {
        if(mNews != null) {
            NewsItem cNews = mNews.get(position);
            holder.title.setText("Title: " + cNews.getTitle());
            holder.description.setText("Description: " + cNews.getDescription());
            holder.date.setText("Date: " + cNews.getPublished ());
        }
        else{
            holder.title.setText("Title: No Title");
            holder.description.setText("Description: No Description");
            holder.date.setText("Date: No Date");
        }
    }

    void setNews(List<NewsItem> news){
        mNews = news;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount()
    {
        if(mNews != null)
            return mNews.size();
        else
            return 0;
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        TextView description;
        TextView date;
        LinearLayout playout;

        public NewsViewHolder(View txt) {
            super(txt);
            title = itemView.findViewById(R.id.Title_txt);
            description = itemView.findViewById(R.id.Description_txt);
            date = itemView.findViewById(R.id.Date_txt);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v){
           // playout = itemView.findViewById(R.id.playout);
            Intent clicked = new Intent(Intent.ACTION_VIEW,Uri.parse(mNews.get(getAdapterPosition()).getUrl()));
            v.getContext().startActivity(clicked);
        }


    }

        public List<NewsItem> getmNews(){
            return mNews;
        }

        public void setmNews(List<NewsItem> mNews){
            this.mNews = mNews;
        }
}

