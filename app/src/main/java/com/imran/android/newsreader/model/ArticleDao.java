package com.imran.android.newsreader.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.imran.android.newsreader.Constants;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

/**
 * Created by Shah Md Imran Hossain on 04, December, 2020
 */

@Dao
public interface ArticleDao {
    // insert query
    @Insert(onConflict = REPLACE)
    void insert(ArticleData articleData);

    @Delete
    void delete(ArticleData articleData);

    // delete all query
    @Delete
    void reset(List<ArticleData> articleDataList);

    // update query
    @Query("UPDATE " + Constants.TABLE_NAME_ARTICLE +
            " SET title = :uTitle, content = :uContent  WHERE ID = :uID")
    void update(int uID, String uTitle, String uContent);

    // update query
    @Query("UPDATE " + Constants.TABLE_NAME_ARTICLE +
            " SET articleID = :uArticleID, title = :uTitle WHERE ID = :uID")
    void update(int uID, int uArticleID, String uTitle);

    // update query
    @Query("UPDATE " + Constants.TABLE_NAME_ARTICLE +
            " SET articleID = :uArticleID, title = :uTitle, content = :uContent WHERE ID = :uID")
    void update(int uID, int uArticleID, String uTitle, String uContent);

    // get all data query
    @Query("SELECT * FROM " + Constants.TABLE_NAME_ARTICLE)
    List<ArticleData> getAll();
}
