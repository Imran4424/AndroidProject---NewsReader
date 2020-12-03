package com.imran.android.newsreader.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * Created by Shah Md Imran Hossain on 04, December, 2020
 */

@Database(entities = {ArticleData.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    // create database instance
    private static RoomDB database;
}
