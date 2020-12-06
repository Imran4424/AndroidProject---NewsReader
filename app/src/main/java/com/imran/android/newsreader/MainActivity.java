package com.imran.android.newsreader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.imran.android.newsreader.model.ArticleData;
import com.imran.android.newsreader.model.RoomDB;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> titleList = new ArrayList<>();
    List<ArticleData> articleDataList = new ArrayList<>();
    RecyclerView recyclerView;
    NewsListRecyclerAdapter newsListRecyclerAdapter;
    LinearLayoutManager linearLayoutManager;

    RoomDB database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = RoomDB.getInstance(this);

        DownloadTask downloadTask = new DownloadTask();
        try {
            //downloadTask.execute("https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty");
        } catch (Exception e) {
            e.printStackTrace();
        }

        recyclerView = findViewById(R.id.recyclerView);
        newsListRecyclerAdapter = new NewsListRecyclerAdapter(this, titleList);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(newsListRecyclerAdapter);

        updateListView();
    }

    private void updateListView() {
        titleList.clear();
        articleDataList = database.articleDao().getAll();
        for (int i = 0; i < articleDataList.size(); i++) {
            titleList.add(articleDataList.get(i).getTitle());
        }
        newsListRecyclerAdapter.notifyDataSetChanged();
    }

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection httpURLConnection = null;

            try {
                url = new URL(urls[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();

                result = getResultUrl(httpURLConnection);

                JSONArray jsonArray = new JSONArray(result);
                int numberOfItem = 20;

                if (jsonArray.length() < numberOfItem) {
                    numberOfItem = jsonArray.length();
                }

                for (int i = 0; i < numberOfItem; i++) {
                    String articleId = jsonArray.getString(i);
                    url = new URL("https://hacker-news.firebaseio.com/v0/item/" + articleId + ".json?print=pretty");
                    httpURLConnection = (HttpURLConnection) url.openConnection();

                    String articleInfo = getResultUrl(httpURLConnection);

                    JSONObject jsonObject = new JSONObject(articleInfo);

                    if (!jsonObject.isNull("title") && !jsonObject.isNull("url")) {
                        String articleTitle = jsonObject.getString("title");
                        String articleUrl = jsonObject.getString("url");

                        url = new URL(articleUrl);
                        httpURLConnection = (HttpURLConnection) url.openConnection();
                        String articleContent = getResultUrl(httpURLConnection);

                        Log.i("HTML", articleContent);
                        ArticleData articleData = new ArticleData();
                        articleData.setArticleID(articleId);
                        articleData.setTitle(articleTitle);
                        articleData.setContent(articleContent);

                        database.articleDao().insert(articleData);
                        updateListView();
                    }
                }

                Log.i("URL content", result);
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        private String getResultUrl(HttpURLConnection httpURLConnection) throws IOException {
            String result = "";
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            int data = inputStreamReader.read();

            while (data != -1) {
                char current = (char) data;
                result += current;
                data = inputStreamReader.read();
            }
            return result;
        }
    }
}