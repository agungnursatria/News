package com.anb.news.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
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
 * Created by Agung Nursatria on 4/5/2018.
 */

public class ArticlePagerAdapter extends PagerAdapter {

    private ArrayList<Article> articles;
    private LayoutInflater layoutInflater;

    public ArticlePagerAdapter(Context context, ArrayList<Article> articles) {
        this.articles = articles;
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return articles.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = layoutInflater.inflate(R.layout.news_pager_item, container, false);

        ImageView imgCard = v.findViewById(R.id.imgCard);
        TextView titleCard = v.findViewById(R.id.titleCard);
        TextView authorCard = v.findViewById(R.id.authorCard);
        TextView publishedAtCard = v.findViewById(R.id.publishedAtCard);

        Picasso.get().load(articles.get(position).urlToImage).centerCrop().into(imgCard);
        titleCard.setText(articles.get(position).title);
        authorCard.setText(articles.get(position).author);
        publishedAtCard.setText(articles.get(position).publishedAt);

        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
