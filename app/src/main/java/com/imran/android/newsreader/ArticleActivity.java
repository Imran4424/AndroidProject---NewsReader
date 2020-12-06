package com.imran.android.newsreader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import java.util.ArrayList;
import java.util.List;

public class ArticleActivity extends AppCompatActivity {
    public static int position;
    List<String> articleContentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        WebView articleWebView = findViewById(R.id.articleWebView);
    }
}