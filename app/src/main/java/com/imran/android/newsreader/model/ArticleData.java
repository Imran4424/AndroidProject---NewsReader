package com.imran.android.newsreader.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.imran.android.newsreader.Constants;

import java.io.Serializable;

/**
 * Created by Shah Md Imran Hossain on 04, December, 2020
 */

@Entity(tableName = Constants.TABLE_NAME_ARTICLE)
public class ArticleData implements Serializable {
    // create id column
    @PrimaryKey(autoGenerate = true)
    private int ID;

    // create articleID column
    @ColumnInfo(name = Constants.COLUMN_ARTICLE_ID)
    private String articleID;

    // create title column
    @ColumnInfo(name = Constants.COLUMN_TITLE)
    private String title;

    // create content column
    @ColumnInfo(name = Constants.COLUMN_CONTENT)
    private String content;

    public ArticleData() { }

    public ArticleData(String articleID, String title, String content) {
        this.articleID = articleID;
        this.title = title;
        this.content = content;
    }

    // generate getter and setter
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getArticleID() {
        return articleID;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
