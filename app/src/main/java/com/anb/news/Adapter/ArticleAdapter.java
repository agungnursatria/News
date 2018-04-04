package com.anb.news.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anb.news.Model.Article;
import com.anb.news.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Agung Nursatria on 3/29/2018.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    Context context;
    ArrayList<Article> articleList;

    public ArticleAdapter(Context context, ArrayList<Article> articleList) {
        this.context = context;
        this.articleList = articleList;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.news_recycler_item, parent, false);
        return new ArticleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        Picasso.get().load(articleList.get(position).urlToImage).into(holder.imgNews);
        holder.txtSource.setText(articleList.get(position).source);
        holder.txtTitle.setText(articleList.get(position).title);
        holder.txtAuthor.setText(articleList.get(position).author);
        holder.txtDatePublished.setText(articleList.get(position).publishedAt);
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder{
        public ImageView imgNews;
        public TextView txtSource;
        public TextView txtTitle;
        public TextView txtAuthor;
        public TextView txtDatePublished;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            imgNews = itemView.findViewById(R.id.);
            txtSource = itemView.findViewById(R.id.txtSource);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtAuthor = itemView.findViewById(R.id.txtAuthor);
            txtDatePublished = itemView.findViewById(R.id.txtPublished);
        }
    }
}
