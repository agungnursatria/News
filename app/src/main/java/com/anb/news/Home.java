package com.anb.news;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.anb.news.Adapter.ArticleAdapter;
import com.anb.news.Adapter.ArticlePagerAdapter;
import com.anb.news.Model.Article;
import com.anb.news.Model.News;
import com.anb.news.Retrofit.RequestInterface;
import com.anb.news.Retrofit.RetroServer;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Home extends AppCompatActivity {

    private ViewPager headlines;
    private RecyclerView recyclerArticles;
    private ArrayList<Article> articles;
    private ArrayList<Article> headlineArticles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        headlines = findViewById(R.id.newsPager);
        recyclerArticles = findViewById(R.id.recyclerNews);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        requestData();
        recyclerArticles.setHasFixedSize(true);
        recyclerArticles.setLayoutManager(layoutManager);
    }

    private void requestData() {

        RequestInterface request = RetroServer.getClient().create(RequestInterface.class);
        Call<News> call = request.getHeadlines(Constant.IGN_SOURCE,Constant.API_KEY);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                headlineArticles = response.body().articles;
                headlines.setAdapter(new ArticlePagerAdapter(Home.this, headlineArticles));
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Log.e("HOME", "onFailure: Something went wrong: " + t.getMessage());
                Toast.makeText(Home.this, "Failed to retrieve headlines", Toast.LENGTH_SHORT).show();
            }
        });

        call = request.getEverything(Constant.IGN_SOURCE,Constant.API_KEY);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                articles = response.body().articles;
                recyclerArticles.setAdapter(new ArticleAdapter(Home.this, articles));
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Log.e("HOME", "onFailure: Something went wrong: " + t.getMessage());
                Toast.makeText(Home.this, "Failed to retrieve everything", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
