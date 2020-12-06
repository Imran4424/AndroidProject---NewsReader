package com.imran.android.newsreader;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.imran.android.newsreader.model.ArticleData;
import com.imran.android.newsreader.model.RoomDB;

import java.util.ArrayList;
import java.util.List;

public class ArticleActivity extends AppCompatActivity {
    public static int position;
    private List<String> articleContentList = new ArrayList<>();
    private List<ArticleData> articleDataList = new ArrayList<>();

    private RoomDB database;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        WebView articleWebView = findViewById(R.id.articleWebView);
        articleWebView.getSettings().setJavaScriptEnabled(true);
        articleWebView.setWebViewClient(new WebViewClient());

        database = RoomDB.getInstance(this);
        articleDataList = database.articleDao().getAll();
        Log.i("html in web view", articleDataList.get(position).getContent());

        articleWebView.loadData(articleDataList.get(position).getContent(), "text/html", "UTF-8");
    }
}